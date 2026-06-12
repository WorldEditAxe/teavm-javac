/*
 *  Copyright 2026.
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

package org.teavm.classlib.java.io;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.nio.charset.StandardCharsets;
import org.teavm.interop.Async;
import org.teavm.interop.AsyncCallback;
import org.teavm.interop.Export;
import org.teavm.interop.Import;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.typedarrays.Int8Array;
import org.teavm.runtime.fs.VirtualFile;
import org.teavm.runtime.fs.VirtualFileAccessor;
import org.teavm.runtime.fs.VirtualFileSystemProvider;

public final class TFileSystemBridge {
    private static final Map<Integer, AsyncCallback<Object>> pendingOpenCallbacks = new HashMap<>();
    private static int nextOpenRequestId;

    private TFileSystemBridge() {
    }

    public static void install(JSObject runtime) {
        setActiveProgramExternal(runtime);
    }

    static void beforeRead(TFile file) throws IOException {
        var path = file.getPath();
        var mode = "read";
        var bytes = fileOperationData(openExternal(pathBytes(path), pathBytes(mode)));
        writeToVirtualFile(file, bytes);
    }

    static void beforeWrite(TFile file, boolean append) throws IOException {
        var path = file.getPath();
        var mode = append ? "append" : "write";
        var bytes = fileOperationData(openExternal(pathBytes(path), pathBytes(mode)));
        writeToVirtualFile(file, bytes);
    }

    static void afterWrite(TFile file, VirtualFileAccessor accessor) throws IOException {
        if (file == null || accessor == null) {
            return;
        }
        writeExternal(pathBytes(file.getPath()), Int8Array.copyFromJavaArray(snapshot(accessor)));
    }

    static void close(TFile file, String mode, VirtualFileAccessor accessor) throws IOException {
        if (file == null) {
            return;
        }
        var data = accessor != null ? Int8Array.copyFromJavaArray(snapshot(accessor)) : null;
        var path = file.getPath();
        closeExternal(pathBytes(path), pathBytes(mode), data);
    }

    private static void writeToVirtualFile(TFile file, byte[] data) throws IOException {
        var absoluteFile = file.getAbsoluteFile();
        var parentFile = absoluteFile.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        var virtualFile = absoluteFile.findVirtualFile();
        if (virtualFile == null || !virtualFile.exists()) {
            var parent = parentOrRoot(absoluteFile);
            if (parent == null || !parent.isDirectory()) {
                throw new IOException("Could not access virtual file parent: " + file.getPath());
            }
            parent.createFile(absoluteFile.getName());
            virtualFile = absoluteFile.findVirtualFile();
        }
        if (virtualFile == null || !virtualFile.isFile()) {
            throw new IOException("Could not create virtual file: " + file.getPath());
        }
        var accessor = virtualFile.createAccessor(false, true, false);
        if (accessor == null) {
            throw new IOException("Could not open virtual file: " + file.getPath());
        }
        try {
            accessor.write(data, 0, data.length);
            accessor.resize(data.length);
            accessor.flush();
        } finally {
            accessor.close();
        }
    }

    static VirtualFile parentOrRoot(TFile file) {
        var parent = file.findParentFile();
        if (parent != null) {
            return parent;
        }
        var name = file.getName();
        if (name == null || name.isEmpty() || "/".equals(name)) {
            return null;
        }
        var fs = VirtualFileSystemProvider.getInstance();
        var roots = fs.getRoots();
        return fs.getFile(roots.length > 0 ? roots[0] : "");
    }

    private static byte[] snapshot(VirtualFileAccessor accessor) throws IOException {
        int position = accessor.tell();
        int size = accessor.size();
        var data = new byte[size];
        accessor.seek(0);
        int offset = 0;
        while (offset < size) {
            int count = accessor.read(data, offset, size - offset);
            if (count <= 0) {
                break;
            }
            offset += count;
        }
        accessor.seek(position);
        return data;
    }

    private static Int8Array pathBytes(String path) {
        return Int8Array.copyFromJavaArray(path.getBytes(StandardCharsets.UTF_8));
    }

    private static byte[] fileOperationData(int resultId) throws IOException {
        try {
            var bytes = operationBytesToJava(resultId);
            if (bytes.length == 0) {
                throw new IOException("File operation failed");
            }
            if (bytes[0] == 0) {
                return Arrays.copyOfRange(bytes, 1, bytes.length);
            }
            var message = bytes.length > 1
                    ? new String(bytes, 1, bytes.length - 1, StandardCharsets.UTF_8)
                    : "File operation failed";
            throw new IOException(message);
        } finally {
            releaseOperationBytes(resultId);
        }
    }

    private static byte[] operationBytesToJava(int resultId) {
        var length = operationByteLength(resultId);
        var bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = operationByteAt(resultId, i);
        }
        return bytes;
    }

    @Import(module = "teavmFile", name = "setActiveProgram")
    @JSBody(params = { "runtime" }, script = ""
            + "if (globalThis.teavmFile && typeof globalThis.teavmFile.setActiveProgram === 'function') {"
            + "  globalThis.teavmFile.setActiveProgram(runtime);"
            + "} else {"
            + "  globalThis.__teavmActiveProgram = runtime || null;"
            + "}")
    private static native void setActiveProgramExternal(JSObject runtime);

    @Async
    private static native int openExternal(Int8Array pathBytes, Int8Array modeBytes);

    @SuppressWarnings("unchecked")
    private static void openExternal(Int8Array pathBytes, Int8Array modeBytes, AsyncCallback<Integer> callback) {
        int requestId = ++nextOpenRequestId;
        pendingOpenCallbacks.put(requestId, (AsyncCallback<Object>) (AsyncCallback<?>) callback);
        openExternalStart(requestId, pathBytes, modeBytes);
    }

    @Import(module = "teavmFile", name = "openAsyncStart")
    @JSBody(params = { "requestId", "pathBytes", "modeBytes" }, script = "globalThis.teavmFile.openAsyncStart(requestId, pathBytes, modeBytes);")
    private static native void openExternalStart(int requestId, Int8Array pathBytes, Int8Array modeBytes);

    @Export(name = "teavm_file_open_complete")
    public static void completeOpenExternal(int requestId, int resultId) {
        var callback = pendingOpenCallbacks.remove(requestId);
        if (callback != null) {
            callback.complete(resultId);
        }
    }

    private static void writeExternal(Int8Array pathBytes, Int8Array data) throws IOException {
        fileOperationData(writeExternalResult(pathBytes, data));
    }

    @Import(module = "teavmFile", name = "writeSync")
    @JSBody(params = { "pathBytes", "data" }, script = "return globalThis.teavmFile.writeSync(pathBytes, data);")
    private static native int writeExternalResult(Int8Array pathBytes, Int8Array data);

    private static void closeExternal(Int8Array pathBytes, Int8Array modeBytes, Int8Array data) throws IOException {
        fileOperationData(closeExternalResult(pathBytes, modeBytes, data));
    }

    @Import(module = "teavmFile", name = "closeSync")
    @JSBody(params = { "pathBytes", "modeBytes", "data" }, script = "return globalThis.teavmFile.closeSync(pathBytes, modeBytes, data);")
    private static native int closeExternalResult(Int8Array pathBytes, Int8Array modeBytes, Int8Array data);

    @Import(module = "teavmFile", name = "operationByteLength")
    @JSBody(params = { "resultId" }, script = "return globalThis.teavmFile.operationByteLength(resultId);")
    private static native int operationByteLength(int resultId);

    @Import(module = "teavmFile", name = "operationByteAt")
    @JSBody(params = { "resultId", "index" }, script = "return globalThis.teavmFile.operationByteAt(resultId, index);")
    private static native byte operationByteAt(int resultId, int index);

    @Import(module = "teavmFile", name = "releaseOperationBytes")
    @JSBody(params = { "resultId" }, script = "globalThis.teavmFile.releaseOperationBytes(resultId);")
    private static native void releaseOperationBytes(int resultId);

}
