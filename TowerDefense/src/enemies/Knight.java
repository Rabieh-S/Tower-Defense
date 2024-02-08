package enemies;

import static helpers.Constants.Enemies.KNIGHT;

public class Knight extends Enemy {

	public Knight(float x, float y, int ID) {
		super("Knight", x, y, ID, KNIGHT, 400, 400, 5, 3, 60);
	}

}
