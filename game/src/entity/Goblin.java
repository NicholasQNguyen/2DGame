package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/** Moving enemy.
 *
 * @author Nicholas Nguyen
 *
 */
public class Goblin extends Entity {
  // Sprites + sprite stuff
  public BufferedImage up1;
  public BufferedImage up2;
  public BufferedImage down1;
  public BufferedImage down2;
  public BufferedImage left1;
  public BufferedImage left2;
  public BufferedImage right1;
  public BufferedImage right2;
  public int spriteCounter = 0;
  public int spriteNumber = 1;

  public Goblin() {
    // TODO Auto-generated constructor stub
  }
  
  private void getImage() {
    try {
      up1 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinUp1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinUp1.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinDown1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinDown2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinLeft1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinLeft2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinRight1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/enemy/goblinRight2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update() {
    // TODO Auto-generated method stub

  }

  @Override
  public void draw(Graphics2D g2) {
    // TODO Auto-generated method stub

  }

}
