package ragmad;

import java.nio.file.Paths;

import ragmad.graphics.sprite.Sprite;
import ragmad.io.Keyboard;
import ragmad.scenes.Scene;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.gamescene.Tile;
import ragmad.scenes.mainmenu.MainMenu;
import ragmad.scenes.settingsscene.Settings;
import ragmad.sound_engine.Sound;

/**
 * Renders the game to the screen
 */
public class GameEngine implements Runnable {

	private GameWindow window;



	private static Scene currentScene;
	static MainMenu menu;
	static Settings settings;
	static GameScene gamescene;
	
	private static Sound soundEngine;
	
	private Thread thread;
	private boolean running;
	public static int frames_per_sec = 60;
	
	private static int[] pixels; 	// Every class should use this pixels to render!!
	private static int m_width;
	private static int m_height;
	
	private boolean paused;
	
	
	
//-------------- Constructors Area ---------------
	/**
	 * Build a game prototype with the given size.
	 * @param width - game width
	 * @param height - game height
	 */
	public GameEngine(int width, int height) {
		m_height = height;
		m_width = width;
		this.paused = false;
		
		window = new GameWindow(width, height);
		pixels = window.getPixels();
		soundEngine = new Sound();
	}
	
//	
//	/**
//	 * Build a game prototype with the given size.
//	 * @param m_width - game width
//	 * @param m_height - game height
//	 * @param main - Takes an initial scene (Main menu scene)
//	 * @param settings - A defualt settings Scene.
//	 */
//	public GameEngine(int m_width, int m_height, MainMenu main, Settings setting) {
//		this.paused = false;
//		window = new GameWindow(m_width, m_height);
//		pixels = window.getPixels();
//		this.m_height = m_height; 
//		soundEngine = new Sound();
//		this.m_width = m_width;
//		this.menu = main;
//		currentScene = main;
//		this.settings = setting;
//	}
	
	
//-------------- Methods Area --------------------
	
	/**
	 * An update function that updates the game componenets per frame
	 */
	private void update() {
		this.currentScene.update();
		this.window.update();
	}
	
	
	/**
	 * A rendering function called per frame
	 */
	private synchronized void render() {
		for(int i = 0 ;i < pixels.length ; i++) {pixels[i] = 0xff87CEEB;}
		
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
		int ups = 0;
		while(running) {
			long currTime = System.nanoTime();
			delta += (currTime - prevTime) / nanoSec; // Converting it to sec after getting the time diff
			prevTime = currTime;
			
			while(delta >= 1) {
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


	/**
	 * Changes the current scene to the one provided in parameter
	 * @param scene scene to be displayed
	 */
	public static synchronized void ChangeScene(String scene) {
		if(scene.equals("GameScene")){
			currentScene = gamescene;
		}
		else if(scene.equals("Menu")) {
			currentScene = menu; 
		}
		else if(scene.equals("Settings")) {
			currentScene = settings;
		}
	}
//---------------------- Setters Area -----------------

	/**
	 * Initializes Game Scene to the one specified GameScene Object and sets the current scene to Game Scene.
	 * @param gs - a GameScene Object
	 */
	public static void InitGameScene(GameScene gs) 	{gamescene = gs; currentScene = gamescene;}

	/**
	 * Initializes Main Menu Scene to the one specified MainMenu Object and sets the current scene to Main Menu.
	 * @param mm - a MainMenu Object
	 */
	public static void InitMainMenu(MainMenu mm) 	{menu = mm; currentScene = menu;}

	/**
	 * Initializes Settings Scene to the one specified Settings Object and sets the current scene to Settings Scene
	 * @param st - a Settings Object
	 */
	public static void InitSettings(Settings st)	 {settings = st; currentScene = settings;}
	
//------------------- Getters Area -----------------------
	/**
	 * Every game component and object should access this pixels array freely.
	 * @return - The pixels array in which game components should render into. It will be copied to the GameCanvas Image
	 */
	public static synchronized int[] GetPixels() {return pixels;}

	/**
	 * Get the width of the window
	 * @return width of type integer
	 */
	public static int GetWidth() {return m_width;}

	/**
	 * Get height of the window
	 * @return height of type integer
	 */
	public static int GetHeight() {return m_height;}
	//public static double GetDelta()  {return delta;}

	/**
	 * Gets the sound engine
	 * @return an instance of the sound engine
	 */
	public static Sound GetSoundEngine() {return soundEngine;}

	/**
	 * Gets the current scene being displayed on the screen
	 * @return an instance of the current scene
	 */
	public static Scene getCurrentScene() {return currentScene;}
}
//remove getParent()