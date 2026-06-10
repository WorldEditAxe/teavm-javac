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
