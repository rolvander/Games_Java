package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class Player extends Entity {
	public boolean right, left;
	public int pedras = 200, flechas = 0;
	public boolean countFire = true;
	public boolean ativateArrow = false;
	public boolean fire, fireArrow;
	public int life = 5;
	public int pontos = 0;
	public BufferedImage[] sprite1;
	private int frames = 0;
	private int maxFrames = 8;
	private int maxIndex = 1;
	private int index = 0;

	public Player(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprite1 = new BufferedImage[2];
		for (int i = 0; i < 2; i++) {
			sprite1[i] = Game.spritesheet.getSprite(0 + (i * 16), 0, 16, 16);
		}

	}

	public void tick() {
		depth = 1;
		if (right) {
			Sound.rigth.play();
			x += speed;

		}
		if (left) {
			Sound.left.play();
			x -= speed;

		}
		if (x > Game.WIDTH - 16) {
			x = Game.WIDTH - 16;
		}
		if (x <= 0) {
			x = 0;
		}

		// PEDRAS
		if (pedras > 0) {
			if (fire) {
				fire = false;
				Sound.pedra.play();
				BulletShoot bullet1 = new BulletShoot(this.getX() + 4, this.getY() + 2, 3, 3, 4, null);
				Game.bullets.add(bullet1);
				pedras -= 1;

			}
		}
		// FLECHAS
		if (flechas > 0) {
			if (fireArrow) {
				fireArrow = false;
				BulletShoot2 bullet2 = new BulletShoot2(this.getX() + 2, this.getY() + 3, 7, 9, 4,
						Game.spritesheet.getSprite(125, 0, 7, 8));
				Game.bullets2.add(bullet2);
				Sound.firearrow.play();
				flechas -= 1;

			}
		}

		// GAMEOVER
		if (life <= 0) {
			Game.gamestate = "GAMEOVER";
			life = 0;
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
		g.drawImage(sprite1[index], this.getX(), this.getY(), null);
	}
}
