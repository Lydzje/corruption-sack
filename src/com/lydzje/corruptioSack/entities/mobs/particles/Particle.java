package com.lydzje.corruptioSack.entities.mobs.particles;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.levels.Level;
import com.lydzje.corruptioSack.maths.Vector2d;

public class Particle {

	private int color;

	private int life;
	private int timer = 0;

	private Vector2d position;

	private Vector2d speed;

	private boolean toRemove = false;

	public static enum Type {
		DEFAULT, SPARKY, PLAYER_DEATH, HEALING
	};

	private Type type = Type.DEFAULT;

	public Particle(Vector2d position, int life, int color) {
		this.position = position;
		this.life = life + Game.random.nextInt(20) - 10;
		this.color = color;

		speed = new Vector2d(Game.random.nextGaussian() * 7, (Game.random.nextGaussian() + 1.5) * 7);
	}

	public void setType(Type type) {
		this.type = type;
		if (type == Type.HEALING) speed = new Vector2d(Game.random.nextGaussian() * 0.2,
				(Game.random.nextGaussian() + 2) * 0.4);
	}

	public boolean isRemoved() {
		return toRemove;
	}

	private void remove() {
		toRemove = true;
	}

	private void move() {
		if (type == Type.DEFAULT) {
			speed.x *= 0.75;
			if (speed.y > 0.1) {
				speed.y *= 0.75;
				position.y -= speed.y * 0.5;
			}
			else {
				if (speed.y > 0) speed.y *= -1;
				speed.y *= 1.15;
				position.y -= speed.y * 0.5;
			}

			position.x += speed.x * 0.5;
		}

		else if (type == Type.SPARKY) {
			speed.x *= 0.9;
			speed.y += 0.9;

			position.x += speed.x;
			position.y += speed.y;
		}

		else if (type == Type.PLAYER_DEATH) {
			speed.x *= 0.85;
			if (speed.y > 0.1) {
				speed.y *= 0.85;
				position.y -= speed.y * 0.7;
			}
			else {
				if (speed.y > 0) speed.y *= -1;
				speed.y *= 1.01;
				position.y -= speed.y * 0.7;
			}

			position.x += speed.x * 0.5;
		}

		else if (type == Type.HEALING) {

			speed.y *= 1.000005;
			position.y -= speed.y;
			position.x += speed.x;

		}

	}

	public void update() {
		timer++;

		timer = timer > 1000 ? 0 : timer;
		if (timer > life) remove();
		if (position.y < Level.terrainLevel + Game.random.nextInt(5)) move();
		if (position.y > Level.terrainLevel) position.y = Level.terrainLevel;

	}

	public void render(Screen screen) {
		screen.drawRect(position, 1, 1, color, true);
	}

}
