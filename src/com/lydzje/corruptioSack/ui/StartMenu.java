package com.lydzje.corruptioSack.ui;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.graphics.AnimatedSprite;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.Sprite;
import com.lydzje.corruptioSack.maths.Vector2d;
import com.lydzje.corruptioSack.maths.Vector3i;

public class StartMenu {
	private UIManager ui;
	private UIPanel panel;
	private UIButton play;
	private UIButton quit;

	private Sprite sprite, playB, quitB;

	public StartMenu() {
		this.ui = Game.getUIManager();
		playB = Sprite.playB;
		quitB = Sprite.quitB;

		Vector2d panelPos = new Vector2d(196, 179);
		panel = new UIPanel(panelPos);
		ui.add(panel);

		play = new UIButton(panelPos, new Vector2d(0, 0), playB, new ButtonActionListener() {

			public void perform() {
				Game.state = 1;
				panel.setActive(false);
			}

		});

		play.setButtonListener(new UIButtonListener() {
			public void entered(UIButton button) {
				button.getSprite().setToBright(true);
				button.getSprite().setBright(new Vector3i(-7, -19, -161));
			}

			public void exited(UIButton button) {
				button.getSprite().setToBright(false);
				button.getSprite().setBright(new Vector3i(0, 0, 0));
			}

			public void pressed(UIButton button) {
				button.getSprite().setToBright(true);
				button.getSprite().setBright(new Vector3i(0, -135, -208));
			}

			public void released(UIButton button) {
				button.getSprite().setToBright(true);
				button.getSprite().setBright(new Vector3i(-7, -19, -161));
			}
		});
		panel.add(play);

		quit = new UIButton(panelPos, new Vector2d(6, 41), quitB, new ButtonActionListener() {

			public void perform() {
				System.exit(0);
			}

		});

		quit.setButtonListener(new UIButtonListener() {
			public void entered(UIButton button) {
				button.getSprite().setToBright(true);
				button.getSprite().setBright(new Vector3i(-7, -19, -161));
			}

			public void exited(UIButton button) {
				button.getSprite().setToBright(false);
				button.getSprite().setBright(new Vector3i(0, 0, 0));
			}

			public void pressed(UIButton button) {
				button.getSprite().setToBright(true);
				button.getSprite().setBright(new Vector3i(0, -135, -208));

			}

			public void released(UIButton button) {
				button.getSprite().setToBright(true);
				button.getSprite().setBright(new Vector3i(-7, -19, -161));
			}
		});
		panel.add(quit);
		panel.setActive(false);
	}

	public void activaUI(boolean state) {
		panel.setActive(state);
	}

	public void update() {
		AnimatedSprite.startMenuAnim.update();
	}

	public void render(Screen screen) {
		sprite = AnimatedSprite.startMenuAnim.getSprite();
		screen.renderSprite(new Vector2d(0, 0), sprite, false);
	}

}
