package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.gcstudios.entities.Apple;
import com.gcstudios.entities.Enemy;
import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Orange;
import com.gcstudios.entities.Player;
import com.gcstudios.graficos.Spritesheet;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	
	public static int frames = 0;
	public static int maxFrames = 3;
	
	
	public World(String path){
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++){
				for(int yy = 0; yy < map.getHeight(); yy++){
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					if(pixelAtual == 0xFF000000){
						//Ch�o
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFFFFFFFF){
						//Parede
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
					}else if(pixelAtual == 0xFF0026FF) {
						//Player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}else if(pixelAtual == 0xFF00A5A8) {
						//Instanciar inimigo e adicionar na lista das entities
						Enemy enemy1 = new Enemy(xx*16, yy*16, 16, 16, 3, Entity.ENEMY_BLUE);
						Game.entities.add(enemy1);
						//System.out.println("Carregando inimigo azul");
					}else if(pixelAtual == 0xFF57007F) {
						//Instanciar inimigo e adicionar na lista das entities
						Enemy enemy2 = new Enemy(xx*16, yy*16, 16, 16, 3, Entity.ENEMY_RED);
						Game.entities.add(enemy2);
						//System.out.println("Carregando inimigo azul");
					}else if(pixelAtual == 0xFFFFD800) {
						//Ma��
						Apple apple = new Apple(xx*16, yy*16, 16, 16, 0, Entity.APPLE_SPRITE);
						Game.entities.add(apple);
						Game.fruta_contagem++;
					}else if (pixelAtual == 0xFFFF7800) {
						Orange orange = new Orange(xx*16, yy*16, 16, 16, 0, Entity.ORANGE_SPRITE);
						Game.entities.add(orange);
						Game.orange_contagem++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFreeDynamic(int xnext,int ynext, int width, int height){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+width -1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+height-1) / TILE_SIZE;
		
		int x4 = (xnext+width -1) / TILE_SIZE;
		int y4 = (ynext+height-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	public static boolean isFree(int xnext,int ynext){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE -1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE -1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}

	public static void restartGame(){
		
		Game.game_State = "NORMAL";
		Game.player = new Player(0,0,16,16,1,Game.spritesheet.getSprite(32, 0,16,16));
		Game.entities.clear();
		Game.spritesheet = new Spritesheet("/spritesheet.png");
		Game.entities.add(Game.player);
		Game.fruta_contagem = 0;
		Game.fruta_atual = 0;
		if(Game.win == true) {
			Player.winTime = 0;
			if(Player.winTime < Player.winMaxTime) {
				Sound.winner.loop();
				Player.winTime = 0;
			}
		}
		Game.win = false;
		Game.gameover = false;
		Enemy.over = false;
		Game.estado_cena = Game.entrada;
		if(Game.estado_cena == Game.entrada) {
			Sound.music.loop();
		}
		Game.framesEntrada = 0;
		Game.world = new World("/level1.png");
		return;
	
	}

	public void render(Graphics g){
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
