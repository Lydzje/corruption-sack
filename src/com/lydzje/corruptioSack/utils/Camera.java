package com.lydzje.corruptioSack.utils;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.entities.mobs.Player;
import com.lydzje.corruptioSack.levels.Tile;
import com.lydzje.corruptioSack.maths.Vector2d;

public class Camera {
	private Player actor;
	private Keyboard input;
	private static boolean locked = false;
	private static boolean shake = false;
	private static Vector2d position = new Vector2d(0, 0);
	private double speed;

	public Camera(Player actor, Keyboard input) {
		this.input = input;
		this.actor = actor;
		speed = actor.getSpeed() * 0.72;
	}

	public static Vector2d getPosition() {
		return position;
	}

	public void setPosition(Vector2d position) {
		Camera.position = position;
	}

	public static void lockCamera(boolean state) {
		locked = state;
	}

	public static void shakeCamera(int time) {
		shake = true;
		position.y = 1;
		timer = 1;
		Camera.time = time;
	}

	public static boolean contains(double init, double end) {
		if (init > -position.x + Game.getWIDTH() || end < -position.x) return false;
		else return true;
	}

	public boolean constains(Tile tile) {
		int tx = (int) tile.getPosition().x;
		int tw = tx + tile.getSprite().getWidth();

		if (tx > -position.x + Game.getWIDTH() || tw < -position.x) return false;
		else return true;
	}

	public void move() {
		if (!actor.isMovementLocked()) {
			if (input.right) position.x -= speed;
			if (input.left) position.x += position.x >= 0 ? 0 : speed;
		}

	}

	private static int timer = 1;
	private static int time = 0;

	public void update() {
		timer++;
		if (timer == time) {
			shake = false;
			position.y = 0;
		}
		if (timer % 3 == 0 && shake) position.y *= -1;

		if (!locked) {
			move();

			if (actor.getPosition().x < (int) -position.x + 90) {
				position.x += position.x >= 0 ? 0 : 1;
			} else if (actor.getPosition().x > (int) -position.x + 120) {
				if (actor.getPosition().x > (int) -position.x + 300) speed = actor.getSpeed();
				position.x -= 1;
			}
			else speed = actor.getSpeed() * 0.72;
			position.x = (int) position.x;

		}
	}
}
