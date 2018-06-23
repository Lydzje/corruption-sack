package com.lydzje.corruptioSack.ui;

import java.awt.event.MouseEvent;

import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.Sprite;
import com.lydzje.corruptioSack.maths.Vector2d;
import com.lydzje.corruptioSack.utils.Mouse;

public class UIButton extends UIComponent {

	private Sprite sprite;
	private UIButtonListener listener;
	private ButtonActionListener actionListener;

	public UIButton(Vector2d position, Vector2d offset, Sprite sprite, ButtonActionListener actionListener) {
		super(position, offset);
		this.sprite = sprite;
		this.actionListener = actionListener;
		listener = new UIButtonListener();
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setButtonListener(UIButtonListener listener) {
		this.listener = listener;
	}

	private boolean isMouseInside() {
		double xA = position.x + offset.x;
		double yA = position.y + offset.y;

		if (Mouse.getX() >= xA && Mouse.getX() <= xA + sprite.getWidth() && Mouse.getY() >= yA
				&& Mouse.getY() <= yA + sprite.getHeight())
			return true;

		return false;

	}

	private boolean leftMouseButtonDown = false, inside = false, ignorePressed = false, pressed = false;

	public void update() {
		leftMouseButtonDown = Mouse.getButton() == MouseEvent.BUTTON1;
		if (isMouseInside()) {
			if (!inside) {
				if (leftMouseButtonDown)
					ignorePressed = true;
				else
					ignorePressed = false;

				listener.entered(this);
			}
			inside = true;

			if (!pressed && !ignorePressed && leftMouseButtonDown) {
				listener.pressed(this);
				pressed = true;

			} else if (Mouse.getButton() == MouseEvent.NOBUTTON) {
				if (pressed) {
					listener.released(this);
					actionListener.perform();
					pressed = false;
				}
				ignorePressed = false;
			}
		} else {
			if (inside) {
				listener.exited(this);
				pressed = false;
			}
			inside = false;
		}
	}

	public void render(Screen screen) {
		screen.renderSprite(getAbsolutePosition(), sprite, false);
	}
}
