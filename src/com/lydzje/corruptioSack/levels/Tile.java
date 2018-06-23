package com.lydzje.corruptioSack.levels;

import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.Sprite;
import com.lydzje.corruptioSack.maths.Vector2d;

public class Tile {

	private Vector2d position;
	private Sprite sprite;

	public Tile(Vector2d position, Sprite sprite) {
		this.position = position;
		this.sprite = sprite;
	}

	public Vector2d getPosition() {
		return position;
	}

	public void setPosition(Vector2d position) {
		this.position = position;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void render(Screen screen) {
		screen.renderSprite(position, sprite, true);
	}

}
