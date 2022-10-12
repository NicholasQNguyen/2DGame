package entity;

import game.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/** Parent class for all entities (Player, enemies, etc.
 *
 * @author Nicholas Nguyen 
 *
 */
public abstract class Entity {
  // Movement variables
  public int worldX;
  public int worldY;
  public int screenX;
  public int screenY;
  public double velocityY = 0.0;
  public double velocityX = 0.0;
  public double accelX = 0.0;

  public String direction;
  
  public Rectangle solidArea;

  // Mostly Collision stuff
  public GamePanel gamePanel;
  public boolean topCollision = false;
  public boolean bottomCollision = false;
  public boolean leftCollision = false;
  public boolean rightCollision = false;

  public int hp;
  
  public abstract void update();
  
  public abstract void draw(Graphics2D g2);
  
  public void takeDamage(int damage) {
    this.hp -= damage;
  }
}