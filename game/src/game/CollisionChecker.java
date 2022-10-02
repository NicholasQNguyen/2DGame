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
    
    int tileNum1;
    int tileNum2;
    
    switch (entity.direction) {
      case "up":
        entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
        tileNum1 = gamePanel.tm.mapTileNumber[entityLeftColumn][entityTopRow];
        tileNum2 = gamePanel.tm.mapTileNumber[entityRightColumn][entityTopRow];
        if (gamePanel.tm.tile[tileNum1].collision || gamePanel.tm.tile[tileNum2].collision) {
          entity.collisionOn = true;
          System.out.println("TOP COLLIDE");
        }
        break;
      case "down":
        entityBottomRow = (entityBottomWorldY - entity.speed) / gamePanel.tileSize;
        tileNum1 = gamePanel.tm.mapTileNumber[entityLeftColumn][entityBottomRow];
        tileNum2 = gamePanel.tm.mapTileNumber[entityRightColumn][entityBottomRow];
        if (gamePanel.tm.tile[tileNum1].collision || gamePanel.tm.tile[tileNum2].collision) {
          entity.collisionOn = true;
          System.out.println("BOTTOM COLLIDE");
        }
        break;
      case "left":
        entityLeftColumn = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
        tileNum1 = gamePanel.tm.mapTileNumber[Math.max(0, entityLeftColumn)][entityTopRow];
        tileNum2 = gamePanel.tm.mapTileNumber[Math.max(0, entityLeftColumn)][entityBottomRow];
        if (gamePanel.tm.tile[tileNum1].collision || gamePanel.tm.tile[tileNum2].collision) {
          entity.collisionOn = true;
          System.out.println("LEFT COLLIDE");
        }
        break;
      case "right": 
        entityRightColumn = (entityRightWorldX - entity.speed) / gamePanel.tileSize;
        tileNum1 = gamePanel.tm.mapTileNumber[Math.max(0, entityRightColumn)][entityTopRow];
        tileNum2 = gamePanel.tm.mapTileNumber[Math.max(0, entityRightColumn)][entityBottomRow];
        if (gamePanel.tm.tile[tileNum1].collision || gamePanel.tm.tile[tileNum2].collision) {
          entity.collisionOn = true;
          System.out.println("RIGHT COLLIDE");
        }
        break;
      case "standing":
        entityBottomRow = (entityBottomWorldY - entity.speed) / gamePanel.tileSize;
        tileNum1 = gamePanel.tm.mapTileNumber[entityLeftColumn][entityBottomRow];
        tileNum2 = gamePanel.tm.mapTileNumber[entityRightColumn][entityBottomRow];
        if (gamePanel.tm.tile[tileNum1].collision || gamePanel.tm.tile[tileNum2].collision) {
          entity.collisionOn = true;
          System.out.println("BOTTOM COLLIDE");
        }
        break;
      default:
        break;
        // System.out.println("PROBLEM");
    }
    
  }
}
