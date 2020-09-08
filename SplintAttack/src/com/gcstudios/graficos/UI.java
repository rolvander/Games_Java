package com.gcstudios.graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import com.gcstudios.entities.Entity;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class UI {

	public int frames = 0, maxframes = 1;
	public int index, maxIndex = 3;
	private int frames2 = 0;
	public static int seconds = 0;
	public static int minutes = 0;
	private int timecena = 0;
	private int maxtimecena = 60 * 3;
	private int timecena2 = 0;
	private int maxtimecena2 = 60 * 3;
	private int timecena3 = 0;
	private int maxtimecen3 = 60 * 3;

	public void tick() {

		frames2++;
		if (frames2 == 60) {
			frames2 = 0;
			seconds++;
			if (seconds == 60) {
				seconds = 0;
				minutes++;

			}
		}
	}

	public void render(Graphics g) {

		// BARRA DE ITENS
		g.setColor(new Color(50, 50, 100));

		// TEMPO
		String formatTime = "";
		if (minutes < 10) {
			formatTime += "0" + minutes + ":";
		} else {
			formatTime += minutes + ":";
		}

		if (seconds < 10) {
			formatTime += "0" + seconds;
		} else {
			formatTime += seconds;
		}

		// LEVEL's
		if (minutes < 1) {// LEVEL 1
			Game.levelGame = 1;
		} else if (minutes == 1) {// LEVEL 2
			Game.levelGame = 2;
		} else if (minutes == 2) {// LEVEL 3
			Game.levelGame = 3;
		} else if (minutes == 3) {// LEVEL 4
			Game.levelGame = 4;
		}

		// CUTSCENE DE LEVEL'S
		if (Game.levelGame == 2) {
			timecena++;

			if (timecena > 0 && timecena < 58) {
				g.setColor(new Color(0, 0, 50));
				g.setFont(Game.seven);
				g.drawString("LEVEL " + Game.levelGame, 120, 240);
				if (timecena == 10) {
					Sound.levelup.play();
				}
				if (timecena > maxtimecena) {
					timecena = 0;
				}
			}

		}
		if (Game.levelGame == 3) {
			timecena2++;

			if (timecena2 > 0 && timecena2 <= 60) {
				g.setColor(new Color(0, 0, 50));
				g.setFont(Game.seven);
				g.drawString("LEVEL " + Game.levelGame, 120, 240);
				if (timecena2 == 10) {
					Sound.levelup.play();
				}
				if (timecena2 > maxtimecena2) {
					timecena2 = 0;
				}
			}
		}
		if (Game.levelGame == 4) {
			timecena3++;
			if (timecena3 > 0 && timecena3 <= 60) {
				g.setColor(new Color(0, 0, 50));
				g.setFont(Game.seven);
				g.drawString("LEVEL " + Game.levelGame, 120, 240);
				if (timecena3 == 10) {
					Sound.levelup.play();
				}

				if (timecena3 > maxtimecen3) {
					timecena3 = 0;
				}
			}
		}

		// TEMPO
		g.setColor(new Color(50, 50, 100));
		g.setFont(Game.fonttime);
		g.drawImage(Entity.ICONCLOCK, (Game.WIDTH * Game.SCALE) - 105, 16, 20, 20, null);
		g.drawString(formatTime, (Game.WIDTH * Game.SCALE) - 82, 33);

		// PONTOS
		g.setColor(new Color(10, 10, 100));
		g.setFont(Game.fonttime);
		g.drawString("" + Game.player.pontos, 226, 34);
		g.drawString("SCORE", 156, 34);

		// PEDRAS
		g.setColor(new Color(200, 200, 210));
		g.fillRoundRect(24, 18, 200 / 3, 16, 14, 14);
		g.setColor(new Color(70, 70, 70));
		g.setFont(Game.newfont1);
		g.drawImage(Entity.ICONROCK, 6, 13, 23, 23, null);
		g.fillRoundRect(24, 18, Game.player.pedras / 3, 16, 14, 14);
		g.drawRoundRect(24, 18, 200 / 3, 15, 14, 14);

		if (Game.player.pedras >= 200) {
			Game.player.pedras = 200;
		}
		if (Game.player.pedras <= 0) {
			Game.player.pedras = 0;
		}

		// FLECHAS
		g.setColor(new Color(130, 39, 14));
		g.setFont(Game.newfont1);
		g.drawImage(Entity.ICONARROW, (Game.WIDTH * Game.SCALE) - 406, 46, 23, 23, null);
		g.fillRoundRect(21, 52, Game.player.flechas * 3, 16, 14, 14);

		// GAME OVER
		if (Game.gamestate == "GAMEOVER") {
			Sound.gameover.loop();
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(0, 0, 20, 250));
			g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
			g.setColor(new Color(255, 255, 255));
			g.setFont(Game.algebrian);
			g.drawString("gameover", 24, 200);

			frames++;
			if (frames > maxframes) {
				g.setColor(new Color(255, 255, 255));
				g.setFont(Game.fonttime);
				g.drawString("> Pressione ENTER para ir ao MENU! <", 15, 350);
				frames = 0;
			}

		}

		// WIN
		if (Game.win == 1) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(0, 0, 20, 250));
			g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
			g.setColor(new Color(255, 255, 255));
			g.setFont(Game.algebrian);
			g.drawString("winner!", 54, 200);
			g.setColor(new Color(255, 255, 255));
			
			g.setColor(new Color(255, 255, 55));
			g.setFont(Game.newfont1);
			g.drawRoundRect(190, 280, 100, 40, 9, 9);
			g.drawString("SCORE: "+Game.player.pontos, 100,310);
			
			g.setColor(new Color(255, 255, 255));
			g.setFont(Game.fonttime);
			g.drawString("> Tecle ENTER para reiniciar! <", 50, 500);

		}
	}
}
