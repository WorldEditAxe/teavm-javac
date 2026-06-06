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

package processing.platform.os;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import processing.awt.ShimAWT;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.platform.core.PlatformProcess;
import processing.platform.core.PlatformRuntime;

/**
 * JVM desktop platform runtime backed by AWT/Swing/ImageIO.
 */
public class OSPlatformRuntime implements PlatformRuntime {
  @Override
  public int platform() {
    String name = getProperty("os.name", "");
    if (name.contains("Mac")) {
      return PConstants.MACOS;
    } else if (name.contains("Windows")) {
      return PConstants.WINDOWS;
    } else if (name.equals("Linux")) {
      return PConstants.LINUX;
    }
    return PConstants.OTHER;
  }


  @Override
  public String defaultRenderer() {
    return PConstants.JAVA2D;
  }


  @Override
  public String getProperty(String key) {
    return System.getProperty(key);
  }


  @Override
  public String getProperty(String key, String defaultValue) {
    return System.getProperty(key, defaultValue);
  }


  @Override
  public void setProperty(String key, String value) {
    System.setProperty(key, value);
  }


  @Override
  public String getenv(String name) {
    return System.getenv(name);
  }


  @Override
  public long currentTimeMillis() {
    return System.currentTimeMillis();
  }


  @Override
  public long nanoTime() {
    return System.nanoTime();
  }


  @Override
  public int availableProcessors() {
    return Runtime.getRuntime().availableProcessors();
  }


  @Override
  public PlatformProcess exec(String... args) throws IOException {
    return new OSPlatformProcess(Runtime.getRuntime().exec(args));
  }


  @Override
  public PGraphics createGraphics(String renderer) throws Exception {
    Class<?> rendererClass = Class.forName(renderer);
    return (PGraphics) rendererClass.getDeclaredConstructor().newInstance();
  }


  @Override
  public void exit(int status) {
    System.exit(status);
  }


  @Override
  public void initRun() {
    ShimAWT.initRun();
  }


  @Override
  public void initSketch(PApplet sketch) {
    if (platform() == PConstants.MACOS) {
      try {
        ThinkDifferent.init(sketch);
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }


  @Override
  public void cleanupSketch(PApplet sketch) {
    if (platform() == PConstants.MACOS) {
      try {
        ThinkDifferent.cleanup();
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }


  @Override
  public void hideMenuBar() {
    if (platform() == PConstants.MACOS) {
      try {
        ThinkDifferent.hideMenuBar();
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }


  @Override
  public void setIconImage(Object image) {
    if (platform() == PConstants.MACOS) {
      try {
        ThinkDifferent.setIconImage((java.awt.Image) image);
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }


  @Override
  public int getDisplayWidth() {
    return ShimAWT.getDisplayWidth();
  }


  @Override
  public int getDisplayHeight() {
    return ShimAWT.getDisplayHeight();
  }


  @Override
  public int getDisplayCount() {
    return ShimAWT.getDisplayCount();
  }


  @Override
  public int getDisplayDensity(int display) {
    return ShimAWT.getDisplayDensity(display);
  }


  @Override
  public PImage loadImage(PApplet sketch, String path, Object... args) {
    return ShimAWT.loadImage(sketch, path, args);
  }


  @Override
  public PImage loadBase64Image(String imagePath) {
    return ShimAWT.loadBase64Image(imagePath);
  }


  @Override
  public boolean saveImage(PImage image, String path, String... args) {
    return ShimAWT.saveImage(image, path, args);
  }


  @Override
  public void resizeImage(PImage image, int width, int height, int interpolationMode) {
    ShimAWT.resizeImage(image, width, height, interpolationMode);
  }


  @Override
  public void fromNativeImage(Object nativeImage, PImage target) {
    ShimAWT.fromNativeImage((java.awt.Image) nativeImage, target);
  }


  @Override
  public Object getNativeImage(PImage image) {
    return ShimAWT.getNativeImage(image);
  }


  @Override
  public File getDesktopFolder() {
    if (platform() == PConstants.WINDOWS) {
      return ShimAWT.getWindowsDesktop();
    }
    return new File(getProperty("user.home", ""), "Desktop");
  }


  @Override
  public String calcSketchPath() {
    String folder = getProperty("user.dir");
    try {
      URL jarURL =
        PApplet.class.getProtectionDomain().getCodeSource().getLocation();
      String jarPath = jarURL.toURI().getSchemeSpecificPart();

      if (platform() == PConstants.MACOS) {
        if (jarPath.contains("Contents/Java/")) {
          String appPath = jarPath.substring(0, jarPath.indexOf(".app") + 4);
          File containingFolder = new File(appPath).getParentFile();
          folder = containingFolder.getAbsolutePath();
        }
      } else if (jarPath.contains("/lib/")) {
        folder = new File(jarPath, "../..").getCanonicalPath();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return folder;
  }


  @Override
  public File dataFile(PApplet sketch, String sketchPath, String where) {
    File file = new File(where);
    if (file.isAbsolute()) return file;

    URL jarURL = sketch.getClass().getProtectionDomain().getCodeSource().getLocation();
    String jarPath;
    try {
      jarPath = jarURL.toURI().getPath();
    } catch (URISyntaxException e) {
      e.printStackTrace();
      return null;
    }
    if (jarPath.contains("Contents/Java/")) {
      File containingFolder = new File(jarPath).getParentFile();
      File dataFolder = new File(containingFolder, "data");
      return new File(dataFolder, where);
    }
    return new File(sketchPath + File.separator + "data" + File.separator + where);
  }


  @Override
  public boolean openLink(String url) {
    return ShimAWT.openLink(url);
  }


  @Override
  public void selectInput(String prompt, String callback, File file, Object callbackObject) {
    ShimAWT.selectInput(prompt, callback, file, callbackObject);
  }


  @Override
  public void selectOutput(String prompt, String callback, File file, Object callbackObject) {
    ShimAWT.selectOutput(prompt, callback, file, callbackObject);
  }


  @Override
  public void selectFolder(String prompt, String callback, File file, Object callbackObject) {
    ShimAWT.selectFolder(prompt, callback, file, callbackObject);
  }


  static class OSPlatformProcess implements PlatformProcess {
    private final Process process;

    OSPlatformProcess(Process process) {
      this.process = process;
    }

    @Override
    public java.io.InputStream getInputStream() {
      return process.getInputStream();
    }

    @Override
    public java.io.InputStream getErrorStream() {
      return process.getErrorStream();
    }

    @Override
    public int waitFor() throws InterruptedException {
      return process.waitFor();
    }
  }
}
