package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;

public class Enemy extends Entity{

	public int frames = 0, maxFrames = 8;
	public int index = 0, maxIndex = 3;

	public boolean rigth = true, left = false;
	public int dir = 1;
	public int life = 1;
	 
	public BufferedImage[] sprite_right;
	public BufferedImage[] sprite_left;
	
	
	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		sprite_right = new BufferedImage[3];
		sprite_left = new BufferedImage[3];
		
		
		for(int i = 0; i < 3; i++) {
			sprite_right[i] = Game.spritesheet.getSprite(32 + (i * 16), 48, 16, 16);
		}
		for(int i = 0; i < 3; i++) {
			sprite_left[i] = Game.spritesheet.getSprite(32 + (i * 16), 32, 16, 16);
		}
		
	}
	public void tick() {
		
		if(World.isFree((int)x, (int)(y+1))) {
			y+=1;
		}
		
		
		if(rigth && World.isFree((int)(x+speed), (int)y)) {
			x+=speed;
			dir = 1;
			if(World.isFree((int)(x+16), (int) y+1)) {
				rigth = false;
				left = true;
		}
		}else {
			rigth = false;
			left = true;
		}
		if(left && World.isFree((int)(x-speed), (int)y)) {
			x-=speed;
			dir = -1;
			if(World.isFree((int)(x-16), (int) y+1)) {
				rigth = true;
				left = false;
			}
		}else {
			rigth = true;
			left = false;
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
		
		if(dir == 1) {
			g.drawImage(sprite_right[index], getX() - Camera.x, getY() - Camera.y, null);
		}else if (dir == -1) {
			g.drawImage(sprite_left[index], getX() - Camera.x, getY() - Camera.y, null);
		}
		
	}


}
