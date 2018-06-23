package com.lydzje.corruptioSack.gameplay;

import com.lydzje.corruptioSack.entities.mobs.Mob;
import com.lydzje.corruptioSack.entities.mobs.Player;
import com.lydzje.corruptioSack.utils.Sound;

public class Attack {
	private Mob mob;
	private AttackArea[] areas;
	private int[] aKeys;
	private int attackFramePerformed = -1;
	private Sound[] sounds;
	private int[] sKeys;
	private int soundFramePerformed = -1;

	private boolean byEnemy = true;

	public Attack(Mob mob, AttackArea[] areas, int[] aKeys, Sound[] sounds, int[] sKeys) {
		this.mob = mob;
		if (mob instanceof Player) byEnemy = false;
		this.areas = areas;
		this.aKeys = aKeys;
		this.sounds = sounds;
		this.sKeys = sKeys;

	}

	public boolean isByEnemy() {
		return byEnemy;
	}

	public void setByEnemy(boolean byEnemy) {
		this.byEnemy = byEnemy;
	}

	public void update() {

		for (int i = 0; i < sKeys.length && mob.getAnimSprite().getFrame() != soundFramePerformed; i++)
			if (mob.getAnimSprite().getFrame() == sKeys[i]) {
				sounds[i].play();

			}
		soundFramePerformed = mob.getAnimSprite().getFrame();

		for (int i = 0; i < aKeys.length && mob.getAnimSprite().getFrame() != attackFramePerformed; i++)
			if (mob.getAnimSprite().getFrame() == aKeys[i]) {
				new AttackArea(areas[i]);
			}
		attackFramePerformed = mob.getAnimSprite().getFrame();

		if (mob.getAnimSprite().getFrame() == mob.getAnimSprite().getLength() - 1) {
			areas[0].getMob().setAttacking(false);
			attackFramePerformed = -1;
			soundFramePerformed = -1;
		}

	}
}