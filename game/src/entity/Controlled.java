package entity;

import fsm.EntityState;
import game.ControlledKeyHandler;
import game.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** Abstract class for entities that are controlled.
 *
 * @author Nicholas Nguyen
 *
 */
public abstract class Controlled extends Entity {
  // Sprites + sprite stuff
  public BufferedImage up1;
  public BufferedImage up2;
  public BufferedImage down1;
  public BufferedImage down2;
  public BufferedImage left1;
  public BufferedImage left2;
  public BufferedImage right1;
  public BufferedImage right2;
  public BufferedImage leftBlock1;
  public BufferedImage leftBlock2;
  public BufferedImage rightBlock1;
  public BufferedImage rightBlock2;
  public int spriteCounter = 0;
  public int spriteNumber = 1;

  public List<Fireball> fireballList = new CopyOnWriteArrayList<Fireball>();
  final double fireballTime = .0001;
  double fireballTimer = 0;

  double jumpSpeed;
  EntityState state = new EntityState("standing");
  ControlledKeyHandler keyHandler;

  String facing;
  boolean blocking = false;

  /** Contructor.
   * 
   */
  public Controlled(GamePanel gp, ControlledKeyHandler kh) {
    super(gp);
    this.keyHandler = kh;
    this.jumpSpeed = 3.55;
    this.direction = "standing";
    this.accelX = 0.2;
    this.solidArea = new Rectangle(0, 0, gamePanel.tileSize - 8, gamePanel.tileSize + 2);
  }

  void jump() {
    this.velocityY -= this.jumpSpeed;
  }

  @Override
  public void update(double delta) {
    this.handleEvent();
    this.state.manageState(this.direction);
    this.gamePanel.collisionChecker.checkTile(this.worldX, this.worldY, this.solidArea, this);

    // Apply gravity
    this.velocityY += gamePanel.gravity;
    // Apply velocity
    this.worldX += this.velocityX;
    this.worldY += this.velocityY;
    // Handle the fireballTimer
    this.fireballTimer -= delta;
    // Cycle through the 2 animation frames
    this.spriteCounter++;
    if (this.spriteCounter > 10) {
      if (this.spriteNumber == 1) {
        this.spriteNumber = 2;
      } else {
        this.spriteNumber = 1;
      }
      spriteCounter = 0;
    }
    this.checkBlocking();
  }

  /** Deal with keyboard input.
   *
   */
  public void handleEvent() {
    if (keyHandler.upPressed) {
      direction = "up";
    } else if (keyHandler.downPressed) {
      direction = "down";
    } else if (keyHandler.leftPressed) {
      direction = "left";
    } else if (keyHandler.rightPressed) {
      direction = "right";
    } else {
      direction = "standing";
    }
    if (keyHandler.spacePressed && this.fireballTimer < 0 && !this.blocking) {
      this.spitFire();
      this.fireballTimer = this.fireballTime;
    }
  } 
  
  void checkCollision() {
    if (this.bottomCollision) {
      this.velocityY = 0;
      switch (this.state.getState()) {
        case "up":
          this.jump();
          break;
        case "right":
          if (this.velocityX < 3) {
            this.velocityX += this.accelX;
          }
          break;
        case "left":
          if (this.velocityX > -3) {
            this.velocityX -= this.accelX;
          }
          break;
        case "down":
        case "standing":
          this.velocityX = 0;
          break;
        default:
          System.out.println("PROBLEM");
      }
    }
  }

  /** Handle of the the drawing of the player.
   *
   * @param g2 Graphics 2D from gamePanel
   */
  public void draw(Graphics2D g2) {
    BufferedImage image = null;

    if (this.direction == "up") {
      if (this.spriteNumber == 1) {
        image = up1;
      } else {
        image = up2;
      }
    } else if (this.direction == "down") {
      if (this.spriteNumber == 1) {
        image = down1;
      } else {
        image = down2;
      }
    } else if (this.direction == "right") {
      if (this.getBlocking()) {
        if (this.spriteNumber == 1) {
          image = rightBlock1;
        } else {
          image = rightBlock2;
        }
      } else {
        if (this.spriteNumber == 1) {
          image = right1;
        } else {
          image = right2;
        }
      }
    } else if (this.direction == "left") {
      if (this.getBlocking()) {
        if (this.spriteNumber == 1) {
          image = leftBlock1;
        } else {
          image = leftBlock2;
        }
      } else {
        if (this.spriteNumber == 1) {
          image = left1;
        } else {
          image = left2;
        }
      }
    } else if (this.direction == "standing") {
      if (this.state.getLastFacing() == "left") {
        if (this.spriteNumber == 1) {
          image = left1;
        } else {
          image = left2;
        }
      } else {
        if (this.spriteNumber == 1) {
          image = right1;
        } else {
          image = right2;
        }
      }
    }
    g2.drawImage(image, this.screenX, this.screenY, gamePanel.tileSize, gamePanel.tileSize, null);
  }
  
  public int getWorldX() {
    return this.worldX;
  }

  public int getWorldY() {
    return this.worldY;
  }

  public int getScreenX() {
    return this.screenX;
  }

  public int getScreenY() {
    return this.screenY;
  }

  /** Instantiate and add a new fireball to the list.
   * 
   */
  void spitFire() {
    this.fireballList.add(new Fireball(gamePanel,
                                       this.worldX,
                                       this.worldY,
                                       this.facing));
    this.fireballTimer = this.fireballTime;
  }
  
  /** Change the direction it's facing to match the enemy's direction.
  *
  * @param enemy opponent
  */
  String orient(Controlled enemy) {
    if (enemy.worldX < this.worldX) {
      return "left";
    } else {
      return "right";
    }
  }
  
  /** Method to return opposites of directions. e.g. inputting "right" returns "left."
   *
   * @param direction direction currently facing
   * @return the opposite of that direction
   */
  String opposite(String direction) {
    if (direction != null) {
      switch (direction) {
        case "right":
          return "left";
        case "left":
          return "right";
        case "up":
          return "down";
        case "down":
          return "up";
        default:
          System.out.println("PROBLEM");
          return "YIKES";
      }
    } else {
      return "N/A";
    }
  }
  
  void checkBlocking() {
    if (opposite(this.facing) == this.state.getLastFacing()
        && (this.keyHandler.leftPressed || this.keyHandler.rightPressed)) {
      this.blocking = true;
    } else {
      this.blocking = false;
    }
  }
  
  public boolean getBlocking() {
    return this.blocking;
  }
}