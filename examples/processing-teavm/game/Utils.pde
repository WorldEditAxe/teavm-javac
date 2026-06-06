import java.util.*;

// Pretty much all things "game engine" are
// located in this file. We store helper classes
// here to assist with game development.

public class CollisionUtil {
  protected ArrayList<Floater> floaters;

  public CollisionUtil(ArrayList<Floater> floaters) {
    this.floaters = floaters;
  }

  public void updateFloatersRef(ArrayList<Floater> floaters) {
    this.floaters = floaters;
  }

  // this took so long you don't even know TwT
  public void performCollisionChecks() {
    ArrayList<Floater> active = new ArrayList<Floater>();
    ArrayList<Floater> inMotion = new ArrayList<Floater>();

    for (Floater floater : floaters) {
      if ((floater.x > -10 && floater.x <= width + 10)
        && (floater.y > -10 && floater.y <= height + 10)) {
        active.add(floater);
        inMotion.add(floater);
      }
    }

    for (int i = 0; i < inMotion.size(); i++) {
      Floater floater1 = inMotion.get(i);
      for (int j = i + 1; j < active.size(); j++) {
        Floater floater2 = active.get(j);
        if (floater1 == floater2) continue;

        float dx = floater1.getEffectivePhysicsX() - floater2.getEffectivePhysicsX();
        float dy = floater1.getEffectivePhysicsY() - floater2.getEffectivePhysicsY();
        float minDist = floater1.getRadius() + floater2.getRadius();

        float actualDist = sqrt((dx * dx) + (dy * dy));
        if (actualDist < minDist) {
          float nx = dx / actualDist;
          float ny = dy / actualDist;

          float speed1 = floater1.getSpeed();
          float angle1 = radians(floater1.getSpeedRotation());
          float vx1 = speed1 * cos(angle1);
          float vy1 = speed1 * sin(angle1);

          float speed2 = floater2.getSpeed();
          float angle2 = radians(floater2.getSpeedRotation());
          float vx2 = speed2 * cos(angle2);
          float vy2 = speed2 * sin(angle2);

          float dvx = vx1 - vx2;
          float dvy = vy1 - vy2;

          float vn = dvx * nx + dvy * ny;
          if (vn > 0) continue;

          float m1 = floater1.getMass();
          float m2 = floater2.getMass();
          float e = 1.0f;

          {
            float j1 = -(1 + e) * vn / (1/m1 + 1/m2);

            float v1ImpulseX = (j1 / m1) * nx;
            float v1ImpulseY = (j1 / m1) * ny;
            vx1 += v1ImpulseX;
            vy1 += v1ImpulseY;

            float v2ImpulseX = -(j1 / m2) * nx;
            float v2ImpulseY = -(j1 / m2) * ny;
            vx2 += v2ImpulseX;
            vy2 += v2ImpulseY;
          }

          floater1.setSpeed(sqrt(vx1 * vx1 + vy1 * vy1));
          floater1.setSpeedRotation(degrees(atan2(vy1, vx1)));
          floater2.setSpeed(sqrt(vx2 * vx2 + vy2 * vy2));
          floater2.setSpeedRotation(degrees(atan2(vy2, vx2)));

          floater1.onCollision(floater2, floater1.getSpeed() * floater1.getMass());
          floater2.onCollision(floater1, floater2.getSpeed() * floater2.getMass());
        }
      }
    }
  }
}


public class SceneManager {
  private Scene activeScene;

  public Scene getScene() {
    return this.activeScene;
  }
  public void setScene(Scene s) {
    this.activeScene = s;
    s.setup();
  }
}

public static class UserInputManager {
  private static ArrayList<Character> heldKeys = new ArrayList<Character>();

  public static void keyDown(char c) {
    if (!heldKeys.contains((Character) c)) heldKeys.add((Character) c);
  }

  public static void keyUp(char c) {
    heldKeys.remove((Character) c);
  }

  public static boolean isKeyDown(char c) {
    return heldKeys.contains((Character) c);
  }

  public static int getHeldKeyAmount() {
    return heldKeys.size();
  }

  public static void clearHeldKeys() {
    heldKeys.clear();
  }

  public static ArrayList<Character> getHeldKeys() {
    return new ArrayList<Character>(heldKeys);
  }
}

public static class DeferredTaskRunner {
  private static HashMap<Runnable, Long> tasks = new HashMap<Runnable, Long>();

  public static void addTask(Runnable r, long delay) {
    tasks.put(r, (Long) (System.currentTimeMillis() + delay));
  }

  public static void flush() {
    tasks.clear(); // this might break some things
  }

  public static void runTasks() {
    ArrayList<Runnable> finished = new ArrayList<Runnable>();
    for (Runnable task : tasks.keySet()) {
      if (System.currentTimeMillis() >= tasks.get(task)) {
        task.run();
        finished.add(task);
      }
    }
    for (Runnable task : finished) {
      tasks.remove(task);
    }
  }
}

public interface Scene {
  void setup();
  void draw();
}

public interface Battlefield {
  ArrayList<Floater> getFloaters();
}

public class Shaders {
  private ArrayList<Shader> pipeline;
  private ArrayList<Shader> basePipeline;

  public Shaders(ArrayList<Shader> basePipeline) {
    this.basePipeline = basePipeline;
    pipeline = new ArrayList(basePipeline);
  }

  public Shaders() {
    this.basePipeline = new ArrayList();
    pipeline = new ArrayList();
  }

  public void addShader(Shader s) {
    pipeline.add(s);
  }

  public void removeShader(Shader s) {
    pipeline.remove(s);
  }

  public void clear() {
    pipeline.clear();
  }

  public ArrayList<Shader> getPipeline() {
    return pipeline;
  }

  public void reset() {
    ArrayList<Shader> arr = new ArrayList();
    for (Shader shader : basePipeline) arr.add(shader);
    this.pipeline = arr;
  }

  public color[] process(color[] fb) {
    for (Shader s : pipeline) fb = s.processFramebuffer(fb);
    return fb;
  }
}

public interface Shader {
  color[] processFramebuffer(color[] fb);
}

// 2lazy2code
// thanks https://cglab.ca/~sander/misc/ConvexGeneration/ValtrAlgorithm.java

public static class Point2D {
  protected float x, y;

  public Point2D(float x, float y) {
    this.x = x;
    this.y = y;
  }


  public Point2D(double x, double y) {
    this.x = (float) x;
    this.y = (float) y;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }
}

public static List<Point2D> generateRandomConvexPolygon(int n) {
  Random RAND = new Random();
  List<Double> xPool = new ArrayList<Double>(n);
  List<Double> yPool = new ArrayList<Double>(n);

  for (int i = 0; i < n; i++) {
    xPool.add(RAND.nextDouble());
    yPool.add(RAND.nextDouble());
  }

  Collections.sort(xPool);
  Collections.sort(yPool);

  Double minX = xPool.get(0);
  Double maxX = xPool.get(n - 1);
  Double minY = yPool.get(0);
  Double maxY = yPool.get(n - 1);

  List<Double> xVec = new ArrayList<Double>(n);
  List<Double> yVec = new ArrayList<Double>(n);

  double lastTop = minX, lastBot = minX;

  for (int i = 1; i < n - 1; i++) {
    double x = xPool.get(i);

    if (RAND.nextBoolean()) {
      xVec.add(x - lastTop);
      lastTop = x;
    } else {
      xVec.add(lastBot - x);
      lastBot = x;
    }
  }

  xVec.add(maxX - lastTop);
  xVec.add(lastBot - maxX);

  double lastLeft = minY, lastRight = minY;

  for (int i = 1; i < n - 1; i++) {
    double y = yPool.get(i);

    if (RAND.nextBoolean()) {
      yVec.add(y - lastLeft);
      lastLeft = y;
    } else {
      yVec.add(lastRight - y);
      lastRight = y;
    }
  }

  yVec.add(maxY - lastLeft);
  yVec.add(lastRight - maxY);
  Collections.shuffle(yVec);

  List<Point2D> vec = new ArrayList<Point2D>(n);
  for (int i = 0; i < n; i++) {
    vec.add(new Point2D(xVec.get(i), yVec.get(i)));
  }
  Collections.sort(vec, new Comparator<Point2D>() {
      @Override
      public int compare(Point2D v1, Point2D v2) {
          double angle1 = Math.atan2(v1.getY(), v1.getX());
          double angle2 = Math.atan2(v2.getY(), v2.getX());
          return Double.compare(angle1, angle2);
      }
  });
  double x = 0, y = 0;
  double minPolygonX = 0;
  double minPolygonY = 0;
  List<Point2D> points = new ArrayList<Point2D>(n);

  for (int i = 0; i < n; i++) {
    points.add(new Point2D(x, y));

    x += vec.get(i).getX();
    y += vec.get(i).getY();

    minPolygonX = Math.min(minPolygonX, x);
    minPolygonY = Math.min(minPolygonY, y);
  }

  double xShift = minX - minPolygonX;
  double yShift = minY - minPolygonY;

  for (int i = 0; i < n; i++) {
    Point2D p = points.get(i);
    points.set(i, new Point2D(p.x + xShift, p.y + yShift));
  }

  return points;
}
