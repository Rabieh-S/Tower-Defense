package towers;

public class ArcheryTower extends Tower implements TowerUpgradable{
    public ArcheryTower(int x, int y, int ID, int TowerType) {
        super(20, 100, 30.0f, 100, x, y, ID, TowerType);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public Tower createTowerInstance(int x, int y, int id) {
        return new ArcheryTower(x, y, id, 0);
    }
}
