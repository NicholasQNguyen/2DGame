package fsm;

import game.GamePanel;
import game.GoblinKeyHandler;
import game.Main;
import game.MenuPanel;
import game.PlayerKeyHandler;

import javax.swing.JFrame;

/** Manager to handle if we're in game, menu, etc.
 *
 * @author Nicholas Nguyen
 * 
 *
 */
public class ScreenManager {
  public static JFrame window;
  static GamePanel gamePanel = Main.gp;
  static MenuPanel menuPanel = Main.mp;

  /** Constructor.
   *
   */
  public ScreenManager() {
    window = Main.window;
    window.add(menuPanel);
    // window.add(gamePanel);
    menuPanel.startGameThread();
    // gamePanel.startGameThread();
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
      gamePanel.startGameThread();
      window.add(gamePanel);
      gamePanel.requestFocus();
      // menuPanel.stopGameThread();
    }
  }
}
