class FieldGrid {
  int step;

  FieldGrid(int nextStep) {
    step = nextStep;
  }

  float angleAt(float x, float y, float offset) {
    float nx = x * 0.0045;
    float ny = y * 0.0045;
    float n = noise(nx + offset * 0.01, ny - offset * 0.01, pulse * 0.28);
    float radial = atan2(y - height * 0.5, x - width * 0.5);
    return n * TWO_PI * 2.2 + radial * 0.28 + spin;
  }

  void render() {
    strokeWeight(1);
    for (int y = step / 2; y < height; y += step) {
      for (int x = step / 2; x < width; x += step) {
        float angle = angleAt(x, y, 4);
        float len = 12 + 10 * noise(x * 0.01, y * 0.01, pulse);
        float h = palette.hue(x * 0.08 + y * 0.05);
        stroke(h, 34, 72, 20);
        line(x - cos(angle) * len * 0.5, y - sin(angle) * len * 0.5,
             x + cos(angle) * len * 0.5, y + sin(angle) * len * 0.5);
      }
    }
  }
}
