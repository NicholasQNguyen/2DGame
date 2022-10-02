package tile;

import game.GamePanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

/** Manager to handle tiling with Tiles.
 *
 * @author Nicholas Nguyen
 */
public class TileManager {

  GamePanel gamePanel;
  public Tile[] tile;
  public int[][] mapTileNumber;

  /** Constructor.
   *
   * @param gp GamePanel
   */
  public TileManager(GamePanel gp) {
    gamePanel = gp;
    tile = new Tile[10];
    mapTileNumber = new int[gamePanel.maxWorldColumns][gamePanel.maxWorldRows];
    getTileImage();
    loadMap("/maps/stage1.txt");
  }

  /** Load all of the background tiles into Tile objects.
   *
   */
  public void getTileImage() {
    try {
      tile[0] = new Tile();
      tile[0].image = ImageIO.read(getClass().getResourceAsStream("/background/grass.png"));
      tile[0].collision = true;
      tile[1] = new Tile();
      tile[1].image = ImageIO.read(getClass().getResourceAsStream("/background/water.png"));
      // tile[1].collision = true;
      tile[2] = new Tile();
      tile[2].image = ImageIO.read(getClass().getResourceAsStream("/background/sky.png"));
      // tile[2].collision = true;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Load the map file into the array of arrays mapTileNumber.
   *
   * @param file Map file
   */
  public void loadMap(String file) {
    try {
      InputStream inputStream = getClass().getResourceAsStream(file);
      BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
      int column = 0;
      int row = 0;
      while (column < gamePanel.maxWorldColumns && row < gamePanel.maxWorldRows) {
        String line = br.readLine();
        while (column < gamePanel.maxWorldColumns) {
          String[] numbers = line.split(" ");
          int number = Integer.parseInt(numbers[column]);
          mapTileNumber[column][row] = number;
          column++;
        }
        if (column == gamePanel.maxWorldColumns) {
          column = 0;
          row++;
        }
      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** Loops to tile the whole map.
   *
   * @param g2 Graphics 2D
   */
  public void draw(Graphics2D g2) {
    int worldColumn = 0;
    int worldRow = 0;

    while (worldColumn < gamePanel.maxWorldColumns 
           && worldRow < gamePanel.maxWorldRows) {
      int tileNumber = mapTileNumber[worldColumn][worldRow];

      int worldX = worldColumn * gamePanel.tileSize;
      int worldY = worldRow * gamePanel.tileSize;
      int screenX = worldX - gamePanel.player.worldX;
      int screenY = worldY - gamePanel.player.worldY;

      g2.drawImage(this.tile[tileNumber].image, screenX, screenY,
                   gamePanel.tileSize, gamePanel.tileSize, null);

      worldColumn++;

      if (worldColumn == gamePanel.maxWorldColumns) {
        worldColumn = 0;
        worldRow++;
      }
    }
  }
}