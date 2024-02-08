package enemies;

import static helpers.Constants.Enemies.OCTOROK;

public class Octorok extends Enemy{

	public Octorok(float x, float y, int ID) {
		super("Octorok", x, y, ID, OCTOROK, 200, 200,0, 2, 40);
	}

}
