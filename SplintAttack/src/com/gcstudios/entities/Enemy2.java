package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.graficos.UI;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;


public class Enemy2 extends Entity {
	public int frames = 0, maxFrames = 8;
	public int index = 0, maxIndex = 2;
	private int enemyLife = 10;
	private double dx;

	public BufferedImage[] sprite1;

	public Enemy2(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprite1 = new BufferedImage[3];

		int ee = Entity.rand.nextInt(3);
		for (int i = 0; i < 3; i++) {
			sprite1[i] = Game.spritesheet.getSprite(0 + (i * 16), 80 + (16 * ee), 16, 16);
		}
		double radius = Math.atan2(Game.bird.y - 11, Game.bird.x - 5);//CALCULO DO RAIO DE DISTANCIA
		this.dx = Math.cos(radius);
		
	}

	public void tick() {
		// MOVIMENTO DOS INIMIGOS
		y += speed;

		if(UI.seconds > 25 && Game.levelGame == 1) {
			if (Game.bird.x < this.x) {
				x -= (dx * (speed + 1));
			}
		}
		if(UI.seconds > 45 && Game.levelGame == 1) {
			if (Game.bird.x > this.x) {
				x += (dx * (speed + 1));
			}
		}

		if (Game.levelGame >= 2) {// Level 2 em ação
			if (Game.bird.x < this.x) {
				x -= (dx * (speed + 1));
			} else {
				x += (dx * (speed + 1));
			}
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
				int dan = Entity.rand.nextInt(6 - 2) + 2;
				enemyLife -= dan;
				Sound.hurt1.play();
				Game.bullets.remove(i);
				return;
			}
		}
		for (int i = 0; i < Game.bullets2.size(); i++) {// COLIDINDO COM A FECHA
			Entity e = Game.bullets2.get(i);
			if (isColidding(this, e)) {
				enemyLife -= 10;
				Sound.hurtA.play();
				Game.bullets2.remove(i);
				return;
			}
		}

		if (enemyLife <= 0) {
			Game.inimigos.remove(this);
			Explosion explosion = new Explosion(x, y, 16, 16, 0, null);
			Game.entities.add(explosion);
			int p = Entity.rand.nextInt(11 - 8) + 8;
			Game.player.pontos += p;
			int pedras = Entity.rand.nextInt(6 - 2) + 2;
			Game.player.pedras += pedras;
			return;
		}

	}

	public void render(Graphics g) {
		g.drawImage(sprite1[index], this.getX(), this.getY(), null);

		g.setColor(new Color(0, 180, 0));
		g.drawRoundRect((int) x + 2, (int) y - 2, 11, 3, 5, 5);
		g.fillRoundRect((int) x + 2, (int) y - 2, enemyLife + 1, 3, 5, 5);

	}

}
