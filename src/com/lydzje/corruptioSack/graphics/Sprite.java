package com.lydzje.corruptioSack.graphics;

import com.lydzje.corruptioSack.maths.Vector3i;

public class Sprite {

	private int width, height;
	private int[] pixels;
	private boolean toBright = false;
	private Vector3i bright = new Vector3i(0, 0, 0);

	public static Sprite test2 = new Sprite(0, 0, 64, 64, SpriteSheet.test);
	public static Sprite test = new Sprite(0, 1, 32, 32, SpriteSheet.test);

	public static Sprite playerTest = new Sprite(0, 0, 64, 64, SpriteSheet.playerAnimSheet);

	public static Sprite playB = new Sprite(0, 0, 57, 25, SpriteSheet.playB);
	public static Sprite quitB = new Sprite(0, 0, 42, 21, SpriteSheet.quitB);

	public static Sprite corruptionSack = new Sprite(0, 0, 235, 103, SpriteSheet.corruptionSack);

	public static Sprite healthBar = new Sprite(0, 0, 125, 10, SpriteSheet.healthBar);

	public static Sprite iceB = new Sprite(0, 0, 16, 16, SpriteSheet.transButtonsSheet);
	public static Sprite thunderB = new Sprite(0, 1, 16, 16, SpriteSheet.transButtonsSheet);
	public static Sprite shieldB = new Sprite(0, 2, 16, 16, SpriteSheet.transButtonsSheet);

	// =============================
	// ==========TERRAINS===========
	public static Sprite grassT = new Sprite(0, 0, 96, 48, SpriteSheet.grassTerrSheet);

	// =============================
	// ==========SCENARIO===========
	public static Sprite tree = new Sprite(0, 0, 64, 96, SpriteSheet.tree);
	public static Sprite sky = new Sprite(0, 0, 455, 256, SpriteSheet.sky);

	// =============================
	// ============ICE==============
	public static Sprite iceProjectile = new Sprite(0, 0, 16, 16, SpriteSheet.iceProjectile);
	public static Sprite iceProjectileF = new Sprite(0, 0, 16, 16, SpriteSheet.iceProjectileF);

	public Sprite(int x, int y, int width, int height, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		pixels = new int[this.width * this.height];
		loadSprite(x, y, width, height, sheet);
	}

	public Sprite(int[] pixels, int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isToBright() {
		return toBright;
	}

	public Vector3i getBright() {
		return bright;
	}

	public void setBright(Vector3i bright) {
		this.bright = bright;
	}

	public void setToBright(boolean toBright) {
		this.toBright = toBright;
	}

	public int[] getPixels() {
		return pixels;
	}

	private void loadSprite(int x, int y, int w, int h, SpriteSheet sheet) {
		for (int yy = 0; yy < h; yy++) {
			int aY = yy + y * h;
			for (int xx = 0; xx < w; xx++) {
				int aX = xx + x * w;
				pixels[xx + yy * w] = sheet.getPixels()[aX + aY * sheet.getWidth()];
			}
		}
	}
}
