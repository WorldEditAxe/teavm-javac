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
- Missing Processing images return `null` and print a Processing-style error
  instead of throwing `FileNotFoundException`.
