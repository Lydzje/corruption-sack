package com.lydzje.corruptioSack.gameplay;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.maths.Vector2d;

public class Message {

	public static List<Message> messages = new ArrayList<Message>();

	private Vector2d position;
	private BufferedImage img;

	private String str;
	private Font font;
	private Color color;

	private int duration;
	private double scale;

	private float alpha, alphaSpeed;
	private boolean in = true;

	/**
	 * 
	 * @param duration is the duration in seconds
	 */
	public Message(Vector2d position, BufferedImage img, int duration, double scale) {
		this.position = position;
		this.img = img;
		this.duration = duration * 60;
		this.scale = scale;

		this.alphaSpeed = 0.008f;// tardará 120 updates, es decir 2 segundos cada transicion

		messages.add(this);

	}

	public Message(Vector2d position, String str, Font font, Color black, int duration) {
		this.position = position;
		this.str = str;
		this.font = font;
		this.color = black;
		this.duration = duration * 60;

		this.alphaSpeed = 0.008f;// tardará 120 updates, es decir 2 segundos cada transicion

		messages.add(this);

	}

	private int time = 0;

	public void update() {

		if (alpha == 1) time++;

		if (alpha == 1 && time == duration) in = false;

		if (!in && alpha == 0) messages.remove(this);

		alpha += in ? alphaSpeed : -alphaSpeed;

		if (alpha >= 1) alpha = 1;
		if (alpha <= 0) alpha = 0;

	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2.setComposite(c);

		// System.out.println((int) position.y * Game.getScale());

		if (img != null) g2.drawImage(img, (int) position.x * Game.getScale(),
				(int) position.y * Game.getScale(), (int) (img.getWidth() * scale),
				(int) (img.getHeight() * scale), null);

		if (str != null) {
			g2.setFont(font);
			g2.setColor(color);
			g2.drawString(str, (int) position.x * Game.getScale(), (int) position.y * Game.getScale());
		}
	}

}
