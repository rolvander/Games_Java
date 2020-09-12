package com.gcstudios.entities;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;


public class Player extends Entity{

	public boolean attack = false;
	public boolean attack2 = false;
	public int xTarget, yTarget;
	public int xTarget2, yTarget2;
	
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	}
	
	public void tick(){
		
		//ATAQUE AO INIMIGO 1
		Enemy enemy = null;
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
				if(e instanceof Enemy) {
					int xEnemy = e.getX();
					int yEnemy = e.getY();
					if(Entity.calculateDistance(this.getX(), this.getY(), xEnemy, yEnemy) < 28) {
						
						enemy = (Enemy)e;
				}
			}
		}
		
		if(enemy != null) {
			attack = true;
			xTarget = enemy.getX();
			yTarget = enemy.getY();
			if(Entity.rand.nextInt(100) < 40) {
				enemy.vida_enemy1-=Entity.rand.nextDouble();
			}
			
		}else {
			attack = false;
		}
		//ATAQUE AO INIMIGO 2
		if(Game.towerCount > 10) {//Caso o número de torres passe de 10
			Enemy2 enemy2 = null;
			for(int j = 0; j < Game.entities.size(); j++) {
				Entity e2 = Game.entities.get(j);
				if(e2 instanceof Enemy2) {
					//INIMIGO 2
					int xEnemy2 = e2.getX();
					int yEnemy2 = e2.getY();
					if(Entity.calculateDistance(this.getX(), this.getY(), xEnemy2, yEnemy2) < 32) {
						enemy2 = (Enemy2)e2;
					}	
				}
			}
		
		if(enemy2 != null) {
			attack2 = true;
			xTarget2 = enemy2.getX();
			yTarget2 = enemy2.getY();
			if(Entity.rand.nextInt(100) < 10) {
				enemy2.vida_enemy2-=Entity.rand.nextDouble();
			}
		}else {
			attack2 = false;
		}
		}//Contagem de Torres para a entrada do Inimigo 2
		

		
	}
	public void render(Graphics g) {
		super.render(g);
		if(attack) {
			g.setColor(new Color(6,151,255));
			g.drawLine((int)getX() + 7, (int)getY() +2, xTarget + 7, yTarget +7);
		}
		if(attack2) {
			g.setColor(new Color(6,151,255));
			g.drawLine((int)getX() + 7, (int)getY() +2, xTarget2 + 7, yTarget2 +7);
		}
	}
}
