package game;

import entity.Entity;
import entity.Fireball;
import entity.Goblin;
import entity.Player;
import entity.Target;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

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

  // In-game variables
  public final double gravity = .1;

  // Initialize some components
  Thread gameThread;
  KeyHandler keyHandler = new KeyHandler();
  public Player player = new Player(this, keyHandler);
  TileManager tm = new TileManager(this);
  public CollisionChecker collisionChecker = new CollisionChecker(this);
  // TODO Get controllers working
  // JoystickHandler jsHandler;
  
  // List to hold the enemies in the game
  private List<Entity> enemyList = new CopyOnWriteArrayList<Entity>();

  /** Constructor.
   *
 */
  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.white);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyHandler);
    this.setFocusable(true);
    // TODO Get controllers working.
    // this.jsHandler = new JoystickHandler();

    // Spawn a dummy target in the middle of the world
    for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 5 + 1); i++) {
      enemyList.add(new Target(this,
                           ((worldWidth / 2) + 150) + ThreadLocalRandom.current().nextInt(-150, 50),
                               400 + ThreadLocalRandom.current().nextInt(-150, 50)));
    }
    enemyList.add(new Goblin(this, worldWidth / 2, 400));
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  // The game loop
  @Override
  public void run() {

    while (gameThread != null) {
      if (Clock.getInstance().update()) {

        update(Clock.getInstance().getDelta());
        repaint();
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
        for (Entity enemy : this.enemyList) {
          if (this.collisionChecker.checkFireball(enemy, f)) {
            player.fireballList.remove(f);
            enemy.takeDamage(f.damage);
            if (enemy.hp <= 0) {
              this.enemyList.remove(enemy);
            }
            System.out.println(enemy.hp);
            System.out.println(enemyList);
          }
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
    for (Entity enemy : this.enemyList) {
      enemy.draw(g2);
    }
    // Free up the memory after we draw
    g2.dispose();
  }
}
