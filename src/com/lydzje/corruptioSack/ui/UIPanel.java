package com.lydzje.corruptioSack.ui;

import java.util.ArrayList;
import java.util.List;

import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.maths.Vector2d;

public class UIPanel {
	// ---------------------------------VARIABLES----------------------------------

	private Vector2d position;

	private boolean active = true;

	private List<UIComponent> components = new ArrayList<UIComponent>();

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------

	public UIPanel(Vector2d position) {
		this.position = position;
	}

	public Vector2d getPosition() {
		return position;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean state) {
		active = state;
	}

	// ---------------------------------MÉTODOS----------------------------------

	public void add(UIComponent component) {
		components.add(component);
	}

	public void update() {
		for (int i = 0; i < components.size(); i++)
			if (components.get(i).isActive()) components.get(i).update();
	}

	public void render(Screen screen) {
		for (int i = 0; i < components.size(); i++)
			if (components.get(i).isActive()) components.get(i).render(screen);
	}
}
