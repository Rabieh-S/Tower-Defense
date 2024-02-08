package towers;

public class MagicTower extends Tower {
    public MagicTower(int x, int y, int ID, int TowerType) {
        super(20, 100, 30.5f, 125,x, y, ID, TowerType);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public Tower createTowerInstance(int x, int y, int id) {
        return new MagicTower(x, y, id, 6);
    }
}
