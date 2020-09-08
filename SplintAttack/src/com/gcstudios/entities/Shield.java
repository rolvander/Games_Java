package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Shield extends Entity {

	private int time = 0;
	private int maxTime = 60 * 14;

	public static boolean showShield = false;

	public BufferedImage[] spriteShield;
	private int frames = 0;
	private int maxFrames = 12;
	private int maxIndex = 2;
	private int index = 0;

	public Shield(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

		spriteShield = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			spriteShield[i] = Game.spritesheet.getSprite(127, 23 + (i * 33), 32, 33);
		}

	}

	public void tick() {

		x = Game.bird.getX() - 4;
		y = Game.bird.getY() - 7;

		time++;
		if (time == maxTime) {
			Game.entities.remove(this);
			showShield = false;
			time = 0;

		}
		for (int i = 0; i < Game.inimigos.size(); i++) {// COLIDINDO INIMIGO 1
			Entity e = Game.inimigos.get(i);
			if (e instanceof Enemy) {
				if (Entity.isColidding(e, this)) {
					Game.inimigos.remove(e);
					Game.player.pedras += 3;
					Game.player.pontos += 2;
					return;
				}
			}
		}

		for (int i = 0; i < Game.inimigos.size(); i++) {// COLIDINDO INIMIGO 2
			Entity e = Game.inimigos.get(i);
			if (e instanceof Enemy2) {
				if (Entity.isColidding(e, this)) {
					Game.inimigos.remove(e);
					Game.player.pedras += 3;
					Game.player.pontos += 2;
					return;
				}
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

	}

	public void render(Graphics g) {
		g.drawImage(spriteShield[index], this.getX(), this.getY(), null);
	}

}
