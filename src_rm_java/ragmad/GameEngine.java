package ragmad;

import java.nio.file.Paths;

import ragmad.io.Keyboard;
import ragmad.scenes.Scene;
import ragmad.scenes.mainmenu.MainMenu;

public class GameEngine implements Runnable {
	
	private GameWindow window;
	
	private static Scene currentScene; 
	
	public static int[] pixels; 	// Every class should use this pixels to render!!
	
	private Thread thread;
	private boolean running;
	
	private static int m_width;

	private static int m_height;
	
	private boolean paused;
	
//-------------- Constructors Area ---------------
	/**
	 * Build a game prototype with the given size.
	 * @param m_width - game width
	 * @param m_height - game height
	 */
	public GameEngine(int m_width, int m_height) {
		this.paused = false;
		window = new GameWindow(m_width, m_height);
		pixels = new int[m_width*m_height];
		this.m_height = m_height;
		this.m_width = m_width;
		String background_path = Paths.get("").toAbsolutePath().getParent().toString() + "\\res\\main_menu_temp.jpg";	// TESTING
		currentScene = new MainMenu(m_width, m_height, background_path);	// TESTING
		
	}
	
	
//-------------- Methods Area --------------------
	
	/**
	 * An update function that updates the game componenets per frame
	 */
	private void update() {
		if (Keyboard.esc()) 			
			this.paused = !this.paused;
		if (this.paused && !(this.currentScene instanceof MainMenu)) { 
			System.out.println("game paused");
			return;
		}
		this.currentScene.update();
		this.window.update();
	}
	
	
	/**
	 * A rendering function called per frame
	 */
	private void render() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0; ///clearing the screen for the next Background
		}
		this.currentScene.render(); // TESTING
		this.window.render();
	}
	
	
	/**
	 * working with blocks of priority and starting the program
	 */
	public synchronized void start() {
		running = true;
		thread = new Thread(this,"Display");
		thread.start();
	}
	
	
	/**
	 * Running every the game timer
	 */
	public void run() {
		long prevTime = System.nanoTime(); //Storing the time before looping
		long timer = System.currentTimeMillis();
		final double nanoSec = 1000000000.0 / 60.0;
		double delta = 0; 
		
		int fps = 0;
		while(running) {
			long currTime = System.nanoTime();
			delta += (currTime - prevTime) / nanoSec; // Converting it to sec after getting the time diff
			prevTime = currTime;
			
			while(delta >= 1) {
				//FOR TESTING!!
				this.update();
				this.render();
				fps++;
				delta--;
			}
			
			//Show the result after completing 1 sec to measure the fps
			if(System.currentTimeMillis() - timer > 1000) {
				timer+= 1000; //To keep tracking with real time process shifting (It is not accurate 100%)
				System.out.println(fps + " updates");
				fps = 0;
			}
		 }
	}
	
	
	/**
	 * Stop the threading which stops the program
	 */
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	
//------------------- Getters Area -----------------------
	/**
	 * Every game component and object should access this pixels array freely.
	 * @return - The pixels array in which game components should render into. It will be copied to the GameCanvas Image
	 */
	public static int[] getPixels() {
		return pixels;
	}
	
	
	public static void ChangeScene() {
		System.out.println("Scene changed");
	}
	
}
