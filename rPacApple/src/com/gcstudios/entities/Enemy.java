package com.gcstudios.entities;



import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.Random;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;
import com.gcstudios.world.AStar;
import com.gcstudios.world.Vector2i;


public class Enemy extends Entity{
	
	public static boolean ghostMode = false;
	public static int ghostFrames = 0;
	public static boolean over = false;
	
	public Enemy(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

	}

	public void tick(){
		depth = 0;
		
		if(calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 180) {
			
			if(Game.game_State == "NORMAL") {
				Sound.sirene.loop();
			}
		if(ghostMode == false) {
			if(over == false) {
		
			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
				Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
				path = AStar.findPath(Game.world, start, end);
			}
		
			if(new Random().nextInt(100) < 83)
				//Inteligência dos fantasmas
				followPath(path);
			
			if(x % 16 == 0 && y % 16 == 0) {
				if(new Random().nextInt(100) < 10) {
					Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
					Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
					path = AStar.findPath(Game.world, start, end);
				}
			}
		}
		}else if(ghostMode == true) {
					ghostFrames++;
							
		}
		if(ghostFrames == 60*8) {
				ghostMode = false;
				ghostFrames = 0;
		}
	}		
		if(Game.win == true) {	
			for(int i =0; i < Game.entities.size(); i++) {
				Entity current = Game.entities.get(i);
				if(current == this) {
					Game.entities.remove(i);
				}
			}		
		}
		if(isColidding(this, Game.player)) {
			//System.out.println("Dano!");
			Game.player.life -= Game.rand.nextInt(3);
		}	
	}
	
	public void render(Graphics g) {
		super.render(g);
	}
}
