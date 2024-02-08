package scenes;

import javax.swing.*;
import java.awt.*;
import main.Game;
import ui.ButtonModel;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {

	private ButtonModel bPlaying, bEdit, bSettings, bQuit;
	private Image backgroundImage;

	public Menu(Game game) {
		super(game);
		initUI();
		initButtons();
	}

	private void initUI() {
		backgroundImage = new ImageIcon("resources/imagemenu.jpg").getImage();
	}

	private void initButtons() {
		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 350;
		int yOffset = 100;

		bPlaying = new ButtonModel("Play", x, y, w, h);
		bEdit = new ButtonModel("Edit", x, y + yOffset, w, h);
		bSettings = new ButtonModel("How to play", x, y + yOffset * 2, w, h);
		bQuit = new ButtonModel("Quit", x, y + yOffset * 3, w, h);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, game.getWidth(), game.getHeight(), null);
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bPlaying.draw(g);
		bEdit.draw(g);
		bSettings.draw(g);
		bQuit.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bPlaying.getBounds().contains(x, y))
			SetGameState(PLAYING);
		else if (bEdit.getBounds().contains(x, y))
			SetGameState(EDIT);
		else if (bSettings.getBounds().contains(x, y))
			SetGameState(SETTINGS);
		else if (bQuit.getBounds().contains(x, y))
			System.exit(0);
	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		bEdit.setMouseOver(false);
		bSettings.setMouseOver(false);
		bQuit.setMouseOver(false);

		if (bPlaying.getBounds().contains(x, y))
			bPlaying.setMouseOver(true);
		else if (bEdit.getBounds().contains(x, y))
			bEdit.setMouseOver(true);
		else if (bSettings.getBounds().contains(x, y))
			bSettings.setMouseOver(true);
		else if (bQuit.getBounds().contains(x, y))
			bQuit.setMouseOver(true);
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bPlaying.getBounds().contains(x, y))
			bPlaying.setMousePressed(true);
		else if (bEdit.getBounds().contains(x, y))
			bEdit.setMousePressed(true);
		else if (bSettings.getBounds().contains(x, y))
			bSettings.setMousePressed(true);
		else if (bQuit.getBounds().contains(x, y))
			bQuit.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bEdit.resetBooleans();
		bSettings.resetBooleans();
		bQuit.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {

	}
}
