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

        var target = new WasmGCTarget();
        var refCache = new ReferenceCache();
        var teavm = createTeaVM(target, refCache);
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

        var target = new JavaScriptTarget();
        var refCache = new ReferenceCache();
        var teavm = createTeaVM(target, refCache);
        teavm.setEntryPoint(mainClass);
        target.setObfuscated(false);
        target.setModuleType(getModuleType(options.getModuleType()));
        teavm.build(new MemoryBuildTarget(javaScriptOutputFiles), outputName);
        return reportTeaVMDiagnostics(teavm);
    }

    private TeaVM createTeaVM(TeaVMTarget target, ReferenceCache refCache) {
        if (classSource == null) {
            resourceProvider = new MemoryResourceProvider(List.of(teavmClasslibFiles, classFiles, outputFiles));
            classSource = new ClasspathClassHolderSource(resourceProvider, refCache);
        }
        var currentResourceProvider = new CompositeResourceProvider(new MemoryResourceProvider(List.of(outputFiles)),
                resourceProvider);
        var currentClassSource = new CompositeClassHolderSource(List.of(
                new ClasspathClassHolderSource(currentResourceProvider, refCache), classSource));
        var teavm = new TeaVMBuilder(target)
                .setClassSource(currentClassSource)
                .setResourceProvider(currentResourceProvider)
                .setReferenceCache(refCache)
                .setObfuscated(true)
                .setStrict(true)
                .build();
        teavm.setOptimizationLevel(TeaVMOptimizationLevel.ADVANCED);
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
