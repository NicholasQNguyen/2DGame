package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

  /** Constructor. 
   * 
   */
  public MenuPanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.white);
    this.setDoubleBuffered(true);
    this.addKeyListener(menuKeyHandler);
    this.setFocusable(true);
  }

  @Override
  void update(double delta) {
    // Exit on esc press
    System.out.println("UPDATING");
    System.out.println("ESC: " + this.menuKeyHandler.escPressed);
    if (menuKeyHandler.escPressed) {
      System.exit(0);
    }  
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    mtm.draw(g2);
    // Free up the memory after we draw
    g2.dispose();
  }

  @Override
  public void run() {
    while (thread != null) {
      if (Clock.getInstance().update()) {
        this.update(Clock.getInstance().getDelta());
        repaint();
      }
    }
  }
}  