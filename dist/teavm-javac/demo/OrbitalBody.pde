class OrbitalBody {
  int id;
  float radius;
  float bodySize;
  float phase;
  float speed;
  float elevation;
  float wobble;
  float hueOffset;
  float angle;

  OrbitalBody(int nextId) {
    id = nextId;
    radius = 78 + id * 26 + random(-8, 8);
    bodySize = 9 + (id % 5) * 3.3 + random(0, 2.4);
    phase = random(TWO_PI);
    speed = random(0.006, 0.018) * (id % 2 == 0 ? 1 : -1);
    elevation = random(-88, 88);
    wobble = random(14, 54);
    hueOffset = id * 18.5 + random(30);
    angle = phase;
  }

  void update() {
    angle += speed * speedFactor();
  }

  float x() {
    return cos(angle) * radius;
  }

  float y() {
    return elevation + sin(angle * 1.7 + phase) * wobble;
  }

  float z() {
    return sin(angle) * radius;
  }

  void render() {
    pushMatrix();
    translate(x(), y(), z());
    rotateY(-angle + pulse * 0.5);
    rotateX(phase + pulse * 0.18);

    noStroke();
    fill(palette.hue(hueOffset), 72, 96, 94);
    sphere(bodySize);

    stroke(palette.hue(hueOffset + 70), 48, 100, 42);
    strokeWeight(1.2);
    noFill();
    beginShape();
    for (int i = 0; i <= 32; i++) {
      float t = TWO_PI * i / 32.0;
      vertex(cos(t) * bodySize * 1.9, sin(t) * bodySize * 1.9, sin(t + pulse) * bodySize * 0.35);
    }
    endShape();

    if (id % 3 == 0) {
      rotateY(HALF_PI);
      stroke(palette.hue(hueOffset + 126), 42, 100, 30);
      beginShape();
      for (int i = 0; i <= 32; i++) {
        float t = TWO_PI * i / 32.0;
        vertex(cos(t) * bodySize * 2.35, sin(t) * bodySize * 2.35, 0);
      }
      endShape();
    }
    popMatrix();
  }
}
