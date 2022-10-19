package game;

import java.awt.event.KeyEvent;

/** Class to handle keyboard input.
 *
 * @author Nicholas Nguyen
 *
 */
public class PlayerKeyHandler extends ControlledKeyHandler {

  public void keyTyped(KeyEvent e) {
  }

  /** When you press a key.
   * 
   */
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    if (code == KeyEvent.VK_W) {
      upPressed = true;
    }
    if (code == KeyEvent.VK_A) {
      leftPressed = true;
    }
    if (code == KeyEvent.VK_S) {
      downPressed = true;
    }
    if (code == KeyEvent.VK_D) {
      rightPressed = true;
    }
    if (code == KeyEvent.VK_ESCAPE) {
      escPressed = true;
    }
    if (code == KeyEvent.VK_F) {
      spacePressed = true;
    }
  }

  /** When you release a key.
   *
   */
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();

    if (code == KeyEvent.VK_W) {
      upPressed = false;
    }
    if (code == KeyEvent.VK_A) {
      leftPressed = false;
    }
    if (code == KeyEvent.VK_S) {
      downPressed = false;
    }
    if (code == KeyEvent.VK_D) {
      rightPressed = false;
    }
    if (code == KeyEvent.VK_F) {
      spacePressed = false;
    }
  }
}