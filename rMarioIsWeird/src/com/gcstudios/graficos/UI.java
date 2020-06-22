package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;

public class UI {
	
	public static int frames = 0;
	public static int maxFrames = 100;
	private static int frames2 = 0;
	private static int maxFrames2 = 2;
	
	public void render(Graphics g) {
		//LEVEL
		g.setColor(new Color(255,255,190));
		g.setFont(new Font("arial", Font.BOLD, 22));
		g.drawString("Level: "+Game.CUR_LEVEL, 488, 40);
		
		//VIDA
		g.setColor(Color.green);
		g.drawRect(20, 17, 200, 25);
		g.setColor(Color.green);
		g.fillRect(20, 17, (Player.life * 2), 25);
		g.setColor(new Color(0,165,0));
		g.setFont(new Font("arial", Font.BOLD, 15));
		g.drawString("Vida", 100, 35);
		if(Player.life <= 0) {
			g.setColor(new Color(245,0,0));
			g.drawRect(20, 17, 200, 25);
			g.setFont(new Font("arial", Font.BOLD, 15));
			g.drawString("Vida", 100, 35);
			Player.life = 0;
		}
		//MOEDAS
		g.setColor(new Color(255,255,190));
		g.setFont(new Font("arial", Font.BOLD, 22));
		g.drawString("X", 366, 41);
		g.setFont(new Font("arial", Font.BOLD, 25));
		g.drawString(" "+(int)Player.currentCoin, 379, 41);
	
		//GAMER OVER
		if(Player.life <= 0 || Game.gameover == true) {
			frames++;
			//System.out.println("Frames Over:"+(frames/60));
			if(frames > maxFrames) {
				//System.out.println("MODO DE APRESENTAÇÃO GAME OVER");
				Graphics2D g2 = (Graphics2D) g;	
				g2.setColor(new Color(20,20,20,230));
				g.fillRect(0, 0,Game.WIDTH*Game.SCALE,Game.HEIGHT*Game.SCALE);
				
				frames2++;
				Game.game_State = "GAMEOVER";
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 16));
				g.drawString("ENTER para reiniciar", 278, 287);
				g.drawString("ESC para fechar", 293, 317);
				if(frames2 == maxFrames2) {
						//System.out.println("Está em Game Over!!");
						g.setColor(Color.red);
						g.setFont(new Font("arial", Font.BOLD, 33));
						g.drawString("Game Over!", 258, 217);
						g.drawRect(244, 187, 210, 38);
			
						
						frames2 = 0;	
				}
			}
			//frames = 0;
		}
		
		if(Game.game_State == "GAMEOVERTIME") {
			frames++;
			//System.out.println("Frames Over:"+(frames/60));
			if(frames > maxFrames) {
				//System.out.println("MODO DE APRESENTAÇÃO GAME OVER");
				Graphics2D g2 = (Graphics2D) g;	
				g2.setColor(new Color(20,20,20,230));
				g.fillRect(0, 0,Game.WIDTH*Game.SCALE,Game.HEIGHT*Game.SCALE);
			
				frames2++;
				if(frames2 == maxFrames2) {
						//System.out.println("Está em Time Over!!");
						g.setColor(Color.red);
						g.setFont(new Font("arial", Font.BOLD, 33));
						g.drawString("Time Over!", 258, 217);
						g.drawRect(244, 187, 210, 38);
						frames2 = 0;
				}
			}
			//frames = 0;
		}
	}
}
