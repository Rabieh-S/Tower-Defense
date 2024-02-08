package enemies;

import static helpers.Constants.Enemies.SKELETON;

public class Skeleton extends Enemy {

	public Skeleton(float x, float y, int ID) {
		super("Skeleton", x, y, ID, SKELETON, 75, 75,0, 1, 15);
	}

}
