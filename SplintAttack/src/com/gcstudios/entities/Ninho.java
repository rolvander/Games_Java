package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.graficos.UI;
import com.gcstudios.main.Game;


public class Ninho extends Entity {

	public BufferedImage[] sprite1;
	private int frames = 0;
	private int maxFrames = 16;
	private int maxIndex = 1;
	private int index = 0;
	public static boolean showNinho = false;

	public Ninho(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

		sprite1 = new BufferedImage[2];

		for (int i = 0; i < 2; i++) {
			sprite1[i] = Game.spritesheet.getSprite(48 + (i * 16), 64, 16, 16);
		}

	}

	public void tick() {
	
		if (UI.minutes >= 4) {// VERIFICAÇÃO DE WIN
			y += 1;
			
			if (y >= 70) {
				y = 70;
			}
			if(y == 20) {
				Game.gamestate = "WIN";//ESTADO DO JOGO COMO WIN, TIRANDO A REDENRIZAÇÃO DOS ENEMIES
			}

			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxIndex) {
					index = 0;
				}
			}
			if (y >= 200) {
				Game.entities.remove(this);
			}
		}

		for (int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if (e instanceof Bird) {
				if (Entity.isColidding(this, e)) {
					Game.win = 1;//LEVAR PARA A INTERFACE DE WIN NO UI
				}
			}

		}

	}

	public void render(Graphics g) {
		g.drawImage(sprite1[index], this.getX(), this.getY(), null);

	}

}
