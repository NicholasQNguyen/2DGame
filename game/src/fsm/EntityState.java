package fsm;

import java.util.HashMap;
import java.util.Map;

/** FSM that handles the states for entities like the player.
 *
 * @author Nicholas Nguyen
 *
 */
public class EntityState {
  public HashMap<String, Boolean> movement = new HashMap<String, Boolean>();
  private String state;
  private String lastFacing;
  
  /** Returns the state.
   *
   * @return The state we're in
   */
  public String getState() {
    return this.state;
  }

  public void setState(String newState) {
    this.state = newState;
  }

  /** Constructor.
   *
   * @param state initial state
   */
  public EntityState(String state) {
    setState(state);
    lastFacing = state;
    // Fill up the movement map
    movement.put("up", false);
    movement.put("down", false);
    movement.put("left", false);
    movement.put("right", false);
    movement.put("standing", false);
  }
  
  /** Returns the direction we're currently facing and resets the lastFacing.
   *
   */
  public String getFacing() {
    for (Map.Entry<String, Boolean> e : this.movement.entrySet()) {
      if (e.getKey() == "up" && e.getValue()) {
        this.lastFacing = "up";
      } else if (e.getKey() == "down" && e.getValue()) {
        this.lastFacing = "down";
      } else if (e.getKey() == "left" && e.getValue()) {
        this.lastFacing = "left";
      } else if (e.getKey() == "right" && e.getValue()) {
        this.lastFacing = "right";
      } else {
        this.lastFacing = "standing";
      }
    }
    return this.lastFacing;
  }
  
  /** Handle the movement from input.
   *
   * @param action String with the direction inputted
   */
  public void manageState(String action) {
    for (Map.Entry<String, Boolean> e : this.movement.entrySet()) {
      // Reset all of the states
      e.setValue(false);
      if (e.getKey() == action) {
        e.setValue(true);
        this.state = action;
      }
    }
  }
}
