package game;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import net.java.games.input.Event;

/** Class to handle all gamepad input.
 *
 * @author Nicholas Nguyen
 *
 */
public class JoystickHandler {
  private ControllerManager controllers;

  /** Constructor.
   *
   */
  public JoystickHandler() {
    this.controllers = new ControllerManager();
    this.controllers.initSDLGamepad();
  }

  public ControllerState getControllerState() {
    System.out.println(this.controllers.getNumControllers());
    return this.controllers.getState(0);
  }
}