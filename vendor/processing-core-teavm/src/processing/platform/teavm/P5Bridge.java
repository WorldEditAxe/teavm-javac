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

final class P5Bridge {
  private P5Bridge() { }


  @JSFunctor
  interface AnimationFrameCallback extends JSObject {
    void call(double time);
  }


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


  @JSBody(params = { "p5" }, script =
    "if (p5 && typeof p5.noLoop === 'function') p5.noLoop();")
  static native void noLoop(JSObject p5);


  @JSBody(params = { "p5", "width", "height" }, script =
    "if (!p5 || typeof p5.createCanvas !== 'function') return null;" +
    "return p5.createCanvas(width, height);")
  static native JSObject createCanvas(JSObject p5, int width, int height);


  @JSBody(params = { "p5", "width", "height" }, script =
    "if (p5 && typeof p5.resizeCanvas === 'function') p5.resizeCanvas(width, height, true);")
  static native void resizeCanvas(JSObject p5, int width, int height);


  @JSBody(params = { "p5", "renderer" }, script =
    "var target = renderer || p5;" +
    "if (target && target.canvas) return target.canvas;" +
    "if (target && target.elt) return target.elt;" +
    "if (p5 && p5.canvas) return p5.canvas;" +
    "if (p5 && p5._renderer && p5._renderer.canvas) return p5._renderer.canvas;" +
    "return null;")
  static native JSObject canvas(JSObject p5, JSObject renderer);


  @JSBody(params = { "p5", "title" }, script =
    "if (typeof document !== 'undefined') document.title = title;")
  static native void setTitle(JSObject p5, String title);


  @JSBody(params = { "url" }, script =
    "if (typeof window === 'undefined' || typeof window.open !== 'function') return false;" +
    "window.open(url, '_blank'); return true;")
  static native boolean openWindow(String url);


  @JSBody(params = { "p5", "path" }, script =
    "if (!p5 || typeof p5.loadImage !== 'function') return null;" +
    "return p5.loadImage(path);")
  static native JSObject loadImage(JSObject p5, String path);


  @JSBody(params = { "p5", "width", "height" }, script =
    "if (!p5 || typeof p5.createImage !== 'function') return null;" +
    "return p5.createImage(width, height);")
  static native JSObject createImage(JSObject p5, int width, int height);


  @JSBody(params = { "p5", "target", "name" }, script =
    "if (!p5 || typeof p5.save !== 'function') return false;" +
    "p5.save(target, name); return true;")
  static native boolean save(JSObject p5, JSObject target, String name);


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


  @JSBody(params = { "p5" }, script =
    "if (p5 && typeof p5.loadPixels === 'function') p5.loadPixels();")
  static native void loadPixels(JSObject p5);


  @JSBody(params = { "p5" }, script =
    "if (p5 && typeof p5.updatePixels === 'function') p5.updatePixels();")
  static native void updatePixels(JSObject p5);


  @JSBody(params = { "p5" }, script =
    "return p5 && p5.pixels ? p5.pixels.length : 0;")
  static native int pixelLength(JSObject p5);


  @JSBody(params = { "p5", "index" }, script =
    "return p5 && p5.pixels ? p5.pixels[index] | 0 : 0;")
  static native int pixel(JSObject p5, int index);


  @JSBody(params = { "p5", "index", "value" }, script =
    "if (p5 && p5.pixels) p5.pixels[index] = value & 255;")
  static native void setPixel(JSObject p5, int index, int value);


  @JSBody(params = { "p5", "r", "g", "b", "a" }, script =
    "if (p5 && typeof p5.background === 'function') p5.background(r, g, b, a);")
  static native void background(JSObject p5, int r, int g, int b, int a);


  @JSBody(params = { "p5", "r", "g", "b", "a" }, script =
    "if (p5 && typeof p5.fill === 'function') p5.fill(r, g, b, a);")
  static native void fill(JSObject p5, int r, int g, int b, int a);


  @JSBody(params = { "p5" }, script =
    "if (p5 && typeof p5.noFill === 'function') p5.noFill();")
  static native void noFill(JSObject p5);


  @JSBody(params = { "p5", "r", "g", "b", "a" }, script =
    "if (p5 && typeof p5.stroke === 'function') p5.stroke(r, g, b, a);")
  static native void stroke(JSObject p5, int r, int g, int b, int a);


  @JSBody(params = { "p5" }, script =
    "if (p5 && typeof p5.noStroke === 'function') p5.noStroke();")
  static native void noStroke(JSObject p5);


  @JSBody(params = { "p5", "weight" }, script =
    "if (p5 && typeof p5.strokeWeight === 'function') p5.strokeWeight(weight);")
  static native void strokeWeight(JSObject p5, float weight);


  @JSBody(params = { "p5", "cap" }, script =
    "if (!p5 || typeof p5.strokeCap !== 'function') return;" +
    "var mode = cap === 2 ? (p5.ROUND || 'round') : cap === 4 ? (p5.PROJECT || 'square') : (p5.SQUARE || 'butt');" +
    "p5.strokeCap(mode);")
  static native void strokeCap(JSObject p5, int cap);


  @JSBody(params = { "p5", "join" }, script =
    "if (!p5 || typeof p5.strokeJoin !== 'function') return;" +
    "var mode = join === 2 ? (p5.ROUND || 'round') : join === 32 ? (p5.BEVEL || 'bevel') : (p5.MITER || 'miter');" +
    "p5.strokeJoin(mode);")
  static native void strokeJoin(JSObject p5, int join);


  @JSBody(params = { "p5", "r", "g", "b", "a" }, script =
    "if (p5 && typeof p5.tint === 'function') p5.tint(r, g, b, a);")
  static native void tint(JSObject p5, int r, int g, int b, int a);


  @JSBody(params = { "p5" }, script =
    "if (p5 && typeof p5.noTint === 'function') p5.noTint();")
  static native void noTint(JSObject p5);


  @JSBody(params = { "p5" }, script =
    "if (p5 && typeof p5.push === 'function') p5.push();")
  static native void push(JSObject p5);


  @JSBody(params = { "p5" }, script =
    "if (p5 && typeof p5.pop === 'function') p5.pop();")
  static native void pop(JSObject p5);


  @JSBody(params = { "p5" }, script =
    "if (p5 && typeof p5.resetMatrix === 'function') p5.resetMatrix();")
  static native void resetMatrix(JSObject p5);


  @JSBody(params = { "p5", "x", "y" }, script =
    "if (p5 && typeof p5.translate === 'function') p5.translate(x, y);")
  static native void translate(JSObject p5, float x, float y);


  @JSBody(params = { "p5", "angle" }, script =
    "if (p5 && typeof p5.rotate === 'function') p5.rotate(angle);")
  static native void rotate(JSObject p5, float angle);


  @JSBody(params = { "p5", "x", "y" }, script =
    "if (p5 && typeof p5.scale === 'function') p5.scale(x, y);")
  static native void scale(JSObject p5, float x, float y);


  @JSBody(params = { "p5", "a", "b", "c", "d", "e", "f" }, script =
    "if (p5 && typeof p5.applyMatrix === 'function') p5.applyMatrix(a, b, c, d, e, f);")
  static native void applyMatrix(JSObject p5, float a, float b, float c,
                                 float d, float e, float f);


  @JSBody(params = { "p5", "x", "y" }, script =
    "if (p5 && typeof p5.point === 'function') p5.point(x, y);")
  static native void point(JSObject p5, float x, float y);


  @JSBody(params = { "p5", "x1", "y1", "x2", "y2" }, script =
    "if (p5 && typeof p5.line === 'function') p5.line(x1, y1, x2, y2);")
  static native void line(JSObject p5, float x1, float y1, float x2, float y2);


  @JSBody(params = { "p5", "x1", "y1", "x2", "y2", "x3", "y3" }, script =
    "if (p5 && typeof p5.triangle === 'function') p5.triangle(x1, y1, x2, y2, x3, y3);")
  static native void triangle(JSObject p5, float x1, float y1, float x2, float y2,
                              float x3, float y3);


  @JSBody(params = { "p5", "x1", "y1", "x2", "y2", "x3", "y3", "x4", "y4" }, script =
    "if (p5 && typeof p5.quad === 'function') p5.quad(x1, y1, x2, y2, x3, y3, x4, y4);")
  static native void quad(JSObject p5, float x1, float y1, float x2, float y2,
                          float x3, float y3, float x4, float y4);


  @JSBody(params = { "p5", "x", "y", "width", "height" }, script =
    "if (p5 && typeof p5.rect === 'function') p5.rect(x, y, width, height);")
  static native void rect(JSObject p5, float x, float y, float width, float height);


  @JSBody(params = { "p5", "x", "y", "width", "height", "tl", "tr", "br", "bl" }, script =
    "if (p5 && typeof p5.rect === 'function') p5.rect(x, y, width, height, tl, tr, br, bl);")
  static native void roundedRect(JSObject p5, float x, float y, float width, float height,
                                 float tl, float tr, float br, float bl);


  @JSBody(params = { "p5", "x", "y", "width", "height" }, script =
    "if (p5 && typeof p5.ellipse === 'function') p5.ellipse(x, y, width, height);")
  static native void ellipse(JSObject p5, float x, float y, float width, float height);


  @JSBody(params = { "p5", "x", "y", "width", "height", "start", "stop", "mode" }, script =
    "if (!p5 || typeof p5.arc !== 'function') return;" +
    "var arcMode = mode === 3 ? (p5.PIE || 'pie') : mode === 2 ? (p5.CHORD || 'chord') : (p5.OPEN || 'open');" +
    "p5.arc(x, y, width, height, start, stop, arcMode);")
  static native void arc(JSObject p5, float x, float y, float width, float height,
                         float start, float stop, int mode);


  @JSBody(params = { "p5", "kind" }, script =
    "if (!p5 || typeof p5.beginShape !== 'function') return;" +
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
    "if (p5 && typeof p5.vertex === 'function') p5.vertex(x, y);")
  static native void vertex(JSObject p5, float x, float y);


  @JSBody(params = { "p5", "close" }, script =
    "if (!p5 || typeof p5.endShape !== 'function') return;" +
    "if (close) p5.endShape(p5.CLOSE || 'close'); else p5.endShape();")
  static native void endShape(JSObject p5, boolean close);


  @JSBody(params = { "p5", "image", "x", "y", "width", "height", "sx", "sy", "sw", "sh" }, script =
    "if (!p5 || !image || typeof p5.image !== 'function') return;" +
    "p5.image(image, x, y, width, height, sx, sy, sw, sh);")
  static native void image(JSObject p5, JSObject image, float x, float y,
                           float width, float height, int sx, int sy, int sw, int sh);


  @JSBody(params = { "p5", "text", "x", "y" }, script =
    "if (p5 && typeof p5.text === 'function') p5.text(text, x, y);")
  static native void text(JSObject p5, String text, float x, float y);


  @JSBody(params = { "p5", "size" }, script =
    "if (p5 && typeof p5.textSize === 'function') p5.textSize(size);")
  static native void textSize(JSObject p5, float size);


  @JSBody(params = { "p5", "font" }, script =
    "if (p5 && font && typeof p5.textFont === 'function') p5.textFont(font);")
  static native void textFont(JSObject p5, String font);


  @JSBody(params = { "p5", "alignX", "alignY" }, script =
    "if (!p5 || typeof p5.textAlign !== 'function') return;" +
    "var x = alignX === 3 ? (p5.CENTER || 'center') : alignX === 39 ? (p5.RIGHT || 'right') : (p5.LEFT || 'left');" +
    "var y = alignY === 101 ? (p5.TOP || 'top') : alignY === 102 ? (p5.BOTTOM || 'bottom') :" +
    "        alignY === 3 ? (p5.CENTER || 'center') : (p5.BASELINE || 'alphabetic');" +
    "p5.textAlign(x, y);")
  static native void textAlign(JSObject p5, int alignX, int alignY);


  @JSBody(params = { "p5", "text" }, script =
    "if (!p5 || typeof p5.textWidth !== 'function') return text ? text.length * 10 : 0;" +
    "return p5.textWidth(text);")
  static native float textWidth(JSObject p5, String text);


  @JSBody(params = { "p5", "cursor" }, script =
    "if (!p5 || typeof p5.cursor !== 'function') return;" +
    "p5.cursor(cursor);")
  static native void cursor(JSObject p5, String cursor);


  @JSBody(params = { "p5" }, script =
    "if (p5 && typeof p5.noCursor === 'function') p5.noCursor();")
  static native void noCursor(JSObject p5);
}
