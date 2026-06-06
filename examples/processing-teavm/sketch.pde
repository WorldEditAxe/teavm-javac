float angle = 0;

void setup() {
  size(420, 280);
}

void draw() {
  background(248);
  translate(width / 2, height / 2);
  rotate(angle);

  stroke(25, 34, 44);
  strokeWeight(4);
  fill(80, 145, 210);
  rectMode(CENTER);
  rect(0, 0, 110, 110);

  noStroke();
  fill(255, 210, 88);
  ellipse(0, 0, 46, 46);

  angle += 0.025;
}
