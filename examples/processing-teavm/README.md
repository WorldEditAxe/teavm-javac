# Processing TeaVM Demo

Build the compiler dist first:

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

The preprocess panel works with only `dist/teavm-javac/compiler.wasm`.

For the full p5 sketch runner, place the Processing core archive here:

```text
dist/teavm-javac/processing-core-teavm.jar
```
