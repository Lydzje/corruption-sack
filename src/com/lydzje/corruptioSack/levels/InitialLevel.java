package com.lydzje.corruptioSack.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.entities.mobs.Ice;
import com.lydzje.corruptioSack.entities.mobs.Mob;
import com.lydzje.corruptioSack.entities.mobs.Player;
import com.lydzje.corruptioSack.entities.mobs.Player.Forms;
import com.lydzje.corruptioSack.entities.mobs.Shield;
import com.lydzje.corruptioSack.entities.mobs.Thunder;
import com.lydzje.corruptioSack.gameplay.Message;
import com.lydzje.corruptioSack.graphics.Sprite;
import com.lydzje.corruptioSack.maths.Vector2d;
import com.lydzje.corruptioSack.utils.Camera;

public class InitialLevel extends Level {

	private BufferedImage arrowL, arrowU, background, movementControls, attackControl, seeControlsTut,
			hpTut1, hpTut2, corrupTut1, corrupTut2, punchIceTut, iceAvatar, iceFormUnlocked, transformTut,
			shieldFormUnlocked, shieldAvatar, thunderFormUnlocked, thunderAvatar;

	private int state = 0;

	public InitialLevel(Player player, Camera camera) {
		super(player, camera);
		terrainLevel2 = Game.getHEIGHT() - 32;
		terrainLevel = terrainLevel2;
		player.setPosition(new Vector2d(0, terrainLevel2 - 64));
		loadStrings();
		loadImages();
		drawMap();
		initMobs();
	}

	private void loadStrings() {
	}

	private void loadImages() {
		String path = "";

		try {
			path = "/textures/tutorials/arrowL.png";
			System.out.print("Loading " + path);
			arrowL = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/arrowU.png";
			System.out.print("Loading " + path);
			arrowU = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/background.png";
			System.out.print("Loading " + path);
			background = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/movementControlBlack.png";
			System.out.print("Loading " + path);
			movementControls = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/attackControlBlack.png";
			System.out.print("Loading " + path);
			attackControl = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/seeControlsTut.png";
			System.out.print("Loading " + path);
			seeControlsTut = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/hpTut1.png";
			System.out.print("Loading " + path);
			hpTut1 = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/hpTut2.png";
			System.out.print("Loading " + path);
			hpTut2 = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/corrupTut1.png";
			System.out.print("Loading " + path);
			corrupTut1 = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/corrupTut2.png";
			System.out.print("Loading " + path);
			corrupTut2 = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/punchIceTut.png";
			System.out.print("Loading " + path);
			punchIceTut = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/iceAvatar.png";
			System.out.print("Loading " + path);
			iceAvatar = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/iceFormUnlocked.png";
			System.out.print("Loading " + path);
			iceFormUnlocked = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/transformTut.png";
			System.out.print("Loading " + path);
			transformTut = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/shieldFormUnlocked.png";
			System.out.print("Loading " + path);
			shieldFormUnlocked = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/shieldAvatar.png";
			System.out.print("Loading " + path);
			shieldAvatar = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/thunderFormUnlocked.png";
			System.out.print("Loading " + path);
			thunderFormUnlocked = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/thunderAvatar.png";
			System.out.print("Loading " + path);
			thunderAvatar = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

		} catch (IOException e) {
			System.err.println("failed!");
			e.printStackTrace();
		}
	}

	private void drawMap() {
		int lastP = 0;
		for (int i = 1; i < 20; i++) {
			int xx = Game.random.nextInt(300);
			tiles.add(new Tile(new Vector2d(lastP + xx + i * Sprite.tree.getWidth(), Game.getHEIGHT()
					- 34
					- Sprite.tree.getHeight()), Sprite.tree));

			lastP = lastP + xx + i * Sprite.tree.getWidth();
		}

		for (int i = 0; i < 100; i++) {
			tiles.add(new Tile(new Vector2d(i * Sprite.grassT.getWidth(), Game.getHEIGHT()
					- Sprite.grassT.getHeight()),
					Sprite.grassT));
		}
	}

	private void initMobs() {
		for (int i = 0; i < 1; i++) {
			// mobs.add(new Ice(new Vector2d(500 * i, 160)));
			// mobs.add(new Thunder(new Vector2d(400, 160)));
			// mobs.add(new Shield(new Vector2d(300, terrainLevel - 63)));
		}
	}

	private int time = 0;
	private boolean done = false;
	private Ice tutorialMob;

	private void iceMessages() {
		new Message(new Vector2d(350, 0), background, 20, 1);
		new Message(new Vector2d(400, 10), iceAvatar, 20, 2);
		new Message(new Vector2d(355, 20), "ICE FORM", new Font("Segoe UI Light", Font.BOLD,
				8 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(355, 40), "Attack: ", new Font("Segoe UI Light", Font.BOLD,
				5 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(374, 40), "A not too powerful ice projectile", new Font("Segoe UI Light",
				Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);

		new Message(new Vector2d(355, 46), "that slows enemies for a few seconds.", new Font(
				"Segoe UI Light", Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);

		new Message(new Vector2d(355, 56), "Speed: ", new Font("Segoe UI Light", Font.BOLD,
				5 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(374, 56), "108 u/s", new Font("Segoe UI Light",
				Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(355, 62), "(CAT FORM speed is 150 u/s).", new Font(
				"Segoe UI Light", Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);
	}

	private void shieldMessages() {
		new Message(new Vector2d(350, 0), background, 20, 1);
		new Message(new Vector2d(410, 10), shieldAvatar, 20, 1);
		new Message(new Vector2d(355, 20), "SHIELD FORM", new Font("Segoe UI Light", Font.BOLD,
				8 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(355, 40), "Attack: ", new Font("Segoe UI Light", Font.BOLD,
				5 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(374, 40), "A shield bash that deals more ", new Font(
				"Segoe UI Light",
				Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(355, 46), "damage than the CAT FORM's punch.", new Font(
				"Segoe UI Light", Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);

		new Message(new Vector2d(355, 52), "But this attack is slower.", new Font(
				"Segoe UI Light", Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);

		new Message(new Vector2d(355, 62), "Defense: ", new Font("Segoe UI Light", Font.BOLD,
				5 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(378, 62), "Damage received is reduced", new Font("Segoe UI Light",
				Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(355, 68), "by 50%.", new Font("Segoe UI Light",
				Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);

		new Message(new Vector2d(355, 78), "Speed: ", new Font("Segoe UI Light", Font.BOLD,
				5 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(374, 78), "60 u/s", new Font("Segoe UI Light",
				Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(355, 84), "(CAT FORM speed is 150 u/s).", new Font(
				"Segoe UI Light", Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);
	}

	private void thunderMessages() {
		new Message(new Vector2d(350, 0), background, 20, 1);
		new Message(new Vector2d(415, 10), thunderAvatar, 20, 1);
		new Message(new Vector2d(355, 20), "THUNDER FORM", new Font("Segoe UI Light", Font.BOLD,
				7 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(355, 40), "Attack: ", new Font("Segoe UI Light", Font.BOLD,
				5 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(374, 40), "A devastating lightning that ", new Font("Segoe UI Light",
				Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);

		new Message(new Vector2d(355, 46), "deals a lot of damage.", new Font(
				"Segoe UI Light", Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);

		new Message(new Vector2d(355, 56), "Speed: ", new Font("Segoe UI Light", Font.BOLD,
				5 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(374, 56), "120 u/s", new Font("Segoe UI Light",
				Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);
		new Message(new Vector2d(355, 62), "(CAT FORM speed is 150 u/s).", new Font(
				"Segoe UI Light", Font.ITALIC, 5 * Game.getScale()), Color.WHITE, 20);
	}

	private void state0() {
		if (state == 0) {
			if (!done) {
				time = 0;
				done = true;
			}
			time++;
			if (time == 120) {
				new Message(new Vector2d(145, 80), movementControls, 4, 2);
				player.unlockMovement(true);
			}
			/**
			 * CÓMO CALCULAR LAS COORDENADAS SIGUIENTES
			 * 
			 * La separación que tienen estos dos sprites en el editor de imágenes será la misma aquí, pero
			 * tendrá que ser escalada en función del scale que se pasa como parámetro y el scale del juego.
			 * Por ejemplo, en esta situación en el editor nos encontramos con que la diferencia de altura
			 * entre ambos sprites es de 18. Como el scale del juego es 3, el resultado final será 3 veces
			 * mayor PERO como el scale pasado como parámetro, que es el scale de la imagen, es 2 nos
			 * quedaremos con una diferencia de 18/3*2 = 12 a usar para indicar la posición.
			 */
			if (time == 600) {
				new Message(new Vector2d(162, 92), attackControl, 4, 2);
				player.unlockAttacks(true);
			}
			if (time == 1100) new Message(new Vector2d(100, 93), seeControlsTut, 4, 2);
			if (time == 1400) {
				Mob.setBounds(0, 3000);
				done = false;
				state = 1;
				time = 0;
			}
		}
	}

	private void state1() {
		if (state == 1) {

			if (time <= 10) time++;

			if (time == 10) {
				tutorialMob = new Ice(new Vector2d(1900, 160));
				mobs.add(tutorialMob);
			}

			if (player.isFrozen() && !done) {
				done = true;
				new Message(new Vector2d(100, 93), punchIceTut, 6, 2);
			}

			if (time > 10) {
				if (tutorialMob != null) {
					if (tutorialMob.isDead()) {
						tutorialMob = null;
					}
				} else {
					time++;

					if (time == 70) {
						player.unlockMovement(false);
						player.unlockAttacks(false);
						new Message(new Vector2d(165, 39), arrowL, 6, 1);
						new Message(new Vector2d(165, 60), hpTut1, 6, 2);
					}
					if (time == 670) new Message(new Vector2d(165, 60), hpTut2, 6, 2);

					if (time == 1300) {
						new Message(new Vector2d(110, 55), arrowU, 6, 1);
						new Message(new Vector2d(150, 60), corrupTut1, 6, 2);
					}
					if (time == 1900) new Message(new Vector2d(150, 60), corrupTut2, 6, 2);

					if (time == 2320) {
						player.unlockMovement(true);
						player.unlockAttacks(true);
						done = false;
						time = 0;
						state = 2;
					}

				}
			}

		}
	}

	private void state2() {
		if (state == 2) {
			if (!done) {
				Mob.setBounds(0, 5000);
				mobs.add(new Ice(new Vector2d(player.getPosition().x + 500, 160)));
				mobs.add(new Ice(new Vector2d(player.getPosition().x + 700, 160)));
				mobs.add(new Ice(new Vector2d(player.getPosition().x + 900, 160)));
				done = true;
			}

			if (time == 0 && player.isThisFormUnlocked(Forms.ICE)) {
				time++;
				new Message(new Vector2d(100, 93), iceFormUnlocked, 4, 2);
				iceMessages();
			}
			if (time > 0 && time <= 800) time++;
			if (time == 500) {
				new Message(new Vector2d(50, 75), arrowU, 6, 1);
				new Message(new Vector2d(100, 92), transformTut, 6, 2);
			}

			if (time == 800) {
				Mob.setBounds(0, 9000);
				mobs.add(new Shield(new Vector2d(player.getPosition().x + 500, terrainLevel - 63)));
			}

			if (shieldsKilled == 1 && time == 801) {
				time++;
				mobs.add(new Shield(new Vector2d(player.getPosition().x + 500, terrainLevel - 63)));
				mobs.add(new Shield(new Vector2d(player.getPosition().x + 600, terrainLevel - 63)));
			}

			if (shieldsKilled == 3 && time == 802) {
				time++;
				mobs.add(new Shield(new Vector2d(player.getPosition().x + 490, terrainLevel - 63)));
				mobs.add(new Ice(new Vector2d(player.getPosition().x + 480, 160)));
			}

			if (player.isThisFormUnlocked(Forms.SHIELD) && time == 803) {
				time++;
				new Message(new Vector2d(100, 93), shieldFormUnlocked, 4, 2);
				shieldMessages();
			}

			if (time > 803 && time < 1164) time++;
			if (time == 1164) {
				time++;
				mobs.add(new Thunder(new Vector2d(player.getPosition().x + 500, 160)));
			}

			if (time == 1165 && thundersKilled == 1) {
				time++;
				mobs.add(new Thunder(new Vector2d(player.getPosition().x + 500, 160)));
				mobs.add(new Ice(new Vector2d(player.getPosition().x + 500, 160)));
			}
			if (time == 1166 && thundersKilled == 2 && icesKilled == 6) {
				time++;
				mobs.add(new Thunder(new Vector2d(player.getPosition().x + 520, 160)));
				mobs.add(new Shield(new Vector2d(player.getPosition().x + 500, terrainLevel - 63)));
				mobs.add(new Shield(new Vector2d(player.getPosition().x + 480, terrainLevel - 63)));
			}
			if (time == 1167 && thundersKilled == 3 && shieldsKilled == 6) {
				time++;
				mobs.add(new Thunder(new Vector2d(player.getPosition().x + 480, 160)));
				mobs.add(new Thunder(new Vector2d(player.getPosition().x + 500, 160)));
				mobs.add(new Thunder(new Vector2d(player.getPosition().x + 520, 160)));
			}
			if (time == 1168 && player.isThisFormUnlocked(Forms.THUNDER)) {
				time++;
				new Message(new Vector2d(100, 93), thunderFormUnlocked, 4, 2);
				thunderMessages();
				done = false;
				time = 0;
				state = 3;
			}
		}
	}

	public void state3() {
		if (state == 3) {
			if (time == 0) {
				time++;
				mobs.add(new Ice(new Vector2d(player.getPosition().x + 480, 160)));
				mobs.add(new Ice(new Vector2d(player.getPosition().x + 490, 160)));
				mobs.add(new Ice(new Vector2d(player.getPosition().x + 500, 160)));
				mobs.add(new Ice(new Vector2d(player.getPosition().x + 510, 160)));
			}
			if (time == 1 && icesKilled == 10) {
				time++;
				mobs.add(new Shield(new Vector2d(player.getPosition().x + 480, terrainLevel - 63)));
				mobs.add(new Shield(new Vector2d(player.getPosition().x + 500, terrainLevel - 63)));
				mobs.add(new Ice(new Vector2d(player.getPosition().x + 480, 160)));
				mobs.add(new Ice(new Vector2d(player.getPosition().x + 500, 160)));
				mobs.add(new Thunder(new Vector2d(player.getPosition().x + 490, 160)));

			}
		}
	}

	public void update() {
		super.update();
		state0();
		state1();
		state2();
		state3();
	}
}
