package entity;

import fsm.EntityState;
import game.GamePanel;
import game.GoblinKeyHandler;

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
public class Goblin extends Controlled {

  /** Constructor.
   *
   */
  public Goblin(GamePanel gp, GoblinKeyHandler kh) {
    super(gp, kh);
    this.worldX = (this.gamePanel.worldWidth / 2);
    this.worldY = (this.gamePanel.worldHeight / 2);
    this.getImage();
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
  public void handleEvent() {
    super.handleEvent();
  }

  @Override
  public void update(double delta) {
    super.update(delta);
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

