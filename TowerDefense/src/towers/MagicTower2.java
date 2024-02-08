package towers;

public class MagicTower2 extends Tower implements TowerUpgradable{
    public MagicTower2(int x, int y, int ID, int TowerType) {
        super(35, 120, 26.5f, 275,x, y, ID, TowerType);
    }

    @Override
    public void upgrade() {

    }

    @Override
    public Tower createTowerInstance(int x, int y, int id) {
        return new MagicTower2(x, y, id, 7);
    }
}
