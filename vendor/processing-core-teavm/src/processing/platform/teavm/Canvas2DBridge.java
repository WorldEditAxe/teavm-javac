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

import org.teavm.jso.JSBody;
import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;

final class Canvas2DBridge {
  private Canvas2DBridge() { }


  @JSFunctor
  interface AnimationFrameCallback extends JSObject {
    void call(double time);
  }


  @JSFunctor
  interface MouseInputCallback extends JSObject {
    void call(JSObject nativeEvent, double millis, int action, int modifiers,
              int x, int y, int button, int count);
  }


  @JSFunctor
  interface KeyInputCallback extends JSObject {
    void call(JSObject nativeEvent, double millis, int action, int modifiers,
              int key, int keyCode, boolean repeat);
  }


  @JSBody(params = { "host", "renderer", "mouseCallback", "keyCallback" }, script =
    "var target = renderer || host;" +
    "var canvas = target && target.canvas ? target.canvas : target && target.elt ? target.elt : null;" +
    "if (!canvas && host && host.canvas) canvas = host.canvas;" +
    "if (!canvas && host && host._renderer && host._renderer.canvas) canvas = host._renderer.canvas;" +
    "if (!canvas || canvas.__processingTeaVMInputInstalled) return;" +
    "canvas.__processingTeaVMInputInstalled = true;" +
    "canvas.tabIndex = canvas.tabIndex >= 0 ? canvas.tabIndex : 0;" +
    "canvas.style.outline = canvas.style.outline || 'none';" +
    "var LEFT = 37, CENTER = 3, RIGHT = 39, CODED = 65535;" +
    "var MOUSE_PRESS = 1, MOUSE_RELEASE = 2, MOUSE_CLICK = 3, MOUSE_DRAG = 4, MOUSE_MOVE = 5, MOUSE_WHEEL = 8;" +
    "var KEY_PRESS = 1, KEY_RELEASE = 2, KEY_TYPE = 3;" +
    "function millis() {" +
    "  return typeof performance !== 'undefined' && performance.now ? performance.now() : Date.now();" +
    "}" +
    "function modifiers(ev) {" +
    "  var value = 0;" +
    "  if (ev.shiftKey) value |= 1;" +
    "  if (ev.ctrlKey) value |= 2;" +
    "  if (ev.metaKey) value |= 4;" +
    "  if (ev.altKey) value |= 8;" +
    "  return value;" +
    "}" +
    "function button(ev) {" +
    "  return ev.button === 1 ? CENTER : ev.button === 2 ? RIGHT : LEFT;" +
    "}" +
    "function position(ev) {" +
    "  var rect = canvas.getBoundingClientRect();" +
    "  var density = 1;" +
    "  if (host && typeof host.pixelDensity === 'function') {" +
    "    var candidateDensity = host.pixelDensity();" +
    "    if (isFinite(candidateDensity) && candidateDensity > 0) density = candidateDensity;" +
    "  } else if (typeof window !== 'undefined' && window.devicePixelRatio) {" +
    "    density = window.devicePixelRatio;" +
    "  }" +
    "  var logicalWidth = host && host.width ? host.width : canvas.width / density;" +
    "  var logicalHeight = host && host.height ? host.height : canvas.height / density;" +
    "  var scaleX = rect.width ? logicalWidth / rect.width : 1;" +
    "  var scaleY = rect.height ? logicalHeight / rect.height : 1;" +
    "  return {" +
    "    x: Math.round((ev.clientX - rect.left) * scaleX)," +
    "    y: Math.round((ev.clientY - rect.top) * scaleY)" +
    "  };" +
    "}" +
    "function emitMouse(ev, action, count) {" +
    "  if (!mouseCallback) return;" +
    "  var pos = position(ev);" +
    "  mouseCallback(ev, millis(), action, modifiers(ev), pos.x, pos.y, button(ev), count || ev.detail || 0);" +
    "}" +
    "function keyData(ev) {" +
    "  var named = {" +
    "    ArrowLeft: 37, ArrowUp: 38, ArrowRight: 39, ArrowDown: 40," +
    "    Alt: 18, Control: 17, Shift: 16, Meta: 157," +
    "    Backspace: 8, Tab: 9, Enter: 10, Escape: 27, Delete: 127" +
    "  };" +
    "  var code = named[ev.key];" +
    "  if (code) return { key: CODED, keyCode: code };" +
    "  if (ev.key === ' ') return { key: 32, keyCode: 32 };" +
    "  if (ev.key && ev.key.length === 1) {" +
    "    return { key: ev.key.charCodeAt(0), keyCode: ev.key.toUpperCase().charCodeAt(0) };" +
    "  }" +
    "  return { key: 0, keyCode: ev.keyCode || ev.which || 0 };" +
    "}" +
    "function isSketchKey(ev) {" +
    "  return ev.key === ' ' || ev.key === 'Tab' || ev.key === 'Backspace' || ev.key === 'Delete' ||" +
    "    ev.key === 'ArrowLeft' || ev.key === 'ArrowRight' || ev.key === 'ArrowUp' || ev.key === 'ArrowDown' ||" +
    "    /^[a-zA-Z0-9]$/.test(ev.key || '');" +
    "}" +
    "function emitKey(ev, action) {" +
    "  if (!keyCallback) return;" +
    "  var data = keyData(ev);" +
    "  keyCallback(ev, millis(), action, modifiers(ev), data.key, data.keyCode, !!ev.repeat);" +
    "  if (isSketchKey(ev) && typeof ev.preventDefault === 'function') ev.preventDefault();" +
    "}" +
    "canvas.addEventListener('mousedown', function(ev) {" +
    "  canvas.focus();" +
    "  emitMouse(ev, MOUSE_PRESS, ev.detail || 1);" +
    "  if (typeof ev.preventDefault === 'function') ev.preventDefault();" +
    "});" +
    "canvas.addEventListener('mousemove', function(ev) {" +
    "  emitMouse(ev, ev.buttons ? MOUSE_DRAG : MOUSE_MOVE, 0);" +
    "});" +
    "canvas.addEventListener('click', function(ev) {" +
    "  emitMouse(ev, MOUSE_CLICK, ev.detail || 1);" +
    "});" +
    "canvas.addEventListener('wheel', function(ev) {" +
    "  var count = ev.deltaY < 0 ? -1 : ev.deltaY > 0 ? 1 : 0;" +
    "  emitMouse(ev, MOUSE_WHEEL, count);" +
    "  if (typeof ev.preventDefault === 'function') ev.preventDefault();" +
    "}, { passive: false });" +
    "canvas.addEventListener('contextmenu', function(ev) {" +
    "  if (typeof ev.preventDefault === 'function') ev.preventDefault();" +
    "});" +
    "window.addEventListener('mouseup', function(ev) {" +
    "  emitMouse(ev, MOUSE_RELEASE, ev.detail || 1);" +
    "});" +
    "canvas.addEventListener('keydown', function(ev) {" +
    "  emitKey(ev, KEY_PRESS);" +
    "});" +
    "canvas.addEventListener('keyup', function(ev) {" +
    "  emitKey(ev, KEY_RELEASE);" +
    "});" +
    "canvas.addEventListener('keypress', function(ev) {" +
    "  emitKey(ev, KEY_TYPE);" +
    "});")
  static native void installInputHandlers(JSObject host, JSObject renderer,
                                          MouseInputCallback mouseCallback,
                                          KeyInputCallback keyCallback);


  @JSBody(params = { }, script = "return Date.now();")
  static native double dateNow();


  @JSBody(params = { }, script =
    "return typeof performance !== 'undefined' && performance.now ? performance.now() : Date.now();")
  static native double performanceNow();


  @JSBody(params = { }, script =
    "return typeof navigator !== 'undefined' && navigator.hardwareConcurrency ? navigator.hardwareConcurrency : 1;")
  static native int hardwareConcurrency();


  @JSBody(params = { }, script =
    "return typeof window !== 'undefined' && window.innerWidth ? window.innerWidth : 0;")
  static native int innerWidth();


  @JSBody(params = { }, script =
    "return typeof window !== 'undefined' && window.innerHeight ? window.innerHeight : 0;")
  static native int innerHeight();


  @JSBody(params = { }, script =
    "return typeof window !== 'undefined' && window.devicePixelRatio ? window.devicePixelRatio : 1;")
  static native double devicePixelRatio();


  @JSBody(params = { "callback" }, script =
    "return requestAnimationFrame(callback);")
  static native double requestAnimationFrame(AnimationFrameCallback callback);


  @JSBody(params = { "id" }, script =
    "if (typeof cancelAnimationFrame === 'function') cancelAnimationFrame(id);")
  static native void cancelAnimationFrame(double id);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.noLoop === 'function') host.noLoop();")
  static native void noLoop(JSObject host);


  @JSBody(params = { "host", "width", "height" }, script =
    "if (!host || typeof host.createCanvas !== 'function') return null;" +
    "return host.createCanvas(width, height);")
  static native JSObject createCanvas(JSObject host, int width, int height);


  @JSBody(params = { "host", "width", "height" }, script =
    "if (host && typeof host.resizeCanvas === 'function') host.resizeCanvas(width, height, true);")
  static native void resizeCanvas(JSObject host, int width, int height);


  @JSBody(params = { "host", "renderer" }, script =
    "var target = renderer || host;" +
    "if (target && target.canvas) return target.canvas;" +
    "if (target && target.elt) return target.elt;" +
    "if (host && host.canvas) return host.canvas;" +
    "if (host && host._renderer && host._renderer.canvas) return host._renderer.canvas;" +
    "return null;")
  static native JSObject canvas(JSObject host, JSObject renderer);


  @JSBody(params = { "host", "title" }, script =
    "if (typeof document !== 'undefined') document.title = title;")
  static native void setTitle(JSObject host, String title);


  @JSBody(params = { "url" }, script =
    "if (typeof window === 'undefined' || typeof window.open !== 'function') return false;" +
    "window.open(url, '_blank'); return true;")
  static native boolean openWindow(String url);


  @JSBody(params = { "host", "path" }, script =
    "if (!host || typeof host.loadImage !== 'function') return null;" +
    "return host.loadImage(path);")
  static native JSObject loadImage(JSObject host, String path);


  @JSBody(params = { "host", "width", "height" }, script =
    "if (!host || typeof host.createImage !== 'function') return null;" +
    "return host.createImage(width, height);")
  static native JSObject createImage(JSObject host, int width, int height);


  @JSBody(params = { "host", "target", "name" }, script =
    "if (!host || typeof host.save !== 'function') return false;" +
    "host.save(target, name); return true;")
  static native boolean save(JSObject host, JSObject target, String name);


  @JSBody(params = { "image" }, script =
    "return image && image.width ? image.width : 0;")
  static native int imageWidth(JSObject image);


  @JSBody(params = { "image" }, script =
    "return image && image.height ? image.height : 0;")
  static native int imageHeight(JSObject image);


  @JSBody(params = { "image" }, script =
    "if (image && typeof image.loadPixels === 'function') image.loadPixels();")
  static native void imageLoadPixels(JSObject image);


  @JSBody(params = { "image" }, script =
    "if (image && typeof image.updatePixels === 'function') image.updatePixels();")
  static native void imageUpdatePixels(JSObject image);


  @JSBody(params = { "image", "width", "height" }, script =
    "if (image && typeof image.resize === 'function') image.resize(width, height);")
  static native void imageResize(JSObject image, int width, int height);


  @JSBody(params = { "image" }, script =
    "return image && image.pixels ? image.pixels.length : 0;")
  static native int imagePixelLength(JSObject image);


  @JSBody(params = { "image", "index" }, script =
    "return image && image.pixels ? image.pixels[index] | 0 : 0;")
  static native int imagePixel(JSObject image, int index);


  @JSBody(params = { "image", "index", "value" }, script =
    "if (image && image.pixels) image.pixels[index] = value & 255;")
  static native void setImagePixel(JSObject image, int index, int value);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.loadPixels === 'function') host.loadPixels();")
  static native void loadPixels(JSObject host);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.updatePixels === 'function') host.updatePixels();")
  static native void updatePixels(JSObject host);


  @JSBody(params = { "host" }, script =
    "return host && host.pixels ? host.pixels.length : 0;")
  static native int pixelLength(JSObject host);


  @JSBody(params = { "host", "index" }, script =
    "return host && host.pixels ? host.pixels[index] | 0 : 0;")
  static native int pixel(JSObject host, int index);


  @JSBody(params = { "host", "index", "value" }, script =
    "if (host && host.pixels) host.pixels[index] = value & 255;")
  static native void setPixel(JSObject host, int index, int value);


  @JSBody(params = { "host", "r", "g", "b", "a" }, script =
    "if (host && typeof host.background === 'function') host.background(r, g, b, a);")
  static native void background(JSObject host, int r, int g, int b, int a);


  @JSBody(params = { "host", "r", "g", "b", "a" }, script =
    "if (host && typeof host.fill === 'function') host.fill(r, g, b, a);")
  static native void fill(JSObject host, int r, int g, int b, int a);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.noFill === 'function') host.noFill();")
  static native void noFill(JSObject host);


  @JSBody(params = { "host", "r", "g", "b", "a" }, script =
    "if (host && typeof host.stroke === 'function') host.stroke(r, g, b, a);")
  static native void stroke(JSObject host, int r, int g, int b, int a);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.noStroke === 'function') host.noStroke();")
  static native void noStroke(JSObject host);


  @JSBody(params = { "host", "weight" }, script =
    "if (host && typeof host.strokeWeight === 'function') host.strokeWeight(weight);")
  static native void strokeWeight(JSObject host, float weight);


  @JSBody(params = { "host", "cap" }, script =
    "if (!host || typeof host.strokeCap !== 'function') return;" +
    "var mode = cap === 2 ? (host.ROUND || 'round') : cap === 4 ? (host.PROJECT || 'square') : (host.SQUARE || 'butt');" +
    "host.strokeCap(mode);")
  static native void strokeCap(JSObject host, int cap);


  @JSBody(params = { "host", "join" }, script =
    "if (!host || typeof host.strokeJoin !== 'function') return;" +
    "var mode = join === 2 ? (host.ROUND || 'round') : join === 32 ? (host.BEVEL || 'bevel') : (host.MITER || 'miter');" +
    "host.strokeJoin(mode);")
  static native void strokeJoin(JSObject host, int join);


  @JSBody(params = { "host", "r", "g", "b", "a" }, script =
    "if (host && typeof host.tint === 'function') host.tint(r, g, b, a);")
  static native void tint(JSObject host, int r, int g, int b, int a);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.noTint === 'function') host.noTint();")
  static native void noTint(JSObject host);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.push === 'function') host.push();")
  static native void push(JSObject host);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.pop === 'function') host.pop();")
  static native void pop(JSObject host);


  @JSBody(params = { "host", "x", "y", "width", "height" }, script =
    "if (host && typeof host.clipRect === 'function') host.clipRect(x, y, width, height);")
  static native void clipRect(JSObject host, float x, float y, float width, float height);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.noClip === 'function') host.noClip();")
  static native void noClip(JSObject host);


  @JSBody(params = { "host", "mode" }, script =
    "if (!host || typeof host.blendMode !== 'function') return;" +
    "var blendMode = 'blend';" +
    "switch (mode) {" +
    "  case 0: blendMode = 'replace'; break;" +
    "  case 1: blendMode = 'blend'; break;" +
    "  case 2: blendMode = 'add'; break;" +
    "  case 4: blendMode = 'subtract'; break;" +
    "  case 8: blendMode = 'lightest'; break;" +
    "  case 16: blendMode = 'darkest'; break;" +
    "  case 32: blendMode = 'difference'; break;" +
    "  case 64: blendMode = 'exclusion'; break;" +
    "  case 128: blendMode = 'multiply'; break;" +
    "  case 256: blendMode = 'screen'; break;" +
    "  case 512: blendMode = 'overlay'; break;" +
    "  case 1024: blendMode = 'hard_light'; break;" +
    "  case 2048: blendMode = 'soft_light'; break;" +
    "  case 4096: blendMode = 'dodge'; break;" +
    "  case 8192: blendMode = 'burn'; break;" +
    "}" +
    "host.blendMode(blendMode);")
  static native void blendMode(JSObject host, int mode);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.resetMatrix === 'function') host.resetMatrix();")
  static native void resetMatrix(JSObject host);


  @JSBody(params = { "host", "x", "y" }, script =
    "if (host && typeof host.translate === 'function') host.translate(x, y);")
  static native void translate(JSObject host, float x, float y);


  @JSBody(params = { "host", "angle" }, script =
    "if (host && typeof host.rotate === 'function') host.rotate(angle);")
  static native void rotate(JSObject host, float angle);


  @JSBody(params = { "host", "x", "y" }, script =
    "if (host && typeof host.scale === 'function') host.scale(x, y);")
  static native void scale(JSObject host, float x, float y);


  @JSBody(params = { "host", "a", "b", "c", "d", "e", "f" }, script =
    "if (host && typeof host.applyMatrix === 'function') host.applyMatrix(a, b, c, d, e, f);")
  static native void applyMatrix(JSObject host, float a, float b, float c,
                                 float d, float e, float f);


  @JSBody(params = { "host", "x", "y" }, script =
    "if (host && typeof host.point === 'function') host.point(x, y);")
  static native void point(JSObject host, float x, float y);


  @JSBody(params = { "host", "x1", "y1", "x2", "y2" }, script =
    "if (host && typeof host.line === 'function') host.line(x1, y1, x2, y2);")
  static native void line(JSObject host, float x1, float y1, float x2, float y2);


  @JSBody(params = { "host", "x1", "y1", "x2", "y2", "x3", "y3" }, script =
    "if (host && typeof host.triangle === 'function') host.triangle(x1, y1, x2, y2, x3, y3);")
  static native void triangle(JSObject host, float x1, float y1, float x2, float y2,
                              float x3, float y3);


  @JSBody(params = { "host", "x1", "y1", "x2", "y2", "x3", "y3", "x4", "y4" }, script =
    "if (host && typeof host.quad === 'function') host.quad(x1, y1, x2, y2, x3, y3, x4, y4);")
  static native void quad(JSObject host, float x1, float y1, float x2, float y2,
                          float x3, float y3, float x4, float y4);


  @JSBody(params = { "host", "x", "y", "width", "height" }, script =
    "if (host && typeof host.rect === 'function') host.rect(x, y, width, height);")
  static native void rect(JSObject host, float x, float y, float width, float height);


  @JSBody(params = { "host", "x", "y", "width", "height", "tl", "tr", "br", "bl" }, script =
    "if (host && typeof host.roundedRect === 'function') host.roundedRect(x, y, width, height, tl, tr, br, bl);")
  static native void roundedRect(JSObject host, float x, float y, float width, float height,
                                 float tl, float tr, float br, float bl);


  @JSBody(params = { "host", "x", "y", "width", "height" }, script =
    "if (host && typeof host.ellipse === 'function') host.ellipse(x, y, width, height);")
  static native void ellipse(JSObject host, float x, float y, float width, float height);


  @JSBody(params = { "host", "x", "y", "width", "height", "start", "stop", "mode" }, script =
    "if (!host || typeof host.arc !== 'function') return;" +
    "var arcMode = mode === 3 ? (host.PIE || 'pie') :" +
    "              mode === 2 ? (host.CHORD || 'chord') :" +
    "              mode === 1 ? (host.OPEN || 'open') : 'default';" +
    "host.arc(x, y, width, height, start, stop, arcMode);")
  static native void arc(JSObject host, float x, float y, float width, float height,
                         float start, float stop, int mode);


  @JSBody(params = { "host", "kind" }, script =
    "if (!host || typeof host.beginShape !== 'function') return;" +
    "var mode = null;" +
    "switch (kind) {" +
    "  case 3: mode = host.POINTS || 'points'; break;" +
    "  case 5: mode = host.LINES || 'lines'; break;" +
    "  case 9: mode = host.TRIANGLES || 'triangles'; break;" +
    "  case 10: mode = host.TRIANGLE_STRIP || 'triangle_strip'; break;" +
    "  case 11: mode = host.TRIANGLE_FAN || 'triangle_fan'; break;" +
    "  case 17: mode = host.QUADS || 'quads'; break;" +
    "  case 18: mode = host.QUAD_STRIP || 'quad_strip'; break;" +
    "  case 20: mode = null; break;" +
    "  case 50: mode = host.LINE_STRIP || 'line_strip'; break;" +
    "  case 51: mode = host.LINE_LOOP || 'line_loop'; break;" +
    "}" +
    "if (mode === null) host.beginShape(); else host.beginShape(mode);")
  static native void beginShape(JSObject host, int kind);


  @JSBody(params = { "host", "x", "y" }, script =
    "if (host && typeof host.vertex === 'function') host.vertex(x, y);")
  static native void vertex(JSObject host, float x, float y);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.beginContour === 'function') host.beginContour();")
  static native void beginContour(JSObject host);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.endContour === 'function') host.endContour();")
  static native void endContour(JSObject host);


  @JSBody(params = { "host", "x1", "y1", "x2", "y2", "x3", "y3" }, script =
    "if (host && typeof host.bezierVertex === 'function') host.bezierVertex(x1, y1, x2, y2, x3, y3);")
  static native void bezierVertex(JSObject host, float x1, float y1,
                                  float x2, float y2, float x3, float y3);


  @JSBody(params = { "host", "x0", "y0", "x1", "y1", "x2", "y2", "x3", "y3" }, script =
    "if (host && typeof host.curveVertexSegment === 'function') host.curveVertexSegment(x0, y0, x1, y1, x2, y2, x3, y3);")
  static native void curveVertexSegment(JSObject host, float x0, float y0,
                                        float x1, float y1, float x2, float y2,
                                        float x3, float y3);


  @JSBody(params = { "host", "close" }, script =
    "if (!host || typeof host.endShape !== 'function') return;" +
    "if (close) host.endShape(host.CLOSE || 'close'); else host.endShape();")
  static native void endShape(JSObject host, boolean close);


  @JSBody(params = { "host", "image", "x", "y", "width", "height", "sx", "sy", "sw", "sh" }, script =
    "if (!host || !image || typeof host.image !== 'function') return;" +
    "host.image(image, x, y, width, height, sx, sy, sw, sh);")
  static native void image(JSObject host, JSObject image, float x, float y,
                           float width, float height, int sx, int sy, int sw, int sh);


  @JSBody(params = { "host", "text", "x", "y" }, script =
    "if (host && typeof host.text === 'function') host.text(text, x, y);")
  static native void text(JSObject host, String text, float x, float y);


  @JSBody(params = { "host", "size" }, script =
    "if (host && typeof host.textSize === 'function') host.textSize(size);")
  static native void textSize(JSObject host, float size);


  @JSBody(params = { "host", "font" }, script =
    "if (host && font && typeof host.textFont === 'function') host.textFont(font);")
  static native void textFont(JSObject host, String font);


  @JSBody(params = { "host", "alignX", "alignY" }, script =
    "if (!host || typeof host.textAlign !== 'function') return;" +
    "var x = alignX === 3 ? (host.CENTER || 'center') : alignX === 39 ? (host.RIGHT || 'right') : (host.LEFT || 'left');" +
    "var y = alignY === 101 ? (host.TOP || 'top') : alignY === 102 ? (host.BOTTOM || 'bottom') :" +
    "        alignY === 3 ? (host.CENTER || 'center') : (host.BASELINE || 'alphabetic');" +
    "host.textAlign(x, y);")
  static native void textAlign(JSObject host, int alignX, int alignY);


  @JSBody(params = { "host", "text" }, script =
    "if (!host || typeof host.textWidth !== 'function') return text ? text.length * 10 : 0;" +
    "return host.textWidth(text);")
  static native float textWidth(JSObject host, String text);


  @JSBody(params = { "host", "fallback" }, script =
    "if (!host || typeof host.textAscent !== 'function') return fallback;" +
    "var value = host.textAscent();" +
    "return isFinite(value) && value > 0 ? value : fallback;")
  static native float textAscent(JSObject host, float fallback);


  @JSBody(params = { "host", "fallback" }, script =
    "if (!host || typeof host.textDescent !== 'function') return fallback;" +
    "var value = host.textDescent();" +
    "return isFinite(value) && value > 0 ? value : fallback;")
  static native float textDescent(JSObject host, float fallback);


  @JSBody(params = { "host", "cursor" }, script =
    "if (!host || typeof host.cursor !== 'function') return;" +
    "host.cursor(cursor);")
  static native void cursor(JSObject host, String cursor);


  @JSBody(params = { "host" }, script =
    "if (host && typeof host.noCursor === 'function') host.noCursor();")
  static native void noCursor(JSObject host);
}
