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
import java.io.InputStream;
import java.util.Objects;
import org.teavm.runtime.fs.VirtualFileAccessor;

public class TFileInputStream extends InputStream {
    private static final byte[] ONE_BYTE_BUFFER = new byte[1];

    private VirtualFileAccessor accessor;
    private TFile file;

    public TFileInputStream(TFile file) throws FileNotFoundException {
        super();
        this.file = file;
        try {
            TFileSystemBridge.beforeRead(file);
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
        var virtualFile = file.findVirtualFile();
        if (virtualFile == null || !virtualFile.isFile()) {
            throw new FileNotFoundException();
        }
        accessor = virtualFile.createAccessor(true, false, false);
        if (accessor == null) {
            throw new FileNotFoundException();
        }
    }

    public TFileInputStream(String name) throws FileNotFoundException {
        this(new TFile(name));
    }

    public TFileInputStream(TFileDescriptor fdObj) {
        super();
    }

    public TFileInputStream(VirtualFileAccessor accessor) {
        super();
        this.accessor = accessor;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        Objects.requireNonNull(b);
        if (off < 0 || len < 0 || off > b.length - len) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }
        ensureOpened();
        int result = accessor.read(b, off, len);
        return result > 0 ? result : -1;
    }

    @Override
    public long skip(long n) throws IOException {
        if (n < 0) {
            throw new IOException("Can't skip negative number of bytes: " + n);
        }
        if (n == 0) {
            return 0;
        }
        ensureOpened();
        int position = accessor.tell();
        accessor.skip((int) n - 1);
        if (accessor.read(ONE_BYTE_BUFFER, 0, 1) < 1) {
            int size = accessor.size();
            accessor.seek(size);
            return size - position;
        }
        return n;
    }

    @Override
    public int available() throws IOException {
        ensureOpened();
        return Math.max(0, accessor.size() - accessor.tell());
    }

    @Override
    public void close() throws IOException {
        if (accessor != null) {
            try {
                TFileSystemBridge.close(file, "read", null);
            } finally {
                accessor.close();
            }
        }
        accessor = null;
        file = null;
    }

    @Override
    public int read() throws IOException {
        ensureOpened();
        int result = accessor.read(ONE_BYTE_BUFFER, 0, 1);
        return result != 0 ? ONE_BYTE_BUFFER[0] & 0xff : -1;
    }

    public final TFileDescriptor getFD() throws IOException {
        return new TFileDescriptor();
    }

    private void ensureOpened() throws IOException {
        if (accessor == null) {
            throw new IOException("This stream is already closed");
        }
    }
}
