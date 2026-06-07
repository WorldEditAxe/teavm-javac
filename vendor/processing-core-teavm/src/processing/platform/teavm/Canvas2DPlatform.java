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
 * TeaVM export used by JavaScript hosts to provide a direct Canvas2D host.
 */
public final class Canvas2DPlatform {
  static private JSObject host;


  private Canvas2DPlatform() { }


  @JSExport
  static public void install(JSObject canvasHost) {
    if (canvasHost == null) {
      throw new NullPointerException("canvasHost");
    }
    host = canvasHost;
    Canvas2DBridge.noLoop(canvasHost);
    PlatformRuntimeProvider.set(new Canvas2DPlatformRuntime(canvasHost));
  }


  @JSExport
  static public void useCanvas2D(JSObject canvasHost) {
    install(canvasHost);
  }


  @JSExport
  static public void installCanvas2D(JSObject canvasHost) {
    install(canvasHost);
  }


  static JSObject getHost() {
    return host;
  }
}
