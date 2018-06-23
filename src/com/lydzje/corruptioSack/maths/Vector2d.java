package com.lydzje.corruptioSack.maths;

public class Vector2d {

	public double x, y;

	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2d(Vector2d vector) {
		x = vector.x;
		y = vector.y;
	}

	public Vector2d add(double x, double y) {
		return new Vector2d(this.x + x, this.y + y);
	}

	public double getDistanceTo(Vector2d vector) {
		return Math.sqrt((x - vector.x) * (x - vector.x) + (y - vector.y) * (y - vector.y));
	}

	public boolean isBetween(double x1, double x2) {
		return x > x1 && x < x2;
	}

}
