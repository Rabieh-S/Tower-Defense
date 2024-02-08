package towers;

public class CannonTower3 extends Tower implements TowerUpgradable{
    public CannonTower3(int x, int y, int ID, int TowerType) {
        super(50, 150, 23.5f, 350, x, y, ID, TowerType);
    }

    @Override
    public void upgrade() {

    }
    @Override
    public Tower createTowerInstance(int x, int y, int id) {
        return new CannonTower3(x, y, id, 5);
    }
}
