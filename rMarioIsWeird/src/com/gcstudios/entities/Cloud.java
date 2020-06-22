package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;

public class Cloud extends Entity{
	

	private BufferedImage sprite1[];
	
	public Cloud(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		sprite1 = new BufferedImage[3];
		for(int i = 0; i < 3; i++ ) {
			sprite1[i] = Game.spritesheet.getSprite(0 + (i *16), 96,16, 16);
		}
	}

	public void tick() {
		
	
	}

	public void render(Graphics g) {
		g.drawImage(sprite1[1],getX() - Camera.x, getY() - Camera.y, null);
		//System.out.println("CLOUD RENDER");
	}
}
