package com.lydzje.corruptioSack.gameplay.ai;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.entities.mobs.Mob;
import com.lydzje.corruptioSack.entities.mobs.Mob.Direction;
import com.lydzje.corruptioSack.entities.mobs.Player;
import com.lydzje.corruptioSack.levels.Level;

public class IceAI extends AIManager {

	public IceAI(Mob mob) {
		this.mob = mob;
		implementedAIs.add(this);
	}

	private int time = 1;

	private void simpleFighter() {

		time++;

		if (Level.players.isEmpty()) {
			mob.setHasTarget(false);
			mob.setTarget(null);
		}
		else {

			for (Player player : Level.players) {
				if (player.getPosition().x >= mob.getPosition().x - 220
						&& player.getPosition().x <= mob.getPosition().x + 220) {

					mob.setHasTarget(true);
					mob.setTarget(player);
				}
				else {
					mob.setHasTarget(false);
					mob.setTarget(null);
				}
			}

			if (mob.getTarget() != null) {
				if (Math.abs(mob.getPosition().x - mob.getTarget().getPosition().x) < 120
						&& !mob.isNextToBound()) {

					if (Game.random.nextBoolean() && mob.getAttackRate() == 0
							&& Game.random.nextInt(100) < 2) {
						mob.setDir(mob.getTarget().getPosition().x <= mob.getPosition().x ? Direction.LEFT
								: Direction.RIGHT);
						mob.attack();
					}

					if (time % 20 == 0) {
						mob.setDir(mob.getTarget().getPosition().x <= mob.getPosition().x ? Direction.RIGHT
								: Direction.LEFT);
						mob.setWalking(true);
					}

				} else {
					mob.moveTo();
					if (mob.isInRange() && mob.getAttackRate() == 0) {
						if (time % 20 == 0 && Game.random.nextBoolean()) {
							time = 1;
							if (Game.random.nextBoolean()) mob.attack();
						}
					}
				}
			}

		}
	}

	protected void perform() {
		if (!mob.isAttacking()) simpleFighter();
	}

}