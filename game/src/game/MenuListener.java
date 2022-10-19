package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Class to handle the button doing stuff.
 *
 * @author Nicholas Nguyen 
 *
 */
public class MenuListener implements ActionListener {
  boolean starting = false;

  @Override
  public void actionPerformed(ActionEvent e) {
    starting = true;
    System.out.println("HAVE FUN");
  }

}
