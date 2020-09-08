package com.gcstudios.world;

import java.awt.Graphics;
import com.gcstudios.entities.Enemy;
import com.gcstudios.entities.Enemy2;
import com.gcstudios.entities.Entity;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class EnemyGenerator {
	public int timeE1, time11, time1, timeE2 = 0;
	public int maxTimeE = 60 * 4;
	private int maxTimeE2 = 60 * 2;
	public int maxTime1 = 60 * 1;
	public int maxtime11 = 60 * 1;

	public void tick() {

		if (Game.levelGame == 1) {//INIMIGOS LEVEL 1
			time1++;

			if (time1 == maxTime1) {
				int posicao2 = Entity.rand.nextInt(120 - 2) + 2;
				Enemy2 enemy2 = new Enemy2(posicao2, 0, 16, 16, 0.7, Game.spritesheet.getSprite(0, 64, 16, 16));
				Game.inimigos.add(enemy2);
				time1 = 0;
			}
		} else {
			time11++;

			if (time11 == maxtime11) {
				int posicao2 = Entity.rand.nextInt(120 - 2) + 2;
				Enemy2 enemy2 = new Enemy2(posicao2, 0, 16, 16, 0.9, Game.spritesheet.getSprite(0, 64, 16, 16));
				Game.inimigos.add(enemy2);
				time11 = 0;
			}
		}
		if (Game.levelGame == 3) {// Verificação de level no UI
			timeE1++;
			if (timeE1 == maxTimeE) {
				int posicao1 = Entity.rand.nextInt(110 - 2) + 2;
				Enemy enemyX = new Enemy(posicao1, 0, 16, 14, 1.1, Game.spritesheet.getSprite(0, 46, 16, 14));
				Sound.eagle.play();
				Game.inimigos.add(enemyX);
				timeE1 = 0;
			}
		}else if(Game.levelGame == 4) {
			timeE2++;
			if (timeE2 == maxTimeE2) {
				int posicao1 = Entity.rand.nextInt(110 - 2) + 2;
				Enemy enemyX = new Enemy(posicao1, 0, 16, 14, 1.4, Game.spritesheet.getSprite(0, 46, 16, 14));
				Sound.eagle4.play();
				Game.inimigos.add(enemyX);
				timeE2 = 0;
			}
		}

	}

	public void render(Graphics g) {

	}
}
