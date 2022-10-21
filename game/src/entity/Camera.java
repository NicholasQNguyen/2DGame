package entity;

import java.awt.Graphics2D;

import game.GamePanel;

/** Invisible entity that the camera tracks.
 *
 * @author nguyennq23
 *
 */
public class Camera extends Entity {

  int offsetX;
  int offsetY;

  /** Instantiate it between the two characters.
   * 
   */
  public Camera(GamePanel gp) {
    super(gp);
    this.gamePanel = gp;

    this.worldX = (gamePanel.player.getWorldX() + gamePanel.goblin.getWorldX()) / 2;
    this.worldY = (gamePanel.player.getWorldY() + gamePanel.goblin.getWorldY()) / 2;
    this.screenX = (gamePanel.player.getWorldX() + gamePanel.goblin.getWorldX()) / 2;
    this.screenY = (gamePanel.player.getWorldY() + gamePanel.goblin.getWorldY()) / 2;
  }

  @Override
  public void update(double delta) {
    this.worldX = (gamePanel.player.getWorldX() + gamePanel.goblin.getWorldX()) / 2;
    this.worldY = (gamePanel.player.getWorldY() + gamePanel.goblin.getWorldY()) / 2;
    this.screenX = (gamePanel.player.getWorldX() + gamePanel.goblin.getWorldX()) / 2;
    this.screenY = (gamePanel.player.getWorldY() + gamePanel.goblin.getWorldY()) / 2;

  }

  @Override
  public void draw(Graphics2D g2) {

  }

  /** Method to get the screen offset from world -> screen coordinates.
   *
   * @param screenSizeX Horizontal screen size
   * @param screenSizeY Vertical screen size
   * @param worldSizeX Horizontal total world size
   * @param worldSizeY Vertical total world size
   */
  public void updateWindowOffset(int screenSizeX, int screenSizeY, int worldSizeX, int worldSizeY) {
    this.offsetX = Math.min(Math.max(0, 
                                     this.worldX + (this.solidArea.width / 2) - (screenSizeX / 2)),
                            worldSizeX - screenSizeX);
    this.offsetY = Math.min(Math.max(0,
                                     this.worldY + (this.solidArea.height / 2) - (screenSizeY / 2)),
                            worldSizeY - screenSizeY);
  }
}