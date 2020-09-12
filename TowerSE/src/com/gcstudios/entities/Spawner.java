package com.gcstudios.entities;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Spawner extends Entity {

	
	private int timer = 60;
	private int curTimer = 0;
	private int curTimer2 = 0;
	private int timer2 = 140;
	
	public Spawner(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
	}
	
	public void tick() {
		
		curTimer++;
		if(curTimer == timer){
			curTimer = 0;
			Enemy enemy = new Enemy(x, y, 16, 16, Entity.rand.nextDouble(), Entity.ENEMY1);
			Game.entities.add(enemy);
		}
			if(Game.towerCount > 10) {//Verificação para entrada do Enemy2
				curTimer2++;
				if(curTimer2 == timer2){
					curTimer2 = 0;
					Enemy2 enemy2 = new Enemy2(x, y, 16, 16, Entity.rand.nextDouble(), ENEMY2);
					Game.entities.add(enemy2);
				}
			}
	}
	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect((int)x, (int)y, width, height);
	}

}
