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

public class PGraphicsCanvas2D extends PGraphics {
  private PMatrix2D matrix = new PMatrix2D();
  private PMatrix2D[] matrixStack = new PMatrix2D[MATRIX_STACK_DEPTH];
  private int matrixStackDepth;
  private boolean openContour;
  private float[] curveCoordX;
  private float[] curveCoordY;
  private float[] curveDrawX;
  private float[] curveDrawY;


  private JSObject host() {
    return Canvas2DPlatformRuntime.current().host();
  }


  JSObject canvas() {
    return Canvas2DBridge.canvas(host(), null);
  }


  @Override
  public PSurface createSurface() {
    return surface = new Canvas2DSurface(this);
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

    Canvas2DBridge.loadPixels(host());
    int pixelCount = Math.min(pixels.length, Canvas2DBridge.pixelLength(host()) / 4);
    for (int i = 0; i < pixelCount; i++) {
      int base = i * 4;
      int r = Canvas2DBridge.pixel(host(), base);
      int g = Canvas2DBridge.pixel(host(), base + 1);
      int b = Canvas2DBridge.pixel(host(), base + 2);
      int a = Canvas2DBridge.pixel(host(), base + 3);
      pixels[i] = (a << 24) | (r << 16) | (g << 8) | b;
    }
    setLoaded();
  }


  @Override
  public void updatePixels(int x, int y, int w, int h) {
    if (pixels == null) {
      return;
    }

    Canvas2DBridge.loadPixels(host());
    int pixelCount = Math.min(pixels.length, Canvas2DBridge.pixelLength(host()) / 4);
    for (int i = 0; i < pixelCount; i++) {
      int argb = pixels[i];
      int base = i * 4;
      Canvas2DBridge.setPixel(host(), base, (argb >> 16) & 0xff);
      Canvas2DBridge.setPixel(host(), base + 1, (argb >> 8) & 0xff);
      Canvas2DBridge.setPixel(host(), base + 2, argb & 0xff);
      Canvas2DBridge.setPixel(host(), base + 3, (argb >>> 24) & 0xff);
    }
    Canvas2DBridge.updatePixels(host());
    super.updatePixels(x, y, w, h);
  }


  @Override
  public void point(float x, float y) {
    Canvas2DBridge.point(host(), x, y);
  }


  @Override
  public void line(float x1, float y1, float x2, float y2) {
    Canvas2DBridge.line(host(), x1, y1, x2, y2);
  }


  @Override
  public void triangle(float x1, float y1, float x2, float y2, float x3, float y3) {
    Canvas2DBridge.triangle(host(), x1, y1, x2, y2, x3, y3);
  }


  @Override
  public void quad(float x1, float y1, float x2, float y2,
                   float x3, float y3, float x4, float y4) {
    Canvas2DBridge.quad(host(), x1, y1, x2, y2, x3, y3, x4, y4);
  }


  @Override
  protected void rectImpl(float x1, float y1, float x2, float y2) {
    Canvas2DBridge.rect(host(), x1, y1, x2 - x1, y2 - y1);
  }


  @Override
  protected void rectImpl(float x1, float y1, float x2, float y2,
                          float tl, float tr, float br, float bl) {
    Canvas2DBridge.roundedRect(host(), x1, y1, x2 - x1, y2 - y1, tl, tr, br, bl);
  }


  @Override
  protected void ellipseImpl(float x, float y, float w, float h) {
    Canvas2DBridge.ellipse(host(), x + w / 2, y + h / 2, w, h);
  }


  @Override
  protected void arcImpl(float x, float y, float w, float h,
                         float start, float stop, int mode) {
    Canvas2DBridge.arc(host(), x + w / 2, y + h / 2, w, h, start, stop, mode);
  }


  @Override
  public void beginShape(int kind) {
    super.beginShape(kind);
    vertexCount = 0;
    Canvas2DBridge.beginShape(host(), kind);
  }


  @Override
  public void vertex(float x, float y) {
    super.vertex(x, y);
    Canvas2DBridge.vertex(host(), x, y);
  }


  @Override
  public void vertex(float x, float y, float z) {
    vertex(x, y);
  }


  @Override
  public void endShape(int mode) {
    Canvas2DBridge.endShape(host(), mode == CLOSE);
    shape = 0;
    vertexCount = 0;
    openContour = false;
  }


  @Override
  public void beginContour() {
    if (openContour) {
      PGraphics.showWarning("Already called beginContour()");
      return;
    }
    Canvas2DBridge.beginContour(host());
    openContour = true;
  }


  @Override
  public void endContour() {
    if (!openContour) {
      PGraphics.showWarning("Need to call beginContour() first");
      return;
    }
    Canvas2DBridge.endContour(host());
    openContour = false;
  }


  @Override
  protected void clipImpl(float x1, float y1, float x2, float y2) {
    Canvas2DBridge.clipRect(host(), x1, y1, x2 - x1, y2 - y1);
  }


  @Override
  public void noClip() {
    Canvas2DBridge.noClip(host());
  }


  @Override
  protected void blendModeImpl() {
    Canvas2DBridge.blendMode(host(), blendMode);
  }


  @Override
  public void bezierVertex(float x1, float y1,
                           float x2, float y2,
                           float x3, float y3) {
    bezierVertexCheck();
    Canvas2DBridge.bezierVertex(host(), x1, y1, x2, y2, x3, y3);
    vertexCheck();
    vertices[vertexCount][X] = x3;
    vertices[vertexCount][Y] = y3;
    vertices[vertexCount][Z] = 0;
    vertexCount++;
  }


  @Override
  public void bezierVertex(float x2, float y2, float z2,
                           float x3, float y3, float z3,
                           float x4, float y4, float z4) {
    showDepthWarningXYZ("bezierVertex");
  }


  @Override
  public void quadraticVertex(float ctrlX, float ctrlY,
                              float endX, float endY) {
    bezierVertexCheck();
    float[] prev = vertices[vertexCount - 1];
    float x1 = prev[X];
    float y1 = prev[Y];

    bezierVertex(x1 + ((ctrlX - x1) * 2 / 3.0f), y1 + ((ctrlY - y1) * 2 / 3.0f),
                 endX + ((ctrlX - endX) * 2 / 3.0f), endY + ((ctrlY - endY) * 2 / 3.0f),
                 endX, endY);
  }


  @Override
  public void quadraticVertex(float x2, float y2, float z2,
                              float x4, float y4, float z4) {
    showDepthWarningXYZ("quadVertex");
  }


  @Override
  protected void curveVertexCheck() {
    super.curveVertexCheck();

    if (curveCoordX == null) {
      curveCoordX = new float[4];
      curveCoordY = new float[4];
      curveDrawX = new float[4];
      curveDrawY = new float[4];
    }
  }


  @Override
  protected void curveVertexSegment(float x1, float y1,
                                    float x2, float y2,
                                    float x3, float y3,
                                    float x4, float y4) {
    curveCoordX[0] = x1;
    curveCoordY[0] = y1;
    curveCoordX[1] = x2;
    curveCoordY[1] = y2;
    curveCoordX[2] = x3;
    curveCoordY[2] = y3;
    curveCoordX[3] = x4;
    curveCoordY[3] = y4;

    curveToBezierMatrix.mult(curveCoordX, curveDrawX);
    curveToBezierMatrix.mult(curveCoordY, curveDrawY);
    Canvas2DBridge.curveVertexSegment(host(), curveDrawX[0], curveDrawY[0],
                                      curveDrawX[1], curveDrawY[1],
                                      curveDrawX[2], curveDrawY[2],
                                      curveDrawX[3], curveDrawY[3]);
  }


  @Override
  public void curveVertex(float x, float y, float z) {
    showDepthWarningXYZ("curveVertex");
  }


  @Override
  protected void imageImpl(PImage image,
                           float x1, float y1, float x2, float y2,
                           int u1, int v1, int u2, int v2) {
    Object nativeImage = Canvas2DPlatformRuntime.current().getNativeImage(image);
    if (nativeImage instanceof JSObject) {
      Canvas2DBridge.image(host(), (JSObject) nativeImage, x1, y1, x2 - x1, y2 - y1,
                     u1, v1, u2 - u1, v2 - v1);
    }
  }


  @Override
  protected void backgroundImpl(PImage image) {
    imageImpl(image, 0, 0, width, height, 0, 0, image.width, image.height);
  }


  @Override
  protected void backgroundImpl() {
    Canvas2DBridge.background(host(), backgroundRi, backgroundGi, backgroundBi, backgroundAi);
  }


  @Override
  public void noStroke() {
    super.noStroke();
    Canvas2DBridge.noStroke(host());
  }


  @Override
  protected void strokeFromCalc() {
    super.strokeFromCalc();
    Canvas2DBridge.stroke(host(), strokeRi, strokeGi, strokeBi, strokeAi);
  }


  @Override
  public void strokeWeight(float weight) {
    super.strokeWeight(weight);
    Canvas2DBridge.strokeWeight(host(), weight);
  }


  @Override
  public void strokeCap(int cap) {
    super.strokeCap(cap);
    Canvas2DBridge.strokeCap(host(), cap);
  }


  @Override
  public void strokeJoin(int join) {
    super.strokeJoin(join);
    Canvas2DBridge.strokeJoin(host(), join);
  }


  @Override
  public void noFill() {
    super.noFill();
    Canvas2DBridge.noFill(host());
  }


  @Override
  protected void fillFromCalc() {
    super.fillFromCalc();
    Canvas2DBridge.fill(host(), fillRi, fillGi, fillBi, fillAi);
  }


  @Override
  public void noTint() {
    super.noTint();
    Canvas2DBridge.noTint(host());
  }


  @Override
  protected void tintFromCalc() {
    super.tintFromCalc();
    Canvas2DBridge.tint(host(), tintRi, tintGi, tintBi, tintAi);
  }


  @Override
  public void pushMatrix() {
    if (matrixStackDepth == matrixStack.length) {
      throw new RuntimeException(ERROR_PUSHMATRIX_OVERFLOW);
    }
    matrixStack[matrixStackDepth++] = matrix.get();
    Canvas2DBridge.push(host());
  }


  @Override
  public void popMatrix() {
    if (matrixStackDepth == 0) {
      throw new RuntimeException(ERROR_PUSHMATRIX_UNDERFLOW);
    }
    matrix.set(matrixStack[--matrixStackDepth]);
    matrixStack[matrixStackDepth] = null;
    Canvas2DBridge.pop(host());
  }


  @Override
  public void resetMatrix() {
    matrix.reset();
    Canvas2DBridge.resetMatrix(host());
  }


  @Override
  public void translate(float x, float y) {
    matrix.translate(x, y);
    Canvas2DBridge.translate(host(), x, y);
  }


  @Override
  public void translate(float x, float y, float z) {
    translate(x, y);
  }


  @Override
  public void rotate(float angle) {
    matrix.rotate(angle);
    Canvas2DBridge.rotate(host(), angle);
  }


  @Override
  public void scale(float scale) {
    matrix.scale(scale, scale);
    Canvas2DBridge.scale(host(), scale, scale);
  }


  @Override
  public void scale(float x, float y) {
    matrix.scale(x, y);
    Canvas2DBridge.scale(host(), x, y);
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
    Canvas2DBridge.applyMatrix(host(), n00, n10, n01, n11, n02, n12);
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
    Canvas2DBridge.resetMatrix(host());
    Canvas2DBridge.applyMatrix(host(), matrix.m00, matrix.m10, matrix.m01,
                         matrix.m11, matrix.m02, matrix.m12);
  }


  @Override
  public void setMatrix(PMatrix3D source) {
    matrix.set(source.m00, source.m01, source.m03,
               source.m10, source.m11, source.m13);
    Canvas2DBridge.resetMatrix(host());
    Canvas2DBridge.applyMatrix(host(), matrix.m00, matrix.m10, matrix.m01,
                         matrix.m11, matrix.m02, matrix.m12);
  }


  @Override
  public float screenX(float x, float y) {
    return matrix.m00 * x + matrix.m01 * y + matrix.m02;
  }


  @Override
  public float screenY(float x, float y) {
    return matrix.m10 * x + matrix.m11 * y + matrix.m12;
  }


  @Override
  public float screenX(float x, float y, float z) {
    showDepthWarningXYZ("screenX");
    return 0;
  }


  @Override
  public float screenY(float x, float y, float z) {
    showDepthWarningXYZ("screenY");
    return 0;
  }


  @Override
  public float screenZ(float x, float y, float z) {
    showDepthWarningXYZ("screenZ");
    return 0;
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
    Canvas2DBridge.textSize(host(), size);
  }


  @Override
  protected void textFontImpl(PFont font, float size) {
    textFont = font;
    handleTextSize(size);
    Canvas2DBridge.textFont(host(), font.getName());
    Canvas2DBridge.textSize(host(), size);
  }


  @Override
  public void textAlign(int alignX, int alignY) {
    super.textAlign(alignX, alignY);
    Canvas2DBridge.textAlign(host(), alignX, alignY);
  }


  @Override
  public float textAscent() {
    return Canvas2DBridge.textAscent(host(), textSize * 0.8f);
  }


  @Override
  public float textDescent() {
    return Canvas2DBridge.textDescent(host(), textSize * 0.2f);
  }


  @Override
  public float textWidth(char c) {
    return Canvas2DBridge.textWidth(host(), String.valueOf(c));
  }


  @Override
  public float textWidth(String text) {
    return Canvas2DBridge.textWidth(host(), text);
  }


  @Override
  public float textWidth(char[] chars, int start, int length) {
    return Canvas2DBridge.textWidth(host(), new String(chars, start, length));
  }


  @Override
  protected float textWidthImpl(char[] buffer, int start, int stop) {
    return Canvas2DBridge.textWidth(host(), new String(buffer, start, stop - start));
  }


  @Override
  protected void textLineImpl(char[] buffer, int start, int stop, float x, float y) {
    Canvas2DBridge.text(host(), new String(buffer, start, stop - start), x, y);
  }
}
