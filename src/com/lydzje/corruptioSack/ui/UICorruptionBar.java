package com.lydzje.corruptioSack.ui;

import com.lydzje.corruptioSack.entities.mobs.Player;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.maths.Vector2d;

public class UICorruptionBar extends UIComponent {

	private Player player;
	private double corruptionGoal;
	private double currentCorruption;

	public UICorruptionBar(Vector2d position, Vector2d offset, Player player) {
		super(position, offset);
		this.player = player;
		corruptionGoal = 700;
		currentCorruption = player.getCorruption();
	}

	private float barSpeed = 0.001f;

	public void update() {

		if (player.getCorruption() > currentCorruption * corruptionGoal) {
			if (barSpeed == 0) barSpeed = 0.001f;
			currentCorruption = currentCorruption >= 1 ? 1 : (currentCorruption + barSpeed);
			if (currentCorruption > player.getCorruption() / corruptionGoal) currentCorruption = player
					.getCorruption() / corruptionGoal;
			barSpeed += 0.001f;
		} else barSpeed = 0;

		if (currentCorruption >= 1) {
			player.addCorruption((int) -corruptionGoal);
			player.unlockForm();
			barSpeed = 0;
			corruptionGoal *= 2.5;
			currentCorruption = 0;
		}

		// System.out.println(barSpeed + "\t|\t" + currentCorruption + "\t|\t" + player.getCorruption());

	}

	public void render(Screen screen) {
		double x = getAbsolutePosition().x;
		double y = getAbsolutePosition().y;

		screen.drawRect(new Vector2d(x, y), 100, 3, 0xff897D7D, false);
		screen.drawRect(new Vector2d(x, y), (int) (100 * currentCorruption), 3, 0xFF203E47, false);
		screen.drawRect(new Vector2d(x, y), 110, 1, 0xFF070D0F, false);
	}

}
