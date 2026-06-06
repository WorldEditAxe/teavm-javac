/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

package processing.core;

import processing.data.XML;

/**
 * TeaVM placeholder for Processing's desktop SVG parser.
 *
 * <p>The full implementation depends on desktop geometry and font APIs. The p5.js
 * renderer does not currently route loadShape() through this class, so keeping a
 * minimal type here avoids leaking desktop UI APIs into the browser core jar.</p>
 */
public class PShapeSVG extends PShape {
  XML element;


  public PShapeSVG(XML svg) {
    throw unsupported();
  }


  protected PShapeSVG(PShapeSVG parent, XML properties, boolean parseKids) {
    throw unsupported();
  }


  private static UnsupportedOperationException unsupported() {
    return new UnsupportedOperationException("SVG loading is not available in the TeaVM Processing runtime");
  }
}
