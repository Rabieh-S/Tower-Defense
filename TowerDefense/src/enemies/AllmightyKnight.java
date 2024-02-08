package enemies;

import static helpers.Constants.Enemies.ALLMIGHTYKNIGHT;

public class AllmightyKnight extends Enemy{

    public AllmightyKnight(float x, float y, int ID) {
        super("All Mighty Knight", x, y, ID, ALLMIGHTYKNIGHT, 1000, 1000,15, 5, 250);
    }
}
