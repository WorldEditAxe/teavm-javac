public class BaseBattleScene implements Scene {
  protected color framebufferColor;
  protected Shaders postShader;
  protected Shaders preShader;

  public BaseBattleScene() {
    this.framebufferColor = color(16);
    this.postShader = new Shaders();
    this.preShader = new Shaders();
  }

  void setup() {
    background(framebufferColor);
    preShader.addShader(new MotionBlurShader(framebufferColor, 0.7));
    postShader.addShader(new VingetteShader(0.7));
  }

  protected void drawBackgroundPrelude() {
  }

  protected void drawPrimatives() {
    throw new UnsupportedOperationException("drawPrimatives() must be implemented by the subclass");
  }

  protected long begin = System.currentTimeMillis();
  void draw() {
    drawBackgroundPrelude();
    loadPixels();
    pixels = preShader.process(pixels);
    updatePixels();

    drawPrimatives();
    loadPixels();
    pixels = postShader.process(pixels);
    updatePixels();

    getSurface().setTitle(String.format("Asteroids ::= %.2f ms/frame; %.2f fps", (double) (System.currentTimeMillis() - begin), 1000.0 / (System.currentTimeMillis() - begin)));
    begin = System.currentTimeMillis();
  }
}

public class DefaultScene extends BaseBattleScene implements Scene, Battlefield {
  private ArrayList<Floater> floaters;
  private CollisionUtil collisions;
  private Spaceship ss;
  private Star[] stars;

  static final int ASTEROID_COUNT = 20;

  public DefaultScene() {
    super();
    this.ss = new Spaceship(height / 2, width / 2, 0.02, postShader, this);
    this.stars = new Star[200];
    this.floaters = new ArrayList<Floater>();
    this.collisions = new CollisionUtil(floaters);
    this.framebufferColor = color(18, 12, 24);
    floaters.add(this.ss);

    for (int i = 0; i < stars.length; i++) {
      stars[i] = new Star((int) (Math.random() * (width + 50)) - 50, (int) (Math.random() * (height + 50)) - 50, this);
    }

    fillEmpty();
  }

  protected void fillEmpty() {
    int cnt = 0;
    for (Floater floater : floaters) {
      if (floater instanceof Asteroid && !(floater instanceof AsteroidFragment)) cnt++;
    }
    for (int i = 0; i < ASTEROID_COUNT - cnt; i++) {
      Asteroid a = new Asteroid((int) random(width), (int) random(height), 0.02, this);
      // a.setSpeed((float) (Math.random() * 5) + 5);
      // a.setSpeedRotation((float) (Math.random() * 360));
      floaters.add(a);
    }
  }

  public Star[] getStars() {
    return stars;
  }

  public ArrayList<Floater> getFloaters() {
    return floaters;
  }

  protected void drawBackgroundPrelude() {
  }

  protected void drawPrimatives() {
    pushMatrix();
    translate(-mouseX / 32, -mouseY / 32);
    for (Star star : stars) {
      star.tick();
      star.draw();
    }
    ArrayList<Floater> pendingRemoval = new ArrayList<Floater>();
    for (Floater f : floaters) {
      if (!(f instanceof Debris)) continue;
      f.tick();
      f.draw();
      if (f.getRadius() == 0 || f.getX() > width + 50 || f.getX() < -50 || f.getY() > height + 50 || f.getX() < -50) pendingRemoval.add(f);
    }
    popMatrix();

    pushMatrix();
    translate(-mouseX / 64, -mouseY / 64);
    for (Floater f : floaters) {
      if ((f instanceof Debris)) continue;

      f.tick();
      f.draw();
      if (f.getRadius() == 0 || f.getX() > width + 5 || f.getX() < -5 || f.getY() > height + 5 || f.getX() < -5) pendingRemoval.add(f);
    }
    for (Floater f : pendingRemoval) floaters.remove(f);

    ss.processKeyboardInput();
    ss.tick();
    ss.draw();
    popMatrix();

    fillEmpty();
    collisions.performCollisionChecks();
    if (Math.random() < 0.25) {
      floaters.add(new Debris((int) width + random(20), (int) random(height) - 20, this));
    }
  }
}
