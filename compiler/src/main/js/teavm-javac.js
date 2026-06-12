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
const textEncoder = typeof TextEncoder === "function" ? new TextEncoder() : null;
const compilerRuntimeCache = new Map();
let compilerWasmGCSupportPromise = null;

globalThis.__teavmInstallFileImports = installTeaVmFileImports;
installGlobalTeaVmFileModule();

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

export async function loadJavaWasm(wasmBytes, options = {}) {
  const runtimeModule = options.runtimeModule ?? await import(String(
    options.wasmRuntime ??
    options.wasmRuntimeUrl ??
    DEFAULT_COMPILER_WASM_RUNTIME_URL
  ));
  if (typeof runtimeModule.load !== "function") {
    throw new Error("TeaVM Wasm-GC runtime loader did not export load()");
  }
  const runtime = await runtimeModule.load(
    wasmBytes,
    createCompilerWasmRuntimeOptions(options.wasmRuntimeOptions ?? options.runtimeOptions ?? {})
  );
  return setActiveRuntime(runtime);
}

export async function runJavaMain(runtime, args = [], options = {}) {
  if (!runtime || typeof runtime !== "object") {
    throw new TypeError("runJavaMain requires a TeaVM runtime object");
  }
  setActiveRuntime(runtime);
  const main = runtime?.exports?.main;
  if (typeof main !== "function") {
    throw new Error("TeaVM Java runtime did not export main()");
  }
  const state = beginJavaRun(runtime);
  try {
    main(args, () => {
      state.callbackFinished = true;
    });
    await waitForJavaRuntimeStop(runtime, state, options);
  } finally {
    endJavaRun(state);
  }
  options.onFinish?.({ runtime });
  return { runtime };
}

export async function runJavaWasm(wasmBytes, options = {}) {
  const runtime = await loadJavaWasm(wasmBytes, options);
  return await runJavaMain(runtime, options.args ?? [], options);
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
    this.sources = new Map();
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
    this.sources.set(path, String(content));
    return this;
  }

  clearSources() {
    this.raw.clearSourceFiles();
    this.sources.clear();
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
    const moduleType = normalizeModule(options.module ?? options.moduleType ?? "umd");
    const ok = this.raw.generateJavaScript({
      outputName: fileName,
      mainClass: required(options.mainClass, "mainClass"),
      moduleType,
      sourceMap,
      sourceMapName,
      optimizationLevel: normalizeOptimizationLevel(options.optimizationLevel ?? options.optimization ?? "simple"),
      fastGlobalAnalysis: Boolean(options.fastGlobalAnalysis ?? options.fastDependencyAnalysis),
    });
    const rawBytes = ok ? this.js.get(fileName) : null;
    const sourceMapBytes = ok && sourceMap ? this.js.get(sourceMapName) : null;
    const rawText = rawBytes != null ? textDecoder.decode(rawBytes) : null;
    const outputText = rawText != null && moduleType === "ES2015" && options.runtimeModule !== false
      ? appendJavaRuntimeModule(rawText)
      : rawText;
    const bytes = outputText != null && outputText !== rawText ? encodeText(outputText) : rawBytes;

    return {
      ok,
      fileName,
      bytes,
      text: outputText,
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
    createCompilerWasmRuntimeOptions(request.wasmRuntimeOptions)
  ));
  runtime.backend = "wasm-gc";
  runtime.source = wasmInput;
  return runtime;
}

function createCompilerWasmRuntimeOptions(baseOptions = {}) {
  return {
    ...baseOptions,
    installImports(imports) {
      baseOptions.installImports?.(imports);
      installTeaVmFileImports(imports);
    },
  };
}

function installTeaVmFileImports(imports) {
  imports.teavmFile ??= {};
  const fileImports = createTeaVmFileImportModule();
  imports.teavmFile.setActiveRuntime ??= fileImports.setActiveRuntime;
  imports.teavmFile.open ??= fileImports.open;
  imports.teavmFile.openAsyncStart ??= fileImports.openAsyncStart;
  imports.teavmFile.write ??= fileImports.write;
  imports.teavmFile.writeSync ??= fileImports.writeSync;
  imports.teavmFile.close ??= fileImports.close;
  imports.teavmFile.closeSync ??= fileImports.closeSync;
  imports.teavmFile.loadImageAsyncStart ??= fileImports.loadImageAsyncStart;
  imports.teavmFile.loadFontAsyncStart ??= fileImports.loadFontAsyncStart;
  imports.teavmFile.errorResult ??= fileImports.errorResult;
  imports.teavmFile.storeOperationBytes ??= fileImports.storeOperationBytes;
  imports.teavmFile.operationBytes ??= fileImports.operationBytes;
  imports.teavmFile.operationByteLength ??= fileImports.operationByteLength;
  imports.teavmFile.operationByteAt ??= fileImports.operationByteAt;
  imports.teavmFile.releaseOperationBytes ??= fileImports.releaseOperationBytes;
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

export function setActiveRuntime(runtime) {
  globalThis.__teavmActiveRuntime = runtime || null;
  installGlobalTeaVmFileModule();
  return runtime;
}

function appendJavaRuntimeModule(text) {
  return `${text}\n`
    + `export function setActiveRuntime(runtime) {\n`
    + `  globalThis.__teavmActiveRuntime = runtime || null;\n`
    + `  return runtime;\n`
    + `}\n`
    + `export function runJavaMain(args, callback) {\n`
    + `  setActiveRuntime({\n`
    + `    teavm_file_open_complete: typeof teavm_file_open_complete === "function" ? teavm_file_open_complete : undefined,\n`
    + `    teavm_asset_load_complete: typeof teavm_asset_load_complete === "function" ? teavm_asset_load_complete : undefined,\n`
    + `  });\n`
    + `  const entry = typeof $rt_export_main === "function" ? $rt_export_main : main;\n`
    + `  return entry(args, callback);\n`
    + `}\n`;
}

function installGlobalTeaVmFileModule() {
  const fileImports = createTeaVmFileImportModule();
  globalThis.teavmFile ??= {};
  globalThis.teavmFile.setActiveRuntime ??= fileImports.setActiveRuntime;
  globalThis.teavmFile.open ??= fileImports.open;
  globalThis.teavmFile.openAsyncStart ??= fileImports.openAsyncStart;
  globalThis.teavmFile.write ??= fileImports.write;
  globalThis.teavmFile.writeSync ??= fileImports.writeSync;
  globalThis.teavmFile.close ??= fileImports.close;
  globalThis.teavmFile.closeSync ??= fileImports.closeSync;
  globalThis.teavmFile.loadImageAsyncStart ??= fileImports.loadImageAsyncStart;
  globalThis.teavmFile.loadFontAsyncStart ??= fileImports.loadFontAsyncStart;
  globalThis.teavmFile.errorResult ??= fileImports.errorResult;
  globalThis.teavmFile.storeOperationBytes ??= fileImports.storeOperationBytes;
  globalThis.teavmFile.operationBytes ??= fileImports.operationBytes;
  globalThis.teavmFile.operationByteLength ??= fileImports.operationByteLength;
  globalThis.teavmFile.operationByteAt ??= fileImports.operationByteAt;
  globalThis.teavmFile.releaseOperationBytes ??= fileImports.releaseOperationBytes;
}

function createTeaVmFileImportModule() {
  return {
    setActiveRuntime(runtime) {
      return setActiveRuntime(runtime);
    },
    open(pathBytes, modeBytes) {
      return toFileOperationResult(() => openFilePath(decodeFileBytes(pathBytes), decodeFileBytes(modeBytes) || "read"));
    },
    openAsyncStart(requestId, pathBytes, modeBytes) {
      openFilePath(decodeFileBytes(pathBytes), decodeFileBytes(modeBytes) || "read")
        .then((bytes) => storeFileOperationBytes(fileOkResult(bytes)), (reason) => storeFileOperationBytes(fileErrorResult(reason)))
        .then((resultId) => completeFileOpen(requestId, resultId));
    },
    write(pathBytes, data) {
      return toFileOperationStatus(() => {});
    },
    writeSync(pathBytes, data) {
      return storeFileOperationBytes(toFileOperationStatus(() => {}));
    },
    close(pathBytes, modeBytes, data) {
      return toFileOperationStatus(() => {});
    },
    closeSync(pathBytes, modeBytes, data) {
      return storeFileOperationBytes(toFileOperationStatus(() => {}));
    },
    loadImageAsyncStart(requestId, pathBytes, data) {
      loadDecodedImageAsset(decodeFileBytes(pathBytes), data)
        .then((token) => storeFileOperationBytes(fileOkResult(encodeText(token))), (reason) => storeFileOperationBytes(fileErrorResult(reason)))
        .then((resultId) => completeAssetLoad(requestId, resultId));
    },
    loadFontAsyncStart(requestId, pathBytes, data) {
      loadFontAsset(decodeFileBytes(pathBytes), data)
        .then((family) => storeFileOperationBytes(fileOkResult(encodeText(family))), (reason) => storeFileOperationBytes(fileErrorResult(reason)))
        .then((resultId) => completeAssetLoad(requestId, resultId));
    },
    errorResult(error) {
      return fileErrorResult(error);
    },
    storeOperationBytes(value) {
      return storeFileOperationBytes(value);
    },
    operationBytes(value) {
      return typeof value === "number" ? getStoredFileOperationBytes(value) : normalizeFileOperationBytes(value);
    },
    operationByteLength(resultId) {
      return getStoredFileOperationBytes(resultId).length | 0;
    },
    operationByteAt(resultId, index) {
      return getStoredFileOperationBytes(resultId)[index] | 0;
    },
    releaseOperationBytes(resultId) {
      fileOperationByteStore().delete(resultId | 0);
    },
  };
}

function fileOperationByteStore() {
  return globalThis.__teavmJavacFileOperationBytes ??= new Map();
}

function storeFileOperationBytes(value) {
  const store = fileOperationByteStore();
  const bytes = normalizeFileOperationBytes(value);
  let id = globalThis.__teavmJavacFileOperationBytesNextId = (globalThis.__teavmJavacFileOperationBytesNextId ?? 0) + 1;
  if (id > 0x7fffffff) {
    id = 1;
    globalThis.__teavmJavacFileOperationBytesNextId = id;
  }
  store.set(id, bytes);
  return id;
}

function getStoredFileOperationBytes(resultId) {
  const bytes = fileOperationByteStore().get(resultId | 0);
  return bytes ?? fileErrorResult(new Error("File operation result was released"));
}

function completeFileOpen(requestId, resultId) {
  const runtime = globalThis.__teavmActiveRuntime;
  const complete = runtime?.exports?.teavm_file_open_complete
    ?? runtime?.instance?.exports?.teavm_file_open_complete
    ?? runtime?.teavm_file_open_complete
    ?? globalThis.teavm_file_open_complete;
  if (typeof complete !== "function") {
    throw new Error("TeaVM file runtime is not installed");
  }
  try {
    complete(requestId | 0, resultId | 0);
  } catch (error) {
    notifyJavaRunError(runtime, error);
    throw error;
  }
}

function completeAssetLoad(requestId, resultId) {
  const runtime = globalThis.__teavmActiveRuntime;
  const complete = runtime?.exports?.teavm_asset_load_complete
    ?? runtime?.instance?.exports?.teavm_asset_load_complete
    ?? runtime?.teavm_asset_load_complete
    ?? globalThis.teavm_asset_load_complete;
  if (typeof complete !== "function") {
    throw new Error("TeaVM asset runtime is not installed");
  }
  try {
    complete(requestId | 0, resultId | 0);
  } catch (error) {
    notifyJavaRunError(runtime, error);
    throw error;
  }
}

function beginJavaRun(runtime) {
  const state = { runtime, error: null, callbackFinished: false };
  globalThis.__teavmJavacJavaRunState = state;
  return state;
}

function endJavaRun(state) {
  if (globalThis.__teavmJavacJavaRunState === state) {
    globalThis.__teavmJavacJavaRunState = null;
  }
}

function notifyJavaRunError(runtime, error) {
  const state = globalThis.__teavmJavacJavaRunState;
  if (state && state.runtime === runtime) {
    state.error = error;
  }
}

async function waitForJavaRuntimeStop(runtime, state, options = {}) {
  const timeoutMs = options.timeoutMs ?? 0;
  const started = Date.now();
  while (!isJavaRuntimeStopped(runtime)) {
    if (state.error) {
      throw state.error;
    }
    if (timeoutMs > 0 && Date.now() - started > timeoutMs) {
      throw new Error("Timed out waiting for Java main to finish");
    }
    await waitForNextTask();
  }
  if (state.error) {
    throw state.error;
  }
}

function isJavaRuntimeStopped(runtime) {
  const stopped = runtime?.instance?.exports?.teavm_stopped
    ?? runtime?.exports?.teavm_stopped
    ?? runtime?.teavm_stopped;
  return typeof stopped === "function" ? Boolean(stopped()) : false;
}

function waitForNextTask() {
  return new Promise((resolve) => setTimeout(resolve, 0));
}

function normalizeFileOperationBytes(value) {
  if (value instanceof Int8Array) {
    return value;
  }
  if (value instanceof Uint8Array || ArrayBuffer.isView(value)) {
    return new Int8Array(value.buffer.slice(value.byteOffset, value.byteOffset + value.byteLength));
  }
  if (value instanceof ArrayBuffer) {
    return new Int8Array(value.slice(0));
  }
  return fileErrorResult(new TypeError("File operation did not return bytes"));
}

function toFileOperationResult(operation) {
  try {
    const value = operation();
    if (isPromiseLike(value)) {
      return value.then(fileOkResult, fileErrorResult);
    }
    return fileOkResult(value);
  } catch (error) {
    return fileErrorResult(error);
  }
}

function toFileOperationStatus(operation) {
  return toFileOperationResult(() => {
    const value = operation();
    if (isPromiseLike(value)) {
      throw new TypeError("File write/close callbacks must be synchronous");
    }
    return new Uint8Array(0);
  });
}

function fileOkResult(value = new Uint8Array(0)) {
  const bytes = normalizeFileContent(value) ?? new Uint8Array(0);
  const result = new Int8Array(bytes.length + 1);
  result[0] = 0;
  result.set(bytes, 1);
  return result;
}

function fileErrorResult(error) {
  const message = encodeText(safeErrorMessage(error));
  const result = new Int8Array(message.length + 1);
  result[0] = 1;
  result.set(message, 1);
  return result;
}

function safeErrorMessage(error) {
  if (error == null) {
    return String(error);
  }
  try {
    const message = error.message;
    if (typeof message === "string") {
      return message;
    }
    if (message != null) {
      return String(message);
    }
  } catch {
  }
  try {
    const name = error.name;
    if (typeof name === "string") {
      return name;
    }
    if (name != null) {
      return String(name);
    }
  } catch {
  }
  try {
    return Object.prototype.toString.call(error);
  } catch {
    return "File operation failed";
  }
}

async function openFilePath(path, mode = "read") {
  const normalizedPath = normalizeStorePath(path);
  mode = normalizeFileMode(mode);
  if (mode === "write" || mode === "append") {
    return new Uint8Array(0);
  }
  return await fetchFilePath(normalizedPath);
}

async function fetchFilePath(path) {
  if (typeof fetch !== "function") {
    throw new Error("fetch is not available for file read");
  }
  const response = await fetch(path);
  if (!response.ok) {
    throw createFileNotFoundError(path);
  }
  return new Uint8Array(await response.arrayBuffer());
}

function isPromiseLike(value) {
  return value != null && typeof value.then === "function";
}

function normalizeFileContent(value) {
  if (value == null) {
    return null;
  }
  if (typeof value === "string") {
    return encodeText(value);
  }
  if (value instanceof ArrayBuffer) {
    return new Int8Array(value);
  }
  if (ArrayBuffer.isView(value)) {
    return new Int8Array(value.buffer, value.byteOffset, value.byteLength);
  }
  if (Array.isArray(value)) {
    return Int8Array.from(value);
  }
  throw new TypeError("file content must be string, ArrayBuffer, typed array, array, null, or undefined");
}

function normalizeFilePath(path, pathBytes) {
  if (typeof path === "string") {
    return path;
  }
  if (pathBytes != null) {
    return decodeFileBytes(pathBytes);
  }
  return String(path);
}

function normalizeFileMode(mode, modeBytes) {
  if (typeof mode === "string" && mode.length > 0) {
    return mode;
  }
  if (modeBytes != null) {
    return normalizeFilePath(null, modeBytes);
  }
  return "read";
}

function decodeFileBytes(value) {
  const bytes = copyFileWriteData(value);
  if (typeof TextDecoder === "function") {
    return new TextDecoder().decode(bytes);
  }
  let result = "";
  for (let i = 0; i < bytes.length; i++) {
    result += String.fromCharCode(bytes[i]);
  }
  return result;
}

function resolveFileAssetUrl(path, mimeType, data = null) {
  if (data == null) {
    throw new Error(`File bytes were not provided for asset: ${path}`);
  }
  const bytes = copyFileWriteData(data);
  const cache = fileAssetUrlCache();
  const key = `${path}\0${mimeType ?? ""}`;
  const cached = cache.get(key);
  if (cached) {
    return cached;
  }
  const url = URL.createObjectURL(new Blob([bytes], { type: mimeType || "application/octet-stream" }));
  cache.set(key, url);
  return url;
}

async function loadDecodedImageAsset(path, data) {
  const bytes = copyFileWriteData(data);
  const mimeType = guessMimeType(path);
  const url = resolveFileAssetUrl(path, mimeType, bytes);
  const image = new Image();
  image.src = url;
  if (typeof image.decode === "function") {
    await image.decode();
  } else {
    await new Promise((resolve, reject) => {
      image.onload = () => resolve();
      image.onerror = () => reject(new Error(`Could not decode image: ${path}`));
    });
  }
  if (!image.naturalWidth || !image.naturalHeight) {
    throw new Error(`Could not decode image: ${path}`);
  }
  const token = nextDecodedImageToken();
  decodedImageStore().set(token, image);
  return token;
}

function nextDecodedImageToken() {
  const id = globalThis.__teavmJavacImageNextId = (globalThis.__teavmJavacImageNextId ?? 0) + 1;
  return `teavm-decoded-image:${id}`;
}

function decodedImageStore() {
  return globalThis.__teavmJavacDecodedImages ??= new Map();
}

async function loadFontAsset(path, data) {
  const family = cssFontFamilyFromPath(path);
  if (data == null) {
    throw new Error(`File bytes were not provided for font: ${path}`);
  }
  if (typeof FontFace !== "function" || !globalThis.document?.fonts) {
    return family;
  }
  const cache = fileFontCache();
  if (cache.has(path)) {
    return cache.get(path);
  }
  const bytes = copyFileWriteData(data);
  const source = bytes.buffer.slice(bytes.byteOffset, bytes.byteOffset + bytes.byteLength);
  const font = new FontFace(family, source);
  await font.load();
  globalThis.document.fonts.add(font);
  cache.set(path, family);
  return family;
}

function cssFontFamilyFromPath(path) {
  const name = String(path ?? "font")
    .replace(/\\/g, "/")
    .replace(/^.*\//, "")
    .replace(/\.(ttf|otf)$/i, "")
    .replace(/[^a-zA-Z0-9_-]+/g, "_");
  return name || "font";
}

function guessMimeType(path) {
  const lower = String(path ?? "").toLowerCase();
  if (lower.endsWith(".png")) {
    return "image/png";
  }
  if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) {
    return "image/jpeg";
  }
  if (lower.endsWith(".gif")) {
    return "image/gif";
  }
  if (lower.endsWith(".webp")) {
    return "image/webp";
  }
  if (lower.endsWith(".svg") || lower.endsWith(".svgz")) {
    return "image/svg+xml";
  }
  if (lower.endsWith(".bmp")) {
    return "image/bmp";
  }
  return "application/octet-stream";
}

function createFileNotFoundError(path) {
  const error = new Error(`File not found: ${path}`);
  error.name = "FileNotFoundError";
  error.path = path;
  return error;
}

function normalizeStorePath(path) {
  return String(path ?? "").replace(/\\/g, "/");
}

function invalidateFileAssetCaches(path) {
  const assetUrls = globalThis.__teavmJavacAssetUrls;
  if (assetUrls) {
    for (const [key, url] of assetUrls) {
      if (key.startsWith(`${path}\0`)) {
        URL.revokeObjectURL(url);
        assetUrls.delete(key);
      }
    }
  }
  globalThis.__teavmJavacFonts?.delete(path);
}

function fileAssetUrlCache() {
  return globalThis.__teavmJavacAssetUrls ??= new Map();
}

function fileFontCache() {
  return globalThis.__teavmJavacFonts ??= new Map();
}

function copyFileWriteData(data) {
  if (data instanceof Uint8Array) {
    return new Uint8Array(data);
  }
  if (ArrayBuffer.isView(data)) {
    return new Uint8Array(data.buffer.slice(data.byteOffset, data.byteOffset + data.byteLength));
  }
  if (data instanceof ArrayBuffer) {
    return new Uint8Array(data.slice(0));
  }
  return Uint8Array.from(data);
}

function encodeText(value) {
  if (textEncoder != null) {
    return new Int8Array(textEncoder.encode(value).buffer);
  }
  const bytes = new Int8Array(value.length);
  for (let i = 0; i < value.length; i++) {
    bytes[i] = value.charCodeAt(i) & 255;
  }
  return bytes;
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
