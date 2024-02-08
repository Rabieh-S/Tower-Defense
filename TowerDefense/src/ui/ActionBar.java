package ui;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.*;
import java.text.DecimalFormat;

import helpers.Constants;
import towers.*;
import scenes.Playing;

public class ActionBar extends Bar {

	private Playing playing;
	private ButtonModel bMenu;
	private ButtonModel[] towerButton;
	private Tower selectedTower;
	private Tower displayedTower;
	private DecimalFormat formatter;
	public ActionBar(int x, int y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		formatter = new DecimalFormat("0.0");
		initButtons();
	}

	private void initButtons() {

		bMenu = new ButtonModel("Menu", 2, 642, 100, 30);
		towerButton = new ButtonModel[9];
		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f);
		for (int i = 0; i < towerButton.length; i++) {
			towerButton[i] = new ButtonModel("", xStart + xOffset * i, yStart, w, h, i);
		}

	}

	private void drawButtons(Graphics g) {

		bMenu.draw(g);
		for(ButtonModel b : towerButton) {
			g.setColor(Color.gray);
			g.fillRect(b.x, b.y, b.width, b.height);
			g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, null);
			drawButtonFeedback(g, b);
		}
	}

	public void draw(Graphics g) {

		// Background
		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, width, height);

		// Buttons
		drawButtons(g);

		drawDisplayedTower(g);
		drawWaveInfo(g);
	}

	private void drawWaveInfo(Graphics g) {
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		drawWaveTimerInfo(g);
		drawEnemiesLeftInfo(g);
		drawWavesLeftInfo(g);

	}

	private void drawWavesLeftInfo(Graphics g) {
		int current = playing.getWaveManager().getWaveIndex();
		int size = playing.getWaveManager().getWaves().size();
		g.drawString("Wave " + (current + 1) + " / " + size, 425, 750);

	}

	private void drawEnemiesLeftInfo(Graphics g) {
		int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();
		g.drawString("Enemies Left: " + remaining, 425, 780);
	}

	private void drawWaveTimerInfo(Graphics g) {
		if (playing.getWaveManager().isWaveTimerStarted()) {

			g.setColor(Color.black);
			float timeLeft = playing.getWaveManager().getTimeLeft();
			String formattedText = formatter.format(timeLeft);
			g.drawString("Time Left: " + formattedText, 425, 720);
		}
	}



	public void displayTower(Tower t) {
		displayedTower = t;
	}

	private void drawDisplayedTower(Graphics g) {
		if (displayedTower != null) {
			g.setColor(Color.red);
			g.fillRect(400, 710, 220, 85 );
			g.setColor(Color.black);
			g.drawRect(400,710,220,85);
			g.drawRect(405,715,50,50);
			g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 405, 715, 50, 50, null );
			g.setColor(Color.black);
			g.drawString("" + Constants.Towers.GetName(displayedTower.getTowerType()), 460, 730);
			drawDisplayedTowerBorder(g);
			drawTowerRange(g);
		}
	}

	private void drawTowerRange(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawOval(displayedTower.getX() + 16 - (int) (displayedTower.getRange() * 2 / 2), displayedTower.getY() + 16 - (int) (displayedTower.getRange() * 2 / 2), (int)displayedTower.getRange() * 2, (int)displayedTower.getRange() * 2);
	}

	private void drawDisplayedTowerBorder(Graphics g) {
		g.setColor(Color.CYAN);
		g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
	}

	public void mouseClicked(int x, int y) {
		for (ButtonModel b : towerButton) {
			if (b.getBounds().contains(x, y)) {
				Tower selectedTower = createTower(b.getId());
				System.out.println(b.getId());

				if (selectedTower instanceof TowerUpgradable) {
					((TowerUpgradable) selectedTower).upgrade();
				}

				playing.setSelectedTower(selectedTower);
				return;
			}
		}
	}

	private Tower createTower(int id) {
		switch (id) {
			case 0:
				return new ArcheryTower(0, 0, -1, 0);
			case 1:
				return new ArcheryTower2(0, 0, -1, 1);
			case 2:
				return new ArcheryTower3(0, 0, -1, 2);
			case 3:
				return new CannonTower(0, 0, -1, 3);
			case 4:
				return new CannonTower2(0, 0, -1, 4);
			case 5:
				return new CannonTower3(0, 0, -1, 5);
			case 6:
				return new MagicTower(0, 0, -1, 6);
			case 7:
				return new MagicTower2(0, 0, -1, 7);
			case 8:
				return new MagicTower3(0, 0, -1, 8);
			default:
				throw new IllegalArgumentException("ID de tour inconnu : " + id);
		}
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
		else {
			for(ButtonModel b: towerButton) {
				if (b.getBounds().contains(x, y)) {
					b.setMouseOver(true);
					return;
				}
			}
		}
	}

	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
		else {
			for(ButtonModel b: towerButton) {
				if (b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
					return;
				}
			}
		}
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		for (ButtonModel b: towerButton) {
			b.resetBooleans();
		}
	}

}
