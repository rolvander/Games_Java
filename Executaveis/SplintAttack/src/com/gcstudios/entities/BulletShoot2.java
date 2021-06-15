package com.gcstudios.entities;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;

public class BulletShoot2 extends Entity {

	private int life = 150, curLife = 0;
	public BufferedImage[] spriteArrow;
	private int frames = 0;
	private int maxFrames = 10;
	private int maxIndex = 1;
	private int index = 0;

	public BulletShoot2(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

		spriteArrow = new BufferedImage[2];
		for (int i = 0; i < 2; i++) {
			spriteArrow[i] = Game.spritesheet.getSprite(125 + (i * 7), 0, 7, 8);
		}
	}

	public void tick() {

		y -= speed;

		curLife++;
		if (curLife == life) {
			Game.bullets2.remove(this);
			return;
		}

		frames++;
		if (frames == maxFrames) {
			frames = 0;
			index++;
			if (index > maxIndex) {
				index = 0;
			}
		}
	}

	public void render(Graphics g) {

		if (Game.player.ativateArrow) {
			g.drawImage(spriteArrow[index], this.getX(), this.getY(), null);
		}
	}

}
