# Repository Structure

This repository is organized around the browser compiler artifact and the inputs needed to build and try it.

## Root Projects

- `compiler/` - TeaVM-facing compiler library, JavaScript wrappers, classlib packaging, and distribution task.
- `javac/` - OpenJDK `javac` extraction and compilation support.
- `protocol/` - message types shared by the worker/API surface.

## Compiler Source Roots

- `compiler/src/main/java/` - project-owned compiler implementation under `org.teavm.javac`.
- `compiler/src/main/js/` - ES module and TypeScript declaration wrappers shipped with the distribution.
- `compiler/src/main/resources/` - service registrations and runtime resources.
- `compiler/src/classlibEmu/java/` - class library emulation classes packed into the compile-time classlib.
- `compiler/src/runtimeClasslibEmu/java/` - runtime-only TeaVM classlib emulation, including Java file API bridge classes.
- `compiler/src/sharedClasslibEmu/` - emulation sources shared by compile-time and runtime classlib packaging.
- `compiler/src/teavm/java/` - TeaVM-specific replacement classes used by TeaVM tasks.
- `compiler/src/patches/java/` - local TeaVM/Rhino/classlib/backend patch sources kept separate from project code.
- `compiler/src/vendor/java/` - embedded third-party Processing preprocessor and ANTLR compatibility sources.

## Supporting Trees

- `config/checkstyle/` - Checkstyle configuration shared by Java modules.
- `dist/` - checked-in ready-to-use distribution files and local unpacked builds.
- `examples/processing-teavm/` - browser demo for Processing preprocessing and sketch execution.
- `vendor/processing-core-teavm/` - local Processing core fork used to build the optional Processing runtime jar.
- `gradle/` - Gradle wrapper and dependency catalog.
