package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;

public class BulletShoot extends Entity{

	private int life = 150,curLife = 0;
	
	public BulletShoot(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}

		public void tick() {
		
			//System.out.println("Posição da bala: "+ y);
			y--;
			
			curLife++;
			if(curLife == life) {
				Game.bullets.remove(this);
				return;
			}
		}
		
		public void render(Graphics g) {
			g.setColor(new Color(255, 225,0));
			g.fillOval(this.getX() - Camera.x,this.getY() - Camera.y,3,3);
		}
	
}
