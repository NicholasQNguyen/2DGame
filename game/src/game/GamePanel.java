package game;

import entity.Controlled;
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
import tile.TileManager;

/** Contains main data and art loops.
 *
 * @author Nicholas Nguyen
 *
*/
public class GamePanel extends AbstractPanel implements Runnable {
  private static final long serialVersionUID = 8456515379314064814L;

  // In-game variables
  public final double gravity = .1;

  // Initialize some components
  PlayerKeyHandler playerKeyHandler = new PlayerKeyHandler();
  GoblinKeyHandler goblinKeyHandler = new GoblinKeyHandler();
  public Player player = new Player(this, playerKeyHandler);
  public Goblin goblin = new Goblin(this, goblinKeyHandler);
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
    this.addKeyListener(playerKeyHandler);
    this.addKeyListener(goblinKeyHandler);
    this.setFocusable(true);
    // TODO Get controllers working.
    // this.jsHandler = new JoystickHandler();

    // Spawn a dummy target in the middle of the world
    for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 5 + 1); i++) {
      enemyList.add(new Target(this,
                           ((worldWidth / 2) + 150) + ThreadLocalRandom.current().nextInt(-150, 50),
                               400 + ThreadLocalRandom.current().nextInt(-150, 50)));
    }
  }

  /** Main game data loop.
   * 
   */
  public void update(double delta) {
    // Exit on esc press
    if (playerKeyHandler.escPressed) {
      System.exit(0);
    }  
    player.update(Clock.getInstance().getDelta());
    goblin.update(Clock.getInstance().getDelta());
    player.updateWindowOffset(screenWidth, screenHeight, worldWidth, worldHeight);
    for (Entity enemy : this.enemyList) {
      enemy.update(Clock.getInstance().getDelta());
    }
    this.fireballCollision(player, goblin);
    this.fireballCollision(goblin, player);
    
    if (this.player.getHp() <= 0) {
      System.out.println("GOBLIN WINS");
      System.exit(0);
    } else if (this.goblin.getHp() <= 0) {
      System.out.println("MUDKIP WINS");
      System.exit(0); 
    }
  }

  /** Draw all of the elements.
   * 
   */
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    tm.draw(g2);
    player.draw(g2);
    goblin.draw(g2);
    for (Fireball f : player.fireballList) {
      f.draw(g2);
    }
    for (Fireball f : goblin.fireballList) {
      f.draw(g2);
    }
    for (Entity enemy : this.enemyList) {
      enemy.draw(g2);
    }
    // Free up the memory after we draw
    g2.dispose();
  }

  private void fireballCollision(Controlled player, Controlled target) {
    for (Fireball f : player.fireballList) {
      f.update(Clock.getInstance().getDelta());
      if (player.fireballList.size() > 0) {
        for (Entity enemy : this.enemyList) {
          if (this.collisionChecker.checkFireball(enemy, f)) {
            player.fireballList.remove(f);
            enemy.takeDamage(f.damage);
            System.out.println(enemy.hp);
            if (enemy.hp <= 0) {
              this.enemyList.remove(enemy);
            }
          }
          if (this.collisionChecker.checkFireball(target, f)) {
            player.fireballList.remove(f);
            if (!target.getBlocking()) {
              target.takeDamage(f.damage);
              System.out.println(enemy.hp);
            }
          }
        }
      }
    }
  }

  @Override
  public void run() {
    System.out.println("RUNNING");
    super.run();
  }
}
