/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

/*
  Part of the Processing project - http://processing.org

  Copyright (c) 2012-2014 The Processing Foundation
  Copyright (c) 2007-2012 Ben Fry and Casey Reas

  This program is free software; you can redistribute it and/or
  modify it under the terms of the GNU General Public License
  version 2, as published by the Free Software Foundation.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software Foundation,
  Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package processing.platform.os;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.Taskbar;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import processing.core.PApplet;
import processing.platform.core.PlatformRuntimeProvider;

/**
 * Deal with issues related to macOS application behavior.
 */
public class ThinkDifferent {
  static private Desktop desktop;
  static private Taskbar taskbar;

  static boolean attemptedQuit;


  static public void init(final PApplet sketch) {
    getDesktop().setQuitHandler((event, quitResponse) -> {
      sketch.exit();

      boolean noKnownCrash = PApplet.uncaughtThrowable == null;

      if (noKnownCrash && !attemptedQuit) {
        quitResponse.cancelQuit();
        attemptedQuit = true;
      } else {
        quitResponse.performQuit();
      }
    });
  }


  static public void cleanup() {
    getDesktop().setQuitHandler(null);
  }


  static public void setIconImage(Image image) {
    getTaskbar().setIconImage(image);
  }


  static private Taskbar getTaskbar() {
    if (taskbar == null) {
      taskbar = Taskbar.getTaskbar();
    }
    return taskbar;
  }


  static private Desktop getDesktop() {
    if (desktop == null) {
      desktop = Desktop.getDesktop();
    }
    return desktop;
  }


  static native public void hideMenuBar();

  static native public void showMenuBar();

  static native public void activateIgnoringOtherApps();

  static native public void activate();


  static public boolean activateSketchWindow() {
    try {
      String osVersion = PlatformRuntimeProvider.get().getProperty("os.version");
      int versionNumber = Integer.parseInt(osVersion.split("\\.")[0]);

      if (versionNumber >= 14) {
        activate();
        return true;
      } else if (versionNumber >= 10) {
        activateIgnoringOtherApps();
        return true;
      }
    } catch (Exception e) {
      return false;
    }

    return false;
  }


  static {
    final String nativeFilename = "libDifferent.jnilib";
    try {
      File temp = File.createTempFile("processing", "different");
      if (temp.delete() && temp.mkdirs()) {
        temp.deleteOnExit();

        File jnilibFile = new File(temp, nativeFilename);
        InputStream input =
          PApplet.class.getResourceAsStream("/processing/core/" + nativeFilename);
        if (input != null) {
          if (PApplet.saveStream(jnilibFile, input)) {
            System.load(jnilibFile.getAbsolutePath());
          } else {
            System.err.println("Full screen disabled: could not save library");
          }
        } else {
          System.err.println("Full screen disabled: could not load " + nativeFilename + " from core.jar");
        }
      } else {
        System.err.println("Full screen disabled: could not create temporary folder");
      }
    } catch (IOException e) {
      System.err.println("Full screen disabled");
      e.printStackTrace();
    }
  }
}
