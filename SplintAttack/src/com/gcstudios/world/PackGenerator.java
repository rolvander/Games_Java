package com.gcstudios.world;

import java.awt.Graphics;

import com.gcstudios.entities.ArrowPack;
import com.gcstudios.entities.Entity;
import com.gcstudios.entities.LifePack;
import com.gcstudios.entities.ShieldPack;
import com.gcstudios.main.Game;

public class PackGenerator {
	
	private int timeA = 0;
	private int timeL = 0;
	private int timeS = 0;

	private int interval1 = Entity.rand.nextInt(23 - 17) + 17;
	private int maxTimeArrow = 60 * interval1;
	private int interval2 = Entity.rand.nextInt(45 - 35) + 35;
	private int maxTimeLife = 60 * interval2;
	private int interval3 = Entity.rand.nextInt(50 - 37) + 40;
	private int maxTimeShield = 60 * interval3;

	public void tick() {

		timeA++;
		if (timeA == maxTimeArrow) {
			int posicao = Entity.rand.nextInt(100 - 5) + 5;
			ArrowPack arrowpack = new ArrowPack(posicao, 0, 14, 14, 1, Game.spritesheet.getSprite(96, 32, 14, 14));
			Game.entities.add(arrowpack);
			timeA = 0;

		}

		timeL++;
		if (timeL == maxTimeLife) {
			int posicao = Entity.rand.nextInt(100 - 5) + 5;
			LifePack lifePack = new LifePack(posicao, 0, 14, 14, 1, Game.spritesheet.getSprite(96, 46, 14, 14));
			Game.entities.add(lifePack);
			timeL = 0;
		}

		timeS++;
		if (timeS == maxTimeShield) {
			int posicao = Entity.rand.nextInt(100 - 5) + 5;
			ShieldPack shield = new ShieldPack(posicao, 0, 15, 15, 1, Game.spritesheet.getSprite(96, 60, 15, 15));
			Game.entities.add(shield);
			timeS = 0;
		}

	}

	public void render(Graphics g) {

	}

}
