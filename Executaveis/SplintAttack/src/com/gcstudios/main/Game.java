package com.gcstudios.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

import com.gcstudios.entities.ArrowPack;
import com.gcstudios.entities.Bird;
import com.gcstudios.entities.BulletShoot;
import com.gcstudios.entities.BulletShoot2;
import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Ninho;
import com.gcstudios.entities.Player;
import com.gcstudios.entities.ShieldPack;
import com.gcstudios.graficos.Spritesheet;
import com.gcstudios.graficos.UI;
import com.gcstudios.world.EnemyGenerator;
import com.gcstudios.world.PackGenerator;
import com.gcstudios.world.World;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener {
	// VARIAVEIS
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 138;
	public static final int HEIGHT = 190;
	public static final int SCALE = 3;
	public static String gamestate = "MENU";
	public static int win = 0;
	public static int levelGame = 1;
	public int backY = 0;
	public int backY2 = 180;
	public int backspeed = 1;
	public static boolean restart = false;
	public static double score = 0;

	// LISTAS
	public static List<Entity> entities;
	public static List<Entity> inimigos;
	public static List<BulletShoot> bullets;
	public static List<BulletShoot2> bullets2;
	// OBJETOS
	private BufferedImage image;
	public BufferedImage background1;
	public BufferedImage background2;
	public UI ui;
	public static Spritesheet spritesheet;
	public static Player player;
	public static Bird bird;
	public static EnemyGenerator enemygenerator;
	public static PackGenerator packgenerator;
	public static ArrowPack arrowpack;
	public static ShieldPack shieldp;
	public static Menu menu;
	public static Instrucoes instrucoes;
	public static Ninho ninho;
	// CUTSCENE
	public static int entrada = 0;
	public static int comecar1 = 1;
	public static int jogando = 10;
	public static int estado_cena = entrada;
	private int timecena1 = 0, maxTimecena1 = 60 * 4;

	// ESTILO DE FONTE
	public static InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("pixelmix.ttf");
	public static InputStream stream2 = ClassLoader.getSystemClassLoader().getResourceAsStream("pixel.ttf");
	public static InputStream stream12 = ClassLoader.getSystemClassLoader().getResourceAsStream("pixelmix_bold.ttf");
	public static InputStream stream3 = ClassLoader.getSystemClassLoader().getResourceAsStream("time.ttf");
	public static InputStream stream4 = ClassLoader.getSystemClassLoader().getResourceAsStream("seven.ttf");
	public static InputStream stream5 = ClassLoader.getSystemClassLoader().getResourceAsStream("texto.ttf");
	public static InputStream stream6 = ClassLoader.getSystemClassLoader().getResourceAsStream("algebrian.ttf");
	public static Font newfont1, newfont12, newfont2, fonttime, seven, texto, algebrian;

	public Game() {
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		// INICIALIZANDO OBJETOS
		spritesheet = new Spritesheet("/spritesheet.png");
		entities = new ArrayList<Entity>();
		inimigos = new ArrayList<Entity>();
		bullets = new ArrayList<BulletShoot>();
		bullets2 = new ArrayList<BulletShoot2>();
		player = new Player(5, HEIGHT - 16, 16, 16, 1.5, spritesheet.getSprite(0, 0, 16, 16));
		bird = new Bird(Game.WIDTH / 2, Game.HEIGHT - 39, 22, 15, 1, Game.spritesheet.getSprite(0, 16, 22, 17));
		ninho = new Ninho(50, -16, 16, 16, 1, Game.spritesheet.getSprite(48, 64, 16, 16));
		ui = new UI();
		menu = new Menu();
		instrucoes = new Instrucoes();

		try {// FONTES
			newfont1 = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(20f);
			newfont12 = Font.createFont(Font.TRUETYPE_FONT, stream12).deriveFont(40f);
			newfont2 = Font.createFont(Font.TRUETYPE_FONT, stream2).deriveFont(40f);
			fonttime = Font.createFont(Font.TRUETYPE_FONT, stream3).deriveFont(40f);
			seven = Font.createFont(Font.TRUETYPE_FONT, stream4).deriveFont(50f);
			texto = Font.createFont(Font.TRUETYPE_FONT, stream5).deriveFont(19f);
			algebrian = Font.createFont(Font.TRUETYPE_FONT, stream6).deriveFont(67f);
		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			background1 = ImageIO.read(getClass().getResource("/back1.png"));
			background2 = ImageIO.read(getClass().getResource("/back1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		enemygenerator = new EnemyGenerator();
		packgenerator = new PackGenerator();
		entities.add(player);
		entities.add(bird);
		entities.add(ninho);
	}

	public void initFrame() {
		frame = new JFrame("..:: Splint Attack ::..");
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
		Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");

		frame.setCursor(c);
		frame.setIconImage(icon);
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
		// System.out.println(gamestate);
		// ESTADO DO JOGO
		if (gamestate == "NORMAL") {
			Sound.theme.loop();
			if (estado_cena == jogando) {// JOGO RODANDO

				for (int i = 0; i < entities.size(); i++) {
					Entity e = entities.get(i);
					e.tick();
				}

				for (int i = 0; i < inimigos.size(); i++) {
					Entity e = inimigos.get(i);
					e.tick();
				}

				for (int i = 0; i < bullets.size(); i++) {
					bullets.get(i).tick();
				}
				for (int i = 0; i < bullets2.size(); i++) {
					bullets2.get(i).tick();
				}

				packgenerator.tick();
				enemygenerator.tick();
				ui.tick();

				// SISTEMA DE BACKGROUND
				backY += backspeed;
				if (backY >= background1.getHeight()) {
					backY = 0;
				}

			} else {
				if (estado_cena == entrada) {// SISTEMA DE INICIACAO DE CUTSCENE
					if (levelGame == 1) {// level 1
						if (Game.player.getX() < 100) {
							Game.player.x++;
							if (Game.player.getX() == 60) {
								estado_cena = comecar1;
							}
						}
					}
				} else if (estado_cena == comecar1) {// ENTRADA LEVEL 1
					timecena1++;
					if (timecena1 == maxTimecena1) {
						estado_cena = jogando;
						timecena1 = 0;
					}
				}
			}

		} else if (gamestate == "MENU") {// PAUSE OU MENU INICIAL
			menu.tick();

		} else if (gamestate == "WIN") {// JOGO CONCLUIDO, SOMENTE OS PLAYERS RENDERIZADOS
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
			Sound.win.loop();
		}

	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.drawImage(background1, 0, backY, null);
		g.drawImage(background2, 0, backY - background1.getHeight(), null);

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(255, 255, 255, 120));
		g2.fillRect(0, 0, WIDTH, HEIGHT);

		/* Renderização do jogo */
		// Graphics2D g2 = (Graphics2D) g;
		Collections.sort(entities, Entity.nodeSorter);

		// ESTADO DO JOGO
		if (gamestate == "NORMAL") {// JOGO RODANDO

			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.render(g);
			}

			for (int i = 0; i < inimigos.size(); i++) {
				Entity e = inimigos.get(i);
				e.render(g);
			}
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).render(g);
			}
			for (int i = 0; i < bullets2.size(); i++) {
				bullets2.get(i).render(g);
			}

			packgenerator.render(g);

		} else if (gamestate == "WIN") {// JOGO CONCLUIDO
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.render(g);
			}

		}

		/***/
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		ui.render(g);

		// CUTSCENE RENDER
		if (estado_cena == comecar1) {
			if (timecena1 >= 0 && timecena1 <= 80) {
				g.setFont(newfont12);
				g.setColor(new Color(0, 0, 50));
				g.drawString("LEVEL " + levelGame, 110, 240);

			} else if (timecena1 > 80 && timecena1 <= 160) {
				g.setFont(newfont12);
				g.setColor(new Color(0, 0, 50));
				g.drawString("READY!", 125, 240);

			} else if (timecena1 > 160 && timecena1 < 239) {
				g.setFont(newfont12);
				g.setColor(new Color(0, 0, 50));
				g.drawString("GO!", 170, 240);
			}
		}
		if (gamestate == "MENU") {// ESTADO MENU
			menu.render(g);
		} else if (gamestate == "INSTRUCOES") {// ESTADO INSTRUCOES
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
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
			if (gamestate == "MENU") {
				menu.right = true;
			}
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
			if (gamestate == "MENU") {
				menu.left = true;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {

			if (gamestate == "MENU") {
				menu.down = true;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			if (gamestate == "MENU") {
				menu.up = true;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_X) {
			player.fire = true;
			if (Game.player.pedras <= 0) {
				Sound.noammopedra.play();
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			player.fireArrow = true;
			if (Game.player.flechas <= 0) {
				Sound.noammo.play();
			}
		}

		if (gamestate == "MENU") {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				menu.enter = true;

			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (gamestate == "NORMAL") {
				Sound.pause.play();
				gamestate = "MENU";
				menu.pause = true;
			}
		}

		if (gamestate == "WIN") {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				Game.gamestate = "MENU";
			}
		}

		if (gamestate == "GAMEOVER") {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				World.restartGame();
				return;
			}
		}

		if (gamestate == "INSTRUCOES") {
			if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				Sound.select.play();
				Game.gamestate = "MENU";
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.fire = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_X) {
			player.fireArrow = false;
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
