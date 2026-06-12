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

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PMatrix;
import processing.core.PMatrix2D;
import processing.core.PMatrix3D;
import processing.core.PSurface;

public class PGraphicsP5 extends PGraphics {
  private final boolean webgl;
  private PMatrix3D matrix = new PMatrix3D();
  private PMatrix3D lightNormalMatrix = new PMatrix3D();
  private PMatrix3D[] matrixStack = new PMatrix3D[MATRIX_STACK_DEPTH];
  private int matrixStackDepth;
  private boolean webglDefaultsPending;
  private float lightVectorX;
  private float lightVectorY;
  private float lightVectorZ;


  public PGraphicsP5() {
    this(false);
  }


  public PGraphicsP5(boolean webgl) {
    this.webgl = webgl;
  }


  boolean isWebGL() {
    return webgl;
  }


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
  public void setSize(int width, int height) {
    super.setSize(width, height);
    if (webgl) {
      webglDefaultsPending = true;
    }
  }


  @Override
  public void beginDraw() {
    checkSettings();
    matrixStackDepth = 0;
    if (webgl && webglDefaultsPending) {
      perspective();
      camera();
      webglDefaultsPending = false;
    }
    resetMatrix();
    if (webgl) {
      P5Bridge.noLights(p5());
      P5Bridge.defaultLightState(p5());
    }
    vertexCount = 0;
  }


  @Override
  protected void defaultSettings() {
    super.defaultSettings();
    if (webgl) {
      ambient(255);
      specular(125);
      emissive(0);
      shininess(1);
      setAmbient = false;
      P5Bridge.ambientMaterial(p5(), fillRi, fillGi, fillBi, fillAi);
    }
  }


  @Override
  public void endDraw() {
  }


  @Override
  public void loadPixels() {
    if (pixels == null || pixels.length != pixelWidth * pixelHeight) {
      pixels = new int[pixelWidth * pixelHeight];
    }

    P5Bridge.loadPixelsAndCopyHostPixelsToIntArray(p5(), pixels, pixels.length);
    setLoaded();
  }


  @Override
  public void updatePixels(int x, int y, int w, int h) {
    if (pixels == null) {
      return;
    }

    P5Bridge.loadAndWriteHostPixels(p5(), pixels, pixels.length);
    super.updatePixels(x, y, w, h);
  }


  @Override
  public void point(float x, float y) {
    P5Bridge.point(p5(), x, y);
  }


  @Override
  public void point(float x, float y, float z) {
    if (webgl) {
      P5Bridge.point3(p5(), x, y, z);
    } else {
      point(x, y);
    }
  }


  @Override
  public void line(float x1, float y1, float x2, float y2) {
    P5Bridge.line(p5(), x1, y1, x2, y2);
  }


  @Override
  public void line(float x1, float y1, float z1,
                   float x2, float y2, float z2) {
    if (webgl) {
      P5Bridge.line3(p5(), x1, y1, z1, x2, y2, z2);
    } else {
      line(x1, y1, x2, y2);
    }
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
    super.vertex(x, y, z);
    if (webgl) {
      P5Bridge.vertex3(p5(), x, y, z);
    } else {
      P5Bridge.vertex(p5(), x, y);
    }
  }


  @Override
  public void normal(float nx, float ny, float nz) {
    super.normal(nx, ny, nz);
    if (webgl) {
      P5Bridge.normal(p5(), nx, ny, nz);
    }
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
    if (image instanceof P5Image) {
      ((P5Image) image).syncSize();
      if (u1 == 0 && v1 == 0 && u2 == 0 && v2 == 0) {
        u2 = image.width;
        v2 = image.height;
      }
    }
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
    if (webgl && !setAmbient) {
      ambientFromCalc();
      setAmbient = false;
    }
  }


  @Override
  protected void ambientFromCalc() {
    super.ambientFromCalc();
    if (webgl) {
      P5Bridge.ambientMaterial(p5(), calcRi, calcGi, calcBi, calcAi);
    }
  }


  @Override
  protected void specularFromCalc() {
    super.specularFromCalc();
    if (webgl) {
      P5Bridge.specularMaterial(p5(), calcRi, calcGi, calcBi, calcAi);
    }
  }


  @Override
  protected void emissiveFromCalc() {
    super.emissiveFromCalc();
    if (webgl) {
      P5Bridge.emissiveMaterial(p5(), calcRi, calcGi, calcBi, calcAi);
    }
  }


  @Override
  public void shininess(float shine) {
    super.shininess(shine);
    if (webgl) {
      P5Bridge.shininess(p5(), shine);
    }
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
    applyWebglOrigin();
  }


  private void applyWebglOrigin() {
    if (webgl) {
      P5Bridge.translate3(p5(), -width / 2.0f, -height / 2.0f, 0);
    }
  }


  @Override
  public void translate(float x, float y) {
    matrix.translate(x, y);
    if (webgl) {
      P5Bridge.translate3(p5(), x, y, 0);
    } else {
      P5Bridge.translate(p5(), x, y);
    }
  }


  @Override
  public void translate(float x, float y, float z) {
    matrix.translate(x, y, z);
    if (webgl) {
      P5Bridge.translate3(p5(), x, y, z);
    } else {
      P5Bridge.translate(p5(), x, y);
    }
  }


  @Override
  public void rotate(float angle) {
    matrix.rotateZ(angle);
    if (webgl) {
      P5Bridge.rotateZ(p5(), angle);
    } else {
      P5Bridge.rotate(p5(), angle);
    }
  }


  @Override
  public void rotateX(float angle) {
    if (webgl) {
      matrix.rotateX(angle);
      P5Bridge.rotateX(p5(), angle);
    } else {
      super.rotateX(angle);
    }
  }


  @Override
  public void rotateY(float angle) {
    if (webgl) {
      matrix.rotateY(angle);
      P5Bridge.rotateY(p5(), angle);
    } else {
      super.rotateY(angle);
    }
  }


  @Override
  public void rotateZ(float angle) {
    rotate(angle);
  }


  @Override
  public void scale(float scale) {
    if (webgl) {
      matrix.scale(scale, scale, scale);
      P5Bridge.scale3(p5(), scale, scale, scale);
    } else {
      matrix.scale(scale, scale);
      P5Bridge.scale(p5(), scale, scale);
    }
  }


  @Override
  public void scale(float x, float y) {
    matrix.scale(x, y);
    if (webgl) {
      P5Bridge.scale3(p5(), x, y, 1);
    } else {
      P5Bridge.scale(p5(), x, y);
    }
  }


  @Override
  public void scale(float x, float y, float z) {
    matrix.scale(x, y, z);
    if (webgl) {
      P5Bridge.scale3(p5(), x, y, z);
    } else {
      P5Bridge.scale(p5(), x, y);
    }
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
    if (webgl) {
      matrix.apply(source);
      P5Bridge.applyMatrix3(p5(),
                             source.m00, source.m01, source.m02, source.m03,
                             source.m10, source.m11, source.m12, source.m13,
                             source.m20, source.m21, source.m22, source.m23,
                             source.m30, source.m31, source.m32, source.m33);
    } else {
      applyMatrix(source.m00, source.m01, source.m03,
                  source.m10, source.m11, source.m13);
    }
  }


  @Override
  public void applyMatrix(float n00, float n01, float n02, float n03,
                          float n10, float n11, float n12, float n13,
                          float n20, float n21, float n22, float n23,
                          float n30, float n31, float n32, float n33) {
    if (webgl) {
      matrix.apply(n00, n01, n02, n03,
                   n10, n11, n12, n13,
                   n20, n21, n22, n23,
                   n30, n31, n32, n33);
      P5Bridge.applyMatrix3(p5(),
                             n00, n01, n02, n03,
                             n10, n11, n12, n13,
                             n20, n21, n22, n23,
                             n30, n31, n32, n33);
    } else {
      applyMatrix(n00, n01, n03,
                  n10, n11, n13);
    }
  }


  @Override
  public PMatrix getMatrix() {
    return webgl ? matrix.get() : getMatrix(new PMatrix2D());
  }


  @Override
  public PMatrix2D getMatrix(PMatrix2D target) {
    if (target == null) {
      target = new PMatrix2D();
    }
    target.set(matrix.m00, matrix.m01, matrix.m03,
               matrix.m10, matrix.m11, matrix.m13);
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
    applyWebglOrigin();
    P5Bridge.applyMatrix(p5(), matrix.m00, matrix.m10, matrix.m01,
                         matrix.m11, matrix.m03, matrix.m13);
  }


  @Override
  public void setMatrix(PMatrix3D source) {
    matrix.set(source);
    P5Bridge.resetMatrix(p5());
    applyWebglOrigin();
    if (webgl) {
      P5Bridge.applyMatrix3(p5(),
                             source.m00, source.m01, source.m02, source.m03,
                             source.m10, source.m11, source.m12, source.m13,
                             source.m20, source.m21, source.m22, source.m23,
                             source.m30, source.m31, source.m32, source.m33);
    } else {
      P5Bridge.applyMatrix(p5(), matrix.m00, matrix.m10, matrix.m01,
                           matrix.m11, matrix.m03, matrix.m13);
    }
  }


  @Override
  public void box(float size) {
    box(size, size, size);
  }


  @Override
  public void box(float width, float height, float depth) {
    if (webgl) {
      P5Bridge.box(p5(), width, height, depth);
    } else {
      super.box(width, height, depth);
    }
  }


  @Override
  public void sphere(float radius) {
    if (webgl) {
      if ((sphereDetailU < 3) || (sphereDetailV < 2)) {
        sphereDetail(30);
      }
      P5Bridge.sphere(p5(), radius, sphereDetailU, sphereDetailV);
    } else {
      super.sphere(radius);
    }
  }


  @Override
  public void lights() {
    if (webgl) {
      P5Bridge.noLights(p5());
      P5Bridge.defaultLightState(p5());
      P5Bridge.ambientLight(p5(), 128, 128, 128);
      P5Bridge.directionalLight(p5(), 128, 128, 128, 0, 0, -1);
    } else {
      super.lights();
    }
  }


  @Override
  public void noLights() {
    if (webgl) {
      P5Bridge.noLights(p5());
      P5Bridge.defaultLightState(p5());
    } else {
      super.noLights();
    }
  }


  @Override
  public void ambientLight(float v1, float v2, float v3) {
    if (webgl) {
      colorCalc(v1, v2, v3);
      P5Bridge.ambientLight(p5(), calcRi, calcGi, calcBi);
    } else {
      super.ambientLight(v1, v2, v3);
    }
  }


  @Override
  public void ambientLight(float v1, float v2, float v3,
                           float x, float y, float z) {
    ambientLight(v1, v2, v3);
  }


  @Override
  public void lightFalloff(float constant, float linear, float quadratic) {
    if (webgl) {
      P5Bridge.lightFalloff(p5(), constant, linear, quadratic);
    } else {
      super.lightFalloff(constant, linear, quadratic);
    }
  }


  @Override
  public void lightSpecular(float v1, float v2, float v3) {
    if (webgl) {
      colorCalc(v1, v2, v3);
      P5Bridge.lightSpecular(p5(), calcRi, calcGi, calcBi);
    } else {
      super.lightSpecular(v1, v2, v3);
    }
  }


  @Override
  public void directionalLight(float v1, float v2, float v3,
                               float nx, float ny, float nz) {
    if (webgl) {
      colorCalc(v1, v2, v3);
      transformLightVector(nx, ny, nz);
      P5Bridge.directionalLight(p5(), calcRi, calcGi, calcBi,
                                lightVectorX, lightVectorY, lightVectorZ);
    } else {
      super.directionalLight(v1, v2, v3, nx, ny, nz);
    }
  }


  @Override
  public void pointLight(float v1, float v2, float v3,
                         float x, float y, float z) {
    if (webgl) {
      colorCalc(v1, v2, v3);
      P5Bridge.pointLight(p5(), calcRi, calcGi, calcBi,
                          toWebglX(transformPointX(x, y, z)),
                          toWebglY(transformPointY(x, y, z)),
                          transformPointZ(x, y, z));
    } else {
      super.pointLight(v1, v2, v3, x, y, z);
    }
  }


  @Override
  public void spotLight(float v1, float v2, float v3,
                        float x, float y, float z,
                        float nx, float ny, float nz,
                        float angle, float concentration) {
    if (webgl) {
      colorCalc(v1, v2, v3);
      transformLightVector(nx, ny, nz);
      P5Bridge.spotLight(p5(), calcRi, calcGi, calcBi,
                         toWebglX(transformPointX(x, y, z)),
                         toWebglY(transformPointY(x, y, z)),
                         transformPointZ(x, y, z),
                         lightVectorX, lightVectorY, lightVectorZ,
                         angle, concentration);
    } else {
      super.spotLight(v1, v2, v3, x, y, z, nx, ny, nz, angle, concentration);
    }
  }


  private float transformPointX(float x, float y, float z) {
    return matrix.m00 * x + matrix.m01 * y + matrix.m02 * z + matrix.m03;
  }


  private float transformPointY(float x, float y, float z) {
    return matrix.m10 * x + matrix.m11 * y + matrix.m12 * z + matrix.m13;
  }


  private float transformPointZ(float x, float y, float z) {
    return matrix.m20 * x + matrix.m21 * y + matrix.m22 * z + matrix.m23;
  }


  private void transformLightVector(float x, float y, float z) {
    lightNormalMatrix.set(matrix);
    if (!lightNormalMatrix.invert()) {
      lightVectorX = 0;
      lightVectorY = 0;
      lightVectorZ = 0;
      return;
    }

    float nx = x * lightNormalMatrix.m00 +
               y * lightNormalMatrix.m10 +
               z * lightNormalMatrix.m20;
    float ny = x * lightNormalMatrix.m01 +
               y * lightNormalMatrix.m11 +
               z * lightNormalMatrix.m21;
    float nz = x * lightNormalMatrix.m02 +
               y * lightNormalMatrix.m12 +
               z * lightNormalMatrix.m22;
    float length = PApplet.dist(0, 0, 0, nx, ny, nz);
    if (length > 0) {
      float inverseLength = 1.0f / length;
      lightVectorX = nx * inverseLength;
      lightVectorY = ny * inverseLength;
      lightVectorZ = nz * inverseLength;
    } else {
      lightVectorX = 0;
      lightVectorY = 0;
      lightVectorZ = 0;
    }
  }


  @Override
  public void camera() {
    float cameraZ = (height / 2.0f) / PApplet.tan(PConstants.PI * 60.0f / 360.0f);
    camera(width / 2.0f, height / 2.0f, cameraZ,
           width / 2.0f, height / 2.0f, 0,
           0, 1, 0);
  }


  @Override
  public void camera(float eyeX, float eyeY, float eyeZ,
                     float centerX, float centerY, float centerZ,
                     float upX, float upY, float upZ) {
    if (webgl) {
      P5Bridge.camera(p5(),
                      toWebglX(eyeX), toWebglY(eyeY), eyeZ,
                      toWebglX(centerX), toWebglY(centerY), centerZ,
                      upX, upY, upZ);
    } else {
      super.camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }
  }


  private float toWebglX(float x) {
    return x - width / 2.0f;
  }


  private float toWebglY(float y) {
    return y - height / 2.0f;
  }


  @Override
  public void ortho() {
    ortho(-width / 2.0f, width / 2.0f, -height / 2.0f, height / 2.0f);
  }


  @Override
  public void ortho(float left, float right, float bottom, float top) {
    ortho(left, right, bottom, top, 0, Math.max(width, height) * 10.0f);
  }


  @Override
  public void ortho(float left, float right,
                    float bottom, float top,
                    float near, float far) {
    if (webgl) {
      P5Bridge.ortho(p5(), left, right, bottom, top, near, far);
    } else {
      super.ortho(left, right, bottom, top, near, far);
    }
  }


  @Override
  public void perspective() {
    float cameraZ = (height / 2.0f) / PApplet.tan(PConstants.PI * 60.0f / 360.0f);
    perspective(PConstants.PI / 3.0f, (float) width / (float) height,
                cameraZ / 10.0f, cameraZ * 10.0f);
  }


  @Override
  public void perspective(float fovy, float aspect, float zNear, float zFar) {
    if (webgl) {
      P5Bridge.perspective(p5(), fovy, aspect, zNear, zFar);
    } else {
      super.perspective(fovy, aspect, zNear, zFar);
    }
  }


  @Override
  public void frustum(float left, float right,
                      float bottom, float top,
                      float near, float far) {
    if (webgl) {
      P5Bridge.frustum(p5(), left, right, bottom, top, near, far);
    } else {
      super.frustum(left, right, bottom, top, near, far);
    }
  }


  @Override
  public boolean is3D() {
    return webgl;
  }


  @Override
  public boolean isGL() {
    return webgl;
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
    // PGraphics prepositions each text line for alignment before calling textLineImpl().
    P5Bridge.textAlign(p5(), LEFT, BASELINE);
  }


  @Override
  public float textAscent() {
    return P5Bridge.textAscent(p5(), textSize * 0.8f);
  }


  @Override
  public float textDescent() {
    return P5Bridge.textDescent(p5(), textSize * 0.2f);
  }


  @Override
  public float textWidth(char c) {
    return textWidth(String.valueOf(c));
  }


  @Override
  public float textWidth(String text) {
    return P5Bridge.textWidth(p5(), text);
  }


  @Override
  public float textWidth(char[] chars, int start, int length) {
    return textWidth(new String(chars, start, length));
  }


  @Override
  protected float textWidthImpl(char[] buffer, int start, int stop) {
    return textWidth(new String(buffer, start, stop - start));
  }


  @Override
  protected void textLineImpl(char[] buffer, int start, int stop, float x, float y) {
    if (!fill) {
      return;
    }
    String text = new String(buffer, start, stop - start);
    P5Bridge.text(p5(), text, x, y);
  }
}
