package com.lydzje.corruptioSack.gameplay;

import java.util.ArrayList;
import java.util.List;

import com.lydzje.corruptioSack.entities.mobs.Mob;
import com.lydzje.corruptioSack.entities.mobs.particles.ParticleEmmiter;
import com.lydzje.corruptioSack.graphics.AnimatedSprite;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.SpriteSheet;
import com.lydzje.corruptioSack.levels.Level;
import com.lydzje.corruptioSack.maths.Vector2d;
import com.lydzje.corruptioSack.utils.Camera;
import com.lydzje.corruptioSack.utils.Sound;

public class Thunderbolt {

	public static List<Thunderbolt> thunderbolts = new ArrayList<Thunderbolt>();
	public static List<Thunderbolt> thunderboltsAttackAreas = new ArrayList<Thunderbolt>();

	private Vector2d position;
	private AnimatedSprite thunderbolt = new AnimatedSprite(64, 224, 16, SpriteSheet.thunderBolt);
	private int damage;

	public Thunderbolt(Vector2d position) {
		this.position = position;
		thunderbolts.add(this);
		thunderboltsAttackAreas.add(this);
		damage = 140;
		thunderbolt.setPlayingAnim(true);
	}

	public int getFrame() {
		return thunderbolt.getFrame();
	}

	public int getDamage() {
		return damage;
	}

	public boolean contains(Mob mob) {
		int x = (int) position.x;
		int width = 64;
		int mobX = (int) (mob.getMobX() + mob.getPosition().x);
		int mobW = (int) (mobX + mob.getMobWidth());

		return ((x <= mobX && x + width >= mobX) || (x >= mobX && x + width <= mobW) || (x <= mobW && x
				+ width >= mobW));
	}

	public void update() {
		thunderbolt.update();
		if (thunderbolt.getFrame() == 14) {
			Camera.shakeCamera(30);
			Level.pEmmiters.add(new ParticleEmmiter(new Vector2d(
					position.x + 32, 224), 80, 80, 0xff748139));
			Sound.sounds[3].play();
		}
		if (!thunderbolt.isPlayingAnim()) thunderbolts.remove(this);
	}

	public void render(Screen screen) {
		screen.renderSprite(position, thunderbolt.getSprite(), true);
	}
}
