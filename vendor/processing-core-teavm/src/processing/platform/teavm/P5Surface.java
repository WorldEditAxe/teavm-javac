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

public class P5Surface extends PSurfaceNone {
  private JSObject renderer;
  private P5Bridge.AnimationFrameCallback frameCallback;
  private P5Bridge.MouseInputCallback mouseInputCallback;
  private P5Bridge.KeyInputCallback keyInputCallback;
  private double frameRequest = -1;
  private double lastFrameMillis = -1;
  private boolean running;


  public P5Surface(PGraphics graphics) {
    super(graphics);
  }


  private JSObject p5() {
    return P5PlatformRuntime.current().p5();
  }


  @Override
  public int displayDensity() {
    return P5PlatformRuntime.current().getDisplayDensity(1);
  }


  @Override
  public int displayDensity(int display) {
    return P5PlatformRuntime.current().getDisplayDensity(display);
  }


  @Override
  public void initFrame(PApplet sketch) {
    this.sketch = sketch;
    P5Bridge.noLoop(p5());
    boolean webgl = graphics instanceof PGraphicsP5 && ((PGraphicsP5) graphics).isWebGL();
    renderer = P5Bridge.createCanvas(p5(), sketch.sketchWidth(), sketch.sketchHeight(), webgl);
    graphics.setSize(sketch.sketchWidth(), sketch.sketchHeight());
    mouseInputCallback = this::postMouseEvent;
    keyInputCallback = this::postKeyEvent;
    P5Bridge.installInputHandlers(p5(), renderer, mouseInputCallback, keyInputCallback);
  }


  @Override
  public Object getNative() {
    return P5Bridge.canvas(p5(), renderer);
  }


  @Override
  public void setTitle(String title) {
    P5Bridge.setTitle(p5(), title);
  }


  @Override
  public void setSize(int width, int height) {
    P5Bridge.resizeCanvas(p5(), width, height);
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
      P5Bridge.cursor(p5(), "default");
      break;
    case PConstants.CROSS:
      P5Bridge.cursor(p5(), "crosshair");
      break;
    case PConstants.HAND:
      P5Bridge.cursor(p5(), "pointer");
      break;
    case PConstants.MOVE:
      P5Bridge.cursor(p5(), "move");
      break;
    case PConstants.TEXT:
      P5Bridge.cursor(p5(), "text");
      break;
    case PConstants.WAIT:
      P5Bridge.cursor(p5(), "wait");
      break;
    default:
      P5Bridge.cursor(p5(), "default");
      break;
    }
  }


  @Override
  public void setCursor(PImage image, int hotspotX, int hotspotY) {
    P5Bridge.cursor(p5(), "default");
  }


  @Override
  public void showCursor() {
    P5Bridge.cursor(p5(), "default");
  }


  @Override
  public void hideCursor() {
    P5Bridge.noCursor(p5());
  }


  @Override
  public PImage loadImage(String path, Object... args) {
    return P5PlatformRuntime.current().loadImage(sketch, path, args);
  }


  @Override
  public boolean openLink(String url) {
    return P5PlatformRuntime.current().openLink(url);
  }


  @Override
  public void selectInput(String prompt, String callback, File file, Object callbackObject) {
    P5PlatformRuntime.current().selectInput(prompt, callback, file, callbackObject);
  }


  @Override
  public void selectOutput(String prompt, String callback, File file, Object callbackObject) {
    P5PlatformRuntime.current().selectOutput(prompt, callback, file, callbackObject);
  }


  @Override
  public void selectFolder(String prompt, String callback, File file, Object callbackObject) {
    P5PlatformRuntime.current().selectFolder(prompt, callback, file, callbackObject);
  }


  @Override
  public void startThread() {
    if (running) {
      throw new IllegalStateException("Thread already started in " + getClass().getSimpleName());
    }

    running = true;
    frameCallback = this::onAnimationFrame;
    sketch.start();
    frameRequest = P5Bridge.requestAnimationFrame(frameCallback);
  }


  @Override
  public boolean stopThread() {
    if (!running) {
      return false;
    }
    running = false;
    if (frameRequest >= 0) {
      P5Bridge.cancelAnimationFrame(frameRequest);
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
      syncMousePosition();
      sketch.handleDraw();
      lastFrameMillis = timeMillis;
    }
    frameRequest = P5Bridge.requestAnimationFrame(frameCallback);
  }


  private void syncMousePosition() {
    JSObject currentP5 = p5();
    int x = P5Bridge.mouseX(currentP5);
    int y = P5Bridge.mouseY(currentP5);
    sketch.mouseX = x;
    sketch.mouseY = y;
    sketch.rmouseX = x;
    sketch.rmouseY = y;
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
