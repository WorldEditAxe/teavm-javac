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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import org.teavm.runtime.fs.VirtualFileAccessor;

public class TFileOutputStream extends OutputStream {
    private static final byte[] ONE_BYTE_BUFFER = new byte[1];

    private VirtualFileAccessor accessor;
    private TFile file;
    private boolean dirty;

    public TFileOutputStream(TFile file) throws FileNotFoundException {
        this(file, false);
    }

    public TFileOutputStream(String name) throws FileNotFoundException {
        this(new TFile(name));
    }

    public TFileOutputStream(String name, boolean append) throws FileNotFoundException {
        this(new TFile(name), append);
    }

    public TFileOutputStream(TFileDescriptor fdObj) {
        super();
    }

    public TFileOutputStream(TFile file, boolean append) throws FileNotFoundException {
        super();
        this.file = file;
        if (file.getName().isEmpty()) {
            throw new FileNotFoundException("Invalid file name");
        }
        try {
            TFileSystemBridge.beforeWrite(file, append);
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
        var parent = TFileSystemBridge.parentOrRoot(file);
        var virtualFile = file.findVirtualFile();
        if (virtualFile == null && parent != null && parent.isDirectory()) {
            try {
                parent.createFile(file.getName());
            } catch (IOException e) {
                throw new FileNotFoundException();
            }
        }
        virtualFile = file.findVirtualFile();
        if (virtualFile == null || !virtualFile.isFile()) {
            throw new FileNotFoundException("Could not create file");
        }
        accessor = virtualFile.createAccessor(false, true, append);
        if (accessor == null) {
            throw new FileNotFoundException();
        }
        dirty = true;
    }

    public TFileOutputStream(VirtualFileAccessor accessor) {
        super();
        this.accessor = accessor;
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        Objects.requireNonNull(b);
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new IndexOutOfBoundsException();
        }
        ensureOpened();
        accessor.write(b, off, len);
        dirty = true;
        notifyWriteIfDirty();
    }

    @Override
    public void flush() throws IOException {
        ensureOpened();
        accessor.flush();
        notifyWriteIfDirty();
    }

    @Override
    public void close() throws IOException {
        if (accessor != null) {
            try {
                notifyWriteIfDirty();
                TFileSystemBridge.close(file, "write", accessor);
                accessor.close();
            } finally {
                accessor = null;
                file = null;
            }
        }
    }

    @Override
    public void write(int b) throws IOException {
        ensureOpened();
        ONE_BYTE_BUFFER[0] = (byte) b;
        accessor.write(ONE_BYTE_BUFFER, 0, 1);
        dirty = true;
        notifyWriteIfDirty();
    }

    public final TFileDescriptor getFD() throws IOException {
        return new TFileDescriptor();
    }

    private void notifyWriteIfDirty() throws IOException {
        if (!dirty) {
            return;
        }
        TFileSystemBridge.afterWrite(file, accessor);
        dirty = false;
    }

    private void ensureOpened() throws IOException {
        if (accessor == null) {
            throw new IOException("This stream is already closed");
        }
    }
}
