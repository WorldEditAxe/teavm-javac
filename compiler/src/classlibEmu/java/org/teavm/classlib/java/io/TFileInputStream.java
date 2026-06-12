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

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class TFileInputStream extends InputStream {
    public TFileInputStream(String name) throws FileNotFoundException {
    }

    public TFileInputStream(File file) throws FileNotFoundException {
    }

    public TFileInputStream(FileDescriptor fdObj) {
    }

    @Override
    public int read() throws IOException {
        return -1;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return -1;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return -1;
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        return new byte[0];
    }

    @Override
    public byte[] readNBytes(int len) throws IOException {
        return new byte[0];
    }

    @Override
    public long transferTo(OutputStream out) throws IOException {
        return 0;
    }

    @Override
    public long skip(long n) throws IOException {
        return 0;
    }

    @Override
    public int available() throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {
    }

    public final FileDescriptor getFD() throws IOException {
        return null;
    }

    public FileChannel getChannel() {
        return null;
    }
}
