package entity;

import game.GamePanel;
import game.GoblinKeyHandler;
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
    this.setDefaultValues();
    this.getImage();
  }
  
  private void setDefaultValues() {
    this.worldX = gamePanel.worldWidth / 2 - 400;
    this.worldY = 0;
    this.screenX = gamePanel.screenWidth / 2;
    this.screenY = gamePanel.screenHeight / 2;
    this.worldX += this.screenX;
    this.worldY += this.screenY;
    this.hp = 20;
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
    super.update(delta);
    super.checkCollision();
    // Move 1 pixel away to prevent being stuck forever
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

    this.screenX = worldX - this.gamePanel.player.offsetX;
    this.screenY = worldY - this.gamePanel.player.offsetY;

    this.facing = this.orient(this.gamePanel.player);
    System.out.println("GOBLIN: " + this.hp);
  }
}