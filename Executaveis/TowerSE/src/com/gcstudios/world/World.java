package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Spawner;
import com.gcstudios.entities.TowerController;
import com.gcstudios.graficos.Spritesheet;
import com.gcstudios.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	public static  int xFINAL = 0;
	public static  int yFINAL = 0;
	public static  int xINITIAL = 0;
	public static  int yINITIAL = 0;
	
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
					if(pixelAtual == 0xFF000000) {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
					}else if(pixelAtual == 0xFFffffff) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
						if(yy - 1 >= 0 && pixels[xx+ ((yy -1) * map.getWidth())] == 0xFFffffff) {
							tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Game.spritesheet.getSprite(16, 16, 16, 16));
						}
						if(xx - 1 >= 0 && pixels[(xx - 1)+ (yy * map.getWidth())] == 0xFFffffff) {
								tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Game.spritesheet.getSprite(16, 32, 16, 16));
						}

					}else if(pixelAtual == 0xFFD89A6E) {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16, yy*16, Entity.FIGURA1);
						
					}else if(pixelAtual == 0xFF00FF21) {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16, yy*16, Entity.FIGURA2);
						
					}else if(pixelAtual == 0xFF484849) {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16, yy*16, Entity.FIGURA3);
						
					}else if(pixelAtual == 0xFFFFFF00) {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16, yy*16, Entity.FIGURA4);
						
					}else if(pixelAtual == 0xFF00FFFF) {
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16, yy*16, Entity.FIGURA5);
						
					}
					else if(pixelAtual == 0xFFFF0000) {//ENTRADA cor vermelha
						//Criar Spawner (Inimigo chegando)
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
						Spawner spawner = new Spawner(xx*16, yy*16, 16, 16, 0,null);
						xINITIAL = xx;
						yINITIAL = yy;
						Game.entities.add(spawner);
						
					}else if(pixelAtual == 0xFF0026FF) {//Chegada cor azul
						//Ponto Final do Inimigo
						tiles[xx + (yy * WIDTH)] = new TargetTile(xx*16,yy*16,Tile.TILE_FLOOR);
						
						xFINAL = xx;
						yFINAL = yy;
					}else if (pixelAtual == 0xFFD35400) {
						///System.out.println("TESTE LARANJA");
						tiles[xx + (yy * WIDTH)] = new TargetTile(xx*16,yy*16,Tile.TILE_FLOOR_CHEGADA);
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
	
	public static void restartGame(){
		//TODO: Aplicar método para reiniciar o jogo corretamente.
		System.out.println("REINICIOU");
		Game.gameState = "NORMAL";
		Game.entities.clear();
		Game.spritesheet = new Spritesheet("/spritesheet.png");
		Game.lose = false;
		Game.win = false;
		Game.time = (Game.timeInitial * 60);
		Game.money = 32;
		Game.life = 10;
		Game.price = 4;
		Game.towerCount = 0;
		Game.towerController = new TowerController(0, 0, 0, 0, 0, null);
		Game.entities = new ArrayList<Entity>();
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
