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
  boolean checked = false;
  
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
    player.fireballUpdate();
    goblin.fireballUpdate();
    camera.updateWindowOffset(screenWidth, screenHeight, worldWidth, worldHeight);
    if (!checked) {
      this.fireballCollision(player, goblin);
      this.fireballCollision(goblin, player);
      this.checked = false;
    }
    
    if (this.player.getHp() <= 0) {
      this.player.iterateRounds();
      if (this.player.getRounds() == 5) {
        ScreenManager.displayVictor("goblin");
        if (ScreenManager.desiredState != "menu") {
          ScreenManager.chooseRun("menu");
        }
      } else {
        this.reset();
      }
    } else if (this.goblin.getHp() <= 0) {
      this.goblin.iterateRounds();
      if (this.goblin.getRounds() == 5) {
        ScreenManager.displayVictor("mudkip");
        if (ScreenManager.desiredState != "menu") {
          ScreenManager.chooseRun("menu");
        }
      } else {
        this.reset();
      }
    }
  }

  private void reset() {
    this.player.setHp(20);
    this.goblin.setHp(20);
    this.player.worldX = this.worldWidth / 2 - 1200;
    this.player.worldY = 0;
    this.goblin.worldX = this.worldWidth / 2 - 400;
    this.goblin.worldY = 0;
    this.player.screenX = this.screenWidth / 2;
    this.player.screenY = this.screenHeight / 2;
    this.player.screenX = this.screenWidth / 2;
    this.player.screenY = this.screenHeight / 2;
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
      if (player.fireballList.size() > 0) {
        if (this.collisionChecker.checkFireball(target, f)) {
          player.fireballList.remove(f);
          this.checked = true;
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