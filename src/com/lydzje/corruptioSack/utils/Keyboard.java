package com.lydzje.corruptioSack.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.levels.Level;

public class Keyboard implements KeyListener {

	private int iKeys = 10;
	private boolean[] keys = new boolean[iKeys];

	public boolean right, left, jump, esc, pause, sprint;

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) keys[0] = true;
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) keys[1] = true;
		if (e.getKeyCode() == KeyEvent.VK_SPACE) keys[2] = true;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) keys[3] = true;
		if (e.getKeyCode() == KeyEvent.VK_P) {
			keys[4] = true;
			if(!Level.players.isEmpty() && Game.state>0)Game.setGamePaused(!Game.isGamePaused());
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) keys[5] = true;
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) keys[0] = false;
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) keys[1] = false;
		if (e.getKeyCode() == KeyEvent.VK_SPACE) keys[2] = false;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) keys[3] = false;
		if (e.getKeyCode() == KeyEvent.VK_P) keys[4] = false;
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) keys[5] = false;
	}

	public void update() {
		right = keys[0];
		left = keys[1];
		jump = keys[2];
		esc = keys[3];
		pause = keys[4];
		sprint = keys[5];
	}
}
