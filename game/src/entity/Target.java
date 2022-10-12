package entity;

import game.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Dummy target for testing mostly probably.
 *
 * @author Nicholas Nguyen
 *
 */
public class Target extends Entity {
  
  BufferedImage image;

  /** Constructor.
   *
   * @param gp GamePanel
   * @param x Starting X
   * @param y Starting Y
   */
  public Target(GamePanel gp, int x, int y) {
    this.gamePanel = gp;
    this.worldX = x;
    this.worldY = y;
    this.screenX = worldX - this.gamePanel.player.offsetX;
    this.screenY = worldY - this.gamePanel.player.offsetY;
    this.solidArea = new Rectangle(0, 0, gamePanel.tileSize - 30, gamePanel.tileSize - 30);
    
    this.hp = 50;
    
    getImage();
  }

  /** Load in the target image.
   *
   */
  public void getImage() {
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/enemy/target.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update() {
    this.screenX = worldX - this.gamePanel.player.offsetX;
    this.screenY = worldY - this.gamePanel.player.offsetY;
  }

  public void draw(Graphics2D g2) {
    g2.drawImage(image, this.screenX, this.screenY, gamePanel.tileSize, gamePanel.tileSize, null);
  }
}