const PARTICLE_COUNT = 340;
const RIBBON_COUNT = 18;
const BURST_COUNT = 96;
const MODE_FLOW = 0;
const MODE_GRAVITY = 1;
const MODE_VORTEX = 2;

let particles = [];
let ribbons = [];
let bursts = [];
let palette;
let field;

let attractX = 0;
let attractY = 0;
let pulse = 0;
let spin = 0;
let cameraShake = 0;
let baseHue = 188;
let mode = MODE_FLOW;
let burstIndex = 0;
let paused = false;
let showField = true;
let trails = true;
let inputLabel = "move";

function setup() {
  createCanvas(960, 620);
  smooth();
  frameRate(60);
  colorMode(HSB, 360, 100, 100, 100);
  textAlign(LEFT, BASELINE);
  palette = new Palette();
  field = new FieldGrid(48);
  attractX = width * 0.5;
  attractY = height * 0.5;

  particles = new Array(PARTICLE_COUNT);
  ribbons = new Array(RIBBON_COUNT);
  bursts = new Array(BURST_COUNT);

  for (let i = 0; i < particles.length; i++) {
    particles[i] = new Particle(i);
    particles[i].reset(random(width), random(height), random(TWO_PI), random(0.6, 2.8));
  }
  for (let i = 0; i < ribbons.length; i++) {
    ribbons[i] = new Ribbon(i);
  }
  for (let i = 0; i < bursts.length; i++) {
    bursts[i] = new BurstDot();
  }
}

function draw() {
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
  push();
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
  pop();
  drawHud();
}

function updateParticles() {
  for (const particle of particles) {
    particle.update();
  }
}

function updateRibbons() {
  for (let i = 0; i < ribbons.length; i++) {
    const source = (i * 19 + floor(frameCount / 3)) % particles.length;
    ribbons[i].follow(particles[source].x, particles[source].y);
  }
}

function updateBursts() {
  for (const burst of bursts) {
    burst.update();
  }
}

function drawBackdrop() {
  if (trails) {
    noStroke();
    fill(222, 34, 6, 18);
    rect(0, 0, width, height);
  } else {
    background(222, 34, 6);
  }

  stroke(216, 18, 22, 32);
  strokeWeight(1);
  for (let x = 0; x <= width; x += 40) {
    line(x, 0, x, height);
  }
  for (let y = 20; y <= height; y += 40) {
    line(0, y, width, y);
  }

  noStroke();
  for (let i = 0; i < 6; i++) {
    const r = 210 + i * 70;
    const h = palette.hue(i * 23 + frameCount * 0.08);
    fill(h, 32, 16 + i * 3, 8);
    ellipse(width * 0.5, height * 0.5, r, r * 0.72);
  }
}

function drawOrbitRings() {
  push();
  translate(width * 0.5, height * 0.5);
  rotate(spin * 0.38);
  noFill();
  for (let i = 0; i < 8; i++) {
    const w = 150 + i * 88 + sin(pulse + i) * 20;
    const h = 84 + i * 52 + cos(pulse * 0.8 + i) * 15;
    stroke(palette.hue(i * 19), 42, 92, 18);
    strokeWeight(i % 3 === 0 ? 2.0 : 1.0);
    ellipse(0, 0, w, h);
  }
  pop();
}

function drawParticles() {
  for (const particle of particles) {
    particle.render();
  }
}

function drawRibbons() {
  for (const ribbon of ribbons) {
    ribbon.render();
  }
}

function drawBursts() {
  for (const burst of bursts) {
    burst.render();
  }
}

function drawBeacon() {
  push();
  translate(attractX, attractY);
  rotate(-spin);
  noFill();
  stroke(palette.hue(120), 78, 100, mouseIsPressed ? 88 : 50);
  strokeWeight(2);
  const r = mouseIsPressed ? 58 : 38;
  ellipse(0, 0, r, r);
  line(-18, 0, 18, 0);
  line(0, -18, 0, 18);
  rotate(spin * 3.0);
  stroke(palette.hue(176), 52, 100, 46);
  rect(-24, -24, 48, 48, 4);
  pop();
}

function spawnBurst(x, y, power) {
  for (let i = 0; i < 24; i++) {
    const angle = TWO_PI * i / 24.0 + random(-0.08, 0.08);
    bursts[burstIndex].set(x, y, angle, power * random(0.65, 1.25));
    burstIndex = (burstIndex + 1) % bursts.length;
  }
}

function mouseMoved() {
  inputLabel = "mouse " + mouseX + ", " + mouseY;
}

function mousePressed() {
  inputLabel = mouseButton === LEFT ? "burst" : "alternate burst";
  cameraShake = 4.8;
  spawnBurst(mouseX, mouseY, mouseButton === LEFT ? 8.5 : 5.4);
}

function mouseDragged() {
  inputLabel = "drawing force";
  if (frameCount % 3 === 0) {
    spawnBurst(mouseX, mouseY, 3.5);
  }
}

function mouseReleased() {
  inputLabel = "release";
}

function keyPressed() {
  if (key === "1") {
    mode = MODE_FLOW;
    inputLabel = "mode flow";
  } else if (key === "2") {
    mode = MODE_GRAVITY;
    inputLabel = "mode gravity";
  } else if (key === "3") {
    mode = MODE_VORTEX;
    inputLabel = "mode vortex";
  } else if (key === " ") {
    paused = !paused;
    inputLabel = paused ? "paused" : "running";
  } else if (key === "f" || key === "F") {
    showField = !showField;
    inputLabel = showField ? "field on" : "field off";
  } else if (key === "t" || key === "T") {
    trails = !trails;
    inputLabel = trails ? "trails on" : "trails off";
  } else if (key === "p" || key === "P") {
    baseHue = (baseHue + 41) % 360;
    inputLabel = "palette shifted";
  } else if (key === "r" || key === "R") {
    resetShowcase();
  }
}

function resetShowcase() {
  background(222, 34, 6);
  for (const particle of particles) {
    particle.reset(random(width), random(height), random(TWO_PI), random(0.6, 2.8));
  }
  for (const ribbon of ribbons) {
    ribbon.clear();
  }
  for (const burst of bursts) {
    burst.active = false;
  }
  inputLabel = "reset";
}

class Particle {
  constructor(id) {
    this.id = id;
    this.x = 0;
    this.y = 0;
    this.vx = 0;
    this.vy = 0;
    this.life = 0;
    this.size = 0;
    this.offset = random(1000);
  }

  reset(x, y, angle, speed) {
    this.x = x;
    this.y = y;
    this.vx = cos(angle) * speed;
    this.vy = sin(angle) * speed;
    this.life = random(0.3, 1.0);
    this.size = random(2.0, 6.5);
  }

  update() {
    const angle = field.angleAt(this.x, this.y, this.offset);
    let ax = cos(angle) * 0.075;
    let ay = sin(angle) * 0.075;
    const dx = attractX - this.x;
    const dy = attractY - this.y;
    const d = max(24, sqrt(dx * dx + dy * dy));

    if (mode === MODE_GRAVITY) {
      ax += dx / d * 0.22;
      ay += dy / d * 0.22;
    } else if (mode === MODE_VORTEX) {
      ax += -dy / d * 0.24;
      ay += dx / d * 0.24;
    } else {
      ax += dx / d * 0.025;
      ay += dy / d * 0.025;
    }

    if (mouseIsPressed) {
      const force = mouseButton === LEFT ? 0.75 : -0.55;
      ax += dx / d * force;
      ay += dy / d * force;
    }

    this.vx = (this.vx + ax) * 0.974;
    this.vy = (this.vy + ay) * 0.974;
    const speed = sqrt(this.vx * this.vx + this.vy * this.vy);
    if (speed > 7.0) {
      this.vx = this.vx / speed * 7.0;
      this.vy = this.vy / speed * 7.0;
    }

    this.x += this.vx;
    this.y += this.vy;
    this.life += 0.006;
    this.wrap();
  }

  wrap() {
    if (this.x < -20) {
      this.x = width + 20;
    } else if (this.x > width + 20) {
      this.x = -20;
    }
    if (this.y < -20) {
      this.y = height + 20;
    } else if (this.y > height + 20) {
      this.y = -20;
    }
  }

  render() {
    const speed = sqrt(this.vx * this.vx + this.vy * this.vy);
    const h = palette.hue(this.id * 1.7 + speed * 12 + this.life * 18);
    const tail = 4 + speed * 4;
    stroke(h, 70, 100, 42);
    strokeWeight(max(1.0, this.size * 0.35));
    line(this.x - this.vx * tail, this.y - this.vy * tail, this.x, this.y);

    noStroke();
    fill(h, 68, 96, 78);
    ellipse(this.x, this.y, this.size + speed * 0.7, this.size + speed * 0.7);
    fill((h + 42) % 360, 28, 100, 40);
    ellipse(this.x, this.y, this.size * 0.42, this.size * 0.42);
  }
}

class Ribbon {
  constructor(id) {
    this.points = 36;
    this.xs = new Array(this.points);
    this.ys = new Array(this.points);
    this.head = 0;
    this.id = id;
    this.clear();
  }

  clear() {
    for (let i = 0; i < this.points; i++) {
      this.xs[i] = width * 0.5;
      this.ys[i] = height * 0.5;
    }
    this.head = 0;
  }

  follow(x, y) {
    this.head = (this.head + 1) % this.points;
    this.xs[this.head] = x;
    this.ys[this.head] = y;
  }

  render() {
    noFill();
    strokeWeight(1.5 + this.id % 3);
    stroke(palette.hue(this.id * 17 + frameCount * 0.2), 42, 100, 16);
    beginShape();
    for (let i = 0; i < this.points; i++) {
      const idx = (this.head + i + 1) % this.points;
      vertex(this.xs[idx], this.ys[idx]);
    }
    endShape();

    strokeWeight(0.75);
    stroke(palette.hue(this.id * 17 + 80), 70, 95, 30);
    const tail = (this.head + 1) % this.points;
    line(this.xs[tail], this.ys[tail], this.xs[this.head], this.ys[this.head]);
  }
}

class BurstDot {
  constructor() {
    this.x = 0;
    this.y = 0;
    this.vx = 0;
    this.vy = 0;
    this.age = 0;
    this.maxAge = 0;
    this.active = false;
  }

  set(x, y, angle, speed) {
    this.x = x;
    this.y = y;
    this.vx = cos(angle) * speed;
    this.vy = sin(angle) * speed;
    this.age = 0;
    this.maxAge = random(34, 72);
    this.active = true;
  }

  update() {
    if (!this.active) {
      return;
    }
    this.x += this.vx;
    this.y += this.vy;
    this.vx *= 0.962;
    this.vy *= 0.962;
    this.age++;
    if (this.age > this.maxAge) {
      this.active = false;
    }
  }

  render() {
    if (!this.active) {
      return;
    }
    const t = 1.0 - this.age / this.maxAge;
    const h = palette.hue(this.age * 2.6 + this.maxAge);
    noStroke();
    fill(h, 80, 100, 75 * t);
    ellipse(this.x, this.y, 3 + 16 * t, 3 + 16 * t);
    stroke((h + 90) % 360, 48, 100, 45 * t);
    strokeWeight(1.2);
    line(this.x, this.y, this.x - this.vx * 3.5, this.y - this.vy * 3.5);
  }
}

class FieldGrid {
  constructor(step) {
    this.step = step;
  }

  angleAt(x, y, offset) {
    const nx = x * 0.0045;
    const ny = y * 0.0045;
    const n = noise(nx + offset * 0.01, ny - offset * 0.01, pulse * 0.28);
    const radial = atan2(y - height * 0.5, x - width * 0.5);
    return n * TWO_PI * 2.2 + radial * 0.28 + spin;
  }

  render() {
    strokeWeight(1);
    for (let y = this.step / 2; y < height; y += this.step) {
      for (let x = this.step / 2; x < width; x += this.step) {
        const angle = this.angleAt(x, y, 4);
        const len = 12 + 10 * noise(x * 0.01, y * 0.01, pulse);
        const h = palette.hue(x * 0.08 + y * 0.05);
        stroke(h, 34, 72, 20);
        line(
          x - cos(angle) * len * 0.5,
          y - sin(angle) * len * 0.5,
          x + cos(angle) * len * 0.5,
          y + sin(angle) * len * 0.5
        );
      }
    }
  }
}

class Palette {
  hue(offset) {
    return (baseHue + offset + sin(pulse * 0.9 + offset * 0.04) * 22 + 360) % 360;
  }

  modeName() {
    if (mode === MODE_GRAVITY) {
      return "gravity";
    }
    if (mode === MODE_VORTEX) {
      return "vortex";
    }
    return "flow";
  }
}

function drawHud() {
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
  text("mouse " + mouseX + ", " + mouseY + "   fps " + nf(frameRate(), 2, 1), 28, 102);
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

function drawModeButton(x, y, label, buttonMode) {
  const active = mode === buttonMode;
  stroke(active ? palette.hue(80) : 220, active ? 70 : 18, active ? 100 : 45, active ? 86 : 42);
  fill(active ? palette.hue(60) : 222, active ? 70 : 25, active ? 70 : 18, active ? 76 : 64);
  rect(x, y, 32, 30, 5);
  fill(active ? 222 : 0, active ? 22 : 0, active ? 8 : 96, 96);
  textAlign(CENTER, CENTER);
  text(label, x + 16, y + 15);
  textAlign(LEFT, BASELINE);
}
