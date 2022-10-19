package fsm;

import game.AbstractPanel;
import game.GamePanel;
import game.MenuPanel;
import javax.swing.JFrame;

/** Manager to handle if we're in game, menu, etc.
 *
 * @author Nicholas Nguyen
 *
 */
public class ScreenManager {
  private String state;
  private JFrame window;
  private GamePanel gamepanel;
  private MenuPanel menuPanel;
  AbstractPanel currentPanel;

  /** Constructor.
   *
   * @param window JFrame window from Main.
   */
  public ScreenManager(JFrame window) {
    this.window = window;
    this.state = "menu";
    final GamePanel gamePanel = new GamePanel();
    final MenuPanel menuPanel = new MenuPanel();
    window.add(gamePanel);
    window.add(menuPanel);
    this.currentPanel = menuPanel;
  }
    
  public void setState(String newState) {
    this.state = newState;
  }
  
  public String getState() {
    return this.state;
  }

  public void setPanel(AbstractPanel newPanel) {
    this.currentPanel = newPanel;
  }
  
  public AbstractPanel getPanel() {
    return this.currentPanel;
  }
}