package enemies;

import static helpers.Constants.Enemies.BAT;

public class Bat extends Enemy {

	public Bat(float x, float y, int ID) {
		super("Bat", x, y, ID, BAT, 150, 150, 0, 1, 35);
	}

}
