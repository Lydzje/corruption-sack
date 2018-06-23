package com.lydzje.corruptioSack.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.lydzje.corruptioSack.Game;

public class Mouse implements MouseListener, MouseMotionListener {

	private static int mouseX = -1, mouseY = -1, mouseB = -1;

	public Mouse() {

	}

	public static int getX() {
		return (int) (mouseX / Game.getScale());
	}

	public static int getY() {
		return (int) (mouseY / Game.getScale());
	}

	public static int getUnscaledX() {
		return mouseX;
	}

	public static int getUnscaledY() {
		return mouseY;
	}

	public static int getButton() {
		return mouseB;
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
	}

	public void mouseReleased(MouseEvent e) {
		mouseB = MouseEvent.NOBUTTON;
	}

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

}