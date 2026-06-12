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
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class TFileOutputStream extends OutputStream {
    public TFileOutputStream(String name) throws FileNotFoundException {
    }

    public TFileOutputStream(String name, boolean append) throws FileNotFoundException {
    }

    public TFileOutputStream(File file) throws FileNotFoundException {
    }

    public TFileOutputStream(File file, boolean append) throws FileNotFoundException {
    }

    public TFileOutputStream(FileDescriptor fdObj) {
    }

    @Override
    public void write(int b) throws IOException {
    }

    @Override
    public void write(byte[] b) throws IOException {
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
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
