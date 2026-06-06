public class VingetteShader implements Shader {
  protected float intensity;

  public VingetteShader() {
    this.intensity = 0.5f;
  }

  public VingetteShader(float intensity) {
    this.intensity = constrain(intensity, 0, 1);
  }

  public color[] processFramebuffer(color[] fb) {
    for (int i = 0; i < fb.length; i++) {
      float x = i % width;
      float y = i / width;
      float baseDistance = (float) Math.sqrt(Math.pow(x - width / 2, 2) + Math.pow(y - height / 2, 2));
      float maxDistance = (float) Math.sqrt(Math.pow(width / 2, 2) + Math.pow(height / 2, 2));
      float intensity = map(baseDistance, 0, maxDistance, 0, 1) * this.intensity;
      float adjustedDistance = baseDistance * intensity * 2;
      fb[i] = lerpColor(fb[i], color(0), Math.min(intensity, 1));
    }
    return fb;
  }
}

public class MotionBlurShader implements Shader {
  protected color bg;
  protected float intensity;
  protected final float SNAP_TOL = 0.1;

  public MotionBlurShader(color bg, float intensity) {
    this.bg = bg;
    this.intensity = constrain(1 - intensity, 0, 1);
  }

  public color[] processFramebuffer(color[] fb) {
    for (int i = 0; i < fb.length; i++) {
      color curr = fb[i];

      float rDiff = abs(red(curr) - red(bg));
      float gDiff = abs(green(curr) - green(bg));
      float bDiff = abs(blue(curr) - blue(bg));
      float avgDiff = (rDiff + gDiff + bDiff) / (255.0 * 3);

      fb[i] = (avgDiff < SNAP_TOL) ? bg : lerpColor(curr, bg, intensity);
    }
    return fb;
  }
}

public class VibranceShader implements Shader {
  protected float intensity;

  public VibranceShader(float intensity) {
    this.intensity = constrain(intensity, 0, 1);
  }

  public color[] processFramebuffer(color[] fb) {
    for (int i = 0; i < fb.length; i++) {
      color curr = fb[i];
      float r = red(curr);
      float g = green(curr);
      float b = blue(curr);

      float avg = (r + g + b) / 3;
      float rDiff = r - avg;
      float gDiff = g - avg;
      float bDiff = b - avg;

      fb[i] = color(r + rDiff * intensity, g + gDiff * intensity, b + bDiff * intensity);
    }
    return fb;
  }
}

public class WarpEffectShader extends MotionBlurShader implements Shader {
  private long start;
  private long end;
  private double startIntensity;

  public WarpEffectShader(color bg, long duration, double startIntensity) {
    super(bg, 1);
    this.start = System.currentTimeMillis();
    this.end = this.start + duration;
    this.startIntensity = startIntensity;
  }


  public WarpEffectShader(color bg, long duration) {
    super(bg, 1);
    this.start = System.currentTimeMillis();
    this.end = this.start + duration;
    this.startIntensity = 0;
  }

  public color[] processFramebuffer(color[] fb) {
    this.intensity = 1 - (float) startIntensity -  (float) ((1 - startIntensity) * Math.min((float) (System.currentTimeMillis() - this.start) / (float) (this.end - this.start), 1));
    return super.processFramebuffer(fb);
  }
}

Map<Integer, float[][]> backdropCache = new HashMap();

public void createLightingBackdrop(int sX, int sY, int radius, color light) {
  createLightingBackdrop(sX, sY, radius, light, 0.35);
}

public void createLightingBackdrop(int sX, int sY, int radius, color light, float intensityMulti) {
  float[][] backdrop = backdropCache.get(radius);
  if (backdrop == null) {
    backdrop = new float[radius * 2][radius * 2];
    int cX = radius - 1, cY = radius - 1;
    for (int x = 0; x < radius * 2; x++) {
      for (int y = 0; y < radius * 2; y++) {
        float distance = dist(cX, cY, x, y);
        float normalized = distance / radius;
        if (normalized > 1.0) continue;
        float intensity = 1.0 - (normalized * normalized);
        backdrop[x][y] = constrain(intensity, 0, 1);
      }
    }
    backdropCache.put(radius, backdrop);
  }
  
  loadPixels();
  for (int x = 0; x < radius * 2; x++) {
    for (int y = 0; y < radius * 2; y++) {
      // check for clip
      int screenX = sX + x - radius, screenY = sY + y - radius;
      if (screenX < 0 || screenX >= width || screenY < 0 || screenY >= height) {
        continue;
      }
      int index = (screenY * width) + screenX;
      float intensity = backdrop[x][y];
      pixels[index] = lerpColor(pixels[index], light, intensity * intensityMulti);
    }
  }
  updatePixels();
}

PVector transformPoint(float x, float y) {
  PMatrix m = getMatrix();
  PVector transformed = new PVector();
  m.mult(new PVector(x, y), transformed);
  return transformed;
}

public static class ShaderRemover {
  protected Shader shader;
  protected Shaders hook;

  public ShaderRemover(Shader shader, Shaders hook) {
    this.shader = shader;
    this.hook = hook;
  }

  public void remove() {
    hook.removeShader(shader);
  }
}
