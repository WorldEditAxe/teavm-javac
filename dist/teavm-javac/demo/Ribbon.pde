class Ribbon {
  final int POINTS = 36;
  float[] xs = new float[POINTS];
  float[] ys = new float[POINTS];
  int head = 0;
  int id;

  Ribbon(int nextId) {
    id = nextId;
    clear();
  }

  void clear() {
    for (int i = 0; i < POINTS; i++) {
      xs[i] = width * 0.5;
      ys[i] = height * 0.5;
    }
    head = 0;
  }

  void follow(float x, float y) {
    head = (head + 1) % POINTS;
    xs[head] = x;
    ys[head] = y;
  }

  void render() {
    noFill();
    strokeWeight(1.5 + id % 3);
    stroke(palette.hue(id * 17 + frameCount * 0.2), 42, 100, 16);
    beginShape();
    for (int i = 0; i < POINTS; i++) {
      int idx = (head + i + 1) % POINTS;
      vertex(xs[idx], ys[idx]);
    }
    endShape();

    strokeWeight(0.75);
    stroke(palette.hue(id * 17 + 80), 70, 95, 30);
    int tail = (head + 1) % POINTS;
    line(xs[tail], ys[tail], xs[head], ys[head]);
  }
}
