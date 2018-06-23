package com.lydzje.corruptioSack.entities.mobs.particles;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.entities.mobs.particles.Particle.Type;
import com.lydzje.corruptioSack.levels.Level;
import com.lydzje.corruptioSack.maths.Vector2d;

public class ParticleEmmiter {
	private boolean toRemove = false;

	public ParticleEmmiter(Vector2d position, int life, int amount, int color) {

		for (int i = 0; i < amount; i++) {
			Particle part = new Particle(new Vector2d(position.x + Game.random.nextInt(10) - 5,
					position.y + Game.random.nextInt(10) - 5), life, color);
			Level.particles.add(part);
		}
		toRemove = true;
	}

	public ParticleEmmiter(Vector2d position, Type type, int life, int amount, int color) {

		for (int i = 0; i < amount; i++) {
			Particle part = new Particle(new Vector2d(position.x + Game.random.nextInt(40) - 20,
					position.y + Game.random.nextInt(40) - 20), life, color);
			part.setType(type);
			Level.particles.add(part);
		}
		toRemove = true;
	}

	public boolean isRemoved() {
		return toRemove;
	}

}
