package com.lydzje.corruptioSack.entities.mobs;

import com.lydzje.corruptioSack.gameplay.Attack;
import com.lydzje.corruptioSack.gameplay.AttackArea;
import com.lydzje.corruptioSack.gameplay.ai.ShieldAI;
import com.lydzje.corruptioSack.graphics.AnimatedSprite;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.SpriteSheet;
import com.lydzje.corruptioSack.maths.Vector2d;
import com.lydzje.corruptioSack.utils.Sound;

public class Shield extends Mob {

	private AnimatedSprite shieldR = new AnimatedSprite(64, 64, 4, SpriteSheet.shield_right);
	private AnimatedSprite shieldL = new AnimatedSprite(64, 64, 4, SpriteSheet.shield_left);
	private AnimatedSprite walkingR = new AnimatedSprite(64, 64, 4, SpriteSheet.shieldW_right);
	private AnimatedSprite walkingL = new AnimatedSprite(64, 64, 4, SpriteSheet.shieldW_left);

	private AnimatedSprite pushR = new AnimatedSprite(64, 64, 3, SpriteSheet.shield_pushR);
	private AnimatedSprite pushL = new AnimatedSprite(64, 64, 3, SpriteSheet.shield_pushL);

	private Attack push;

	public Shield(Vector2d position) {
		super(position);
		new ShieldAI(this);
		mobX = 9;
		mobWidth = 44;
		speed = 1;
		originalSpeed = speed;
		maxHP = 500;
		hp = 500;
		corruption = 400;

		anim = shieldR;
		anim.setFps(5);

		sprite = anim.getSprite();

		initAttacks();
	}

	public void initAttacks() {
		attacks = new Attack[1];

		areas = new AttackArea[1];
		aKeys = new int[1];
		areas[0] = new AttackArea(this, 9, 32, (int) position.y, 24, 64, 90, 0);
		aKeys[0] = 1;

		sounds = new Sound[1];
		sKeys = new int[1];
		sounds[0] = Sound.sounds[1];
		sKeys[0] = 1;

		push = new Attack(this, areas, aKeys, sounds, sKeys);

		attacks[0] = push;
	}

	private int timer = 1;

	public void attack() {
		attackRate = 100;
		if (dir == Direction.RIGHT) anim = pushR;
		else if (dir == Direction.LEFT) anim = pushL;
		anim.setPlayingAnim(true);
		attacking = true;
		attackToPerform = true;
	}

	public void update() {
		anim.update();
		timer++;

		if (attacking) {
			for (int i = 0; i < attacks.length; i++)
				attacks[i].update();
		}

		if (attackRate > 0) attackRate--;

		/*
		 * if (timer % 120 == 0) {
		 * dir = Game.random.nextInt(2) == 0 ? Direction.LEFT : Direction.RIGHT;
		 * walking = Game.random.nextBoolean();
		 * }
		 */

		if (!anim.isPlayingAnim()) {
			if (walking) anim = dir == Direction.LEFT ? walkingL : walkingR;
			else anim = dir == Direction.LEFT ? shieldL : shieldR;
		}
		super.update();

		if (!hasTarget) moveRandomly(shieldL, shieldR, walkingL, walkingR);
	}

	public void render(Screen screen) {
		sprite = anim.getSprite();
		if (!hurt && !frozen) screen.renderMob(position, this, hurt, frozen);
		else if (frozen) screen.renderMob(position, this, hurt, frozen);
		else if (hurt && timer % 30 >= 0 && timer % 30 <= 20) screen.renderMob(position, this, hurt, frozen);
	}

}
