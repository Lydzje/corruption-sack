package com.lydzje.corruptioSack.ui;

import java.util.ArrayList;
import java.util.List;

import com.lydzje.corruptioSack.graphics.Screen;

public class UIManager {

	private List<UIPanel> panels = new ArrayList<UIPanel>();

	public void add(UIPanel panel) {
		panels.add(panel);
	}

	public void update() {
		for (int i = 0; i < panels.size(); i++) {
			if (panels.get(i).isActive()) panels.get(i).update();
		}
	}

	public void render(Screen screen) {
		for (UIPanel panel : panels) {
			if (panel.isActive()) panel.render(screen);
		}
	}

}
