package towers;

public class MagicTower3 extends Tower implements TowerUpgradable{
    public MagicTower3(int x, int y, int ID, int TowerType) {
        super(50, 150, 23.5f, 350,x, y, ID, TowerType);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public Tower createTowerInstance(int x, int y, int id) {
        return new MagicTower3(x, y, id, 8);
    }
}
