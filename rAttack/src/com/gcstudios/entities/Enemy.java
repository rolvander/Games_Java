package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class Enemy extends Entity{
	

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
	}
	
	public void tick() {
		y+=0.6;
		
		if(y > Game.HEIGHT) {

			Game.player.life--;
			Game.entities.remove(this);
			return;
		}
		
		for(int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
				if(Entity.isColidding(this,e)) {
					Game.entities.remove(this);
					Game.bullets.remove(i);
					Sound.hurtEffect.play();
					Game.player.pontos+=10;
					return;
				}
		}
		
	}

	public void render(Graphics g) {
		if(sprite != null){
			g.drawImage(sprite, this.getX(), this.getY(), width, height, null);
			//g.drawImage(sprite2, this.getX(), this.getY(), width, height, null);
		}else {
			g.setColor(new Color(0,200,0));
			g.fillRect((int)x, (int)y, width, height);
		}
	}
}
