package entity;

import game.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Projectile the player can fire.
 *
 * @author Nicholas Nguyen
 *
 */
public class Fireball {
  int worldX;
  int worldY;
  int screenX;
  int screenY;
  int velocity;
  String direction;
  BufferedImage image;
  GamePanel gamePanel;
  
  /** Constructor.
   *
   * @param gp GamePanel
   * @param x x world position
   * @param y y world position
   */
  public Fireball(GamePanel gp, int x, int y, String direction) {
    int offset = 300;
    this.worldX = x;
    this.worldY = y;
    this.screenX = worldX - offset;
    this.screenY = worldY - offset;
    this.velocity = 4;
    this.gamePanel = gp;
    this.direction = direction;
    loadImage();
  }
  
  private void loadImage() {
    try {
      image = ImageIO.read(getClass().getResourceAsStream("/player/fire.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Moves the fireball.
   *
   */
  public void update() {
    switch (this.direction) {
      case "left":
        this.worldX -= velocity;
        break;
      case "right":
        this.worldX += velocity;
        break;
      default:
    }
  }
  
  public void draw(Graphics2D g2) {
    g2.drawImage(image, this.screenX, this.screenY, gamePanel.tileSize, gamePanel.tileSize, null);
  }
}
