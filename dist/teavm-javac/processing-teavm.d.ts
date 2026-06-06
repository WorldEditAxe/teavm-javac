import type {
  BinaryInput,
  CreateCompilerOptions,
  JavaCompiler,
  ProcessingPreprocessResult,
  ProcessingSource,
} from "./teavm-javac.js";

export interface ProcessingRunOptions {
  root?: ParentNode;
  selector?: string;
  target?: "js" | "javascript" | string;
  baseUrl?: string | URL;
  p5?: P5Constructor;
  core?: BinaryInput;
  coreUrl?: BinaryInput;
  coreArchive?: BinaryInput;
  compiler?: JavaCompiler;
  compilerOptions?: CreateCompilerOptions;
  sourceMaps?: boolean;
  sketchName?: string;
  launcherClass?: string;
}

export interface ProcessingCompileOptions {
  p5?: P5Constructor;
  core?: BinaryInput;
  coreUrl?: BinaryInput;
  coreArchive?: BinaryInput;
  compiler?: JavaCompiler;
  compilerOptions?: CreateCompilerOptions;
  sourceMaps?: boolean;
  sketchName?: string;
  launcherClass?: string;
  diagnosticsElement?: Element;
}

export interface ProcessingDiagnostic {
  type?: string;
  severity?: string;
  fileName?: string | null;
  sourceUrl?: string;
  lineNumber?: number;
  columnNumber?: number;
  generatedFileName?: string | null;
  generatedLineNumber?: number;
  generatedColumnNumber?: number;
  message?: string;
}

export interface ProcessingCompileResult {
  compiler: JavaCompiler;
  compiled: boolean;
  diagnostics: ProcessingDiagnostic[];
  preprocessed: ProcessingPreprocessResult;
  mapper: unknown;
  launcherClass: string;
  launcherSource: string;
}

export interface ProcessingRunResult {
  element: Element;
  output: "js";
  p5: unknown;
  sketch: unknown;
  diagnostics: ProcessingDiagnostic[];
  preprocessed: ProcessingPreprocessResult;
  files: string[];
  module?: unknown;
  sourceMap?: string | null;
  compilerSourceMap?: string | null;
}

export interface P5Constructor {
  new (sketch: (p: unknown) => void, node?: Element): unknown;
}

export class ProcessingLoadError extends Error {
  readonly element: Element | null;
  constructor(element: Element | null, message: string);
}

export class ProcessingPreprocessError extends Error {
  readonly preprocessed: ProcessingPreprocessResult;
  readonly issues: ProcessingPreprocessResult["issues"];
  constructor(preprocessed: ProcessingPreprocessResult);
}

export class ProcessingCompileError extends Error {
  readonly element: Element | null;
  readonly compiled?: ProcessingCompileResult;
  readonly diagnostics: ProcessingDiagnostic[];
  readonly preprocessed?: ProcessingPreprocessResult;
  constructor(element: Element | null, message: string, compiled?: ProcessingCompileResult);
}

export function runProcessingSketches(options?: ProcessingRunOptions): Promise<ProcessingRunResult[]>;
export const runProcessingTags: typeof runProcessingSketches;
export function runProcessingElement(
  element: Element,
  options?: ProcessingRunOptions,
  index?: number
): Promise<ProcessingRunResult>;
export function compileProcessingSketch(
  sources: Array<string | ProcessingSource>,
  options?: ProcessingCompileOptions
): Promise<ProcessingCompileResult>;
export function preprocessProcessing(
  sources: Array<string | ProcessingSource>,
  options?: ProcessingCompileOptions
): Promise<ProcessingPreprocessResult>;
