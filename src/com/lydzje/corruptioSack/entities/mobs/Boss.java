package com.lydzje.corruptioSack.entities.mobs;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.graphics.AnimatedSprite;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.SpriteSheet;
import com.lydzje.corruptioSack.maths.Vector2d;

public class Boss extends Mob {

	private AnimatedSprite bossR = new AnimatedSprite(64, 64, 4, SpriteSheet.boss_right);
	private AnimatedSprite bossL = new AnimatedSprite(64, 64, 4, SpriteSheet.boss_left);

	public Boss(Vector2d position) {
		super(position);
		mobX = 26;
		mobWidth = 14;

		speed = 1.5;
		originalSpeed = speed;
		maxHP = 500;
		hp = 500;
		anim = bossR;
		sprite = anim.getSprite();
	}

	private int timer = 1;

	public void update() {
		anim.update();
		timer++;

		if (timer % 120 == 0) {
			dir = Game.random.nextInt(2) == 0 ? Direction.LEFT : Direction.RIGHT;
			// walking = Game.random.nextBoolean();
		}

		/*
		 * if (walking) anim = dir == Direction.LEFT ? walkingL : walkingR;
		 * else
		 */anim = dir == Direction.LEFT ? bossL : bossR;

		super.update();
	}

	public void render(Screen screen) {
		sprite = anim.getSprite();

		if (!hurt && !frozen) screen.renderMob(position, this, hurt, frozen);
		else if (frozen) screen.renderMob(position, this, hurt, frozen);
		else if (hurt && timer % 30 >= 0 && timer % 30 <= 20) screen.renderMob(position, this, hurt, frozen);
	}

}
