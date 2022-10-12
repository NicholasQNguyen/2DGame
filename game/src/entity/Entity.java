package entity;

import game.GamePanel;
import java.awt.Rectangle;

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
  public double velocityY = 0.0;
  public double velocityX = 0.0;
  public double accelX = 0.0;

  public String direction;
  
  public Rectangle solidArea;

  public GamePanel gamePanel;
  public boolean topCollision = false;
  public boolean bottomCollision = false;
  public boolean leftCollision = false;
  public boolean rightCollision = false;

  public void update() {
  }
}