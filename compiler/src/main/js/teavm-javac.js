const DEFAULT_COMPILER_JS_URL = new URL("./compiler.js", import.meta.url);
const DEFAULT_JAVAC_CLASSLIB_URL = new URL("./compile-classlib-teavm.bin", import.meta.url);
const DEFAULT_RUNTIME_CLASSLIB_URL = new URL("./runtime-classlib-teavm.bin", import.meta.url);
const COMPILER_EXPORT_NAMES = [
  "Compiler",
  "ProcessingPreprocessResult",
  "ListenerRegistration",
  "TeaVMDiagnostic",
  "JavaDiagnostic",
  "createCompiler",
  "installWorker",
];

const textDecoder = new TextDecoder();
const compilerRuntimeCache = new Map();

export async function createCompiler(options = {}) {
  const runtime = await loadCompilerRuntime(
    options.compilerJs ?? options.compilerJsUrl ?? options.compilerRuntime ?? DEFAULT_COMPILER_JS_URL
  );
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
  const runtime = await loadCompilerRuntime(
    options.compilerJs ?? options.compilerJsUrl ?? options.compilerRuntime ?? DEFAULT_COMPILER_JS_URL
  );
  runtime.exports.installWorker();
  return runtime;
}

export async function loadCompilerRuntime(input = DEFAULT_COMPILER_JS_URL) {
  if (isCompilerRuntime(input)) {
    return normalizeCompilerRuntime(input);
  }

  if (typeof input !== "string" && !(input instanceof URL)) {
    throw new TypeError("Expected a compiler.js URL or TeaVM compiler runtime object");
  }

  const specifier = String(input);
  if (!compilerRuntimeCache.has(specifier)) {
    compilerRuntimeCache.set(specifier, importCompilerRuntime(specifier));
  }
  return await compilerRuntimeCache.get(specifier);
}

export const loadRuntime = loadCompilerRuntime;

export class JavaCompiler {
  constructor(raw, runtime) {
    this.raw = raw;
    this.runtime = runtime;
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
    await import(specifier);
    runtime = captureCompilerRuntime(globalObject, specifier);
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

function captureCompilerRuntime(globalObject, source) {
  const exports = {};
  for (const name of COMPILER_EXPORT_NAMES) {
    if (globalObject[name] !== undefined) {
      exports[name] = globalObject[name];
    }
  }

  if (typeof exports.createCompiler !== "function") {
    throw new Error(`TeaVM compiler JavaScript did not export createCompiler from ${source}`);
  }
  if (typeof exports.installWorker !== "function") {
    throw new Error(`TeaVM compiler JavaScript did not export installWorker from ${source}`);
  }

  return { exports, source };
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
    return input;
  }
  return {
    exports: input,
    source: null,
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
