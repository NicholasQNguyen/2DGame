package game;

import fsm.ScreenManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
  MenuListener menuListener = new MenuListener();
  boolean starting = false;

  /** Constructor. 
   * 
   */
  public MenuPanel() {
    super();
    this.addKeyListener(menuKeyHandler);
    button1.setBounds(50, 100, 95, 30);
    button1.addActionListener(menuListener);
    this.add(button1);
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
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    mtm.draw(g2);
    this.button1.repaint();
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