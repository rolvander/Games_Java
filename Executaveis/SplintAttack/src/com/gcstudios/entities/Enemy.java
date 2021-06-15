package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class Enemy extends Entity {
	public BufferedImage[] sprite1;
	private int frames = 0;
	private int maxFrames = 8;
	private int maxIndex = 1;
	private int index = 0;
	private int enemyLife = 10;
	private double dx, dy;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprite1 = new BufferedImage[2];
		int ee = Entity.rand.nextInt(2);
		for (int i = 0; i < 2; i++) {
			sprite1[i] = Game.spritesheet.getSprite(0 + (i * 16), 46 + (ee * 14), 16, 14);
		}

		double radius = Math.atan2(Game.bird.y - 11, Game.bird.x - 5);
		this.dx = Math.cos(radius);
		this.dy = Math.sin(radius);
	}

	public void tick() {

		y += (dy * speed);
		int pos = Entity.rand.nextInt(50 - 20) + 20;
		if (Game.bird.x < (x - pos)) {
			x -= (dx * speed);
		}else {
			x += (dx * speed);
		}
		

		frames++;
		if (frames == maxFrames) {
			frames = 0;
			index++;
			if (index > maxIndex) {
				index = 0;
			}
		}

		if (y > Game.HEIGHT) {
			Game.inimigos.remove(this);
			return;
		}
		if (x > Game.WIDTH - 14) {
			x = Game.WIDTH - 14;
		}
		if(this.x == 5) {
			x = 5;
		}

		for (int i = 0; i < Game.bullets.size(); i++) {// COLIDINDO COM A PEDRA
			Entity e = Game.bullets.get(i);
			if (isColidding(this, e)) {
				int dan1 = Entity.rand.nextInt(4 - 1) + 1;
				enemyLife -= dan1;
				Sound.hurt2.play();
				Game.bullets.remove(i);
				return;
			}
		}
		for (int i = 0; i < Game.bullets2.size(); i++) {// COLIDINDO COM A FLECHA
			Entity e = Game.bullets2.get(i);
			if (isColidding(this, e)) {
				enemyLife -= 7;
				Sound.hurtA.play();
				Game.bullets2.remove(i);
				
				return;
			}
		}

		if (enemyLife <= 0) {
			Game.inimigos.remove(this);
			Explosion explosion = new Explosion(x, y, 16, 16, 0, null);
			Game.entities.add(explosion);
			int p = Entity.rand.nextInt(16 - 12) + 12;
			Game.player.pontos += p;
			Game.player.pedras += 3;
		}

	}

	public void render(Graphics g) {
		g.drawImage(sprite1[index], this.getX(), this.getY(), null);
		
		g.setColor(new Color(0, 180, 0));
		g.drawRoundRect((int) x + 2, (int) y - 3, 11, 3, 5, 5);
		g.fillRoundRect((int) x + 2, (int) y - 3, enemyLife + 1, 3, 5, 5);
	}
}
