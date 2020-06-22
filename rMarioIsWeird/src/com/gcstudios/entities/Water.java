package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;

public class Water extends Entity{
	
	public BufferedImage sprite1[];
	public int frames, index = 0;
	public int maxFrames = 9;
	public int maxIndex = 3;
	
	public Water(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		sprite1 = new BufferedImage[3];
		for(int i = 0; i < 3; i++) {
			sprite1[i] = Game.spritesheet.getSprite(48+ (i * 16), 80, 16, 16);
		}

	}
	public void tick() {
		frames++;
		if(frames == maxFrames) {
			index++;
			frames = 0;
			if(index == maxIndex) {
				index = 0;
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite1[index],getX() - Camera.x, getY() - Camera.y, null);
		//System.out.println("WATER RENDER");
	}

}
