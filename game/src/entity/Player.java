package entity;

import game.GamePanel;
import game.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


/** Class for the player controlled entity.
 *
 * @author Nicholas Nguyen 
 *
 */
public class Player extends Entity {
  KeyHandler keyHandler;
  public int screenX;
  public int screenY;

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
    this.worldX = gamePanel.worldWidth / 2;
    // this.worldY = -25;
    this.worldY = 0;
    // Start just off center X and above the ground Y
    // this.screenX = gamePanel.screenWidth / 2;
    // this.screenY = gamePanel.tileSize * 9;
    this.screenX = gamePanel.screenWidth / 2;
    this.screenY = gamePanel.screenHeight / 2;
    this.worldX += this.screenX;
    this.worldY += this.screenY;
    // this.screenX = 0;
    // this.screenY = 0;
    this.speed = 4;
    this.direction = "down";
    
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
    if (keyHandler.upPressed == true) {
      direction = "up";
    } else if (keyHandler.downPressed == true) {
      direction = "down";
    } else if (keyHandler.leftPressed == true) {
      direction = "left";
    } else if (keyHandler.rightPressed == true) {
      direction = "right";
    } else {
      direction = "standing";
    }
    
    // Check tile collision
    collisionOn = false;
    gamePanel.collisionChecker.checkTile(this);
    
    if (collisionOn == false) {
      switch (this.direction) {
        case "up":
          this.worldY -= this.speed;
          // this.screenY -= this.speed;
          break;
        case "down":
          this.worldY += this.speed;
          // this.screenY += this.speed;
          break;
        case "left":
          this.worldX -= this.speed;
          // this.screenX -= this.speed;
          break;
        case "right":
          this.worldX += this.speed;
          // this.screenX += this.speed;
          break;
        case "standing":
          break;
        default:
          break;
      }
    }

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
}
