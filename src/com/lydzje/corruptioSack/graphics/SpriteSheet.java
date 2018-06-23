package com.lydzje.corruptioSack.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private int width, height;
	private int[] pixels;
	public Sprite[] sprites;

	// =============================
	// ==============ETC============

	

	public static SpriteSheet test = new SpriteSheet("/test.png");

	public static SpriteSheet startMenuSheet = new SpriteSheet("/textures/menu/startMenu.png");
	public static SpriteSheet startMenu = new SpriteSheet(startMenuSheet, 0, 0, 1, 8, 455, 256);

	// =============================
	// ==============UI=============
	public static SpriteSheet playB = new SpriteSheet("/textures/menu/play.png");
	public static SpriteSheet quitB = new SpriteSheet("/textures/menu/quit.png");

	public static SpriteSheet corruptionSack = new SpriteSheet("/textures/menu/corruptionSack.png");

	public static SpriteSheet transButtonsSheet = new SpriteSheet("/textures/ui/transButtons2.png");

	public static SpriteSheet healthBar = new SpriteSheet("/textures/ui/healthBar.png");

	// =============================
	// ==========PLAYER=============
	public static SpriteSheet playerAnimSheet = new SpriteSheet("/textures/mobs/player/player.png");
	public static SpriteSheet player_right = new SpriteSheet(playerAnimSheet, 0, 0, 1, 8, 64, 64);
	public static SpriteSheet player_left = new SpriteSheet(playerAnimSheet, 1, 0, 1, 8, 64, 64);

	public static SpriteSheet playerWalkSheet = new SpriteSheet("/textures/mobs/player/walking.png");
	public static SpriteSheet playerW_right = new SpriteSheet(playerWalkSheet, 0, 0, 1, 4, 64, 64);
	public static SpriteSheet playerW_left = new SpriteSheet(playerWalkSheet, 1, 0, 1, 4, 64, 64);

	public static SpriteSheet playerPunchSheet = new SpriteSheet("/textures/mobs/player/punch.png");
	public static SpriteSheet player_punchR = new SpriteSheet(playerPunchSheet, 0, 0, 1, 3, 64, 64);
	public static SpriteSheet player_punchL = new SpriteSheet(playerPunchSheet, 1, 0, 1, 3, 64, 64);

	/*
	 * public static SpriteSheet playerDeathSheet = new SpriteSheet("/textures/mobs/player/death.png");
	 * public static SpriteSheet player_deathR = new SpriteSheet(playerDeathSheet, 0, 0, 1, 8, 64, 64);
	 * public static SpriteSheet player_deathL = new SpriteSheet(playerDeathSheet, 1, 0, 1, 8, 64, 64);
	 */
	// =============================
	// ============ICE==============

	public static SpriteSheet iceAnimSheet = new SpriteSheet("/textures/mobs/ice/ice.png");
	public static SpriteSheet ice_right = new SpriteSheet(iceAnimSheet, 0, 0, 1, 4, 32, 32);
	public static SpriteSheet ice_left = new SpriteSheet(iceAnimSheet, 1, 0, 1, 4, 32, 32);
	public static SpriteSheet iceW_right = new SpriteSheet(iceAnimSheet, 2, 0, 1, 4, 32, 32);
	public static SpriteSheet iceW_left = new SpriteSheet(iceAnimSheet, 3, 0, 1, 4, 32, 32);

	public static SpriteSheet iceProjectile = new SpriteSheet("/textures/mobs/ice/iceProjectile.png");

	public static SpriteSheet iceAnimSheetF = new SpriteSheet("/textures/mobs/player/transformations/ice.png");
	public static SpriteSheet ice_rightF = new SpriteSheet(iceAnimSheetF, 0, 0, 1, 4, 32, 32);
	public static SpriteSheet ice_leftF = new SpriteSheet(iceAnimSheetF, 1, 0, 1, 4, 32, 32);
	public static SpriteSheet iceW_rightF = new SpriteSheet(iceAnimSheetF, 2, 0, 1, 4, 32, 32);
	public static SpriteSheet iceW_leftF = new SpriteSheet(iceAnimSheetF, 3, 0, 1, 4, 32, 32);

	public static SpriteSheet iceProjectileF = new SpriteSheet(
			"/textures/mobs/player/transformations/iceProjectile.png");

	// =============================
	// ===========THUNDER===========

	public static SpriteSheet thunderAnimSheet = new SpriteSheet("/textures/mobs/thunder/thunder2.png");
	public static SpriteSheet thunder_right = new SpriteSheet(thunderAnimSheet, 0, 0, 1, 4, 64, 64);
	public static SpriteSheet thunder_left = new SpriteSheet(thunderAnimSheet, 1, 0, 1, 4, 64, 64);
	public static SpriteSheet thunderW_right = new SpriteSheet(thunderAnimSheet, 2, 0, 1, 4, 64, 64);
	public static SpriteSheet thunderW_left = new SpriteSheet(thunderAnimSheet, 3, 0, 1, 4, 64, 64);

	public static SpriteSheet thunderBoltAnimSheet = new SpriteSheet("/textures/mobs/thunder/thunderbolt.png");
	public static SpriteSheet thunderBolt = new SpriteSheet(thunderBoltAnimSheet, 0, 0, 1, 16, 64, 224);

	public static SpriteSheet thunderAnimSheetF = new SpriteSheet(
			"/textures/mobs/player/transformations/thunder.png");
	public static SpriteSheet thunder_rightF = new SpriteSheet(thunderAnimSheetF, 0, 0, 1, 4, 64, 64);
	public static SpriteSheet thunder_leftF = new SpriteSheet(thunderAnimSheetF, 1, 0, 1, 4, 64, 64);
	public static SpriteSheet thunderW_rightF = new SpriteSheet(thunderAnimSheetF, 2, 0, 1, 4, 64, 64);
	public static SpriteSheet thunderW_leftF = new SpriteSheet(thunderAnimSheetF, 3, 0, 1, 4, 64, 64);

	// =============================
	// ===========SHIELD============

	public static SpriteSheet shieldAnimSheet = new SpriteSheet("/textures/mobs/shield/shield.png");
	public static SpriteSheet shield_right = new SpriteSheet(shieldAnimSheet, 0, 0, 1, 4, 64, 64);
	public static SpriteSheet shield_left = new SpriteSheet(shieldAnimSheet, 1, 0, 1, 4, 64, 64);
	public static SpriteSheet shieldW_right = new SpriteSheet(shieldAnimSheet, 2, 0, 1, 4, 64, 64);
	public static SpriteSheet shieldW_left = new SpriteSheet(shieldAnimSheet, 3, 0, 1, 4, 64, 64);

	public static SpriteSheet shieldPushSheet = new SpriteSheet("/textures/mobs/shield/push.png");
	public static SpriteSheet shield_pushR = new SpriteSheet(shieldPushSheet, 0, 0, 1, 3, 64, 64);
	public static SpriteSheet shield_pushL = new SpriteSheet(shieldPushSheet, 1, 0, 1, 3, 64, 64);

	public static SpriteSheet shieldAnimSheetF = new SpriteSheet(
			"/textures/mobs/player/transformations/shield.png");
	public static SpriteSheet shield_rightF = new SpriteSheet(shieldAnimSheetF, 0, 0, 1, 4, 64, 64);
	public static SpriteSheet shield_leftF = new SpriteSheet(shieldAnimSheetF, 1, 0, 1, 4, 64, 64);
	public static SpriteSheet shieldW_rightF = new SpriteSheet(shieldAnimSheetF, 2, 0, 1, 4, 64, 64);
	public static SpriteSheet shieldW_leftF = new SpriteSheet(shieldAnimSheetF, 3, 0, 1, 4, 64, 64);

	public static SpriteSheet shieldPushSheetF = new SpriteSheet(
			"/textures/mobs/player/transformations/push.png");
	public static SpriteSheet shield_pushRF = new SpriteSheet(shieldPushSheetF, 0, 0, 1, 3, 64, 64);
	public static SpriteSheet shield_pushLF = new SpriteSheet(shieldPushSheetF, 1, 0, 1, 3, 64, 64);

	// =============================
	// ==========BOSS=============
	public static SpriteSheet bossAnimSheet = new SpriteSheet("/textures/mobs/boss/boss.png");
	public static SpriteSheet boss_right = new SpriteSheet(bossAnimSheet, 0, 0, 1, 4, 64, 64);
	public static SpriteSheet boss_left = new SpriteSheet(bossAnimSheet, 1, 0, 1, 4, 64, 64);

	// =============================
	// ==========TERRAINS=============
	public static SpriteSheet grassTerrSheet = new SpriteSheet("/textures/terrains/grass.png");
	public static SpriteSheet bossBackgroundSheet = new SpriteSheet("/textures/boss/background.png");
	public static SpriteSheet bossBackground = new SpriteSheet(bossBackgroundSheet, 0, 0, 1, 3, 455, 256);

	// =============================
	// ==========SCENARIO=============
	public static SpriteSheet sky = new SpriteSheet("/textures/sky/sky.png");
	public static SpriteSheet tree = new SpriteSheet("/textures/objects/tree.png");

	public SpriteSheet(String path) {
		loadImage(path);
	}

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteW, int spriteH) {
		int xx = x * spriteW;
		int yy = y * spriteH;
		int w = width * spriteW;
		int h = height * spriteH;

		this.width = w;
		this.height = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yP = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xP = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xP + yP * sheet.width];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteW * spriteH];
				for (int y0 = 0; y0 < spriteH; y0++) {
					for (int x0 = 0; x0 < spriteW; x0++) {
						spritePixels[x0 + y0 * spriteW] = pixels[(x0 + xa * spriteW) + (y0 + ya * spriteH)
								* this.width];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteW, spriteH);
				sprites[frame++] = sprite;
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getPixels() {
		return pixels;
	}

	private void loadImage(String path) {
		try {
			System.out.print("Loading " + path);
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			System.out.println(" succeded!");

			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			pixels = image.getRGB(0, 0, width, height, pixels, 0, width);

		} catch (IOException e) {
			System.err.println(" failed!");
			e.printStackTrace();
		}
	}

}
