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
