import {
  preprocessProcessing,
  runProcessingElement,
} from "../../dist/teavm-javac/processing-teavm.js";

const coreUrl = new URL("../../dist/teavm-javac/processing-core-teavm.jar", import.meta.url);

const sketchElement = document.querySelector("#sketch");
const statusElement = document.querySelector("#status");
const pdeSourceElement = document.querySelector("#pde-source");
const javaSourceElement = document.querySelector("#java-source");
const preprocessButton = document.querySelector("#preprocess");
const runButton = document.querySelector("#run");

let cachedSources = [];

class CoreJarMissingError extends Error {
  constructor(message) {
    super(message);
    this.name = "CoreJarMissingError";
  }
}

preprocessButton.addEventListener("click", () => {
  runPreprocess().catch((error) => showError(error));
});

runButton.addEventListener("click", () => {
  runSketch().catch((error) => showError(error));
});

await initialize();

async function initialize() {
  setBusy(true);
  try {
    await loadPdeSource();
    await runPreprocess();
    await runSketch();
  } catch (error) {
    showError(error);
  } finally {
    setBusy(false);
  }
}

async function loadPdeSource() {
  const sourcePaths = parseSourceList(sketchElement.getAttribute("src"));
  cachedSources = await Promise.all(sourcePaths.map(async (path) => {
    const url = new URL(path, import.meta.url);
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`Could not load ${url}: ${response.status} ${response.statusText}`);
    }
    return {
      path,
      url: String(url),
      content: await response.text(),
    };
  }));

  pdeSourceElement.textContent = cachedSources
    .map((source) => `// ${source.path}\n${source.content}`)
    .join("\n\n");
}

async function runPreprocess() {
  setBusy(true);
  setStatus("Preprocessing PDE source.", "warn");
  try {
    const result = await preprocessProcessing(cachedSources, {
      sketchName: sketchElement.getAttribute("sketch-name") ?? "AsteroidsGame",
      compilerOptions: {
        loadClasslib: false,
      },
    });

    javaSourceElement.textContent = result.javaSource;
    if (result.ok) {
      setStatus(`Preprocessed ${result.sourceFileName} with ${result.edits.length} source edits.`, "ok");
    } else {
      setStatus(result.issues[0]?.message ?? "Preprocessing failed.", "error");
    }
    return result;
  } finally {
    setBusy(false);
  }
}

async function runSketch() {
  setBusy(true);
  setStatus("Checking Processing core jar.", "warn");
  try {
    if (typeof globalThis.p5 !== "function") {
      setStatus("p5.js is not loaded. Check the CDN script in index.html.", "error");
      return null;
    }

    const coreArchive = await loadCoreArchive();
    setStatus("Compiling and running sketch.", "warn");
    const result = await runProcessingElement(sketchElement, {
      p5: globalThis.p5,
      coreArchive,
      sourceMaps: true,
    });
    setStatus("Sketch is running.", "ok");
    return result;
  } catch (error) {
    if (error instanceof CoreJarMissingError) {
      setStatus(error.message, "warn");
      return null;
    }
    throw error;
  } finally {
    setBusy(false);
  }
}

async function loadCoreArchive() {
  const response = await fetch(coreUrl, { cache: "no-store" });
  if (!response.ok) {
    throw new CoreJarMissingError(
      `Preprocess works. For full run, place processing-core-teavm.jar at ${coreUrl.pathname}.`
    );
  }
  return new Int8Array(await response.arrayBuffer());
}

function setBusy(busy) {
  preprocessButton.disabled = busy;
  runButton.disabled = busy;
}

function setStatus(message, state = "") {
  statusElement.textContent = message;
  statusElement.dataset.state = state;
}

function showError(error) {
  console.error(error);
  const diagnostics = formatDiagnostics(error);
  if (diagnostics) {
    javaSourceElement.textContent = diagnostics;
  }
  setStatus(diagnostics.split("\n", 1)[0] || error?.message || String(error), "error");
}

function parseSourceList(src) {
  return String(src ?? "").split(/[,\s]+/).map((part) => part.trim()).filter(Boolean);
}

function formatDiagnostics(error) {
  const diagnostics = error?.diagnostics;
  if (!Array.isArray(diagnostics) || diagnostics.length === 0) {
    return "";
  }
  return diagnostics.map((diagnostic) => {
    const file = diagnostic.fileName ?? diagnostic.generatedFileName ?? "<unknown>";
    const line = diagnostic.lineNumber ?? diagnostic.generatedLineNumber ?? "?";
    const column = diagnostic.columnNumber ?? diagnostic.generatedColumnNumber ?? "?";
    return `${file}:${line}:${column}: ${diagnostic.message ?? diagnostic.type ?? "Compilation error"}`;
  }).join("\n");
}
