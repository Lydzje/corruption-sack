package com.lydzje.corruptioSack.ui;

import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.maths.Vector2d;

public class UIComponent {
	// ---------------------------------VARIABLES----------------------------------

	protected Vector2d position;
	protected Vector2d offset;

	protected boolean active = true;

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public UIComponent(Vector2d position, Vector2d offset) {
		this.position = position;
		this.offset = offset;
	}

	public Vector2d getAbsolutePosition() {
		return new Vector2d(position.x + offset.x, position.y + offset.y);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean state) {
		active = state;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void update() {
	}

	public void render(Screen screen) {

	}

}