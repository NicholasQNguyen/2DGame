package game;

import entity.Camera;
import entity.Controlled;
import entity.Fireball;
import entity.Goblin;
import entity.Player;
import fsm.ScreenManager;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
  public Camera camera = new Camera(this);
  TileManager tm = new TileManager(this);
  public CollisionChecker collisionChecker = new CollisionChecker(this);
  
  /** Constructor.
   *
   */
  public GamePanel() {
    super();
    this.addKeyListener(playerKeyHandler);
    this.addKeyListener(goblinKeyHandler);
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
    camera.update(Clock.getInstance().getDelta());
    camera.updateWindowOffset(screenWidth, screenHeight, worldWidth, worldHeight);
    this.fireballCollision(player, goblin);
    this.fireballCollision(goblin, player);
    
    if (this.player.getHp() <= 0) {
      // System.out.println("GOBLIN WINS");
      ScreenManager.displayVictor("goblin");
      if (ScreenManager.desiredState != "menu") {
        ScreenManager.chooseRun("menu");
      }
    } else if (this.goblin.getHp() <= 0) {
      // System.out.println("MUDKIP WINS");
      ScreenManager.displayVictor("mudkip");
      if (ScreenManager.desiredState != "menu") {
        ScreenManager.chooseRun("menu");
      }
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
    // Free up the memory after we draw
    g2.dispose();
  }

  private void fireballCollision(Controlled player, Controlled target) {
    for (Fireball f : player.fireballList) {
      f.update(Clock.getInstance().getDelta());
      if (player.fireballList.size() > 0) {
        if (this.collisionChecker.checkFireball(target, f)) {
          player.fireballList.remove(f);
          if (!target.getBlocking()) {
            target.takeDamage(f.damage);
          }
        }
      }
    }
  }

  @Override
  public void run() {
    super.run();
  }
}