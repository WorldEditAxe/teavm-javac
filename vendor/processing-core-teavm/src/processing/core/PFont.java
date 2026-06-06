/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

package processing.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Browser-safe PFont implementation for TeaVM.
 *
 * <p>Desktop Processing builds this class around platform font rasterization.
 * The TeaVM runtime cannot depend on desktop UI APIs, so this version keeps
 * the Processing API surface and .vlw bitmap loading while delegating native
 * text drawing to the active renderer.</p>
 */
public class PFont implements PConstants {
  protected int glyphCount;
  protected Glyph[] glyphs;
  protected String name;
  protected String psname;
  protected int size;
  protected int density = 1;
  protected boolean smooth;
  protected int ascent;
  protected int descent;
  protected int[] ascii;
  protected boolean lazy;
  protected Object nativeFont;
  protected boolean stream;
  protected boolean subsetting;


  public PFont() {
    init("sans-serif", 12, true, null, false, 1);
  }


  public PFont(String name, float size, boolean smooth) {
    this(name, size, smooth, null, false, 1);
  }


  public PFont(String name, float size, boolean smooth, char[] charset) {
    this(name, size, smooth, charset, false, 1);
  }


  public PFont(String name, float size, boolean smooth, char[] charset,
               boolean stream, int density) {
    init(name, Math.max(1, Math.round(size)), smooth, charset, stream, density);
  }


  public PFont(InputStream input) throws IOException {
    DataInputStream is = new DataInputStream(input);

    glyphCount = is.readInt();
    int version = is.readInt();
    size = Math.max(1, is.readInt());
    is.readInt();
    ascent = is.readInt();
    descent = is.readInt();

    glyphs = new Glyph[glyphCount];
    ascii = new int[128];
    Arrays.fill(ascii, -1);

    for (int i = 0; i < glyphCount; i++) {
      Glyph glyph = new Glyph(is);
      if (glyph.value < 128) {
        ascii[glyph.value] = i;
      }
      glyph.index = i;
      glyphs[i] = glyph;
    }

    if ((ascent == 0) && (descent == 0)) {
      ascent = Math.max(1, Math.round(size * 0.8f));
      descent = Math.max(1, size - ascent);
    }

    for (Glyph glyph : glyphs) {
      glyph.readBitmap(is);
    }

    if (version >= 10) {
      name = is.readUTF();
      psname = is.readUTF();
    }
    if (version == 11) {
      smooth = is.readBoolean();
    }
    if (name == null || name.length() == 0) {
      name = "sans-serif";
    }
    if (psname == null || psname.length() == 0) {
      psname = name;
    }
  }


  private void init(String name, int size, boolean smooth, char[] charset,
                    boolean stream, int density) {
    this.name = (name == null || name.length() == 0) ? "sans-serif" : name;
    this.psname = this.name;
    this.size = Math.max(1, size);
    this.smooth = smooth;
    this.stream = stream;
    this.density = Math.max(1, density);
    this.ascent = Math.max(1, Math.round(this.size * 0.8f));
    this.descent = Math.max(1, this.size - this.ascent);

    ascii = new int[128];
    Arrays.fill(ascii, -1);

    if (charset == null) {
      lazy = true;
      glyphs = new Glyph[32];
      glyphCount = 0;
    } else {
      char[] sortedCharset = Arrays.copyOf(charset, charset.length);
      Arrays.sort(sortedCharset);
      glyphs = new Glyph[Math.max(1, sortedCharset.length)];
      glyphCount = 0;
      for (char c : sortedCharset) {
        addGlyph(c);
      }
    }
  }


  public void save(OutputStream output) throws IOException {
    DataOutputStream os = new DataOutputStream(output);
    os.writeInt(glyphCount);
    os.writeInt(11);
    os.writeInt(size);
    os.writeInt(0);
    os.writeInt(ascent);
    os.writeInt(descent);

    for (int i = 0; i < glyphCount; i++) {
      glyphs[i].writeHeader(os);
    }
    for (int i = 0; i < glyphCount; i++) {
      glyphs[i].writeBitmap(os);
    }

    os.writeUTF(name == null ? "" : name);
    os.writeUTF(psname == null ? "" : psname);
    os.writeBoolean(smooth);
    os.flush();
  }


  protected void addGlyph(char c) {
    Glyph glyph = new Glyph(c);
    if (glyphCount == glyphs.length) {
      glyphs = (Glyph[]) PApplet.expand(glyphs);
    }
    glyph.index = glyphCount;
    glyphs[glyphCount] = glyph;
    if (glyph.value < 128) {
      ascii[glyph.value] = glyphCount;
    }
    glyphCount++;
  }


  public String getName() {
    return name;
  }


  public String getPostScriptName() {
    return psname;
  }


  public void setNative(Object font) {
    nativeFont = font;
  }


  @Deprecated
  public Object getFont() {
    return nativeFont;
  }


  public Object getNative() {
    return subsetting ? null : nativeFont;
  }


  public int getSize() {
    return size;
  }


  public int getDefaultSize() {
    return Math.max(1, size / density);
  }


  public boolean isSmooth() {
    return smooth;
  }


  public boolean isStream() {
    return stream;
  }


  public void setSubsetting() {
    subsetting = true;
  }


  public Object findNative() {
    return nativeFont;
  }


  public Glyph getGlyph(char c) {
    int index = index(c);
    return (index == -1) ? null : glyphs[index];
  }


  protected int index(char c) {
    int index = indexActual(c);
    if (index == -1 && lazy) {
      addGlyph(c);
      index = glyphCount - 1;
    }
    return index;
  }


  protected int indexActual(char c) {
    if (glyphCount == 0) return -1;
    if (c < 128) return ascii[c];

    for (int i = 0; i < glyphCount; i++) {
      if (glyphs[i].value == c) return i;
    }
    return -1;
  }


  public float kern(char a, char b) {
    return 0;
  }


  public float ascent() {
    return ((float) ascent / (float) size);
  }


  public float descent() {
    return ((float) descent / (float) size);
  }


  public float width(char c) {
    if (c == ' ') return 0.33f;

    int cc = index(c);
    if (cc == -1) return 0.6f;

    return ((float) glyphs[cc].setWidth / (float) size);
  }


  public int getGlyphCount() {
    return glyphCount;
  }


  public Glyph getGlyph(int i) {
    return glyphs[i];
  }


  public PShape getShape(char ch) {
    return getShape(ch, 0);
  }


  public PShape getShape(char ch, float detail) {
    throw new UnsupportedOperationException("Vector font outlines are not available in TeaVM");
  }


  static final char[] EXTRA_CHARS = {
    0x0080, 0x0081, 0x0082, 0x0083, 0x0084, 0x0085, 0x0086, 0x0087,
    0x0088, 0x0089, 0x008A, 0x008B, 0x008C, 0x008D, 0x008E, 0x008F,
    0x0090, 0x0091, 0x0092, 0x0093, 0x0094, 0x0095, 0x0096, 0x0097,
    0x0098, 0x0099, 0x009A, 0x009B, 0x009C, 0x009D, 0x009E, 0x009F,
    0x00A0, 0x00A1, 0x00B0, 0x00B1, 0x00BF, 0x00C0, 0x00C1, 0x00C2,
    0x00C3, 0x00C4, 0x00C5, 0x00C6, 0x00C7, 0x00C8, 0x00C9, 0x00CA,
    0x00CB, 0x00CC, 0x00CD, 0x00CE, 0x00CF, 0x00D1, 0x00D2, 0x00D3,
    0x00D4, 0x00D5, 0x00D6, 0x00D8, 0x00D9, 0x00DA, 0x00DB, 0x00DC,
    0x00DD, 0x00DF, 0x00E0, 0x00E1, 0x00E2, 0x00E3, 0x00E4, 0x00E5,
    0x00E6, 0x00E7, 0x00E8, 0x00E9, 0x00EA, 0x00EB, 0x00EC, 0x00ED,
    0x00EE, 0x00EF, 0x00F1, 0x00F2, 0x00F3, 0x00F4, 0x00F5, 0x00F6,
    0x00F8, 0x00F9, 0x00FA, 0x00FB, 0x00FC, 0x00FD, 0x00FF
  };

  static public char[] CHARSET;
  static {
    CHARSET = new char[126 - 33 + 1 + EXTRA_CHARS.length];
    int index = 0;
    for (int i = 33; i <= 126; i++) {
      CHARSET[index++] = (char) i;
    }
    for (char extraChar : EXTRA_CHARS) {
      CHARSET[index++] = extraChar;
    }
  }


  static public String[] list() {
    return new String[] { "sans-serif", "serif", "monospace" };
  }


  public class Glyph {
    public PImage image;
    public int value;
    public int height;
    public int width;
    public int index;
    public int setWidth;
    public int topExtent;
    public int leftExtent;


    public Glyph() {
      index = -1;
    }


    public Glyph(DataInputStream is) throws IOException {
      index = -1;
      readHeader(is);
    }


    protected void readHeader(DataInputStream is) throws IOException {
      value = is.readInt();
      height = is.readInt();
      width = is.readInt();
      setWidth = is.readInt();
      topExtent = is.readInt();
      leftExtent = is.readInt();
      is.readInt();

      if (value == 'd' && ascent == 0) {
        ascent = topExtent;
      }
      if (value == 'p' && descent == 0) {
        descent = -topExtent + height;
      }
    }


    protected void writeHeader(DataOutputStream os) throws IOException {
      os.writeInt(value);
      os.writeInt(height);
      os.writeInt(width);
      os.writeInt(setWidth);
      os.writeInt(topExtent);
      os.writeInt(leftExtent);
      os.writeInt(0);
    }


    protected void readBitmap(DataInputStream is) throws IOException {
      image = new PImage(width, height, ALPHA);
      int bitmapSize = width * height;
      byte[] temp = new byte[bitmapSize];
      is.readFully(temp);

      int[] pixels = image.pixels;
      for (int i = 0; i < bitmapSize; i++) {
        pixels[i] = temp[i] & 0xff;
      }
    }


    protected void writeBitmap(DataOutputStream os) throws IOException {
      int[] pixels = image == null ? null : image.pixels;
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int value = pixels == null ? 0 : pixels[y * width + x];
          os.write(value & 0xff);
        }
      }
    }


    protected Glyph(char c) {
      value = c;
      height = Math.max(1, size);
      setWidth = Math.max(1, Math.round(size * (c == ' ' ? 0.33f : 0.6f)));
      width = Math.max(1, setWidth);
      topExtent = ascent;
      leftExtent = 0;
      image = new PImage(width, height, ALPHA);
    }
  }
}
