package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import main.Game;
import ui.ButtonModel;

import static main.GameStates.*;

public class Settings extends GameScene implements SceneMethods {

	private ButtonModel bMenu;
	private Image backgroundImage;

	public Settings(Game game) {
		super(game);
		initButtons();
		initBackgroundImage();
	}

	private void initButtons() {
		bMenu = new ButtonModel("Menu", 270, 730, 100, 30);
	}

	private void initBackgroundImage() {
		backgroundImage = new ImageIcon("resources/rules.png").getImage();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, null);
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		bMenu.draw(g);
	}

	@Override
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			SetGameState(MENU);
	}

	@Override
	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMouseOver(true);
	}

	@Override
	public void mousePressed(int x, int y) {
		if (bMenu.getBounds().contains(x, y))
			bMenu.setMousePressed(true);
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bMenu.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {

	}
}
