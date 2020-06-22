package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Tubo extends Entity{

	public Tubo(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
	}
	
	public void tick() {
		depth = 0;
		x--;
		if(x+width < 0) {
			//System.out.println("Destruído");
			//Game.score+= 0.5;
			Game.entities.remove(this);
			return;
		}
		if(x+ width == Game.player.x) {
			//System.out.println("Marcou ponto");
			Game.score+= 0.5;
			return;
		}
	}
	
	public void render(Graphics g) {
		
		//g.drawImage(img, x, y, null);
		//g.drawImage(Entity.TUBO1, (int)x, (int)y, null);
		if(sprite != null){
			g.drawImage(sprite, this.getX(), this.getY(), width, height, null);
			//g.drawImage(sprite2, this.getX(), this.getY(), width, height, null);
		}else {
			g.setColor(new Color(0,200,0));
			g.fillRect((int)x, (int)y, width, height);
		}
		
		//super.render(g);
	}

}
