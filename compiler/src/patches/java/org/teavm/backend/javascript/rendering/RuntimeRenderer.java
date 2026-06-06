/*
 *  Copyright 2026 Alexey Andreev.
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
package org.teavm.backend.javascript.rendering;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.teavm.backend.javascript.codegen.SourceWriter;
import org.teavm.backend.javascript.codegen.SourceWriterSink;
import org.teavm.backend.javascript.templating.AstRemoval;
import org.teavm.backend.javascript.templating.RemovablePartsFinder;
import org.teavm.backend.javascript.templating.TemplatingAstTransformer;
import org.teavm.backend.javascript.templating.TemplatingAstWriter;
import org.teavm.model.ClassReaderSource;
import org.teavm.model.analysis.ClassInitializerInfo;
import org.teavm.rhino.javascript.CompilerEnvirons;
import org.teavm.rhino.javascript.Context;
import org.teavm.rhino.javascript.EvaluatorException;
import org.teavm.rhino.javascript.ast.AstRoot;
import org.teavm.vm.RenderingException;

public class RuntimeRenderer {
    private final List<AstRoot> runtimeAstParts = new ArrayList<>();
    private final List<AstRoot> epilogueAstParts = new ArrayList<>();
    private final RemovablePartsFinder removablePartsFinder = new RemovablePartsFinder();
    private final ClassReaderSource classSource;
    private final SourceWriter writer;
    private final ClassInitializerInfo classInitializerInfo;
    private final Set<String> topLevelNames = new HashSet<>();

    public RuntimeRenderer(ClassReaderSource classSource, SourceWriter writer,
            ClassInitializerInfo classInitializerInfo) {
        this.classSource = classSource;
        this.writer = writer;
        this.classInitializerInfo = classInitializerInfo;
    }

    public void prepareAstParts(boolean threadLibraryUsed) {
        runtimeAstParts.add(prepareAstPart("runtime.js"));
        runtimeAstParts.add(prepareAstPart("primitive.js"));
        runtimeAstParts.add(prepareAstPart("numeric.js"));
        runtimeAstParts.add(prepareAstPart("long.js"));
        runtimeAstParts.add(prepareAstPart("array.js"));
        runtimeAstParts.add(prepareAstPart("string.js"));
        runtimeAstParts.add(prepareAstPart("reflection.js"));
        runtimeAstParts.add(prepareAstPart("exception.js"));
        runtimeAstParts.add(prepareAstPart("check.js"));
        runtimeAstParts.add(prepareAstPart("console.js"));
        runtimeAstParts.add(prepareAstPart("metadata.js"));
        runtimeAstParts.add(prepareAstPart(threadLibraryUsed ? "thread.js" : "simpleThread.js"));
        epilogueAstParts.add(prepareAstPart("types.js"));
    }

    public void renderRuntime() {
        for (var ast : runtimeAstParts) {
            renderRuntimePart(ast);
        }
    }

    public void renderEpilogue() {
        for (var ast : epilogueAstParts) {
            renderRuntimePart(ast);
        }
    }

    private AstRoot prepareAstPart(String name) {
        var ast = parseRuntime(name);
        ast.visit(new StringConstantElimination());
        new TemplatingAstTransformer(classSource).visit(ast);
        removablePartsFinder.visit(ast);
        topLevelNames.addAll(ast.getSymbolTable().keySet());
        return ast;
    }

    private void renderRuntimePart(AstRoot ast)  {
        var astWriter = new TemplatingAstWriter(writer, classInitializerInfo, true);
        for (var name : topLevelNames) {
            astWriter.declareNameEmitter(name, (w, prec) -> w.appendFunction(name));
        }
        astWriter.print(ast);
    }

    private AstRoot parseRuntime(String name) {
        var env = new CompilerEnvirons();
        env.setRecoverFromErrors(true);
        env.setLanguageVersion(Context.VERSION_1_8);
        var factory = new JSParser(env);
        var path = "org/teavm/backend/javascript/" + name;

        ClassLoader loader = RuntimeRenderer.class.getClassLoader();
        try (InputStream input = loader.getResourceAsStream(path)) {
            if (input == null) {
                throw new RenderingException("TeaVM JavaScript runtime resource not found: " + path);
            }
            var source = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            try {
                return factory.parse(source, path, 0);
            } catch (EvaluatorException e) {
                throw new RenderingException(formatParseError(path, e), e);
            } catch (RuntimeException e) {
                throw new RenderingException("Error parsing TeaVM JavaScript runtime resource " + path + ": "
                        + e.getMessage(), e);
            }
        } catch (IOException e) {
            throw new RenderingException(e);
        }
    }

    private static String formatParseError(String path, EvaluatorException e) {
        var message = new StringBuilder("Error parsing TeaVM JavaScript runtime resource ");
        message.append(path).append(": ").append(e.details());
        if (e.getLineNumber() > 0) {
            message.append(" at line ").append(e.getLineNumber());
            if (e.getColumnNumber() > 0) {
                message.append(", column ").append(e.getColumnNumber());
            }
        }
        var lineSource = e.getLineSource();
        if (lineSource != null) {
            message.append(": ").append(lineSource.strip());
        }
        return message.toString();
    }

    public final SourceWriterSink sink = new SourceWriterSink() {
        @Override
        public SourceWriterSink appendFunction(String name) {
            removablePartsFinder.markUsedDeclaration(name);
            return this;
        }
    };

    public void removeUnusedParts() {
        var removal = new AstRemoval(removablePartsFinder.getAllRemovableParts());
        for (var part : runtimeAstParts) {
            removal.visit(part);
        }
        for (var part : epilogueAstParts) {
            removal.visit(part);
        }
    }
}
