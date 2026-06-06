/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

package processing.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/**
 * Browser-safe placeholder for Processing's OpenGL binding layer.
 *
 * <p>The TeaVM p5.js runtime does not expose JOGL. This class keeps Processing's
 * public API signatures available to sketches without pulling desktop OpenGL or
 * desktop UI classes into the browser core jar.</p>
 */
public class PGL {
  public static final int POINTS = 0x0000;
  public static final int LINES = 0x0001;
  public static final int TRIANGLES = 0x0004;
  public static final int TEXTURE0 = 0x84C0;
  public static final int TEXTURE_2D = 0x0DE1;
  public static final int ARRAY_BUFFER = 0x8892;
  public static final int ELEMENT_ARRAY_BUFFER = 0x8893;
  public static final int STATIC_DRAW = 0x88E4;
  public static final int DYNAMIC_DRAW = 0x88E8;
  public static final int STREAM_DRAW = 0x88E0;
  public static final int STREAM_READ = 0x88E1;
  public static final int READ_ONLY = 0x88B8;
  public static final int WRITE_ONLY = 0x88B9;
  public static final int READ_WRITE = 0x88BA;

  public static final int SIZEOF_BYTE = 1;
  public static final int SIZEOF_SHORT = 2;
  public static final int SIZEOF_INT = 4;
  public static final int SIZEOF_FLOAT = 4;
  public static final int SIZEOF_INDEX = SIZEOF_SHORT;


  public Object getNative() {
    return null;
  }


  static public ByteBuffer allocateByteBuffer(int size) {
    return ByteBuffer.allocate(size).order(ByteOrder.nativeOrder());
  }


  static public ShortBuffer allocateShortBuffer(int size) {
    return allocateByteBuffer(size * SIZEOF_SHORT).asShortBuffer();
  }


  static public IntBuffer allocateIntBuffer(int size) {
    return allocateByteBuffer(size * SIZEOF_INT).asIntBuffer();
  }


  static public FloatBuffer allocateFloatBuffer(int size) {
    return allocateByteBuffer(size * SIZEOF_FLOAT).asFloatBuffer();
  }
}
