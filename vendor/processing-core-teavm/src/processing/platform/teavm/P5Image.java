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

import processing.core.PConstants;
import processing.core.PImage;

public class P5Image extends PImage {
  private JSObject nativeImage;


  public P5Image(JSObject nativeImage) {
    this.nativeImage = nativeImage;
    syncSize();
  }


  JSObject nativeImage() {
    return nativeImage;
  }


  void syncSize() {
    int nativeWidth = P5Bridge.imageWidth(nativeImage);
    int nativeHeight = P5Bridge.imageHeight(nativeImage);
    if (nativeWidth < 0) {
      nativeWidth = 0;
    }
    if (nativeHeight < 0) {
      nativeHeight = 0;
    }
    if (width != nativeWidth || height != nativeHeight) {
      width = nativeWidth;
      height = nativeHeight;
      pixelDensity = 1;
      pixelWidth = nativeWidth;
      pixelHeight = nativeHeight;
      format = PConstants.ARGB;
      if (pixels != null && pixels.length != pixelWidth * pixelHeight) {
        pixels = null;
      }
    }
  }


  @Override
  public void loadPixels() {
    syncSize();
    pixels = new int[pixelWidth * pixelHeight];
    if (pixelWidth == 0 || pixelHeight == 0) {
      setLoaded();
      return;
    }

    P5Bridge.imageLoadPixels(nativeImage);
    int pixelCount = Math.min(pixels.length, P5Bridge.imagePixelLength(nativeImage) / 4);
    for (int i = 0; i < pixelCount; i++) {
      int base = i * 4;
      int r = P5Bridge.imagePixel(nativeImage, base);
      int g = P5Bridge.imagePixel(nativeImage, base + 1);
      int b = P5Bridge.imagePixel(nativeImage, base + 2);
      int a = P5Bridge.imagePixel(nativeImage, base + 3);
      pixels[i] = (a << 24) | (r << 16) | (g << 8) | b;
    }
    setLoaded();
  }


  @Override
  public void updatePixels(int x, int y, int w, int h) {
    syncSize();
    if (pixels == null) {
      return;
    }

    P5Bridge.imageLoadPixels(nativeImage);
    int pixelCount = Math.min(pixels.length, P5Bridge.imagePixelLength(nativeImage) / 4);
    for (int i = 0; i < pixelCount; i++) {
      int argb = pixels[i];
      int base = i * 4;
      P5Bridge.setImagePixel(nativeImage, base, (argb >> 16) & 0xff);
      P5Bridge.setImagePixel(nativeImage, base + 1, (argb >> 8) & 0xff);
      P5Bridge.setImagePixel(nativeImage, base + 2, argb & 0xff);
      P5Bridge.setImagePixel(nativeImage, base + 3, (argb >>> 24) & 0xff);
    }
    P5Bridge.imageUpdatePixels(nativeImage);
    super.updatePixels(x, y, w, h);
  }


  @Override
  public int get(int x, int y) {
    loadPixels();
    return super.get(x, y);
  }


  @Override
  public PImage get(int x, int y, int width, int height) {
    loadPixels();
    return super.get(x, y, width, height);
  }


  @Override
  public PImage get() {
    loadPixels();
    return super.get();
  }


  @Override
  public PImage copy() {
    loadPixels();
    return super.copy();
  }


  @Override
  public void set(int x, int y, int value) {
    loadPixels();
    super.set(x, y, value);
  }


  @Override
  public void set(int x, int y, PImage image) {
    loadPixels();
    image.loadPixels();
    super.set(x, y, image);
  }


  @Override
  public void resize(int width, int height, int interpolationMode) {
    resize(width, height);
  }


  @Override
  public void resize(int width, int height) {
    if (width == 0 && height == 0) {
      return;
    }
    if (this.width <= 0 || this.height <= 0) {
      return;
    }
    if (width == 0) {
      width = Math.max(1, Math.round(this.width * (height / (float) this.height)));
    } else if (height == 0) {
      height = Math.max(1, Math.round(this.height * (width / (float) this.width)));
    }
    nativeImage = P5Bridge.imageResize(nativeImage, width, height);
    syncSize();
    pixels = null;
    setLoaded(false);
  }


  static void copyNativeToImage(JSObject nativeImage, PImage target) {
    P5Image source = new P5Image(nativeImage);
    source.loadPixels();
    target.init(source.width, source.height, PConstants.ARGB, 1);
    System.arraycopy(source.pixels, 0, target.pixels, 0, source.pixels.length);
    target.setLoaded();
  }


  static JSObject createNativeFromImage(JSObject p5, PImage source) {
    JSObject nativeImage = P5Bridge.createImage(p5, source.width, source.height);
    if (nativeImage == null) {
      return null;
    }

    source.loadPixels();
    P5Bridge.imageLoadPixels(nativeImage);
    int pixelCount = Math.min(source.pixels.length, P5Bridge.imagePixelLength(nativeImage) / 4);
    for (int i = 0; i < pixelCount; i++) {
      int argb = source.pixels[i];
      int base = i * 4;
      P5Bridge.setImagePixel(nativeImage, base, (argb >> 16) & 0xff);
      P5Bridge.setImagePixel(nativeImage, base + 1, (argb >> 8) & 0xff);
      P5Bridge.setImagePixel(nativeImage, base + 2, argb & 0xff);
      P5Bridge.setImagePixel(nativeImage, base + 3, (argb >>> 24) & 0xff);
    }
    P5Bridge.imageUpdatePixels(nativeImage);
    return nativeImage;
  }
}
