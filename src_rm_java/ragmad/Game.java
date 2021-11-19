package ragmad;

import java.nio.file.Paths;
import java.util.HashMap;

import ragmad.entity.characters.Direction;
import ragmad.entity.characters.Player;
import ragmad.graphics.sprite.Sprite;
import ragmad.graphics.sprite.SpriteSheet;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.gamescene.Map;
import ragmad.scenes.gamescene.Tile;
import ragmad.scenes.mainmenu.MainMenu;
import ragmad.scenes.settingsscene.Settings;
import ragmad.sound_engine.MusicClips;

/**
 * Initializes the Game resources, sprites, and the rest of the Game Engine. It is the GameCore in other words.
 * This class was created to seperate the GameEngine from the Game itself. The Game engine can be used for different Isometric games and Ragnarok Madness is just build on top of it!
 * @author Mohido
 *
 */
public class Game {
 
	
	/*Sound Paths*/
    final String MUSIC_GOT_URL = Paths.get("").toAbsolutePath().getParent()  + "/res/sounds/got.wav";
    final String SOUND_SLASH_URL = Paths.get("").toAbsolutePath().getParent()  +  "/res/sounds/button_sound.wav";
    
    
    /*Image Paths*/
    final String MENU_IMAGE_URL =  Paths.get("").toAbsolutePath().getParent()  + "/res/main_menu_temp.jpg";
    final String SETTINGS_IMAGE_URL = Paths.get("").toAbsolutePath().getParent()  +  "/res/settings_menu.jpeg";
    
	/*Loading Spritesheets.*/
	final SpriteSheet DESERT_SHEET = new SpriteSheet(Paths.get("").toAbsolutePath().getParent()  +  "/res/desert_res_orig.png");
	final SpriteSheet PORTAL_SHEET = new SpriteSheet(Paths.get("").toAbsolutePath().getParent()  + "/res/porotals.png");
	final SpriteSheet PLAYER_SHEET = new SpriteSheet( Paths.get("").toAbsolutePath().getParent() + "/res/jaden_yuki_2.png");
	
	/*Loading Sprites*/
	final Sprite DESERT_TILE_1 = new Sprite(DESERT_SHEET, 0, 0, 64, 32);
	final Sprite DESERT_TILE_2 = new Sprite(DESERT_SHEET, 0, 1, 64, 32);
	final Sprite PORTAL_TILE_1 = new Sprite(PORTAL_SHEET, 0, 0, 128, 64, -1, 1, 0);

	

	/**
	 * Here we load all the GameProperties and initializes the Game engine.
	 * @param width width of the game window
	 * @param height height of the game window
	 */
	public Game(int width, int height) {
		
		// Create A Game Engine.
		GameEngine engine = new GameEngine(width, height);
		
		// Initializing the player.
		Player player = initPlayer(); 
		
		
		//Creates a GameScene
		GameScene gameScene = initGameScene(player);
		gameScene.zoomIn();
		
		// Creates a Main Menu
		MainMenu mainMenu = initMainMenu();
		
		
		//Create a Settings Menu
		Settings settings = new Settings(GameEngine.GetWidth(), GameEngine.GetHeight(), SETTINGS_IMAGE_URL );
		
		//Initializing the Game engine with the current scenes
		engine.InitGameScene(gameScene);
		engine.InitSettings(settings);
		engine.InitMainMenu(mainMenu);
		engine.ChangeScene("Menu");
		
		engine.start();
		
	}

	/**
	 * Initialises the Main Menu scene
	 * @return main menu
	 */

	private MainMenu initMainMenu() {
		//Init Main Menu Scene
		String[] opts = new String[3];
		String[] buttonSounds = new String[3];
		opts[0] = "Start";		buttonSounds[0] =  SOUND_SLASH_URL;
		opts[1] = "Settings";	buttonSounds[1] =  SOUND_SLASH_URL;
		opts[2] = "Exit";		buttonSounds[2] =  SOUND_SLASH_URL;
		return new MainMenu(GameEngine.GetWidth(), GameEngine.GetHeight(), MENU_IMAGE_URL, opts, buttonSounds, MUSIC_GOT_URL);
	}
	
	
	
	private GameScene initGameScene(Player player) {
		// Initialize GameScene.
		HashMap<Integer, Tile> colorMap = new HashMap<Integer, Tile>();
		colorMap.put( 0xff0032ff, new Tile(0, PORTAL_TILE_1, true));
		colorMap.put( 0xff8e4a4a, new Tile(0, DESERT_TILE_1, false));
		
		String mapPath =  Paths.get("").toAbsolutePath().getParent()  +  "/res/map4.png";
		Map map = new Map(mapPath, colorMap);
		
		return new GameScene(GameEngine.GetWidth(), GameEngine.GetHeight(), map, player);
		
	}
	
	
	
	
	
	
	private Player initPlayer() {
		/*Initialization the Sprites of the player*/
		int spriteWidth = 7; //Sprites per Direction
		int spriteHeight = 8; //Directions
		Sprite[] playerSprites = new Sprite[spriteWidth*spriteHeight];
		
		
		
		/*	Note!!!
		 * 	Mohido: This algorithm that will load all the sprites
		 * 		from the sprite sheet specifically for the Given sprite sheet. (Jaden Yuuki)
		 * 
		 */
		/*Sprites of the basic directions.*/
		int basicDirectionSprites = spriteWidth*4;				 //Up,Left,Right,Down
		int c_r = 0;											//current row
		int i_s = 0;											// index_sprite_spriteSheet
		while(i_s < basicDirectionSprites){	
			int c_c = 0; // current column
			while(c_c < spriteWidth) {
				playerSprites[c_c + c_r*spriteWidth] = new Sprite(PLAYER_SHEET, i_s % 10 , Math.floorDiv(i_s, 10), 51, 84); // "/" floors the value.
				c_c++; i_s++;
			}
			c_r++;
		}
		
		/*Sprites of the Diagonal Directions (SpriteSheet is devided into 2, basic and diagonals). The only difference is that, there is a Bias in the Sprite assignment.*/
		i_s = 0;	 										// index_sprite_spriteSheet
		while(i_s < basicDirectionSprites){	
			int c_c = 0; // current column
			while(c_c < spriteWidth) { 
				playerSprites[c_c + c_r*spriteWidth] = new Sprite(PLAYER_SHEET, (i_s % 10), Math.floorDiv(i_s, 10) + 3 , 51, 84);
				c_c++; i_s++;
			}
			c_r++;
		} 
		
		/*Creating Direction -> Sprite map*/
		HashMap<Direction, Integer> dirSprMap = new HashMap<Direction, Integer>(); 
		dirSprMap.put( Direction.DOWN, 2); 
		dirSprMap.put( Direction.LEFT, 3); 
		dirSprMap.put( Direction.UP, 0); 
		dirSprMap.put( Direction.RIGHT, 1); 
		dirSprMap.put( Direction.DOWN_LEFT, 6);
		dirSprMap.put( Direction.UP_LEFT, 7);
		dirSprMap.put( Direction.UP_RIGHT, 4);
		dirSprMap.put( Direction.DOWN_RIGHT, 5);
		
		return new Player(GameEngine.GetWidth()/2, GameEngine.GetHeight()/2, playerSprites, spriteHeight, spriteWidth, dirSprMap);
	}
	
}
