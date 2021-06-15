package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Explosion extends Entity {

	private int frames = 0;
	private int maxFrames = 4;
	private int index = 0;
	private int maxIndex = 2;

	BufferedImage[] spritesExplosion;

	public Explosion(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

		spritesExplosion = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			spritesExplosion[i] = Game.spritesheet.getSprite(48 + (i * 16), 46, 16, 16);
		}
	}

	public void tick() {
		frames++;
		if (frames == maxFrames) {
			frames = 0;
			index++;
			if (index > maxIndex) {
				Game.entities.remove(this);
				return;
			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(spritesExplosion[index], this.getX(), this.getY(), null);
	}

}
