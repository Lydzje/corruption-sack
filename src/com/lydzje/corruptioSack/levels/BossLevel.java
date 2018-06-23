package com.lydzje.corruptioSack.levels;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.entities.mobs.Boss;
import com.lydzje.corruptioSack.entities.mobs.Player;
import com.lydzje.corruptioSack.graphics.AnimatedSprite;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.SpriteSheet;
import com.lydzje.corruptioSack.maths.Vector2d;
import com.lydzje.corruptioSack.utils.Camera;

public class BossLevel extends Level {

	private AnimatedSprite anim;
	private Tile background;

	public BossLevel(Player player, Camera camera) {
		super(player, camera);
		anim = new AnimatedSprite(455, 256, 3, SpriteSheet.bossBackground);

		terrainLevel2 = Game.getHEIGHT() - 24;
		terrainLevel = terrainLevel2;
		player.setPosition(new Vector2d(0, terrainLevel2 - 64));
		drawMap();
		// initMobs();
	}

	private void drawMap() {
		background = new Tile(new Vector2d(0, 0), anim.getSprite());
		tiles.add(background);
	}

	public static void initMobs() {
		mobs.add(new Boss(new Vector2d(300, getTerrainLevel() - 60)));
	}

	public void update() {
		anim.update();
		background.setSprite(anim.getSprite());
		super.update();
	}

	public void render(Screen screen) {
		screen.renderSprite(new Vector2d(0, 0), background.getSprite(), false);

		for (int i = 0; i < mobs.size(); i++)
			mobs.get(i).render(screen);

		player.render(screen);

		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).render(screen);

		for (int i = 0; i < particles.size(); i++)
			particles.get(i).render(screen);
	}

}
