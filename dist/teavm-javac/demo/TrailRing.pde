class TrailRing {
  int id;
  float radius;
  float tilt;
  float spin;
  float zSquash;

  TrailRing(int nextId) {
    id = nextId;
    radius = 92 + id * 38;
    tilt = random(-0.8, 0.8);
    spin = random(TWO_PI);
    zSquash = random(0.38, 0.86);
  }

  void update() {
    spin += (0.004 + id * 0.0012) * speedFactor();
  }

  void render() {
    pushMatrix();
    rotateX(tilt + sin(pulse * 0.25 + id) * 0.1);
    rotateY(spin);
    noFill();
    strokeWeight(id % 2 == 0 ? 1.4 : 0.9);
    stroke(palette.hue(id * 24 + pulse * 12), 52, 100, 18 + id * 2);
    beginShape();
    for (int i = 0; i <= 120; i++) {
      float t = TWO_PI * i / 120.0;
      float ripple = sin(t * 4 + pulse * 1.8 + id) * (2.0 + id * 0.5);
      vertex(cos(t) * (radius + ripple), sin(t * 2 + id) * 8, sin(t) * radius * zSquash);
    }
    endShape();
    popMatrix();
  }
}
