package com.gcstudios.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Menu {
	public String[] options = { "novo jogo", "instrucoes", "sair" };

	public int currentOption = 0;
	public int maxOption = options.length - 1;

	public boolean up, down, enter;

	public boolean pause = false;

	public void tick() {
		if (up) {
			up = false;
			currentOption--;
			if (currentOption < 0)
				currentOption = maxOption;
		}
		if (down) {
			down = false;
			currentOption++;
			if (currentOption > maxOption)
				currentOption = 0;
		}
		if (enter) {

			enter = false;
			if (options[currentOption] == "novo jogo" || options[currentOption] == "continuar") {
				Game.gameState = "NORMAL";
				pause = false;
			}
			if (options[currentOption] == "instrucoes") {
				// CLASSE INSTRUÇÕES
				Game.gameState = "INSTRUCOES";
				pause = true;
			} else if (options[currentOption] == "sair") {
				System.exit(1);
			}
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 30, 240));
		g2.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);

		// NOME DO JOGO
		g.setColor(new Color(210, 210,255));
		g.setFont(Game.power);
		g.drawString("Tower S.E", 230, 130);

		// OPCOES DO MENU
		g.setColor(Color.white);
		g.setFont(Game.timeFont);
		if (pause == false)
			g.drawString("Novo jogo", (Game.WIDTH * Game.SCALE) / 2 - 75, 270);
		else
			g.drawString("Continuar", (Game.WIDTH * Game.SCALE) / 2 - 75, 270);
			g.drawString("Instruções", (Game.WIDTH * Game.SCALE) / 2 - 75, 330);
			g.drawString("Sair", (Game.WIDTH * Game.SCALE) / 2 - 75, 390);

		if (options[currentOption] == "novo jogo") {
			g.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 100, 270);
		} else if (options[currentOption] == "instrucoes") {
			g.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 100, 330);
		} else if (options[currentOption] == "sair") {
			g.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 100, 390);
		}
	}
}
