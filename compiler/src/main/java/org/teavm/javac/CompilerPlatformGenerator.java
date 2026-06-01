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

package org.teavm.javac;

import org.teavm.backend.javascript.codegen.SourceWriter;
import org.teavm.backend.javascript.spi.Generator;
import org.teavm.backend.javascript.spi.GeneratorContext;
import org.teavm.backend.javascript.spi.Injector;
import org.teavm.backend.javascript.spi.InjectorContext;
import org.teavm.backend.javascript.templating.JavaScriptTemplate;
import org.teavm.backend.javascript.templating.JavaScriptTemplateFactory;
import org.teavm.dependency.DependencyAgent;
import org.teavm.dependency.DependencyPlugin;
import org.teavm.dependency.MethodDependency;
import org.teavm.model.MethodReference;
import org.teavm.model.ValueType;

final class CompilerPlatformGenerator implements Generator, Injector, DependencyPlugin {
    static final String PLATFORM_CLASS = "org.teavm.platform.Platform";
    private static final String PLATFORM_RUNNABLE_CLASS = "org.teavm.platform.PlatformRunnable";
    private JavaScriptTemplate template;

    @Override
    public void methodReached(DependencyAgent agent, MethodDependency method) {
        switch (method.getReference().getName()) {
            case "clone":
                method.getVariable(1).connect(method.getResult());
                break;
            case "startThread":
            case "schedule": {
                var launchMethod = agent.linkMethod(new MethodReference(PLATFORM_CLASS, "launchThread",
                        ValueType.object(PLATFORM_RUNNABLE_CLASS), ValueType.VOID));
                method.getVariable(1).connect(launchMethod.getVariable(1));
                launchMethod.use();
                break;
            }
        }
    }

    @Override
    public void generate(InjectorContext context, MethodReference methodRef) {
        if (methodRef.getName().equals("getPlatformObject")) {
            context.writeExpr(context.getArgument(0));
        }
    }

    @Override
    public void generate(GeneratorContext context, SourceWriter writer, MethodReference methodRef) {
        if (template == null) {
            template = new JavaScriptTemplateFactory(context.getClassLoader(), context.getClassSource())
                    .createFromResource("org/teavm/platform/plugin/Platform.js");
        }
        template.builder(methodRef.getName()).withContext(context).build().write(writer, 0);
    }
}
