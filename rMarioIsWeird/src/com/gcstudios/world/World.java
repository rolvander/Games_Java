package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import com.gcstudios.entities.Flag;
import com.gcstudios.entities.Cloud;
import com.gcstudios.entities.Coin;
import com.gcstudios.entities.Enemy;
import com.gcstudios.entities.Enemy2;
import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Player;
import com.gcstudios.entities.Tower;
import com.gcstudios.entities.Water;
import com.gcstudios.graficos.Spritesheet;
import com.gcstudios.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;

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
					//Estilos de backgrounds
					if(Game.CUR_LEVEL == 1) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					}else if(Game.CUR_LEVEL == 2) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR2);
					}else if(Game.CUR_LEVEL == 3) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR3);
					}else if(Game.CUR_LEVEL == 4) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4);
					}else if(Game.CUR_LEVEL == 5) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR5);
					}else {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					}
					
					
					if(pixelAtual == 0xFF000000) {
						//Estilos de backgrounds
						if(Game.CUR_LEVEL == 1) {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
							
							
						}else if(Game.CUR_LEVEL == 2) {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR2);
						}else if(Game.CUR_LEVEL == 3) {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR3);
						}else if(Game.CUR_LEVEL == 4) {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR4);
						}else if(Game.CUR_LEVEL == 5) {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR5);
						}else {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
						}
					
						
					//Paredes
					}else if(pixelAtual == 0xFFffffff) {
						if(Game.CUR_LEVEL == 1) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
							if(yy - 1 >= 0 && pixels[xx+ ((yy -1) * map.getWidth())] == 0xFFffffff) {
								tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Game.spritesheet.getSprite(16, 16, 16, 16));
							}
						}else if(Game.CUR_LEVEL == 2) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL2);
							if(yy - 1 >= 0 && pixels[xx+ ((yy -1) * map.getWidth())] == 0xFFffffff) {
								tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Game.spritesheet.getSprite(16, 48, 16, 16));
							}
						}else if(Game.CUR_LEVEL == 3) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL3);
							if(yy - 1 >= 0 && pixels[xx+ ((yy -1) * map.getWidth())] == 0xFFffffff) {
								tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Game.spritesheet.getSprite(16, 80, 16, 16));
							}
						}else if(Game.CUR_LEVEL == 4) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL4);
							if(yy - 1 >= 0 && pixels[xx+ ((yy -1) * map.getWidth())] == 0xFFffffff) {
								tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Game.spritesheet.getSprite(32, 80, 16, 16));
							}
						}else if(Game.CUR_LEVEL == 5) {
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL5);
							if(yy - 1 >= 0 && pixels[xx+ ((yy -1) * map.getWidth())] == 0xFFffffff) {
								tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Game.spritesheet.getSprite(16, 16, 16, 16));
							}
						}
					
						if(yy - 1 >= 0 && pixels[xx+ ((yy -1) * map.getWidth())] == 0xFF3DC5F7){
							//System.out.println("Desenhando agua tile");
							tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Game.spritesheet.getSprite(48, 96, 16, 16));
						}
						
					}else if(pixelAtual == 0xFF0026FF) {
						//Jogador
						Game.player.setX(xx*12);
						Game.player.setY(yy*12);
						//System.out.println("Player Carregado");
					}else if(pixelAtual == 0xFFFF0000) {
						Enemy enemy1 = new Enemy(xx*16, yy*16, 16, 16, 1, Entity.ENEMY1);
						Game.entities.add(enemy1);
						//System.out.println("Inimigo Carregado");
					}else if(pixelAtual == 0xFF7F0F0F) {
						Enemy2 enemy2 = new Enemy2(xx*16, yy*16, 16, 16, 0.7, Entity.ENEMY2);
						Game.entities.add(enemy2);
						//System.out.println("Inimigo 2 Carregado");
					}else if(pixelAtual == 0xFFFFFF00) {
						Coin coin = new Coin(xx*16, yy*16, 16, 16, 1, Entity.COIN);
						Game.entities.add(coin);
						Player.maxCoins++;
						//System.out.println("MOEDA Carregada");
					}else if(pixelAtual == 0xFF3DC5F7) {
						Water water = new Water(xx*16, yy*16, 16, 16, 1, Entity.WATER);
						Game.entities.add(water);
					}else if(pixelAtual == 0xFFD89A6E) {
						Cloud cloud = new Cloud(xx*16, yy*16, 16, 16, 1, Entity.CLOUD);
						Game.entities.add(cloud);
					}

					/*else if(pixelAtual == 0xFF4CFF00) {
						Tower torre = new Tower(xx*16, yy*16, 6, 48, 1, Entity.TORRE);
						Game.chegada.add(torre);
						System.out.println("Torre1 Carregada");
					}else if(pixelAtual == 0xFF7F3300) {
						Flag flag = new Flag(xx*16, yy*16, 10, 10, 1, Entity.FLAG);
						Game.entities.add(flag);
						System.out.println("Bandeira Carregada");
					}*/
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xnext,int ynext){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
			
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;

		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	public static void restartGame(String level){
		//TODO: Aplicar método para reiniciar o jogo corretamente.
		
		Game.entities.clear();
		Game.chegada.clear();
		Game.gameover = false;
		Player.currentCoin = 0;
		Player.maxCoins = 0;
		Player.showFlag = false;
		Player.life = 100;
		Game.entities = new ArrayList<Entity>();
		Game.spritesheet = new Spritesheet("/spritesheet.png");
		Game.chegada = new ArrayList<Entity>();
		Game.player = new Player(Game.WIDTH/2 - 100,Game.HEIGHT/2,12,16,1,Entity.PLAYER_SPRITE_RIGHT);
		Game.tower = new Tower((Game.WIDTH*5) + 55,Game.HEIGHT - 64, 6, 48, 1, Entity.TORRE);
		Game.flag = new Flag(Game.tower.getX() - 8,Game.tower.getY() + 6, 116, 16, 1, Entity.FLAG);
		Game.entities.add(Game.player);
		Game.chegada.add(Game.tower);
		Game.entities.add(Game.flag);
		Game.world = new World("/"+level);
		//System.out.println("Reiniciou World!!!");
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
