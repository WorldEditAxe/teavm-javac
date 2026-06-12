final int MARK_COUNT = 180;
final int RIPPLE_COUNT = 90;
final float PLAYER_ACCEL = 0.65;
final float PLAYER_DRAG = 0.88;

Mark[] marks = new Mark[MARK_COUNT];
Ripple[] ripples = new Ripple[RIPPLE_COUNT];
boolean upKey = false;
boolean downKey = false;
boolean leftKey = false;
boolean rightKey = false;
boolean paused = false;
int markIndex = 0;
int rippleIndex = 0;
int tool = 0;
int clickCount = 0;
float playerX;
float playerY;
float playerVX = 0;
float playerVY = 0;
float playerHeading = 0;
float baseHue = 170;
String lastKeyLabel = "none";
String lastMouseLabel = "move";

void setup() {
  size(960, 620);
  smooth(4);
  frameRate(60);
  colorMode(HSB, 360, 100, 100, 100);
  playerX = width * 0.5;
  playerY = height * 0.5;

  for (int i = 0; i < marks.length; i++) {
    marks[i] = new Mark();
  }
  for (int i = 0; i < ripples.length; i++) {
    ripples[i] = new Ripple();
  }
}

void draw() {
  if (!paused) {
    updatePlayer();
    updateRipples();
  }

  drawBackdrop();
  drawMousePath();
  drawMarks();
  drawRipples();
  drawPlayer();
  drawCursorTarget();
  drawHud();
}

void drawBackdrop() {
  background(220, 34, 8);
  stroke(220, 20, 22, 40);
  strokeWeight(1);
  for (int x = 40; x < width; x += 40) {
    line(x, 0, x, height);
  }
  for (int y = 40; y < height; y += 40) {
    line(0, y, width, y);
  }
}

void updatePlayer() {
  if (leftKey) {
    playerVX -= PLAYER_ACCEL;
  }
  if (rightKey) {
    playerVX += PLAYER_ACCEL;
  }
  if (upKey) {
    playerVY -= PLAYER_ACCEL;
  }
  if (downKey) {
    playerVY += PLAYER_ACCEL;
  }

  float dx = mouseX - playerX;
  float dy = mouseY - playerY;
  float distanceToMouse = sqrt(dx * dx + dy * dy);
  if (mousePressed && distanceToMouse > 2) {
    float pull = tool == 1 ? -0.5 : 0.42;
    playerVX += dx / distanceToMouse * pull;
    playerVY += dy / distanceToMouse * pull;
  }

  float speed = sqrt(playerVX * playerVX + playerVY * playerVY);
  if (speed > 9) {
    playerVX = playerVX / speed * 9;
    playerVY = playerVY / speed * 9;
  }

  playerX += playerVX;
  playerY += playerVY;
  playerVX *= PLAYER_DRAG;
  playerVY *= PLAYER_DRAG;
  playerX = constrain(playerX, 24, width - 24);
  playerY = constrain(playerY, 24, height - 24);

  if (abs(playerVX) + abs(playerVY) > 0.1) {
    playerHeading = atan2(playerVY, playerVX);
  }
}

void updateRipples() {
  for (int i = 0; i < ripples.length; i++) {
    ripples[i].update();
  }
}

void drawMousePath() {
  if (!mousePressed) {
    return;
  }

  stroke((baseHue + tool * 48) % 360, 72, 95, 56);
  strokeWeight(4);
  line(pmouseX, pmouseY, mouseX, mouseY);
  strokeWeight(1);
  stroke((baseHue + 120) % 360, 48, 100, 34);
  line(playerX, playerY, mouseX, mouseY);
}

void drawMarks() {
  for (int i = 0; i < marks.length; i++) {
    marks[i].render();
  }
}

void drawRipples() {
  for (int i = 0; i < ripples.length; i++) {
    ripples[i].render();
  }
}

void drawPlayer() {
  pushMatrix();
  translate(playerX, playerY);
  rotate(playerHeading);
  noStroke();
  fill((baseHue + 28) % 360, 68, 98, 94);
  triangle(22, 0, -15, -13, -9, 0);
  fill((baseHue + 86) % 360, 52, 100, 84);
  triangle(22, 0, -9, 0, -15, 13);
  stroke(0, 0, 100, 24);
  strokeWeight(2);
  line(-17, 0, -28 - 18 * keyThrust(), 0);
  popMatrix();
}

float keyThrust() {
  return upKey || downKey || leftKey || rightKey ? 1.0 : 0.25;
}

void drawCursorTarget() {
  float r = mousePressed ? 42 : 28;
  noFill();
  strokeWeight(2);
  stroke((baseHue + tool * 54 + 12) % 360, 70, 96, mousePressed ? 72 : 38);
  ellipse(mouseX, mouseY, r, r);
  strokeWeight(1);
  line(mouseX - 15, mouseY, mouseX + 15, mouseY);
  line(mouseX, mouseY - 15, mouseX, mouseY + 15);
}

void drawHud() {
  noStroke();
  fill(220, 26, 6, 80);
  rect(14, 14, 292, 114, 7);
  fill((baseHue + 16) % 360, 45, 96, 96);
  textSize(12);
  text("tool " + toolName(), 28, 38);
  text("mouse " + lastMouseLabel + " @ " + mouseX + ", " + mouseY, 28, 58);
  text("key " + lastKeyLabel, 28, 78);
  text("clicks " + clickCount + "   marks " + activeMarkCount(), 28, 98);
  text("velocity " + nf(playerVX, 1, 2) + ", " + nf(playerVY, 1, 2), 28, 118);

  drawKeyLamp(336, 31, "W", upKey);
  drawKeyLamp(336, 69, "S", downKey);
  drawKeyLamp(296, 69, "A", leftKey);
  drawKeyLamp(376, 69, "D", rightKey);
}

void drawKeyLamp(float x, float y, String label, boolean active) {
  stroke(220, 20, active ? 92 : 38, active ? 82 : 44);
  fill(active ? (baseHue + 42) % 360 : 220, active ? 70 : 24, active ? 94 : 18, active ? 88 : 72);
  rect(x, y, 32, 30, 5);
  fill(active ? 220 : 0, active ? 16 : 0, active ? 12 : 92, 96);
  textAlign(CENTER, CENTER);
  text(label, x + 16, y + 15);
  textAlign(LEFT, BASELINE);
}

String toolName() {
  if (tool == 0) {
    return "stamp";
  }
  if (tool == 1) {
    return "repel";
  }
  return "orbit";
}

int activeMarkCount() {
  int count = 0;
  for (int i = 0; i < marks.length; i++) {
    if (marks[i].active) {
      count++;
    }
  }
  return count;
}

void mouseMoved() {
  lastMouseLabel = "move";
}

void mousePressed() {
  clickCount++;
  lastMouseLabel = mouseButton == LEFT ? "press left" : "press other";
  addMark(mouseX, mouseY, 22 + tool * 7);
  addRipple(mouseX, mouseY, tool);
}

void mouseDragged() {
  lastMouseLabel = "drag";
  addMark(mouseX, mouseY, 10 + tool * 4);
  if (frameCount % 3 == 0) {
    addRipple(mouseX, mouseY, tool);
  }
}

void mouseReleased() {
  lastMouseLabel = "release";
  addRipple(mouseX, mouseY, tool);
}

void keyPressed() {
  setMoveKey(key, keyCode, true);
  lastKeyLabel = keyLabel();

  if (key == '1') {
    tool = 0;
  } else if (key == '2') {
    tool = 1;
  } else if (key == '3') {
    tool = 2;
  } else if (key == ' ') {
    paused = !paused;
  } else if (key == 'r' || key == 'R') {
    resetDemo();
  } else if (key == 'c' || key == 'C') {
    clearMarks();
  } else if (key == 'p' || key == 'P') {
    baseHue = (baseHue + 37) % 360;
  }
}

void keyReleased() {
  setMoveKey(key, keyCode, false);
  lastKeyLabel = "released " + keyLabel();
}

String keyLabel() {
  if (key == CODED) {
    if (keyCode == LEFT) {
      return "left arrow";
    }
    if (keyCode == RIGHT) {
      return "right arrow";
    }
    if (keyCode == UP) {
      return "up arrow";
    }
    if (keyCode == DOWN) {
      return "down arrow";
    }
    return "code " + keyCode;
  }
  if (key == ' ') {
    return "space";
  }
  return "" + key;
}

void setMoveKey(char value, int code, boolean active) {
  if (value == 'w' || value == 'W' || code == UP) {
    upKey = active;
  }
  if (value == 's' || value == 'S' || code == DOWN) {
    downKey = active;
  }
  if (value == 'a' || value == 'A' || code == LEFT) {
    leftKey = active;
  }
  if (value == 'd' || value == 'D' || code == RIGHT) {
    rightKey = active;
  }
}

void resetDemo() {
  clearMarks();
  playerX = width * 0.5;
  playerY = height * 0.5;
  playerVX = 0;
  playerVY = 0;
  clickCount = 0;
  lastKeyLabel = "reset";
  lastMouseLabel = "move";
}

void clearMarks() {
  for (int i = 0; i < marks.length; i++) {
    marks[i].active = false;
  }
  for (int i = 0; i < ripples.length; i++) {
    ripples[i].active = false;
  }
}

void addMark(float x, float y, float size) {
  marks[markIndex].set(x, y, size, tool);
  markIndex = (markIndex + 1) % marks.length;
}

void addRipple(float x, float y, int kind) {
  ripples[rippleIndex].set(x, y, kind);
  rippleIndex = (rippleIndex + 1) % ripples.length;
}

class Mark {
  float x;
  float y;
  float size;
  float age;
  int kind;
  boolean active = false;

  void set(float nextX, float nextY, float nextSize, int nextKind) {
    x = nextX;
    y = nextY;
    size = nextSize;
    kind = nextKind;
    age = 1.0;
    active = true;
  }

  void render() {
    if (!active) {
      return;
    }
    age *= paused ? 1.0 : 0.986;
    if (age < 0.04) {
      active = false;
      return;
    }

    float hue = (baseHue + kind * 58 + size * 0.9) % 360;
    noStroke();
    fill(hue, 64, 96, 58 * age);
    ellipse(x, y, size * age, size * age);
    noFill();
    stroke((hue + 120) % 360, 46, 100, 34 * age);
    strokeWeight(1.5);
    ellipse(x, y, size * 1.7 * age, size * 1.7 * age);
  }
}

class Ripple {
  float x;
  float y;
  float radius;
  float speed;
  int kind;
  boolean active = false;

  void set(float nextX, float nextY, int nextKind) {
    x = nextX;
    y = nextY;
    kind = nextKind;
    radius = 8;
    speed = 4.2 + nextKind * 1.4;
    active = true;
  }

  void update() {
    if (!active) {
      return;
    }
    radius += speed;
    if (radius > 180) {
      active = false;
    }
  }

  void render() {
    if (!active) {
      return;
    }
    float alpha = map(radius, 8, 180, 72, 0);
    noFill();
    stroke((baseHue + kind * 66 + radius) % 360, 72, 96, alpha);
    strokeWeight(2);
    ellipse(x, y, radius, radius);
  }
}
