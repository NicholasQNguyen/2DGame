package game;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tile.TileManager;

/** Contains main data and art loops.
 *
 * @author Nicholas Nguyen
 *
*/
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {

  final int originalTileSize = 16;
  final int scale = 3;
  public final int tileSize = originalTileSize * scale;

  // Screen Settings
  public final int maxScreenColumns = 16;
  public final int maxScreenRows = 12;
  public final int screenWidth = tileSize * maxScreenColumns;
  public final int screenHeight = tileSize * maxScreenRows;

  // World Settings
  public final int maxWorldColumns = 50;
  public final int maxWorldRows = 12;
  public final int worldWidth = this.maxWorldColumns * this.tileSize;
  public final int worldHeight = this.maxWorldRows * this.tileSize;

  final int fps = 60;

  Thread gameThread;
  KeyHandler keyHandler = new KeyHandler();
  public Player player = new Player(this, keyHandler);
  TileManager tm = new TileManager(this);
  public CollisionChecker collisionChecker = new CollisionChecker(this);
  
  /** Constructor.
   *
 */
  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.white);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyHandler);
    this.setFocusable(true);
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  // The game loop
  @Override
  public void run() {

    final double drawInterval = 1000000000 / fps;
    double delta = 0.0;
    long lastTime = System.nanoTime();
    long currentTime;

    while (gameThread != null) {
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / drawInterval;
      lastTime = currentTime;
      if (delta > 1) {

        update();
        repaint();

        delta--;
      }
    }
  }
  
  /** Main game data loop.
   * 
   */
  public void update() {
    // Exit on esc press
    if (keyHandler.escPressed) {
      System.exit(0);
    }  
    player.update();
    System.out.println("X: " + player.worldX);
    System.out.println("Y: " + player.worldY);
  }

  /** All of the drawing stuff.
   * 
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    tm.draw(g2);
    player.draw(g2);
    // Free up the memory after we draw
    g2.dispose();
  }
}
