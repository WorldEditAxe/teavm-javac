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
import java.io.InputStream;
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
 * Browser runtime for TeaVM sketches using a direct Canvas2D host.
 */
public class Canvas2DPlatformRuntime implements PlatformRuntime {
  private final Map<String, String> properties = new HashMap<>();
  private JSObject host;


  public Canvas2DPlatformRuntime() {
  }


  public Canvas2DPlatformRuntime(JSObject host) {
    this.host = host;
  }


  static Canvas2DPlatformRuntime current() {
    PlatformRuntime runtime = PlatformRuntimeProvider.get();
    if (runtime instanceof Canvas2DPlatformRuntime) {
      return (Canvas2DPlatformRuntime) runtime;
    }
    throw new IllegalStateException("TeaVM Canvas2D platform is not installed");
  }


  JSObject host() {
    if (host == null) {
      host = Canvas2DPlatform.getHost();
    }
    if (host == null) {
      throw new IllegalStateException("Call processing.platform.teavm.Canvas2DPlatform.install(canvasHost) before running a sketch");
    }
    return host;
  }


  @Override
  public int platform() {
    return PConstants.OTHER;
  }


  @Override
  public String defaultRenderer() {
    return PGraphicsCanvas2D.class.getName();
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
    return (long) Canvas2DBridge.dateNow();
  }


  @Override
  public long nanoTime() {
    return (long) (Canvas2DBridge.performanceNow() * 1000000.0);
  }


  @Override
  public int availableProcessors() {
    return Math.max(1, Canvas2DBridge.hardwareConcurrency());
  }


  @Override
  public PlatformProcess exec(String... args) throws IOException {
    throw new UnsupportedOperationException("Process execution is not available in TeaVM");
  }


  @Override
  public PGraphics createGraphics(String renderer) {
    if (renderer.equals(PGraphicsCanvas2D.class.getName())) {
      return new PGraphicsCanvas2D();
    }
    throw new UnsupportedOperationException("Renderer is not available in TeaVM: " + renderer);
  }


  @Override
  public void exit(int status) {
    Canvas2DBridge.noLoop(host());
  }


  @Override
  public void initRun() {
    Canvas2DBridge.noLoop(host());
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
    return Canvas2DBridge.innerWidth();
  }


  @Override
  public int getDisplayHeight() {
    return Canvas2DBridge.innerHeight();
  }


  @Override
  public int getDisplayCount() {
    return 1;
  }


  @Override
  public int getDisplayDensity(int display) {
    return Math.max(1, (int) Math.round(Canvas2DBridge.devicePixelRatio()));
  }


  @Override
  public PImage loadImage(PApplet sketch, String path, Object... args) {
    String imagePath = loadImagePath(sketch, path);
    if (imagePath == null) {
      return null;
    }
    JSObject nativeImage = Canvas2DBridge.loadImage(host(), imagePath);
    if (nativeImage == null) {
      return null;
    }
    Canvas2DImage image = new Canvas2DImage(nativeImage);
    image.parent = sketch;
    return image;
  }


  private String loadImagePath(PApplet sketch, String path) {
    try (InputStream input = sketch.createInput(path)) {
      if (input == null) {
        System.err.println("The image " + path + " could not be found.");
        return null;
      }
      return FileAssetBridge.loadImage(path, PApplet.loadBytes(input));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }


  @Override
  public PImage loadBase64Image(String imagePath) {
    JSObject nativeImage = Canvas2DBridge.loadImage(host(), imagePath);
    return nativeImage == null ? null : new Canvas2DImage(nativeImage);
  }


  @Override
  public boolean saveImage(PImage image, String path, String... args) {
    Object nativeImage = getNativeImage(image);
    return nativeImage instanceof JSObject &&
      Canvas2DBridge.save(host(), (JSObject) nativeImage, path);
  }


  @Override
  public void resizeImage(PImage image, int width, int height, int interpolationMode) {
    if (image instanceof Canvas2DImage) {
      ((Canvas2DImage) image).resize(width, height);
      return;
    }
    resizePixels(image, width, height);
  }


  @Override
  public void fromNativeImage(Object nativeImage, PImage target) {
    if (nativeImage instanceof JSObject) {
      Canvas2DImage.copyNativeToImage((JSObject) nativeImage, target);
    }
  }


  @Override
  public Object getNativeImage(PImage image) {
    if (image instanceof Canvas2DImage) {
      return ((Canvas2DImage) image).nativeImage();
    }
    if (image instanceof PGraphicsCanvas2D) {
      return ((PGraphicsCanvas2D) image).canvas();
    }
    return Canvas2DImage.createNativeFromImage(host(), image);
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
    return Canvas2DBridge.openWindow(url);
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
