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

package org.teavm.backend.javascript.ast;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.teavm.backend.javascript.rendering.JSParser;
import org.teavm.rhino.javascript.CompilerEnvirons;
import org.teavm.rhino.javascript.Context;
import org.teavm.rhino.javascript.EvaluatorException;
import org.teavm.rhino.javascript.ast.AstNode;

public final class AstUtil {
    private AstUtil() {
    }

    public static AstNode parse(String string) {
        var env = new CompilerEnvirons();
        env.setRecoverFromErrors(true);
        env.setLanguageVersion(Context.VERSION_1_8);
        env.setIdeMode(true);
        var factory = new JSParser(env);

        return factory.parse(string, null, 0);
    }

    public static AstNode parseFromResources(ClassLoader classLoader, String path) {
        try (var input = classLoader.getResourceAsStream(path)) {
            if (input == null) {
                throw new RuntimeException("TeaVM JavaScript resource not found: " + path);
            }
            var source = new String(input.readAllBytes(), StandardCharsets.UTF_8);
            try {
                return parse(source);
            } catch (EvaluatorException e) {
                throw new RuntimeException(formatParseError(path, e), e);
            } catch (RuntimeException e) {
                throw new RuntimeException("Error parsing TeaVM JavaScript resource " + path + ": "
                        + e.getMessage(), e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String formatParseError(String path, EvaluatorException e) {
        var message = new StringBuilder("Error parsing TeaVM JavaScript resource ");
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
}
