package fsm;

/** FSM for the screen states.
 *
 * @author Nicholas Nguyen
 *
 */
public class ScreenState {

  static StateInstance instance;
  
  /** Instantiates an instance of ScreenState or just return it.
   *
   * @return THE instance of ScreenState
   */
  public static StateInstance getInstance() {
    if (instance != null) {
      return instance;
    } else {
      instance = new StateInstance();
      return instance;
    }
  }
    
  /** Singleton instance that handles what screen we're on.
   *
   * @author Nicholas Nguyen
   *
   */
  public static class StateInstance {
    private String state = "main menu";
    
    public void setState(String newState) {
      this.state = newState;
    }
    
    public String getState() {
      return this.state;
    }
  }
}