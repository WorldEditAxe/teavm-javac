final int BODY_COUNT = 13;
final int DRONE_COUNT = 34;
final int RING_COUNT = 7;

Palette3D palette = new Palette3D();
OrbitalBody[] bodies = new OrbitalBody[BODY_COUNT];
Drone[] drones = new Drone[DRONE_COUNT];
TrailRing[] rings = new TrailRing[RING_COUNT];
StarField stars;
GridPlane grid;

float pulse = 0;
float yaw = -0.35;
float pitch = 0.58;
float targetYaw = -0.35;
float targetPitch = 0.58;
float sceneScale = 1.0;
float targetScale = 1.0;
float baseHue = 198;
float shake = 0;
int mode = 0;
boolean paused = false;
boolean showGrid = true;
boolean showTrails = true;
String inputLabel = "drag to orbit";

void setup() {
  size(960, 620, P3D);
  frameRate(60);
  colorMode(HSB, 360, 100, 100, 100);
  textAlign(LEFT, BASELINE);
  sphereDetail(18);

  stars = new StarField(150, 680);
  grid = new GridPlane(520, 44);

  for (int i = 0; i < bodies.length; i++) {
    bodies[i] = new OrbitalBody(i);
  }
  for (int i = 0; i < drones.length; i++) {
    drones[i] = new Drone(i);
  }
  for (int i = 0; i < rings.length; i++) {
    rings[i] = new TrailRing(i);
  }
}

void draw() {
  if (!paused) {
    pulse += 0.018 * speedFactor();
    yaw = lerp(yaw, targetYaw, 0.08);
    pitch = lerp(pitch, targetPitch, 0.08);
    sceneScale = lerp(sceneScale, targetScale, 0.08);
    shake *= 0.86;
    updateScene();
  }

  drawScene();
  drawHud();
}

void updateScene() {
  for (int i = 0; i < bodies.length; i++) {
    bodies[i].update();
  }
  for (int i = 0; i < drones.length; i++) {
    drones[i].update();
  }
  for (int i = 0; i < rings.length; i++) {
    rings[i].update();
  }
}

void drawScene() {
  background(222, 48, 4);
  ambientLight(218, 30, 22);
  directionalLight(202, 22, 92, -0.25, 0.55, -1.0);
  pointLight(palette.hue(40), 70, 100, width * 0.5, height * 0.32, 240);
  pointLight(palette.hue(170), 55, 80, width * 0.22, height * 0.68, -180);

  pushMatrix();
  translate(width * 0.5, height * 0.53, 0);
  if (shake > 0.02) {
    translate(random(-shake, shake), random(-shake, shake), random(-shake * 2, shake * 2));
  }
  scale(sceneScale);
  rotateX(pitch);
  rotateY(yaw);
  rotateZ(sin(pulse * 0.35) * 0.045);

  stars.render();
  if (showGrid) {
    grid.render();
  }
  drawCore();
  if (showTrails) {
    drawRings();
  }
  drawBodies();
  drawDrones();
  drawAxisBeacon();
  popMatrix();
}

void drawCore() {
  pushMatrix();
  rotateY(pulse * 0.7);
  rotateX(pulse * 0.42);
  noStroke();
  fill(palette.hue(18), 74, 100, 92);
  sphere(38 + sin(pulse * 2.0) * 3);

  noFill();
  strokeWeight(2);
  for (int i = 0; i < 4; i++) {
    stroke(palette.hue(70 + i * 22), 64, 100, 48);
    rotateY(HALF_PI * 0.5);
    rotateZ(0.35 + i * 0.21);
    beginShape();
    for (int a = 0; a <= 96; a++) {
      float t = TWO_PI * a / 96.0;
      vertex(cos(t) * 88, sin(t) * 88, sin(t * 2 + pulse) * 18);
    }
    endShape();
  }
  popMatrix();
}

void drawRings() {
  for (int i = 0; i < rings.length; i++) {
    rings[i].render();
  }
}

void drawBodies() {
  for (int i = 0; i < bodies.length; i++) {
    bodies[i].render();
  }
}

void drawDrones() {
  for (int i = 0; i < drones.length; i++) {
    drones[i].render();
  }
}

void drawAxisBeacon() {
  strokeWeight(2);
  stroke(palette.hue(8), 78, 100, 55);
  line(-260, 0, 0, 260, 0, 0);
  stroke(palette.hue(128), 65, 100, 45);
  line(0, -170, 0, 0, 170, 0);
  stroke(palette.hue(218), 65, 100, 48);
  line(0, 0, -260, 0, 0, 260);
}

float speedFactor() {
  if (mode == 1) {
    return 1.45;
  }
  if (mode == 2) {
    return 2.1;
  }
  return 1.0;
}

String modeName() {
  if (mode == 1) {
    return "lattice";
  }
  if (mode == 2) {
    return "warp";
  }
  return "orbital";
}

void mouseDragged() {
  targetYaw += (mouseX - pmouseX) * 0.012;
  targetPitch += (mouseY - pmouseY) * 0.010;
  targetPitch = constrain(targetPitch, -0.25, 1.35);
  inputLabel = "orbit " + nf(targetYaw, 1, 2) + ", " + nf(targetPitch, 1, 2);
}

void mousePressed() {
  shake = 8.0;
  inputLabel = "impulse";
}

void keyPressed() {
  if (key == '1') {
    mode = 0;
    inputLabel = "mode orbital";
  } else if (key == '2') {
    mode = 1;
    inputLabel = "mode lattice";
  } else if (key == '3') {
    mode = 2;
    inputLabel = "mode warp";
  } else if (key == ' ') {
    paused = !paused;
    inputLabel = paused ? "paused" : "running";
  } else if (key == 'g' || key == 'G') {
    showGrid = !showGrid;
    inputLabel = showGrid ? "grid on" : "grid off";
  } else if (key == 't' || key == 'T') {
    showTrails = !showTrails;
    inputLabel = showTrails ? "trails on" : "trails off";
  } else if (key == 'p' || key == 'P') {
    baseHue = (baseHue + 47) % 360;
    inputLabel = "palette shift";
  } else if (key == 'w' || key == 'W') {
    targetScale = constrain(targetScale + 0.08, 0.72, 1.45);
    inputLabel = "zoom in";
  } else if (key == 's' || key == 'S') {
    targetScale = constrain(targetScale - 0.08, 0.72, 1.45);
    inputLabel = "zoom out";
  } else if (key == 'r' || key == 'R') {
    targetYaw = -0.35;
    targetPitch = 0.58;
    targetScale = 1.0;
    mode = 0;
    baseHue = 198;
    showGrid = true;
    showTrails = true;
    paused = false;
    inputLabel = "reset";
  }
}
