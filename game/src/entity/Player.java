package entity;

import fsm.EntityState;
import game.GamePanel;
import game.PlayerKeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;


/** Class for the player controlled entity.
 *
 * @author Nicholas Nguyen 
 *
 */
public class Player extends Controlled {

  public int offsetX;
  public int offsetY;
  private final double fireballTime = .0001;
  double fireballTimer;
  public List<Fireball> fireballList = new CopyOnWriteArrayList<Fireball>();
  
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
    this.worldX = gamePanel.worldWidth / 2 - 400;
    this.worldY = 100;
    // Start just off center X and above the ground Y
    this.screenX = gamePanel.screenWidth / 2;
    this.screenY = gamePanel.screenHeight / 2;
    this.worldX += this.screenX;
    this.worldY += this.screenY;
    this.fireballTimer = 0;
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
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleEvent() {
    super.handleEvent();
    if (keyHandler.spacePressed && this.fireballTimer < 0) {
      this.spitFire();
      this.fireballTimer = this.fireballTime;
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

    // Handle the fireballTimer
    this.fireballTimer -= delta;
    // Cycle through the 2 animation frames
    this.spriteCounter++;
    if (this.spriteCounter > 10) {
      if (this.spriteNumber == 1) {
        this.spriteNumber = 2;
      } else {
        this.spriteNumber = 1;
      }
      spriteCounter = 0;
    }
    this.screenX = worldX - this.offsetX;
    this.screenY = worldY - this.offsetY;
  }


  /** Instantiate and add a new fireball to the list.
   * 
   */
  private void spitFire() {
    this.fireballList.add(new Fireball(gamePanel,
                                       this.worldX,
                                       this.worldY,
                                       this.state.getLastFacing()));
    this.fireballTimer = this.fireballTime;
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