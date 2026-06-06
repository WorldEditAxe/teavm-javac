import { createCompiler } from "./teavm-javac.js";

const DEFAULT_CORE_URL = new URL("./processing-core-teavm.jar", import.meta.url);
const DEFAULT_JS_FILE = "processing-sketch.js";

let coreArchivePromise = null;

export async function runProcessingSketches(options = {}) {
  const root = options.root ?? document;
  const selector = options.selector ?? "processing";
  const elements = Array.from(root.querySelectorAll(selector));
  const results = [];

  for (let i = 0; i < elements.length; i++) {
    results.push(await runProcessingElement(elements[i], options, i));
  }

  return results;
}

export const runProcessingTags = runProcessingSketches;

export async function runProcessingElement(element, options = {}, index = 0) {
  const src = element.getAttribute("src");
  if (!src) {
    throw new ProcessingLoadError(element, "Processing element is missing src");
  }

  normalizeOutput(
    element.getAttribute("target") ??
    element.getAttribute("output") ??
    element.getAttribute("type") ??
    options.target ??
    "js"
  );
  const sources = await loadPdeSources(src, element, options);
  const sketchName = toJavaIdentifier(
    options.sketchName ??
    element.getAttribute("sketch-name") ??
    firstSourceBaseName(sources[0]?.path ?? `Sketch${index + 1}`)
  );

  setState(element, "compiling");
  const compiled = await compileProcessingSketch(sources, {
    ...options,
    sketchName,
    diagnosticsElement: element,
  });

  if (!compiled.compiled) {
    setState(element, "error");
    throw new ProcessingCompileError(element, "Java compilation failed", compiled);
  }

  setState(element, "emitting");
  const result = await emitAndRunJs(compiled, element, options);

  setState(element, "running");
  return result;
}

export async function compileProcessingSketch(sources, options = {}) {
  const normalizedSources = normalizeSources(sources);
  const compiler = options.compiler ?? await createCompiler(options.compilerOptions ?? {});
  const diagnostics = [];
  const preprocessed = compiler.preprocessProcessing(normalizedSources, {
    sketchName: options.sketchName ?? firstSourceBaseName(normalizedSources[0]?.path),
  });
  const mapper = createProcessingMapper(preprocessed, normalizedSources);

  if (!preprocessed.ok) {
    throw new ProcessingPreprocessError(preprocessed);
  }

  compiler.onDiagnostic((diagnostic) => {
    const mapped = mapDiagnostic(toPlainDiagnostic(diagnostic), mapper);
    diagnostics.push(mapped);
    options.diagnosticsElement?.dispatchEvent?.(new CustomEvent("processingdiagnostic", {
      detail: mapped,
    }));
  });

  const launcherClass = toJavaIdentifier(options.launcherClass ?? `${preprocessed.className}Launcher`);
  const launcherSource = createLauncherSource(launcherClass, preprocessed.className);
  const coreArchive = await getCoreArchive(options);

  compiler.addDependencyArchive(coreArchive);
  compiler.addClassArchive(coreArchive);
  compiler.addSource(preprocessed.sourceFileName, preprocessed.javaSource);
  compiler.addSource(`${launcherClass}.java`, launcherSource);

  return {
    compiler,
    compiled: compiler.compile(),
    diagnostics,
    preprocessed,
    mapper,
    launcherClass,
    launcherSource,
  };
}

export async function preprocessProcessing(sources, options = {}) {
  const compiler = options.compiler ?? await createCompiler(options.compilerOptions ?? {});
  return compiler.preprocessProcessing(normalizeSources(sources), options);
}

async function emitAndRunJs(compiled, element, options) {
  const sourceMapName = `${DEFAULT_JS_FILE}.map`;
  const emitted = compiled.compiler.emitJs({
    mainClass: compiled.launcherClass,
    module: "esm",
    fileName: DEFAULT_JS_FILE,
    sourceMap: options.sourceMaps !== false,
    sourceMapName,
  });

  if (!emitted.ok || !emitted.text) {
    throw new ProcessingCompileError(element, "TeaVM JavaScript emit failed", compiled);
  }

  const sourceMap = composeTeaVmSourceMap(emitted.sourceMapText, compiled.mapper, compiled.preprocessed);
  const moduleText = inlineSourceMap(emitted.text, sourceMap);
  const moduleUrl = URL.createObjectURL(new Blob([moduleText], { type: "text/javascript" }));
  try {
    const module = await import(moduleUrl);
    const mounted = await mountSketch(element, module.start, options.p5);
    return {
      element,
      output: "js",
      module,
      p5: mounted.p5,
      sketch: mounted.sketch,
      diagnostics: compiled.diagnostics,
      preprocessed: compiled.preprocessed,
      sourceMap,
      compilerSourceMap: emitted.sourceMapText,
      files: emitted.files,
    };
  } finally {
    URL.revokeObjectURL(moduleUrl);
  }
}

async function mountSketch(element, start, suppliedP5) {
  const P5 = suppliedP5 ?? globalThis.p5;
  if (typeof P5 !== "function") {
    throw new ProcessingLoadError(element, "p5.js is not loaded; pass { p5 } or load p5 before running sketches");
  }

  element.replaceChildren();
  element.style.display ||= "block";

  return await new Promise((resolve, reject) => {
    let p5Instance = null;
    p5Instance = new P5((p) => {
      p.setup = () => {
        try {
          const sketch = start(p);
          element.dispatchEvent(new CustomEvent("processingready", {
            detail: { p5: p5Instance, sketch },
          }));
          resolve({ p5: p5Instance, sketch });
        } catch (error) {
          reject(error);
        }
      };
    }, element);
  });
}

async function loadPdeSources(src, element, options) {
  const paths = parseSourceList(src);
  const baseUrl = options.baseUrl ?? element.ownerDocument.baseURI;
  return await Promise.all(paths.map(async (path) => {
    const url = new URL(path, baseUrl);
    const response = await fetch(url);
    if (!response.ok) {
      throw new ProcessingLoadError(element, `Failed to fetch ${url}: ${response.status} ${response.statusText}`);
    }
    return {
      path,
      url: String(url),
      content: await response.text(),
    };
  }));
}

function parseSourceList(src) {
  return src.split(/[,\s]+/).map((part) => part.trim()).filter(Boolean);
}

function normalizeSources(sources) {
  if (!Array.isArray(sources) || sources.length === 0) {
    throw new TypeError("Expected at least one Processing source");
  }
  return sources.map((source, index) => {
    if (typeof source === "string") {
      return {
        path: index === 0 ? "sketch.pde" : `sketch${index + 1}.pde`,
        content: source,
      };
    }
    if (source == null || typeof source.content !== "string") {
      throw new TypeError("Processing sources must be strings or { path, content } objects");
    }
    return {
      path: source.path ?? source.name ?? (index === 0 ? "sketch.pde" : `sketch${index + 1}.pde`),
      url: source.url,
      content: source.content,
    };
  });
}

async function getCoreArchive(options = {}) {
  const source = options.coreArchive ?? options.core ?? options.coreUrl ?? DEFAULT_CORE_URL;
  if (source instanceof ArrayBuffer || ArrayBuffer.isView(source)) {
    return toBytes(source);
  }
  if (source === DEFAULT_CORE_URL && coreArchivePromise) {
    return coreArchivePromise;
  }

  const promise = readBinary(source);
  if (source === DEFAULT_CORE_URL) {
    coreArchivePromise = promise;
  }
  return await promise;
}

function createLauncherSource(launcherClass, sketchClass) {
  return `import org.teavm.jso.JSExport;
import org.teavm.jso.JSObject;
import processing.core.PApplet;
import processing.platform.teavm.P5Platform;

public class ${launcherClass} {
  public static void main(String[] args) {
  }

  @JSExport
  public static Object start(JSObject p5) {
    P5Platform.installP5(p5);
    ${sketchClass} sketch = new ${sketchClass}();
    PApplet.runSketch(sketch);
    return sketch;
  }
}
`;
}

function createProcessingMapper(preprocessed, sources) {
  const combined = combineSources(sources);
  const lineStarts = computeLineStarts(combined.content);
  const tabs = preprocessed.tabs.map((tab, index) => ({
    ...tab,
    source: sources[index] ?? sources.find((source) => source.path === tab.path),
    nextCharStart: preprocessed.tabs[index + 1]?.charStart ?? combined.content.length,
  }));
  const offsetMapper = buildOffsetMapper(combined.content.length, preprocessed.edits);

  return {
    sources,
    combined,
    preprocessed,
    mapGenerated(line, column = 0) {
      const generatedOffset = lineColumnToOffset(preprocessed.javaSource, line, column);
      if (generatedOffset < 0) {
        return null;
      }
      const inputOffset = offsetMapper.getInputOffset(generatedOffset);
      if (inputOffset < 0) {
        return null;
      }
      const tab = findTabAtOffset(tabs, inputOffset);
      if (!tab?.source) {
        return null;
      }
      const inputLine = offsetToLine(lineStarts, inputOffset);
      const inputColumn = inputOffset - lineStarts[inputLine];
      return {
        source: tab.source.path,
        url: tab.source.url,
        content: tab.source.content,
        line: inputLine - tab.lineStart,
        column: inputColumn,
      };
    },
  };
}

function combineSources(sources) {
  return {
    content: sources.map((source) => source.content).join("\n") + "\n",
  };
}

function buildOffsetMapper(inputLength, edits) {
  const inEdits = edits.map(copyEdit).sort((a, b) => a.fromOffset - b.fromOffset);
  const outEdits = edits.map(copyEdit).sort((a, b) => a.toOffset - b.toOffset);
  const inMap = [];
  const outMap = [];

  let inIndex = 0;
  let outIndex = 0;
  let inEdit = inEdits[inIndex] ?? null;
  let outEdit = outEdits[outIndex] ?? null;
  let inEditOffset = inEdit?.fromOffset ?? inputLength;
  let outEditOffset = outEdit?.toOffset ?? inputLength;
  let inOffset = 0;
  let outOffset = 0;

  while (inOffset < inputLength || inEdit != null || outEdit != null) {
    const nextEditOffset = Math.min(inEditOffset, outEditOffset);
    const length = nextEditOffset - inOffset;
    if (length > 0) {
      const move = {
        fromOffset: inOffset,
        fromLength: length,
        toOffset: outOffset,
        toLength: length,
        text: null,
      };
      inMap.push(move);
      outMap.push(move);
    }
    outOffset += nextEditOffset - inOffset;
    inOffset = nextEditOffset;

    while (inEdit != null && inOffset >= inEditOffset) {
      inOffset += inEdit.fromLength;
      if (inEdit.fromLength > 0) {
        inMap.push(inEdit);
      }
      inIndex++;
      inEdit = inEdits[inIndex] ?? null;
      inEditOffset = inEdit?.fromOffset ?? inputLength;
    }

    while (outEdit != null && inOffset >= outEditOffset) {
      outEdit.toOffset = outOffset;
      if (outEdit.toLength > 0) {
        outMap.push(outEdit);
      }
      outOffset += outEdit.toLength;
      outIndex++;
      outEdit = outEdits[outIndex] ?? null;
      outEditOffset = outEdit?.toOffset ?? inputLength;
    }
  }

  const inputStart = inMap.find((edit) => edit.fromLength > 0);
  const outputOffsetOfInputStart = inputStart?.toOffset ?? 0;

  return {
    getInputOffset(outputOffset) {
      if (outputOffset < outputOffsetOfInputStart || outMap.length === 0) {
        return -1;
      }
      const index = findEditByOutputOffset(outMap, outputOffset);
      const edit = outMap[index];
      const diff = outputOffset - edit.toOffset;
      return edit.fromOffset + Math.min(diff, Math.max(0, edit.fromLength - 1));
    },
  };
}

function copyEdit(edit) {
  return {
    fromOffset: edit.fromOffset,
    fromLength: edit.fromLength,
    toOffset: edit.toOffset,
    toLength: edit.toLength,
    text: edit.text ?? null,
  };
}

function findEditByOutputOffset(outMap, outputOffset) {
  let low = 0;
  let high = outMap.length - 1;
  while (low <= high) {
    const mid = (low + high) >> 1;
    if (outMap[mid].toOffset <= outputOffset) {
      low = mid + 1;
    } else {
      high = mid - 1;
    }
  }
  return Math.max(0, Math.min(high, outMap.length - 1));
}

function findTabAtOffset(tabs, offset) {
  let current = null;
  for (const tab of tabs) {
    if (tab.charStart <= offset && offset < tab.nextCharStart) {
      return tab;
    }
    if (tab.charStart <= offset) {
      current = tab;
    }
  }
  return current;
}

function mapDiagnostic(diagnostic, mapper) {
  if (diagnostic.lineNumber == null || diagnostic.lineNumber < 1) {
    return diagnostic;
  }
  const mapped = mapper.mapGenerated(
    diagnostic.lineNumber - 1,
    Math.max(0, (diagnostic.columnNumber ?? 1) - 1)
  );
  if (!mapped) {
    return diagnostic;
  }
  return {
    ...diagnostic,
    generatedFileName: diagnostic.fileName,
    generatedLineNumber: diagnostic.lineNumber,
    generatedColumnNumber: diagnostic.columnNumber,
    fileName: mapped.source,
    sourceUrl: mapped.url,
    lineNumber: mapped.line + 1,
    columnNumber: mapped.column + 1,
  };
}

function composeTeaVmSourceMap(compilerSourceMapText, mapper, preprocessed) {
  if (!compilerSourceMapText) {
    return null;
  }

  let sourceMap;
  try {
    sourceMap = JSON.parse(compilerSourceMapText);
  } catch {
    return compilerSourceMapText;
  }

  const decoded = decodeMappings(sourceMap.mappings ?? "");
  const sources = [];
  const sourcesContent = [];
  const sourceIndexByName = new Map();

  const addSource = (name, content = null) => {
    if (sourceIndexByName.has(name)) {
      return sourceIndexByName.get(name);
    }
    const index = sources.length;
    sourceIndexByName.set(name, index);
    sources.push(name);
    sourcesContent.push(content);
    return index;
  };

  const outputLines = decoded.map((segments) => segments.map((segment) => {
    if (segment.source == null) {
      return { ...segment };
    }

    const compilerSource = sourceMap.sources?.[segment.source] ?? "";
    if (!sameSourceName(compilerSource, preprocessed.sourceFileName)) {
      return {
        ...segment,
        source: addSource(compilerSource, sourceMap.sourcesContent?.[segment.source] ?? null),
      };
    }

    const mapped = mapper.mapGenerated(segment.originalLine, segment.originalColumn ?? 0);
    if (!mapped) {
      return {
        ...segment,
        source: addSource(compilerSource, preprocessed.javaSource),
      };
    }

    return {
      ...segment,
      source: addSource(mapped.source, mapped.content),
      originalLine: mapped.line,
      originalColumn: mapped.column,
    };
  }));

  return JSON.stringify({
    ...sourceMap,
    sources,
    sourcesContent,
    mappings: encodeMappings(outputLines),
  });
}

function sameSourceName(left, right) {
  return String(left).split(/[\\/]/).pop() === String(right).split(/[\\/]/).pop();
}

function inlineSourceMap(text, sourceMapText) {
  if (!sourceMapText) {
    return text;
  }
  const encoded = base64EncodeUtf8(sourceMapText);
  const withoutExternalMap = text.replace(/\n?\/\/# sourceMappingURL=.*$/m, "");
  return `${withoutExternalMap}\n//# sourceMappingURL=data:application/json;base64,${encoded}\n`;
}

function decodeMappings(mappings) {
  const lines = [];
  let index = 0;
  let generatedLine = 0;
  let previousGeneratedColumn = 0;
  let previousSource = 0;
  let previousOriginalLine = 0;
  let previousOriginalColumn = 0;
  let previousName = 0;

  while (index <= mappings.length) {
    if (index === mappings.length || mappings[index] === ";") {
      lines.push([]);
      generatedLine++;
      previousGeneratedColumn = 0;
      index++;
      continue;
    }
    if (mappings[index] === ",") {
      index++;
      continue;
    }

    let value;
    [value, index] = decodeVlq(mappings, index);
    previousGeneratedColumn += value;
    const segment = { generatedLine, generatedColumn: previousGeneratedColumn };

    if (index < mappings.length && mappings[index] !== "," && mappings[index] !== ";") {
      [value, index] = decodeVlq(mappings, index);
      previousSource += value;
      segment.source = previousSource;

      [value, index] = decodeVlq(mappings, index);
      previousOriginalLine += value;
      segment.originalLine = previousOriginalLine;

      [value, index] = decodeVlq(mappings, index);
      previousOriginalColumn += value;
      segment.originalColumn = previousOriginalColumn;

      if (index < mappings.length && mappings[index] !== "," && mappings[index] !== ";") {
        [value, index] = decodeVlq(mappings, index);
        previousName += value;
        segment.name = previousName;
      }
    }

    lines[generatedLine] ??= [];
    lines[generatedLine].push(segment);
  }

  return lines;
}

function encodeMappings(lines) {
  let mappings = "";
  let previousSource = 0;
  let previousOriginalLine = 0;
  let previousOriginalColumn = 0;
  let previousName = 0;

  for (let line = 0; line < lines.length; line++) {
    if (line > 0) {
      mappings += ";";
    }
    let previousGeneratedColumn = 0;
    const segments = lines[line] ?? [];
    for (let i = 0; i < segments.length; i++) {
      const segment = segments[i];
      if (i > 0) {
        mappings += ",";
      }
      mappings += encodeVlq(segment.generatedColumn - previousGeneratedColumn);
      previousGeneratedColumn = segment.generatedColumn;

      if (segment.source != null) {
        mappings += encodeVlq(segment.source - previousSource);
        previousSource = segment.source;
        mappings += encodeVlq(segment.originalLine - previousOriginalLine);
        previousOriginalLine = segment.originalLine;
        mappings += encodeVlq((segment.originalColumn ?? 0) - previousOriginalColumn);
        previousOriginalColumn = segment.originalColumn ?? 0;
        if (segment.name != null) {
          mappings += encodeVlq(segment.name - previousName);
          previousName = segment.name;
        }
      }
    }
  }

  return mappings;
}

const BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
const BASE64_VALUES = new Map(Array.from(BASE64_CHARS, (ch, index) => [ch, index]));

function decodeVlq(text, index) {
  let result = 0;
  let shift = 0;
  let continuation;
  do {
    const digit = BASE64_VALUES.get(text[index++]);
    continuation = digit & 32;
    result += (digit & 31) << shift;
    shift += 5;
  } while (continuation);
  const negative = result & 1;
  result >>= 1;
  return [negative ? -result : result, index];
}

function encodeVlq(value) {
  let vlq = value < 0 ? ((-value) << 1) + 1 : value << 1;
  let result = "";
  do {
    let digit = vlq & 31;
    vlq >>>= 5;
    if (vlq > 0) {
      digit |= 32;
    }
    result += BASE64_CHARS[digit];
  } while (vlq > 0);
  return result;
}

function lineColumnToOffset(text, line, column) {
  const starts = computeLineStarts(text);
  if (line < 0 || line >= starts.length) {
    return -1;
  }
  return Math.min(starts[line] + column, text.length);
}

function computeLineStarts(text) {
  const starts = [0];
  for (let i = 0; i < text.length; i++) {
    if (text[i] === "\n") {
      starts.push(i + 1);
    }
  }
  return starts;
}

function offsetToLine(lineStarts, offset) {
  let low = 0;
  let high = lineStarts.length - 1;
  while (low <= high) {
    const mid = (low + high) >> 1;
    if (lineStarts[mid] <= offset) {
      low = mid + 1;
    } else {
      high = mid - 1;
    }
  }
  return Math.max(0, high);
}

async function readBinary(input) {
  if (input == null) {
    throw new TypeError("Expected a URL, ArrayBuffer, or typed array");
  }
  if (typeof input === "string" || input instanceof URL) {
    const response = await fetch(input);
    if (!response.ok) {
      throw new Error(`Failed to fetch ${input}: ${response.status} ${response.statusText}`);
    }
    return new Int8Array(await response.arrayBuffer());
  }
  return toBytes(input);
}

function toBytes(input) {
  if (input instanceof Int8Array) {
    return input;
  }
  if (input instanceof ArrayBuffer) {
    return new Int8Array(input);
  }
  if (ArrayBuffer.isView(input)) {
    return new Int8Array(input.buffer, input.byteOffset, input.byteLength);
  }
  throw new TypeError("Expected an ArrayBuffer or typed array");
}

function toPlainDiagnostic(diagnostic) {
  return {
    type: diagnostic.type,
    severity: diagnostic.severity,
    fileName: diagnostic.fileName,
    lineNumber: diagnostic.lineNumber,
    columnNumber: diagnostic.columnNumber,
    message: diagnostic.message,
  };
}

function normalizeOutput(output) {
  const normalized = String(output).toLowerCase();
  if (normalized === "wasm" || normalized === "webassembly") {
    throw new Error("Processing TeaVM browser runner is packaged for JavaScript output only");
  }
  return "js";
}

function firstSourceBaseName(path) {
  const name = String(path ?? "ProcessingSketch").split(/[\\/]/).pop().replace(/\.[^.]*$/, "");
  return name || "ProcessingSketch";
}

function toJavaIdentifier(value) {
  let identifier = String(value ?? "ProcessingSketch").replace(/^[^A-Za-z_$]/, "_").replace(/[^A-Za-z0-9_$]/g, "_");
  if (!identifier) {
    identifier = "ProcessingSketch";
  }
  return identifier;
}

function setState(element, state) {
  element.dataset.processingState = state;
}

function base64EncodeUtf8(text) {
  const bytes = new TextEncoder().encode(text);
  let binary = "";
  for (const byte of bytes) {
    binary += String.fromCharCode(byte);
  }
  return btoa(binary);
}

export class ProcessingLoadError extends Error {
  constructor(element, message) {
    super(message);
    this.name = "ProcessingLoadError";
    this.element = element;
  }
}

export class ProcessingPreprocessError extends Error {
  constructor(preprocessed) {
    super(preprocessed.issues[0]?.message ?? "Processing preprocessing failed");
    this.name = "ProcessingPreprocessError";
    this.preprocessed = preprocessed;
    this.issues = preprocessed.issues;
  }
}

export class ProcessingCompileError extends Error {
  constructor(element, message, compiled) {
    super(message);
    this.name = "ProcessingCompileError";
    this.element = element;
    this.compiled = compiled;
    this.diagnostics = compiled?.diagnostics ?? [];
    this.preprocessed = compiled?.preprocessed;
  }
}
