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

import org.teavm.jso.JSObject;

import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PMatrix;
import processing.core.PMatrix2D;
import processing.core.PMatrix3D;
import processing.core.PSurface;

public class PGraphicsP5 extends PGraphics {
  private PMatrix2D matrix = new PMatrix2D();
  private PMatrix2D[] matrixStack = new PMatrix2D[MATRIX_STACK_DEPTH];
  private int matrixStackDepth;


  private JSObject p5() {
    return P5PlatformRuntime.current().p5();
  }


  JSObject canvas() {
    return P5Bridge.canvas(p5(), null);
  }


  @Override
  public PSurface createSurface() {
    return surface = new P5Surface(this);
  }


  @Override
  public void beginDraw() {
    checkSettings();
    matrixStackDepth = 0;
    resetMatrix();
    vertexCount = 0;
  }


  @Override
  public void endDraw() {
  }


  @Override
  public void loadPixels() {
    if (pixels == null || pixels.length != pixelWidth * pixelHeight) {
      pixels = new int[pixelWidth * pixelHeight];
    }

    P5Bridge.loadPixels(p5());
    int pixelCount = Math.min(pixels.length, P5Bridge.pixelLength(p5()) / 4);
    for (int i = 0; i < pixelCount; i++) {
      int base = i * 4;
      int r = P5Bridge.pixel(p5(), base);
      int g = P5Bridge.pixel(p5(), base + 1);
      int b = P5Bridge.pixel(p5(), base + 2);
      int a = P5Bridge.pixel(p5(), base + 3);
      pixels[i] = (a << 24) | (r << 16) | (g << 8) | b;
    }
    setLoaded();
  }


  @Override
  public void updatePixels(int x, int y, int w, int h) {
    if (pixels == null) {
      return;
    }

    P5Bridge.loadPixels(p5());
    int pixelCount = Math.min(pixels.length, P5Bridge.pixelLength(p5()) / 4);
    for (int i = 0; i < pixelCount; i++) {
      int argb = pixels[i];
      int base = i * 4;
      P5Bridge.setPixel(p5(), base, (argb >> 16) & 0xff);
      P5Bridge.setPixel(p5(), base + 1, (argb >> 8) & 0xff);
      P5Bridge.setPixel(p5(), base + 2, argb & 0xff);
      P5Bridge.setPixel(p5(), base + 3, (argb >>> 24) & 0xff);
    }
    P5Bridge.updatePixels(p5());
    super.updatePixels(x, y, w, h);
  }


  @Override
  public void point(float x, float y) {
    P5Bridge.point(p5(), x, y);
  }


  @Override
  public void line(float x1, float y1, float x2, float y2) {
    P5Bridge.line(p5(), x1, y1, x2, y2);
  }


  @Override
  public void triangle(float x1, float y1, float x2, float y2, float x3, float y3) {
    P5Bridge.triangle(p5(), x1, y1, x2, y2, x3, y3);
  }


  @Override
  public void quad(float x1, float y1, float x2, float y2,
                   float x3, float y3, float x4, float y4) {
    P5Bridge.quad(p5(), x1, y1, x2, y2, x3, y3, x4, y4);
  }


  @Override
  protected void rectImpl(float x1, float y1, float x2, float y2) {
    P5Bridge.rect(p5(), x1, y1, x2 - x1, y2 - y1);
  }


  @Override
  protected void rectImpl(float x1, float y1, float x2, float y2,
                          float tl, float tr, float br, float bl) {
    P5Bridge.roundedRect(p5(), x1, y1, x2 - x1, y2 - y1, tl, tr, br, bl);
  }


  @Override
  protected void ellipseImpl(float x, float y, float w, float h) {
    P5Bridge.ellipse(p5(), x + w / 2, y + h / 2, w, h);
  }


  @Override
  protected void arcImpl(float x, float y, float w, float h,
                         float start, float stop, int mode) {
    P5Bridge.arc(p5(), x + w / 2, y + h / 2, w, h, start, stop, mode);
  }


  @Override
  public void beginShape(int kind) {
    super.beginShape(kind);
    vertexCount = 0;
    P5Bridge.beginShape(p5(), kind);
  }


  @Override
  public void vertex(float x, float y) {
    super.vertex(x, y);
    P5Bridge.vertex(p5(), x, y);
  }


  @Override
  public void vertex(float x, float y, float z) {
    vertex(x, y);
  }


  @Override
  public void endShape(int mode) {
    P5Bridge.endShape(p5(), mode == CLOSE);
    shape = 0;
    vertexCount = 0;
  }


  @Override
  protected void imageImpl(PImage image,
                           float x1, float y1, float x2, float y2,
                           int u1, int v1, int u2, int v2) {
    Object nativeImage = P5PlatformRuntime.current().getNativeImage(image);
    if (nativeImage instanceof JSObject) {
      P5Bridge.image(p5(), (JSObject) nativeImage, x1, y1, x2 - x1, y2 - y1,
                     u1, v1, u2 - u1, v2 - v1);
    }
  }


  @Override
  protected void backgroundImpl(PImage image) {
    imageImpl(image, 0, 0, width, height, 0, 0, image.width, image.height);
  }


  @Override
  protected void backgroundImpl() {
    P5Bridge.background(p5(), backgroundRi, backgroundGi, backgroundBi, backgroundAi);
  }


  @Override
  public void noStroke() {
    super.noStroke();
    P5Bridge.noStroke(p5());
  }


  @Override
  protected void strokeFromCalc() {
    super.strokeFromCalc();
    P5Bridge.stroke(p5(), strokeRi, strokeGi, strokeBi, strokeAi);
  }


  @Override
  public void strokeWeight(float weight) {
    super.strokeWeight(weight);
    P5Bridge.strokeWeight(p5(), weight);
  }


  @Override
  public void strokeCap(int cap) {
    super.strokeCap(cap);
    P5Bridge.strokeCap(p5(), cap);
  }


  @Override
  public void strokeJoin(int join) {
    super.strokeJoin(join);
    P5Bridge.strokeJoin(p5(), join);
  }


  @Override
  public void noFill() {
    super.noFill();
    P5Bridge.noFill(p5());
  }


  @Override
  protected void fillFromCalc() {
    super.fillFromCalc();
    P5Bridge.fill(p5(), fillRi, fillGi, fillBi, fillAi);
  }


  @Override
  public void noTint() {
    super.noTint();
    P5Bridge.noTint(p5());
  }


  @Override
  protected void tintFromCalc() {
    super.tintFromCalc();
    P5Bridge.tint(p5(), tintRi, tintGi, tintBi, tintAi);
  }


  @Override
  public void pushMatrix() {
    if (matrixStackDepth == matrixStack.length) {
      throw new RuntimeException(ERROR_PUSHMATRIX_OVERFLOW);
    }
    matrixStack[matrixStackDepth++] = matrix.get();
    P5Bridge.push(p5());
  }


  @Override
  public void popMatrix() {
    if (matrixStackDepth == 0) {
      throw new RuntimeException(ERROR_PUSHMATRIX_UNDERFLOW);
    }
    matrix.set(matrixStack[--matrixStackDepth]);
    matrixStack[matrixStackDepth] = null;
    P5Bridge.pop(p5());
  }


  @Override
  public void resetMatrix() {
    matrix.reset();
    P5Bridge.resetMatrix(p5());
  }


  @Override
  public void translate(float x, float y) {
    matrix.translate(x, y);
    P5Bridge.translate(p5(), x, y);
  }


  @Override
  public void translate(float x, float y, float z) {
    translate(x, y);
  }


  @Override
  public void rotate(float angle) {
    matrix.rotate(angle);
    P5Bridge.rotate(p5(), angle);
  }


  @Override
  public void scale(float scale) {
    P5Bridge.scale(p5(), scale, scale);
  }


  @Override
  public void scale(float x, float y) {
    matrix.scale(x, y);
    P5Bridge.scale(p5(), x, y);
  }


  @Override
  public void scale(float x, float y, float z) {
    scale(x, y);
  }


  @Override
  public void applyMatrix(PMatrix source) {
    if (source instanceof PMatrix2D) {
      applyMatrix((PMatrix2D) source);
    } else if (source instanceof PMatrix3D) {
      applyMatrix((PMatrix3D) source);
    }
  }


  @Override
  public void applyMatrix(PMatrix2D source) {
    applyMatrix(source.m00, source.m01, source.m02,
                source.m10, source.m11, source.m12);
  }


  @Override
  public void applyMatrix(float n00, float n01, float n02,
                          float n10, float n11, float n12) {
    matrix.apply(n00, n01, n02,
                 n10, n11, n12);
    P5Bridge.applyMatrix(p5(), n00, n10, n01, n11, n02, n12);
  }


  @Override
  public void applyMatrix(PMatrix3D source) {
    applyMatrix(source.m00, source.m01, source.m03,
                source.m10, source.m11, source.m13);
  }


  @Override
  public void applyMatrix(float n00, float n01, float n02, float n03,
                          float n10, float n11, float n12, float n13,
                          float n20, float n21, float n22, float n23,
                          float n30, float n31, float n32, float n33) {
    applyMatrix(n00, n01, n03,
                n10, n11, n13);
  }


  @Override
  public PMatrix getMatrix() {
    return matrix.get();
  }


  @Override
  public PMatrix2D getMatrix(PMatrix2D target) {
    if (target == null) {
      target = new PMatrix2D();
    }
    target.set(matrix);
    return target;
  }


  @Override
  public PMatrix3D getMatrix(PMatrix3D target) {
    if (target == null) {
      target = new PMatrix3D();
    }
    target.set(matrix);
    return target;
  }


  @Override
  public void setMatrix(PMatrix source) {
    if (source instanceof PMatrix2D) {
      setMatrix((PMatrix2D) source);
    } else if (source instanceof PMatrix3D) {
      setMatrix((PMatrix3D) source);
    }
  }


  @Override
  public void setMatrix(PMatrix2D source) {
    matrix.set(source);
    P5Bridge.resetMatrix(p5());
    P5Bridge.applyMatrix(p5(), matrix.m00, matrix.m10, matrix.m01,
                         matrix.m11, matrix.m02, matrix.m12);
  }


  @Override
  public void setMatrix(PMatrix3D source) {
    matrix.set(source.m00, source.m01, source.m03,
               source.m10, source.m11, source.m13);
    P5Bridge.resetMatrix(p5());
    P5Bridge.applyMatrix(p5(), matrix.m00, matrix.m10, matrix.m01,
                         matrix.m11, matrix.m02, matrix.m12);
  }


  @Override
  public void textSize(float size) {
    if (size <= 0) {
      System.err.println("textSize(" + size + ") ignored: the text size must be larger than zero");
      return;
    }
    textSizeImpl(size);
  }


  @Override
  protected void textSizeImpl(float size) {
    handleTextSize(size);
    P5Bridge.textSize(p5(), size);
  }


  @Override
  protected void textFontImpl(PFont font, float size) {
    textFont = font;
    handleTextSize(size);
    P5Bridge.textFont(p5(), font.getName());
    P5Bridge.textSize(p5(), size);
  }


  @Override
  public void textAlign(int alignX, int alignY) {
    super.textAlign(alignX, alignY);
    P5Bridge.textAlign(p5(), alignX, alignY);
  }


  @Override
  public float textAscent() {
    return textSize * 0.8f;
  }


  @Override
  public float textDescent() {
    return textSize * 0.2f;
  }


  @Override
  public float textWidth(char c) {
    return P5Bridge.textWidth(p5(), String.valueOf(c));
  }


  @Override
  public float textWidth(String text) {
    return P5Bridge.textWidth(p5(), text);
  }


  @Override
  public float textWidth(char[] chars, int start, int length) {
    return P5Bridge.textWidth(p5(), new String(chars, start, length));
  }


  @Override
  protected float textWidthImpl(char[] buffer, int start, int stop) {
    return P5Bridge.textWidth(p5(), new String(buffer, start, stop - start));
  }


  @Override
  protected void textLineImpl(char[] buffer, int start, int stop, float x, float y) {
    P5Bridge.text(p5(), new String(buffer, start, stop - start), x, y);
  }
}
