package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import fsm.ScreenManager;

/** Class to handle the button doing stuff.
 *
 * @author Nicholas Nguyen 
 *
 */
public class MenuListenerHowTo implements ActionListener {
  boolean starting = false;

  @Override
  public void actionPerformed(ActionEvent e) {
    JOptionPane.showConfirmDialog(ScreenManager.menuPanel, "Mudkip (P1): \n"
                                                          + "A and D to move left and right \n" 
                                                          + "W to jump \n"
                                                          + "F to fire fireball \n"
                                                          + "Goblin (P2): \n"
                                                          + "Left and Right arrows to move left and right \n" 
                                                          + "Up Arrow to jump \n"
                                                          + "Enter to fire fireball");

  }
}
