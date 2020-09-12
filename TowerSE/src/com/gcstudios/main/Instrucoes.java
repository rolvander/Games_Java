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
		g.drawString("Este jogo e uma representacao do jogo Tower Defense.", 115, 160);
		g.drawString("A proposta e: Construir Torres de Tesla para impedir ", 115, 190);
		g.drawString("que o inimigos chegem a base. Cada Torre requer 4 moedas", 100, 220);
		g.drawString("para construi-las e para cada inimigo destruido a quantidade", 80, 250);
		g.drawString("de moedas aumenta. Nao permita que os inimigos cheguem ", 110, 280);
		g.drawString("a base antes que o tempo se esgote.", 190, 310);
		g.drawString("Utilize o mouse para posicionar e adicionar as torres.", 120, 410);
		
		g.setFont(Game.timeFont);
		g.drawString("Aproveite!", 80, 510);
		
		g.drawString("Voltar (ESC)", 580, 510);
	}
}
