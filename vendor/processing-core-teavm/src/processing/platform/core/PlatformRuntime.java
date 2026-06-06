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

package processing.platform.core;

import java.io.File;
import java.io.IOException;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 * Runtime services that Processing core needs from the host platform.
 */
public interface PlatformRuntime {
  int platform();

  String defaultRenderer();

  String getProperty(String key);

  String getProperty(String key, String defaultValue);

  void setProperty(String key, String value);

  String getenv(String name);

  long currentTimeMillis();

  long nanoTime();

  int availableProcessors();

  PlatformProcess exec(String... args) throws IOException;

  PGraphics createGraphics(String renderer) throws Exception;

  void exit(int status);

  void initRun();

  void initSketch(PApplet sketch);

  void cleanupSketch(PApplet sketch);

  void hideMenuBar();

  void setIconImage(Object image);

  int getDisplayWidth();

  int getDisplayHeight();

  int getDisplayCount();

  int getDisplayDensity(int display);

  PImage loadImage(PApplet sketch, String path, Object... args);

  PImage loadBase64Image(String imagePath);

  boolean saveImage(PImage image, String path, String... args);

  void resizeImage(PImage image, int width, int height, int interpolationMode);

  void fromNativeImage(Object nativeImage, PImage target);

  Object getNativeImage(PImage image);

  File getDesktopFolder();

  String calcSketchPath();

  File dataFile(PApplet sketch, String sketchPath, String where);

  boolean openLink(String url);

  void selectInput(String prompt, String callback, File file, Object callbackObject);

  void selectOutput(String prompt, String callback, File file, Object callbackObject);

  void selectFolder(String prompt, String callback, File file, Object callbackObject);
}
