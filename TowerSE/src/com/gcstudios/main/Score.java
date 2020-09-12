package com.gcstudios.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Score {
	
	public void tick() {
		
		Game.pontosTotal = (Game.pontos + Game.money);//Soma dos pontos
		if(Game.lose) {
			Game.pontos = 0;
		}
	}
	
	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,30,240));
		g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		if(Game.lose) {
			Game.win = false;
			g.setColor(new Color(255, 0, 0));
			g.setFont(Game.power);
			g.drawString("YOU LOSE", 270, 150);
			
			g.setColor(new Color(255, 255, 255));
			g.setFont(Game.pixelBold);
			g.drawString("SCORE", 320, 270);
			
			if(Game.pontosTotal == 32) {
				Game.pontosTotal = 0;
				g.setFont(Game.pixelBold);
				g.drawString(""+Game.pontosTotal, 380, 330);
			}
			g.setFont(Game.pixel);
			g.drawString("ENTER para reiniciar", 320, 480);
			g.setFont(Game.pixel);
			g.drawString("X para sair", 360, 520);
			
		}else if(Game.win){
			Game.lose = false;
			g.setColor(new Color(0, 105, 255));
			g.setFont(Game.power);
			g.drawString("YOU WIN", 270, 150);
			
			g.setColor(new Color(255, 255, 255));
			g.setFont(Game.pixelBold);
			g.drawString("SCORE", 320, 270);
			
			g.setFont(Game.pixelBold);
			g.drawString(""+Game.pontosTotal, 380, 330);
			
			g.setFont(Game.pixel);
			g.drawString("ENTER para reiniciar", 320, 480);
			g.setFont(Game.pixel);
			g.drawString("X para sair", 360, 520);
		
		}
		
	}

}
