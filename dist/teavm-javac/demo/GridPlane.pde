class GridPlane {
  float span;
  float step;

  GridPlane(float nextSpan, float nextStep) {
    span = nextSpan;
    step = nextStep;
  }

  void render() {
    float y = 132 + sin(pulse * 0.4) * 8;
    strokeWeight(1);
    for (float p = -span; p <= span; p += step) {
      float alpha = 12 + 18 * (1.0 - abs(p) / span);
      stroke(210, 28, 72, alpha);
      line(-span, y, p, span, y, p);
      stroke(186, 34, 82, alpha);
      line(p, y, -span, p, y, span);
    }

    strokeWeight(2);
    stroke(palette.hue(28), 58, 100, 36);
    line(-span, y, 0, span, y, 0);
    stroke(palette.hue(190), 48, 100, 34);
    line(0, y, -span, 0, y, span);
  }
}
