package com.gcstudios.entities;

import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class ShieldPack extends Entity {

	public ShieldPack(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

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
				//System.out.println("COLIDIU");
				Game.bullets.remove(e);
				Game.entities.remove(this);
				Shield.showShield = true;
				Shield shield = new Shield(x, y, 32, 33, 1, Game.spritesheet.getSprite(127, 23, 32, 33));
				Game.entities.add(shield);
				Sound.shield.play();

			}
		}

	}

}
