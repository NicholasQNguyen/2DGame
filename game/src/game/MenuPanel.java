package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/** A panel to run the main menu.
 *
 * @author Nicholas Nguyen
 *
 */
public class MenuPanel extends JPanel implements Runnable {
  // Tile settings
  final int originalTileSize = 16;
  final int scale = 3;
  public final int tileSize = originalTileSize * scale;
  // Screen Settings
  public final int maxScreenColumns = 32;
  public final int maxScreenRows = 14;
  public final int screenWidth = tileSize * maxScreenColumns;
  public final int screenHeight = tileSize * maxScreenRows;

  // World Settings
  public final int maxWorldColumns = 32;
  public final int maxWorldRows = 17;
  public final int worldWidth = this.maxWorldColumns * this.tileSize;
  public final int worldHeight = this.maxWorldRows * this.tileSize;

  /** Constructor. 
   * 
   */
  public MenuPanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.white);
    this.setDoubleBuffered(true);
    this.setFocusable(true);
  }

  public MenuPanel(LayoutManager layout) {
    super(layout);
    // TODO Auto-generated constructor stub
  }

  public MenuPanel(boolean isDoubleBuffered) {
    super(isDoubleBuffered);
    // TODO Auto-generated constructor stub
  }

  public MenuPanel(LayoutManager layout, boolean isDoubleBuffered) {
    super(layout, isDoubleBuffered);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    
  }

}
