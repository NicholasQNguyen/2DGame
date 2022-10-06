package entity;

import game.GamePanel;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/** Parent class for all entities (Player, enemies, etc.
 *
 * @author Nicholas Nguyen 
 *
 */
public abstract class Entity {
  public int worldX;
  public int worldY;
  public int screenX;
  public int screenY;
  public int speed;
  public double velocityY = 0.0;
  public double velocityX = 0.0;
  public double accelX = 0.0;


  public String direction;
  public int spriteCounter = 0;
  public int spriteNumber = 1;
  
  public Rectangle solidArea;
  public boolean collisionOn = false;

  public GamePanel gamePanel;
  public boolean topCollision = false;
  public boolean bottomCollision = false;
  public boolean leftCollision = false;
  public boolean rightCollision = false;
}
