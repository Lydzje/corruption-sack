package com.lydzje.corruptioSack.maths;

public class Maths {

	private Maths() {
	}

	public static int clamp(int value, int min, int max) {
		if (value < min) return min;
		else if (value > max) return max;
		else return value;
	}

}
