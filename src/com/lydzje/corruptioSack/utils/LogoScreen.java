package com.lydzje.corruptioSack.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.lydzje.corruptioSack.Game;

public class LogoScreen {

	private float alpha, alpha2 = 1;
	private BufferedImage logo;

	private boolean in = true;

	public LogoScreen() {
		try {
			logo = ImageIO.read(this.getClass().getResource("/textures/logo/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (alpha == 1) {
			in = false;
			Game.state = -1;
		}

		alpha += in ? 0.006f : -0.01f;
		alpha2 -= (!in && alpha <= 0) ? 0.003f : 0;

		if (alpha > 1) alpha = 1;
		if (alpha < 0) alpha = 0;

		if (alpha2 <= 0) {
			alpha2 = 0;
			Game.state = 0;
		}

	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, in ? 1 : alpha2);
		g.setColor(Color.BLACK);
		g2.setComposite(c);
		g2.fillRect(0, 0, Game.getWIDTH() * Game.getScale(), Game.getHEIGHT() * Game.getScale());

		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2.setComposite(c);
		g2.drawImage(logo, 165 * Game.getScale(), (int) (85.34 * Game.getScale()), logo.getWidth(),
				logo.getHeight(), null);

		g2.setFont(new Font("Segoe UI Light", 0, 7 * Game.getScale()));
		g2.setColor(Color.WHITE);
		g2.drawString("© 2016 Lydzje Software", 190 * Game.getScale(), 242 * Game.getScale());

	}
}
