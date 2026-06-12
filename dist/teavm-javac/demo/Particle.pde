class Particle {
  int id;
  float x;
  float y;
  float vx;
  float vy;
  float life;
  float size;
  float offset;

  Particle(int nextId) {
    id = nextId;
    offset = random(1000);
  }

  void reset(float nextX, float nextY, float angle, float speed) {
    x = nextX;
    y = nextY;
    vx = cos(angle) * speed;
    vy = sin(angle) * speed;
    life = random(0.3, 1.0);
    size = random(2.0, 6.5);
  }

  void update() {
    float angle = field.angleAt(x, y, offset);
    float ax = cos(angle) * 0.075;
    float ay = sin(angle) * 0.075;
    float dx = attractX - x;
    float dy = attractY - y;
    float d = max(24, sqrt(dx * dx + dy * dy));

    if (mode == MODE_GRAVITY) {
      ax += dx / d * 0.22;
      ay += dy / d * 0.22;
    } else if (mode == MODE_VORTEX) {
      ax += -dy / d * 0.24;
      ay += dx / d * 0.24;
    } else {
      ax += dx / d * 0.025;
      ay += dy / d * 0.025;
    }

    if (mousePressed) {
      float force = mouseButton == LEFT ? 0.75 : -0.55;
      ax += dx / d * force;
      ay += dy / d * force;
    }

    vx = (vx + ax) * 0.974;
    vy = (vy + ay) * 0.974;
    float speed = sqrt(vx * vx + vy * vy);
    if (speed > 7.0) {
      vx = vx / speed * 7.0;
      vy = vy / speed * 7.0;
    }

    x += vx;
    y += vy;
    life += 0.006;
    wrap();
  }

  void wrap() {
    if (x < -20) {
      x = width + 20;
    } else if (x > width + 20) {
      x = -20;
    }
    if (y < -20) {
      y = height + 20;
    } else if (y > height + 20) {
      y = -20;
    }
  }

  void render() {
    float speed = sqrt(vx * vx + vy * vy);
    float h = palette.hue(id * 1.7 + speed * 12 + life * 18);
    float tail = 4 + speed * 4;
    stroke(h, 70, 100, 42);
    strokeWeight(max(1.0, size * 0.35));
    line(x - vx * tail, y - vy * tail, x, y);

    noStroke();
    fill(h, 68, 96, 78);
    ellipse(x, y, size + speed * 0.7, size + speed * 0.7);
    fill((h + 42) % 360, 28, 100, 40);
    ellipse(x, y, size * 0.42, size * 0.42);
  }
}
