package com.lydzje.corruptioSack.levels;

import java.util.ArrayList;
import java.util.List;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.entities.mobs.Ice;
import com.lydzje.corruptioSack.entities.mobs.Mob;
import com.lydzje.corruptioSack.entities.mobs.Player;
import com.lydzje.corruptioSack.entities.mobs.Shield;
import com.lydzje.corruptioSack.entities.mobs.Thunder;
import com.lydzje.corruptioSack.entities.mobs.particles.Particle;
import com.lydzje.corruptioSack.entities.mobs.particles.ParticleEmmiter;
import com.lydzje.corruptioSack.entities.projectiles.Projectile;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.Sprite;
import com.lydzje.corruptioSack.maths.Vector2d;
import com.lydzje.corruptioSack.utils.Camera;

public class Level {

	public static List<Level> levels = new ArrayList<Level>();

	protected List<Tile> tiles = new ArrayList<Tile>();
	public static List<Mob> mobs = new ArrayList<Mob>();
	public static List<Player> players = new ArrayList<Player>();
	public static List<Projectile> projectiles = new ArrayList<Projectile>();
	public static List<ParticleEmmiter> pEmmiters = new ArrayList<ParticleEmmiter>();
	public static List<Particle> particles = new ArrayList<Particle>();

	public static int shieldsKilled, icesKilled, thundersKilled;

	public static int terrainLevel;
	public int terrainLevel2;

	protected boolean isActive = false;

	protected Player player;
	protected Camera camera;

	protected Level(Player player, Camera camera) {
		this.player = player;
		this.camera = camera;
		if (players.size() == 0) players.add(player);
		levels.add(this);
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static int getTerrainLevel() {
		return terrainLevel;
	}

	public void clear() {
		tiles.clear();
		mobs.clear();
		players.clear();
		projectiles.clear();
		pEmmiters.clear();
		particles.clear();
	}

	public void update() {

		if (levels.get(0).isActive) terrainLevel = levels.get(0).terrainLevel2;
		else if (levels.get(1).isActive) terrainLevel = levels.get(1).terrainLevel2;

		for (int i = 0; i < players.size(); i++)
			player.update();

		camera.update();
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).isDead()) {

				Camera.shakeCamera(30);
				Game.setGameSpeed(0.5, 60);

				Mob mob = mobs.get(i);
				if (mob instanceof Ice) {
					icesKilled++;
					new ParticleEmmiter(mob.getPosition().add(mob.getSprite().getWidth() / 2.0, 0), 90, 150,
							0xff49B7C7);

				}
				if (mob instanceof Shield) {
					shieldsKilled++;
					new ParticleEmmiter(mob.getPosition().add(mob.getSprite().getWidth() / 2.0, 0), 90, 200,
							0xff440006);
				}
				if (mob instanceof Thunder) {
					thundersKilled++;
					new ParticleEmmiter(mob.getPosition().add(mob.getSprite().getWidth() / 2.0, 0), 90, 150,
							0xffAFAFAF);
				}

				mobs.remove(i);
				i--;
			}
			else mobs.get(i).update();
		}

		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) {
				projectiles.remove(i);
				i--;
			}
			else projectiles.get(i).update();
		}

		for (int i = 0; i < pEmmiters.size(); i++) {
			if (pEmmiters.get(i).isRemoved()) {
				pEmmiters.remove(i);
				i--;
			}
		}

		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) {
				particles.remove(i);
				i--;
			}
			else particles.get(i).update();
		}

	}

	public void render(Screen screen) {
		screen.renderSprite(new Vector2d(0, 0), Sprite.sky, false);
		for (Tile tile : tiles)
			if (camera.constains(tile)) tile.render(screen);

		for (int i = 0; i < mobs.size(); i++)
			if (Camera.contains(mobs.get(i).getPosition().x, mobs.get(i).getPosition().x
					+ mobs.get(i).getSprite().getWidth())) mobs.get(i).render(screen);

		for (int i = 0; i < players.size(); i++)
			player.render(screen);

		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).render(screen);

		for (int i = 0; i < particles.size(); i++)
			particles.get(i).render(screen);

	}
}
