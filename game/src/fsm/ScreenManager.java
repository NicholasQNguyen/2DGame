package fsm;

import game.AbstractPanel;
import game.GamePanel;
import game.Main;
import game.MenuPanel;
import javax.swing.JFrame;

/** Manager to handle if we're in game, menu, etc.
 *
 * @author Nicholas Nguyen
 * 
 *
 */
public class ScreenManager {
  static String state;
  public static JFrame window;
  static GamePanel gamePanel = Main.gp;
  static MenuPanel menuPanel = Main.mp;
  static AbstractPanel currentPanel;

  /** Constructor.
   *
   * @param window JFrame window from Main.
   */
  public ScreenManager(JFrame window) {
    ScreenManager.window = window;
  }
    
  public void setState(String newState) {
    state = newState;
  }
  
  public String getState() {
    return state;
  }

  public void setPanel(AbstractPanel newPanel) {
    currentPanel = newPanel;
  }
  
  public AbstractPanel getPanel() {
    return currentPanel;
  }
  
  /** Changes which program we run based on which state we're in.
   *
   */
  public static void chooseRun(String desiredState) {
    if (desiredState  == "menu") {
      menuPanel.run();
    }
    if (desiredState == "game") {
      menuPanel.setVisible(false);
      window.add(gamePanel);
      gamePanel.run();
    }
  }
}
