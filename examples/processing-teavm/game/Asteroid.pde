public class Asteroid extends Floater {
  protected float direction;
  protected Battlefield bf;
  protected List<Point2D> shapePoints;

  public Asteroid(float x, float y, Battlefield bf) {
    super(x, y);
    direction = (float) Math.random() * (Math.random() > 0.5 ? 1 : -1);
    this.kineticFriction = 0.0001f;
    this.bf = bf;
    this.shapePoints = generateRandomConvexPolygon(7);
    for (Point2D p : shapePoints) {
      p.setX(Math.min(p.getX() * radius, radius * 0.9));
      p.setY(Math.min(p.getY() * radius, radius * 0.9));
    }
  }

  public Asteroid(float x, float y, float kineticFriction, Battlefield bf) {
    super(x, y, kineticFriction, 5, 40);
    direction = (float) Math.random() * (Math.random() > 0.5 ? 1 : -1);
    this.kineticFriction = 0.0001f;
    this.bf = bf;
    this.shapePoints = generateRandomConvexPolygon(7);
    for (Point2D p : shapePoints) {
      p.setX(Math.min(p.getX() * radius, radius * 0.9));
      p.setY(Math.min(p.getY() * radius, radius * 0.9));
    }
  }

  public float getEffectivePhysicsX() {
    float biasedX = 0;
    int average = shapePoints.size();
    for (Point2D p : shapePoints) {
      biasedX += p.getX();
    }
    return this.x + (biasedX / average);
  }

  public float getEffectivePhysicsY() {
    float biasedY = 0;
    int average = shapePoints.size();
    for (Point2D p : shapePoints) {
      biasedY += p.getY();
    }
    return this.y + (biasedY / average);
  }

  public void onCollision(Floater origin, float force) {
    if (!(origin instanceof Spaceship) && !(origin instanceof Projectile)) return;
    if (force > this.getMass() * 1.75) {
      if (this.radius > 1.5) {
        int segments = (int) (Math.random() * 10) + 7;
        if (this.radius > 30 && origin instanceof Projectile) {
          for (int i = 0; i < segments / 4; i++) {
            Asteroid a = new Asteroid(this.x, this.y, this.kineticFriction, this.bf);
            a.setSpeed(this.getSpeed() * 2f);
            a.setSpeedRotation((float) (Math.random() * 360));
            a.setRadius(this.radius / segments * 2);
            a.setMass(this.getMass() / segments);
            bf.getFloaters().add(a);
          }
        }
        for (int i = 0; i < segments; i++) {
          Asteroid a = new AsteroidFragment(this.x, this.y, this.kineticFriction, this.bf);
          a.setSpeed(this.getSpeed() * 2f);
          a.setSpeedRotation((float) (Math.random() * 360));
          a.setRadius(Math.min(this.radius / 2, 2));
          a.setMass(this.getMass() / segments);
          bf.getFloaters().add(a);
        }
      }

      this.radius = 0;
    }
  }

  public void tick() {
    super.tick();
    this.setRotation(this.getRotation() + direction);
  }

  public void draw() {
    float px = this.getEffectivePhysicsX(), py = this.getEffectivePhysicsY();
    pushMatrix();
    translate(px, py);
    rotate(radians(rotation));
    translate(-(px - x), -(py - y));
    {
      color s = g.strokeColor;
      noStroke();
      beginShape();
      for (Point2D p : shapePoints) {
        vertex(p.getX(), p.getY());
      }
      endShape(CLOSE);

      stroke(s);
    }
    popMatrix();
  }
}

public class AsteroidFragment extends Asteroid {
  private long startTime, removeTime;
  private color startColor, endColor;

  public AsteroidFragment(float x, float y, Battlefield bf) {
    super(x, y, bf);
    startTime = System.currentTimeMillis();
    removeTime = startTime + (long) (Math.random() * 3000) + 2000;
    this.kineticFriction = 0.03f;

    int variation = (int) (Math.random() * 50 * (Math.random() > 0.5 ? 1 : -1));
    startColor = color(247 + variation, 128 + variation, 15 + variation);
    endColor = color(0);
  }

  public AsteroidFragment(float x, float y, float kineticFriction, Battlefield bf) {
    super(x, y, kineticFriction, bf);
    startTime = System.currentTimeMillis();
    removeTime = startTime + (long) (Math.random() * 3000) + 2000;
    this.kineticFriction = 0.03f;

    int variation = (int) (Math.random() * 50 * (Math.random() > 0.5 ? 1 : -1));
    startColor = color(247 + variation, 128 + variation, 15 + variation);
    endColor = color(0);
  }

  public void tick() {
    super.tick();
    this.setRotation(this.getRotation() + direction);

    if (System.currentTimeMillis() > removeTime) {
      this.radius = 0;
    }
  }

  public void draw() {
    pushMatrix();
    translate(x, y);
    rotate(radians(rotation));
    PVector transformed = transformPoint(0, 0);
    createLightingBackdrop((int) (Math.round(transformed.x)), (int) (Math.round(transformed.y)), (int) radius * 10, lerpColor(startColor, endColor, (float) (System.currentTimeMillis() - startTime) / (removeTime - startTime)));
    popMatrix();
  }
}

public class Debris extends Floater {
  // private long startTime, removeTime;
  private DefaultScene scene;

  public Debris(float x, float y, DefaultScene scene) {
    super(x, y);
    // startTime = System.currentTimeMillis();
    // removeTime = startTime + (long) (Math.random() * 3000) + 2000;
    kineticFriction = 0.001f;
    this.scene = scene;
    this.radius = 5;

    this.setSpeed((float) (Math.random() * 3) + 2);
    this.setSpeedRotation(155 + (float)(Math.random() * 30));
  }

  public void tick() {
    super.tick();
    // we'll let the culling system help us out here
    // if (System.currentTimeMillis() > removeTime) {
    //   this.radius = 0;
    // }
  }

  public void draw() {
    pushMatrix();
    translate(x, y);
    {
      color oc = g.fillColor, sc = g.strokeColor;
      noStroke();
      fill(color(64));
      square(0, 0, radius);
      fill(oc);
      stroke(sc);
    }
    popMatrix();
  }
}
