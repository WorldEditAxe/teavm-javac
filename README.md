An offline and self-contained browser-based Java compiler (forked from the [original project](https://github.com/konsoletyper/teavm-javac)) that runs on top of OpenJDK's `javac` and TeaVM's compiler backends for JavaScript and WebAssembly. The compiler runtime is distributed as both JavaScript and Wasm-GC, with automatic Wasm-GC selection when the browser supports it.

Unlike the original project, this fork is written to be more developer-oriented and ready for inclusion in applications. It also has the option to build for JavaScript targets, includes more of the TeaVM classlib, and fixes a bug in the original TeaVM compiler that broke `@JSBody`. The API surface has been reworked as well.

**IMPORTANT** Not everything will work out of the box. Some programs will require tweaking; other simpler applications won't.

## Building

```
./gradlew :compiler:createDist
```

Resulting library archive can be found at `compiler/build/distributions/dist.zip`.

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

In a more complex scenario, you can re-use existing compiler instance without passing SDK and classlib again.
This should also make repeated compilation faster, since compilers can re-use results of previous builds.

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

You need Java 25 installed on your machine.

Run

```
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
