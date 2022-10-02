package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;

public class TileManager {

	GamePanel gamePanel;
	Tile[] tile;
	
	public TileManager(GamePanel gp) {
		gamePanel = gp;
		tile = new Tile[10];
		getTileImage();
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/background/grass.png"));
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/background/water.png"));
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/background/sky.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		int column = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while (column < gamePanel.MAX_SCREEN_COLUMNS &&
			   row < gamePanel.MAX_SCREEN_ROWS) {
			g2.drawImage(tile[0].image, x, y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
			column++;
			x += gamePanel.TILE_SIZE;
			
			if (column == gamePanel.MAX_SCREEN_COLUMNS) {
				column = 0;
				x = 0;
				row++;
				y += gamePanel.TILE_SIZE;
			}
		}
	}
}
