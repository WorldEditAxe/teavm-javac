public class Spaceship extends Floater
{
  protected Shaders shaderHook;
  protected Battlefield bf;

  public Spaceship(float x, float y, Shaders sh, Battlefield bf) {
    super(x, y);
    this.shaderHook = sh;
    this.bf = bf;
  }

  public Spaceship(float x, float y, float kineticFriction, Shaders sh, Battlefield bf) {
    super(x, y, kineticFriction, 10, 10);
    this.shaderHook = sh;
    this.bf = bf;
  }

  public void onCollision(Floater origin, float force) {
    if (force > this.mass * 1.2 && !dead && origin instanceof Asteroid && !(origin instanceof AsteroidFragment)) {
      this.kill();
    }
  }

  public void tick() {
    super.tick();

    if (x > width) x = 0;
    else if (x < 0) x = width;

    if (y > height) y = 0;
    else if (y < 0) y = height;
  }

  public void draw() {
    if (System.currentTimeMillis() < deadTill) return;
    pushMatrix();
    translate(x, y);
    rotate(radians(rotation));
    PVector transformed = transformPoint(5, 0);
    createLightingBackdrop((int) (Math.round(transformed.x)), (int) (Math.round(transformed.y)), (int) radius * 2, color(68, 122, 156), 0.2);
    {
      color s = g.strokeColor;
      noStroke();
      triangle(
        0, 10,
        0, -1,
        10, -1
        );
      triangle(
        0, -10,
        0, 1,
        10, 1
        );
      stroke(s);
    }
    popMatrix();
  }

  public void kill() {
    int fragments = (int) (Math.random() * 10) + 5;
    for (int i = 0; i < fragments; i++) {
      Asteroid a = new AsteroidFragment(this.x, this.y, this.kineticFriction, this.bf);
      a.setSpeed(Math.max(this.getSpeed() * 2f, 6));
      a.setSpeedRotation((float) (Math.random() * 360));
      a.setRadius(Math.min(this.radius / 2, 2));
      a.setMass(this.getMass() / fragments);
      bf.getFloaters().add(a);
    }
    this.setSpeed(0);
    deadTill = System.currentTimeMillis() + 5000;
    dead = true;

    final WarpEffectShader warp = new WarpEffectShader(color(255, 0, 0), 1000, 0.8);
    shaderHook.addShader(warp);
    DeferredTaskRunner.addTask(new Runnable() {
      @Override
      public void run() {
        shaderHook.removeShader(warp);
      }
    }, 1000);
  }

  protected final int MAX_SPEED = 10;
  protected final float SPEED_INCREMENT = 0.1f;
  protected final float SLOW_DECREMENT = -0.05f;

  protected final double MAX_ROTATION_MOD = 5;
  protected final int ROTATION_INCREMENT = 2;
  protected final double ROTATION_INCREMENT_POW = 0.002;

  protected long lastHold = -1;
  protected long tillNextWarp = System.currentTimeMillis();
  protected long tillNextBulletShoot = System.currentTimeMillis();
  protected char direction = 'n';

  protected long deadTill = -1;
  protected boolean dead = false;

  public void processKeyboardInput() {
    if (System.currentTimeMillis() < deadTill) return;
    if (dead) {
      dead = false;
      float rotation = (float) (Math.random() * 360);
      this.setX((float) (Math.random() * width));
      this.setY((float) (Math.random() * height));
      this.setRotation(rotation);

      this.setSpeed(0);
      this.setSpeedRotation(rotation);
      this.mass = 10;
    }

    float speed = this.getSpeed();
    boolean shouldAccelerate = Math.abs(speed) < MAX_SPEED;

    boolean turned = false;
    if (UserInputManager.isKeyDown('a')) {
      if (lastHold == -1 || direction != 'a') {
        lastHold = System.currentTimeMillis();
      }
      direction = 'a';
      turned = true;

      float timeFactor = (float) Math.min(Math.pow((System.currentTimeMillis() - lastHold) * 0.001, ROTATION_INCREMENT_POW), MAX_ROTATION_MOD);
      float rotationAmount = ROTATION_INCREMENT * timeFactor;
      this.setRotation(this.rotation - rotationAmount);
      this.setSpeedRotation(this.rotation);
    }
    if (UserInputManager.isKeyDown('d')) {
      if (lastHold == -1 || direction != 'd') {
        lastHold = System.currentTimeMillis();
      }
      direction = 'd';
      turned = true;

      float timeFactor = (float) Math.min(Math.pow((System.currentTimeMillis() - lastHold) * 0.001, ROTATION_INCREMENT_POW), MAX_ROTATION_MOD);
      float rotationAmount = ROTATION_INCREMENT * timeFactor;
      this.setRotation(this.rotation + rotationAmount);
      this.setSpeedRotation(this.rotation);
    }
    if (!turned) {
      lastHold = -1;
      direction = 'n';
    }

    if (UserInputManager.isKeyDown('w')) {
      if (shouldAccelerate) {
        float speedRot = this.getSpeedRotation(), realRot = getRotation();
        if (Math.abs(realRot - speedRot) <= 0.0001 || Math.abs(speed) <= 0.07) this.setSpeedRotation(realRot);
        else this.setSpeedRotation(speedRot + (float) (Math.abs(realRot - speedRot) * (realRot > speedRot ? 1 : -1)));
        this.setSpeed(speed + SPEED_INCREMENT);
      }
    }

    if (UserInputManager.isKeyDown('s')) {
      if (speed > 0 || speed > -MAX_SPEED) this.setSpeed(speed + SLOW_DECREMENT);
    }

    if (UserInputManager.isKeyDown('e') && System.currentTimeMillis() > tillNextWarp) {
      tillNextWarp = System.currentTimeMillis() + 1000;
      float rotation = (float) (Math.random() * 360);
      this.setX((float) (Math.random() * width));
      this.setY((float) (Math.random() * height));
      this.setRotation(rotation);

      this.setSpeed(0);
      this.setSpeedRotation(rotation);

      final WarpEffectShader warp = new WarpEffectShader(color(32, 64, 256), 1000);
      shaderHook.addShader(warp);
      DeferredTaskRunner.addTask(new Runnable() {
        @Override
        public void run() {
          shaderHook.removeShader(warp);
        }
      }, 1000);
    }

    if (UserInputManager.isKeyDown(' ') && System.currentTimeMillis() > tillNextBulletShoot) {
      tillNextBulletShoot = System.currentTimeMillis() + 100;
      float spawnX = this.x + (float) Math.cos(radians(this.rotation)) * this.radius * 2;
      float spawnY = this.y + (float) Math.sin(radians(this.rotation)) * this.radius * 2;
      Projectile p = new Projectile(spawnX, spawnY, this.getSpeed() + 10, this.getRotation());
      bf.getFloaters().add(p);
    }
  }
}

public class Projectile extends Floater {
  color thisColor;

  public Projectile(float x, float y, float speed, float rotation) {
    super(x, y, 0.02, 0.5, 4);
    this.setSpeed(speed);
    this.setSpeedRotation(rotation);
    this.kineticFriction = 0.0001f;
    thisColor = lerpColor(color(255, 124, 10), color(255, 67, 10), (float) Math.random());
  }

  public void onCollision(Floater origin, float force) {
    if (origin instanceof Asteroid) {
      Asteroid a = (Asteroid) origin;
      this.radius = 0;
    }
  }

  public void draw() {
    pushMatrix();
    translate(x, y);
    rotate(radians(rotation));
    createLightingBackdrop((int) this.x, (int) this.y, (int) this.radius, thisColor, 0.8);
    popMatrix();
  }
}
