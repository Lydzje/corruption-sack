// © 2016 Lydzje Software

package com.lydzje.corruptioSack;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.lydzje.corruptioSack.entities.mobs.Player;
import com.lydzje.corruptioSack.gameplay.AttackSystem;
import com.lydzje.corruptioSack.gameplay.Message;
import com.lydzje.corruptioSack.gameplay.ai.AIManager;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.levels.BossLevel;
import com.lydzje.corruptioSack.levels.InitialLevel;
import com.lydzje.corruptioSack.levels.Level;
import com.lydzje.corruptioSack.maths.Vector2d;
import com.lydzje.corruptioSack.ui.DeathScreen;
import com.lydzje.corruptioSack.ui.StartMenu;
import com.lydzje.corruptioSack.ui.UIManager;
import com.lydzje.corruptioSack.ui.UIString;
import com.lydzje.corruptioSack.utils.Camera;
import com.lydzje.corruptioSack.utils.Keyboard;
import com.lydzje.corruptioSack.utils.LogoScreen;
import com.lydzje.corruptioSack.utils.Mouse;
import com.lydzje.corruptioSack.utils.Sound;

public class Game extends Canvas implements Runnable {

	/**
	 * TODO:
	 * - Implementar más patrones de movimiento para partículas, especialmente para los thunderbolt
	 * 
	 * - Aplicar un daño recibido doble al player si este está en la forma de "shield" y el golpe le impacta
	 * detrás (es decir, esta forma solo reducirá el daño recibido si los golpes impactan en el escudo)
	 * 
	 * - Aplicar rojo parpadeante al juego cuando el jugador tenga poca vida
	 * 
	 * - The landing, FF VIII
	 * - On Our Way, FF VII
	 */

	private static final long serialVersionUID = 1L;

	private static final int HEIGHT = 256;
	private static final int WIDHT = HEIGHT / 9 * 16;// 455
	private static final int SCALE = 3;

	private int fpsCap = 60;
	private static double gameSpeed = 1;

	public static Random random = new Random();

	private JFrame frame;

	private Thread thread;
	private boolean running = false;

	private Screen screen;

	private static Graphics graphicsTool;

	private static UIManager ui;

	private BufferedImage image = new BufferedImage(WIDHT, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Keyboard key;
	private Mouse mouse;

	private Camera camera;

	private Player player;

	public static int state = -2;

	private StartMenu startMenu;

	private static AttackSystem attackManager;
	private static AIManager aiManager;

	private Level initialLevel;
	private Level bossLevel;

	private static boolean gamePaused = false;

	private BufferedImage pressP, movementControls, attackControl;

	private LogoScreen logoScreen = new LogoScreen();
	private DeathScreen deathScreen = new DeathScreen();

	public Game() {
		setPreferredSize(new Dimension(WIDHT * SCALE, HEIGHT * SCALE));

		frame = new JFrame();

		loadSounds();
		loadImages();

		screen = new Screen(WIDHT, HEIGHT, pixels);

		ui = new UIManager();

		key = new Keyboard();
		mouse = new Mouse();
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		attackManager = new AttackSystem();
		aiManager = new AIManager();

		player = new Player(new Vector2d(0, 136), key);
		camera = new Camera(player, key);

		startMenu = new StartMenu();
		initialLevel = new InitialLevel(player, camera);
		bossLevel = new BossLevel(player, camera);

	}

	public void loadImages() {
		String path = "";
		try {
			path = "/textures/tutorials/pressP.png";
			System.out.print("Loading " + path);
			pressP = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/movementControlWhite.png";
			System.out.print("Loading " + path);
			movementControls = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

			path = "/textures/tutorials/attackControlWhite.png";
			System.out.print("Loading " + path);
			attackControl = ImageIO.read(this.getClass().getResource(path));
			System.out.println(" succeded!");

		} catch (IOException e) {
			System.err.println("failed!");
			e.printStackTrace();
		}
	}

	public void loadSounds() {
		Sound.sounds[0] = new Sound("/sounds/punch.wav");
		Sound.sounds[0].setGain(0.3);
		Sound.sounds[1] = new Sound("/sounds/push.wav");
		Sound.sounds[1].setGain(0.3);
		Sound.sounds[2] = new Sound("/sounds/start.wav");
		Sound.sounds[2].setGain(0.05);
		Sound.sounds[3] = new Sound("/sounds/thunderbolt.wav");
		Sound.sounds[3].setGain(0.07);
		Sound.sounds[4] = new Sound("/sounds/iceProjectile.wav");
		Sound.sounds[4].setGain(0.3);
		Sound.sounds[5] = new Sound("/sounds/iceProjectile.wav");
		Sound.sounds[5].setGain(0.3);
	}

	public static Graphics getGraphicsTool() {
		return graphicsTool;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static int getWIDTH() {
		return WIDHT;
	}

	public static int getScale() {
		return SCALE;
	}

	public static void setGameSpeed(double speedFactor, int time) {
		gameSpeed = speedFactor;
		Game.time = (int) (time * speedFactor);
		timer = 1;
	}

	public static UIManager getUIManager() {
		return ui;
	}

	private void start() {
		running = true;
		thread = new Thread(this, "GameThread");
		thread.start();
	}

	private void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean isGamePaused() {
		return gamePaused;
	}

	public static void setGamePaused(boolean gamePaused) {
		Game.gamePaused = gamePaused;
	}

	private static int time = 0;
	private static int timer = 0;

	@Override
	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double nsU = 1000000000.0 / 60.0;
		int iUpdates = 0;
		double deltaU = 0;

		double nsF = 1000000000.0 / fpsCap;
		int iFrames = 0;
		double deltaF = 0;

		requestFocus();

		while (running) {

			if (Game.timer == time) {
				gameSpeed = 1;
			}

			long now = System.nanoTime();
			deltaU += (now - lastTime) / nsU * gameSpeed;
			deltaF += (now - lastTime) / nsF;
			lastTime = now;

			if (deltaU > 1) {
				update();
				iUpdates++;
				deltaU--;
			}

			if (deltaF > 1) {
				render();
				iFrames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("Corruption Sack   |   " + iUpdates + " ups   " + iFrames + " fps");
				iUpdates = 0;
				iFrames = 0;
			}

		}
		stop();
	}

	private boolean startMenuReset = false;
	private boolean bossLevelReset = false;
	private boolean initialLevelReset = false;

	public void update() {
		// debuggingPrint();
		Game.timer++;
		if (state <= -1) {
			logoScreen.update();
			if (state == -1) startMenu.update();
		}
		else if (!gamePaused || state == 0) {
			ui.update();
			if (key.esc) state = 2;
			if (state == 0) {
				startMenu.activaUI(true);
				startMenu.update();
				if (!startMenuReset) {
					startMenuReset = true;
					Sound.sounds[2].loop();
				}
			}
			else {
				key.update();
				if (state == 1) {
					initialLevel.update();

					if (!initialLevelReset) {
						Sound.sounds[2].stop();
						initialLevel.setActive(true);
						bossLevel.setActive(false);
						initialLevelReset = true;
						bossLevelReset = false;
						startMenuReset = false;
						player.setPosition(new Vector2d(0, InitialLevel.getTerrainLevel() - 64));
						player.activateUI(true);
						Camera.lockCamera(false);
					}

					attackManager.update();
					aiManager.update();
					for (UIString str : UIString.stringsToDraw)
						str.update();

					for (int i = 0; i < Message.messages.size(); i++)
						Message.messages.get(i).update();
					if (player.isDead()) deathScreen.update();

				} else if (state == 2) {
					bossLevel.update();
					if (!bossLevelReset) {
						Sound.sounds[2].stop();
						bossLevel.setActive(true);
						initialLevel.setActive(false);
						initialLevelReset = false;
						startMenuReset = false;
						bossLevelReset = true;
						player.setPosition(new Vector2d(0, BossLevel.getTerrainLevel() - 64));
						player.activateUI(true);
						Camera.getPosition().x = 0;
						AIManager.implementedAIs.clear();
						AttackSystem.attacks.clear();
						initialLevel.clear();
						Camera.lockCamera(true);
						BossLevel.initMobs();
					}
					attackManager.update();
					aiManager.update();
					for (UIString str : UIString.stringsToDraw)
						str.update();

					for (int i = 0; i < Message.messages.size(); i++)
						Message.messages.get(i).update();
					if (player.isDead()) deathScreen.update();
				}
			}
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		graphicsTool = g;
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// screen.clean();
		screen.setOffSets((int) Camera.getPosition().x, (int) Camera.getPosition().y);

		drawPixels(screen);

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		drawGraphics(g);
		drawOtherGraphics(g);

		g.setFont(new Font("Verdana", 1, 5 * SCALE));
		g.drawString("x: " + Mouse.getX() + " y: " + Mouse.getY(), 20 * SCALE, 20 * SCALE);

		g.dispose();
		bs.show();
	}

	public void debuggingPrint() {
		System.out.println("\n======================================"
				+ "\nMobs size: " + Level.mobs.size()
				+ "\nPlayers size: " + Level.players.size()
				+ "\nProjectiles size: " + Level.projectiles.size()
				+ "\nParticles emmiters size: " + Level.pEmmiters.size()
				+ "\nParticles size: " + Level.particles.size()
				+ "\nImplemented AI's: " + AIManager.implementedAIs.size()
				+ "\nAttacks: " + AttackSystem.attacks.size()
				+ "\nIces killed: " + Level.icesKilled
				+ "\nShields killed: " + Level.shieldsKilled
				+ "\nThunders killed: " + Level.thundersKilled
				+ "\nPlayer X position: "
				+ (Level.players.size() != 0 ? Level.players.get(0).getPosition().x : "DEAD")
				+ "\n======================================\n");
	}

	private void drawPixels(Screen screen) {
		if (state == -1 || state == 0) startMenu.render(screen);
		else {
			if (state == 1) initialLevel.render(screen);
			else if (state == 2) bossLevel.render(screen);
		}

		attackManager.render(screen);
		ui.render(screen);
	}

	private void drawGraphics(Graphics g) {
		if (state <= -1) logoScreen.render(g);
		else {
			if (state == 1) {
				for (int i = 0; i < Message.messages.size(); i++)
					Message.messages.get(i).render(g);
			} else if (state == 2) {
			}
		}

		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g2.setComposite(c);
	}

	private void drawPauseScreen(Graphics g) {
		double sc = (SCALE * 0.67); // 2

		g.setColor(new Color(0xDD000000, true));
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(new Color(0xFFFFFFFF, false));
		g.setFont(new Font("Segoe UI Light", 0, 30 * SCALE));
		g.drawString("PAUSED", 172 * SCALE, 128 * SCALE);

		g.drawImage(movementControls, 176 * SCALE, 150 * SCALE, (int) (movementControls.getWidth() * sc),
				(int) (movementControls.getHeight() * sc), null);

		g.drawImage(attackControl, 194 * SCALE, 198 * SCALE, (int) (attackControl.getWidth() * sc),
				(int) (attackControl.getHeight() * sc), null);

		g.fillRect(163 * SCALE, 130 * SCALE, 122 * SCALE, (int) (1 * SCALE));
	}

	private void drawOtherGraphics(Graphics g) {
		double sc = (SCALE * 0.67); // 2

		for (int i = 0; i < UIString.stringsToDraw.size(); i++) {
			UIString str = UIString.stringsToDraw.get(i);
			if (str.isDead()) {
				str.remove();
				i--;
			}
			else str.draw(g);
		}

		if (state > 0) {
			g.setColor(state == 1 ? Color.BLACK : Color.WHITE);
			g.setFont(new Font("Impact", 0, 4 * SCALE));
			g.drawString("HEALTH POINTS:", 47 * SCALE, 39 * SCALE);
			// g.drawString("CORRUPTION:", 10 * SCALE, 53 * SCALE);

			if (!gamePaused) {
				g.drawImage(pressP, 350 * SCALE, (state == 1 ? 240 : 10) * SCALE,
						(int) (pressP.getWidth() * sc), (int) (pressP.getHeight() * sc), null);

				if (player.isDead()) {
					deathScreen.render(g);
				}
			} else {
				drawPauseScreen(g);
			}

		}

		if (state == 0 || gamePaused) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Verdana", 0, 5 * SCALE));
			g.drawString("Game for Ludum Dare 35 made by", 350 * SCALE, 224 * SCALE);
			g.setFont(new Font("Verdana", 1, 5 * SCALE));
			g.drawString("Lydzje Software", 370 * SCALE, 233 * SCALE);
			g.drawString("Twitter:   @lydzje_", 350 * SCALE, 245 * SCALE);
		}

	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Corruption Sack");
		try {
			game.frame.setIconImage(ImageIO.read(Game.class.getResource("/textures/icon/icon.png")));
		} catch (IOException e) {
			System.err.println("Frame icon loading failed");
			e.printStackTrace();
		}
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}
