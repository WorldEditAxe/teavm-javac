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

package org.teavm.rhino.javascript;

import java.lang.reflect.AccessibleObject;
import org.teavm.rhino.javascript.jdk18.VMBridge_jdk18;

public abstract class VMBridge {
    static final VMBridge instance = new VMBridge_jdk18();

    protected abstract Object getThreadContextHelper();

    protected abstract Context getContext(Object contextHelper);

    protected abstract void setContext(Object contextHelper, Context cx);

    protected abstract boolean tryToMakeAccessible(AccessibleObject accessible);

    protected abstract Object getInterfaceProxyHelper(ContextFactory cf, Class<?>[] interfaces);

    protected abstract Object newInterfaceProxy(Object proxyHelper, ContextFactory cf, InterfaceAdapter adapter,
            Object target, Scriptable topScope);
}
