package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** Class to handle keyboard input.
 *
 * @author Nicholas Nguyen
 *
 */
public class GoblinKeyHandler implements KeyListener {

  public boolean upPressed = false;
  public boolean downPressed = false;
  public boolean leftPressed = false;
  public boolean rightPressed = false;
  public boolean spacePressed = false;
  public boolean escPressed = false;

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
    if (code == KeyEvent.VK_SPACE) {
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
    if (code == KeyEvent.VK_SPACE) {
      spacePressed = false;
    }
  }
}