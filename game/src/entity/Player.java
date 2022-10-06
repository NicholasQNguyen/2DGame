package entity;

import game.GamePanel;
import game.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;


/** Class for the player controlled entity.
 *
 * @author Nicholas Nguyen 
 *
 */
public class Player extends Entity {
  public BufferedImage up1;
  public BufferedImage up2;
  public BufferedImage down1;
  public BufferedImage down2;
  public BufferedImage left1;
  public BufferedImage left2;
  public BufferedImage right1;
  public BufferedImage right2;

  KeyHandler keyHandler;
  public int screenX;
  public int screenY;
  public int offsetX;
  public int offsetY;
  private double jumpSpeed;
  private final long fireballTime = 550000;
  long fireballTimer;
  public List<Fireball> fireballList = new ArrayList<Fireball>();

  /** Constructor.
   *
   * @param gp The GamePanel
   * @param kh KeyHandler for keyboard input
   */
  public Player(GamePanel gp, KeyHandler kh) {
    this.gamePanel = gp;
    this.keyHandler = kh;
    setDefaultValues();
    getPlayerImage();
  }

  /** Helper function to constructor where we initialize some values.
   *
   */
  public void setDefaultValues() {
    this.worldX = gamePanel.worldWidth / 2 - 400;
    this.worldY = 100;
    // Start just off center X and above the ground Y
    this.screenX = gamePanel.screenWidth / 2;
    this.screenY = gamePanel.screenHeight / 2;
    this.worldX += this.screenX;
    this.worldY += this.screenY;
    this.jumpSpeed = 4.55;
    this.direction = "standing";
    this.accelX = 0.5;
    
    this.fireballTimer = 0;
    
    this.solidArea = new Rectangle(0, 0, gamePanel.tileSize, gamePanel.tileSize);
  }

  /** Load all of the player images from a stream.
   *
   */
  public void getPlayerImage() {
    try {
      up1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipBack1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipBack2.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipFront1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipFront2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipLeft1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipLeft2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipRight1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipRight2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Grab the keyboard input and change the player's position.
   * 
   */
  public void update() { 
    // Get starting time to get time elapsed
    final long start = System.nanoTime();
    
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

    if (keyHandler.spacePressed && this.fireballTimer < 0) {
      this.spitFire();
      this.fireballTimer = this.fireballTime;
    }
    
    // Apply gravity
    this.velocityY += gamePanel.gravity;
    // Check tile collision
    gamePanel.collisionChecker.checkTile(this);

    // Touching the ground
    if (this.bottomCollision) {
      this.velocityY = 0;
      switch (this.direction) {
        case "up":
          this.jump();
          break;
        case "right":
          if (this.velocityX < 5) {
            this.velocityX += this.accelX;
          }
          break;
        case "left":
          if (this.velocityX > -5) {
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
    
    if (this.leftCollision) {
      this.velocityX = 0;
      this.worldX += 1;
    } else if (this.rightCollision) {
      this.velocityX = 0;
      this.worldX -= 1;
    }
    
    // Reset the collisions
    this.bottomCollision = false;
    this.topCollision = false;
    this.leftCollision = false;
    this.rightCollision = false;

    // Apply velocity
    this.worldY += this.velocityY;
    this.worldX += this.velocityX;
    
    // Handle the fireballTimer
    this.fireballTimer -= (System.nanoTime() - start);

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
  }

  private void spitFire() {
    Fireball fireball = new Fireball(gamePanel, this.worldX, this.worldY, this.direction);
    fireballList.add(fireball);
  }

  private void jump() {
    this.velocityY -= this.jumpSpeed;
  }

  /** Handle of the the drawing of the player.
   *
   * @param g2 Graphics 2D from gamePanel
   */
  public void draw(Graphics2D g2) {
    BufferedImage image = null;

    switch (direction) {
      case "up":
        if (this.spriteNumber == 1) {
          image = up1;
        } else {
          image = up2;
        }
        break;
      case "down":
        if (this.spriteNumber == 1) {
          image = down1;
        } else {
          image = down2;
        }
        break;
      case "right":
        if (this.spriteNumber == 1) {
          image = right1;
        } else {
          image = right2;
        }
        break;
      case "left":
        if (this.spriteNumber == 1) {
          image = left1;
        } else {
          image = left2;
        }
        break;
      case "standing":
        if (this.spriteNumber == 1) {
          image = right1;
        } else {
          image = right2;
        }
        break;
      default:
        System.out.println("PROBLEM");
    }
    g2.drawImage(image, this.screenX, this.screenY, gamePanel.tileSize, gamePanel.tileSize, null);
  }
  
  /** Method to get the screen offset from world -> screen coordinates.
   *
   * @param screenSizeX Horizontal screen size
   * @param screenSizeY Vertical screen size
   * @param worldSizeX Horizontal total world size
   * @param worldSizeY Vertical total world size
   */
  public void updateWindowOffset(int screenSizeX, int screenSizeY, int worldSizeX, int worldSizeY) {
    this.offsetX = Math.min(Math.max(0, 
                                     this.worldX + (this.solidArea.width / 2) - (screenSizeX / 2)),
                            worldSizeX - screenSizeX);
    this.offsetY = Math.min(Math.max(0,
                                     this.worldY + (this.solidArea.height / 2) - (screenSizeY / 2)),
                            worldSizeY - screenSizeY);
  }
}
