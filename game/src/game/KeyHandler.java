package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** Class to handle keyboard input.
 *
 * @author Nicholas Nugyen
 *
 */
public class KeyHandler implements KeyListener {

  public boolean upPressed = false;
  public boolean downPressed = false;
  public boolean leftPressed = false;
  public boolean rightPressed = false;
  public boolean escPressed = false;

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
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
  }

  @Override
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
  }
}