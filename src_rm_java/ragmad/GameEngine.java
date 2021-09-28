package ragmad;

import java.nio.file.Paths;

import ragmad.io.Keyboard;
import ragmad.scenes.Scene;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.mainmenu.MainMenu;
import ragmad.sound_engine.Sound;

public class GameEngine implements Runnable {
	
	private GameWindow window;
	
	private static Scene currentScene; 
	
	private static int[] pixels; 	// Every class should use this pixels to render!!
	private static double delta;
	private static Sound soundEngine;
	
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
		soundEngine = new Sound();
		this.m_width = m_width;
		String background_path = Paths.get("").toAbsolutePath().getParent().toString() + "\\res\\main_menu_temp.jpg";	// TESTING
		currentScene = new MainMenu(m_width, m_height, background_path);	// TESTING
		delta = 0;
		
	}
	
	
//-------------- Methods Area --------------------
	
	/**
	 * An update function that updates the game componenets per frame
	 */
	private void update() {
		if (Keyboard.esc()) 			
			this.paused = !this.paused;
		if (this.paused && !(currentScene instanceof MainMenu)) { 
			System.out.println("game paused");
			return;
		}
		this.currentScene.update();
		this.window.update();
	}
	
	
	/**
	 * A rendering function called per frame
	 */
	private synchronized void render() {
		for(int i =0 ;i < pixels.length ; i++) {pixels[i] = 0xff87CEEB;}
		currentScene.render(); 
		
		
		
		this.window.render(); // draw the pixels that we have currently to the window
	}
	
	
	/**
	 * working with blocks of priority and starting the program
	 */
	public synchronized void start() {
		running = true;
		thread = new Thread(this,"Display");
		thread.start();
		soundEngine.start();
		//GameEngine.soundEngine.updateAudio(".//ragmad//sound_engine//themes//got.wav", 7000);
	}
	
	
	/**
	 * Running every the game timer
	 */
	public void run() {
		long prevTime = System.nanoTime(); //Storing the time before looping
		long timer = System.currentTimeMillis();
		final double nanoSec = 1000000000.0 / 60.0;
		delta = 0; 
		
		int fps = 0;
		int ups = 0;
		while(running) {
			long currTime = System.nanoTime();
			delta += (currTime - prevTime) / nanoSec; // Converting it to sec after getting the time diff
			prevTime = currTime;
			
			while(delta >= 1) {
				//FOR TESTING!!
				this.update();
				ups++;
				
				delta--;
			}
			
			fps++;
			this.render();
			//Show the result after completing 1 sec to measure the fps
			if(System.currentTimeMillis() - timer > 1000) {
				timer+= 1000; //To keep tracking with real time process shifting (It is not accurate 100%)
				System.out.println(ups + " updates, " + fps + " frames" );
				fps = 0;
				ups = 0;
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
	
	
	
	public static synchronized void ChangeScene() {
		String mapPath = Paths.get("").toAbsolutePath().getParent().toString() + "\\res\\map1.png";
		currentScene = new GameScene(m_width, m_height,mapPath);
		
	}
	
	
//------------------- Getters Area -----------------------
	/**
	 * Every game component and object should access this pixels array freely.
	 * @return - The pixels array in which game components should render into. It will be copied to the GameCanvas Image
	 */
	public static synchronized int[] GetPixels() {return pixels;}
	public static int GetWidth() {return m_width;}
	public static int GetHeight() {return m_height;}
	public static double GetDelta()  {return delta;}
	public static Sound GetSoundEngine() {return soundEngine;}
	
}
