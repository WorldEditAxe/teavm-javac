class BurstDot {
  float x;
  float y;
  float vx;
  float vy;
  float age;
  float maxAge;
  boolean active = false;

  void set(float nextX, float nextY, float angle, float speed) {
    x = nextX;
    y = nextY;
    vx = cos(angle) * speed;
    vy = sin(angle) * speed;
    age = 0;
    maxAge = random(34, 72);
    active = true;
  }

  void update() {
    if (!active) {
      return;
    }
    x += vx;
    y += vy;
    vx *= 0.962;
    vy *= 0.962;
    age++;
    if (age > maxAge) {
      active = false;
    }
  }

  void render() {
    if (!active) {
      return;
    }
    float t = 1.0 - age / maxAge;
    float h = palette.hue(age * 2.6 + maxAge);
    noStroke();
    fill(h, 80, 100, 75 * t);
    ellipse(x, y, 3 + 16 * t, 3 + 16 * t);
    stroke((h + 90) % 360, 48, 100, 45 * t);
    strokeWeight(1.2);
    line(x, y, x - vx * 3.5, y - vy * 3.5);
  }
}
