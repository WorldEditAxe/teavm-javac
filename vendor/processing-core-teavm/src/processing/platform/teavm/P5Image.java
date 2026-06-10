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
    if (width != nativeWidth || height != nativeHeight || pixels == null) {
      init(nativeWidth, nativeHeight, PConstants.ARGB, 1);
    }
  }


  @Override
  public void loadPixels() {
    syncSize();
    if (pixelWidth == 0 || pixelHeight == 0) {
      setLoaded();
      return;
    }

    P5Bridge.imageLoadAndCopyImagePixelsToIntArray(nativeImage, pixels, pixels.length);
    setLoaded();
  }


  @Override
  public void updatePixels(int x, int y, int w, int h) {
    syncSize();
    if (pixels == null) {
      return;
    }

    P5Bridge.imageLoadAndWriteImagePixels(nativeImage, pixels, pixels.length);
    super.updatePixels(x, y, w, h);
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
    P5Bridge.imageResize(nativeImage, width, height);
    syncSize();
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
    P5Bridge.imageLoadAndWriteImagePixels(nativeImage, source.pixels, source.pixels.length);
    return nativeImage;
  }
}
