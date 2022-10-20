package fsm;

import game.GamePanel;
import game.Main;
import game.MenuPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
      gamePanel.setVisible(false);
      gamePanel.stopGameThread();
      menuPanel.setVisible(true);
      menuPanel.requestFocus();
    }
    if (desiredState == "game") {
      menuPanel.setVisible(false);
      gamePanel.startGameThread();
      window.add(gamePanel);
      gamePanel.requestFocus();
      // menuPanel.stopGameThread();
    }
  }

  /** Determine which victory popup to display.
   *
   * @param victor whether goblin or mudkip won
   */
  public static void displayVictor(String victor) {
    if (victor == "mudkip") {
      JOptionPane.showMessageDialog(window, "Mudkip Wins!");
    } else {
      JOptionPane.showMessageDialog(window, "Goblin Wins!");
      
    }
  }
}
