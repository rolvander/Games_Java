package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.gcstudios.entities.Bird;
import com.gcstudios.entities.BulletShoot;
import com.gcstudios.entities.BulletShoot2;
import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Ninho;
import com.gcstudios.entities.Player;
import com.gcstudios.graficos.Spritesheet;
import com.gcstudios.graficos.UI;
import com.gcstudios.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;

	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for (int xx = 0; xx < map.getWidth(); xx++) {
				for (int yy = 0; yy < map.getHeight(); yy++) {

					tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isFree(int xnext, int ynext) {

		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;

		int x2 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;

		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext + TILE_SIZE - 1) / TILE_SIZE;

		int x4 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (ynext + TILE_SIZE - 1) / TILE_SIZE;

		return !((tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile));
	}

	public static void restartGame() {
		System.out.println("REINICIOU");
		Game.win = 0;
		Game.levelGame = 1;
		Game.estado_cena = Game.comecar1;
		Game.entities.clear();
		Game.bullets.clear();
		Game.bullets2.clear();
		Game.player.flechas = 0;
		Game.player.pedras = 100;
		Game.player.pontos = 0;
		UI.minutes = 0;
		UI.seconds = 0;
		Game.spritesheet = new Spritesheet("/spritesheet.png");
		Game.entities = new ArrayList<Entity>();
		Game.bullets = new ArrayList<BulletShoot>();
		Game.inimigos = new ArrayList<Entity>();
		Game.bullets2 = new ArrayList<BulletShoot2>();
		Game.player = new Player(Game.WIDTH / 2, Game.HEIGHT - 15, 16, 16, 2, Game.spritesheet.getSprite(0, 0, 16, 16));
		Game.bird = new Bird(Game.WIDTH / 2, Game.HEIGHT - 38, 22, 15, 1, Game.spritesheet.getSprite(0, 16, 22, 17));
		Game.ninho = new Ninho(50, -16, 16, 16, 1, Game.spritesheet.getSprite(48, 64, 16, 16));
		Game.entities.add(Game.player);
		Game.entities.add(Game.bird);
		Game.entities.add(Game.ninho);
		Game.gamestate = "NORMAL";
		return;
	}

	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;

		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);

		for (int xx = xstart; xx <= xfinal; xx++) {
			for (int yy = ystart; yy <= yfinal; yy++) {
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}

}
