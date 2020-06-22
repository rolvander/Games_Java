package com.gcstudios.world;

import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Tubo;
import com.gcstudios.main.Game;

public class TuboGenerator {
	
	public int time = 0;
	public int targetTime = 60;
	
	public void tick() {
		
		time++;
		if(time == 60) {
			int altura1 = Entity.rand.nextInt(70 - 30)+ 30;
			Tubo tubo1 = new Tubo(Game.WIDTH, 0, 20, altura1, 1, Game.spritesheet.getSprite(0, 16, 16, 16));
			
			
			int altura2 = Entity.rand.nextInt(70 - 30)+ 30;
			Tubo tubo2 = new Tubo(Game.WIDTH, Game.HEIGHT - altura2, 20, altura2, 1,Game.spritesheet.getSprite(0, 32, 16, 16));
		
			Game.entities.add(tubo1);
			Game.entities.add(tubo2);
			time = 0;
		
		}
	}

}
