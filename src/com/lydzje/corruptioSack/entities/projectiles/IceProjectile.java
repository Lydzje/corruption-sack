package com.lydzje.corruptioSack.entities.projectiles;

import com.lydzje.corruptioSack.entities.mobs.Mob.Direction;
import com.lydzje.corruptioSack.entities.mobs.particles.ParticleEmmiter;
import com.lydzje.corruptioSack.graphics.Sprite;
import com.lydzje.corruptioSack.levels.Level;
import com.lydzje.corruptioSack.maths.Vector2d;

public class IceProjectile extends Projectile {

	public IceProjectile(Vector2d origin, Direction dir) {
		super(origin, dir);
		sprite = Sprite.iceProjectile;
		this.origin.y -= sprite.getHeight() / 2;
		position.y -= sprite.getHeight() / 2;
		center = new Vector2d(position.x + sprite.getWidth() / 2, position.y + sprite.getHeight() / 2);
		speed = 2.5;
		range = 200;
		damage = 10;

	}

	public void update() {
		super.update();

		if (!Level.players.isEmpty()) {
			int xp = (int) Level.players.get(0).getPosition().x + Level.players.get(0).getMobX();

			if (center.isBetween(xp, xp + Level.players.get(0).getMobWidth())) {
				remove();
				if (!Level.players.get(0).getAnim().isPlayingAnim()) {
					Level.players.get(0).setFrozen(true);
					Level.players.get(0).setHurt(true);
					Level.players.get(0).setDamageTaken(damage);
				}
			}
		}

		if (toRemove) {
			Level.pEmmiters.add(new ParticleEmmiter(position, 80, 100, 0xffD2EAEB));
		}

	}

}
