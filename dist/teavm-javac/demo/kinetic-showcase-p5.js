export function createKineticShowcaseSketch(p) {
  const PARTICLE_COUNT = 340;
  const RIBBON_COUNT = 18;
  const BURST_COUNT = 96;
  const MODE_FLOW = 0;
  const MODE_GRAVITY = 1;
  const MODE_VORTEX = 2;

  const particles = new Array(PARTICLE_COUNT);
  const ribbons = new Array(RIBBON_COUNT);
  const bursts = new Array(BURST_COUNT);
  const palette = new Palette();
  const field = new FieldGrid(48);

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

  p.setup = () => {
    p.createCanvas(960, 620);
    p.smooth();
    p.frameRate(60);
    p.colorMode(p.HSB, 360, 100, 100, 100);
    p.textAlign(p.LEFT, p.BASELINE);
    attractX = p.width * 0.5;
    attractY = p.height * 0.5;

    for (let i = 0; i < particles.length; i++) {
      particles[i] = new Particle(i);
      particles[i].reset(p.random(p.width), p.random(p.height), p.random(p.TWO_PI), p.random(0.6, 2.8));
    }
    for (let i = 0; i < ribbons.length; i++) {
      ribbons[i] = new Ribbon(i);
    }
    for (let i = 0; i < bursts.length; i++) {
      bursts[i] = new BurstDot();
    }
  };

  p.draw = () => {
    if (!paused) {
      pulse += 0.018;
      spin += 0.011;
      attractX = p.lerp(attractX, p.mouseX, 0.065);
      attractY = p.lerp(attractY, p.mouseY, 0.065);
      updateParticles();
      updateRibbons();
      updateBursts();
      cameraShake *= 0.86;
    }

    drawBackdrop();
    p.push();
    if (cameraShake > 0.02) {
      p.translate(p.random(-cameraShake, cameraShake), p.random(-cameraShake, cameraShake));
    }
    if (showField) {
      field.render();
    }
    drawOrbitRings();
    drawRibbons();
    drawBursts();
    drawParticles();
    drawBeacon();
    p.pop();
    drawHud();
  };

  p.mouseMoved = () => {
    inputLabel = `mouse ${p.mouseX}, ${p.mouseY}`;
  };

  p.mousePressed = () => {
    inputLabel = p.mouseButton === p.LEFT ? "burst" : "alternate burst";
    cameraShake = 4.8;
    spawnBurst(p.mouseX, p.mouseY, p.mouseButton === p.LEFT ? 8.5 : 5.4);
  };

  p.mouseDragged = () => {
    inputLabel = "drawing force";
    if (p.frameCount % 3 === 0) {
      spawnBurst(p.mouseX, p.mouseY, 3.5);
    }
  };

  p.mouseReleased = () => {
    inputLabel = "release";
  };

  p.keyPressed = () => {
    if (p.key === "1") {
      mode = MODE_FLOW;
      inputLabel = "mode flow";
    } else if (p.key === "2") {
      mode = MODE_GRAVITY;
      inputLabel = "mode gravity";
    } else if (p.key === "3") {
      mode = MODE_VORTEX;
      inputLabel = "mode vortex";
    } else if (p.key === " ") {
      paused = !paused;
      inputLabel = paused ? "paused" : "running";
    } else if (p.key === "f" || p.key === "F") {
      showField = !showField;
      inputLabel = showField ? "field on" : "field off";
    } else if (p.key === "t" || p.key === "T") {
      trails = !trails;
      inputLabel = trails ? "trails on" : "trails off";
    } else if (p.key === "p" || p.key === "P") {
      baseHue = (baseHue + 41) % 360;
      inputLabel = "palette shifted";
    } else if (p.key === "r" || p.key === "R") {
      resetShowcase();
    }
  };

  function updateParticles() {
    for (const particle of particles) {
      particle.update();
    }
  }

  function updateRibbons() {
    for (let i = 0; i < ribbons.length; i++) {
      const source = (i * 19 + Math.floor(p.frameCount / 3)) % particles.length;
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
      p.noStroke();
      p.fill(222, 34, 6, 18);
      p.rect(0, 0, p.width, p.height);
    } else {
      p.background(222, 34, 6);
    }

    p.stroke(216, 18, 22, 32);
    p.strokeWeight(1);
    for (let x = 0; x <= p.width; x += 40) {
      p.line(x, 0, x, p.height);
    }
    for (let y = 20; y <= p.height; y += 40) {
      p.line(0, y, p.width, y);
    }

    p.noStroke();
    for (let i = 0; i < 6; i++) {
      const r = 210 + i * 70;
      const h = palette.hue(i * 23 + p.frameCount * 0.08);
      p.fill(h, 32, 16 + i * 3, 8);
      p.ellipse(p.width * 0.5, p.height * 0.5, r, r * 0.72);
    }
  }

  function drawOrbitRings() {
    p.push();
    p.translate(p.width * 0.5, p.height * 0.5);
    p.rotate(spin * 0.38);
    p.noFill();
    for (let i = 0; i < 8; i++) {
      const w = 150 + i * 88 + p.sin(pulse + i) * 20;
      const h = 84 + i * 52 + p.cos(pulse * 0.8 + i) * 15;
      p.stroke(palette.hue(i * 19), 42, 92, 18);
      p.strokeWeight(i % 3 === 0 ? 2.0 : 1.0);
      p.ellipse(0, 0, w, h);
    }
    p.pop();
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
    p.push();
    p.translate(attractX, attractY);
    p.rotate(-spin);
    p.noFill();
    p.stroke(palette.hue(120), 78, 100, p.mouseIsPressed ? 88 : 50);
    p.strokeWeight(2);
    const r = p.mouseIsPressed ? 58 : 38;
    p.ellipse(0, 0, r, r);
    p.line(-18, 0, 18, 0);
    p.line(0, -18, 0, 18);
    p.rotate(spin * 3.0);
    p.stroke(palette.hue(176), 52, 100, 46);
    p.rect(-24, -24, 48, 48, 4);
    p.pop();
  }

  function spawnBurst(x, y, power) {
    for (let i = 0; i < 24; i++) {
      const angle = p.TWO_PI * i / 24.0 + p.random(-0.08, 0.08);
      bursts[burstIndex].set(x, y, angle, power * p.random(0.65, 1.25));
      burstIndex = (burstIndex + 1) % bursts.length;
    }
  }

  function resetShowcase() {
    p.background(222, 34, 6);
    for (const particle of particles) {
      particle.reset(p.random(p.width), p.random(p.height), p.random(p.TWO_PI), p.random(0.6, 2.8));
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
      this.offset = p.random(1000);
    }

    reset(x, y, angle, speed) {
      this.x = x;
      this.y = y;
      this.vx = p.cos(angle) * speed;
      this.vy = p.sin(angle) * speed;
      this.life = p.random(0.3, 1.0);
      this.size = p.random(2.0, 6.5);
    }

    update() {
      const angle = field.angleAt(this.x, this.y, this.offset);
      let ax = p.cos(angle) * 0.075;
      let ay = p.sin(angle) * 0.075;
      const dx = attractX - this.x;
      const dy = attractY - this.y;
      const d = p.max(24, p.sqrt(dx * dx + dy * dy));

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

      if (p.mouseIsPressed) {
        const force = p.mouseButton === p.LEFT ? 0.75 : -0.55;
        ax += dx / d * force;
        ay += dy / d * force;
      }

      this.vx = (this.vx + ax) * 0.974;
      this.vy = (this.vy + ay) * 0.974;
      const speed = p.sqrt(this.vx * this.vx + this.vy * this.vy);
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
        this.x = p.width + 20;
      } else if (this.x > p.width + 20) {
        this.x = -20;
      }
      if (this.y < -20) {
        this.y = p.height + 20;
      } else if (this.y > p.height + 20) {
        this.y = -20;
      }
    }

    render() {
      const speed = p.sqrt(this.vx * this.vx + this.vy * this.vy);
      const h = palette.hue(this.id * 1.7 + speed * 12 + this.life * 18);
      const tail = 4 + speed * 4;
      p.stroke(h, 70, 100, 42);
      p.strokeWeight(p.max(1.0, this.size * 0.35));
      p.line(this.x - this.vx * tail, this.y - this.vy * tail, this.x, this.y);

      p.noStroke();
      p.fill(h, 68, 96, 78);
      p.ellipse(this.x, this.y, this.size + speed * 0.7, this.size + speed * 0.7);
      p.fill((h + 42) % 360, 28, 100, 40);
      p.ellipse(this.x, this.y, this.size * 0.42, this.size * 0.42);
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
        this.xs[i] = p.width * 0.5;
        this.ys[i] = p.height * 0.5;
      }
      this.head = 0;
    }

    follow(x, y) {
      this.head = (this.head + 1) % this.points;
      this.xs[this.head] = x;
      this.ys[this.head] = y;
    }

    render() {
      p.noFill();
      p.strokeWeight(1.5 + this.id % 3);
      p.stroke(palette.hue(this.id * 17 + p.frameCount * 0.2), 42, 100, 16);
      p.beginShape();
      for (let i = 0; i < this.points; i++) {
        const idx = (this.head + i + 1) % this.points;
        p.vertex(this.xs[idx], this.ys[idx]);
      }
      p.endShape();

      p.strokeWeight(0.75);
      p.stroke(palette.hue(this.id * 17 + 80), 70, 95, 30);
      const tail = (this.head + 1) % this.points;
      p.line(this.xs[tail], this.ys[tail], this.xs[this.head], this.ys[this.head]);
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
      this.vx = p.cos(angle) * speed;
      this.vy = p.sin(angle) * speed;
      this.age = 0;
      this.maxAge = p.random(34, 72);
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
      p.noStroke();
      p.fill(h, 80, 100, 75 * t);
      p.ellipse(this.x, this.y, 3 + 16 * t, 3 + 16 * t);
      p.stroke((h + 90) % 360, 48, 100, 45 * t);
      p.strokeWeight(1.2);
      p.line(this.x, this.y, this.x - this.vx * 3.5, this.y - this.vy * 3.5);
    }
  }

  class FieldGrid {
    constructor(step) {
      this.step = step;
    }

    angleAt(x, y, offset) {
      const nx = x * 0.0045;
      const ny = y * 0.0045;
      const n = p.noise(nx + offset * 0.01, ny - offset * 0.01, pulse * 0.28);
      const radial = p.atan2(y - p.height * 0.5, x - p.width * 0.5);
      return n * p.TWO_PI * 2.2 + radial * 0.28 + spin;
    }

    render() {
      p.strokeWeight(1);
      for (let y = this.step / 2; y < p.height; y += this.step) {
        for (let x = this.step / 2; x < p.width; x += this.step) {
          const angle = this.angleAt(x, y, 4);
          const len = 12 + 10 * p.noise(x * 0.01, y * 0.01, pulse);
          const h = palette.hue(x * 0.08 + y * 0.05);
          p.stroke(h, 34, 72, 20);
          p.line(
            x - p.cos(angle) * len * 0.5,
            y - p.sin(angle) * len * 0.5,
            x + p.cos(angle) * len * 0.5,
            y + p.sin(angle) * len * 0.5
          );
        }
      }
    }
  }

  class Palette {
    hue(offset) {
      return (baseHue + offset + p.sin(pulse * 0.9 + offset * 0.04) * 22 + 360) % 360;
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
    p.noStroke();
    p.fill(222, 28, 5, 82);
    p.rect(14, 14, 390, 142, 7);
    p.fill(palette.hue(24), 38, 100, 96);
    p.textSize(13);
    p.text("Kinetic 2D Processing Showcase", 28, 39);
    p.fill(0, 0, 100, 78);
    p.textSize(12);
    p.text(`mode ${palette.modeName()}   particles ${particles.length}   ribbons ${ribbons.length}`, 28, 62);
    p.text(`input ${inputLabel}`, 28, 82);
    p.text(`mouse ${p.mouseX}, ${p.mouseY}   fps ${p.nf(p.frameRate(), 2, 1)}`, 28, 102);
    p.text("1 flow  2 gravity  3 vortex  F field  T trails  P palette  R reset", 28, 126);

    drawModeButton(423, 24, "1", MODE_FLOW);
    drawModeButton(463, 24, "2", MODE_GRAVITY);
    drawModeButton(503, 24, "3", MODE_VORTEX);

    p.noFill();
    p.stroke(palette.hue(150), 50, 100, 38);
    p.strokeWeight(1);
    p.arc(p.width - 58, 58, 58, 58, -p.HALF_PI, -p.HALF_PI + (p.frameCount % 240) / 240.0 * p.TWO_PI);
    p.fill(palette.hue(180), 48, 100, 74);
    p.noStroke();
    p.textAlign(p.CENTER, p.CENTER);
    p.text(paused ? "PAUSE" : "LIVE", p.width - 58, 58);
    p.textAlign(p.LEFT, p.BASELINE);
  }

  function drawModeButton(x, y, label, buttonMode) {
    const active = mode === buttonMode;
    p.stroke(active ? palette.hue(80) : 220, active ? 70 : 18, active ? 100 : 45, active ? 86 : 42);
    p.fill(active ? palette.hue(60) : 222, active ? 70 : 25, active ? 70 : 18, active ? 76 : 64);
    p.rect(x, y, 32, 30, 5);
    p.fill(active ? 222 : 0, active ? 22 : 0, active ? 8 : 96, 96);
    p.textAlign(p.CENTER, p.CENTER);
    p.text(label, x + 16, y + 15);
    p.textAlign(p.LEFT, p.BASELINE);
  }
}

export function mountKineticShowcase(node, P5 = globalThis.p5) {
  if (typeof P5 !== "function") {
    throw new Error("p5.js is required to mount the kinetic showcase sketch");
  }
  return new P5(createKineticShowcaseSketch, node);
}

if (typeof window !== "undefined") {
  window.createKineticShowcaseSketch = createKineticShowcaseSketch;
  window.mountKineticShowcase = mountKineticShowcase;

  const target = document.querySelector("[data-p5-kinetic-showcase]");
  if (target && typeof window.p5 === "function") {
    mountKineticShowcase(target, window.p5);
  }
}
