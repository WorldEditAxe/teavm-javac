class Drone {
  int id;
  float radius;
  float angle;
  float speed;
  float yBase;
  float yRange;
  float size;
  float hueOffset;

  Drone(int nextId) {
    id = nextId;
    radius = random(110, 310);
    angle = random(TWO_PI);
    speed = random(0.006, 0.025) * (random(1) > 0.45 ? 1 : -1);
    yBase = random(-130, 126);
    yRange = random(16, 84);
    size = random(5.5, 13.5);
    hueOffset = random(360);
  }

  void update() {
    angle += speed * speedFactor();
  }

  void render() {
    float x = cos(angle) * radius;
    float z = sin(angle) * radius;
    float y = yBase + sin(angle * 2.0 + id) * yRange;
    float tailAngle = angle - speed * 24;
    float tailX = cos(tailAngle) * radius;
    float tailZ = sin(tailAngle) * radius;
    float tailY = yBase + sin(tailAngle * 2.0 + id) * yRange;

    stroke(palette.hue(hueOffset + pulse * 20), 58, 100, 32);
    strokeWeight(1.3);
    line(tailX, tailY, tailZ, x, y, z);

    pushMatrix();
    translate(x, y, z);
    rotateY(-angle);
    rotateZ(sin(pulse + id) * 0.45);
    noStroke();
    fill(palette.hue(hueOffset), 66, 100, 92);
    box(size * 2.3, size * 0.72, size * 1.25);
    fill(palette.hue(hueOffset + 90), 42, 100, 70);
    box(size * 0.42, size * 2.0, size * 0.42);
    popMatrix();
  }
}
