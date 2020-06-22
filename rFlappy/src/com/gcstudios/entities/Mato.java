package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Mato extends Entity {

	public Mato(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
	}

	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,0,0));
		g.fillRect(0, 0,Game.WIDTH*Game.SCALE,Game.HEIGHT*Game.SCALE);
		if(sprite != null){
			g2.drawImage(sprite, this.getX(), this.getY(), width, height, null);
		}else {
			g.setColor(new Color(0,200,0));
			g.fillRect((int)x, (int)y, width, height);
		}
	}

}
