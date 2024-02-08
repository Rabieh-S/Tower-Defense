package towers;
public abstract class Tower implements TowerUpgradable {
    protected static int price;
    protected int range;
    protected int damage;
    protected int attackTick;
    protected float attackSpeed;
    private int x, y, id, towerType;

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getTowerType() {
        return towerType;
    }

    public Tower(int damage, int range, float attackSpeed,int price, int x, int y, int id, int towerType) {
        this.damage = damage;
        this.range = range;
        this.attackSpeed = attackSpeed;
        this.price = price;
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
    }
    public abstract Tower createTowerInstance(int x, int y, int id);

    public void update() {
        attackTick++;
    }

    public static int getPrice() {
        return price;
    }

    public boolean isAttackReset(){
        return attackTick >= attackSpeed;
    }

    public void refreshAttack(){
        attackTick = 0;
    }

}