class StarField {
  float[] xs;
  float[] ys;
  float[] zs;
  float[] bright;
  float span;

  StarField(int count, float nextSpan) {
    span = nextSpan;
    xs = new float[count];
    ys = new float[count];
    zs = new float[count];
    bright = new float[count];
    for (int i = 0; i < count; i++) {
      xs[i] = random(-span, span);
      ys[i] = random(-span * 0.55, span * 0.45);
      zs[i] = random(-span, span);
      bright[i] = random(28, 100);
    }
  }

  void render() {
    strokeWeight(2);
    for (int i = 0; i < xs.length; i++) {
      float twinkle = bright[i] + sin(pulse * 2.2 + i * 0.73) * 16;
      stroke(palette.hue(i * 3.1), 18, constrain(twinkle, 18, 100), 36);
      point(xs[i], ys[i], zs[i]);
    }
  }
}
