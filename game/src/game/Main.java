package game;

import javax.swing.JFrame;

/** Class to start up the game.
 *
 * @author Nicholas Nguyen
 *
 */
public class Main {

  /** Main function.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {

    System.out.println("Hello World!");
    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("2D Game");

    GamePanel gamePanel = new GamePanel();
    window.add(gamePanel);
    window.pack();

    window.setLocationRelativeTo(null);
    window.setVisible(true);

    gamePanel.startGameThread();
  }
}