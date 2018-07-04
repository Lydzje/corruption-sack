package com.lydzje.corruptioSack.entities.mobs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.entities.mobs.particles.Particle.Type;
import com.lydzje.corruptioSack.entities.mobs.particles.ParticleEmmiter;
import com.lydzje.corruptioSack.entities.projectiles.CatIceProjectile;
import com.lydzje.corruptioSack.gameplay.Attack;
import com.lydzje.corruptioSack.gameplay.AttackArea;
import com.lydzje.corruptioSack.gameplay.Thunderbolt;
import com.lydzje.corruptioSack.graphics.AnimatedSprite;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.Sprite;
import com.lydzje.corruptioSack.graphics.SpriteSheet;
import com.lydzje.corruptioSack.levels.Level;
import com.lydzje.corruptioSack.maths.Vector2d;
import com.lydzje.corruptioSack.maths.Vector3i;
import com.lydzje.corruptioSack.ui.ButtonActionListener;
import com.lydzje.corruptioSack.ui.UIButton;
import com.lydzje.corruptioSack.ui.UIButtonListener;
import com.lydzje.corruptioSack.ui.UICorruptionBar;
import com.lydzje.corruptioSack.ui.UIHealthBar;
import com.lydzje.corruptioSack.ui.UIManager;
import com.lydzje.corruptioSack.ui.UIPanel;
import com.lydzje.corruptioSack.ui.UIString;
import com.lydzje.corruptioSack.utils.Camera;
import com.lydzje.corruptioSack.utils.Keyboard;
import com.lydzje.corruptioSack.utils.Mouse;
import com.lydzje.corruptioSack.utils.Sound;

public class Player extends Mob {

	private Keyboard input;

	private UIManager ui;
	private UIPanel panel;
	private UIButton iceB;
	private Sprite ice;
	private UIButton thunderB;
	private Sprite thunder;
	private UIButton shieldB;
	private Sprite shield;
	private UIHealthBar healthBar;
	private UICorruptionBar corruptionBar;

	private AnimatedSprite playerR = new AnimatedSprite(64, 64, 8, SpriteSheet.player_right);
	private AnimatedSprite playerL = new AnimatedSprite(64, 64, 8, SpriteSheet.player_left);
	private AnimatedSprite walkingR = new AnimatedSprite(64, 64, 4, SpriteSheet.playerW_right);
	private AnimatedSprite walkingL = new AnimatedSprite(64, 64, 4, SpriteSheet.playerW_left);

	/*
	 * private AnimatedSprite deathR = new AnimatedSprite(64, 64, 8, SpriteSheet.player_deathR);
	 * private AnimatedSprite deathL = new AnimatedSprite(64, 64, 8, SpriteSheet.player_deathL);
	 */
	private AnimatedSprite punchR = new AnimatedSprite(64, 64, 3, SpriteSheet.player_punchR);
	private AnimatedSprite punchL = new AnimatedSprite(64, 64, 3, SpriteSheet.player_punchL);

	private Attack punch;

	private AnimatedSprite pushR = new AnimatedSprite(64, 64, 3, SpriteSheet.shield_pushRF);
	private AnimatedSprite pushL = new AnimatedSprite(64, 64, 3, SpriteSheet.shield_pushLF);

	private Attack push;

	public int mobsKilled = 0;

	public static enum Forms {
		CAT, ICE, SHIELD, THUNDER;
	}

	private Forms form;
	private boolean transformed = false;
	private int formTime = 0;

	private int iceTransCD = 0;
	private boolean iceTransOnCD = false;
	private int thunderTransCD = 0;
	private boolean thunderTransOnCD = false;
	private int shieldTransCD = 0;
	private boolean shieldTransOnCD = false;

	private boolean movementLocked = true;
	private boolean attacksLocked = true;

	public Player(Vector2d position, Keyboard input) {
		super(position);
		this.input = input;
		form = Forms.CAT;
		mobX = 22;
		mobWidth = 24;
		maxHP = 300;
		hp = maxHP;
		speed = 2.5;
		originalSpeed = speed;
		dir = Direction.RIGHT;
		anim = playerR;
		sprite = anim.getSprite();
		initAttacks();
		initUI();
		center = new Vector2d(mobX + mobWidth / 2, sprite.getHeight() / 2);

	}

	public void initUI() {
		this.ui = Game.getUIManager();

		Vector2d panelPos = new Vector2d(22, 52);

		ice = Sprite.iceB;
		shield = Sprite.shieldB;
		thunder = Sprite.thunderB;

		panel = new UIPanel(panelPos);
		ui.add(panel);

		iceB = new UIButton(panelPos, new Vector2d(25, 4), ice, new ButtonActionListener() {

			public void perform() {

			}

		});

		iceB.setButtonListener(new UIButtonListener() {
			public void entered(UIButton button) {
				button.getSprite().setToBright(true);
				if (iceTransCD == 0) button.getSprite().setBright(new Vector3i(40, 40, 40));
				else button.getSprite().setBright(new Vector3i(-80, -80, -80));
			}

			public void exited(UIButton button) {
				if (iceTransCD == 0) {
					button.getSprite().setToBright(false);
					button.getSprite().setBright(new Vector3i(0, 0, 0));
				}
				else {
					button.getSprite().setToBright(true);
					button.getSprite().setBright(new Vector3i(-80, -80, -80));
				}
			}

			public void pressed(UIButton button) {
				button.getSprite().setToBright(true);
				button.getSprite().setBright(new Vector3i(-80, -80, -80));
				if (iceTransCD == 0) setForm(Forms.ICE);
			}

			public void released(UIButton button) {
				button.getSprite().setToBright(true);
				if (iceTransCD == 0) button.getSprite().setBright(new Vector3i(40, 40, 40));
				else button.getSprite().setBright(new Vector3i(-80, -80, -80));
			}
		});
		iceB.setActive(false);
		panel.add(iceB);

		shieldB = new UIButton(panelPos, new Vector2d(45, 4), shield, new ButtonActionListener() {

			public void perform() {

			}

		});

		shieldB.setButtonListener(new UIButtonListener() {
			public void entered(UIButton button) {
				button.getSprite().setToBright(true);
				if (shieldTransCD == 0) button.getSprite().setBright(new Vector3i(40, 40, 40));
				else button.getSprite().setBright(new Vector3i(-80, -80, -80));
			}

			public void exited(UIButton button) {
				if (shieldTransCD == 0) {
					button.getSprite().setToBright(false);
					button.getSprite().setBright(new Vector3i(0, 0, 0));
				}
				else {
					button.getSprite().setToBright(true);
					button.getSprite().setBright(new Vector3i(-80, -80, -80));
				}
			}

			public void pressed(UIButton button) {
				button.getSprite().setToBright(true);
				button.getSprite().setBright(new Vector3i(-80, -80, -80));
				if (shieldTransCD == 0) setForm(Forms.SHIELD);
			}

			public void released(UIButton button) {
				button.getSprite().setToBright(true);
				if (shieldTransCD == 0) button.getSprite().setBright(new Vector3i(40, 40, 40));
				else button.getSprite().setBright(new Vector3i(-80, -80, -80));
			}
		});
		shieldB.setActive(false);
		panel.add(shieldB);

		thunderB = new UIButton(panelPos, new Vector2d(65, 4), thunder, new ButtonActionListener() {

			public void perform() {

			}

		});

		thunderB.setButtonListener(new UIButtonListener() {
			public void entered(UIButton button) {
				button.getSprite().setToBright(true);
				if (thunderTransCD == 0) button.getSprite().setBright(new Vector3i(40, 40, 40));
				else button.getSprite().setBright(new Vector3i(-80, -80, -80));
			}

			public void exited(UIButton button) {
				if (thunderTransCD == 0) {
					button.getSprite().setToBright(false);
					button.getSprite().setBright(new Vector3i(0, 0, 0));
				}
				else {
					button.getSprite().setToBright(true);
					button.getSprite().setBright(new Vector3i(-80, -80, -80));
				}
			}

			public void pressed(UIButton button) {
				button.getSprite().setToBright(true);
				button.getSprite().setBright(new Vector3i(-80, -80, -80));
				if (thunderTransCD == 0) setForm(Forms.THUNDER);
			}

			public void released(UIButton button) {
				button.getSprite().setToBright(true);
				if (thunderTransCD == 0) button.getSprite().setBright(new Vector3i(40, 40, 40));
				else button.getSprite().setBright(new Vector3i(-80, -80, -80));
			}
		});
		thunderB.setActive(false);
		panel.add(thunderB);

		healthBar = new UIHealthBar(panelPos, new Vector2d(15, -10), this);
		panel.add(healthBar);

		corruptionBar = new UICorruptionBar(panelPos, new Vector2d(17, -2), this);
		panel.add(corruptionBar);

		panel.setActive(false);

	}

	public void initAttacks() {
		attacks = new Attack[2];

		areas = new AttackArea[1];
		aKeys = new int[1];
		areas[0] = new AttackArea(this, 2, 44, (int) position.y, 20, 64, 75, 0);
		aKeys[0] = 2;

		sounds = new Sound[1];
		sKeys = new int[1];
		sounds[0] = Sound.sounds[0];
		sKeys[0] = 2;

		punch = new Attack(this, areas, aKeys, sounds, sKeys);

		attacks[0] = punch;

		// SHIELD FORM

		AttackArea[] areas = new AttackArea[1];
		int[] aKeys = new int[1];
		areas[0] = new AttackArea(this, 9, 32, (int) position.y, 24, 64, 90, 0);
		aKeys[0] = 1;

		Sound[] sounds = new Sound[1];
		int[] sKeys = new int[1];
		sounds[0] = Sound.sounds[1];
		sKeys[0] = 1;

		push = new Attack(this, areas, aKeys, sounds, sKeys);

		attacks[1] = push;

	}

	public void activateUI(boolean state) {
		panel.setActive(state);
	}

	public void setForm(Forms form) {
		this.form = form;
		if (form == Forms.CAT) {
			playerR = new AnimatedSprite(64, 64, 8, SpriteSheet.player_right);
			playerL = new AnimatedSprite(64, 64, 8, SpriteSheet.player_left);
			walkingR = new AnimatedSprite(64, 64, 4, SpriteSheet.playerW_right);
			walkingL = new AnimatedSprite(64, 64, 4, SpriteSheet.playerW_left);

			transformed = false;
			mobX = 22;
			mobWidth = 24;
			originalSpeed = 2.5;
			attackRate = 0;

			hp = maxHP == 600 ? (int) (hp / (double) maxHP * 300) : hp;
			maxHP = 300;

			anim.setFps(7);

			center = new Vector2d(mobX + mobWidth / 2, sprite.getHeight() / 2);
			Level.pEmmiters.add(new ParticleEmmiter(center.add(position.x, position.y), 80, 200, 0xff192123));
		}
		else if (form == Forms.ICE) {
			playerR = new AnimatedSprite(32, 32, 4, SpriteSheet.ice_rightF);
			playerL = new AnimatedSprite(32, 32, 4, SpriteSheet.ice_leftF);
			walkingR = new AnimatedSprite(32, 32, 4, SpriteSheet.iceW_rightF);
			walkingL = new AnimatedSprite(32, 32, 4, SpriteSheet.iceW_leftF);

			transformed = true;
			iceTransCD = 1200; // 20 seconds * 60 updates
			iceTransOnCD = true;
			formTime = 600; // 10 seconds * 60 updates
			mobX = 4;
			mobWidth = 24;
			originalSpeed = 1.8;
			attackRate = 0;

			hp = maxHP == 600 ? (int) (hp / (double) maxHP * 300) : hp;
			maxHP = 300;

			center = new Vector2d(mobX + mobWidth / 2, sprite.getHeight() / 2);
			Level.pEmmiters.add(new ParticleEmmiter(center.add(position.x, position.y), 80, 200, 0xff84B0B5));
		}

		else if (form == Forms.THUNDER) {
			playerR = new AnimatedSprite(64, 64, 4, SpriteSheet.thunder_rightF);
			playerL = new AnimatedSprite(64, 64, 4, SpriteSheet.thunder_leftF);
			walkingR = new AnimatedSprite(64, 64, 4, SpriteSheet.thunderW_rightF);
			walkingL = new AnimatedSprite(64, 64, 4, SpriteSheet.thunderW_leftF);

			transformed = true;
			thunderTransCD = 1200; // 20 seconds * 60 updates
			thunderTransOnCD = true;
			formTime = 600; // 10 seconds * 60 updates

			mobX = 12;
			mobWidth = 49;
			originalSpeed = 2;
			attackRate = 0;

			hp = maxHP == 600 ? (int) (hp / (double) maxHP * 300) : hp;
			maxHP = 300;

			center = new Vector2d(mobX + mobWidth / 2, sprite.getHeight() / 2);
			Level.pEmmiters.add(new ParticleEmmiter(center.add(position.x, position.y), 80, 200, 0xff727272));
		}

		else if (form == Forms.SHIELD) {
			playerR = new AnimatedSprite(64, 64, 4, SpriteSheet.shield_rightF);
			playerL = new AnimatedSprite(64, 64, 4, SpriteSheet.shield_leftF);
			walkingR = new AnimatedSprite(64, 64, 4, SpriteSheet.shieldW_rightF);
			walkingL = new AnimatedSprite(64, 64, 4, SpriteSheet.shieldW_leftF);

			transformed = true;
			shieldTransCD = 1200;
			shieldTransOnCD = true;
			formTime = 600;

			mobX = 9;
			mobWidth = 44;
			originalSpeed = 1;
			attackRate = 0;

			maxHP *= 2;
			hp *= 2;

			anim.setFps(5);

			center = new Vector2d(mobX + mobWidth / 2, sprite.getHeight() / 2);
			Level.pEmmiters.add(new ParticleEmmiter(center.add(position.x, position.y), 80, 200, 0xff223051));

		}

	}

	public Forms getForm() {
		return form;
	}

	public boolean isThisFormUnlocked(Forms form) {
		if (form == Forms.ICE) return iceB.isActive();
		else if (form == Forms.SHIELD) return shieldB.isActive();
		else if (form == Forms.THUNDER) return thunderB.isActive();
		else return false;
	}

	public void unlockMovement(boolean state) {
		movementLocked = !state;
	}

	public void unlockAttacks(boolean state) {
		attacksLocked = !state;
	}

	public boolean isMovementLocked() {
		return movementLocked;
	}

	public boolean areAttacksLocked() {
		return attacksLocked;
	}

	public void unlockForm() {
		hp += hp <= 0.57 * maxHP ? 0.43 * maxHP : (maxHP - hp);
		new ParticleEmmiter(position.add(center.x, sprite.getHeight()), Type.HEALING, 90, 200, 0xFF192123);
		if (!iceB.isActive()) {
			iceB.setActive(true);
		}
		else if (!shieldB.isActive()) {
			shieldB.setActive(true);
		}
		else if (!thunderB.isActive()) {
			thunderB.setActive(true);
		}
	}

	private void handleCoolDowns() {
		if (iceTransCD > 0) {
			iceTransCD--;
			new UIString(iceTransCD / 60 < 10 ? "0" + iceTransCD / 60 : iceTransCD / 60 + "", new Font(
					"Verdana", 0, 7 * Game.getScale()), Color.WHITE, iceB.getAbsolutePosition().add(4, 11));
		}
		if (iceTransOnCD && iceTransCD == 0) {
			iceB.getSprite().setBright(new Vector3i(0, 0, 0));
			iceTransOnCD = false;
		}

		if (thunderTransCD > 0) {
			thunderTransCD--;
			new UIString(thunderTransCD / 60 < 10 ? "0" + thunderTransCD / 60 : thunderTransCD / 60 + "",
					new Font("Verdana", 0, 7 * Game.getScale()), Color.WHITE, thunderB.getAbsolutePosition()
							.add(4, 11));
		}
		if (thunderTransOnCD && thunderTransCD == 0) {
			thunderB.getSprite().setBright(new Vector3i(0, 0, 0));
			thunderTransOnCD = false;
		}

		if (shieldTransCD > 0) {
			shieldTransCD--;
			new UIString(shieldTransCD / 60 < 10 ? "0" + shieldTransCD / 60 : shieldTransCD / 60 + "",
					new Font("Verdana", 0, 7 * Game.getScale()), Color.WHITE, shieldB.getAbsolutePosition()
							.add(4, 11));
		}
		if (shieldTransOnCD && shieldTransCD == 0) {
			shieldB.getSprite().setBright(new Vector3i(0, 0, 0));
			shieldTransOnCD = false;
		}
	}

	private void handleAttacksUpdate() {
		if (form == Forms.CAT) {
			if (attacking) attacks[0].update();
			if (!jumping) position.y = Level.getTerrainLevel() - sprite.getHeight();
			if (input.jump && !jumping) jump();
		}

		if (form == Forms.SHIELD && attacking) attacks[1].update();
	}

	public void jump() {
		jumping = true;
		jumpPower = 5.0;
	}

	public void attack() {
		if (!attacksLocked) {
			if (form == Forms.CAT) {
				attackRate = 40;
				if (dir == Direction.RIGHT) anim = punchR;
				else if (dir == Direction.LEFT) anim = punchL;
				anim.setPlayingAnim(true);
				anim.setFps(8);
				attacking = true;
				attackToPerform = true;
			}

			else if (form == Forms.ICE) {
				attackRate = 90;
				shoot();
			}

			else if (form == Forms.THUNDER) {
				attackRate = 180;

				new Thunderbolt(new Vector2d(Mouse.getX() - Camera.getPosition().x - 32, 0));
			}

			else if (form == Forms.SHIELD) {
				attackRate = 100;
				if (dir == Direction.RIGHT) anim = pushR;
				else if (dir == Direction.LEFT) anim = pushL;
				anim.setPlayingAnim(true);
				attacking = true;
				attackToPerform = true;
			}
		}

	}

	// ICE FORM
	private void shoot() {
		Vector2d shotPos = new Vector2d(position.x + sprite.getWidth() / 2, position.y
				+ sprite.getHeight() / 2);
		Sound.sounds[5].play();
		Level.projectiles.add(new CatIceProjectile(shotPos, dir));
	}

	public void update() {

		anim.update();

		handleCoolDowns();
		if (formTime > 0) formTime--;
		if (formTime == 0 && transformed) setForm(Forms.CAT);
		if (attackRate > 0) attackRate--;

		handleAttacksUpdate();

		if (Mouse.getButton() == MouseEvent.BUTTON3 && attackRate == 0) attack();

		if (!anim.isPlayingAnim()) {
			if (walking) anim = dir == Direction.LEFT ? walkingL : walkingR;
			else anim = dir == Direction.LEFT ? playerL : playerR;

			if (!movementLocked) {
				if (input.right && !input.left) {
					dir = Direction.RIGHT;
					walking = true;
				}
				else if (input.left && !input.right) {
					dir = Direction.LEFT;
					walking = true;
				}
				else walking = false;
			} else walking = false;
		}

		super.update();

		if (hp <= 0) {
			Camera.lockCamera(true);
			new ParticleEmmiter(position.add(center.x, center.y), Type.PLAYER_DEATH, 600, 600, 0xFF192123);
			dead = true;
			Level.players.remove(this);
		}

	}

	public void render(Screen screen) {
		sprite = anim.getSprite();

		if (!hurt && !frozen) screen.renderMob(position, this, hurt, frozen);
		else {
			if (hurt && timer % 30 >= 0 && timer % 30 <= 20) screen.renderMob(position, this, hurt, frozen);
			else if (frozen && !hurt) screen.renderMob(position, this, hurt, frozen);
		}

	}

}
