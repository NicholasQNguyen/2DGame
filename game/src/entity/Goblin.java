package entity;

import fsm.EntityState;
import game.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Moving enemy.
 *
 * @author Nicholas Nguyen
 *
 */
public class Goblin extends Entity {
  // Sprites + sprite stuff
  public BufferedImage up1;
  public BufferedImage up2;
  public BufferedImage down1;
  public BufferedImage down2;
  public BufferedImage left1;
  public BufferedImage left2;
  public BufferedImage right1;
  public BufferedImage right2;
  public int spriteCounter = 0;
  public int spriteNumber = 1;
  
  public double jumpSpeed;
  
  private EntityState state;

  /** Constructor.
   *
   */
  public Goblin(GamePanel gp, int x, int y) {
    this.gamePanel = gp;
    this.worldX = x;
    this.worldY = y;
    this.getImage();
    this.jumpSpeed = 4.55;
    this.direction = "standing";
    this.accelX = 0.5;
    this.solidArea = new Rectangle(0, 0, gamePanel.tileSize, gamePanel.tileSize);
    this.state = new EntityState("left");
    this.hp = 100;
  }
  
  private void getImage() {
    try {
      up1 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinUp1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinUp1.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinDown1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinDown2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinLeft1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinLeft2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinRight1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinRight2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(double delta) {
    // TODO Auto-generated method stub
    // Apply gravity
    this.velocityY -= this.gamePanel.gravity;
    gamePanel.collisionChecker.checkTile(this);
    this.worldX += this.velocityX;
    this.worldY -= this.velocityY;
    if (this.bottomCollision) {
      this.velocityY = 0;
    }
    this.screenX = worldX - this.gamePanel.player.offsetX;
    this.screenY = worldY - this.gamePanel.player.offsetY;
  }

  @Override
  public void draw(Graphics2D g2) {
    // TODO Auto-generated method stub
    BufferedImage image = null;

    switch (this.state.getState()) {
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
        break;
      default:
        System.out.println("PROBLEM");
    }
    g2.drawImage(image, this.screenX, this.screenY, gamePanel.tileSize, gamePanel.tileSize, null);
  }
}

