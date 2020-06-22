package com.gcstudios.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;


public class UI {

	public static int frames = 0;
	public static int maxFrames = 2;
	
	public void render(Graphics g) {
		
		g.setColor(Color.yellow);
		g.fillRect(32,9,(int)(Game.fruta_atual),15);
		g.drawRect(32, 9,(int)(Game.fruta_contagem), 15);
		
		g.setColor(Color.green);
		g.fillRect(570,9,(int)(Game.player.life) / 3,15);
		if(Game.player.life <= 83) {
			g.setColor(Color.red);
			g.fillRect(570,9,(int)(Game.player.life) / 3,15);
			g.setFont(new Font("arial", Font.ITALIC, 18));
			g.drawString("danger", 600, 22);
		}
		g.drawRect(570, 9, 100, 15);
		
		if(Game.estado_cena == Game.entrada) {
			frames++;
			if(frames == maxFrames) {
			
				g.setColor(Color.yellow);
				g.setFont(new Font("arial", Font.ITALIC, 30));
				g.drawString("Ready!", 265, 217);
				g.drawRect(250, 187, 131, 38);
				frames = 0;
			}
		}
		if(Game.player.life == 0) {
			
			Graphics2D g2 = (Graphics2D) g;	
			g2.setColor(new Color(0,0,0,220));
			g.fillRect(0, 0,Game.WIDTH*Game.SCALE,Game.HEIGHT*Game.SCALE);
			System.out.println("Está em Game Over!!");
			frames++;
			if(frames == maxFrames) {
				System.out.println("Está em Game Over!!");
				g.setColor(Color.red);
				g.setFont(new Font("arial", Font.BOLD, 33));
				g.drawString("Game Over!", 258, 217);
				g.drawRect(244, 187, 210, 38);
				frames = 0;	
			}
		}
		
		
		if(Game.fruta_contagem == Game.fruta_atual) {
			frames++;
			if(frames == maxFrames) {
				g.setColor(new Color(0, 250,0));
				g.setFont(new Font("arial", Font.BOLD, 33));
				g.drawString("You Win!", 258, 217);
				g.drawRect(244, 187, 171, 38);
				//Sound.winner.loop();
				g.setColor(new Color(255,255,255));
				g.drawRect(32, 32, 640, 416);
				g.drawRect(64, 64, 190, 32);
				g.drawRect(414, 64, 224, 32);
				g.drawRect(320, 64, 32, 97);
				g.drawRect(64, 160, 223, 32);
				g.drawRect(414, 160, 225, 32);
				g.drawRect(64, 255, 32, 130);
				g.drawRect(160, 223, 32, 159);
				g.drawRect(255, 351, 194, 32);
				g.drawRect(511, 352, 128, 32);
				g.drawRect(544, 256, 96, 32);
				g.drawRect(480, 223, 32, 99);
				g.drawRect(97, 416, 32, 32);
				g.drawRect(351, 416, 32, 32);
				g.drawRect(577, 416, 32, 32);
				frames = 0;
			}
		}
		if(Player.winTime > Player.winMaxTime) {
			g.setColor(new Color(220, 220,0));
			g.setFont(new Font("arial", Font.BOLD, 23));
			g.drawString("Pressione ENTER para Reiniciar!", 170, 340);
			//g.drawRect(44, 187, 171, 38);
			
		}
		if(Player.timeOver > Player.timeOverMax) {
			g.setColor(new Color(220, 220,0));
			g.setFont(new Font("arial", Font.BOLD, 23));
			g.drawString("Pressione ENTER para Reiniciar!", 170, 340);
			//g.drawRect(44, 187, 171, 38);
			
		}
	}
}
