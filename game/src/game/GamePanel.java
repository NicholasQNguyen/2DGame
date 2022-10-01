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
	
	Thread gameThread;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	// The game loop
	@Override
	public void run() {
		while (gameThread != null) {
			update();
			repaint();
		}
	}
	
	public void update() {
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.fillRect(0, 0, TILE_SIZE, TILE_SIZE);
		g2.dispose();
	}
}
