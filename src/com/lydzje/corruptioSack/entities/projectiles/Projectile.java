package com.lydzje.corruptioSack.entities.projectiles;

import com.lydzje.corruptioSack.entities.mobs.Mob.Direction;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.Sprite;
import com.lydzje.corruptioSack.maths.Vector2d;

public class Projectile {

	protected Sprite sprite;
	protected final Vector2d origin;
	protected Vector2d position;
	protected Vector2d center;
	protected double distance, speed, range;
	protected int damage;
	protected Direction dir;
	protected boolean toRemove = false;

	public Projectile(Vector2d origin, Direction dir) {
		this.origin = origin;
		position = new Vector2d(origin.x, origin.y);
		this.dir = dir;
	}

	public Vector2d getPosition() {
		return position;
	}

	public void setPosition(Vector2d position) {
		this.position = position;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void remove() {
		toRemove = true;
	}

	public boolean isRemoved() {
		return toRemove;
	}

	public void move() {
		position.x += dir == Direction.LEFT ? -speed : speed;
		center.x += dir == Direction.LEFT ? -speed : speed;
	}

	public void update() {
		distance = origin.getDistanceTo(position);
		if (distance >= range) remove();
		move();
	}

	public void render(Screen screen) {
		screen.renderSprite(position, sprite, true);
	}

}
