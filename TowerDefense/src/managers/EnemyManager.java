package managers;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import enemies.Bat;
import enemies.Enemy;
import enemies.Knight;
import enemies.Octorok;
import enemies.Skeleton;
import enemies.AllmightyKnight;
import helpers.LoadSave;
import objects.PathPoint;
import scenes.Playing;
import static helpers.Constants.Direction.*;
import static helpers.Constants.Tiles.*;
import static helpers.Constants.Enemies.*;

public class EnemyManager {

	private Playing playing;
	private int HpBarWidth = 20;
	private BufferedImage[] enemyImgs;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	//private float speed = 0.5f;
	private PathPoint start, end;

	public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
		this.playing = playing;
		enemyImgs = new BufferedImage[5];
		this.start = start;
		this.end = end;

//		addEnemy(KNIGHT);
//		addEnemy(ALLMIGHTYKNIGHT);
//		addEnemy(OCTOROK);
//		addEnemy(BAT);
//		addEnemy(SKELETON);
		loadEnemyImgs();
	}

	private void loadEnemyImgs() {
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		enemyImgs[0] = atlas.getSubimage(32 * 2, 32 * 3, 32, 32);
		enemyImgs[1] = atlas.getSubimage(32 * 2, 32 * 2, 32, 32);
		enemyImgs[2] = atlas.getSubimage(32 * 4, 32 * 2, 32, 32);
		enemyImgs[3] = atlas.getSubimage(32, 32 * 2, 32, 32);
		enemyImgs[4] = atlas.getSubimage(0, 32 * 3, 32, 32);

	}

	public void update() {

		updateWaveManager();

		if (isWaveStarts()) {
			enemySpawning();
		}

		for (Enemy e : enemies) {
			if (e.isAlive()) {
				updateEnemyMove(e);
			}
		}
	}

	private void updateWaveManager() {
		playing.getWaveManager().update();
	}
	private void enemySpawning() {
		addEnemy(playing.getWaveManager().getNextEnemy());
	}
	private boolean isWaveStarts() {
		if(playing.getWaveManager().isWaveStarts()) {
			if (playing.getWaveManager().isEnemiesLeftInWave()) {
				return true;
			}
		}
			return false;
	}

	public void updateEnemyMove(Enemy e) {
		if (e.getLastDir() == -1)
			setNewDirectionAndMove(e);

		int newX = (int) (e.getX() + getSpeedAndWidth(e.getLastDir(), e.getEnemyType()));
		int newY = (int) (e.getY() + getSpeedAndHeight(e.getLastDir(), e.getEnemyType()));

		if (getTileType(newX, newY) == ROAD_TILE) {
			e.move(GetSpeed(e.getEnemyType()), e.getLastDir());
		} else if (isAtEnd(e)) {
			e.kill();
			System.out.println("A live is lost ! :( ");
		} else {
			setNewDirectionAndMove(e);
		}
	}

	private void setNewDirectionAndMove(Enemy e) {
		int dir = e.getLastDir();

		int xCord = (int) (e.getX() / 32);
		int yCord = (int) (e.getY() / 32);

		fixEnemyOffsetTile(e, dir, xCord, yCord);

		if (isAtEnd(e))
			return;

		if (dir == LEFT || dir == RIGHT) {
			int newY = (int) (e.getY() + getSpeedAndHeight(UP, e.getEnemyType()));
			if (getTileType((int) e.getX(), newY) == ROAD_TILE)
				e.move(GetSpeed(e.getEnemyType()), UP);
			else
				e.move(GetSpeed(e.getEnemyType()), DOWN);
		} else {
			int newX = (int) (e.getX() + getSpeedAndWidth(RIGHT, e.getEnemyType()));
			if (getTileType(newX, (int) e.getY()) == ROAD_TILE)
				e.move(GetSpeed(e.getEnemyType()), RIGHT);
			else
				e.move(GetSpeed(e.getEnemyType()), LEFT);

		}

	}

	private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
		switch (dir) {
		case RIGHT:
			if (xCord < 19)
				xCord++;
			break;
		case DOWN:
			if (yCord < 19)
				yCord++;
			break;
		}

		e.setPos(xCord * 32, yCord * 32);

	}

	private boolean isAtEnd(Enemy e) {
		if (e.getX() == end.getxCord() * 32)
			if (e.getY() == end.getyCord() * 32)
				return true;
		return false;
	}

	private int getTileType(int x, int y) {
		return playing.getTileType(x, y);
	}

	private float getSpeedAndHeight(int dir, int enemyType) {
		if (dir == UP)
			return -GetSpeed(enemyType);
		else if (dir == DOWN)
			return GetSpeed(enemyType) + 32;

		return 0;
	}

	private float getSpeedAndWidth(int dir, int enemyType) {
		if (dir == LEFT)
			return -GetSpeed(enemyType);
		else if (dir == RIGHT)
			return GetSpeed(enemyType) + 32;

		return 0;
	}
	public void spawnEnemy(int nextEnemy) {
		addEnemy(nextEnemy);
	}
	public void addEnemy(int enemyType) {

		int x = start.getxCord() * 32;
		int y = start.getyCord() * 32;

		switch (enemyType) {
		case OCTOROK:
			enemies.add(new Octorok(x, y, 0));
			break;
		case BAT:
			enemies.add(new Bat(x, y, 0));
			break;
		case KNIGHT:
			enemies.add(new Knight(x, y, 0));
			break;
		case SKELETON:
			enemies.add(new Skeleton(x, y, 0));
			break;
		case ALLMIGHTYKNIGHT:
			enemies.add(new AllmightyKnight(x, y, 0));
			break;
		}

	}

	public void draw(Graphics g) {
		for (Enemy e : enemies) {
			if (e.isAlive()) {
				drawEnemy(e, g);
				drawHpBar(e, g);
			}
		}
	}

	public void drawHpBar(Enemy e, Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) e.getX() +16 - (refreshHpBarEnemies(e) / 2), (int) e.getY() - 5, refreshHpBarEnemies(e), 3);
	}

	private int refreshHpBarEnemies(Enemy e) {
		return (int)(HpBarWidth * e.getHpBar());
	}

	private void drawEnemy(Enemy e, Graphics g) {
		g.drawImage(enemyImgs[e.getEnemyType()], (int) e.getX(), (int) e.getY(), null);
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	public int getAmountOfAliveEnemies() {
		int size = 0;
		for (Enemy e : enemies)
			if (e.isAlive())
				size++;
		return size;
	}
}
