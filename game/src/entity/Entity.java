package entity;

import game.GamePanel;
import java.awt.image.BufferedImage;

/** Parent class for all entities (Player, enemies, etc.
 *
 * @author hananguyen
 *
 */
public class Entity {
  public int worldX;
  public int worldY;
  public int speed;

  public BufferedImage up1;
  public BufferedImage up2;
  public BufferedImage down1;
  public BufferedImage down2;
  public BufferedImage left1;
  public BufferedImage left2;
  public BufferedImage right1;
  public BufferedImage right2;

  public String direction;
  public int spriteCounter = 0;
  public int spriteNumber = 1;

  public GamePanel gamePanel;
}
