package com.gcstudios.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import com.gcstudios.entities.Entity;
import com.gcstudios.entities.TowerController;
import com.gcstudios.graficos.Spritesheet;
import com.gcstudios.graficos.UI;
import com.gcstudios.world.World;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 272;
	public static final int HEIGHT = 192;
	public static final int SCALE = 3;

	public static double life = 10;
	public static int money = 32;
	public static int price = 4;
	public static int timeInitial = 100;
	public static int time = (timeInitial * 60);
	public static int pontos;
	public static int pontosTotal = 0;
	public static int towerCount = 0;
	public static boolean win = false;
	public static boolean lose = false;

	public static boolean restartGame = false;

	public static String gameState = "MENU";

	private BufferedImage image;

	public static World world;
	public static List<Entity> entities;
	public static Spritesheet spritesheet;
	public UI ui;
	public static TowerController towerController;
	public Menu menu;
	public Score score;
	public Instrucoes instrucoes;

	// ESTILO DE FONTE
	public static InputStream stream1 = ClassLoader.getSystemClassLoader().getResourceAsStream("power.ttf");
	public static InputStream stream2 = ClassLoader.getSystemClassLoader().getResourceAsStream("time.ttf");
	public static InputStream stream3 = ClassLoader.getSystemClassLoader().getResourceAsStream("pixelmix.ttf");
	public static InputStream stream4 = ClassLoader.getSystemClassLoader().getResourceAsStream("pixel.ttf");

	public static Font power, timeFont, pixel, pixelBold;

	public Game() {
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		// Inicializando objetos.

		spritesheet = new Spritesheet("/spritesheet.png");
		entities = new ArrayList<Entity>();
		world = new World("/level1.png");
		ui = new UI();
		towerController = new TowerController(0, 0, 0, 0, 0, null);
		menu = new Menu();
		score = new Score();
		instrucoes = new Instrucoes();

		try {// FONTES
			power = Font.createFont(Font.TRUETYPE_FONT, stream1).deriveFont(65f);
			timeFont = Font.createFont(Font.TRUETYPE_FONT, stream2).deriveFont(55f);
			pixel = Font.createFont(Font.TRUETYPE_FONT, stream3).deriveFont(15f);
			pixelBold = Font.createFont(Font.TRUETYPE_FONT, stream4).deriveFont(50f);
		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void initFrame() {
		frame = new JFrame("Tower S.E");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		Image icon = null;// Icone
		try {
			icon = ImageIO.read(getClass().getResource("/icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setIconImage(icon);
		// CURSOR
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(getClass().getResource("/cursor.png"));
		Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
		frame.setCursor(c);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}

	public void tick() {

		if (gameState == "NORMAL") {// Modo de jogo em andamento
			Sound.soundtrack.loop();
			Game.time = Game.time - 1;
			if (Game.time <= 0) {
				Game.gameState = "SCORE";
				win = true;
				Sound.win.play();
			}else if(Game.life <= 0) {
				Game.gameState = "SCORE";
				lose = true;
				Sound.lose.play();
			}

			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}

			towerController.tick();

		} else if (gameState == "MENU") {// Modo de jogo no Menu
			menu.tick();

		} else if (gameState == "SCORE") {
			score.tick();
		}

	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = image.getGraphics();
		g.setColor(new Color(122, 102, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		/* Renderização do jogo */
		// Graphics2D g2 = (Graphics2D) g;

		world.render(g);
		towerController.render(g);

		Collections.sort(entities, Entity.nodeSorter);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}

		/***/
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

		ui.render(g);
		// SEM SALDO
		if (money < price) {

			g.setColor(new Color(255, 0, 0));
			g.setFont(pixel);
			g.drawString("Saldo Insuficiente", 500, 31);
		}

		if (gameState == "NORMAL") {
			// TIME
			g.setColor(new Color(255, 255, 255));
			g.setFont(pixel);
			g.drawString("TEMPO: " + (time / 60), 380, 32);

		} else if (gameState == "MENU") {// Menu de opções
			menu.render(g);
		} else if (gameState == "SCORE") {// Modo de jogo finalizado
			score.render(g);
		} else if (gameState == "INSTRUCOES") {// Instruções
			instrucoes.render(g);
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
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}

		}

		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Verificação das teclas de navegação
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			Sound.move.play();
			if (gameState == "MENU") {
				menu.up = true;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Sound.move.play();
			if (gameState == "MENU") {
				menu.down = true;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Sound.select.play();
			if (gameState == "MENU") {
				menu.enter = true;
				towerController.isPressed = false;
			}
			if (gameState == "GAMEOVER") {
				World.restartGame();
				Game.gameState = "NORMAL";

			}
			if (gameState == "SCORE") {
				World.restartGame();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Sound.select.play();
			if (gameState == "NORMAL") {
				gameState = "MENU";
				menu.pause = true;
			} else if (gameState == "INSTRUCOES") {
				gameState = "MENU";
				menu.pause = false;
			}

		}
		if (gameState == "SCORE") {
			if (e.getKeyCode() == KeyEvent.VK_X) {
				System.exit(1);
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

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
		// System.out.println("MOUSE ");
		towerController.isPressed = true;
		towerController.xTarget = e.getX() / 3;
		towerController.yTarget = e.getY() / 3;
		

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
