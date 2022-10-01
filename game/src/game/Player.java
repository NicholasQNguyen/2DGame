package game;

public class Player {
	int HP = 100;
	
	void takeDamage(int damage) {
		this.HP -= damage;
	}
}
