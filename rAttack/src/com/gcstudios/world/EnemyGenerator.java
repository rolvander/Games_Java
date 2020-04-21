package com.gcstudios.world;

import java.awt.Graphics;

import com.gcstudios.entities.Enemy;
import com.gcstudios.entities.Enemy2;
import com.gcstudios.entities.Entity;
import com.gcstudios.main.Game;

public class EnemyGenerator {
	public int time, time2, time3  = 0;
	public int targetTime = 60;
	
	public void tick() {
		time++;
		time2++;
		time3++;
		if(time == 60) {
			int posicao1 = Entity.rand.nextInt(80 - 20) + 20;
			Enemy enemy1 = new Enemy(posicao1, 0, 16, 16, 1, Game.spritesheet.getSprite(0, 16, 16, 16));
			
			Game.entities.add(enemy1);		
			time = 0;
		}
		if(time2 == 90) {
			int posicao2 = Entity.rand.nextInt(120 - 1) + 30;
			Enemy enemy2 = new Enemy(posicao2, 0, 16, 16, 1, Game.spritesheet.getSprite(0, 32, 16, 16));
			
			Game.entities.add(enemy2);
			time2 = 0;
		}
		if(time3 == 120) {
			int posicao3 = Entity.rand.nextInt(50 - 30) + 30;
			Enemy2 enemy3 = new Enemy2(posicao3, 0, 16, 16, 1, Game.spritesheet.getSprite(0, 48, 16, 16));
		
			Game.entities.add(enemy3);
			time3 = 0;
		}
		
		
	}
	public void render(Graphics g) {
		
	}
}
