package towers;

public class CannonTower extends Tower implements TowerUpgradable {
    public CannonTower(int x, int y, int ID, int TowerType) {
        super(20, 100, 30.0f, 150, x, y, ID, TowerType);
    }

    @Override
    public void upgrade() {

    }
    @Override
    public Tower createTowerInstance(int x, int y, int id) {
        return new CannonTower(x, y, id, 3);
    }

}
