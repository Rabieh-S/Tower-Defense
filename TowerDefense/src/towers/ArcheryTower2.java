package towers;

public class ArcheryTower2 extends Tower implements TowerUpgradable{
    public ArcheryTower2(int x, int y, int ID, int TowerType) {
        super(35, 120, 28.0f, 200, x, y, ID, TowerType);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public Tower createTowerInstance(int x, int y, int id) {
        return new ArcheryTower2(x, y, id, 1);
    }
}
