import { load } from "./compiler.wasm-runtime.js";

const DEFAULT_WASM_URL = new URL("./compiler.wasm", import.meta.url);
const DEFAULT_JAVAC_CLASSLIB_URL = new URL("./compile-classlib-teavm.bin", import.meta.url);
const DEFAULT_RUNTIME_CLASSLIB_URL = new URL("./runtime-classlib-teavm.bin", import.meta.url);

const textDecoder = new TextDecoder();

export async function createCompiler(options = {}) {
  const wasm = await readBinary(options.wasm ?? options.wasmUrl ?? DEFAULT_WASM_URL);
  const runtime = await load(wasm, options.runtimeOptions);
  const compiler = new JavaCompiler(runtime.exports.createCompiler(), runtime);

  if (options.loadClasslib !== false) {
    await compiler.setClasslib({
      javac: options.javacClasslib ?? options.javacClasslibUrl ?? DEFAULT_JAVAC_CLASSLIB_URL,
      runtime: options.runtimeClasslib ?? options.runtimeClasslibUrl ?? DEFAULT_RUNTIME_CLASSLIB_URL,
    });
  }

  return compiler;
}

export async function installWorker() {
  const wasm = await readBinary(DEFAULT_WASM_URL);
  const runtime = await load(wasm);
  runtime.exports.installWorker();
  return runtime;
}

export { load as loadRuntime };

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
    const ok = this.raw.generateJavaScript({
      outputName: fileName,
      mainClass: required(options.mainClass, "mainClass"),
      moduleType: normalizeModule(options.module ?? options.moduleType ?? "umd"),
    });
    const bytes = ok ? this.js.get(fileName) : null;

    return {
      ok,
      fileName,
      bytes,
      text: bytes != null ? textDecoder.decode(bytes) : null,
      files: this.js.list(),
    };
  }
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

function required(value, name) {
  if (value == null || value === "") {
    throw new TypeError(`${name} is required`);
  }
  return value;
}
