# Processing TeaVM Demo

Build the compiler dist first:

```sh
WASM_OPT=/path/to/wasm-opt ./gradlew :compiler:createDist
```

`WASM_OPT` can be omitted if `wasm-opt` is already on `PATH`. The build also
requires JDK 25 and network access on the first run so Gradle can download
OpenJDK 25 sources.

Refresh the checked-in demo distribution when needed:

```sh
./gradlew :compiler:createDist
unzip -o compiler/build/distributions/dist.zip -d dist/teavm-javac
```

Serve the repository root over HTTP:

```sh
python3 -m http.server 8765
```

Open:

```text
http://127.0.0.1:8765/examples/processing-teavm/
```

The full Processing runner uses the packaged Processing core archive:

```text
dist/teavm-javac/processing-core-teavm.jar
```

Current file behavior:

- Java file APIs use the exact requested path.
- Processing APIs use Processing lookup rules, so `loadImage("image.png")` tries
  the sketch `data/` location before the literal path.
- File reads are built into the runtime bridge and use `fetch(path)`. There is
  no file-open callback.
- File write and close notifications are optional synchronous callbacks on the
  program options: `fs.onFileWrite(path, content)` and
  `fs.onFileClose(path, mode, content)`.
- `stdio.stdin` is a string. `stdio.stdout` and `stdio.stderr` are functions.
- Missing Processing images return `null` and print a Processing-style error
  instead of throwing `FileNotFoundException`.

Manual Wasm mounting uses program objects:

```js
import { createCanvas2DBackend, createProcessingProgram } from "../../dist/teavm-javac/processing-teavm.js";

const program = await createProcessingProgram(wasmBytes, {
  fs: {
    onFileWrite(path, content) {},
    onFileClose(path, mode, content) {},
  },
});
const backend = createCanvas2DBackend(document.body, {});
const sketch = program.execute({ canvasBackend: backend });
```
