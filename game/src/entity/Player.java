package entity;

import game.GamePanel;
import game.PlayerKeyHandler;
import java.io.IOException;
import javax.imageio.ImageIO;


/** Class for the player controlled entity.
 *
 * @author Nicholas Nguyen 
 *
 */
public class Player extends Controlled {

  public int offsetX;
  public int offsetY;
  
  /** Constructor.
   *
   * @param gp The GamePanel
   * @param kh KeyHandler for keyboard input
   */
  public Player(GamePanel gp, PlayerKeyHandler kh) {
    super(gp, kh);
    setDefaultValues();
    getPlayerImage();
  }

  /** Helper function to constructor where we initialize some values.
   *
   */
  public void setDefaultValues() {
    this.worldX = gamePanel.worldWidth / 2 - 1200;
    this.worldY = 0;
    // Start just off center X and above the ground Y
    this.screenX = gamePanel.screenWidth / 2;
    this.screenY = gamePanel.screenHeight / 2;
    this.worldX += this.screenX;
    this.worldY += this.screenY;
    this.hp = 20;
  }

  /** Load all of the player images from a stream.
   *
   */
  public void getPlayerImage() {
    try {
      up1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipBack1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipBack2.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipFront1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipFront2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipLeft1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipLeft2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipRight1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipRight2.png"));
      leftBlock1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipLeftBlock1.png"));
      leftBlock2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipLeftBlock2.png"));
      rightBlock1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipRightBlock1.png"));
      rightBlock2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipRightBlock2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Check for collision and move accordingly.
   * 
   */
  @Override
  public void update(double delta) { 
    super.update(delta);
    super.checkCollision();
    
    // Move 1 pixel away to prevent being stuck forever
    if (this.leftCollision) {
      this.velocityX = 0;
      this.worldX += 1;
    } else if (this.rightCollision) {
      this.velocityX = 0;
      this.worldX -= 1;
    }

    // Reset the collisions
    this.bottomCollision = false;
    this.topCollision = false;
    this.leftCollision = false;
    this.rightCollision = false;

    this.screenX = worldX - this.offsetX;
    this.screenY = worldY - this.offsetY;

    this.facing = this.orient(this.gamePanel.goblin);
    this.direction = this.facing;
    // System.out.println("MUDKIP: " + this.hp);
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