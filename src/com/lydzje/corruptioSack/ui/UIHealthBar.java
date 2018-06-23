package com.lydzje.corruptioSack.ui;

import com.lydzje.corruptioSack.entities.mobs.Mob;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.Sprite;
import com.lydzje.corruptioSack.maths.Vector2d;

public class UIHealthBar extends UIComponent {
	private Mob mob;
	private float health = 1;
	private float damage = 1;

	public UIHealthBar(Vector2d position, Vector2d offset, Mob mob) {
		super(position, offset);
		this.mob = mob;

	}

	private float damageTaken;
	private float damageTaked;
	private boolean wait = false;
	private boolean done = false;
	private float dmgBarSpeed;

	public void update() {
		if (mob.getDamageTaken() > 0 && mob.getHp() > 0) {
			damageTaked = damageTaken;
			damageTaken += mob.getDamageTaken() / (float) mob.getMaxHP();
			dmgBarSpeed = 0.0001f;
			wait = true;
		}

		if (damageTaken > health && mob.getHp() <= 0 && !done) {
			done = true;
			damageTaken = damageTaked + health;
		}

		health = (float) mob.getHp() / (float) mob.getMaxHP();
		if (mob.isDamageTaked()) {
			if (damageTaken > 1.0f) damageTaken = 1.0f;
			damage = (float) mob.getHp() / (float) mob.getMaxHP() + damageTaken;

		}
		if (damageTaken > 0 && !wait) damageTaken -= dmgBarSpeed;
		dmgBarSpeed += 0.0001f;
		wait = false;
	}

	public void render(Screen screen) {
		double x = getAbsolutePosition().x;
		double y = getAbsolutePosition().y;

		screen.drawRect(new Vector2d(x, y), 120, 5, 0xff897D7D, false);

		screen.drawRect(new Vector2d(x, y), (int) ((120 * damage) > 120 ? 120 : 120 * damage), 5,
				0xFFE5BD72, false);// EBC484
		screen.drawRect(new Vector2d(x, y), (int) (120 * health), 5, 0xFFAF261E, false);// 600009

		screen.renderSprite(new Vector2d(x - 2, y - 3), Sprite.healthBar, false);
	}

}