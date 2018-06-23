package com.lydzje.corruptioSack.graphics;

import com.lydzje.corruptioSack.entities.mobs.Mob;
import com.lydzje.corruptioSack.maths.Vector2d;
import com.lydzje.corruptioSack.utils.ImageUtils;

public class Screen {

	private int width;
	private int height;

	private int xOffset = 0;
	private int yOffset = 0;

	private int[] pixels;

	public Screen(int width, int height, int[] pixels) {
		this.width = width;
		this.height = height;

		this.pixels = pixels;

	}

	public void setOffSets(int xO, int yO) {
		this.xOffset = xO;
		this.yOffset = yO;
	}

	public void clean() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0xffCBB9A7;
	}

	public void drawRect(Vector2d position, int width, int height, int color, boolean fixed) {
		int xP = (int) position.x;
		int yP = (int) position.y;

		if (fixed) {
			xP += (int) xOffset;
			yP += (int) yOffset;
		}

		for (int y = 0; y < height; y++) {
			int aY = y + yP;
			for (int x = 0; x < width; x++) {
				int aX = xP + x;
				if (aX < 0 || aX > this.width || aY < 0 || aY > this.height) continue;
				pixels[aX + aY * this.width] = color;
			}
		}

	}

	public void renderSprite(Vector2d position, Sprite sprite, boolean fixed) {
		int xP = (int) position.x;
		int yP = (int) position.y;

		if (fixed) {
			xP += (int) xOffset;
			yP += (int) yOffset;
		}

		for (int y = 0; y < sprite.getHeight(); y++) {
			int aY = y + yP;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int aX = xP + x;
				if (aX < 0 || aX >= this.width || aY < 0 || aY >= this.height) continue;
				int color = sprite.getPixels()[x + y * sprite.getWidth()];
				if (color != 0x00ffffff) {

					if (sprite.isToBright()) {
						int r = sprite.getBright().x;
						int g = sprite.getBright().y;
						int b = sprite.getBright().z;
						color = ImageUtils.addColor(color, r, g, b);
					}
					pixels[aX + aY * this.width] = color;
				}
			}
		}
	}

	public void renderMob(Vector2d position, Mob mob, boolean hurt, boolean frozen) {
		int xP = (int) position.x;
		int yP = (int) position.y;

		xP += (int) xOffset;
		yP += (int) yOffset;

		for (int y = 0; y < mob.getSprite().getHeight(); y++) {
			int aY = y + yP;
			for (int x = 0; x < mob.getSprite().getWidth(); x++) {
				int aX = xP + x;
				if (aX < 0 || aX >= this.width || aY < 0 || aY >= this.height) continue;
				int color = mob.getSprite().getPixels()[x + y * mob.getSprite().getWidth()];
				if (color != 0x00ffffff && color != 0x00000000) {
					if (hurt) color = ImageUtils.addColor(color, 80, -15, -15);
					else if (frozen) color = ImageUtils.addColor(color, 20, 20, 80);
					pixels[aX + aY * this.width] = color;
				}
			}
		}
	}

}
