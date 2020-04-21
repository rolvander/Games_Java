package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.gcstudios.main.Game;

public class UI {

	public int frames = 0, maxframes = 60;
	public int index, maxIndex = 3;
	public void render(Graphics g) {
		//Vidas
		g.setColor(new Color(255, 255, 255));
		g.setFont(new Font("arial",Font.BOLD,17));
		g.drawString("Vidas: "+Game.player.life, 17, 23);
		
		//Pontos
		g.setColor(new Color(255, 255, 255));
		g.setFont(new Font("arial",Font.BOLD,17));
		g.drawString("Pontos: "+Game.player.pontos, 410, 23);
	
		//Game Over
		if(Game.gameover == 1){
			
			Graphics2D g2 = (Graphics2D) g;	
			g2.setColor(new Color(40,40,40,245));
			g.fillRect(0, 0,Game.WIDTH*Game.SCALE,Game.HEIGHT*Game.SCALE);
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("arial",Font.BOLD,27));
			g.drawString("GAME OVER ", 165, 240);
			
			frames++;
			//System.out.println("Frames: "+frames);
			if(frames > maxframes) {
				g.setColor(new Color(255, 255, 255));
				g.setFont(new Font("arial",Font.BOLD,18));
				g.drawString("> Pressione ENTER para reiniciar <", 95, 290);
				//frames = 0;
			}
			
			
		}
		
	}
	
}
