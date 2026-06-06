/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

/*
  Part of the Processing project - http://processing.org

  Copyright (c) 2026 The Processing Foundation

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation, version 2.1.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public
  License along with this library; if not, write to the Free
  Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
  02111-1307  USA
*/

package processing.platform.core;

/**
 * Access point for Processing's active platform runtime.
 */
public final class PlatformRuntimeProvider {
  static private final String RUNTIME_CLASS_PROPERTY = "processing.platform.runtime";
  static private final String DEFAULT_RUNTIME_CLASS = "processing.platform.teavm.P5PlatformRuntime";

  static private PlatformRuntime runtime;


  private PlatformRuntimeProvider() { }


  static public synchronized PlatformRuntime get() {
    if (runtime == null) {
      runtime = createRuntime();
    }
    return runtime;
  }


  static public synchronized void set(PlatformRuntime replacement) {
    if (replacement == null) {
      throw new NullPointerException("replacement");
    }
    runtime = replacement;
  }


  static private PlatformRuntime createRuntime() {
    String className = System.getProperty(RUNTIME_CLASS_PROPERTY, DEFAULT_RUNTIME_CLASS);
    try {
      Class<?> runtimeClass =
        Class.forName(className);
      return (PlatformRuntime) runtimeClass.getDeclaredConstructor().newInstance();

    } catch (Throwable e) {
      throw new RuntimeException("Could not create Processing platform runtime " + className, e);
    }
  }
}
