package scenes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import enemies.Enemy;
import helpers.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import managers.WaveManager;
import objects.PathPoint;
import towers.Tower;
import ui.ActionBar;
import managers.WaveManager;

import static helpers.Constants.Tiles.*;

public class Playing extends GameScene implements SceneMethods {

	private int[][] lvl;
	private ActionBar actionBar;
	private int mouseX, mouseY;
	private EnemyManager enemyManager;
	private TowerManager towerManager;
	private PathPoint start, end;
	private Tower selectedTower;
	private ProjectileManager projectileManager;
	private WaveManager waveManager;

	public Playing(Game game) {
		super(game);

		loadDefaultLevel();

		actionBar = new ActionBar(0, 640, 640, 160, this);

		enemyManager = new EnemyManager(this, start, end);
		towerManager = new TowerManager(this);
		projectileManager = new ProjectileManager(this);
		waveManager = new WaveManager(this);
	}

	private void loadDefaultLevel() {
		lvl = LoadSave.GetLevelData("new_level");
		System.out.println(lvl);
		ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
		start = points.get(0);
		end = points.get(1);
	}

	public void setLevel(int[][] lvl) {
		this.lvl = lvl;
	}

	public void update() {
		updateTick();
		waveManager.update();
		if (isAllEnemiesDead()) {
			if (isThereMoreWaves()) {
				waveManager.startWaveTimer();
				if (isWaveTimerOver()) {
					waveManager.increaseWaveIndex();
					enemyManager.getEnemies().clear();
					waveManager.resetEnemyIndex();
				}
			}
		}
		if (isTimeForNewEnemy()) {
			spawnEnemy();
		}
		enemyManager.update();
		enemyManager.update();
		towerManager.update();
		towerManager.update();
		projectileManager.update();
	}
	private boolean isWaveTimerOver() {

		return waveManager.isWaveTimerOver();
	}
	private boolean isThereMoreWaves() {
		return waveManager.isThereMoreWaves();
	}
	private boolean isAllEnemiesDead() {
		if (waveManager.isEnemiesLeftInWave())
			return false;
		for (Enemy e : enemyManager.getEnemies())
			if (e.isAlive())
				return false;
		return true;
	}
	private void spawnEnemy() {
		enemyManager.spawnEnemy(waveManager.getNextEnemy());
	}
	private boolean isTimeForNewEnemy() {
		if (waveManager.isWaveStarts()) {
			if (waveManager.isEnemiesLeftInWave())
				return true;
		}
		return false;
	}
	public void setSelectedTower(Tower selectedTower) {
		this.selectedTower = selectedTower;
	}

	@Override
	public void render(Graphics g) {

		drawLevel(g);
		actionBar.draw(g);
		enemyManager.draw(g);
		towerManager.draw(g);
		projectileManager.draw(g);
		drawSelectedTower(g);
		drawHighlight(g);

		drawWaveInfos(g);
	}
	private void drawWaveInfos(Graphics g) {
	}

	private void drawHighlight(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(mouseX, mouseY, 32, 32);
	}

	private void drawLevel(Graphics g) {

		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				if (isAnimation(id)) {
					g.drawImage(getSprite(id, animationIndex), x * 32, y * 32, null);
				} else
					g.drawImage(getSprite(id), x * 32, y * 32, null);
			}
		}
	}

	private void drawSelectedTower(Graphics g) {
		if (selectedTower != null) {
			g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()], mouseX, mouseY, null);
		}
	}

	public int getTileType(int x, int y) {
		int xCord = x / 32;
		int yCord = y / 32;

		if (xCord < 0 || xCord > 19)
			return 0;
		if (yCord < 0 || yCord > 19)
			return 0;

		int id = lvl[y / 32][x / 32];
		return game.getTileManager().getTile(id).getTileType();
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (y >= 640)
			actionBar.mouseClicked(x, y);
		else {
			if(selectedTower != null) {
				if(isTileGrass(mouseX, mouseY)) {
					if (getTowerAt(mouseX, mouseY) == null) {
						towerManager.addTower(selectedTower, mouseX, mouseY);
						selectedTower = null;
					}
				}
			}
			else {
				Tower t = getTowerAt(mouseX, mouseY);
				actionBar.displayTower(t);
			}
		}
	}

	private Tower getTowerAt(int x, int y) {
		return towerManager.getTowerAt(x,y);
	}

	private boolean isTileGrass(int x, int y) {
		int id = lvl[y / 32][x / 32];
		int tileType = game.getTileManager().getTile(id).getTileType();
		return tileType == GRASS_TILE;
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			selectedTower = null;
		}
	}

	@Override
	public void mouseMoved(int x, int y) {
		if (y >= 640)
			actionBar.mouseMoved(x, y);
		else {
			mouseX = (x / 32) * 32;
			mouseY = (y / 32) * 32;
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (y >= 640) {
			actionBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		actionBar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y) {

	}

	public TowerManager getTowerManager() {
		return towerManager;
	}

	public EnemyManager getEnemyManager() {
		return enemyManager;
	}

	public WaveManager getWaveManager() {
		return waveManager;
	}

	public void aimAtEnemy(Tower t, Enemy e) {
		projectileManager.refreshProjectile(t, e);
	}
}