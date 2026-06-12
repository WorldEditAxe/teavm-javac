export type BinaryInput = string | URL | ArrayBuffer | ArrayBufferView | Int8Array;
export type TeaVMOptimizationLevel = "simple" | "advanced" | "full" | "SIMPLE" | "ADVANCED" | "FULL";
export type CompilerBackend = "auto" | "js" | "javascript" | "wasm" | "wasm-gc" | "wasmgc";

export interface CompilerClasslibOptions {
  javac?: BinaryInput;
  runtime?: BinaryInput;
}

export interface CompilerRuntimeExports {
  createCompiler(): unknown;
  installWorker(): void;
  [name: string]: unknown;
}

export interface CompilerRuntime {
  exports: CompilerRuntimeExports;
  backend?: "js" | "wasm-gc" | string;
  source?: string | URL | null;
  instance?: unknown;
  module?: unknown;
}

export interface CompilerRuntimeLoadOptions {
  backend?: CompilerBackend;
  compilerBackend?: CompilerBackend;
  runtimeBackend?: CompilerBackend;
  compilerJs?: string | URL | CompilerRuntime | CompilerRuntimeExports;
  compilerJsUrl?: string | URL;
  compilerRuntime?: CompilerRuntime | CompilerRuntimeExports;
  compilerWasm?: BinaryInput;
  compilerWasmUrl?: BinaryInput;
  compilerWasmRuntime?: string | URL;
  compilerWasmRuntimeUrl?: string | URL;
  wasmRuntime?: string | URL;
  wasmRuntimeUrl?: string | URL;
  wasmRuntimeOptions?: Record<string, unknown>;
  runtimeOptions?: Record<string, unknown>;
  fallbackToJs?: boolean;
}

export interface CreateCompilerOptions extends CompilerRuntimeLoadOptions {
  javacClasslib?: BinaryInput;
  javacClasslibUrl?: BinaryInput;
  runtimeClasslib?: BinaryInput;
  runtimeClasslibUrl?: BinaryInput;
  loadClasslib?: boolean;
}

export interface CompilerFileSet {
  list(): string[];
  get(path: string): Int8Array | null;
  archive(): Int8Array;
  text(path: string): string | null;
}

export interface CompilerDiagnostic {
  type?: string;
  severity?: string;
  fileName?: string | null;
  lineNumber?: number;
  columnNumber?: number;
  message?: string;
}

export interface DiagnosticRegistration {
  unsubscribe(): void;
  dispose(): void;
  raw: unknown;
}

export interface ProcessingSource {
  path?: string;
  name?: string;
  content: string;
}

export interface ProcessingSourceTab {
  path: string;
  charStart: number;
  lineStart: number;
  lineCount: number;
}

export interface ProcessingEdit {
  fromOffset: number;
  fromLength: number;
  toOffset: number;
  toLength: number;
  text: string | null;
}

export interface ProcessingIssue {
  line: number;
  column: number;
  message: string;
}

export interface ProcessingPreprocessOptions {
  sketchName?: string;
  className?: string;
  name?: string;
}

export interface ProcessingPreprocessResult {
  ok: boolean;
  sourceFileName: string;
  className: string;
  javaSource: string;
  programType: "STATIC" | "ACTIVE" | "JAVA" | string;
  lineOffset: number;
  sketchWidth: string | null;
  sketchHeight: string | null;
  sketchRenderer: string | null;
  tabs: ProcessingSourceTab[];
  edits: ProcessingEdit[];
  issues: ProcessingIssue[];
  raw: unknown;
}

export interface EmitWasmOptions {
  name?: string;
  outputName?: string;
  mainClass: string;
  optimizationLevel?: TeaVMOptimizationLevel;
  optimization?: TeaVMOptimizationLevel;
  fastGlobalAnalysis?: boolean;
  fastDependencyAnalysis?: boolean;
}

export interface EmitWasmResult {
  ok: boolean;
  fileName: string;
  bytes: Int8Array | null;
  files: string[];
}

export interface EmitJsOptions {
  name?: string;
  outputName?: string;
  fileName?: string;
  mainClass: string;
  module?: "umd" | "esm" | "commonjs" | "script" | string;
  moduleType?: string;
  sourceMap?: boolean;
  sourceMaps?: boolean;
  sourceMapName?: string;
  optimizationLevel?: TeaVMOptimizationLevel;
  optimization?: TeaVMOptimizationLevel;
  fastGlobalAnalysis?: boolean;
  fastDependencyAnalysis?: boolean;
  runtimeModule?: boolean;
}

export interface EmitJsResult {
  ok: boolean;
  fileName: string;
  bytes: Int8Array | null;
  text: string | null;
  sourceMapName: string;
  sourceMapBytes: Int8Array | null;
  sourceMapText: string | null;
  files: string[];
}

export class JavaCompiler {
  readonly raw: unknown;
  readonly runtime: CompilerRuntime;
  readonly classes: CompilerFileSet;
  readonly wasm: CompilerFileSet;
  readonly js: CompilerFileSet;

  constructor(raw: unknown, runtime: CompilerRuntime);
  setClasslib(options: CompilerClasslibOptions): Promise<this>;
  addSource(path: string, content: string): this;
  clearSources(): this;
  preprocessProcessing(
    sources: Array<string | ProcessingSource>,
    options?: ProcessingPreprocessOptions
  ): ProcessingPreprocessResult;
  addDependency(path: string, bytes: BinaryInput): this;
  addDependencyArchive(bytes: BinaryInput): this;
  clearDependencies(): this;
  addClassFile(path: string, bytes: BinaryInput): this;
  addClassArchive(bytes: BinaryInput): this;
  clearClassFiles(): this;
  onDiagnostic(listener: (diagnostic: CompilerDiagnostic) => void): DiagnosticRegistration;
  compile(): boolean;
  findMainClasses(): string[];
  emitWasm(options: EmitWasmOptions): EmitWasmResult;
  emitJs(options: EmitJsOptions): EmitJsResult;
}

export function createCompiler(options?: CreateCompilerOptions): Promise<JavaCompiler>;
export function setActiveRuntime<T>(runtime: T): T;
export function loadJavaWasm(
  wasmBytes: BinaryInput,
  options?: {
    runtimeModule?: { load(wasmBytes: BinaryInput, options?: Record<string, unknown>): Promise<unknown> };
    wasmRuntime?: string | URL;
    wasmRuntimeUrl?: string | URL;
    wasmRuntimeOptions?: Record<string, unknown>;
    runtimeOptions?: Record<string, unknown>;
  }
): Promise<unknown>;
export function runJavaMain(
  runtime: unknown,
  args?: string[],
  options?: { onFinish?: (result: { runtime: unknown }) => void; timeoutMs?: number }
): Promise<{ runtime: unknown }>;
export function runJavaWasm(
  wasmBytes: BinaryInput,
  options?: {
    args?: string[];
    onFinish?: (result: { runtime: unknown }) => void;
    timeoutMs?: number;
    runtimeModule?: { load(wasmBytes: BinaryInput, options?: Record<string, unknown>): Promise<unknown> };
    wasmRuntime?: string | URL;
    wasmRuntimeUrl?: string | URL;
    wasmRuntimeOptions?: Record<string, unknown>;
    runtimeOptions?: Record<string, unknown>;
  }
): Promise<{ runtime: unknown }>;
export function installWorker(options?: CompilerRuntimeLoadOptions): Promise<CompilerRuntime>;
export function loadCompilerRuntime(
  input?: string | URL | CompilerRuntime | CompilerRuntimeExports | CompilerRuntimeLoadOptions
): Promise<CompilerRuntime>;
export const loadRuntime: typeof loadCompilerRuntime;
export function supportsCompilerWasmGC(): Promise<boolean>;
