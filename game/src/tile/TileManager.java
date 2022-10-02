package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import game.GamePanel;

public class TileManager {

	GamePanel gamePanel;
	Tile[] tile;
	int mapTileNumber[][];
	
	public TileManager(GamePanel gp) {
		gamePanel = gp;
		tile = new Tile[10];
		mapTileNumber = new int[gamePanel.MAX_SCREEN_COLUMNS][gamePanel.MAX_SCREEN_ROWS];
		getTileImage();
		loadMap("/maps/stage1.txt");
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
	
	public void loadMap(String file) {
		try {
			InputStream inputStream = getClass().getResourceAsStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			int column = 0;
			int row = 0;
			while (column < gamePanel.MAX_SCREEN_COLUMNS && row < gamePanel.MAX_SCREEN_ROWS) {
				String line = br.readLine();
				while (column < gamePanel.MAX_SCREEN_COLUMNS) {
					String numbers[] = line.split(" ");
					int number = Integer.parseInt(numbers[column]);
					mapTileNumber[column][row] = number;
					column++;
				}
				if (column == gamePanel.MAX_SCREEN_COLUMNS) {
					column = 0;
					row++;
				}
			}
			br.close();
		}
		catch (Exception e) {
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
			int tileNumber = mapTileNumber[column][row];
			g2.drawImage(this.tile[tileNumber].image, x, y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
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
