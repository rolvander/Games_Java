package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.gcstudios.main.Game;

public class Tile {
	
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0,0,16,16);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16,0,16,16);

	public BufferedImage sprite;
	public int x,y;
	public int width, height;
	public int maskx,masky,mwidth,mheight;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	public void render(Graphics g){
		//g.setColor(Color.gray);
		//g.fillRect(this.x + maskx - Camera.x,this.y + masky - Camera.y, mwidth,mheight);
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
		
	}

}
