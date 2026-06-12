export type BinaryInput = string | URL | ArrayBuffer | ArrayBufferView | Int8Array;
export type TeaVMOptimizationLevel = "simple" | "advanced" | "full" | "SIMPLE" | "ADVANCED" | "FULL";
export type CompilerBackend = "auto" | "js" | "javascript" | "wasm" | "wasm-gc" | "wasmgc";
export type JavaRuntimeStdin = string | ArrayBuffer | ArrayBufferView | (() => number | null | undefined) | {
    readStdin(): number | null | undefined;
} | {
    read(): number | null | undefined;
};
export type JavaRuntimeOutput = ((text: string) => void) | {
    write(text: string): void;
};
export interface JavaRuntimeIOOptions {
    stdin?: JavaRuntimeStdin;
    stdout?: JavaRuntimeOutput;
    stderr?: JavaRuntimeOutput;
    installImports?: (imports: Record<string, unknown>, controller?: unknown) => void;
    [name: string]: unknown;
}
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
    diagnostics?: CompilerDiagnostic[];
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
    stdin?: JavaRuntimeStdin;
    stdout?: JavaRuntimeOutput;
    stderr?: JavaRuntimeOutput;
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
export declare function createCompiler(options?: CreateCompilerOptions): Promise<JavaCompiler>;
export declare function installWorker(options?: CompilerRuntimeLoadOptions): Promise<CompilerRuntime>;
export declare function loadCompilerRuntime(input?: string | URL | CompilerRuntime | CompilerRuntimeExports | CompilerRuntimeLoadOptions): Promise<CompilerRuntime>;
export declare const loadRuntime: typeof loadCompilerRuntime;
export declare function createJavaRuntimeOptions(options?: JavaRuntimeIOOptions): JavaRuntimeIOOptions;
export declare function supportsCompilerWasmGC(): Promise<boolean>;
export declare function getCompilerWasmGCSupport(): Promise<{
    supported: boolean;
    reason: string | null;
}>;
export declare class JavaCompiler {
    readonly raw: any;
    readonly runtime: CompilerRuntime;
    readonly startupDiagnostics: CompilerDiagnostic[];
    readonly classes: CompilerFileSet;
    readonly wasm: CompilerFileSet;
    readonly js: CompilerFileSet;
    constructor(raw: any, runtime: any);
    setClasslib({ javac, runtime }: CompilerClasslibOptions): Promise<this>;
    addSource(path: string, content: string): this;
    clearSources(): this;
    preprocessProcessing(sources: Array<string | ProcessingSource>, options?: ProcessingPreprocessOptions): ProcessingPreprocessResult;
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
