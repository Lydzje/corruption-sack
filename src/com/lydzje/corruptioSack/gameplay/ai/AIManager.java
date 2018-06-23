package com.lydzje.corruptioSack.gameplay.ai;

import java.util.ArrayList;
import java.util.List;

import com.lydzje.corruptioSack.entities.mobs.Mob;

public class AIManager {
	public static List<AIManager> implementedAIs = new ArrayList<AIManager>();

	protected Mob mob;

	public AIManager() {

	}

	protected Mob getMob() {
		return mob;
	}

	protected void perform() {

	}

	public void update() {
		/*
		 * for (int i = 0; i < implementedAIs.size(); i++) {
		 * AIManager ai = implementedAIs.get(i);
		 * 
		 * if (ai.getMob().isDead()) {
		 * implementedAIs.remove(this);
		 * i--;
		 * }else ai.perform();
		 * }
		 */
		for (AIManager implementedAI : implementedAIs)
			if (!implementedAI.getMob().isDead()) implementedAI.perform();
	}

}
