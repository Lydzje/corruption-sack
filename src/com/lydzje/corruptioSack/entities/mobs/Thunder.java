package com.lydzje.corruptioSack.entities.mobs;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.gameplay.Thunderbolt;
import com.lydzje.corruptioSack.gameplay.ai.ThunderAI;
import com.lydzje.corruptioSack.graphics.AnimatedSprite;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.SpriteSheet;
import com.lydzje.corruptioSack.maths.Vector2d;

public class Thunder extends Mob {

	private AnimatedSprite thunderR = new AnimatedSprite(64, 64, 4, SpriteSheet.thunder_right);
	private AnimatedSprite thunderL = new AnimatedSprite(64, 64, 4, SpriteSheet.thunder_left);
	private AnimatedSprite walkingR = new AnimatedSprite(64, 64, 4, SpriteSheet.thunderW_right);
	private AnimatedSprite walkingL = new AnimatedSprite(64, 64, 4, SpriteSheet.thunderW_left);

	public Thunder(Vector2d position) {
		super(position);
		new ThunderAI(this);
		mobX = 12;
		mobWidth = 49;

		speed = 2;
		originalSpeed = speed;
		maxHP = 100;
		hp = 100;
		corruption = 600;
		anim = thunderR;
		sprite = anim.getSprite();
	}

	public void moveTo() {
		if (target.getPosition().x < position.x) {
			dir = Direction.LEFT;
		} else dir = Direction.RIGHT;

		if (Math.abs(position.x - target.getPosition().x) > 150) {
			walking = true;
			inRange = false;
		}

		else {
			walking = false;
			inRange = true;
		}
	}

	public void attack() {
		attackRate = 180;
		new Thunderbolt(new Vector2d(target.getPosition().x
				+ (Game.random.nextBoolean() ? 0 : (Game.random.nextInt(130) - 65)), 0));
	}

	private int timer = 1;

	public void update() {
		anim.update();
		timer++;
		if (attackRate > 0) attackRate--;

		if (walking) anim = dir == Direction.LEFT ? walkingL : walkingR;
		else anim = dir == Direction.LEFT ? thunderL : thunderR;

		super.update();

		if (!hasTarget) moveRandomly(thunderL, thunderR, walkingL, walkingR);
	}

	public void render(Screen screen) {
		sprite = anim.getSprite();

		if (!hurt && !frozen) screen.renderMob(position, this, hurt, frozen);
		else if (frozen) screen.renderMob(position, this, hurt, frozen);
		else if (hurt && timer % 30 >= 0 && timer % 30 <= 20) screen.renderMob(position, this, hurt, frozen);
	}

}
