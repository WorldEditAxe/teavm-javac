/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

package processing.opengl;

import java.net.URL;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PMatrix2D;
import processing.core.PMatrix3D;
import processing.core.PVector;

/**
 * Browser-safe shader placeholder.
 *
 * <p>p5.js-backed Processing sketches can compile against Processing's shader
 * API, but this runtime does not execute Processing OpenGL shaders.</p>
 */
public class PShader implements PConstants {
  public PShader() {
  }


  public PShader(PApplet parent) {
  }


  public PShader(PApplet parent, String vertFilename, String fragFilename) {
  }


  public PShader(PApplet parent, URL vertURL, URL fragURL) {
  }


  public PShader(PApplet parent, String[] vertSource, String[] fragSource) {
  }


  public void set(String name, int x) {
  }


  public void set(String name, int x, int y) {
  }


  public void set(String name, int x, int y, int z) {
  }


  public void set(String name, int x, int y, int z, int w) {
  }


  public void set(String name, float x) {
  }


  public void set(String name, float x, float y) {
  }


  public void set(String name, float x, float y, float z) {
  }


  public void set(String name, float x, float y, float z, float w) {
  }


  public void set(String name, PVector vec) {
  }


  public void set(String name, boolean x) {
  }


  public void set(String name, boolean x, boolean y) {
  }


  public void set(String name, boolean x, boolean y, boolean z) {
  }


  public void set(String name, boolean x, boolean y, boolean z, boolean w) {
  }


  public void set(String name, int[] vec) {
  }


  public void set(String name, int[] vec, int ncoords) {
  }


  public void set(String name, float[] vec) {
  }


  public void set(String name, float[] vec, int ncoords) {
  }


  public void set(String name, boolean[] vec) {
  }


  public void set(String name, boolean[] boolvec, int ncoords) {
  }


  public void set(String name, PMatrix2D mat) {
  }


  public void set(String name, PMatrix3D mat) {
  }


  public void set(String name, PMatrix3D mat, boolean use3x3) {
  }


  public void set(String name, PImage tex) {
  }
}
