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

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.teavm.classlib.ReflectionContext;
import org.teavm.classlib.ReflectionSupplier;
import org.teavm.classlib.ResourceSupplier;
import org.teavm.classlib.ResourceSupplierContext;
import org.teavm.model.FieldReader;
import org.teavm.model.MethodDescriptor;
import org.teavm.model.ValueType;

public final class CompilerResourceSupplier implements ResourceSupplier, ReflectionSupplier {
    private static final Set<String> REFLECTIVE_DEFAULT_CONSTRUCTOR_CLASSES = Set.of(
            "org.teavm.rhino.javascript.Interpreter",
            "org.teavm.rhino.javascript.jdk18.VMBridge_jdk18",
            "org.teavm.rhino.javascript.regexp.RegExpImpl");
    private static final MethodDescriptor DEFAULT_CONSTRUCTOR = new MethodDescriptor("<init>", ValueType.VOID);
    private static final String[] RESOURCES = {
            "org/teavm/backend/javascript/array.js",
            "org/teavm/backend/javascript/check.js",
            "org/teavm/backend/javascript/console.js",
            "org/teavm/backend/javascript/exception.js",
            "org/teavm/backend/javascript/long.js",
            "org/teavm/backend/javascript/metadata.js",
            "org/teavm/backend/javascript/numeric.js",
            "org/teavm/backend/javascript/primitive.js",
            "org/teavm/backend/javascript/reflection.js",
            "org/teavm/backend/javascript/runtime.js",
            "org/teavm/backend/javascript/simpleThread.js",
            "org/teavm/backend/javascript/string.js",
            "org/teavm/backend/javascript/thread.js",
            "org/teavm/backend/javascript/types.js",
            "org/teavm/classlib/java/lang/System.js",
            "org/teavm/classlib/java/lang/ref/ReferenceQueue.js",
            "org/teavm/classlib/java/lang/ref/WeakReference.js",
            "org/teavm/classlib/java/util/ServiceLoader.js",
            "org/teavm/jso/impl/JS.js",
            "org/teavm/platform/plugin/Async.js",
            "org/teavm/platform/plugin/Platform.js",
            "org/teavm/rhino/javascript/resources/Messages.properties",
            "org/teavm/rhino/javascript/resources/Messages_en.properties",
            "org/teavm/rhino/javascript/resources/Messages_fr.properties"
    };

    @Override
    public String[] supplyResources(ResourceSupplierContext context) {
        return RESOURCES.clone();
    }

    @Override
    public Collection<String> getClassesFoundByName(ReflectionContext context) {
        return REFLECTIVE_DEFAULT_CONSTRUCTOR_CLASSES;
    }

    @Override
    public boolean isClassFoundByName(ReflectionContext context, String name) {
        return REFLECTIVE_DEFAULT_CONSTRUCTOR_CLASSES.contains(name);
    }

    @Override
    public Collection<String> getAccessibleFields(ReflectionContext context, String className) {
        if (!isApplicationClass(className)) {
            return List.of();
        }
        var cls = context.getClassSource().get(className);
        return cls != null
                ? cls.getFields().stream().map(FieldReader::getName).collect(Collectors.toList())
                : List.of();
    }

    @Override
    public Collection<MethodDescriptor> getAccessibleMethods(ReflectionContext context, String className) {
        return REFLECTIVE_DEFAULT_CONSTRUCTOR_CLASSES.contains(className) ? List.of(DEFAULT_CONSTRUCTOR) : List.of();
    }

    private static boolean isApplicationClass(String className) {
        return !className.startsWith("java.")
                && !className.startsWith("javax.")
                && !className.startsWith("jdk.")
                && !className.startsWith("sun.")
                && !className.startsWith("com.sun.")
                && !className.startsWith("org.teavm.")
                && !className.startsWith("org.antlr.")
                && !className.startsWith("org.threeten.")
                && !className.startsWith("processing.");
    }
}
