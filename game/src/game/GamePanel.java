package game;

import entity.Entity;
import entity.Fireball;
import entity.Player;
import entity.Target;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import tile.TileManager;

/** Contains main data and art loops.
 *
 * @author Nicholas Nguyen
 *
*/
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {

  // Tile settings
  final int originalTileSize = 16;
  final int scale = 3;
  public final int tileSize = originalTileSize * scale;

  // Screen Settings
  public final int maxScreenColumns = 16;
  public final int maxScreenRows = 12;
  public final int screenWidth = tileSize * maxScreenColumns;
  public final int screenHeight = tileSize * maxScreenRows;

  // World Settings
  public final int maxWorldColumns = 32;
  public final int maxWorldRows = 17;
  public final int worldWidth = this.maxWorldColumns * this.tileSize;
  public final int worldHeight = this.maxWorldRows * this.tileSize;

  final int fps = 60;

  // In-game variables
  public final double gravity = .1;

  // Initialize some components
  Thread gameThread;
  KeyHandler keyHandler = new KeyHandler();
  public Player player = new Player(this, keyHandler);
  TileManager tm = new TileManager(this);
  public CollisionChecker collisionChecker = new CollisionChecker(this);
  
  // List to hold the enemies in the game
  private List<Entity> enemyList = new ArrayList<Entity>();
  // Spawn a dummy target in the middle of the world
  public Target target = new Target(this, ((worldWidth / 2) + 150), 400);

  /** Constructor.
   *
 */
  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.white);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyHandler);
    this.setFocusable(true);
    this.enemyList.add(target);
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
      // TODO Pass delta to update
      delta += (currentTime - lastTime) / drawInterval;
      lastTime = currentTime;
      if (delta > 1) {

        update(delta);
        repaint();

        delta--;
      }
    }
  }
  
  /** Main game data loop.
   * 
   */
  public void update(double delta) {
    // Exit on esc press
    if (keyHandler.escPressed) {
      System.exit(0);
    }  
    player.update();
    player.updateWindowOffset(screenWidth, screenHeight, worldWidth, worldHeight);
    for (Entity enemy : this.enemyList) {
      enemy.update();
    }
    for (Fireball f : player.fireballList) {
      f.update();
      if (player.fireballList.size() > 0) {
        if (this.collisionChecker.checkFireball(target, f)) {
          player.fireballList.remove(f);
        }
      }
    }
  }

  /** All of the drawing stuff.
   * 
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    tm.draw(g2);
    player.draw(g2);
    for (Fireball f : player.fireballList) {
      f.draw(g2);
    }
    target.draw(g2);
    // Free up the memory after we draw
    g2.dispose();
  }
}
