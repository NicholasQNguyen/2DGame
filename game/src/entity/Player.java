package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;
import game.KeyHandler;

public class Player extends Entity{
	KeyHandler keyHandler;
	
	public Player(GamePanel gp, KeyHandler kh) {
		this.gamePanel = gp;
		this.keyHandler = kh;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		this.x = 100;
		this.y = 100;
		this.speed = 4;
		this.direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipBack1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipBack2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipFront1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipFront2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipLeft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/mudkipRight2.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (keyHandler.upPressed == true) {
			direction = "up";
			this.y -= this.speed;
		}
		if (keyHandler.downPressed == true) {
			direction = "down";
			this.y += this.speed;
		}
		if (keyHandler.leftPressed == true) {
			direction = "left";
			this.x -= this.speed;
		}
		if (keyHandler.rightPressed == true) {
			direction = "right";
			this.x += this.speed;
		}
		
		this.spriteCounter++;
		if (this.spriteCounter > 10) {
			if (this.spriteNumber == 1) {
				this.spriteNumber = 2;
			}
			else {
				this.spriteNumber = 1;
			}
			spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(direction) {
			case "up":
				if (this.spriteNumber == 1) {
					image = up1;
				}
				else {
					image = up2;
				}
				break;
			case "down":
				if (this.spriteNumber == 1) {
					image = down1;
				}
				else {
					image = down2;
				}
				break;
			case "right":
				if (this.spriteNumber == 1) {
					image = right1;
				}
				else {
					image = right2;
				}
				break;
			case "left":
				if (this.spriteNumber == 1) {
					image = left1;
				}
				else {
					image = left2;
				}
				break;
		}
		g2.drawImage(image, this.x, this.y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
	}
}
