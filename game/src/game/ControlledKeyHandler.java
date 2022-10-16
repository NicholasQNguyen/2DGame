package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** Keyhandler for controlled entities.
 *
 * @author Nicholas Nguyen
 *
 */
public abstract class ControlledKeyHandler implements KeyListener {
  public boolean upPressed = false;
  public boolean downPressed = false;
  public boolean leftPressed = false;
  public boolean rightPressed = false;
  public boolean spacePressed = false;
  public boolean escPressed = false;

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }

}
