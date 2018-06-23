package com.lydzje.corruptioSack.gameplay;

import java.util.ArrayList;
import java.util.List;

import com.lydzje.corruptioSack.entities.mobs.Mob;
import com.lydzje.corruptioSack.entities.mobs.Player;

public class AttackArea {
	private Mob mob;
	private int x, xL, xR, y;
	private int width, height;
	private int damage;
	private int duration;

	private boolean byEnemy = true;

	private static List<AttackArea> attacks = new ArrayList<AttackArea>();

	// -----------------------------CONSTRUCTORS_&_ACCESOS-------------------------
	/**
	 * TODO:
	 * Mejorar AttackArea implementation, permitiendo pasar como parámetro:
	 * - al mob en lugar de sus coordenadas
	 * - duración del área
	 * - animSprite para áreas de larga duración
	 * Crear variable booleana toRemove que será true cuando el contador de updates del attackArea sea
	 * igual a la duración
	 */
	public AttackArea(int x, int y, int width, int height, int damage) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.damage = damage;
		attacks.add(this);
	}

	/**
	 * * Constructor para guardar los parámetros fijos de un attackArea. Este contructor no añade el área a la
	 * lista de áreas. Para agregar el ataque a la lista hay que pasar como parámetro al constructor
	 * {@link #AttackArea(AttackArea)} una instancia creada por este constructor.
	 * 
	 * @param attacker es el atacante
	 * @param xL es la x si el ataque es a la izquierda
	 * @param xR es la x si el ataque es a la derecha
	 * @param y es la y
	 * @param width es el ancho del ataque
	 * @param height es el alto del ataque
	 * @param damage es el daño del ataque
	 * @param duration es la duración en updates o frames del ataque. NOT IMPLEMENTED
	 */
	public AttackArea(Mob attacker, int xL, int xR, int y, int width, int height, int damage, int duration) {

		this.mob = attacker;
		if (mob instanceof Player) byEnemy = false;
		this.xL = xL;
		this.xR = xR;
		this.y = y;
		this.width = width;
		this.height = height;
		this.damage = damage;
		this.duration = duration;
	}

	public AttackArea(AttackArea area) {
		if (area.getMob() instanceof Player) byEnemy = false;
		this.x = (int) (area.getMob().getDir() == Mob.Direction.LEFT ? area.getMob().getPosition().x
				+ area.getxL()
				: area.getMob().getPosition().x + area.getxR());
		this.y = area.getY();
		this.width = area.getWidth();
		this.height = area.getHeight();
		this.damage = area.getDamage();
		this.duration = area.getDuration();
		attacks.add(this);
	}

	public boolean isByEnemy() {
		return byEnemy;
	}

	public void setByEnemy(boolean byEnemy) {
		this.byEnemy = byEnemy;
	}

	public Mob getMob() {
		return mob;
	}

	public int getxL() {
		return xL;
	}

	public int getxR() {
		return xR;
	}

	public int getX() {
		return x;
	}

	public int getWidth() {
		return width;
	}

	public int getY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDuration() {
		return duration;
	}

	public static List<AttackArea> getAttacks() {
		return attacks;
	}

	public void remove() {
		attacks.remove(this);
	}

	public boolean contains(Mob mob) {
		int mobX = (int) (mob.getMobX() + mob.getPosition().x);
		int mobW = (int) (mobX + mob.getMobWidth());

		// if (mob instanceof Player) System.out.println(mobX + "\t" + mobW + "\t|\t" + x + "\t" + (x +
		// width));

		return ((x <= mobX && x + width >= mobX) || (x >= mobX && x + width <= mobW) || (x <= mobW && x
				+ width >= mobW));
	}

}
