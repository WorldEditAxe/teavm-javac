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
import org.teavm.interop.Async;
import org.teavm.interop.AsyncCallback;

final class P5Bridge {
  private P5Bridge() { }


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


  @JSBody(params = { "p5", "renderer", "mouseCallback", "keyCallback" }, script =
    "var target = renderer || p5;" +
    "var canvas = target.canvas || target.elt || p5.canvas || p5._renderer.canvas;" +
    "if (canvas.__processingTeaVMInputInstalled) return;" +
    "canvas.__processingTeaVMInputInstalled = true;" +
    "canvas.tabIndex = canvas.tabIndex >= 0 ? canvas.tabIndex : 0;" +
    "canvas.style.outline = canvas.style.outline || 'none';" +
    "var LEFT = 37, CENTER = 3, RIGHT = 39, CODED = 65535;" +
    "var MOUSE_PRESS = 1, MOUSE_RELEASE = 2, MOUSE_CLICK = 3, MOUSE_DRAG = 4, MOUSE_MOVE = 5, MOUSE_WHEEL = 8;" +
    "var KEY_PRESS = 1, KEY_RELEASE = 2, KEY_TYPE = 3;" +
    "function millis() {" +
    "  return performance.now();" +
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
    "  var candidateDensity = p5.pixelDensity();" +
    "  if (isFinite(candidateDensity) && candidateDensity > 0) density = candidateDensity;" +
    "  else density = window.devicePixelRatio || 1;" +
    "  var logicalWidth = p5.width || canvas.width / density;" +
    "  var logicalHeight = p5.height || canvas.height / density;" +
    "  var scaleX = rect.width ? logicalWidth / rect.width : 1;" +
    "  var scaleY = rect.height ? logicalHeight / rect.height : 1;" +
    "  return {" +
    "    x: Math.round((ev.clientX - rect.left) * scaleX)," +
    "    y: Math.round((ev.clientY - rect.top) * scaleY)" +
    "  };" +
    "}" +
    "function emitMouse(ev, action, count) {" +
    "  var pos = position(ev);" +
    "  p5.__processingTeaVMMouseX = pos.x;" +
    "  p5.__processingTeaVMMouseY = pos.y;" +
    "  mouseCallback(ev, millis(), action, modifiers(ev), pos.x, pos.y, button(ev), count || ev.detail || 0);" +
    "}" +
    "function insideCanvas(ev) {" +
    "  var rect = canvas.getBoundingClientRect();" +
    "  return ev.clientX >= rect.left && ev.clientX <= rect.right && ev.clientY >= rect.top && ev.clientY <= rect.bottom;" +
    "}" +
    "function emitMouseIfInside(ev, action, count) {" +
    "  if (insideCanvas(ev)) emitMouse(ev, action, count);" +
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
    "  var data = keyData(ev);" +
    "  keyCallback(ev, millis(), action, modifiers(ev), data.key, data.keyCode, !!ev.repeat);" +
    "  if (isSketchKey(ev)) ev.preventDefault();" +
    "}" +
    "canvas.addEventListener('mousedown', function(ev) {" +
    "  canvas.focus();" +
    "  emitMouse(ev, MOUSE_PRESS, ev.detail || 1);" +
    "  ev.preventDefault();" +
    "});" +
    "canvas.addEventListener('mousemove', function(ev) {" +
    "  emitMouse(ev, ev.buttons ? MOUSE_DRAG : MOUSE_MOVE, 0);" +
    "});" +
    "window.addEventListener('mousemove', function(ev) {" +
    "  if (ev.target === canvas) return;" +
    "  emitMouseIfInside(ev, ev.buttons ? MOUSE_DRAG : MOUSE_MOVE, 0);" +
    "});" +
    "canvas.addEventListener('click', function(ev) {" +
    "  emitMouse(ev, MOUSE_CLICK, ev.detail || 1);" +
    "});" +
    "canvas.addEventListener('wheel', function(ev) {" +
    "  var count = ev.deltaY < 0 ? -1 : ev.deltaY > 0 ? 1 : 0;" +
    "  emitMouse(ev, MOUSE_WHEEL, count);" +
    "  ev.preventDefault();" +
    "}, { passive: false });" +
    "canvas.addEventListener('contextmenu', function(ev) {" +
    "  ev.preventDefault();" +
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
  static native void installInputHandlers(JSObject p5, JSObject renderer,
                                          MouseInputCallback mouseCallback,
                                          KeyInputCallback keyCallback);


  @JSBody(params = { "p5" }, script =
    "if (isFinite(p5.__processingTeaVMMouseX)) return p5.__processingTeaVMMouseX | 0;" +
    "return isFinite(p5.mouseX) ? p5.mouseX | 0 : 0;")
  static native int mouseX(JSObject p5);


  @JSBody(params = { "p5" }, script =
    "if (isFinite(p5.__processingTeaVMMouseY)) return p5.__processingTeaVMMouseY | 0;" +
    "return isFinite(p5.mouseY) ? p5.mouseY | 0 : 0;")
  static native int mouseY(JSObject p5);


  @JSBody(params = { }, script = "return Date.now();")
  static native double dateNow();


  @JSBody(params = { }, script =
    "return performance.now();")
  static native double performanceNow();


  @JSBody(params = { }, script =
    "return navigator.hardwareConcurrency || 1;")
  static native int hardwareConcurrency();


  @JSBody(params = { }, script =
    "return window.innerWidth || 0;")
  static native int innerWidth();


  @JSBody(params = { }, script =
    "return window.innerHeight || 0;")
  static native int innerHeight();


  @JSBody(params = { }, script =
    "return window.devicePixelRatio || 1;")
  static native double devicePixelRatio();


  @JSBody(params = { "callback" }, script =
    "return requestAnimationFrame(callback);")
  static native double requestAnimationFrame(AnimationFrameCallback callback);


  @Async
  static native double nextAnimationFrame();


  private static void nextAnimationFrame(AsyncCallback<Double> callback) {
    requestAnimationFrame(time -> {
      callback.complete(time);
    });
  }


  @JSBody(params = { "id" }, script =
    "cancelAnimationFrame(id);")
  static native void cancelAnimationFrame(double id);


  @JSBody(params = { "p5" }, script =
    "p5.noLoop();")
  static native void noLoop(JSObject p5);


  @JSBody(params = { "p5", "width", "height", "webgl" }, script =
    "if (!webgl) return p5.createCanvas(width, height);" +
    "var mode = p5.WEBGL || 'webgl';" +
    "return p5.createCanvas(width, height, mode);")
  static native JSObject createCanvas(JSObject p5, int width, int height, boolean webgl);


  @JSBody(params = { "p5", "density" }, script =
    "p5.pixelDensity(density);")
  static native void pixelDensity(JSObject p5, int density);


  @JSBody(params = { "p5", "width", "height" }, script =
    "p5.resizeCanvas(width, height, true);")
  static native void resizeCanvas(JSObject p5, int width, int height);


  @JSBody(params = { "p5", "renderer" }, script =
    "var target = renderer || p5;" +
    "return target.canvas || target.elt || p5.canvas || p5._renderer.canvas;")
  static native JSObject canvas(JSObject p5, JSObject renderer);


  @JSBody(params = { "p5", "title" }, script =
    "document.title = title;")
  static native void setTitle(JSObject p5, String title);


  @JSBody(params = { "url" }, script =
    "window.open(url, '_blank'); return true;")
  static native boolean openWindow(String url);


  @JSBody(params = { "p5", "path" }, script =
    "var decoded = globalThis.__teavmJavacDecodedImages && globalThis.__teavmJavacDecodedImages.get(path);" +
    "if (decoded) return decoded;" +
    "return p5.loadImage(path);")
  static native JSObject loadImage(JSObject p5, String path);


  @JSBody(params = { "p5", "width", "height" }, script =
    "return p5.createImage(width, height);")
  static native JSObject createImage(JSObject p5, int width, int height);


  @JSBody(params = { "p5", "target", "name" }, script =
    "p5.save(target, name); return true;")
  static native boolean save(JSObject p5, JSObject target, String name);


  @JSBody(params = { "image" }, script =
    "return image ? (image.naturalWidth || image.width || 0) : 0;")
  static native int imageWidth(JSObject image);


  @JSBody(params = { "image" }, script =
    "return image ? (image.naturalHeight || image.height || 0) : 0;")
  static native int imageHeight(JSObject image);


  @JSBody(params = { "image", "destPixels", "pixelCount" }, script =
    "if (image && typeof image.loadPixels === 'function') image.loadPixels();" +
    "var pixels = image && image.pixels ? image.pixels : null;" +
    "if (!pixels || !destPixels) return 0;" +
    "var maxPixels = pixels.length >>> 2;" +
    "if (pixelCount > destPixels.length) pixelCount = destPixels.length;" +
    "if (pixelCount > maxPixels) pixelCount = maxPixels;" +
    "for (var i = 0, base = 0; i < pixelCount; i++, base += 4) {" +
    "  destPixels[i] = (pixels[base + 3] << 24) | (pixels[base] << 16) | (pixels[base + 1] << 8) | pixels[base + 2];" +
    "}" +
    "return pixelCount;")
  static native int imageLoadAndCopyImagePixelsToIntArray(JSObject image, int[] destPixels, int pixelCount);


  @JSBody(params = { "image", "sourcePixels", "pixelCount" }, script =
    "if (image && typeof image.loadPixels === 'function') image.loadPixels();" +
    "var pixels = image && image.pixels ? image.pixels : null;" +
    "if (!pixels || !sourcePixels) return 0;" +
    "if (pixelCount > sourcePixels.length) pixelCount = sourcePixels.length;" +
    "var bytePixelCount = pixels.length >>> 2;" +
    "if (pixelCount > bytePixelCount) pixelCount = bytePixelCount;" +
    "for (var i = 0, base = 0; i < pixelCount; i++, base += 4) {" +
    "  var argb = sourcePixels[i];" +
    "  pixels[base] = (argb >>> 16) & 255;" +
    "  pixels[base + 1] = (argb >>> 8) & 255;" +
    "  pixels[base + 2] = argb & 255;" +
    "  pixels[base + 3] = argb >>> 24;" +
    "}" +
    "if (image && typeof image.updatePixels === 'function') image.updatePixels();" +
    "return pixelCount;")
  static native int imageLoadAndWriteImagePixels(JSObject image, int[] sourcePixels, int pixelCount);


  @JSBody(params = { "image", "width", "height" }, script =
    "if (!image) return image;" +
    "if (typeof image.resize === 'function') { image.resize(width, height); return image; }" +
    "var source = image.canvas || image.elt || image;" +
    "var doc = source.ownerDocument || (typeof document !== 'undefined' ? document : null);" +
    "if (!doc) return image;" +
    "var canvas = doc.createElement('canvas');" +
    "var resized = {" +
    "  canvas: canvas, elt: canvas," +
    "  width: Math.max(1, Math.round(Number(width) || 1))," +
    "  height: Math.max(1, Math.round(Number(height) || 1))," +
    "  pixels: null, imageData: null," +
    "  loadPixels: function() {" +
    "    var context = canvas.getContext('2d');" +
    "    this.imageData = context.getImageData(0, 0, canvas.width, canvas.height);" +
    "    this.pixels = this.imageData.data;" +
    "  }," +
    "  updatePixels: function() {" +
    "    if (this.imageData) canvas.getContext('2d').putImageData(this.imageData, 0, 0);" +
    "  }," +
    "  resize: function(nextWidth, nextHeight) {" +
    "    var oldCanvas = doc.createElement('canvas');" +
    "    oldCanvas.width = canvas.width;" +
    "    oldCanvas.height = canvas.height;" +
    "    oldCanvas.getContext('2d').drawImage(canvas, 0, 0);" +
    "    this.width = Math.max(1, Math.round(Number(nextWidth) || this.width));" +
    "    this.height = Math.max(1, Math.round(Number(nextHeight) || this.height));" +
    "    canvas.width = this.width;" +
    "    canvas.height = this.height;" +
    "    canvas.getContext('2d').drawImage(oldCanvas, 0, 0, this.width, this.height);" +
    "    this.imageData = null;" +
    "    this.pixels = null;" +
    "  }" +
    "};" +
    "canvas.width = resized.width;" +
    "canvas.height = resized.height;" +
    "canvas.getContext('2d').drawImage(source, 0, 0, resized.width, resized.height);" +
    "return resized;")
  static native JSObject imageResize(JSObject image, int width, int height);


  @JSBody(params = { "p5", "destPixels", "pixelCount" }, script =
    "if (p5 && typeof p5.loadPixels === 'function') p5.loadPixels();" +
    "var pixels = p5 && p5.pixels ? p5.pixels : null;" +
    "if (!pixels || !destPixels) return 0;" +
    "var maxPixels = pixels.length >>> 2;" +
    "if (pixelCount > destPixels.length) pixelCount = destPixels.length;" +
    "if (pixelCount > maxPixels) pixelCount = maxPixels;" +
    "for (var i = 0, base = 0; i < pixelCount; i++, base += 4) {" +
    "  destPixels[i] = (pixels[base + 3] << 24) | (pixels[base] << 16) | (pixels[base + 1] << 8) | pixels[base + 2];" +
    "}" +
    "return pixelCount;")
  static native int loadPixelsAndCopyHostPixelsToIntArray(JSObject p5, int[] destPixels, int pixelCount);


  @JSBody(params = { "p5", "sourcePixels", "pixelCount" }, script =
    "if (p5 && typeof p5.loadPixels === 'function') p5.loadPixels();" +
    "var pixels = p5 && p5.pixels ? p5.pixels : null;" +
    "if (!pixels || !sourcePixels) return 0;" +
    "if (pixelCount > sourcePixels.length) pixelCount = sourcePixels.length;" +
    "var bytePixelCount = pixels.length >>> 2;" +
    "if (pixelCount > bytePixelCount) pixelCount = bytePixelCount;" +
    "for (var i = 0, base = 0; i < pixelCount; i++, base += 4) {" +
    "  var argb = sourcePixels[i];" +
    "  pixels[base] = (argb >>> 16) & 255;" +
    "  pixels[base + 1] = (argb >>> 8) & 255;" +
    "  pixels[base + 2] = argb & 255;" +
    "  pixels[base + 3] = argb >>> 24;" +
    "}" +
    "if (p5 && typeof p5.updatePixels === 'function') p5.updatePixels();" +
    "return pixelCount;")
  static native int loadAndWriteHostPixels(JSObject p5, int[] sourcePixels, int pixelCount);


  @JSBody(params = { "p5", "r", "g", "b", "a" }, script =
    "p5.background(r, g, b, a);")
  static native void background(JSObject p5, int r, int g, int b, int a);


  @JSBody(params = { "p5", "r", "g", "b", "a" }, script =
    "p5.fill(r, g, b, a);")
  static native void fill(JSObject p5, int r, int g, int b, int a);


  @JSBody(params = { "p5", "r", "g", "b", "a" }, script =
    "p5.ambientMaterial(r, g, b, a);")
  static native void ambientMaterial(JSObject p5, int r, int g, int b, int a);


  @JSBody(params = { "p5", "r", "g", "b", "a" }, script =
    "p5.specularMaterial(r, g, b, a);")
  static native void specularMaterial(JSObject p5, int r, int g, int b, int a);


  @JSBody(params = { "p5", "r", "g", "b", "a" }, script =
    "p5.emissiveMaterial(r, g, b, a);")
  static native void emissiveMaterial(JSObject p5, int r, int g, int b, int a);


  @JSBody(params = { "p5", "shine" }, script =
    "p5.shininess(shine);")
  static native void shininess(JSObject p5, float shine);


  @JSBody(params = { "p5" }, script =
    "p5.noFill();")
  static native void noFill(JSObject p5);


  @JSBody(params = { "p5", "r", "g", "b", "a" }, script =
    "p5.stroke(r, g, b, a);")
  static native void stroke(JSObject p5, int r, int g, int b, int a);


  @JSBody(params = { "p5" }, script =
    "p5.noStroke();")
  static native void noStroke(JSObject p5);


  @JSBody(params = { "p5", "weight" }, script =
    "p5.strokeWeight(weight);")
  static native void strokeWeight(JSObject p5, float weight);


  @JSBody(params = { "p5", "cap" }, script =
    "var mode = cap === 2 ? (p5.ROUND || 'round') : cap === 4 ? (p5.PROJECT || 'square') : (p5.SQUARE || 'butt');" +
    "p5.strokeCap(mode);")
  static native void strokeCap(JSObject p5, int cap);


  @JSBody(params = { "p5", "join" }, script =
    "var mode = join === 2 ? (p5.ROUND || 'round') : join === 32 ? (p5.BEVEL || 'bevel') : (p5.MITER || 'miter');" +
    "p5.strokeJoin(mode);")
  static native void strokeJoin(JSObject p5, int join);


  @JSBody(params = { "p5", "r", "g", "b", "a" }, script =
    "p5.tint(r, g, b, a);")
  static native void tint(JSObject p5, int r, int g, int b, int a);


  @JSBody(params = { "p5" }, script =
    "p5.noTint();")
  static native void noTint(JSObject p5);


  @JSBody(params = { "p5" }, script =
    "p5.push();")
  static native void push(JSObject p5);


  @JSBody(params = { "p5" }, script =
    "p5.pop();")
  static native void pop(JSObject p5);


  @JSBody(params = { "p5" }, script =
    "p5.resetMatrix();")
  static native void resetMatrix(JSObject p5);


  @JSBody(params = { "p5", "x", "y" }, script =
    "p5.translate(x, y);")
  static native void translate(JSObject p5, float x, float y);


  @JSBody(params = { "p5", "x", "y", "z" }, script =
    "p5.translate(x, y, z);")
  static native void translate3(JSObject p5, float x, float y, float z);


  @JSBody(params = { "p5", "angle" }, script =
    "p5.rotate(angle);")
  static native void rotate(JSObject p5, float angle);


  @JSBody(params = { "p5", "angle" }, script =
    "p5.rotateX(angle);")
  static native void rotateX(JSObject p5, float angle);


  @JSBody(params = { "p5", "angle" }, script =
    "p5.rotateY(angle);")
  static native void rotateY(JSObject p5, float angle);


  @JSBody(params = { "p5", "angle" }, script =
    "p5.rotateZ(angle);")
  static native void rotateZ(JSObject p5, float angle);


  @JSBody(params = { "p5", "x", "y" }, script =
    "p5.scale(x, y);")
  static native void scale(JSObject p5, float x, float y);


  @JSBody(params = { "p5", "x", "y", "z" }, script =
    "p5.scale(x, y, z);")
  static native void scale3(JSObject p5, float x, float y, float z);


  @JSBody(params = { "p5", "a", "b", "c", "d", "e", "f" }, script =
    "p5.applyMatrix(a, b, c, d, e, f);")
  static native void applyMatrix(JSObject p5, float a, float b, float c,
                                 float d, float e, float f);


  @JSBody(params = { "p5", "n00", "n01", "n02", "n03", "n10", "n11", "n12", "n13", "n20", "n21", "n22", "n23", "n30", "n31", "n32", "n33" }, script =
    "p5.applyMatrix(n00, n01, n02, n03, n10, n11, n12, n13, n20, n21, n22, n23, n30, n31, n32, n33);")
  static native void applyMatrix3(JSObject p5,
                                  float n00, float n01, float n02, float n03,
                                  float n10, float n11, float n12, float n13,
                                  float n20, float n21, float n22, float n23,
                                  float n30, float n31, float n32, float n33);


  @JSBody(params = { "p5", "x", "y" }, script =
    "p5.point(x, y);")
  static native void point(JSObject p5, float x, float y);


  @JSBody(params = { "p5", "x", "y", "z" }, script =
    "p5.point(x, y, z);")
  static native void point3(JSObject p5, float x, float y, float z);


  @JSBody(params = { "p5", "x1", "y1", "x2", "y2" }, script =
    "p5.line(x1, y1, x2, y2);")
  static native void line(JSObject p5, float x1, float y1, float x2, float y2);


  @JSBody(params = { "p5", "x1", "y1", "z1", "x2", "y2", "z2" }, script =
    "p5.line(x1, y1, z1, x2, y2, z2);")
  static native void line3(JSObject p5, float x1, float y1, float z1,
                           float x2, float y2, float z2);


  @JSBody(params = { "p5", "x1", "y1", "x2", "y2", "x3", "y3" }, script =
    "p5.triangle(x1, y1, x2, y2, x3, y3);")
  static native void triangle(JSObject p5, float x1, float y1, float x2, float y2,
                              float x3, float y3);


  @JSBody(params = { "p5", "x1", "y1", "x2", "y2", "x3", "y3", "x4", "y4" }, script =
    "p5.quad(x1, y1, x2, y2, x3, y3, x4, y4);")
  static native void quad(JSObject p5, float x1, float y1, float x2, float y2,
                          float x3, float y3, float x4, float y4);


  @JSBody(params = { "p5", "x", "y", "width", "height" }, script =
    "p5.rect(x, y, width, height);")
  static native void rect(JSObject p5, float x, float y, float width, float height);


  @JSBody(params = { "p5", "x", "y", "width", "height", "tl", "tr", "br", "bl" }, script =
    "p5.rect(x, y, width, height, tl, tr, br, bl);")
  static native void roundedRect(JSObject p5, float x, float y, float width, float height,
                                 float tl, float tr, float br, float bl);


  @JSBody(params = { "p5", "x", "y", "width", "height" }, script =
    "p5.ellipse(x, y, width, height);")
  static native void ellipse(JSObject p5, float x, float y, float width, float height);


  @JSBody(params = { "p5", "x", "y", "width", "height", "start", "stop", "mode" }, script =
    "var arcMode = mode === 3 ? (p5.PIE || 'pie') : mode === 2 ? (p5.CHORD || 'chord') : (p5.OPEN || 'open');" +
    "p5.arc(x, y, width, height, start, stop, arcMode);")
  static native void arc(JSObject p5, float x, float y, float width, float height,
                         float start, float stop, int mode);


  @JSBody(params = { "p5", "kind" }, script =
    "var mode = null;" +
    "switch (kind) {" +
    "  case 5: mode = p5.LINES || 'lines'; break;" +
    "  case 9: mode = p5.TRIANGLES || 'triangles'; break;" +
    "  case 10: mode = p5.TRIANGLE_STRIP || 'triangle_strip'; break;" +
    "  case 11: mode = p5.TRIANGLE_FAN || 'triangle_fan'; break;" +
    "  case 17: mode = p5.QUADS || 'quads'; break;" +
    "  case 18: mode = p5.QUAD_STRIP || 'quad_strip'; break;" +
    "  case 20: mode = null; break;" +
    "}" +
    "if (mode === null) p5.beginShape(); else p5.beginShape(mode);")
  static native void beginShape(JSObject p5, int kind);


  @JSBody(params = { "p5", "x", "y" }, script =
    "p5.vertex(x, y);")
  static native void vertex(JSObject p5, float x, float y);


  @JSBody(params = { "p5", "x", "y", "z" }, script =
    "p5.vertex(x, y, z);")
  static native void vertex3(JSObject p5, float x, float y, float z);


  @JSBody(params = { "p5", "close" }, script =
    "if (close) p5.endShape(p5.CLOSE || 'close'); else p5.endShape();")
  static native void endShape(JSObject p5, boolean close);


  @JSBody(params = { "p5", "nx", "ny", "nz" }, script =
    "p5.normal(nx, ny, nz);")
  static native void normal(JSObject p5, float nx, float ny, float nz);


  @JSBody(params = { "p5", "width", "height", "depth" }, script =
    "p5.box(width, height, depth);")
  static native void box(JSObject p5, float width, float height, float depth);


  @JSBody(params = { "p5", "radius", "detailX", "detailY" }, script =
    "if (detailX > 0 && detailY > 0) p5.sphere(radius, detailX, detailY);" +
    "else p5.sphere(radius);")
  static native void sphere(JSObject p5, float radius, int detailX, int detailY);


  @JSBody(params = { "p5" }, script =
    "p5.lights();")
  static native void lights(JSObject p5);


  @JSBody(params = { "p5" }, script =
    "p5.noLights();")
  static native void noLights(JSObject p5);


  @JSBody(params = { "p5" }, script =
    "p5.lightFalloff(1, 0, 0);" +
    "p5.specularColor(0, 0, 0);")
  static native void defaultLightState(JSObject p5);


  @JSBody(params = { "p5", "v1", "v2", "v3" }, script =
    "p5.ambientLight(v1, v2, v3);")
  static native void ambientLight(JSObject p5, float v1, float v2, float v3);


  @JSBody(params = { "p5", "constant", "linear", "quadratic" }, script =
    "p5.lightFalloff(constant, linear, quadratic);")
  static native void lightFalloff(JSObject p5, float constant, float linear, float quadratic);


  @JSBody(params = { "p5", "v1", "v2", "v3" }, script =
    "p5.specularColor(v1, v2, v3);")
  static native void lightSpecular(JSObject p5, float v1, float v2, float v3);


  @JSBody(params = { "p5", "v1", "v2", "v3", "x", "y", "z" }, script =
    "p5.pointLight(v1, v2, v3, x, y, z);")
  static native void pointLight(JSObject p5, float v1, float v2, float v3,
                                float x, float y, float z);


  @JSBody(params = { "p5", "v1", "v2", "v3", "nx", "ny", "nz" }, script =
    "p5.directionalLight(v1, v2, v3, nx, ny, nz);")
  static native void directionalLight(JSObject p5, float v1, float v2, float v3,
                                      float nx, float ny, float nz);


  @JSBody(params = { "p5", "v1", "v2", "v3", "x", "y", "z", "nx", "ny", "nz", "angle", "concentration" }, script =
    "p5.spotLight(v1, v2, v3, x, y, z, nx, ny, nz, angle, concentration);")
  static native void spotLight(JSObject p5, float v1, float v2, float v3,
                               float x, float y, float z,
                               float nx, float ny, float nz,
                               float angle, float concentration);


  @JSBody(params = { "p5", "eyeX", "eyeY", "eyeZ", "centerX", "centerY", "centerZ", "upX", "upY", "upZ" }, script =
    "p5.camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);")
  static native void camera(JSObject p5,
                            float eyeX, float eyeY, float eyeZ,
                            float centerX, float centerY, float centerZ,
                            float upX, float upY, float upZ);


  @JSBody(params = { "p5", "left", "right", "bottom", "top", "near", "far" }, script =
    "p5.ortho(left, right, bottom, top, near, far);")
  static native void ortho(JSObject p5, float left, float right,
                           float bottom, float top, float near, float far);


  @JSBody(params = { "p5", "fovy", "aspect", "near", "far" }, script =
    "p5.perspective(fovy, aspect, near, far);")
  static native void perspective(JSObject p5, float fovy, float aspect,
                                 float near, float far);


  @JSBody(params = { "p5", "left", "right", "bottom", "top", "near", "far" }, script =
    "p5.frustum(left, right, bottom, top, near, far);")
  static native void frustum(JSObject p5, float left, float right,
                             float bottom, float top, float near, float far);


  @JSBody(params = { "p5", "image", "x", "y", "width", "height", "sx", "sy", "sw", "sh" }, script =
    "var source = image && (image.canvas || image.elt) ? (image.canvas || image.elt) : image;" +
    "p5.image(source, x, y, width, height, sx, sy, sw, sh);")
  static native void image(JSObject p5, JSObject image, float x, float y,
                           float width, float height, int sx, int sy, int sw, int sh);


  @JSBody(params = { "p5", "text", "x", "y" }, script =
    "function java2DTextCoordinate(value) {" +
    "  value = Number(value);" +
    "  if (!isFinite(value)) return 0;" +
    "  return value < 0 ? Math.ceil(value + 0.5) : Math.floor(value + 0.5);" +
    "}" +
    "p5.push();" +
    "p5.noStroke();" +
    "p5.text(text, java2DTextCoordinate(x), java2DTextCoordinate(y));" +
    "p5.pop();")
  static native void text(JSObject p5, String text, float x, float y);


  @JSBody(params = { "p5", "size" }, script =
    "p5.textSize(size);")
  static native void textSize(JSObject p5, float size);


  @JSBody(params = { "p5", "font" }, script =
    "if (font) p5.textFont(font);")
  static native void textFont(JSObject p5, String font);


  @JSBody(params = { "p5", "alignX", "alignY" }, script =
    "var x = alignX === 3 ? (p5.CENTER || 'center') : alignX === 39 ? (p5.RIGHT || 'right') : (p5.LEFT || 'left');" +
    "var y = alignY === 101 ? (p5.TOP || 'top') : alignY === 102 ? (p5.BOTTOM || 'bottom') :" +
    "        alignY === 3 ? (p5.CENTER || 'center') : (p5.BASELINE || 'alphabetic');" +
    "p5.textAlign(x, y);")
  static native void textAlign(JSObject p5, int alignX, int alignY);


  @JSBody(params = { "p5", "text" }, script =
    "return p5.textWidth(text);")
  static native float textWidth(JSObject p5, String text);


  @JSBody(params = { "p5", "fallback" }, script =
    "var value = p5.textAscent();" +
    "return isFinite(value) && value > 0 ? value : fallback;")
  static native float textAscent(JSObject p5, float fallback);


  @JSBody(params = { "p5", "fallback" }, script =
    "var value = p5.textDescent();" +
    "return isFinite(value) && value > 0 ? value : fallback;")
  static native float textDescent(JSObject p5, float fallback);


  @JSBody(params = { "p5", "cursor" }, script =
    "p5.cursor(cursor);")
  static native void cursor(JSObject p5, String cursor);


  @JSBody(params = { "p5" }, script =
    "p5.noCursor();")
  static native void noCursor(JSObject p5);
}
