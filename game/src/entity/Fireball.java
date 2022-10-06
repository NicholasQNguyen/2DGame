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
    this.gamePanel = gp;
    this.worldX = x;
    this.worldY = y;
    this.screenX = worldX - this.gamePanel.player.offsetX;
    this.screenY = worldY - this.gamePanel.player.offsetY;
    this.velocity = 4;
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
    this.screenX = worldX - this.gamePanel.player.offsetX;
    this.screenY = worldY - this.gamePanel.player.offsetY - 120;
    switch (this.direction) {
      case "left":
        this.worldX -= velocity;
        break;
      case "right":
        this.worldX += velocity;
        break;
      case "standing":
        this.worldX += velocity;
        break;
      default:
    }
    // delete the fireball if it went off the world
    if (this.worldX > Math.abs(gamePanel.worldWidth)) {
      try {
        this.finalize();
        // System.out.println("DELETE");
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }
  
  /** Draw the fireball with an offset.
   *
   * @param g2 Graphics 2D from gamePanel
   */
  public void draw(Graphics2D g2) {
    // Don't draw if beyond limit
    if (this.worldX < Math.abs(gamePanel.worldWidth)) {
      g2.drawImage(image, this.screenX, this.screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
  }
}