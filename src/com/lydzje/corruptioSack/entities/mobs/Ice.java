package com.lydzje.corruptioSack.entities.mobs;

import com.lydzje.corruptioSack.entities.projectiles.IceProjectile;
import com.lydzje.corruptioSack.gameplay.ai.IceAI;
import com.lydzje.corruptioSack.graphics.AnimatedSprite;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.SpriteSheet;
import com.lydzje.corruptioSack.levels.Level;
import com.lydzje.corruptioSack.maths.Vector2d;
import com.lydzje.corruptioSack.utils.Sound;

public class Ice extends Mob {
	private AnimatedSprite iceR = new AnimatedSprite(32, 32, 4, SpriteSheet.ice_right);
	private AnimatedSprite iceL = new AnimatedSprite(32, 32, 4, SpriteSheet.ice_left);
	private AnimatedSprite walkingR = new AnimatedSprite(32, 32, 4, SpriteSheet.iceW_right);
	private AnimatedSprite walkingL = new AnimatedSprite(32, 32, 4, SpriteSheet.iceW_left);

	public Ice(Vector2d position) {
		super(position);
		new IceAI(this);
		mobX = 4;
		mobWidth = 24;
		maxHP = 100;
		hp = 60;
		corruption = 200;
		speed = 1.8;
		originalSpeed = speed;
		anim = iceR;
		sprite = anim.getSprite();
	}

	public void moveTo() {
		if (target.getPosition().x < position.x) {
			dir = Direction.LEFT;
		} else dir = Direction.RIGHT;

		if (Math.abs(position.x - target.getPosition().x) > 190) {
			walking = true;
			inRange = false;
		}

		else {
			walking = false;
			inRange = true;
		}
	}

	public void attack() {
		attackRate = 90;
		shoot();
	}

	private void shoot() {
		Vector2d shotPos = new Vector2d(position.x + sprite.getWidth() / 2, position.y
				+ sprite.getHeight() / 2);
		Sound.sounds[4].play();
		Level.projectiles.add(new IceProjectile(shotPos, dir));
	}

	private int timer = 1;

	public void update() {
		anim.update();
		timer++;
		if (attackRate > 0) attackRate--;

		if (walking) anim = dir == Direction.LEFT ? walkingL : walkingR;
		else anim = dir == Direction.LEFT ? iceL : iceR;

		super.update();

		if (!hasTarget) moveRandomly(iceL, iceR, walkingL, walkingR);
	}

	public void render(Screen screen) {
		sprite = anim.getSprite();
		if (!hurt && !frozen) screen.renderMob(position, this, hurt, frozen);
		else if (frozen) screen.renderMob(position, this, hurt, frozen);
		else if (hurt && timer % 30 >= 0 && timer % 30 <= 20) screen.renderMob(position, this, hurt, frozen);
	}

}
