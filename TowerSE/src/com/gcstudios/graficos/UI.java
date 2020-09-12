package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;

public class UI {

	public static BufferedImage HEART = Game.spritesheet.getSprite(0, 16, 6, 6);
	public static BufferedImage COIN = Game.spritesheet.getSprite(6, 16, 6, 6);
	
	public void render(Graphics g) {
		
		for(int i = 0; i < (int)(Game.life); i++ ) {
			g.drawImage(HEART, 10 + (i * 26),8, 28, 28, null);
			
		}
		g.setColor(new Color(255,255,255));
		g.setFont(Game.pixel);
		g.drawImage(COIN,(Game.WIDTH * Game.SCALE - 100), 10, 24,24, null);
		g.drawString(""+Game.money, (Game.WIDTH * Game.SCALE - 70), 30 );
		
		
	}
	
}
