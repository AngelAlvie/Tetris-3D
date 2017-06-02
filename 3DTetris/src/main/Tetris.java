package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.VolatileImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Tetris extends JFrame implements Runnable{
	
	//environment variables
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 600;
	public static int HEIGHT = 600;
	public static Image icon;
	public boolean isRunning = false;
	private long ping = 10;
	private VolatileImage screen;
	// game entities	
	PolyCubeContainer game;
	
	public Tetris() {
		super("3D Tetris version 0.0.1");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		try {
			icon = ImageIO.read(new File("resources/logo.png"));
			this.setIconImage(icon);
		} catch (Exception e){}
		this.setLayout(new FlowLayout());
		// construct any objects here
		game = new PolyCubeContainer();
		
		//add any listeners here
		this.addKeyListener(game);
		
	}
	
	public static void main(String[] args) {
		Tetris t = new Tetris();
		t.setVisible(true);
		t.isRunning = true;
		new Thread(t).start();
	}

	
	public void run() {
		screen = createVolatileImage(WIDTH,HEIGHT);
		while(isRunning) {
			
			tick();
			render();
			
			try {
				Thread.sleep(ping);
			}	catch(Exception e) {
				
			}
		}
	
	}

	public void render() {
		
		//make everything on the volatile image
		Graphics g = screen.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//run all the render routines on different objects in the frame
		game.render(g);
		//draw a buffered image to actual graphics of the JFrame
		Graphics g1 = getGraphics();
		g1.drawImage(screen, 0, 0, null);
		g1.dispose();
		
		
		
	}

	public void tick() {
		
	}
}
