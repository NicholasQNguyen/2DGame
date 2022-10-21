package game;

import java.awt.event.KeyEvent;

/** Class to handle keyboard input.
 *
 * @author Nicholas Nguyen
 *
 */
public class GoblinKeyHandler extends ControlledKeyHandler {

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    if (code == KeyEvent.VK_UP) {
      upPressed = true;
    }
    if (code == KeyEvent.VK_LEFT) {
      leftPressed = true;
    }
    if (code == KeyEvent.VK_DOWN) {
      downPressed = true;
    }
    if (code == KeyEvent.VK_RIGHT) {
      rightPressed = true;
    }
    if (code == KeyEvent.VK_ESCAPE) {
      escPressed = true;
    }
    if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
      spacePressed = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();

    if (code == KeyEvent.VK_UP) {
      upPressed = false;
    }
    if (code == KeyEvent.VK_LEFT) {
      leftPressed = false;
    }
    if (code == KeyEvent.VK_DOWN) {
      downPressed = false;
    }
    if (code == KeyEvent.VK_RIGHT) {
      rightPressed = false;
    }
    if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ENTER) {
      spacePressed = false;
    }
  }
}