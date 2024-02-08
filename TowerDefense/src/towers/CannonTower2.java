package towers;

public class CannonTower2 extends Tower implements TowerUpgradable {
    public CannonTower2(int x, int y, int ID, int TowerType) {
        super(35, 120, 25.0f, 250, x, y, ID, TowerType);
    }

    @Override
    public void upgrade() {

    }
    @Override
    public Tower createTowerInstance(int x, int y, int id) {
        return new CannonTower2(x, y, id, 4);
    }
}
