package tile;

import game.MenuPanel;
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
public class MenuTileManager {

  MenuPanel menuPanel;
  public Tile[] tile;
  public int[][] mapTileNumber;

  /** Constructor.
   *
   * @param mp MenuPanel
   */
  public MenuTileManager(MenuPanel mp) {
    menuPanel = mp;
    tile = new Tile[10];
    mapTileNumber = new int[menuPanel.maxWorldColumns][menuPanel.maxWorldRows];
    getTileImage();
    loadMap("/maps/menu.txt");
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
      while (column < menuPanel.maxWorldColumns && row < menuPanel.maxWorldRows) {
        String line = br.readLine();
        while (column < menuPanel.maxWorldColumns) {
          String[] numbers = line.split(" ");
          int number = Integer.parseInt(numbers[column]);
          mapTileNumber[column][row] = number;
          column++;
        }
        if (column == menuPanel.maxWorldColumns) {
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

    while (worldColumn < menuPanel.maxWorldColumns 
           && worldRow < menuPanel.maxWorldRows) {
      int tileNumber = mapTileNumber[worldColumn][worldRow];

      int worldX = worldColumn * menuPanel.tileSize;
      int worldY = worldRow * menuPanel.tileSize;

      g2.drawImage(this.tile[tileNumber].image, worldX, worldY,
                   menuPanel.tileSize, menuPanel.tileSize, null);

      worldColumn++;

      if (worldColumn == menuPanel.maxWorldColumns) {
        worldColumn = 0;
        worldRow++;
      }
    }
  }
}