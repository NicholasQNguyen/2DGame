package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	
	// Screen Settings
	final int ORIGINAL_TILE_SIZE = 16;
	final int SCALE = 3;
	public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
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
	Player player = new Player(this, keyHandler);
	
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
		double delta = 0.0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta > 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	public void update() {
		player.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		player.draw(g2);
		g2.dispose();
	}
}
