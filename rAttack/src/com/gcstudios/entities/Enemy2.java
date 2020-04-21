package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class Enemy2 extends Entity{
	public int frames = 0, maxFrames = 8;
	public int index = 0, maxIndex = 2;
	 
	public BufferedImage[] sprite1;
	
	public Enemy2(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprite1 = new BufferedImage[3];
		
		
		for(int i = 0; i < 3; i++) {
			sprite1[i] = Game.spritesheet.getSprite(0 + (i * 16), 48, 16, 16);
		}
		
		
	}
	
	public void tick() {
		y+=1.4;
		
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
					Sound.hurtEffect2.play();
					Game.player.pontos+=15;
					return;
				}
		}
		
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index == maxIndex) {
				index = 0;
			}		
		}
		
	}
	public void render(Graphics g) {
		if(sprite != null){
			g.drawImage(sprite1[index], this.getX(), this.getY(), width, height, null);
			//g.drawImage(sprite2, this.getX(), this.getY(), width, height, null);
		}else {
			g.setColor(new Color(0,200,0));
			g.fillRect((int)x, (int)y, width, height);
		}
	}

}
