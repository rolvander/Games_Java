package com.gcstudios.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.gcstudios.entities.Coin;
import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Flag;
import com.gcstudios.entities.Player;
import com.gcstudios.entities.Tower;
import com.gcstudios.graficos.Spritesheet;
import com.gcstudios.graficos.UI;
import com.gcstudios.world.World;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener,MouseMotionListener{

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	public static final int SCALE = 3;
	public static boolean restartGame = false;
	public static boolean gameover = false;
	public static boolean FlagRender = false;
	public static int checkWin = 1;
	
	private BufferedImage image;
	
	public static World world;
	public static List<Entity> entities;
	public static List<Entity> chegada;
	public static Spritesheet spritesheet;
	public static Player player;
	public static Tower tower;
	public static Flag flag;
	public static Coin coin;
	public static Random rand;
	public Menu menu;
	public static double gravity = 0.5;
	public UI ui;
	public static String game_State = "MENU";
	public static int CUR_LEVEL = 1,MAX_LEVEL = 5;
	public static int frames = 0;
	public static int maxFrames = 160;

	public Game(){
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		//Inicializando objetos.
		spritesheet = new Spritesheet("/spritesheet.png");
		entities = new ArrayList<Entity>();
		chegada = new ArrayList<Entity>();
		player = new Player(WIDTH/2 - 100,HEIGHT/2,12,16,1,Entity.PLAYER_SPRITE_RIGHT);
		//WIDTH/2 - 100 -- posição do player
		tower = new Tower((WIDTH*5) + 55,HEIGHT - 64, 6, 48, 1, Entity.TORRE);
		flag = new Flag(tower.getX() - 8,tower.getY() + 6, 116, 16, 1, Entity.FLAG);
		coin = new Coin(110, 2, 8*SCALE, 8*SCALE, 1, Entity.COIN);
	
		world = new World("/level1.png");
		ui = new UI();
		rand = new Random();
		entities.add(player);
		chegada.add(tower);
		entities.add(flag);
		
		menu = new Menu();
		
	}
	
	public void initFrame(){
		frame = new JFrame("Mi' W");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		Image icon = null;
		try {
			icon = ImageIO.read(getClass().getResource("/icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(getClass().getResource("/cursor.png"));
		Cursor c = toolkit.createCustomCursor(image, new Point(0,0), "img");
		frame.setCursor(c);
		frame.setIconImage(icon);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		Game game = new Game();
		game.start();
	}
	
	public void tick(){

		//ENTITIES
		Sound.Clips.theme1.loop();
		if(game_State == "NORMAL") {
			
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
				
			}
			
		}
	
		//PROXIMO NIVEL
		if(game_State == "NEXT") {
			CUR_LEVEL++;
			if(CUR_LEVEL > MAX_LEVEL){
				CUR_LEVEL = MAX_LEVEL;
			}
				String newWorld = "level"+CUR_LEVEL+".png";
				World.restartGame(newWorld);
				//System.out.println(newWorld);
				game_State = "NORMAL";
		}

		//Se reiniciar
		if(restartGame) {
			restartGame = false;
				game_State = "NORMAL";
				
				String newWorld = "level"+CUR_LEVEL+".png";
				//System.out.println(newWorld);
				World.restartGame(newWorld);
				//maxsegundos = 500;
		}else if(game_State == "MENU") {//Pausa
			//System.out.println("Cur LEVEL: "+CUR_LEVEL);
			player.updateCamera();
			menu.tick();
		}
			
	}

	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		g.setColor(new Color(122,102,255));
		g.fillRect(0, 0,WIDTH,HEIGHT);
		
		
		/*Renderização do jogo*/
		
		world.render(g);
		
		g.drawImage(Entity.COIN, 111, 7, null);
		Collections.sort(entities,Entity.nodeSorter);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		
		for(int i = 0; i < chegada.size(); i++) {
			Entity e = chegada.get(i);
			e.render(g);
		}
	
		/***/
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
		//reminder.render(g);

		ui.render(g);
		
		if(game_State == "MENU") {
			menu.render(g);
		}
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println("FPS: "+ frames);
				frames = 0;
				timer+=1000;
			}	
		}
		stop();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
			if(game_State == "MENU") {
				menu.right = true;
			}
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
			if(game_State == "MENU") {
				menu.left = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.jump = true;
			if(game_State == "MENU") {
				menu.up = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				
			if(game_State == "MENU") {
				menu.down = true;
			}
		}
		
		
		if(gameover) {
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				//System.out.println(gameover +" Enter");
				Game.restartGame = true;
				//game_State = "MENU";
				gameover = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				//System.out.println(" Exit ");
				System.exit(1);
			}
			
			
		}
		if(game_State == "GAMEOVERTIME") {
			Game.game_State = "MENU";
			//System.out.println("Enter de reinício TimeOver");
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			//this.restartGame = true;
			if(game_State == "MENU") {
				menu.enter = true;

			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			game_State = "MENU";
			menu.pause = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
	
			Game.gameover = false;
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	
	}

	
}
