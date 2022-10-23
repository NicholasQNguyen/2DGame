package game;

import entity.Entity;
import java.awt.Rectangle;

/** Class to handle collisions.
 *
 * @author Nicholas Nguyen
 *
 */
public class CollisionChecker {
  GamePanel gamePanel;

  /** Constructor.
   * 
   */
  public CollisionChecker(GamePanel gp) {
    this.gamePanel = gp;
  }
  
  /** Collision checking method.
   *
   * @param entity player/enemy that we're checking collision for
   */
  public void checkTile(int worldX, int worldY, Rectangle solidArea, Entity entity) {
    int entityLeftWorldX = worldX + solidArea.x;
    int entityRightWorldX = worldX + solidArea.x + solidArea.width;
    int entityTopWorldY = worldY + solidArea.y;
    int entityBottomWorldY = worldY + solidArea.y + solidArea.height;
    
    int entityLeftColumn = entityLeftWorldX / gamePanel.tileSize;
    int entityRightColumn = entityRightWorldX / gamePanel.tileSize;
    int entityTopRow = entityTopWorldY / gamePanel.tileSize;
    int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;
    int topLeftTile = gamePanel.tm.mapTileNumber[entityLeftColumn][entityTopRow];
    int topRightTile = gamePanel.tm.mapTileNumber[entityRightColumn][entityTopRow];
    int bottomLeftTile = gamePanel.tm.mapTileNumber[entityLeftColumn][entityBottomRow];
    int bottomRightTile = gamePanel.tm.mapTileNumber[entityRightColumn][entityBottomRow];
    
    if (gamePanel.tm.tile[topLeftTile].collision
        || gamePanel.tm.tile[topRightTile].collision) {
      entity.topCollision = true;
    }
    if (gamePanel.tm.tile[bottomLeftTile].collision 
        || gamePanel.tm.tile[bottomRightTile].collision) {
      entity.bottomCollision = true;
    }
    if (gamePanel.tm.tile[topLeftTile].collision) {
      entity.leftCollision = true;
    }
    if (gamePanel.tm.tile[topRightTile].collision) {
      entity.rightCollision = true;
    }
  }
  
  /** Checks for fireball collision.
   *
   * @param entity The thing we're hitting
   * @param fireball The fireball
   * @return boolean if we hit or not
   */
  public boolean checkFireball(Entity entity, Entity fireball) {
    // Get collision of thing we're hitting
    int entityLeftWorldX = entity.worldX + entity.solidArea.x;
    int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
    int entityTopWorldY = entity.worldY + entity.solidArea.y;
    int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
    
    int entityLeftColumn = entityLeftWorldX / gamePanel.tileSize;
    int entityRightColumn = entityRightWorldX / gamePanel.tileSize;
    int entityTopRow = entityTopWorldY / gamePanel.tileSize;
    int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

    // Get collision of the fireball
    int fireLeftWorldX = fireball.worldX + fireball.solidArea.x;
    int fireRightWorldX = fireball.worldX + fireball.solidArea.x + fireball.solidArea.width;
    int fireTopWorldY = fireball.worldY + fireball.solidArea.y;
    int fireBottomWorldY = fireball.worldY + fireball.solidArea.y + fireball.solidArea.height;
    
    int fireLeftColumn = fireLeftWorldX / gamePanel.tileSize;
    int fireRightColumn = fireRightWorldX / gamePanel.tileSize;
    int fireTopRow = fireTopWorldY / gamePanel.tileSize;
    int fireBottomRow = fireBottomWorldY / gamePanel.tileSize;
    
    // Check if it hits
    boolean hit = false;
    if ((fireRightColumn == entityLeftColumn
        && fireTopRow == entityTopRow) 
        || (fireLeftColumn == entityRightColumn
        && fireTopRow == entityTopRow)
        || (fireRightColumn == entityLeftColumn
        && fireBottomRow == entityBottomRow) 
        || (fireLeftColumn == entityRightColumn
        && fireBottomRow == entityBottomRow)) {
      hit = true;
    }
    return hit;
  }
}
