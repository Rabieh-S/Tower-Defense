package enemies;

import java.awt.Rectangle;
import static helpers.Constants.Direction.*;

public abstract class Enemy {

	protected float x, y;
	protected int maxHealth;
	protected Rectangle bounds;
	protected int health;
	protected int defense;
	protected int damage;
	protected String name;
	protected int gold;
	protected int ID;
	protected int enemyType;
	protected int lastDir;
	protected boolean isAlive = true;

	public Enemy(String name, float x, float y, int ID, int enemyType, int health, int maxHealth, int defense, int damage, int gold) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.enemyType = enemyType;
		bounds = new Rectangle((int) x, (int) y, 32, 32);
		lastDir = -1;
		this.health = health;
		this.maxHealth = health;
		this.defense = defense;
		this.damage = damage;
		this.gold = gold;
	}

	public String getName() {
		return name;
	}

	public float getHpBar() {
		return health / (float) maxHealth;
	}

	public void receiveDamageFromTowers(int damage) {
		this.health -= damage;
		if (health <= 0) {
			isAlive = false;
		}
	}

	public void kill() {
		isAlive = false;
		health = 0;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public int getGold() {
		return gold;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}


	public void move(float speed, int dir) {
		lastDir = dir;
		switch (dir) {
		case LEFT:
			this.x -= speed;
			break;
		case UP:
			this.y -= speed;
			break;
		case RIGHT:
			this.x += speed;
			break;
		case DOWN:
			this.y += speed;
			break;
		}
		updateEnemyHitbox();
	}

	private void updateEnemyHitbox() {
		bounds.x = (int) x;
		bounds.y = (int) y;
	}

	public void setPos(int x, int y) {
		// Don't use this one for moving the enemy.
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public int getHealth() {
		return health;
	}

	public int getID() {
		return ID;
	}

	public int getEnemyType() {
		return enemyType;
	}

	public int getLastDir() {
		return lastDir;
	}

}
