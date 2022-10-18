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

  MenuKeyHandler menuKeyHandler = new MenuKeyHandler();

  /** Constructor. 
   * 
   */
  public MenuPanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.white);
    this.setDoubleBuffered(true);
    this.setFocusable(true);
    this.addKeyListener(menuKeyHandler);
  }

  public void startGameThread() {
    thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run() {
  }

  @Override
  void update(double delta) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void paintComponent(Graphics g) {
    // TODO Auto-generated method stub
  }
}
