package game;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/** Class to handle all gamepad input.
 *
 * @author Nicholas Nguyen
 *
 */
public class JoystickHandler {

  private Controller[] controllers;
  
  /** Constructor.
   *
   */
  public JoystickHandler() {
    /* Get the available controllers */
    controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
  }
  
  public Controller[] getControllers() {
    return this.controllers;
  }
}