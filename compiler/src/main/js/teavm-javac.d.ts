export type BinaryInput = string | URL | ArrayBuffer | ArrayBufferView | Int8Array;

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
  source?: string | URL | null;
}

export interface CreateCompilerOptions {
  compilerJs?: string | URL | CompilerRuntime | CompilerRuntimeExports;
  compilerJsUrl?: string | URL;
  compilerRuntime?: CompilerRuntime | CompilerRuntimeExports;
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
export function installWorker(options?: Pick<CreateCompilerOptions, "compilerJs" | "compilerJsUrl" | "compilerRuntime">): Promise<CompilerRuntime>;
export function loadCompilerRuntime(
  input?: string | URL | CompilerRuntime | CompilerRuntimeExports
): Promise<CompilerRuntime>;
export const loadRuntime: typeof loadCompilerRuntime;
