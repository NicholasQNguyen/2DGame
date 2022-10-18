package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

/** A panel to run the main menu.
 *
 * @author Nicholas Nguyen
 *
 */
public class MenuPanel extends AbstractPanel implements Runnable {
  /** Constructor. 
   * 
   */
  public MenuPanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.white);
    this.setDoubleBuffered(true);
    this.setFocusable(true);
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    
  }

}
