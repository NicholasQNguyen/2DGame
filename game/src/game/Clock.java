package game;


/** Class to handle the game's clock and FPS.
 * 
 */
public class Clock {
  static C instance;
  
  /** Generate the instance if not instantiated or just return the instance.
   * 
   */
  public static C getInstance() {
    if (instance != null) {
      return instance;
    } else {
      instance = new C();
      return instance;
    }
  }

  /** Class for the clock.
   *
   * @author Nicolas Nguyen 
   *
   */
  public static class C {
    private double delta;
    private long lastTime;
    private long currentTime;
    private final int fps;
    private final double drawInterval;

    /** Constructor.
     * 
     */
    public C() {
      this.delta = 0.0;
      this.lastTime = System.nanoTime();
      this.fps = 60;
      this.drawInterval = 1000000000 / fps;
    }
    
    /** Update the delta and return a bool if it's > 1 or not.
     *
     * @return true if delta > 1 or false if 
     */
    boolean update() {
      currentTime = System.nanoTime();
      delta += (this.currentTime - this.lastTime) / this.drawInterval;
      this.lastTime = this.currentTime;
      if (this.delta > 1) {
        this.delta--;
        return true;
      } else {
        return false;
      }
    }

    public double getDelta() {
      return this.delta;
    }
  }
}