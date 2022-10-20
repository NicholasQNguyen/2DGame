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
  public static String desiredState;

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
    ScreenManager.desiredState = desiredState;
    if (ScreenManager.desiredState  == "menu") {
      menuPanel = new MenuPanel();
      gamePanel.setVisible(false);
      menuPanel.setVisible(true);
      menuPanel.startGameThread();
      window.add(menuPanel);
      menuPanel.requestFocus();
      gamePanel.thread = null;
      gamePanel = null;
    }
    if (ScreenManager.desiredState == "game") {
      gamePanel = new GamePanel();
      menuPanel.setVisible(false);
      gamePanel.setVisible(true);
      gamePanel.startGameThread();
      window.add(gamePanel);
      gamePanel.requestFocus();
      menuPanel.thread = null;
      menuPanel = null;
    }
  }

  /** Method to display a victory pop up window.
   *
   * @param victor "goblin" or "mudkip"
   */
  public static void displayVictor(String victor) {
    if (victor == "mudkip") { 
      Target: main 

      JOptionPane.showConfirmDialog(gamePanel,
                                    "MUDKIP WINS",
                                    "VICTORY",
                                    JOptionPane.OK_CANCEL_OPTION);
    } else {
      JOptionPane.showConfirmDialog(gamePanel,
                                    "GOBLIN WINS",
                                    "VICTORY",
                                    JOptionPane.OK_CANCEL_OPTION);
    }
  }
}
