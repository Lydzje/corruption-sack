package com.lydzje.corruptioSack.utils;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {

	public static Sound[] sounds = new Sound[6];

	static {
		new JFXPanel();
	}

	private volatile MediaPlayer sound;
	private String soundFile = "";

	public Sound(String file) {
		create(file);
	}

	private void create(final String file) {

		try {
			System.out.print("Loading " + file);
			sound = new MediaPlayer(new Media(Sound.class.getResource(file).toString()));
			System.out.println(" succeded!");

		} catch (Exception e) {
			System.err.println(" failed!");
			e.printStackTrace();
		}

	}

	public void setGain(double gain) {
		sound.setVolume(gain);
	}

	public void play() {
		new Thread("Sound") {
			public void run() {
				sound.stop();
				sound.setCycleCount(0);
				sound.play();
			}
		}.start();
	}

	public void loop() {
		new Thread("Sound: " + soundFile) {
			public void run() {
				sound.stop();
				sound.setCycleCount(MediaPlayer.INDEFINITE);
				sound.play();
			}
		}.start();
	}

	public void stop() {
		sound.stop();
	}
}
