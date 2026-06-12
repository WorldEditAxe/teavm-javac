/*
 *  Copyright 2015 Alexey Andreev.
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
package org.teavm.classlib.java.lang;

import java.io.IOException;
import org.teavm.classlib.PlatformDetector;
import org.teavm.classlib.java.io.TInputStream;
import org.teavm.interop.Import;
import org.teavm.jso.JSBody;

class TConsoleInputStream extends TInputStream {
    @Override
    public int read() throws IOException {
        int result;
        if (PlatformDetector.isWebAssemblyGC()) {
            result = readWasmGC();
        } else if (PlatformDetector.isJavaScript()) {
            result = readJs();
        } else {
            result = -1;
        }
        return result >= 0 ? result & 0xFF : -1;
    }

    @Import(name = "readStdin", module = "teavmConsole")
    private static native int readWasmGC();

    @JSBody(script = ""
            + "var reader = typeof $rt_readStdinCustom === 'function' ? $rt_readStdinCustom : null;"
            + "if (reader === null && typeof globalThis !== 'undefined') {"
            + "    reader = globalThis.__teavmJavacReadStdin;"
            + "}"
            + "if (typeof reader !== 'function') {"
            + "    return -1;"
            + "}"
            + "var result = reader();"
            + "return result == null ? -1 : result | 0;")
    private static native int readJs();
}
