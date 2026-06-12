An offline and self-contained browser-based Java compiler (forked from the [original project](https://github.com/konsoletyper/teavm-javac)) that runs on top of OpenJDK's `javac` and TeaVM's compiler backends for JavaScript and WebAssembly. The compiler runtime is distributed as both JavaScript and Wasm-GC, with automatic Wasm-GC selection when the browser supports it.

Unlike the original project, this fork is written to be more developer-oriented and ready for inclusion in applications. It also has the option to build for JavaScript targets, includes more of the TeaVM classlib, and fixes a bug in the original TeaVM compiler that broke `@JSBody`. The API surface has been reworked as well.

**IMPORTANT** Not everything will work out of the box. Some programs will require tweaking; other simpler applications won't.

## Building

Build prerequisites:

- JDK 25 installed and selected by `JAVA_HOME` or `PATH`. Gradle compiles the
  project with Java 25 source/target.
- Network access on the first build. The `javac` module downloads OpenJDK 25
  sources from `github.com/openjdk/jdk25u`.
- Binaryen `wasm-opt` installed. `:compiler:createDist` runs `wasm-opt -O4` over
  the generated Wasm-GC compiler. Put `wasm-opt` on `PATH`, or set `WASM_OPT` to
  the executable path.
- Node.js and npm. The wrapper sources under `compiler/src/main/js` are
  TypeScript and Gradle runs `npm install` plus `npm run build` during the
  distribution build.

```sh
./gradlew :compiler:createDist
```

Resulting library archive can be found at `compiler/build/distributions/dist.zip`.
The checked-in `dist/teavm-javac/` folder is the unpacked browser/package
distribution used by the examples.

When changing the vendored Processing core, rebuild and copy its jar before the
compiler distribution build:

```sh
./gradlew -p vendor/processing-core-teavm clean jar
cp vendor/processing-core-teavm/build/processing-core-teavm.jar compiler/src/main/js/processing-core-teavm.jar
cp vendor/processing-core-teavm/build/processing-core-teavm.jar dist/teavm-javac/processing-core-teavm.jar
WASM_OPT=/path/to/wasm-opt ./gradlew :compiler:createDist
```

Use the last command without `WASM_OPT=...` if `wasm-opt` is already on `PATH`.

## Repository layout

See [docs/REPOSITORY_STRUCTURE.md](docs/REPOSITORY_STRUCTURE.md) for the module and source-root map.

## Using as a library

If you're too lazy to build, the `dist` folder comes with a build ready for use.

The distribution is npm/package-manager ready. It ships ESM modules plus first-class
TypeScript declarations through `package.json` exports:

```ts
import { createCompiler } from "@worldeditaxe/teavm-javac";
import { runProcessingElement } from "@worldeditaxe/teavm-javac/processing";
```

The Processing browser runner compiles and emits sketch JavaScript in a module
worker by default so code generation does not block the UI thread. Pass
`{ worker: false }` to force the old main-thread path, or pass `workerUrl` to host
`processing-teavm-worker.js` somewhere else. Sketch codegen defaults to TeaVM
`"simple"` optimization unless `optimizationLevel`/`optimization` is supplied.
Pass `fastGlobalAnalysis: true` to use TeaVM's fast dependency/global analysis
path for sketch emit. This mirrors TeaVMTool's fast dependency mode, so the
compiler backend uses `SIMPLE` optimization while that mode is enabled.

Sketches run through the p5.js-compatible backend by default. To skip p5 and
draw directly to a browser `CanvasRenderingContext2D`, pass
`{ backend: "canvas2d" }` to `runProcessingElement`/`runProcessingSketches` or
set `backend="canvas2d"` on a `<processing>` element. The direct backend covers
the core 2D path: canvas creation/resizing, drawing primitives, shapes, text,
transforms, pixels, cursor updates, and mouse/keyboard input.

The preferred API is the ES module wrapper in `teavm-javac.js`:

```js
import { createCompiler } from "./teavm-javac.js";

const compiler = await createCompiler();

compiler.onDiagnostic((diagnostic) => {
  console.log(diagnostic.type, diagnostic.severity, diagnostic.fileName, diagnostic.lineNumber, diagnostic.message);
});

compiler.addSource("Main.java", HELLO_WORLD_JAVA_CODE);

if (!compiler.compile()) {
  throw new Error("Java compilation failed");
}

const js = compiler.emitJs({
  mainClass: "Main",
  module: "commonjs",
});

console.log(js.fileName); // classes.js
console.log(js.text);
```

`createCompiler()` loads `compile-classlib-teavm.bin` and
`runtime-classlib-teavm.bin` from the same folder as `teavm-javac.js` by default.
For the compiler runtime, it first tries the Wasm-GC build (`compiler.wasm` through
`compiler.wasm-runtime.js`) when JSPI and the required Wasm-GC/string builtins are
available, then falls back to the JavaScript build (`compiler.js`). You can override
the runtime and classlib files with URLs or binary data:

```ts
type BinarySource = string | URL | ArrayBuffer | ArrayBufferView;

declare function createCompiler(options?: {
  backend?: "auto" | "js" | "wasm-gc";
  compilerJs?: string | URL;
  compilerJsUrl?: string | URL;
  compilerWasm?: BinarySource;
  compilerWasmUrl?: BinarySource;
  compilerWasmRuntime?: string | URL;
  compilerWasmRuntimeUrl?: string | URL;
  fallbackToJs?: boolean;
  javacClasslib?: BinarySource;
  javacClasslibUrl?: BinarySource;
  runtimeClasslib?: BinarySource;
  runtimeClasslibUrl?: BinarySource;
  loadClasslib?: boolean;
  runtimeOptions?: object;
}): Promise<JavaCompiler>;

declare class JavaCompiler {
  raw: unknown; // original TeaVM-exported compiler object
  runtime: unknown; // TeaVM runtime instance

  setClasslib(input: { javac?: BinarySource; runtime?: BinarySource }): Promise<this>;

  addSource(path: string, content: string): this;
  clearSources(): this;

  addDependency(path: string, bytes: BinarySource): this;
  addDependencyArchive(bytes: BinarySource): this;
  clearDependencies(): this;

  addClassFile(path: string, bytes: BinarySource): this;
  addClassArchive(bytes: BinarySource): this;
  clearClassFiles(): this;

  onDiagnostic(listener: (diagnostic: Diagnostic) => void): {
    unsubscribe(): void;
    dispose(): void;
    raw: unknown;
  };

  compile(): boolean;
  findMainClasses(): string[];

  emitWasm(options: {
    mainClass: string;
    name?: string; // defaults to "app"
    outputName?: string;
    optimizationLevel?: "simple" | "advanced" | "full";
    optimization?: "simple" | "advanced" | "full";
    fastGlobalAnalysis?: boolean;
    fastDependencyAnalysis?: boolean;
  }): {
    ok: boolean;
    fileName: string;
    bytes: Int8Array | null;
    files: string[];
  };

  emitJs(options: {
    mainClass: string;
    fileName?: string; // defaults to "classes.js"
    name?: string;
    outputName?: string;
    module?: "umd" | "commonjs" | "cjs" | "esm" | "es2015" | "script" | "none";
    moduleType?: string;
    optimizationLevel?: "simple" | "advanced" | "full";
    optimization?: "simple" | "advanced" | "full";
    fastGlobalAnalysis?: boolean;
    fastDependencyAnalysis?: boolean;
  }): {
    ok: boolean;
    fileName: string;
    bytes: Int8Array | null;
    text: string | null;
    files: string[];
  };

  classes: FileSet;
  wasm: FileSet;
  js: FileSet;
}

declare interface FileSet {
  list(): string[];
  get(path: string): Int8Array | null;
  text(path: string): string | null;
  archive(): Int8Array;
}
```

where

```ts
declare class ListenerRegistration {
  destroy();
}

declare class Diagnostic {
  type: "javac" | "teavm";
  severity: "error" | "warning" | "other";
  fileName: string;
  lineNumber: number;
  message: string;
}
declare class JavaDiagnostic extends Diagnostic {
  type: "javac";
  columnNumber: number;
  startPosition: number;
  position: number;
  endPosition: number;
}
declare class TeaVMDiagnostic extends Diagnostic {
  type: "teavm";
}
```

Please note that methods, that are supposed to add a file, overwrite existing files.

You can still access the original TeaVM-exported API through `compiler.raw`, or by loading
`compiler.wasm` manually with `compiler.wasm-runtime.js`.

```ts
import { load } from "./compiler.wasm-runtime.js";

const teavm = await load(wasmBytes);
const rawCompiler = teavm.exports.createCompiler();
```

### Runtime programs

Do not call TeaVM `exports.main(...)` or Processing `exports.start(...)`
directly. Create a program object, then call `program.execute(...)`. File opens
can suspend and resume through TeaVM async methods, so the program object is the
runtime lifecycle boundary.

#### Runtime API

```ts
type JavaProgramOutput = (text: string) => void;

interface JavaProgramStdioOptions {
  stdin?: string;
  stdout?: JavaProgramOutput;
  stderr?: JavaProgramOutput;
}

interface JavaProgramFileSystemOptions {
  onFileWrite?: (path: string, content: Uint8Array) => void;
  onFileClose?: (path: string, mode: string, content: Uint8Array | null) => void;
}

interface JavaProgramOptions {
  stdio?: false | JavaProgramStdioOptions;
  fs?: false | JavaProgramFileSystemOptions;
  wasmRuntime?: string | URL;
  wasmRuntimeUrl?: string | URL;
  runtimeModule?: {
    load(wasmBytes: BinaryInput, options?: Record<string, unknown>): Promise<unknown>;
  };
  wasmRuntimeOptions?: Record<string, unknown>;
  runtimeOptions?: Record<string, unknown>;
}

interface ProcessingProgramOptions extends JavaProgramOptions {
  sketchWasmRuntime?: string | URL;
  sketchWasmRuntimeUrl?: string | URL;
  sketchWasmRuntimeOptions?: Record<string, unknown>;
}
```

Runtime helpers:

```ts
createJavaProgramOptions(options?): JavaProgramOptions
createJavaProgram(wasmBytes, options?): Promise<JavaProgram>
createProcessingProgramOptions(options?): ProcessingProgramOptions
createProcessingProgram(wasmBytes, options?): Promise<ProcessingProgram>
createCanvas2DBackend(parent, options?): ProcessingCanvas2DBackend

class JavaProgram {
  wasmProgram: unknown;
  exports: unknown;
  instance: unknown;
  module: unknown;
  execute(options?: {
    args?: string[];
    onFinish?: (result: { program: JavaProgram }) => void;
    timeoutMs?: number;
  }): Promise<{ program: JavaProgram }>;
}

class ProcessingProgram {
  wasmProgram: unknown;
  exports: unknown;
  instance: unknown;
  module: unknown;
  execute(options: { canvasBackend: ProcessingCanvas2DBackend }): unknown;
}
```

Runtime option rules:

- `stdio.stdin` is a string.
- `stdio.stdout` and `stdio.stderr` are functions that receive text chunks.
- `stdio: false` skips stdio import installation.
- Java and Processing reads use browser `fetch(path)`.
- There is no `onFileOpen` callback.
- `fs.onFileWrite(path, content)` and
  `fs.onFileClose(path, mode, content)` are synchronous callbacks.
- `content` is a `Uint8Array`; read closes pass `null`.
- `fs: false` skips file import installation.

File path rules:

- Raw Java file APIs use the requested path exactly. `new File("data.txt")`
  opens `data.txt`.
- Processing file APIs follow Processing lookup behavior. `loadImage("x.png")`
  and `createInput("x.txt")` try the sketch `data/` location before the
  literal path.
- Missing Java file reads surface as Java file errors such as
  `FileNotFoundException`.
- Missing Processing images return `null` and print a Processing-style error.

#### Standard Java flow

```js
import { createJavaProgram } from "./teavm-javac.js";

const program = await createJavaProgram(wasmBytes, {
  stdio: {
    stdin: "",
    stdout: (text) => console.log(text),
    stderr: (text) => console.error(text),
  },
  fs: {
    onFileWrite: (path, content) => {
      console.log("write", path, content);
    },
    onFileClose: (path, mode, content) => {
      console.log("close", path, mode, content);
    },
  },
});

await program.execute({
  args: [],
  onFinish: () => {
    console.log("Java main finished");
  },
});
```

`emitJs({ module: "esm" })` appends the same program-style API to generated
JavaScript output: call `createJavaProgram(options)` and then
`program.execute(...)`. The JS program object has the same lifecycle shape as
the Wasm program object, but it does not load Wasm bytes.

#### Standard Processing flow

```js
import {
  createCanvas2DBackend,
  createProcessingProgram,
} from "./processing-teavm.js";

const program = await createProcessingProgram(wasmBytes, {
  stdio: {
    stdin: "",
    stdout: (text) => console.log(text),
    stderr: (text) => console.error(text),
  },
  fs: {
    onFileWrite: (path, content) => {
      console.log("write", path, content);
    },
    onFileClose: (path, mode, content) => {
      console.log("close", path, mode, content);
    },
  },
});

const backend = createCanvas2DBackend(document.body, {});
const sketch = program.execute({ canvasBackend: backend });
```

The Processing high-level runner still exists for pages that use
`<processing>` tags:

```js
import { runProcessingElement } from "./processing-teavm.js";

await runProcessingElement(document.querySelector("processing"), {
  backend: "canvas2d",
  output: "wasm-gc",
});
```

Processing image behavior follows Processing's `PImage` contract.
`loadPixels()` copies native image data into `pixels[]`; edits to `pixels[]`
affect drawing and saving only after `updatePixels()`. Image-level operations
such as `resize()`, `get()`, `set()`, `copy()`, `mask()`, `filter()`,
`blend()`, and `save()` synchronize with the native decoded image at the method
boundary.

### Using simple worker

When the worker initializes, it sends the following message to the page:

```js
{
  command: "initialized";
}
```

When you send a message to the worker, you should pass additional `id` property,
worker will tag its responses to given request with the same `id`.

Available requests:

```js
{
    command: "load-classlib",
    url: "URL of Java class library for javac",
    runtimeUrl: "URL of Java class library for TeaVM"
}
```

which is responded with

```js
{
  command: "ok";
}
```

upon completion, and

```js
{
    command: "compile",
    text: "text of Main.java",
    target: "webassembly" | "javascript" | "js", // optional, defaults to WebAssembly
    outputName: string, // optional; JS defaults to "classes.js", Wasm defaults to "app"
    moduleType: "COMMON_JS" | "UMD" | "NONE" | "ES2015" // optional for JS, defaults to "UMD"
    optimizationLevel: "SIMPLE" | "ADVANCED" | "FULL", // optional, defaults to SIMPLE
    fastGlobalAnalysis: boolean // optional, defaults to false
}
```

which is responded with:

```js
{
    command: "compilation-complete",
    status: "successful" | "errors",
    outputType: "webassembly" | "javascript",
    outputName: string,
    script: result /* Int8Array, containing WebAssembly module or JavaScript, if successful */
}
```

Additionally, worker sends the following messages during compilation:

```js
{
    command: "compiler-diagnostic" | "diagnostic",
    severity: "error" | "warning" | "other",
    fileName: string,
    lineNumber: number,
    // etc, see JavaDiagnostic and TeaVMDiagnostic
}
```

where `compiler-diagnostic` stands for "Java compiler diagnostic" and `diagnostic` stands for
"TeaVM diagnostic"

### Building library from sources

You need the same prerequisites listed in [Building](#building): JDK 25,
network access for the first OpenJDK source download, and Binaryen `wasm-opt`.

Run

```sh
./gradlew :compiler:createDist
```

Library can be found at `compiler/build/distributions/dist.zip`.

## License

This project is licensed under the Apache License 2.0.

NOTICE: This project uses components from the OpenJDK project, which is licensed under
the GNU General Public License v2 with the Classpath Exception.
See: https://openjdk.org/legal/gplv2+ce.html

No code from OpenJDK is modified or included in source form in this project.
During the build process, OpenJDK source code may be downloaded and compiled into bytecode
for inclusion in the final WebAssembly output, as permitted by the Classpath Exception.
