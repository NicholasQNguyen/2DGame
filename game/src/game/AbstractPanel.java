package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/** Abstract class for the panels.
 *
 * @author Nicholas Nguyen
 *
 */
public abstract class AbstractPanel extends JPanel implements Runnable {
  private static final long serialVersionUID = 139014299663258784L;
  // Tile settings
  final int originalTileSize = 16;
  final int scale = 3;
  public final int tileSize = this.originalTileSize * this.scale;

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
  
  public Thread thread = new Thread(this);

  /** Constructor.
   * 
   */
  public AbstractPanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setDoubleBuffered(true);
    this.setBackground(Color.BLUE);
    this.setFocusable(true);
  }

  public void startGameThread() {
    thread.start();
  }
  
  /** Pauses the game thread.
   * 
   */
  public void stopGameThread() {
    try {
      Thread.sleep(10000000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  abstract void update(double delta);

  protected abstract void paintComponent(Graphics g);

  /** The thread to run.
   * 
   */
  public void run() {
    while (thread != null) {
      if (Clock.getInstance().update()) {
        this.update(Clock.getInstance().getDelta());
        repaint();
      }
    }
  }
}