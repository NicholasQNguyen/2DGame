package entity;

import game.GamePanel;
import game.KeyHandler;

public class Player extends Entity{
	GamePanel gamePanel;
	KeyHandler keyHandler;
	
	public Player(GamePanel gp, KeyHandler kh) {
		this.gamePanel = gp;
		this.keyHandler = kh;
	}
}
