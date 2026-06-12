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

public final class TFileDescriptor {
    public static final TFileDescriptor in = new TFileDescriptor(false);
    public static final TFileDescriptor out = new TFileDescriptor(false);
    public static final TFileDescriptor err = new TFileDescriptor(false);

    private final boolean valid;

    public TFileDescriptor() {
        this(false);
    }

    private TFileDescriptor(boolean valid) {
        this.valid = valid;
    }

    public boolean valid() {
        return valid;
    }

    public void sync() throws TSyncFailedException {
        if (!valid) {
            throw new TSyncFailedException("File descriptor is invalid");
        }
    }
}
