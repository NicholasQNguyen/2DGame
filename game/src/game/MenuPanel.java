package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/** A panel to run the main menu.
 *
 * @author Nicholas Nguyen
 *
 */
public class MenuPanel extends AbstractPanel implements Runnable {
  private static final long serialVersionUID = 326275584165267040L;

  MenuKeyHandler menuKeyHandler = new MenuKeyHandler();

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
    if (menuKeyHandler.escPressed) {
      System.exit(0);
    }  
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void paintComponent(Graphics g) {
    // TODO Auto-generated method stub
  }
}
