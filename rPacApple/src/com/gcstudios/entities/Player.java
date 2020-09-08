package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	public BufferedImage[] sprite_right;
	public BufferedImage[] sprite_left;
	public BufferedImage[] sprite_down;
	public BufferedImage[] sprite_up;

	private int frames = 0,maxFrames = 4,index = 0,maxIndex = 3;
	public int lastDir = 1;
	public static int timeOver = 0;
	public static int timeOverMax = 60*3;
	
	public int maxLife = 300;
	public int life = 300;
	public static int winTime = 0;
	public static int winMaxTime = 60*4;

	public Player(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprite_right = new BufferedImage[4];
		sprite_left = new BufferedImage[4];
		sprite_up = new BufferedImage[4];
		sprite_down = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			sprite_right[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);
		}
		for(int i = 0; i < 4; i++) {
			sprite_left[i] = Game.spritesheet.getSprite(32 + (i * 16), 16, 16, 16);
		}
		for(int i = 0; i < 4; i++) {
			sprite_down[i] = Game.spritesheet.getSprite(32 + (i * 16), 32, 16, 16);
		}
		for(int i = 0; i < 4; i++) {
			sprite_up[i] = Game.spritesheet.getSprite(32 + (i * 16), 48, 16, 16);
		}

	}
	
	public void tick(){
		depth = 1;
		
		if(right && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;
			lastDir = 1;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
			lastDir = -1;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
			lastDir = 2;
			
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
			lastDir = -2;
		}
		
		verificarPegaFruta();
		
		if(Game.fruta_contagem == Game.fruta_atual) {
			//VITÓRIA
			Game.win = true;
			Enemy.ghostMode = false;
			//restart Game
			if(Game.win == true) {
				winTime++;
				System.out.println("Time: "+winTime);
				if(winTime < winMaxTime) {
					//Game.estado_cena = Game.ganhou;
					Sound.winner.loop();
					//System.out.println("MUsica");
				}
			}
						
		}
		
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex)
				index = 0;
		}
		
		if(life <= 0) {
			life = 0;
			
			Enemy.over = true;
			timeOver++;
			if(timeOver == timeOverMax) {
				
				for(int i = 0; i < Game.entities.size(); i++) {
					Entity current = Game.entities.get(i);
					if(current == Game.player) {
						Game.entities.remove(i);
					}
			}
		}
			Game.gameover = true;
			if(Game.gameover = true) {
				timeOver++;
				if(timeOver < timeOverMax) {
					Sound.game_over.loop();
				}
			}
				//System.out.println("REINICIOU");
				//World.restartGame();
		
			//restart Game
	}
	
}	
	public void verificarPegaFruta() {
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity current = Game.entities.get(i);
			if(current instanceof Apple) {
				if(Entity.isColidding(this, current)) {
					Game.fruta_atual++;
					Sound.eat_apple.play();
					Game.entities.remove(i);
					return;
				}
			}
			if(current instanceof Orange) {
				if(Entity.isColidding(this, current)) {
					Game.orange_atual++;
					Sound.eat_orange.play();
					Game.entities.remove(i);
					Enemy.ghostMode = true;
					return;
				}
			}
		}
		
		
	}
	public void render (Graphics g) {
		if(lastDir == 1) {
			g.drawImage(sprite_right[index],this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else if(lastDir == -1) {
			g.drawImage(sprite_left[index],this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else if (lastDir == -2) {
			g.drawImage(sprite_down[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else if (lastDir == 2) {
			g.drawImage(sprite_up[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		if(Game.win == true) {
			g.drawImage(SPRITE_WIN, this.getX(), this.getY(), null);
		}
		if(Game.gameover == true) {
			g.drawImage(SPRITE_LOSER, this.getX(), this.getY(), null);
		}
	
	}
	
}
