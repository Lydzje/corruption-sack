package com.lydzje.corruptioSack.utils;

import static com.lydzje.corruptioSack.maths.Maths.clamp;

public class ImageUtils {
	public static int addColor(int color, int r1, int g1, int b1) {

		int red = (color >> 16) & 0xFF;
		int green = (color >> 8) & 0xFF;
		int blue = color & 0xFF;

		red = clamp(red + r1, 0, 255);
		green = clamp(green + g1, 0, 255);
		blue = clamp(blue + b1, 0, 255);

		return ((red) << 16) | (green << 8) | (blue);

	}
}
