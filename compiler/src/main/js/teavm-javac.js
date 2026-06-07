const DEFAULT_COMPILER_JS_URL = new URL("./compiler.js", import.meta.url);
const DEFAULT_COMPILER_WASM_URL = new URL("./compiler.wasm", import.meta.url);
const DEFAULT_COMPILER_WASM_RUNTIME_URL = new URL("./compiler.wasm-runtime.js", import.meta.url);
const DEFAULT_JAVAC_CLASSLIB_URL = new URL("./compile-classlib-teavm.bin", import.meta.url);
const DEFAULT_RUNTIME_CLASSLIB_URL = new URL("./runtime-classlib-teavm.bin", import.meta.url);
const DEFAULT_COMPILER_BACKEND = "auto";
const COMPILER_EXPORT_NAMES = [
  "Compiler",
  "ProcessingPreprocessResult",
  "ListenerRegistration",
  "TeaVMDiagnostic",
  "JavaDiagnostic",
  "createCompiler",
  "installWorker",
];

const WASM_GC_JS_STRING_PROBE_BYTES = new Uint8Array([
  0, 97, 115, 109, 1, 0, 0, 0, 1, 7, 1, 96, 1, 127, 1, 100, 111, 2, 31, 1, 14,
  119, 97, 115, 109, 58, 106, 115, 45, 115, 116, 114, 105, 110, 103, 12, 102,
  114, 111, 109, 67, 104, 97, 114, 67, 111, 100, 101, 0, 0, 3, 1, 0, 5, 4, 1,
  1, 0, 0, 7, 10, 1, 6, 109, 101, 109, 111, 114, 121, 2, 0, 10, 129, 128, 128,
  0, 0,
]);

const textDecoder = new TextDecoder();
const compilerRuntimeCache = new Map();
let compilerWasmGCSupportPromise = null;

export async function createCompiler(options = {}) {
  const runtime = await loadCompilerRuntime(resolveCompilerRuntimeInput(options));
  const compiler = new JavaCompiler(runtime.exports.createCompiler(), runtime);

  if (options.loadClasslib !== false) {
    await compiler.setClasslib({
      javac: options.javacClasslib ?? options.javacClasslibUrl ?? DEFAULT_JAVAC_CLASSLIB_URL,
      runtime: options.runtimeClasslib ?? options.runtimeClasslibUrl ?? DEFAULT_RUNTIME_CLASSLIB_URL,
    });
  }

  return compiler;
}

export async function installWorker(options = {}) {
  const runtime = await loadCompilerRuntime(resolveCompilerRuntimeInput(options));
  runtime.exports.installWorker();
  return runtime;
}

export async function loadCompilerRuntime(input = {}) {
  if (isCompilerRuntime(input)) {
    return normalizeCompilerRuntime(input);
  }

  const request = normalizeCompilerRuntimeRequest(input);
  const cacheKey = compilerRuntimeCacheKey(request);

  if (cacheKey == null) {
    return await loadCompilerRuntimeRequest(request);
  }
  if (!compilerRuntimeCache.has(cacheKey)) {
    compilerRuntimeCache.set(cacheKey, loadCompilerRuntimeRequest(request));
  }
  return await compilerRuntimeCache.get(cacheKey);
}

export const loadRuntime = loadCompilerRuntime;

export async function supportsCompilerWasmGC() {
  return (await getCompilerWasmGCSupport()).supported;
}

export async function getCompilerWasmGCSupport() {
  if (compilerWasmGCSupportPromise == null) {
    compilerWasmGCSupportPromise = probeCompilerWasmGC();
  }
  return await compilerWasmGCSupportPromise;
}

export class JavaCompiler {
  constructor(raw, runtime) {
    this.raw = raw;
    this.runtime = runtime;
    this.startupDiagnostics = Array.from(runtime.diagnostics ?? [], cloneDiagnostic);
    this.classes = createFileSet(
      () => raw.listOutputFiles(),
      (path) => raw.getOutputFile(path),
      () => raw.getOutputJar()
    );
    this.wasm = createFileSet(
      () => raw.listWebAssemblyOutputFiles(),
      (path) => raw.getWebAssemblyOutputFile(path),
      () => raw.getWebAssemblyOutputArchive()
    );
    this.js = createFileSet(
      () => raw.listJavaScriptOutputFiles(),
      (path) => raw.getJavaScriptOutputFile(path),
      () => raw.getJavaScriptOutputArchive()
    );
  }

  async setClasslib({ javac, runtime }) {
    if (javac != null) {
      this.raw.setSdk(await readBinary(javac));
    }
    if (runtime != null) {
      this.raw.setTeaVMClasslib(await readBinary(runtime));
    }
    return this;
  }

  addSource(path, content) {
    this.raw.addSourceFile(path, content);
    return this;
  }

  clearSources() {
    this.raw.clearSourceFiles();
    return this;
  }

  preprocessProcessing(sources, options = {}) {
    const normalized = normalizeProcessingSources(sources);
    const sketchName = options.sketchName ?? options.className ?? options.name ?? firstSourceBaseName(normalized[0].path);
    this.raw.clearProcessingSourceFiles();
    for (const source of normalized) {
      this.raw.addProcessingSourceFile(source.path, source.content);
    }
    const result = this.raw.preprocessProcessing(sketchName);
    return normalizeProcessingPreprocessResult(result);
  }

  addDependency(path, bytes) {
    this.raw.addClassFile(path, toBytes(bytes));
    return this;
  }

  addDependencyArchive(bytes) {
    this.raw.addJarFile(toBytes(bytes));
    return this;
  }

  clearDependencies() {
    this.raw.clearInputClassFiles();
    return this;
  }

  addClassFile(path, bytes) {
    this.raw.addOutputClassFile(path, toBytes(bytes));
    return this;
  }

  addClassArchive(bytes) {
    this.raw.addOutputJarFile("", toBytes(bytes));
    return this;
  }

  clearClassFiles() {
    this.raw.clearOutputFiles();
    return this;
  }

  onDiagnostic(listener) {
    for (const diagnostic of this.startupDiagnostics) {
      listener(cloneDiagnostic(diagnostic));
    }
    const registration = this.raw.onDiagnostic(listener);
    return {
      unsubscribe: () => registration.destroy(),
      dispose: () => registration.destroy(),
      raw: registration,
    };
  }

  compile() {
    return this.raw.compile();
  }

  findMainClasses() {
    return Array.from(this.raw.detectMainClasses());
  }

  emitWasm(options = {}) {
    const name = options.name ?? options.outputName ?? "app";
    const fileName = name.endsWith(".wasm") ? name : `${name}.wasm`;
    const outputName = name.endsWith(".wasm") ? name.slice(0, -".wasm".length) : name;
    const ok = this.raw.generateWebAssembly({
      outputName,
      mainClass: required(options.mainClass, "mainClass"),
      optimizationLevel: normalizeOptimizationLevel(options.optimizationLevel ?? options.optimization ?? "simple"),
      fastGlobalAnalysis: Boolean(options.fastGlobalAnalysis ?? options.fastDependencyAnalysis),
    });
    const bytes = ok ? this.wasm.get(fileName) : null;

    return {
      ok,
      fileName,
      bytes,
      files: this.wasm.list(),
    };
  }

  emitJs(options = {}) {
    const fileName = options.fileName ?? options.name ?? options.outputName ?? "classes.js";
    const sourceMapName = options.sourceMapName ?? `${fileName}.map`;
    const sourceMap = Boolean(options.sourceMap ?? options.sourceMaps);
    const ok = this.raw.generateJavaScript({
      outputName: fileName,
      mainClass: required(options.mainClass, "mainClass"),
      moduleType: normalizeModule(options.module ?? options.moduleType ?? "umd"),
      sourceMap,
      sourceMapName,
      optimizationLevel: normalizeOptimizationLevel(options.optimizationLevel ?? options.optimization ?? "simple"),
      fastGlobalAnalysis: Boolean(options.fastGlobalAnalysis ?? options.fastDependencyAnalysis),
    });
    const bytes = ok ? this.js.get(fileName) : null;
    const sourceMapBytes = ok && sourceMap ? this.js.get(sourceMapName) : null;

    return {
      ok,
      fileName,
      bytes,
      text: bytes != null ? textDecoder.decode(bytes) : null,
      sourceMapName,
      sourceMapBytes,
      sourceMapText: sourceMapBytes != null ? textDecoder.decode(sourceMapBytes) : null,
      files: this.js.list(),
    };
  }
}

async function importCompilerRuntime(specifier) {
  const globalObject = globalThis;
  const previousExports = snapshotGlobals(globalObject);
  const hadSelf = "self" in globalObject;
  const previousSelf = globalObject.self;

  if (typeof globalObject.self === "undefined") {
    setGlobal(globalObject, "self", globalObject);
  }

  let runtime;
  try {
    const moduleNamespace = await import(specifier);
    runtime = captureCompilerRuntime(globalObject, specifier, moduleNamespace);
  } finally {
    restoreGlobals(globalObject, previousExports);
    if (!hadSelf) {
      try {
        delete globalObject.self;
      } catch {
        globalObject.self = undefined;
      }
    } else {
      setGlobal(globalObject, "self", previousSelf);
    }
  }

  return runtime;
}

async function loadCompilerRuntimeRequest(request) {
  switch (request.backend) {
    case "js":
      return await importCompilerRuntime(String(request.compilerJs));
    case "wasm-gc":
      return await importCompilerWasmRuntime(request);
    case "auto":
      return await importBestCompilerRuntime(request);
    default:
      throw new TypeError(`Unsupported compiler backend: ${request.backend}`);
  }
}

async function importBestCompilerRuntime(request) {
  const support = await getCompilerWasmGCSupport();
  if (support.supported) {
    try {
      return await importCompilerWasmRuntime(request);
    } catch (error) {
      if (request.fallbackToJs === false) {
        throw error;
      }
      return await importCompilerRuntimeWithDiagnostics(String(request.compilerJs), [
        createWasmFallbackDiagnostic(`the Wasm-GC compiler runtime failed to load: ${error?.message ?? error}`),
      ]);
    }
  }
  return await importCompilerRuntimeWithDiagnostics(String(request.compilerJs), [
    createWasmFallbackDiagnostic(support.reason ?? "Wasm-GC or JSPI is not available"),
  ]);
}

async function importCompilerWasmRuntime(request) {
  const runtimeModule = await import(String(request.compilerWasmRuntime));
  if (typeof runtimeModule.load !== "function") {
    throw new Error(`TeaVM Wasm-GC runtime did not export load() from ${request.compilerWasmRuntime}`);
  }

  const wasmInput = typeof request.compilerWasm === "string" || request.compilerWasm instanceof URL
    ? String(request.compilerWasm)
    : request.compilerWasm;
  const runtime = normalizeCompilerRuntime(await runtimeModule.load(
    wasmInput,
    request.wasmRuntimeOptions
  ));
  runtime.backend = "wasm-gc";
  runtime.source = wasmInput;
  return runtime;
}

async function importCompilerRuntimeWithDiagnostics(specifier, diagnostics) {
  const runtime = await importCompilerRuntime(specifier);
  runtime.diagnostics = [
    ...(runtime.diagnostics ?? []),
    ...diagnostics.map(cloneDiagnostic),
  ];
  return runtime;
}

function captureCompilerRuntime(globalObject, source, moduleNamespace = null) {
  const exports = {};
  copyCompilerExports(exports, moduleNamespace);
  copyCompilerExports(exports, moduleNamespace?.default);
  copyCompilerExports(exports, moduleNamespace?.["module.exports"]);
  for (const name of COMPILER_EXPORT_NAMES) {
    if (globalObject[name] !== undefined) {
      exports[name] = globalObject[name];
    }
  }

  assertCompilerRuntimeExports(exports, source);

  return { exports, source, backend: "js" };
}

function copyCompilerExports(target, source) {
  if (source == null || typeof source !== "object") {
    return;
  }
  for (const name of COMPILER_EXPORT_NAMES) {
    if (source[name] !== undefined && target[name] === undefined) {
      target[name] = source[name];
    }
  }
}

function snapshotGlobals(globalObject) {
  const snapshot = new Map();
  for (const name of COMPILER_EXPORT_NAMES) {
    snapshot.set(name, {
      had: Object.prototype.hasOwnProperty.call(globalObject, name),
      value: globalObject[name],
    });
  }
  return snapshot;
}

function restoreGlobals(globalObject, snapshot) {
  for (const [name, entry] of snapshot.entries()) {
    if (entry.had) {
      setGlobal(globalObject, name, entry.value);
    } else {
      try {
        delete globalObject[name];
      } catch {
        setGlobal(globalObject, name, undefined);
      }
    }
  }
}

function setGlobal(globalObject, name, value) {
  try {
    globalObject[name] = value;
  } catch {
    // Some hosts expose non-writable globals; failing to restore them is non-fatal.
  }
}

function isCompilerRuntime(input) {
  return input != null
    && typeof input === "object"
    && (
      typeof input.createCompiler === "function"
      || typeof input.exports?.createCompiler === "function"
    );
}

function normalizeCompilerRuntime(input) {
  if (typeof input.exports?.createCompiler === "function") {
    assertCompilerRuntimeExports(input.exports, input.source ?? null);
    return input;
  }
  assertCompilerRuntimeExports(input, null);
  return {
    exports: input,
    source: null,
    diagnostics: Array.from(input.diagnostics ?? [], cloneDiagnostic),
  };
}

function assertCompilerRuntimeExports(exports, source) {
  if (typeof exports.createCompiler !== "function") {
    throw new Error(`TeaVM compiler runtime did not export createCompiler${source ? ` from ${source}` : ""}`);
  }
  if (typeof exports.installWorker !== "function") {
    throw new Error(`TeaVM compiler runtime did not export installWorker${source ? ` from ${source}` : ""}`);
  }
}

function resolveCompilerRuntimeInput(options) {
  if (options.compilerRuntime != null) {
    return options.compilerRuntime;
  }
  if (isCompilerRuntime(options.compilerJs)) {
    return options.compilerJs;
  }
  if (options.compilerJs != null || options.compilerJsUrl != null) {
    return {
      ...options,
      backend: "js",
      compilerJs: options.compilerJs ?? options.compilerJsUrl,
    };
  }
  if (
    options.compilerWasm != null
    || options.compilerWasmUrl != null
    || options.compilerWasmRuntime != null
    || options.compilerWasmRuntimeUrl != null
  ) {
    return {
      ...options,
      backend: options.backend ?? options.compilerBackend ?? "wasm-gc",
    };
  }
  return options;
}

function normalizeCompilerRuntimeRequest(input) {
  if (typeof input === "string" || input instanceof URL) {
    return {
      backend: "js",
      compilerJs: input,
      compilerWasm: DEFAULT_COMPILER_WASM_URL,
      compilerWasmRuntime: DEFAULT_COMPILER_WASM_RUNTIME_URL,
      wasmRuntimeOptions: {},
      fallbackToJs: true,
    };
  }
  if (input == null || typeof input !== "object") {
    throw new TypeError("Expected compiler runtime options, a compiler.js URL, or a TeaVM compiler runtime object");
  }

  const compilerJs = input.compilerJs ?? input.compilerJsUrl ?? input.js ?? input.jsUrl ?? DEFAULT_COMPILER_JS_URL;
  const compilerWasm = input.compilerWasm ?? input.compilerWasmUrl ?? input.wasm ?? input.wasmUrl ?? DEFAULT_COMPILER_WASM_URL;
  const compilerWasmRuntime = input.compilerWasmRuntime
    ?? input.compilerWasmRuntimeUrl
    ?? input.wasmRuntime
    ?? input.wasmRuntimeUrl
    ?? DEFAULT_COMPILER_WASM_RUNTIME_URL;
  const backend = normalizeCompilerBackend(
    input.backend
      ?? input.compilerBackend
      ?? input.runtimeBackend
      ?? DEFAULT_COMPILER_BACKEND
  );

  return {
    backend,
    compilerJs,
    compilerWasm,
    compilerWasmRuntime,
    wasmRuntimeOptions: input.wasmRuntimeOptions ?? input.runtimeOptions ?? {},
    fallbackToJs: input.fallbackToJs !== false,
  };
}

function normalizeCompilerBackend(value) {
  const normalized = String(value ?? DEFAULT_COMPILER_BACKEND).toLowerCase().replaceAll("_", "-");
  switch (normalized) {
    case "auto":
    case "best":
    case "default":
      return "auto";
    case "js":
    case "javascript":
      return "js";
    case "wasm":
    case "wasm-gc":
    case "wasmgc":
      return "wasm-gc";
    default:
      return normalized;
  }
}

function compilerRuntimeCacheKey(request) {
  if (
    isBinaryInput(request.compilerWasm)
    || request.wasmRuntimeOptions == null
    || Object.keys(request.wasmRuntimeOptions).length > 0
  ) {
    return null;
  }
  return [
    request.backend,
    String(request.compilerJs),
    String(request.compilerWasm),
    String(request.compilerWasmRuntime),
    request.fallbackToJs,
  ].join("|");
}

function isBinaryInput(value) {
  return value instanceof ArrayBuffer || ArrayBuffer.isView(value);
}

async function probeCompilerWasmGC() {
  const wasm = globalThis.WebAssembly;
  if (wasm == null) {
    return unsupportedCompilerWasmGC("WebAssembly is not available");
  }
  if (typeof wasm.compile !== "function" || typeof wasm.instantiate !== "function") {
    return unsupportedCompilerWasmGC("WebAssembly.compile or WebAssembly.instantiate is not available");
  }
  if (typeof wasm.Suspending !== "function" || typeof wasm.promising !== "function") {
    return unsupportedCompilerWasmGC("JSPI is not available");
  }

  try {
    const module = await wasm.compile(WASM_GC_JS_STRING_PROBE_BYTES, {
      builtins: ["js-string"],
    });
    await wasm.instantiate(module, {});
    return { supported: true, reason: null };
  } catch (error) {
    return unsupportedCompilerWasmGC(
      `Wasm-GC or js-string builtins are not available: ${error?.message ?? error}`
    );
  }
}

function unsupportedCompilerWasmGC(reason) {
  return { supported: false, reason };
}

function createWasmFallbackDiagnostic(reason) {
  return {
    type: "teavm-runtime",
    severity: "warning",
    fileName: null,
    message: `TeaVM Wasm-GC compiler backend is not available (${reason}); falling back to the JavaScript compiler backend. Use a supporting browser such as modern Chrome for the Wasm-GC compiler runtime.`,
  };
}

function cloneDiagnostic(diagnostic) {
  return {
    ...diagnostic,
  };
}

function createFileSet(listFiles, getFile, getArchive) {
  return {
    list: () => Array.from(listFiles()),
    get: (path) => getFile(path),
    archive: () => getArchive(),
    text: (path) => {
      const bytes = getFile(path);
      return bytes != null ? textDecoder.decode(bytes) : null;
    },
  };
}

async function readBinary(input) {
  if (input == null) {
    throw new TypeError("Expected a URL, ArrayBuffer, or typed array");
  }
  if (typeof input === "string" || input instanceof URL) {
    const response = await fetch(input);
    if (!response.ok) {
      throw new Error(`Failed to fetch ${input}: ${response.status} ${response.statusText}`);
    }
    return new Int8Array(await response.arrayBuffer());
  }
  return toBytes(input);
}

function toBytes(input) {
  if (input instanceof Int8Array) {
    return input;
  }
  if (input instanceof ArrayBuffer) {
    return new Int8Array(input);
  }
  if (ArrayBuffer.isView(input)) {
    return new Int8Array(input.buffer, input.byteOffset, input.byteLength);
  }
  throw new TypeError("Expected an ArrayBuffer or typed array");
}

function normalizeModule(module) {
  const normalized = String(module).toLowerCase().replaceAll("_", "-");
  switch (normalized) {
    case "cjs":
    case "commonjs":
    case "common-js":
      return "COMMON_JS";
    case "esm":
    case "es":
    case "es2015":
    case "module":
      return "ES2015";
    case "script":
    case "none":
    case "global":
      return "NONE";
    case "umd":
      return "UMD";
    default:
      return String(module);
  }
}

function normalizeOptimizationLevel(level) {
  const normalized = String(level).toLowerCase().replaceAll("-", "_");
  switch (normalized) {
    case "simple":
      return "SIMPLE";
    case "advanced":
      return "ADVANCED";
    case "full":
      return "FULL";
    default:
      return String(level);
  }
}

function normalizeProcessingSources(sources) {
  if (!Array.isArray(sources) || sources.length === 0) {
    throw new TypeError("Expected at least one Processing source");
  }
  return sources.map((source, index) => {
    if (typeof source === "string") {
      return {
        path: index === 0 ? "sketch.pde" : `sketch${index + 1}.pde`,
        content: source,
      };
    }
    if (source == null || typeof source.content !== "string") {
      throw new TypeError("Processing sources must be strings or { path, content } objects");
    }
    return {
      path: source.path ?? source.name ?? (index === 0 ? "sketch.pde" : `sketch${index + 1}.pde`),
      content: source.content,
    };
  });
}

function normalizeProcessingPreprocessResult(result) {
  return {
    ok: result.ok,
    sourceFileName: result.sourceFileName,
    className: result.className,
    javaSource: result.javaSource,
    programType: result.programType,
    lineOffset: result.lineOffset,
    sketchWidth: result.sketchWidth,
    sketchHeight: result.sketchHeight,
    sketchRenderer: result.sketchRenderer,
    tabs: JSON.parse(result.tabsJson ?? "[]"),
    edits: JSON.parse(result.editsJson ?? "[]"),
    issues: JSON.parse(result.issuesJson ?? "[]"),
    raw: result,
  };
}

function firstSourceBaseName(path) {
  const name = String(path ?? "ProcessingSketch").split(/[\\/]/).pop().replace(/\.[^.]*$/, "");
  return name || "ProcessingSketch";
}

function required(value, name) {
  if (value == null || value === "") {
    throw new TypeError(`${name} is required`);
  }
  return value;
}
