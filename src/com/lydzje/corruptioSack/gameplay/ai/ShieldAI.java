package com.lydzje.corruptioSack.gameplay.ai;

import com.lydzje.corruptioSack.Game;
import com.lydzje.corruptioSack.entities.mobs.Mob;
import com.lydzje.corruptioSack.entities.mobs.Mob.Direction;
import com.lydzje.corruptioSack.entities.mobs.Player;
import com.lydzje.corruptioSack.levels.Level;

public class ShieldAI extends AIManager {

	public ShieldAI(Mob mob) {
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
				if (player.getPosition().x >= mob.getPosition().x - 200
						&& player.getPosition().x <= mob.getPosition().x + 200) {

					mob.setHasTarget(true);
					mob.setTarget(player);
				}
				else {
					mob.setHasTarget(false);
					mob.setTarget(null);
				}
			}

			if (mob.getTarget() != null) mob.moveTo();
			if (mob.isInRange() && mob.getAttackRate() == 0) {
				if (time % 20 == 0 && Game.random.nextBoolean()) {
					mob.attack();
					time = 1;
				} else if (Math.abs(mob.getPosition().x - mob.getTarget().getPosition().x) < 10) {
					mob.setDir(mob.getDir() == Direction.LEFT ? Direction.RIGHT : Direction.LEFT);
					mob.setWalking(true);

				}
			}
		}
	}

	protected void perform() {
		if (!mob.isAttacking()) simpleFighter();
	}

}
