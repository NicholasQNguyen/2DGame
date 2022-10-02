package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	
	// Screen Settings
	final int ORIGINAL_TILE_SIZE = 16;
	final int SCALE = 3;
	final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
	final int MAX_SCREEN_COLUMNS = 16;
	final int MAX_SCREEN_ROWS = 12;
	final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMNS;
	final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS;
	
	//Player default position
	int playerX = 100;
	int playerY = 100;
	final int PLAYER_SPEED = 4;
	
	final int FPS = 60;

	Thread gameThread;
	KeyHandler keyHandler = new KeyHandler();
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	// The game loop
	@Override
	public void run() {

		final double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime();

		while (gameThread != null) {
			update();
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime /= 1000000;
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		if (keyHandler.upPressed == true) {
			playerY -= PLAYER_SPEED;
		}
		if (keyHandler.downPressed == true) {
			playerY += PLAYER_SPEED;
		}
		if (keyHandler.leftPressed == true) {
			playerX -= PLAYER_SPEED;
		}
		if (keyHandler.rightPressed == true) {
			playerX += PLAYER_SPEED;
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, TILE_SIZE, TILE_SIZE);
		g2.dispose();
	}
}
