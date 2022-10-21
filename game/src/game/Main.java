package game;

import fsm.ScreenManager;
import javax.swing.JFrame;

/** Class to start up the game.
 *
 * @author Nicholas Nguyen
 *
 */
public class Main {
  public static final GamePanel gp = new GamePanel();
  public static final MenuPanel mp = new MenuPanel();
  public static JFrame window = new JFrame();

  /** Main function.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    System.out.println("Hello World!");
    @SuppressWarnings("unused")
    ScreenManager sm = new ScreenManager();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("2D Game");

    window.pack();
    window.setLocationRelativeTo(null);
    window.setVisible(true);
  }
}