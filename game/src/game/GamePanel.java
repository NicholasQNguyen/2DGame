package game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	// Screen Settings
	final int ORIGINAL_TILE_SIZE = 16;
	final int SCALE = 3;
	final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
	
	final int MAX_SCREEN_COLUMNS = 16;
	final int MAX_SCREEN_ROWS = 12;
	
	final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMNS;
	final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}

}
