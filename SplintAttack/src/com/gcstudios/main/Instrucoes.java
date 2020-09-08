package com.gcstudios.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import com.gcstudios.entities.Entity;

public class Instrucoes {

	public void render(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 20, 250));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		
		//INSTRUCOES
		g.setColor(new Color(255, 255, 255));
		g.setFont(Game.newfont2);
		g.drawString("Instrucoes", 96, 50);
		
		//TEXTO
		g.setColor(new Color(255, 255, 255));
		g.setFont(Game.texto);
		g.drawString("Defenda o PASSARO dos", 90, 95);
		g.drawString("INIMIGOS para que ele", 90, 120);
		g.drawString("consiga levar a comida", 90, 145);
		g.drawString("ate o NINHO.", 90, 170);
		g.drawString("Para coletar FLECHA, ", 90, 200);
		g.drawString("VIDA e ESCUDO atire", 90, 225);
		g.drawString("com as pedras.", 90, 250);
		
		
		// QUADRADOS
		g.setColor(new Color(255, 255, 255));
		g.drawRoundRect(Game.WIDTH / 2, 70, 280, 110, 20, 20);
		g.drawRoundRect(Game.WIDTH / 2, 180, 280, 80, 20, 20);
		g.drawRoundRect(Game.WIDTH / 2, 337, 280, 160, 20, 20);
		
		//TECLAS
		g.setFont(Game.newfont2);
		g.drawString("Teclas", 140, 325);
		g.setFont(Game.texto);
		g.drawString("Movimentos", 160, 360);
		g.drawString(" Esquerda", 90, 385);
		g.drawString(" Direita", 230, 385);
		g.drawString("  ou 'A'", 112, 410);
		g.drawImage(Entity.SETA_ESQUERDA, 105, 399, 15, 13, null);
		g.drawImage(Entity.SETA_DIREITA, 240, 399, 15, 13, null);
		g.drawString("  ou 'D'", 245, 410);
		
		g.drawString("Tiros", 180, 445);
		g.drawString(" Pedras ", 90, 460);
		g.drawString(" 'x' ", 105, 485);
		g.drawString(" Flechas ", 230, 460);
		g.drawString(" 'z' ", 255, 485);

		//SAIR
		g.setColor(new Color(255, 255, 255));
		g.setFont(Game.newfont12);
		g.drawString("Voltar", 112, 546);
		g.drawRoundRect(200, 525, 75, 30, 12, 12);
		g.drawImage(Entity.SETA_ESQUERDA, 240, 535, 25, 12, null);
		
	}

}
