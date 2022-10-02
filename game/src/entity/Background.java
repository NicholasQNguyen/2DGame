package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;

public class Background extends Entity{
	BufferedImage image;
	GamePanel gamePanel;
	public Background(GamePanel gp) {
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/background/country-platform-preview.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		gamePanel = gp;
		// this.x = gp.SCREEN_WIDTH;
		// this.y = gp.SCREEN_HEIGHT;
		this.x = 0;
		this.y = 0;
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(this.image, this.x, this.y, null);
	}
}