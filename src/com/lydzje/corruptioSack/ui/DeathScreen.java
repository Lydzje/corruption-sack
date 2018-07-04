package com.lydzje.corruptioSack.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.lydzje.corruptioSack.Game;

public class DeathScreen {

	private String runningAway1 = "Running away is a good idea when there are a lot of enemies,";
	private String runningAway2 = "they'll stop chasing you.";
	private String facingIce = "It's not necessary to face the ice projectile to punch it.";
	private String solo = "Don't fight 3 ice monsters at the same time.";
	private String dodge = "It's very important to dodge enemies attacks.";

	private float alpha;
	private int tip = 0;

	public DeathScreen() {
		tip = Game.random.nextInt(4);
	}

	// private int time = 0;

	public void update() {
		// time++;
		alpha += alpha > 0.85 ? 0.001 : 0.003f;
		if (alpha > 1) alpha = 1;
		// if (time == 700) System.exit(0);
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2.setComposite(c);
		g2.setColor(new Color(0xFF00080A));
		g2.fillRect(0, 0, Game.getWIDTH() * Game.getScale(), Game.getHEIGHT() * Game.getScale());
		g2.setColor(new Color(0xFFFFFFFF));
		g2.setFont(new Font("Segoe UI Light", 0, 30 * Game.getScale()));
		g2.drawString("YOU DIED", 158 * Game.getScale(), 135 * Game.getScale());

		g2.setFont(new Font("Segoe UI Light", Font.BOLD, 9 * Game.getScale()));
		g2.drawLine(211 * Game.getScale(), 191 * Game.getScale(), 227 * Game.getScale(),
				191 * Game.getScale());
		g2.drawString("TIP", 213 * Game.getScale(), 190 * Game.getScale());

		g2.setFont(new Font("Segoe UI Light", Font.ITALIC, 5 * Game.getScale()));

		if (tip == 0) {
			g2.drawString(runningAway1, 157 * Game.getScale(), 200 * Game.getScale());
			g2.drawString(runningAway2, 194 * Game.getScale(), 208 * Game.getScale());
		}
		else if (tip == 1) {
			g2.drawString(facingIce, 167 * Game.getScale(), 200 * Game.getScale());
		} else if (tip == 2) {
			g2.drawString(solo, 175 * Game.getScale(), 200 * Game.getScale());
		} else if (tip == 3) {
			g2.drawString(dodge, 175 * Game.getScale(), 200 * Game.getScale());
		}
	}
}
