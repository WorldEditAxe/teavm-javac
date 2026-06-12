final int PARTICLE_COUNT = 340;
final int RIBBON_COUNT = 18;
final int BURST_COUNT = 96;
final int MODE_FLOW = 0;
final int MODE_GRAVITY = 1;
final int MODE_VORTEX = 2;

Particle[] particles = new Particle[PARTICLE_COUNT];
Ribbon[] ribbons = new Ribbon[RIBBON_COUNT];
BurstDot[] bursts = new BurstDot[BURST_COUNT];
Palette palette = new Palette();
FieldGrid field = new FieldGrid(48);

float attractX;
float attractY;
float pulse = 0;
float spin = 0;
float cameraShake = 0;
float baseHue = 188;
int mode = MODE_FLOW;
int burstIndex = 0;
boolean paused = false;
boolean showField = true;
boolean trails = true;
String inputLabel = "move";

void setup() {
  size(960, 620);
  smooth(4);
  frameRate(60);
  colorMode(HSB, 360, 100, 100, 100);
  textAlign(LEFT, BASELINE);
  attractX = width * 0.5;
  attractY = height * 0.5;

  for (int i = 0; i < particles.length; i++) {
    particles[i] = new Particle(i);
    particles[i].reset(random(width), random(height), random(TWO_PI), random(0.6, 2.8));
  }
  for (int i = 0; i < ribbons.length; i++) {
    ribbons[i] = new Ribbon(i);
  }
  for (int i = 0; i < bursts.length; i++) {
    bursts[i] = new BurstDot();
  }
}

void draw() {
  if (!paused) {
    pulse += 0.018;
    spin += 0.011;
    attractX = lerp(attractX, mouseX, 0.065);
    attractY = lerp(attractY, mouseY, 0.065);
    updateParticles();
    updateRibbons();
    updateBursts();
    cameraShake *= 0.86;
  }

  drawBackdrop();
  pushMatrix();
  if (cameraShake > 0.02) {
    translate(random(-cameraShake, cameraShake), random(-cameraShake, cameraShake));
  }
  if (showField) {
    field.render();
  }
  drawOrbitRings();
  drawRibbons();
  drawBursts();
  drawParticles();
  drawBeacon();
  popMatrix();
  drawHud();
}

void updateParticles() {
  for (int i = 0; i < particles.length; i++) {
    particles[i].update();
  }
}

void updateRibbons() {
  for (int i = 0; i < ribbons.length; i++) {
    int source = (i * 19 + frameCount / 3) % particles.length;
    ribbons[i].follow(particles[source].x, particles[source].y);
  }
}

void updateBursts() {
  for (int i = 0; i < bursts.length; i++) {
    bursts[i].update();
  }
}

void drawBackdrop() {
  if (trails) {
    noStroke();
    fill(222, 34, 6, 18);
    rect(0, 0, width, height);
  } else {
    background(222, 34, 6);
  }

  stroke(216, 18, 22, 32);
  strokeWeight(1);
  for (int x = 0; x <= width; x += 40) {
    line(x, 0, x, height);
  }
  for (int y = 20; y <= height; y += 40) {
    line(0, y, width, y);
  }

  noStroke();
  for (int i = 0; i < 6; i++) {
    float r = 210 + i * 70;
    float h = palette.hue(i * 23 + frameCount * 0.08);
    fill(h, 32, 16 + i * 3, 8);
    ellipse(width * 0.5, height * 0.5, r, r * 0.72);
  }
}

void drawOrbitRings() {
  pushMatrix();
  translate(width * 0.5, height * 0.5);
  rotate(spin * 0.38);
  noFill();
  for (int i = 0; i < 8; i++) {
    float w = 150 + i * 88 + sin(pulse + i) * 20;
    float h = 84 + i * 52 + cos(pulse * 0.8 + i) * 15;
    stroke(palette.hue(i * 19), 42, 92, 18);
    strokeWeight(i % 3 == 0 ? 2.0 : 1.0);
    ellipse(0, 0, w, h);
  }
  popMatrix();
}

void drawParticles() {
  for (int i = 0; i < particles.length; i++) {
    particles[i].render();
  }
}

void drawRibbons() {
  for (int i = 0; i < ribbons.length; i++) {
    ribbons[i].render();
  }
}

void drawBursts() {
  for (int i = 0; i < bursts.length; i++) {
    bursts[i].render();
  }
}

void drawBeacon() {
  pushMatrix();
  translate(attractX, attractY);
  rotate(-spin);
  noFill();
  stroke(palette.hue(120), 78, 100, mousePressed ? 88 : 50);
  strokeWeight(2);
  float r = mousePressed ? 58 : 38;
  ellipse(0, 0, r, r);
  line(-18, 0, 18, 0);
  line(0, -18, 0, 18);
  rotate(spin * 3.0);
  stroke(palette.hue(176), 52, 100, 46);
  rect(-24, -24, 48, 48, 4);
  popMatrix();
}

void spawnBurst(float x, float y, float power) {
  for (int i = 0; i < 24; i++) {
    float angle = TWO_PI * i / 24.0 + random(-0.08, 0.08);
    bursts[burstIndex].set(x, y, angle, power * random(0.65, 1.25));
    burstIndex = (burstIndex + 1) % bursts.length;
  }
}

void mouseMoved() {
  inputLabel = "mouse " + mouseX + ", " + mouseY;
}

void mousePressed() {
  inputLabel = mouseButton == LEFT ? "burst" : "alternate burst";
  cameraShake = 4.8;
  spawnBurst(mouseX, mouseY, mouseButton == LEFT ? 8.5 : 5.4);
}

void mouseDragged() {
  inputLabel = "drawing force";
  if (frameCount % 3 == 0) {
    spawnBurst(mouseX, mouseY, 3.5);
  }
}

void mouseReleased() {
  inputLabel = "release";
}

void keyPressed() {
  if (key == '1') {
    mode = MODE_FLOW;
    inputLabel = "mode flow";
  } else if (key == '2') {
    mode = MODE_GRAVITY;
    inputLabel = "mode gravity";
  } else if (key == '3') {
    mode = MODE_VORTEX;
    inputLabel = "mode vortex";
  } else if (key == ' ') {
    paused = !paused;
    inputLabel = paused ? "paused" : "running";
  } else if (key == 'f' || key == 'F') {
    showField = !showField;
    inputLabel = showField ? "field on" : "field off";
  } else if (key == 't' || key == 'T') {
    trails = !trails;
    inputLabel = trails ? "trails on" : "trails off";
  } else if (key == 'p' || key == 'P') {
    baseHue = (baseHue + 41) % 360;
    inputLabel = "palette shifted";
  } else if (key == 'r' || key == 'R') {
    resetShowcase();
  }
}

void resetShowcase() {
  background(222, 34, 6);
  for (int i = 0; i < particles.length; i++) {
    particles[i].reset(random(width), random(height), random(TWO_PI), random(0.6, 2.8));
  }
  for (int i = 0; i < ribbons.length; i++) {
    ribbons[i].clear();
  }
  for (int i = 0; i < bursts.length; i++) {
    bursts[i].active = false;
  }
  inputLabel = "reset";
}

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

class Palette {
  float hue(float offset) {
    return (baseHue + offset + sin(pulse * 0.9 + offset * 0.04) * 22 + 360) % 360;
  }

  String modeName() {
    if (mode == MODE_GRAVITY) {
      return "gravity";
    }
    if (mode == MODE_VORTEX) {
      return "vortex";
    }
    return "flow";
  }
}

void drawHud() {
  noStroke();
  fill(222, 28, 5, 82);
  rect(14, 14, 390, 142, 7);
  fill(palette.hue(24), 38, 100, 96);
  textSize(13);
  text("Kinetic 2D Processing Showcase", 28, 39);
  fill(0, 0, 100, 78);
  textSize(12);
  text("mode " + palette.modeName() + "   particles " + particles.length + "   ribbons " + ribbons.length, 28, 62);
  text("input " + inputLabel, 28, 82);
  text("mouse " + mouseX + ", " + mouseY + "   fps " + nf(frameRate, 2, 1), 28, 102);
  text("1 flow  2 gravity  3 vortex  F field  T trails  P palette  R reset", 28, 126);

  drawModeButton(423, 24, "1", MODE_FLOW);
  drawModeButton(463, 24, "2", MODE_GRAVITY);
  drawModeButton(503, 24, "3", MODE_VORTEX);

  noFill();
  stroke(palette.hue(150), 50, 100, 38);
  strokeWeight(1);
  arc(width - 58, 58, 58, 58, -HALF_PI, -HALF_PI + (frameCount % 240) / 240.0 * TWO_PI);
  fill(palette.hue(180), 48, 100, 74);
  noStroke();
  textAlign(CENTER, CENTER);
  text(paused ? "PAUSE" : "LIVE", width - 58, 58);
  textAlign(LEFT, BASELINE);
}

void drawModeButton(float x, float y, String label, int buttonMode) {
  boolean active = mode == buttonMode;
  stroke(active ? palette.hue(80) : 220, active ? 70 : 18, active ? 100 : 45, active ? 86 : 42);
  fill(active ? palette.hue(60) : 222, active ? 70 : 25, active ? 70 : 18, active ? 76 : 64);
  rect(x, y, 32, 30, 5);
  fill(active ? 222 : 0, active ? 22 : 0, active ? 8 : 96, 96);
  textAlign(CENTER, CENTER);
  text(label, x + 16, y + 15);
  textAlign(LEFT, BASELINE);
}
