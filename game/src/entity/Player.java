package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import game.GamePanel;
import game.KeyHandler;

public class Player extends Entity{
	GamePanel gamePanel;
	KeyHandler keyHandler;
	
	public Player(GamePanel gp, KeyHandler kh) {
		this.gamePanel = gp;
		this.keyHandler = kh;
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		this.x = 100;
		this.y = 100;
		this.speed = 4;
	}
	
	public void update() {
		if (keyHandler.upPressed == true) {
			this.y -= this.speed;
		}
		if (keyHandler.downPressed == true) {
			this.y += this.speed;
		}
		if (keyHandler.leftPressed == true) {
			this.x -= this.speed;
		}
		if (keyHandler.rightPressed == true) {
			this.x += this.speed;
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.fillRect(this.x, this.y,
					gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
	}
}
