package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class Bird extends Entity {

	public BufferedImage[] sprite1;
	private int frames = 0;
	private int maxFrames = 8;
	private int maxIndex = 2;
	private int index = 0;
	public int lifeBird = 200;
	public boolean isdamage = false;

	public Bird(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprite1 = new BufferedImage[3];

		for (int i = 0; i < 3; i++) {
			sprite1[i] = Game.spritesheet.getSprite(0 + (i * 23), 16, 23, 17);
		}
	}

	public void tick() {

		depth = 1;

		int speed1 = Entity.rand.nextInt(2);
		x += speed1;
		frames++;
		if (frames == maxFrames) {
			frames = 0;
			index++;
			if (index > maxIndex) {
				index = 0;
			}
		}

		if (Game.ninho.y == 70) {// VERIFICACAO DE WIN
			x = Game.ninho.x;
			y -= 1;

		}

		if (this.x > Game.WIDTH) {
			this.x = 0;
		}
		// COLISAO COM INIMIGOS
		for (int i = 0; i < Game.inimigos.size(); i++) {
			Entity e = Game.inimigos.get(i);
			if (e instanceof Enemy) {
				if (Entity.isColidding(this, e)) {

					isdamage = true;
					Sound.dano.play();
					int dano = Entity.rand.nextInt(2 - 1) + 1;
					lifeBird -= dano;
					return;
				} else {
					isdamage = false;
				}
			}
		}
		for (int i = 0; i < Game.inimigos.size(); i++) {
			Entity e = Game.inimigos.get(i);
			if (e instanceof Enemy2) {
				if (Entity.isColidding(this, e)) {
					lifeBird--;
					isdamage = true;
					Sound.dano.play();
					return;
				} else {
					isdamage = false;
				}
			}
		}
		// GAMEOVER
		if (lifeBird == 30) {
			Sound.low.play();
		}

		if (lifeBird <= 0) {	
			Game.gamestate = "GAMEOVER";
			return;
		}

		if (lifeBird >= 200) {
			lifeBird = 200;
		}

	}

	public void render(Graphics g) {

		if (!isdamage) {
			g.drawImage(sprite1[index], this.getX(), this.getY(), null);

			g.setColor(new Color(150, 240, 240));
			g.fillRoundRect((int) x - 3, (int) y + 17, (200 / 7), 5, 6, 6);

			g.setColor(new Color(0, 200, 200));
			g.fillRoundRect((int) x - 3, (int) y + 17, (lifeBird / 7), 5, 6, 6);
			g.drawRoundRect((int) x - 3, (int) y + 17, (200 / 7), 5, 6, 6);
		} else {
			g.drawImage(Entity.ENEMYDANO, this.getX(), this.getY(), null);

			g.setColor(new Color(150, 240, 240));
			g.fillRoundRect((int) x - 3, (int) y + 17, (200 / 7), 5, 6, 6);

			g.setColor(new Color(0, 200, 200));
			g.fillRoundRect((int) x - 3, (int) y + 17, (lifeBird / 7), 5, 6, 6);
			g.drawRoundRect((int) x - 3, (int) y + 17, (200 / 7), 5, 6, 6);
		}

		if (lifeBird <= 25) {
			g.setColor(new Color(200, 0, 0));
			g.fillRoundRect((int) x - 3, (int) y + 17, (lifeBird / 7), 5, 6, 6);
			g.drawRoundRect((int) x - 3, (int) y + 17, (200 / 7), 5, 6, 6);
		}
		if (lifeBird <= 0) {
			g.setColor(new Color(250, 170, 170));
			g.fillRoundRect((int) x - 3, (int) y + 17, (200 / 7), 5, 6, 6);
			g.setColor(new Color(200, 0, 0));
			g.drawRoundRect((int) x - 3, (int) y + 17, (200 / 7), 5, 6, 6);
		}

	}

}
