package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;

public class Flag extends Entity{

	public BufferedImage sprite1[];
	
	public int frames, index = 0;
	public int maxFrames = 5;
	public int maxIndex = 3;
	
	public Flag(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	
		sprite1 = new BufferedImage[3];
		for(int i = 0; i < 3; i++) {
			sprite1[i] = Game.spritesheet.getSprite(117 + (i * 10), 16, 10, 10);
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
		//System.out.println("Bandeira Render NÃO APARECENDO");
		if(Player.currentCoin > (Player.maxCoins * 0.75)) {
			Player.showFlag = true;
				//System.out.println("Bandeira Render");
				g.drawImage(sprite1[index],getX() - Camera.x, getY() - Camera.y, null);
			
	}
		
		

	}

}
