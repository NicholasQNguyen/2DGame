package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import game.GamePanel;

/** Projectile the player can fire.
 *
 * @author Nicholas Nguyen
 *
 */
public class Fireball {
  int worldX;
  int worldY;
  int velocity;
  BufferedImage image;
  GamePanel gamePanel;
  
  /** Constructor.
   *
   * @param gp GamePanel
   * @param x x world position
   * @param y y world position
   */
  public Fireball(GamePanel gp, int x, int y) {
    this.worldX = x;
    this.worldY = y;
    this.velocity = 4;
    this.gamePanel = gp;
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
   * @param direction the direction mudkip is facing
   */
  public void update(String direction) {
    switch (direction) {
      case "Left":
        this.worldX -= velocity;
        break;
      case "Right":
        this.worldX += velocity;
        break;
      default:
        System.out.println("PROBLEM IN FIREBALL");
    }
  }
  
  public void draw(Graphics2D g2) {
    g2.drawImage(image, this.worldX, this.worldY, gamePanel.tileSize, gamePanel.tileSize, null);
  }
}
