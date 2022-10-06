package game;

import entity.Entity;

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
  public void checkTile(Entity entity) {
    int entityLeftWorldX = entity.worldX + entity.solidArea.x;
    int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
    int entityTopWorldY = entity.worldY + entity.solidArea.y;
    int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
    
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
}
