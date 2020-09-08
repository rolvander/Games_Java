package com.gcstudios.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;

public class Player extends Entity{

	public boolean right, left;
	public int dir_right = 1, dir_left = 0;
	private boolean moved = false;
	
	public int dir = dir_right;
	public boolean getCoin = false;
	public double vspd = 0;
	//private double gravity = 1;
	
	public int jumpHeight = 36;
	public int jumpFrames = 0;
	public boolean jump = false;
	public boolean isJumping = false;
	public static int life = 100;
	public static int maxCoins = 0;
	public static int currentCoin = 0;
	private int light = 0;
	public static boolean showFlag = false;
	public int mx,my;
	
	private int frames = 0,maxFrames = 8,index = 0,maxIndex = 4;
	public BufferedImage[] sprite_right;
	public BufferedImage[] sprite_left;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		sprite_right = new BufferedImage[4];
		sprite_left = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			sprite_right[i] = Game.spritesheet.getSprite(32 + (i * 12), 16, 12, 16);
		}
		for(int i = 0; i < 4; i++) {
			sprite_left[i] = Game.spritesheet.getSprite(32 + (i * 12), 0, 12, 16);
		}
	}
	
	public void tick(){
		depth = 2;
		vspd+= Game.gravity;
		
		if(Game.player.getY() <= 0) {
			y = 0;
		}
		
		
		if(!World.isFree((int)x,(int)(y+1)) && jump)//Se tiver chão pra baixo (y)e clicar Jump
		{
			isJumping = true;
			vspd = -6.6;
			jump = false;

		}
		
	 if(!World.isFree((int)x,(int)(y+vspd))) {// Se tiver chão pra baixo
			
			int signVsp = 0;
			if(vspd >= 0)
			{
				signVsp = 1;
			}else  {
				signVsp = -1;
			}
			while(World.isFree((int)x,(int)(y+signVsp))) {
				y = y+signVsp;

			}
			vspd = 0;
			isJumping = false;
		}
				
		y = y + vspd;
		
		if(isJumping) {
			//System.out.println("Pulando");
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Enemy) {
					if(Entity.isColidding(this, e)) {
						//System.out.println("Colidindo Cima");
						Sound.Clips.jump.play();
							vspd = -6;
							jump = false;
							((Enemy)e).life--;
							if(((Enemy)e).life == 0) {
								Game.entities.remove(i);
								break;
							}
						}
					}
				}
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Enemy2) {
					if(Entity.isColidding(this, e)) {
						//System.out.println("Colidindo Cima enemy2");
						Sound.Clips.jump.play();
							vspd = -6;
							jump = false;
							((Enemy2)e).life--;
							if(((Enemy2)e).life == 0) {
								Game.entities.remove(i);
								break;
							}
						}
					}
				}
		}
			
		//Detectar dano!
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				if(Entity.isColidding(this, e)) {
					if(Game.rand.nextInt(100) < 91) {
						life--;
						if(life < 0) {
							life =0;
						}
					}
				}
			}
			
		}//Detectar dano!
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy2) {
				if(Entity.isColidding(this, e)) {
					if(Game.rand.nextInt(100) < 85) {
						life--;
						if(life < 0) {
							life =0;
						}
					}
				}
			}
			
		}
		
		//MOVIMENTO
		moved = false;
		if(right && World.isFree(((int)(x+speed)), (int)y)) {
			dir = dir_right;
			x+=speed;
			moved = true;
		}
		else if(left && World.isFree((int)(x-speed), (int)y)) {//Se estiver livre para esquerda e clicar left
			dir = dir_left;
			x-=speed;
			moved = true;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				index++;
				frames = 0;
				if(index == maxIndex) {
					index = 0;
				}
			}
		}
	
		//GAMEOVER
		if(life <= 0) {
			Sound.Clips.gameOver.play();
			Game.gameover = true;
			Game.game_State = "GAMEOVER";
		}
		
		//Detectar colisão com a moeda
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Coin) {
				if(Entity.isColidding(this, e)) {
					Sound.Clips.coin.play();
					Game.entities.remove(i);
					Player.currentCoin++;
					break;
				}
			}
			
		}
		//COLISÃO COM A BANDEIRA
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Flag) {
				if(showFlag) {
					//System.out.println("Show Flag");
					if(Entity.isColidding(this, e)) {
						Sound.Clips.win.play();
						//CHECKAGEM DE LEVEL
						if(Game.CUR_LEVEL == 1) {
							Game.checkWin = 2;
						}else if(Game.CUR_LEVEL == 2) {
							Game.checkWin = 3;
						}else if(Game.CUR_LEVEL == 3) {
							Game.checkWin = 4;
						}else if(Game.CUR_LEVEL == 4) {
							Game.checkWin = 5;
						}else if(Game.CUR_LEVEL == 5) {
							Game.checkWin = 6;
						}
						//
						
						Game.entities.remove(i);
						Game.game_State = "WINNER";
						break;
						}
					}
				}
			}
		
		//COLISÃO COM A ÁGUA
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Water) {
				if(Entity.isColidding(this, e)) {	
						Game.gameover = true;
						//System.out.println(Game.game_State);
						
				}
			}
		}
		
		updateCamera();
			
	}
	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,World.HEIGHT*16 - Game.HEIGHT);
	}
	
	public void render (Graphics g) {
	
			if(dir == dir_right) {
				g.drawImage(sprite_right[index], getX() - Camera.x, getY() - Camera.y, null);
					
			}else if(dir == dir_left) {
				g.drawImage(sprite_left[index], getX() - Camera.x, getY() - Camera.y, null);
				
			}
			
			if(Game.game_State == "WINNER") {
				Game.frames++;
				//System.out.println("Winner: "+Game.frames/60);
				light+=5;
				g.setColor(new Color(0,110,0));
				g.setFont(new Font("arial", Font.BOLD, 12));
				g.drawString("LEVEL "+Game.CUR_LEVEL+"\n Completed!", (Game.WIDTH/4)+10, 60);
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(new Color(250,250,250,(int)light));
				g2.fillRect(0, 0,Game.WIDTH*Game.SCALE,Game.HEIGHT*Game.SCALE);

				if(light == 100) {
					light = 0;
				}
				
				if(Game.frames == Game.maxFrames){
					Game.frames = 0;
					Game.game_State = "NEXT";
				}
			}
	}	
}
