package com.gcstudios.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Menu {
	public String[] options = {"1","2","3","4","5","novo jogo","carregar jogo","sair"};
	public static int level = 1;
	
	public int currentOption = 0;
	public int maxOption = options.length - 1;
	
	public boolean up,down,right, left, enter;
	
	public boolean pause = false;
	
	public void tick() {
		//System.out.println("TOTAL OPÇÃO: "+maxOption);
	if(pause == false) {
		if(right) {
			right = false;
			currentOption++;
			if(currentOption > maxOption)
				currentOption = 0;
		}
		if(left) {
			left = false;
			currentOption--;
			if(currentOption < 0)
				currentOption = 0;
			}
	}else
		//options[currentOption] = "novo jogo";
		if(currentOption > 4) {
			if(right) {
				right = false;
				currentOption++;
				if(currentOption > maxOption)
					currentOption = 5;
			}
			if(left) {
				left = false;
				currentOption--;
				if(currentOption < 0)
					currentOption = 0;
				}
		}else 
			currentOption = 5;
		
		
		if(enter) {//Está no Pause
			//Sound.music.play();
			
			enter = false;
			if(options[currentOption] == "novo jogo" || options[currentOption] == "continuar") {
				Game.game_State = "NORMAL";
				pause = false;
			}else if(options[currentOption] == "carregar jogo") {
				Game.game_State = "MENU";
				pause = false;
			}else if(options[currentOption] == "sair") {
				System.exit(1);
			}else if(options[currentOption] == "1") {
				//System.out.println("1 Selecionado");
				Game.CUR_LEVEL = 1;
				Game.game_State = "NORMAL";
				Game.restartGame = true;
				pause = false;
			}else if(options[currentOption] == "2") {
				//System.out.println("2 Selecionado");
				Game.CUR_LEVEL = 2;
				Game.game_State = "NORMAL";
				Game.restartGame = true;
				pause = false;
					
			}else if(options[currentOption] == "3") {
				//System.out.println("3 Selecionado");
				Game.CUR_LEVEL = 3;
				Game.game_State = "NORMAL";
				Game.restartGame = true;
				pause = false;
				
			}else if (options[currentOption] == "4") {
				//System.out.println("4 Selecionado");
				Game.CUR_LEVEL = 4;
				Game.game_State = "NORMAL";
				Game.restartGame = true;
				pause = false;
				
			}else if (options[currentOption] == "5") {
				//System.out.println("5 Selecionado");
				Game.CUR_LEVEL = 5;
				Game.game_State = "NORMAL";
				Game.restartGame = true;
				pause = false;
			}
		}
		//System.out.println("OPÇÃO: "+currentOption);
	}
	
	public void render(Graphics g) {
		//..:: MENU ::..
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,10,250));
		g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		g.setColor(new Color(255,255,255));
		g.setFont(new Font("arial",Font.BOLD,23));
		g.drawString("..:: Mi' W ::..", 300,100);
		
		//QUADRADO
		g.setColor(new Color(255,255,255));
		g.drawRect(170, 120, 360, 240);
		
		//LEVEL'S
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,20));

		//OPÇOES DO MENU
			if(pause == false) {//Esiver na Tela de Inicio
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Novo jogo", 180, 400);
				g.drawString("Sair", 480, 400);
				
				g.setColor(new Color(255,255,100));
				g.setFont(new Font("arial",Font.BOLD,11));
				g.drawString("Selecione o Level para jogar:", 280, 142);
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,60));
			
				///LEVELS Com verificação de Win
				if(Game.checkWin >= 1) {
					g.drawString("1", 230, 215);
				}
				if(Game.checkWin >= 2) {
					g.drawString("2", 330, 215);
				}
				if(Game.checkWin >= 3) {
					g.drawString("3", 430, 215);
				}
				if(Game.checkWin >= 4) {
					g.drawString("4", 280, 295);
				}
				if(Game.checkWin >= 5) {
					g.drawString("5", 400, 295);
				}
				if(Game.checkWin > 6) {
					Game.checkWin = 1;
				}
					
			}else {//Se estiver pausado
				g.drawString("Continuar", 210, 180);
				g.drawString("Retornar ao Menu principal", 210, 220);
				g.drawString("Sair", 210, 260);
			}
			

		//Seta
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,20));
		if(pause == false) {//Tela de início
			if(options[currentOption] == "novo jogo") {
				g.drawString(">", 164, 400);
			}else if(options[currentOption] == "carregar jogo") {
				g.drawString(">", 464, 400);
			}else if(options[currentOption] == "sair") {
				g.drawString(">", 464, 400);
			}
			
			//SETA LEVELS com verificação de Win
			if(options[currentOption] == "1") {
				if(Game.checkWin >= 1) {
					
					g.setColor(new Color(255,255,0));
					g.fillRect(217, 164, 60, 60);
					g.setColor(new Color(0,0,40));
					g.setFont(new Font("arial",Font.BOLD,60));
					g.drawString("1", 230, 215);
					level = 1;
				}//Check Win
				
				
			}else if(options[currentOption] == "2") {
					if(Game.checkWin >= 2) {
						g.setColor(new Color(255,255,0));
						g.fillRect(317, 164, 60, 60);
						g.setColor(new Color(0,0,40));
						g.setFont(new Font("arial",Font.BOLD,60));
						g.drawString("2", 330, 215);
						level = 2;
					}//Check Win
				
			}else if(options[currentOption] == "3") {
				if(Game.checkWin >= 3) {
					g.setColor(new Color(255,255,0));
					g.fillRect(417, 164, 60, 60);
					g.setFont(new Font("arial",Font.BOLD,60));
					g.setColor(new Color(0,0,40));
					g.drawString("3", 430, 215);
					level = 3;
				}//Check Win
				
			}else if(options[currentOption] == "4") {
				if(Game.checkWin >= 4) {
					g.setColor(new Color(255,255,0));
					g.fillRect(266, 245, 60, 60);
					g.setFont(new Font("arial",Font.BOLD,60));
					g.setColor(new Color(0,0,40));
					g.drawString("4", 280, 295);
					level = 4;
				}//Check Win
				
			}else if(options[currentOption] == "5") {
				if(Game.checkWin >= 5) {
					g.setColor(new Color(255,255,0));
					g.fillRect(386, 245, 60, 60);
					g.setColor(new Color(0,0,40));
					g.setFont(new Font("arial",Font.BOLD,60));
					g.drawString("5", 400, 295);
					level = 5;
				}//Check Win
				
			}
			//System.out.println("LEVEL WIN: "+Game.checkWin);
			}else {//Se estiver pausado
				if(options[currentOption] == "novo jogo") {
					g.drawString(">", 190, 180);
				}else if(options[currentOption] == "carregar jogo") {
					g.drawString(">", 190, 220);
				}else if(options[currentOption] == "sair") {
					g.drawString(">", 190, 260);
				}
			
		}
	}
}
