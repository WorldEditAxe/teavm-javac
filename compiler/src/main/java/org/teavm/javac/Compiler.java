/*
 *  Copyright 2017 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.teavm.javac;

import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.util.Context;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import org.objectweb.asm.ClassReader;
import org.teavm.backend.javascript.JSModuleType;
import org.teavm.backend.javascript.JavaScriptTarget;
import org.teavm.backend.javascript.templating.JavaScriptTemplateFactory;
import org.teavm.backend.wasm.WasmDebugInfoLocation;
import org.teavm.backend.wasm.WasmGCTarget;
import org.teavm.classlib.impl.JCLPlugin;
import org.teavm.classlib.java.lang.ClassLoaderNativeGenerator;
import org.teavm.classlib.java.lang.DoubleGenerator;
import org.teavm.classlib.java.lang.IntegerNativeGenerator;
import org.teavm.classlib.java.lang.LongNativeGenerator;
import org.teavm.classlib.java.lang.MathNativeGenerator;
import org.teavm.classlib.java.lang.SystemDependencyPlugin;
import org.teavm.classlib.java.lang.SystemNativeGenerator;
import org.teavm.debugging.information.DebugInformationBuilder;
import org.teavm.debugging.information.SourceMapsWriter;
import org.teavm.dependency.FastDependencyAnalyzer;
import org.teavm.jso.JSClass;
import org.teavm.jso.JSExport;
import org.teavm.jso.core.JSObjects;
import org.teavm.jso.core.JSString;
import org.teavm.jso.impl.JSOPlugin;
import org.teavm.jso.impl.JSNativeGenerator;
import org.teavm.jso.impl.JSNativeInjector;
import org.teavm.jso.typedarrays.Int8Array;
import org.teavm.model.ClassReaderSource;
import org.teavm.model.ClassHolderSource;
import org.teavm.model.MethodReference;
import org.teavm.model.ReferenceCache;
import org.teavm.parsing.ClasspathClassHolderSource;
import org.teavm.parsing.CompositeClassHolderSource;
import org.teavm.parsing.resource.CompositeResourceProvider;
import org.teavm.parsing.resource.ResourceProvider;
import org.teavm.platform.plugin.PlatformPlugin;
import org.teavm.vm.TeaVM;
import org.teavm.vm.TeaVMBuilder;
import org.teavm.vm.TeaVMOptimizationLevel;
import org.teavm.vm.TeaVMTarget;
import processing.mode.java.preproc.PdePreprocessIssue;
import processing.mode.java.preproc.PdePreprocessor;
import processing.mode.java.preproc.PreprocessorResult;
import processing.mode.java.preproc.TextTransform;
import processing.utils.SketchException;
import static com.sun.tools.javac.comp.CompileStates.CompileState;

@JSClass(name = "Compiler")
public final class Compiler {
    private static final String JSO_JS_CLASS = "org.teavm.jso.impl.JS";
    private static final Set<String> INTEGER_NATIVE_METHODS = Set.of("divideUnsigned", "remainderUnsigned",
            "compareUnsigned");
    private static final Set<String> DOUBLE_NATIVE_METHODS = Set.of("doubleEqualsJs", "doubleToRawLongBits",
            "longBitsToDouble");
    private static final Set<String> MATH_NATIVE_METHODS = Set.of("sin", "cos", "tan", "asin", "acos", "atan",
            "exp", "log", "sqrt", "ceil", "floor", "powImpl", "atan2", "randomImpl", "minImpl", "maxImpl",
            "absImpl", "sign");
    private static final Set<String> SYSTEM_NATIVE_METHODS = Set.of("doArrayCopy", "currentTimeMillis");
    private final Map<String, FileData> sourceFiles = new LinkedHashMap<>();
    private final Map<String, FileData> processingSourceFiles = new LinkedHashMap<>();
    private final Map<String, FileData> classFiles = new LinkedHashMap<>();
    private final Map<String, FileData> sdkFiles = new LinkedHashMap<>();
    private final Map<String, FileData> teavmClasslibFiles = new LinkedHashMap<>();
    private final Map<String, FileData> outputFiles = new LinkedHashMap<>();
    private final Map<String, FileData> wasmOutputFiles = new LinkedHashMap<>();
    private final Map<String, FileData> javaScriptOutputFiles = new LinkedHashMap<>();
    private SimpleJavaCompiler compiler;
    private List<DiagnosticListenerRegistration> diagnosticListeners = new ArrayList<>();
    private ResourceProvider resourceProvider;
    private ClassHolderSource classSource;

    Compiler() {
    }

    @JSExport
    public void addSourceFile(String name, String content) {
        addFile(sourceFiles, name, content.getBytes(StandardCharsets.UTF_8));
    }

    @JSExport
    public void clearSourceFiles() {
        sourceFiles.clear();
    }

    @JSExport
    public void addProcessingSourceFile(String name, String content) {
        addFile(processingSourceFiles, name, content.getBytes(StandardCharsets.UTF_8));
    }

    @JSExport
    public void clearProcessingSourceFiles() {
        processingSourceFiles.clear();
    }

    @JSExport
    public ProcessingPreprocessResult preprocessProcessing(String sketchName) throws SketchException {
        if (processingSourceFiles.isEmpty()) {
            throw new IllegalArgumentException("No Processing sources added");
        }

        var className = toJavaIdentifier(sketchName, "ProcessingSketch");
        var combined = combineProcessingSources();
        var writer = new StringWriter();
        var preprocessor = PdePreprocessor.builderFor(className)
                .setTabSize(4)
                .build();
        PreprocessorResult result = preprocessor.write(writer, combined.source);
        var javaSource = writer.toString();
        var sourceFileName = className + ".java";

        return new ProcessingPreprocessResult(
                result.getPreprocessIssues().isEmpty(),
                sourceFileName,
                result.getClassName(),
                javaSource,
                result.getProgramType().name(),
                result.getHeaderOffset(),
                result.getSketchWidth(),
                result.getSketchHeight(),
                result.getSketchRenderer(),
                tabsToJson(combined.tabs),
                editsToJson(result.getEdits()),
                issuesToJson(result.getPreprocessIssues()));
    }

    @JSExport
    public void addClassFile(String name, Int8Array content) {
        addFile(classFiles, name, content);
    }

    @JSExport
    public void addOutputClassFile(String name, Int8Array content) {
        addFile(outputFiles, name, content);
    }

    @JSExport
    public void addOutputJarFile(String name, Int8Array content) throws IOException {
        addJarFile(outputFiles, content);
    }

    @JSExport
    public void addJarFile(Int8Array content) throws IOException {
        addJarFile(classFiles, content);
    }

    private void addJarFile(Map<String, FileData> target, Int8Array content) throws IOException {
        try (var input = new ZipInputStream(new ByteArrayInputStream(content.copyToJavaArray()))) {
            while (true) {
                var entry = input.getNextEntry();
                if (entry == null) {
                    break;
                }
                addFile(target, entry.getName(), input.readAllBytes());
            }
        }
    }

    @JSExport
    public void setSdk(Int8Array content) throws IOException {
        try (var input = new ArchiveReader(new ByteArrayInputStream(content.copyToJavaArray()))) {
            while (true) {
                var entry = input.readNext();
                if (entry == null) {
                    break;
                }
                var file = addFile(sdkFiles, entry, input.readData());
                file.isJavaBase = true;
            }
        }
    }

    @JSExport
    public void setTeaVMClasslib(Int8Array content) throws IOException {
        try (var input = new ArchiveReader(new ByteArrayInputStream(content.copyToJavaArray()))) {
            while (true) {
                var entry = input.readNext();
                if (entry == null) {
                    break;
                }
                addFile(teavmClasslibFiles, entry, input.readData());
            }
        }
    }

    @JSExport
    public void clearInputClassFiles() {
        classFiles.clear();
    }

    @JSExport
    public void clearOutputFiles() {
        outputFiles.clear();
    }

    @JSExport
    public Int8Array getOutputFile(String name) {
        var data = outputFiles.get(name);
        if (data == null) {
            return null;
        }
        return Int8Array.copyFromJavaArray(data.data);
    }

    @JSExport
    public String[] listOutputFiles() {
        return outputFiles.keySet().toArray(String[]::new);
    }

    @JSExport
    public Int8Array getOutputJar() throws IOException {
        return archiveFiles(outputFiles);
    }

    @JSExport
    public Int8Array getWebAssemblyOutputFile(String name) {
        var data = wasmOutputFiles.get(name);
        if (data == null) {
            return null;
        }
        return Int8Array.copyFromJavaArray(data.data);
    }

    @JSExport
    public String[] listWebAssemblyOutputFiles() {
        return wasmOutputFiles.keySet().toArray(String[]::new);
    }

    @JSExport
    public Int8Array getWebAssemblyOutputArchive() throws IOException {
        return archiveFiles(wasmOutputFiles);
    }

    @JSExport
    public Int8Array getJavaScriptOutputFile(String name) {
        var data = javaScriptOutputFiles.get(name);
        if (data == null) {
            return null;
        }
        return Int8Array.copyFromJavaArray(data.data);
    }

    @JSExport
    public String[] listJavaScriptOutputFiles() {
        return javaScriptOutputFiles.keySet().toArray(String[]::new);
    }

    @JSExport
    public Int8Array getJavaScriptOutputArchive() throws IOException {
        return archiveFiles(javaScriptOutputFiles);
    }

    private Int8Array archiveFiles(Map<String, FileData> files) throws IOException {
        var output = new ByteArrayOutputStream();
        try (var zip = new ZipOutputStream(output)) {
            for (var file : files.values()) {
                zip.putNextEntry(new ZipEntry(file.path));
                zip.write(file.data);
                zip.closeEntry();
            }
        }
        return Int8Array.copyFromJavaArray(output.toByteArray());
    }

    private FileData addFile(Map<String, FileData> files, String name, Int8Array content) {
        return addFile(files, name, content.copyToJavaArray());
    }

    private FileData addFile(Map<String, FileData> files, String name, byte[] content) {
        var data = new FileData(files);
        data.lastModified = System.currentTimeMillis();
        data.path = name;
        data.data = content;
        files.put(name, data);
        return data;
    }

    private ProcessingCombinedSource combineProcessingSources() {
        var tabs = new ArrayList<ProcessingSourceTab>();
        var source = new StringBuilder();
        var lineStart = 0;
        for (var file : processingSourceFiles.values()) {
            var content = new String(file.data, StandardCharsets.UTF_8);
            var charStart = source.length();
            tabs.add(new ProcessingSourceTab(file.path, charStart, lineStart, countLines(content)));
            source.append(content);
            source.append('\n');
            lineStart += countLines(content);
        }
        return new ProcessingCombinedSource(source.toString(), tabs);
    }

    private static int countLines(String text) {
        var count = 1;
        for (var i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                count++;
            }
        }
        return count;
    }

    private static String toJavaIdentifier(String value, String defaultValue) {
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        var result = new StringBuilder();
        for (var i = 0; i < value.length(); i++) {
            var ch = value.charAt(i);
            if (i == 0) {
                result.append(Character.isJavaIdentifierStart(ch) ? ch : '_');
            } else {
                result.append(Character.isJavaIdentifierPart(ch) ? ch : '_');
            }
        }
        if (result.length() == 0 || !Character.isJavaIdentifierStart(result.charAt(0))) {
            result.insert(0, '_');
        }
        return result.toString();
    }

    private static String tabsToJson(List<ProcessingSourceTab> tabs) {
        var result = new StringBuilder("[");
        for (var i = 0; i < tabs.size(); i++) {
            if (i > 0) {
                result.append(',');
            }
            var tab = tabs.get(i);
            result.append('{')
                    .append("\"path\":").append(jsonString(tab.path)).append(',')
                    .append("\"charStart\":").append(tab.charStart).append(',')
                    .append("\"lineStart\":").append(tab.lineStart).append(',')
                    .append("\"lineCount\":").append(tab.lineCount)
                    .append('}');
        }
        return result.append(']').toString();
    }

    private static String editsToJson(List<TextTransform.Edit> edits) {
        var result = new StringBuilder("[");
        for (var i = 0; i < edits.size(); i++) {
            if (i > 0) {
                result.append(',');
            }
            var edit = edits.get(i);
            result.append('{')
                    .append("\"fromOffset\":").append(edit.getFromOffset()).append(',')
                    .append("\"fromLength\":").append(edit.getFromLength()).append(',')
                    .append("\"toOffset\":").append(edit.getToOffset()).append(',')
                    .append("\"toLength\":").append(edit.getToLength()).append(',')
                    .append("\"text\":").append(jsonString(edit.getOutputText()))
                    .append('}');
        }
        return result.append(']').toString();
    }

    private static String issuesToJson(List<PdePreprocessIssue> issues) {
        var result = new StringBuilder("[");
        for (var i = 0; i < issues.size(); i++) {
            if (i > 0) {
                result.append(',');
            }
            var issue = issues.get(i);
            result.append('{')
                    .append("\"line\":").append(issue.getLine()).append(',')
                    .append("\"column\":").append(issue.getCharPositionInLine()).append(',')
                    .append("\"message\":").append(jsonString(issue.getMsg()))
                    .append('}');
        }
        return result.append(']').toString();
    }

    private static String jsonString(String value) {
        if (value == null) {
            return "null";
        }
        var result = new StringBuilder("\"");
        for (var i = 0; i < value.length(); i++) {
            var ch = value.charAt(i);
            switch (ch) {
                case '"' -> result.append("\\\"");
                case '\\' -> result.append("\\\\");
                case '\b' -> result.append("\\b");
                case '\f' -> result.append("\\f");
                case '\n' -> result.append("\\n");
                case '\r' -> result.append("\\r");
                case '\t' -> result.append("\\t");
                default -> {
                    if (ch < 0x20) {
                        result.append("\\u");
                        var hex = Integer.toHexString(ch);
                        for (var j = hex.length(); j < 4; j++) {
                            result.append('0');
                        }
                        result.append(hex);
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.append('"').toString();
    }

    @JSExport
    public boolean compile() {
        initCompiler();
        try {
            return compiler.simpleCompile();
        } finally {
            compiler = null;
        }
    }

    @JSExport
    public String[] detectMainClasses() throws IOException {
        var mainClasses = new ArrayList<String>();
        for (var file : outputFiles.values()) {
            if (file.getName().endsWith(".class")) {
                var finder = new MainMethodFinder();
                try (var input = file.openInputStream()) {
                    var reader = new ClassReader(input);
                    reader.accept(finder, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG);
                }
                if (finder.className != null && finder.hasMainMethod) {
                    mainClasses.add(finder.className);
                }
            }
        }
        return mainClasses.toArray(new String[0]);
    }

    @JSExport
    public boolean generateWebAssembly(WebAssemblyCompilationOptions options) {
        var outputName = getOptionalString(options.getOutputName(), "app");
        var mainClass = getRequiredString(options.getMainClass(), "Main class");
        var optimizationLevel = getOptimizationLevel(options.getOptimizationLevel());
        var fastGlobalAnalysis = options.isFastGlobalAnalysis();

        var target = new WasmGCTarget();
        var refCache = new ReferenceCache();
        var teavm = createTeaVM(target, refCache, optimizationLevel, fastGlobalAnalysis);
        teavm.setEntryPoint(mainClass);
        target.setObfuscated(false);
        target.setDebugInfoLocation(WasmDebugInfoLocation.EMBEDDED);
        target.setDebugInfo(true);
        teavm.build(new MemoryBuildTarget(wasmOutputFiles), outputName);
        return reportTeaVMDiagnostics(teavm);
    }

    @JSExport
    public boolean generateJavaScript(JavaScriptCompilationOptions options) {
        var outputName = getOptionalString(options.getOutputName(), "classes.js");
        var mainClass = getRequiredString(options.getMainClass(), "Main class");
        var sourceMap = options.isSourceMap();
        var sourceMapName = getOptionalString(options.getSourceMapName(), outputName + ".map");
        var optimizationLevel = getOptimizationLevel(options.getOptimizationLevel());
        var fastGlobalAnalysis = options.isFastGlobalAnalysis();

        var target = new JavaScriptTarget();
        var refCache = new ReferenceCache();
        DebugInformationBuilder debugInformationBuilder = null;
        if (sourceMap) {
            debugInformationBuilder = new DebugInformationBuilder(refCache);
            target.setDebugEmitter(debugInformationBuilder);
        }
        var teavm = createTeaVM(target, refCache, optimizationLevel, fastGlobalAnalysis);
        teavm.setEntryPoint(mainClass);
        target.setObfuscated(false);
        target.setModuleType(getModuleType(options.getModuleType()));
        var buildTarget = new MemoryBuildTarget(javaScriptOutputFiles);
        teavm.build(buildTarget, outputName);
        if (sourceMap && debugInformationBuilder != null) {
            try (var writer = new OutputStreamWriter(buildTarget.createResource(sourceMapName),
                    StandardCharsets.UTF_8)) {
                var sourceMapsWriter = new SourceMapsWriter(writer);
                sourceMapsWriter.write(outputName, "", debugInformationBuilder.getDebugInformation());
            } catch (IOException e) {
                throw new RuntimeException("Could not write JavaScript source map", e);
            }
            appendSourceMapComment(outputName, sourceMapName);
        }
        return reportTeaVMDiagnostics(teavm);
    }

    private void appendSourceMapComment(String outputName, String sourceMapName) {
        var file = javaScriptOutputFiles.get(outputName);
        if (file == null) {
            return;
        }
        var text = new String(file.data, StandardCharsets.UTF_8);
        if (!text.contains("sourceMappingURL=")) {
            text += "\n//# sourceMappingURL=" + sourceMapName + "\n";
            file.data = text.getBytes(StandardCharsets.UTF_8);
            file.lastModified = System.currentTimeMillis();
        }
    }

    private TeaVM createTeaVM(TeaVMTarget target, ReferenceCache refCache,
            TeaVMOptimizationLevel optimizationLevel, boolean fastGlobalAnalysis) {
        if (classSource == null) {
            resourceProvider = new MemoryResourceProvider(List.of(teavmClasslibFiles, classFiles, outputFiles));
            classSource = new ClasspathClassHolderSource(resourceProvider, refCache);
        }
        var currentResourceProvider = new CompositeResourceProvider(new MemoryResourceProvider(List.of(outputFiles)),
                resourceProvider);
        var currentClassSource = new CompositeClassHolderSource(List.of(
                new ClasspathClassHolderSource(currentResourceProvider, refCache), classSource));
        var builder = new TeaVMBuilder(target)
                .setClassSource(currentClassSource)
                .setResourceProvider(currentResourceProvider)
                .setReferenceCache(refCache)
                .setObfuscated(true)
                .setStrict(true);
        if (fastGlobalAnalysis) {
            builder.setDependencyAnalyzerFactory(FastDependencyAnalyzer::new);
        }
        var teavm = builder.build();
        teavm.setOptimizationLevel(fastGlobalAnalysis ? TeaVMOptimizationLevel.SIMPLE : optimizationLevel);
        new PlatformPlugin().install(teavm);
        new JSOPlugin().install(teavm);
        if (target instanceof JavaScriptTarget javaScriptTarget) {
            installJavaScriptNativeHandlers(teavm, javaScriptTarget, currentClassSource);
            installPlatformNativeHandlers(teavm, javaScriptTarget, currentClassSource);
            installClasslibJavaScriptNativeHandlers(teavm, javaScriptTarget, currentClassSource);
        }
        new JCLPlugin().install(teavm);
        return teavm;
    }

    private static void installJavaScriptNativeHandlers(TeaVM teavm, JavaScriptTarget target,
            ClassReaderSource classSource) {
        var nativeInjector = new JSNativeInjector();
        target.addInjectorProvider(context -> isJsoInjectedMethod(context.getMethod()) ? nativeInjector : null);
        target.addGeneratorProvider(context -> {
            var method = context.getMethod();
            if (!isJsoNativeMethod(method) || !isJsoGeneratedMethod(method)) {
                return null;
            }
            var templateFactory = new JavaScriptTemplateFactory(Compiler.class.getClassLoader(),
                    context.getClassSource());
            return new JSNativeGenerator(templateFactory);
        });

        var jsClass = classSource.get(JSO_JS_CLASS);
        if (jsClass != null) {
            for (var method : jsClass.getMethods()) {
                teavm.add(method.getReference(), nativeInjector);
            }
        }
    }

    private static boolean isJsoNativeMethod(MethodReference method) {
        return method.getClassName().equals(JSO_JS_CLASS);
    }

    private static boolean isJsoGeneratedMethod(MethodReference method) {
        return method.getName().equals("function") || method.getName().equals("functionAsObject");
    }

    private static boolean isJsoInjectedMethod(MethodReference method) {
        return isJsoNativeMethod(method) && !isJsoGeneratedMethod(method) && !method.getName().equals("jsArrayItem");
    }

    private static void installPlatformNativeHandlers(TeaVM teavm, JavaScriptTarget target,
            ClassReaderSource classSource) {
        var platformGenerator = new CompilerPlatformGenerator();
        target.addInjectorProvider(context -> isPlatformInjectedMethod(context.getMethod()) ? platformGenerator : null);
        target.addGeneratorProvider(context -> isPlatformGeneratedMethod(context.getMethod()) ? platformGenerator
                : null);

        var platformClass = classSource.get(CompilerPlatformGenerator.PLATFORM_CLASS);
        if (platformClass != null) {
            for (var method : platformClass.getMethods()) {
                switch (method.getName()) {
                    case "clone":
                    case "startThread":
                    case "schedule":
                        teavm.add(method.getReference(), platformGenerator);
                        break;
                }
            }
        }
    }

    private static boolean isPlatformMethod(MethodReference method) {
        return method.getClassName().equals(CompilerPlatformGenerator.PLATFORM_CLASS);
    }

    private static boolean isPlatformInjectedMethod(MethodReference method) {
        return isPlatformMethod(method) && method.getName().equals("getPlatformObject");
    }

    private static boolean isPlatformGeneratedMethod(MethodReference method) {
        if (!isPlatformMethod(method)) {
            return false;
        }
        var name = method.getName();
        return name.equals("clone") || name.equals("startThread") || name.equals("schedule");
    }

    private static void installClasslibJavaScriptNativeHandlers(TeaVM teavm, JavaScriptTarget target,
            ClassReaderSource classSource) {
        var integerGenerator = new IntegerNativeGenerator();
        var doubleGenerator = new DoubleGenerator();
        var classLoaderGenerator = new ClassLoaderNativeGenerator();
        target.addInjectorProvider(context -> {
            var method = context.getMethod();
            if (isNamedClasslibMethod(method, "java.lang.Integer", INTEGER_NATIVE_METHODS)) {
                return integerGenerator;
            }
            if (isNamedClasslibMethod(method, "java.lang.Double", DOUBLE_NATIVE_METHODS)) {
                return doubleGenerator;
            }
            if (isNamedClasslibMethod(method, "java.lang.ClassLoader", Set.of("supplyResources"))) {
                return classLoaderGenerator;
            }
            return null;
        });

        var longGenerator = new LongNativeGenerator();
        var mathGenerator = new MathNativeGenerator();
        target.addGeneratorProvider(context -> {
            var method = context.getMethod();
            if (isNamedClasslibMethod(method, "java.lang.Long", INTEGER_NATIVE_METHODS)) {
                return longGenerator;
            }
            if (isNamedClasslibMethod(method, "java.lang.Math", MATH_NATIVE_METHODS)) {
                return mathGenerator;
            }
            if (isNamedClasslibMethod(method, "java.lang.System", SYSTEM_NATIVE_METHODS)) {
                var templateFactory = new JavaScriptTemplateFactory(Compiler.class.getClassLoader(),
                        context.getClassSource());
                return new SystemNativeGenerator(templateFactory);
            }
            return null;
        });

        var systemClass = classSource.get("java.lang.System");
        if (systemClass != null) {
            var dependencyPlugin = new SystemDependencyPlugin();
            for (var method : systemClass.getMethods()) {
                if (method.getName().equals("doArrayCopy")) {
                    teavm.add(method.getReference(), dependencyPlugin);
                }
            }
        }
    }

    private static boolean isNamedClasslibMethod(MethodReference method, String className, Set<String> methodNames) {
        return method.getClassName().equals(className) && methodNames.contains(method.getName());
    }

    private boolean reportTeaVMDiagnostics(TeaVM teavm) {
        if (!diagnosticListeners.isEmpty()) {
            for (var problem : teavm.getProblemProvider().getProblems()) {
                var wrapper = new TeaVMDiagnostic(problem);
                for (var reg : diagnosticListeners) {
                    reg.listener.onDiagnostic(wrapper);
                }
            }
        }
        return teavm.getProblemProvider().getSevereProblems().isEmpty();
    }

    private static String getRequiredString(JSString value, String name) {
        if (JSObjects.isUndefined(value) || value == null) {
            throw new IllegalArgumentException(name + " not specified");
        }
        return value.stringValue();
    }

    private static String getOptionalString(JSString value, String defaultValue) {
        return value != null && !JSObjects.isUndefined(value) ? value.stringValue() : defaultValue;
    }

    private static JSModuleType getModuleType(JSString value) {
        var moduleType = getOptionalString(value, JSModuleType.UMD.name());
        var normalized = moduleType.replace('-', '_').toUpperCase(Locale.ROOT);
        if (normalized.equals("COMMONJS")) {
            normalized = JSModuleType.COMMON_JS.name();
        }
        try {
            return JSModuleType.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported JavaScript module type: " + moduleType);
        }
    }

    private static TeaVMOptimizationLevel getOptimizationLevel(JSString value) {
        var optimizationLevel = getOptionalString(value, TeaVMOptimizationLevel.SIMPLE.name());
        var normalized = optimizationLevel.replace('-', '_').toUpperCase(Locale.ROOT);
        try {
            return TeaVMOptimizationLevel.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported TeaVM optimization level: " + optimizationLevel
                    + ". Supported levels are SIMPLE, ADVANCED, and FULL.");
        }
    }

    @JSExport
    public ListenerRegistration onDiagnostic(CompilerDiagnosticListener diagnosticListener) {
        var reg = new DiagnosticListenerRegistration(diagnosticListeners, diagnosticListener);
        diagnosticListeners.add(reg);
        return reg;
    }

    private void initCompiler() {
        if (compiler != null) {
            return;
        }
        var context = new Context();
        context.put(DiagnosticListener.class, new DiagnosticListenerImpl(diagnosticListeners));
        var fileManager = new FileManagerImpl(sourceFiles, classFiles, sdkFiles, outputFiles);
        context.put(JavaFileManager.class, fileManager);
        compiler = new SimpleJavaCompiler(context);
        compiler.prepare();
    }

    private class SimpleJavaCompiler extends JavaCompiler {
        SimpleJavaCompiler(Context context) {
            super(context);
        }

        void prepare() {
            syms.unnamedModule.visiblePackages = new LinkedHashMap<>();
            syms.unnamedModule.readModules = Set.of(syms.unnamedModule, syms.java_base);
            syms.java_base.complete();
            for (var export : syms.java_base.exports) {
                syms.unnamedModule.visiblePackages.put(export.packge.fullname, export.packge);
            }
        }

        boolean simpleCompile() {
            var files = sourceFiles.values().stream().map(x -> (JavaFileObject) x).toList();
            var units = stopIfError(CompileState.ENTER, parseFiles(files));
            enterTrees(stopIfError(CompileState.ENTER, initModules(units)));
            generate(desugar(flow(attribute(todo))));
            return log.nerrors == 0;
        }
    }

    private static class ProcessingCombinedSource {
        final String source;
        final List<ProcessingSourceTab> tabs;

        ProcessingCombinedSource(String source, List<ProcessingSourceTab> tabs) {
            this.source = source;
            this.tabs = tabs;
        }
    }

    private static class ProcessingSourceTab {
        final String path;
        final int charStart;
        final int lineStart;
        final int lineCount;

        ProcessingSourceTab(String path, int charStart, int lineStart, int lineCount) {
            this.path = path;
            this.charStart = charStart;
            this.lineStart = lineStart;
            this.lineCount = lineCount;
        }
    }

    static class DiagnosticListenerRegistration extends ListenerRegistration {
        private final List<DiagnosticListenerRegistration> listeners;
        CompilerDiagnosticListener listener;

        DiagnosticListenerRegistration(List<DiagnosticListenerRegistration> listeners,
                CompilerDiagnosticListener listener) {
            this.listeners = listeners;
            this.listener = listener;
        }

        @Override
        public void destroy() {
            listeners.remove(this);
        }
    }
}
