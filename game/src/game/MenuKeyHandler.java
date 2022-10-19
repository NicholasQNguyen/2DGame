package game;

import java.awt.event.KeyEvent;

/** Class to handle keyboard input for the menu.
 *
 * @author Nicholas Nguyen
 *
 */
public class MenuKeyHandler extends ControlledKeyHandler {

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
      upPressed = true;
    }
    if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
      leftPressed = true;
    }
    if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
      downPressed = true;
    }
    if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
      rightPressed = true;
    }
    if (code == KeyEvent.VK_ESCAPE) {
      System.out.println("ESCAPING");
      escPressed = true;
    }
    if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_F) {
      spacePressed = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();

    if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
      upPressed = false;
    }
    if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
      leftPressed = false;
    }
    if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
      downPressed = false;
    }
    if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
      rightPressed = false;
    }
    if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_F) {
      spacePressed = false;
    }
  }
}