package game;

import fsm.ScreenManager;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import tile.MenuTileManager;

/** A panel to run the main menu.
 *
 * @author Nicholas Nguyen
 *
 */
public class MenuPanel extends AbstractPanel implements Runnable {
  private static final long serialVersionUID = 326275584165267040L;

  MenuKeyHandler menuKeyHandler = new MenuKeyHandler();
  MenuTileManager mtm = new MenuTileManager(this);
  
  JButton button1 = new JButton("Start");
  JButton button2 = new JButton("How to Play");
  MenuListener menuListener = new MenuListener();
  MenuListenerHowTo menuListenerHowTo = new MenuListenerHowTo();
  boolean starting = false;

  /** Constructor. 
   * 
   */
  public MenuPanel() {
    super();
    this.addKeyListener(menuKeyHandler);
    button1.addActionListener(menuListener);
    button2.addActionListener(menuListenerHowTo);
    this.add(button1);
    this.add(button2);
  }

  @Override
  void update(double delta) {
    // Exit on esc press
    if (menuKeyHandler.escPressed) {
      System.exit(0);
    }
    if (this.starting) {
      ScreenManager.chooseRun("game");
    }
    if (this.menuListener.starting) {
      this.starting = true;
    }
    this.requestFocus();
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    mtm.draw(g2);
    this.button1.repaint();
    this.button2.repaint();
    // Free up the memory after we draw
    g2.dispose();
  }

  @Override
  public void run() {
    if (!this.starting) {
      super.run();
    }
  }
}  