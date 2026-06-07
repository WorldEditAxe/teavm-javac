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

import java.io.File;

import org.teavm.jso.JSObject;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PSurfaceNone;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Canvas2DSurface extends PSurfaceNone {
  private JSObject renderer;
  private Canvas2DBridge.AnimationFrameCallback frameCallback;
  private Canvas2DBridge.MouseInputCallback mouseInputCallback;
  private Canvas2DBridge.KeyInputCallback keyInputCallback;
  private double frameRequest = -1;
  private double lastFrameMillis = -1;
  private boolean running;


  public Canvas2DSurface(PGraphics graphics) {
    super(graphics);
  }


  private JSObject host() {
    return Canvas2DPlatformRuntime.current().host();
  }


  @Override
  public int displayDensity() {
    return Canvas2DPlatformRuntime.current().getDisplayDensity(1);
  }


  @Override
  public int displayDensity(int display) {
    return Canvas2DPlatformRuntime.current().getDisplayDensity(display);
  }


  @Override
  public void initFrame(PApplet sketch) {
    this.sketch = sketch;
    Canvas2DBridge.noLoop(host());
    renderer = Canvas2DBridge.createCanvas(host(), sketch.sketchWidth(), sketch.sketchHeight());
    graphics.setSize(sketch.sketchWidth(), sketch.sketchHeight());
    mouseInputCallback = this::postMouseEvent;
    keyInputCallback = this::postKeyEvent;
    Canvas2DBridge.installInputHandlers(host(), renderer, mouseInputCallback, keyInputCallback);
  }


  @Override
  public Object getNative() {
    return Canvas2DBridge.canvas(host(), renderer);
  }


  @Override
  public void setTitle(String title) {
    Canvas2DBridge.setTitle(host(), title);
  }


  @Override
  public void setSize(int width, int height) {
    Canvas2DBridge.resizeCanvas(host(), width, height);
    super.setSize(width, height);
  }


  @Override
  public void setFrameRate(float fps) {
    super.setFrameRate(fps);
  }


  @Override
  public void setCursor(int kind) {
    switch (kind) {
    case PConstants.ARROW:
      Canvas2DBridge.cursor(host(), "default");
      break;
    case PConstants.CROSS:
      Canvas2DBridge.cursor(host(), "crosshair");
      break;
    case PConstants.HAND:
      Canvas2DBridge.cursor(host(), "pointer");
      break;
    case PConstants.MOVE:
      Canvas2DBridge.cursor(host(), "move");
      break;
    case PConstants.TEXT:
      Canvas2DBridge.cursor(host(), "text");
      break;
    case PConstants.WAIT:
      Canvas2DBridge.cursor(host(), "wait");
      break;
    default:
      Canvas2DBridge.cursor(host(), "default");
      break;
    }
  }


  @Override
  public void setCursor(PImage image, int hotspotX, int hotspotY) {
    Canvas2DBridge.cursor(host(), "default");
  }


  @Override
  public void showCursor() {
    Canvas2DBridge.cursor(host(), "default");
  }


  @Override
  public void hideCursor() {
    Canvas2DBridge.noCursor(host());
  }


  @Override
  public PImage loadImage(String path, Object... args) {
    return Canvas2DPlatformRuntime.current().loadImage(sketch, path, args);
  }


  @Override
  public boolean openLink(String url) {
    return Canvas2DPlatformRuntime.current().openLink(url);
  }


  @Override
  public void selectInput(String prompt, String callback, File file, Object callbackObject) {
    Canvas2DPlatformRuntime.current().selectInput(prompt, callback, file, callbackObject);
  }


  @Override
  public void selectOutput(String prompt, String callback, File file, Object callbackObject) {
    Canvas2DPlatformRuntime.current().selectOutput(prompt, callback, file, callbackObject);
  }


  @Override
  public void selectFolder(String prompt, String callback, File file, Object callbackObject) {
    Canvas2DPlatformRuntime.current().selectFolder(prompt, callback, file, callbackObject);
  }


  @Override
  public void startThread() {
    if (running) {
      throw new IllegalStateException("Thread already started in " + getClass().getSimpleName());
    }

    running = true;
    frameCallback = this::onAnimationFrame;
    sketch.start();
    frameRequest = Canvas2DBridge.requestAnimationFrame(frameCallback);
  }


  @Override
  public boolean stopThread() {
    if (!running) {
      return false;
    }
    running = false;
    if (frameRequest >= 0) {
      Canvas2DBridge.cancelAnimationFrame(frameRequest);
      frameRequest = -1;
    }
    return true;
  }


  @Override
  public boolean isStopped() {
    return !running;
  }


  @Override
  public void pauseThread() {
    paused = true;
  }


  @Override
  public void resumeThread() {
    paused = false;
  }


  private void onAnimationFrame(double timeMillis) {
    if (!running) {
      return;
    }

    if (sketch.finished) {
      sketch.dispose();
      if (sketch.exitCalled()) {
        sketch.exitActual();
      }
      return;
    }

    if (!paused && shouldDraw(timeMillis)) {
      sketch.handleDraw();
      lastFrameMillis = timeMillis;
    }
    frameRequest = Canvas2DBridge.requestAnimationFrame(frameCallback);
  }


  private void postMouseEvent(JSObject nativeEvent, double millis, int action, int modifiers,
                              int x, int y, int button, int count) {
    sketch.postEvent(new MouseEvent(nativeEvent, (long) millis, action, modifiers, x, y, button, count));
  }


  private void postKeyEvent(JSObject nativeEvent, double millis, int action, int modifiers,
                            int key, int keyCode, boolean repeat) {
    sketch.postEvent(new KeyEvent(nativeEvent, (long) millis, action, modifiers, (char) key, keyCode, repeat));
  }


  private boolean shouldDraw(double timeMillis) {
    if (lastFrameMillis < 0) {
      return true;
    }
    return timeMillis - lastFrameMillis >= 1000.0 / frameRateTarget;
  }
}
