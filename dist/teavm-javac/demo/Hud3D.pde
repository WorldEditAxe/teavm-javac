void drawHud() {
  resetMatrix();
  noLights();
  noStroke();
  fill(222, 32, 5, 82);
  rect(14, 14, 360, 140, 7);

  fill(palette.hue(12), 42, 100, 96);
  textSize(13);
  text("Stellar Forge P3D", 28, 39);
  fill(0, 0, 100, 80);
  textSize(12);
  text("mode " + modeName() + "   bodies " + bodies.length + "   drones " + drones.length, 28, 62);
  text("input " + inputLabel, 28, 82);
  text("yaw " + nf(yaw, 1, 2) + "   pitch " + nf(pitch, 1, 2) + "   scale " + nf(sceneScale, 1, 2), 28, 102);
  text("1 orbit  2 lattice  3 warp  G grid  T trails  W/S zoom", 28, 126);

  drawModeButton(396, 24, "1", 0);
  drawModeButton(436, 24, "2", 1);
  drawModeButton(476, 24, "3", 2);

  noFill();
  stroke(palette.hue(164), 52, 100, 50);
  strokeWeight(1.4);
  arc(width - 58, 58, 58, 58, -HALF_PI, -HALF_PI + (frameCount % 240) / 240.0 * TWO_PI);
  fill(paused ? palette.hue(330) : palette.hue(180), 54, 100, 82);
  noStroke();
  textAlign(CENTER, CENTER);
  text(paused ? "PAUSE" : "P3D", width - 58, 58);
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
