package com.gcstudios.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Instrucoes {

	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,30,240));
		g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		//Titulo
		g.setColor(new Color(210, 210,255));
		g.setFont(Game.power);
		g.drawString("Tower S.E", 200, 100);
		
		//Texto
		g.setFont(Game.pixel);
		g.setColor(new Color(255, 255, 255));
		g.drawString("Este jogo e' uma representacao do jogo Tower Defense.", 125, 180);
		g.drawString("A proposta e': CONSTRUIR TORRES de Tesla para impedir ", 125, 210);
		g.drawString("que o inimigos chegem a base. Cada Torre requer 4 MOEDAS", 110, 240);
		g.drawString("para construi'-las e para cada inimigo destrui'do a quantidade", 90, 270);
		g.drawString("de moedas aumenta. Nao permita que os inimigos cheguem ", 120, 300);
		g.drawString("a base antes que o TEMPO se esgote.", 200, 330);
		g.drawString("UTILIZE O MOUSE PARA ADICIONAR AS TORRES!", 190, 410);
		
		g.setFont(Game.timeFont);
		g.drawString("Aproveite!", 80, 510);
		
		g.drawString("Voltar (ESC)", 580, 510);
	}
}
