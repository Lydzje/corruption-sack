package com.lydzje.corruptioSack.maths;

public class Vector3i {
	public int x, y, z;

	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3i(Vector3i vector) {
		x = vector.x;
		y = vector.y;
		z = vector.z;
	}
}
