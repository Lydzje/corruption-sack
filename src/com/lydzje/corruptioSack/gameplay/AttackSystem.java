package com.lydzje.corruptioSack.gameplay;

import java.util.List;

import com.lydzje.corruptioSack.entities.mobs.Mob;
import com.lydzje.corruptioSack.entities.mobs.Player;
import com.lydzje.corruptioSack.graphics.Screen;
import com.lydzje.corruptioSack.levels.Level;

public class AttackSystem {
	public static List<AttackArea> attacks;
	private List<Mob> mobs;

	public AttackSystem() {
		attacks = AttackArea.getAttacks();
		mobs = Level.mobs;
	}

	public void update() {

		for (int i = 0; i < attacks.size(); i++) {
			if (attacks.get(i).isByEnemy()) {
				for (Player player : Level.players) {
					if (attacks.get(i).contains(player)) {
						player.setHurt(true);
						player.setDamageTaken(attacks.get(i).getDamage());
						player.setDamageTaked(false);
					}
				}
			} else {
				for (Mob mob : mobs)
					if (attacks.get(i).contains(mob)) {
						mob.setHurt(true);
						mob.setDamageTaken(attacks.get(i).getDamage());
						mob.setDamageTaked(false);
					}
			}
			attacks.get(i).remove();
			i--;
		}

		for (int i = 0; i < Thunderbolt.thunderbolts.size(); i++)
			Thunderbolt.thunderbolts.get(i).update();

		for (int i = 0; i < Thunderbolt.thunderboltsAttackAreas.size(); i++) {
			Thunderbolt thunderbolt = Thunderbolt.thunderboltsAttackAreas.get(i);
			if (thunderbolt.getFrame() == 14) {
				for (Player player : Level.players)
					if (thunderbolt.contains(player)) {
						player.setHurt(true);
						player.setDamageTaken(thunderbolt.getDamage());
						player.setDamageTaked(false);
					}

				for (Mob mob : mobs)
					if (thunderbolt.contains(mob)) {
						mob.setHurt(true);
						mob.setDamageTaken(thunderbolt.getDamage());
						mob.setDamageTaked(false);
					}
				Thunderbolt.thunderboltsAttackAreas.remove(thunderbolt);
				i--;
			}

		}

	}

	public void render(Screen screen) {
		for (Thunderbolt thunderbolt : Thunderbolt.thunderbolts) {
			thunderbolt.render(screen);
		}
	}

}