# Embedded Vendor Sources

This source root contains embedded third-party sources required by browser-side compiler features.

- `processing/` contains the Processing PDE preprocessor sources used by Processing sketch support.
- `org/antlr/` contains ANTLR compatibility shims needed by the embedded preprocessor.

The Java packages intentionally remain unchanged from their upstream origins. Keep project-owned compiler code in `../main/java`.
