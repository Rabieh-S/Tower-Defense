package towers;

public class ArcheryTower3 extends Tower implements TowerUpgradable{
    public ArcheryTower3(int x, int y, int ID, int TowerType) {
        super(50, 150, 23.0f, 300, x, y, ID, TowerType);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public Tower createTowerInstance(int x, int y, int id) {
        return new ArcheryTower3(x, y, id, 2);
    }
}
