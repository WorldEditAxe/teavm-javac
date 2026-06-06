public class Floater {
  protected float xSpeed, ySpeed;
  protected float rotation; // in degrees
  protected float x, y;

  protected float kineticFriction;
  protected float mass;
  protected float radius;

  public Floater(float x, float y) {
    this.x = x;
    this.y = y;
    this.xSpeed = 0;
    this.ySpeed = 0;
    this.rotation = 0;
    this.radius = 0;

    this.kineticFriction = 0;
    this.mass = 0;
  }

  public Floater(float x, float y, float kineticFriction, float mass, float radius) {
    this.x = x;
    this.y = y;
    this.xSpeed = 0;
    this.ySpeed = 0;
    this.rotation = 0;

    this.kineticFriction = Math.max(kineticFriction, 0);
    this.mass = Math.max(mass, 0);
    this.radius = Math.max(radius, 0);
  }

  public void onCollision(Floater origin, float force) {
    // to be implemented
  }

  public float getKineticFriction() {
    return 1 - kineticFriction;
  }
  public void setKineticFriction(float kine) {
    kineticFriction = 1 - kine;
  }

  public float getX() {
    return x;
  }
  public float getY() {
    return y;
  }

  public float getEffectivePhysicsX() {
    return x;
  }
  public float getEffectivePhysicsY() {
    return y;
  }

  public void setX(float x) {
    this.x = x;
  }
  public void setY(float y) {
    this.y = y;
  }

  public float getRotation() {
    return rotation;
  }
  public void setRotation(float rot) {
    this.rotation = rot > 360 ? rot - 360 : rot < 0 ? rot + 360 : rot;
  }

  public void setSpeedRotation(float deg) {
    deg = deg > 360 ? deg - 360 : deg < 0 ? deg + 360 : deg;
    float speed = getSpeed();
    this.xSpeed = speed * (float) Math.cos(radians(deg));
    this.ySpeed = speed * (float) Math.sin(radians(deg));
  }

  public void setSpeed(float speed) {
    float deg = getSpeedRotation();
    this.xSpeed = speed * (float) Math.cos(radians(deg));
    this.ySpeed = speed * (float) Math.sin(radians(deg));
  }

  public float getSpeed() {
    return (float) Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2));
  }

  public float getSpeedRotation() {
    float deg = (float) degrees((float) Math.atan2(ySpeed, xSpeed));
    return deg > 360 ? deg - 360 : deg < 0 ? deg + 360 : deg;
  }

  public float getMass() {
    return mass;
  }

  public void setMass(float mass) {
    this.mass = mass;
  }

  public float getRadius() {
    return radius;
  }

  public void setRadius(float radius) {
    this.radius = radius;
  }

  public void tick() {
    this.x += xSpeed;
    this.y += ySpeed;

    xSpeed *= (1 - kineticFriction);
    ySpeed *= (1 - kineticFriction);
  }

  public void draw() {
    throw new RuntimeException("This method has not been implemented!");
  }
}
