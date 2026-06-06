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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.teavm.jso.JSObject;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.platform.core.PlatformProcess;
import processing.platform.core.PlatformRuntime;
import processing.platform.core.PlatformRuntimeProvider;

/**
 * Browser runtime for TeaVM sketches using an injected p5 instance.
 */
public class P5PlatformRuntime implements PlatformRuntime {
  private final Map<String, String> properties = new HashMap<>();
  private JSObject p5;


  public P5PlatformRuntime() {
  }


  public P5PlatformRuntime(JSObject p5) {
    this.p5 = p5;
  }


  static P5PlatformRuntime current() {
    PlatformRuntime runtime = PlatformRuntimeProvider.get();
    if (runtime instanceof P5PlatformRuntime) {
      return (P5PlatformRuntime) runtime;
    }
    throw new IllegalStateException("TeaVM p5 platform is not installed");
  }


  JSObject p5() {
    if (p5 == null) {
      p5 = P5Platform.getP5();
    }
    if (p5 == null) {
      throw new IllegalStateException("Call processing.platform.teavm.P5Platform.install(p5) before running a sketch");
    }
    return p5;
  }


  @Override
  public int platform() {
    return PConstants.OTHER;
  }


  @Override
  public String defaultRenderer() {
    return PGraphicsP5.class.getName();
  }


  @Override
  public String getProperty(String key) {
    String value = properties.get(key);
    if (value != null) {
      return value;
    }

    switch (key) {
    case "java.version":
      return "17";
    case "os.name":
      return "Browser";
    case "user.dir":
    case "user.home":
      return "/";
    case "file.separator":
      return "/";
    case "path.separator":
      return ":";
    case "line.separator":
      return "\n";
    default:
      return null;
    }
  }


  @Override
  public String getProperty(String key, String defaultValue) {
    String value = getProperty(key);
    return value == null ? defaultValue : value;
  }


  @Override
  public void setProperty(String key, String value) {
    if (value == null) {
      properties.remove(key);
    } else {
      properties.put(key, value);
    }
  }


  @Override
  public String getenv(String name) {
    return null;
  }


  @Override
  public long currentTimeMillis() {
    return (long) P5Bridge.dateNow();
  }


  @Override
  public long nanoTime() {
    return (long) (P5Bridge.performanceNow() * 1000000.0);
  }


  @Override
  public int availableProcessors() {
    return Math.max(1, P5Bridge.hardwareConcurrency());
  }


  @Override
  public PlatformProcess exec(String... args) throws IOException {
    throw new UnsupportedOperationException("Process execution is not available in TeaVM");
  }


  @Override
  public PGraphics createGraphics(String renderer) {
    if (renderer.equals(PGraphicsP5.class.getName())) {
      return new PGraphicsP5();
    }
    throw new UnsupportedOperationException("Renderer is not available in TeaVM: " + renderer);
  }


  @Override
  public void exit(int status) {
    P5Bridge.noLoop(p5());
  }


  @Override
  public void initRun() {
    P5Bridge.noLoop(p5());
  }


  @Override
  public void initSketch(PApplet sketch) {
  }


  @Override
  public void cleanupSketch(PApplet sketch) {
  }


  @Override
  public void hideMenuBar() {
  }


  @Override
  public void setIconImage(Object image) {
  }


  @Override
  public int getDisplayWidth() {
    return P5Bridge.innerWidth();
  }


  @Override
  public int getDisplayHeight() {
    return P5Bridge.innerHeight();
  }


  @Override
  public int getDisplayCount() {
    return 1;
  }


  @Override
  public int getDisplayDensity(int display) {
    return Math.max(1, (int) Math.round(P5Bridge.devicePixelRatio()));
  }


  @Override
  public PImage loadImage(PApplet sketch, String path, Object... args) {
    JSObject nativeImage = P5Bridge.loadImage(p5(), path);
    if (nativeImage == null) {
      return null;
    }
    P5Image image = new P5Image(nativeImage);
    image.parent = sketch;
    return image;
  }


  @Override
  public PImage loadBase64Image(String imagePath) {
    JSObject nativeImage = P5Bridge.loadImage(p5(), imagePath);
    return nativeImage == null ? null : new P5Image(nativeImage);
  }


  @Override
  public boolean saveImage(PImage image, String path, String... args) {
    Object nativeImage = getNativeImage(image);
    return nativeImage instanceof JSObject &&
      P5Bridge.save(p5(), (JSObject) nativeImage, path);
  }


  @Override
  public void resizeImage(PImage image, int width, int height, int interpolationMode) {
    if (image instanceof P5Image) {
      ((P5Image) image).resize(width, height);
      return;
    }
    resizePixels(image, width, height);
  }


  @Override
  public void fromNativeImage(Object nativeImage, PImage target) {
    if (nativeImage instanceof JSObject) {
      P5Image.copyNativeToImage((JSObject) nativeImage, target);
    }
  }


  @Override
  public Object getNativeImage(PImage image) {
    if (image instanceof P5Image) {
      return ((P5Image) image).nativeImage();
    }
    if (image instanceof PGraphicsP5) {
      return ((PGraphicsP5) image).canvas();
    }
    return P5Image.createNativeFromImage(p5(), image);
  }


  @Override
  public File getDesktopFolder() {
    return new File("/");
  }


  @Override
  public String calcSketchPath() {
    return "/";
  }


  @Override
  public File dataFile(PApplet sketch, String sketchPath, String where) {
    if (where == null || where.length() == 0) {
      return new File("/");
    }
    if (where.charAt(0) == '/') {
      return new File(where);
    }
    String base = sketchPath == null || sketchPath.length() == 0 ? "/" : sketchPath;
    return new File(base + (base.endsWith("/") ? "" : "/") + "data/" + where);
  }


  @Override
  public boolean openLink(String url) {
    return P5Bridge.openWindow(url);
  }


  @Override
  public void selectInput(String prompt, String callback, File file, Object callbackObject) {
    PApplet.selectCallback(null, callback, callbackObject);
  }


  @Override
  public void selectOutput(String prompt, String callback, File file, Object callbackObject) {
    PApplet.selectCallback(null, callback, callbackObject);
  }


  @Override
  public void selectFolder(String prompt, String callback, File file, Object callbackObject) {
    PApplet.selectCallback(null, callback, callbackObject);
  }


  private void resizePixels(PImage image, int width, int height) {
    if (width == 0 && height == 0) {
      return;
    }
    if (image.width <= 0 || image.height <= 0) {
      return;
    }
    if (width == 0) {
      width = Math.max(1, Math.round(image.width * (height / (float) image.height)));
    } else if (height == 0) {
      height = Math.max(1, Math.round(image.height * (width / (float) image.width)));
    }

    image.loadPixels();
    int oldWidth = image.pixelWidth;
    int oldHeight = image.pixelHeight;
    int[] oldPixels = image.pixels;

    image.init(width, height, image.format, image.pixelDensity);
    for (int y = 0; y < image.pixelHeight; y++) {
      int sourceY = Math.min(oldHeight - 1, y * oldHeight / image.pixelHeight);
      int targetRow = y * image.pixelWidth;
      int sourceRow = sourceY * oldWidth;
      for (int x = 0; x < image.pixelWidth; x++) {
        int sourceX = Math.min(oldWidth - 1, x * oldWidth / image.pixelWidth);
        image.pixels[targetRow + x] = oldPixels[sourceRow + sourceX];
      }
    }
    image.updatePixels();
  }
}
