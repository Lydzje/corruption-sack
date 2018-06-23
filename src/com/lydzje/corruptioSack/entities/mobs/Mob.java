package com.lydzje.corruptioSack.entities.mobs;

import java.awt.Color;
import java.awt.Font;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.gameplay.Attack;
import com.lydzje.corruptioSack.gameplay.AttackArea;
import com.lydzje.corruptioSack.graphics.AnimatedSprite;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.graphics.Sprite;
import com.lydzje.corruptioSack.levels.Level;
import com.lydzje.corruptioSack.maths.Vector2d;
import com.lydzje.corruptioSack.ui.UIString;
import com.lydzje.corruptioSack.ui.UIString.Mode;
import com.lydzje.corruptioSack.utils.Camera;
import com.lydzje.corruptioSack.utils.Sound;

public class Mob {

	protected Sprite sprite;
	protected AnimatedSprite anim;
	protected Vector2d position, center;

	protected int mobX, mobWidth;

	public static enum Direction {
		LEFT, RIGHT;
	}

	protected Direction dir;

	protected int maxHP;
	protected int hp;

	protected int corruption;

	protected int attackRate = 0;

	protected Attack[] attacks;
	protected AttackArea[] areas;
	protected int[] aKeys;

	protected Sound[] sounds;
	protected int[] sKeys;

	protected boolean inRange = false;
	protected boolean walking = false;
	protected boolean jumping = false;
	protected boolean inAir = false;
	protected boolean flying = false;
	protected int hurtTime;
	protected boolean hurt = false;
	protected int frozenTime;
	protected boolean frozen = false;
	protected boolean dead = false;
	protected boolean inmune = false;

	protected boolean hasTarget = false;
	protected Player target = null;

	protected static double leftBound = 0, rightBound = 1500;

	protected boolean attacking = false;
	protected boolean attackToPerform = false;
	protected int damageTaken;
	protected boolean damageTaked = true;

	protected double speed;
	protected double originalSpeed;
	protected double jumpPower;

	public Mob(Vector2d position) {
		this.position = position;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public AnimatedSprite getAnimSprite() {
		return anim;
	}

	public Vector2d getPosition() {
		return position;
	}

	public void setPosition(Vector2d position) {
		this.position = position;
	}

	public int getMobX() {
		return mobX;
	}

	public int getMobWidth() {
		return mobWidth;
	}

	public Direction getDir() {
		return dir;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getOriginalSpeed() {
		return originalSpeed;
	}

	public void setOriginalSpeed(double originalSpeed) {
		this.originalSpeed = originalSpeed;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getCorruption() {
		return corruption;
	}

	public void addCorruption(int corruption) {
		this.corruption += corruption;
	}

	public boolean isInRange() {
		return inRange;
	}

	public void setInRange(boolean inRange) {
		this.inRange = inRange;
	}

	public Player getTarget() {
		return target;
	}

	public void setTarget(Player target) {
		this.target = target;
	}

	public boolean isHasTarget() {
		return hasTarget;
	}

	public void setHasTarget(boolean hasTarget) {
		this.hasTarget = hasTarget;
	}

	public boolean isAttackToPerform() {
		return attackToPerform;
	}

	public void setAttackToPerform(boolean attackToPerform) {
		this.attackToPerform = attackToPerform;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public int getDamageTaken() {
		return damageTaken;
	}

	public void setDamageTaken(int damageTaken) {
		this.damageTaken = damageTaken;
	}

	public boolean isDamageTaked() {
		return damageTaked;
	}

	public void setDamageTaked(boolean damageTaked) {
		this.damageTaked = damageTaked;
	}

	public boolean isHurt() {
		return hurt;
	}

	public void setHurt(boolean hurt) {
		if (hurt) damageTaked = false;
		this.hurt = hurt;
		hurtTime = 60;
		if (this instanceof Player) Camera.shakeCamera(30);
	}

	public int getHurtTime() {
		return hurtTime;
	}

	public boolean isFrozen() {
		return frozen;
	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;

		if (frozen) {
			speed = speed / 3.0;
			frozenTime = 180;
		}
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isInmune() {
		return inmune;
	}

	public void setInmune(boolean inmune) {
		this.inmune = inmune;
	}

	public boolean isJumping() {
		return jumping;
	}

	public boolean isFlying() {
		return flying;
	}

	public AnimatedSprite getAnim() {
		return anim;
	}

	public void setAnim(AnimatedSprite anim) {
		this.anim = anim;
	}

	public static void setBounds(double leftBound, double rightBound) {
		Mob.leftBound = leftBound;
		Mob.rightBound = rightBound;
	}

	public double getLeftBound() {
		return leftBound;
	}

	public double getRightBound() {
		return rightBound;
	}

	public boolean isOnMovementsBounds() {
		return (dir == Direction.LEFT && position.x <= leftBound)
				|| (dir == Direction.RIGHT && position.x + sprite.getWidth() >= rightBound);
	}

	public boolean isNextToBound() {
		return position.x <= leftBound || position.x + sprite.getWidth() >= rightBound;
	}

	public boolean isWalking() {
		return walking;
	}

	public void setWalking(boolean walking) {
		this.walking = walking;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getAttackRate() {
		return attackRate;
	}

	public void setAttackRate(int attackRate) {
		this.attackRate = attackRate;
	}

	public void attack() {
	}

	public void move() {

		position.x += dir == Direction.LEFT ? (isOnMovementsBounds() ? 0 : -speed) : (
				isOnMovementsBounds() ? 0 : speed);

		// else position.x += dir == Direction.LEFT ? (position.x <= 0 ? 0 : -speed) : speed;
	}

	protected void moveRandomly(AnimatedSprite left, AnimatedSprite right, AnimatedSprite runningL,
			AnimatedSprite runningR) {

		if (timer % (30 + Game.random.nextInt(60)) == 0) {
			dir = Game.random.nextInt(2) == 0 ? Direction.LEFT : Direction.RIGHT;
			walking = Game.random.nextInt(3) == 0 ? true : false;
		}

		if (walking) {
			anim = dir == Direction.LEFT ? runningL : runningR;

		} else {
			anim = dir == Direction.LEFT ? left : right;
		}
	}

	public void moveTo() {
		// HACER MÉTODO SIN PARÁMETROS
		if (target.getPosition().x < position.x) {
			dir = Direction.LEFT;
		} else dir = Direction.RIGHT;

		if (Math.abs(position.x - target.getPosition().x) > 32) {
			walking = true;
			inRange = false;
		}

		else {
			walking = false;
			inRange = true;
		}
	}

	protected double yAmount = 0;
	protected int timer = 1;

	public void update() {

		timer++;
		if (hp <= 0) {
			dead = true;
			if (!(this instanceof Player)) {
				if (!Level.players.isEmpty()) {
					Level.players.get(0).mobsKilled++;
					Level.players.get(0).addCorruption(corruption);
					UIString str = new UIString("+" + corruption, new Font("Impact", 0, 5 * Game.getScale()),
							new Color(0xFF000000),
							new Vector2d(150, 65),
							1.5);
					str.setMode(Mode.BUBBLE);
				}
			}
		}

		if (frozen && frozenTime > 0) {
			frozenTime--;

		} else if (frozen) {
			speed = originalSpeed;
			frozen = false;
		}

		if (!frozen) speed = originalSpeed;

		if (hurt && !damageTaked) {
			damageTaked = true;
			if (hp < damageTaken) hp = 0;
			else hp -= damageTaken;
			damageTaken = 0;
		}

		if (hurtTime > 0) hurtTime--;

		else hurt = false;
		if (walking && !anim.isPlayingAnim()) move();
		if (jumping) {
			position.y -= jumpPower;
			jumpPower -= 0.3;
			if (this instanceof Player) {
				Player p = (Player) this;
				if (p.getForm() != Player.Forms.ICE) {
					if (position.y + sprite.getHeight() >= Level.getTerrainLevel()) {
						position.y = Level.getTerrainLevel() - sprite.getHeight();
						jumping = false;
					}
				} else {
					if (position.y + sprite.getHeight() + 32 >= Level.getTerrainLevel()) {
						position.y = Level.getTerrainLevel() - sprite.getHeight() - 32;
						jumping = false;
					}
				}
			} else {
				if (position.y + sprite.getHeight() >= Level.getTerrainLevel()) {
					position.y = Level.getTerrainLevel() - sprite.getHeight();
					jumping = false;
				}
			}
		} else position.y = Level.getTerrainLevel() - 64;
	}

	public void render(Screen screen) {
		screen.renderSprite(position, sprite, true);
	}

}
