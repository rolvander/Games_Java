package com.gcstudios.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import com.gcstudios.entities.Entity;
import com.gcstudios.world.World;

public class Menu {
	public boolean up, down, right, left, enter;
	public int currentOption = 0;
	public String[] options = { "novo jogo", "instrucoes", "sair" };

	public boolean pause = false;
	public int maxOption = options.length - 1;

	public void tick() {
		
		if (pause == false) {// Tela de Inicio
			if (down) {
				Sound.move.play();
				down = false;
				currentOption++;
				if (currentOption > maxOption)
					currentOption = 0;
			}
			if (up) {
				Sound.move.play();
				up = false;
				currentOption--;
				if (currentOption < 0)
					currentOption = 0;
			}
		} else {// pause
			// options[currentOption] = "novo jogo";
			if (down) {
				Sound.move.play();
				down = false;
				currentOption++;
				if (currentOption > maxOption)
					currentOption = 0;
			}
			if (up) {
				Sound.move.play();
				up = false;
				currentOption--;
				if (currentOption < 0)
					currentOption = 0;
			}
		}

		if (enter) {// PAUSE
			enter = false;
			if (options[currentOption] == "novo jogo" || options[currentOption] == "continuar") {
				Sound.select2.play();
				World.restartGame();
				pause = false;
			} else if (options[currentOption] == "instrucoes") {
				Sound.select.play();
				Game.gamestate = "INSTRUCOES";
			} else if (options[currentOption] == "sair") {
				Sound.select.play();
				System.exit(1);
			}
		}

	}

	public void render(Graphics g) {
		// ..:: MENU ::..
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 20, 250));
		g2.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(255, 255, 255));
		g.setFont(Game.newfont2);
		g.drawString("..:: rAttack2 ::..", 62, 80);

		// QUADRADO
		g.setColor(new Color(255, 255, 255));
		g.drawRoundRect(Game.WIDTH / 2, 130, 280, 220, 20, 20);

		// OPÇOES DO MENU
		if (pause == false) {// Esiver na Tela de Inicio

			g.setColor(Color.white);
			g.setFont(Game.newfont1);
			g.drawString("Start", 110, 180);
			g.drawString("Instrucoes", 110, 250);
			g.drawString("Sair", 110, 320);

		} else {// Se estiver pausado
			g.setFont(Game.newfont1);
			g.drawString("Continuar", 110, 180);
			g.drawString("Instrucoes", 110, 250);
			g.drawString("Sair", 110, 320);
		}

		// SETA
		g.setColor(Color.white);
		g.setFont(Game.newfont1);
		if (pause == false) {// Tela de início
			if (options[currentOption] == "novo jogo") {
				g.drawImage(Entity.ROCK_WHITE, 85, 165, 13, 13, null);

			} else if (options[currentOption] == "instrucoes") {
				g.drawImage(Entity.ROCK_WHITE, 85, 235, 13, 13, null);

			} else if (options[currentOption] == "sair") {
				g.drawImage(Entity.ROCK_WHITE, 85, 305, 13, 13, null);
			}
		} else {// Se estiver pausado
			if (currentOption == 0) {
				g.drawImage(Entity.ROCK_WHITE, 85, 165, 13, 13, null);

			} else if (currentOption == 1) {
				g.drawImage(Entity.ROCK_WHITE, 85, 235, 13, 13, null);

			} else if (currentOption == 2) {
				g.drawImage(Entity.ROCK_WHITE, 85, 305, 13, 13, null);
			}

		}

	}

}
