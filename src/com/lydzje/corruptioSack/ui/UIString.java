package com.lydzje.corruptioSack.ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.maths.Vector2d;

public class UIString {

	public static List<UIString> stringsToDraw = new ArrayList<UIString>();

	public static enum Mode {
		DEFAULT, BUBBLE;
	}

	public String string;
	public Font font;
	public Color color;
	public Vector2d position;

	private Mode mode;
	private double life;
	private boolean dead = false;

	private float alpha = .1f;
	private boolean in = true;

	public UIString(String string, Font font, Color color, Vector2d position) {
		this.string = string;
		this.font = font;
		this.color = color;
		this.position = position;
		this.life = 1;
		this.alpha = 1;
		mode = Mode.DEFAULT;
		stringsToDraw.add(this);
	}

	public UIString(String string, Font font, Color color, Vector2d position, double life) {
		this.string = string;
		this.font = font;
		this.color = color;
		this.position = position.add(0, 5 * stringsToDraw.size());
		this.life = life * 60;
		mode = Mode.DEFAULT;
		stringsToDraw.add(this);
	}

	public boolean isDead() {
		return dead;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public void remove() {
		stringsToDraw.remove(this);
	}

	private int time = 0;

	public void update() {
		if (time < life) time++;
		else dead = true;
		if (mode == Mode.BUBBLE) {
			position.y -= 0.3;
			alpha += in ? 0.02f : -0.02f;
			if (alpha > 1) {
				alpha = 1;
				in = false;
			}
		}

	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
		g2.setComposite(c);
		g2.setColor(color);
		g2.setFont(font);
		g.drawString(string, (int) position.x * Game.getScale(), (int) position.y * Game.getScale());

		c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g2.setComposite(c);
	}

}
