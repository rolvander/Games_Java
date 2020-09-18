package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class LifePack extends Entity {

	public BufferedImage[] sprite1;
	private int frames = 0;
	private int maxFrames = 12;
	private int maxIndex = 1;
	private int index = 0;
	public static boolean showPack = false;

	public LifePack(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprite1 = new BufferedImage[2];
		for(int i = 0; i < 2; i++) {
			sprite1[i] = Game.spritesheet.getSprite(96 + (i * 14), 46, 14, 12);
		}
	}

	public void tick() {
		y += speed;

		if (y > Game.HEIGHT - 5) {
			Game.entities.remove(this);
			//System.out.println("REMOVIDO");
		}

		for (int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
			if (Entity.isColidding(this, e)) {
				//System.out.println("COLIDINDO COM PEDRAS");
				Game.bird.lifeBird+=35;
				Sound.life.play();
				Game.entities.remove(this);
				Game.bullets.remove(i);
				return;
			}
		}

		for (int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if (e instanceof Player) {
				if(Entity.isColidding(this, e) && this.y == Game.player.y - 10) {
						//System.out.println("COLIDINDO");
						Game.bird.lifeBird+=10;
						Game.entities.remove(this);
						return;
					
				}
				
			}

		}
		
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(sprite1[index], this.getX(), this.getY(), null);
	}

}
