package game;

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
  
  Thread thread;

  public AbstractPanel() {
  }

  public void startGameThread() {
    thread = new Thread(this);
    thread.start();
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