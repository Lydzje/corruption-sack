package com.lydzje.corruptioSack.graphics;

public class AnimatedSprite extends Sprite {

	private Sprite sprite;
	private SpriteSheet sheet;

	private int frame = 0;
	private int fps = 7;
	private int length;
	private int time = 0;

	private boolean playingAnim = false;

	public static AnimatedSprite startMenuAnim = new AnimatedSprite(455, 256, 8, SpriteSheet.startMenu);

	public AnimatedSprite(int width, int height, int length, SpriteSheet sheet) {
		super(0, 0, width, height, sheet);
		this.length = length;
		this.sheet = sheet;
		sprite = this.sheet.sprites[0];
		if (length > sheet.sprites.length) System.err.println("Error! Length of animation is too long!");
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public boolean isPlayingAnim() {
		return playingAnim;
	}

	public void setPlayingAnim(boolean playingAnim) {
		this.playingAnim = playingAnim;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getLength() {
		return length;
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public void update() {
		time++;

		if (playingAnim) {
			playAnimation();
		}
		else {
			if (time % (60 / fps) == 0) {
				if (frame >= length - 1) frame = 0;
				else frame++;
				sprite = sheet.sprites[frame];
			}
		}
	}

	public void playAnimation() {
		if (time % (60 / fps) == 0) {
			if (frame >= length - 1) {
				frame = 0;
				playingAnim = false;
			} else frame++;
			sprite = sheet.sprites[frame];
		}
	}

}
