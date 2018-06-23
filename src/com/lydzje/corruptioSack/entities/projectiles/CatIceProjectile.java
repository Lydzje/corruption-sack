package com.lydzje.corruptioSack.entities.projectiles;

import com.lydzje.corruptioSack.entities.mobs.Mob;
import com.lydzje.corruptioSack.entities.mobs.Mob.Direction;
import com.lydzje.corruptioSack.entities.mobs.particles.ParticleEmmiter;
import com.lydzje.corruptioSack.graphics.Sprite;
import com.lydzje.corruptioSack.levels.Level;
import com.lydzje.corruptioSack.maths.Vector2d;

public class CatIceProjectile extends Projectile {

	public CatIceProjectile(Vector2d origin, Direction dir) {
		super(origin, dir);
		sprite = Sprite.iceProjectileF;
		this.origin.y -= sprite.getHeight() / 2;
		position.y -= sprite.getHeight() / 2;
		center = new Vector2d(position.x + sprite.getWidth() / 2, position.y + sprite.getHeight() / 2);
		speed = 2.5;
		range = 200;
		damage = 10;

	}

	public void update() {
		super.update();

		for (Mob mob : Level.mobs) {
			int xp = (int) mob.getPosition().x + mob.getMobX();

			if (center.isBetween(xp, xp + mob.getMobWidth())) {
				remove();
				mob.setFrozen(true);
				mob.setHurt(true);
				mob.setDamageTaken(damage);
			}
		}

		if (toRemove) {
			Level.pEmmiters.add(new ParticleEmmiter(position, 80, 100, 0xffA4DBDB));
		}

	}

}
