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

package processing.platform.teavm;

import org.teavm.jso.JSExport;
import org.teavm.jso.JSObject;

import processing.platform.core.PlatformRuntimeProvider;

/**
 * TeaVM export used by JavaScript hosts to provide a p5 instance.
 */
public final class P5Platform {
  static private JSObject p5;


  private P5Platform() { }


  @JSExport
  static public void install(JSObject p5Instance) {
    if (p5Instance == null) {
      throw new NullPointerException("p5Instance");
    }
    p5 = p5Instance;
    PlatformRuntimeProvider.set(new P5PlatformRuntime(p5Instance));
    P5Bridge.noLoop(p5Instance);
  }


  @JSExport
  static public void useP5(JSObject p5Instance) {
    install(p5Instance);
  }


  @JSExport
  static public void installP5(JSObject p5Instance) {
    install(p5Instance);
  }


  static JSObject getP5() {
    return p5;
  }
}
