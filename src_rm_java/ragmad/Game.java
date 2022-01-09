package ragmad;

import java.nio.file.Paths;
import java.util.HashMap;

import ragmad.entity.characters.Direction;
import ragmad.entity.characters.Player;
import ragmad.entity.characters.npc.Foe;
import ragmad.entity.item.WeaponItem;
import ragmad.graphics.sprite.Sprite;
import ragmad.graphics.sprite.SpriteSheet;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.gamescene.Map;
import ragmad.scenes.gamescene.Tile;
import ragmad.scenes.mainmenu.MainMenu;
import ragmad.scenes.settingsscene.Settings;

/**
 * Initializes the Game resources, sprites, and the rest of the Game Engine. It is the GameCore in other words.
 * This class was created to seperate the GameEngine from the Game itself. The Game engine can be used for different Isometric games and Ragnarok Madness is just build on top of it!
 * @author Mohido
 *
 */
public class Game {
//	/*Sound Paths*/
final String MUSIC_GOT_URL = Paths.get("").toAbsolutePath().getParent()  + "/res/sounds/intro.wav";
//For copyright:-
//	Credits (please copy paste)
//	Music: TheFatRat & Everen Maxwell - Warbringer (feat. Lindsey Stirling)
//	Watch the official music video: https://youtu.be/qvH70pJFKps
//	Listen here: https://lnk.to/tfrwarbringeryt
    
    final String SOUND_SLASH_URL = Paths.get("").toAbsolutePath().getParent()  +  "/res/sounds/button_sound.wav";
    
    
    /*Image Paths*/
    final String MENU_IMAGE_URL =  Paths.get("").toAbsolutePath().getParent()  + "/res/main_menu_temp.jpg";
    final String SETTINGS_IMAGE_URL = Paths.get("").toAbsolutePath().getParent()  +  "/res/settings_menu.jpeg";
    
	/*Loading Spritesheets.*/
	final SpriteSheet DESERT_SHEET = new SpriteSheet(Paths.get("").toAbsolutePath().getParent()  +  "/res/desert_res_orig.png");
	final SpriteSheet PORTAL_SHEET = new SpriteSheet(Paths.get("").toAbsolutePath().getParent()  + "/res/porotals.png");
	final SpriteSheet PLAYER_SHEET = new SpriteSheet( Paths.get("").toAbsolutePath().getParent() + "/res/jaden_yuki_2.png");
	final SpriteSheet GRASS_SHEET = new SpriteSheet( Paths.get("").toAbsolutePath().getParent() + "/res/grass.png");
	final SpriteSheet GRAVE_SHEET = new SpriteSheet( Paths.get("").toAbsolutePath().getParent() + "/res/grave2.png");
	final SpriteSheet WATER_MOUNTAIN_SHEET = new SpriteSheet( Paths.get("").toAbsolutePath().getParent() + "/res/water_mountain3.png");
	final SpriteSheet WATER_SHEET = new SpriteSheet( Paths.get("").toAbsolutePath().getParent() + "/res/water.png");
	final SpriteSheet FIRE_SHEET = new SpriteSheet( Paths.get("").toAbsolutePath().getParent() + "/res/fire.png");
	final SpriteSheet CROSS_SHEET = new SpriteSheet( Paths.get("").toAbsolutePath().getParent() + "/res/symbol.png");
	final SpriteSheet CAPSULE_SHEET = new SpriteSheet( Paths.get("").toAbsolutePath().getParent() + "/res/capsules.png");
	final SpriteSheet BULLET_SHEET = new SpriteSheet( Paths.get("").toAbsolutePath().getParent() + "/res/bullet1.png");


	/*Loading Sprites*/
	final Sprite CAPSULE_1 = new Sprite(CAPSULE_SHEET, 0, 0, 32, 32);
	final Sprite BULLET_1 = new Sprite(BULLET_SHEET, 0, 0, 16, 16);
	
	final Sprite DESERT_TILE_1 = new Sprite(DESERT_SHEET, 0, 0, 64, 32);
	final Sprite DESERT_TILE_2 = new Sprite(DESERT_SHEET, 0, 1, 64, 32);
	
	final Sprite WATER_TILE_1 = new Sprite(WATER_SHEET, 0, 0, 64, 32);
	final Sprite FIRE_TILE_1 = new Sprite(FIRE_SHEET, 0, 0, 64, 32);

	final Sprite GRASS_TILE_1 = new Sprite(GRASS_SHEET, 0, 0, 64, 32);
	
	final Sprite GRAVE_TILE_1 = new Sprite(GRAVE_SHEET, 0, 0, 64, 32);

	final Sprite PORTAL_TILE_1 = new Sprite(PORTAL_SHEET, 0, 0, 128, 64, -1, 1, 0);
	final Sprite PORTAL_TILE_2 = new Sprite(PORTAL_SHEET, 1, 0, 128, 64, -1, 1, 0);
	final Sprite PORTAL_TILE_3 = new Sprite(PORTAL_SHEET, 2, 0, 128, 64, -1, 1, 0);
	final Sprite PORTAL_TILE_4 = new Sprite(PORTAL_SHEET, 3, 0, 128, 64, -1, 1, 0);

	final Sprite WATER_MOUNTAIN_TILE_1 = new Sprite(WATER_MOUNTAIN_SHEET, 0, 0, 192, 96, 1,0 , 0);
	//final Sprite CROSS_TILE_1 = new Sprite(CROSS_SHEET, 0, 0, 64, 64, -1, 1, 0);
	

	/**
	 * Here we load all the GameProperties and initializes the Game engine.
	 * @param width width of the game window
	 * @param height height of the game window
	 */
	public Game(int width, int height) {
		// Create A Game Engine.
		GameEngine engine = new GameEngine(width, height);
		
		Map map = initMap();
		
		// Initializing the player. 
		Player player = initPlayer(3,1); 
		//player.setMainWeapon(new WeaponItem("Corruption Pistol", 20, BULLET_1, 0.05, 4));
		player.setMap(map);
		
		
		// Initializing the Foe1.
		Foe foe1 = initFoe1(2,8);
		foe1.setTarget(player);
		foe1.setVisualRange(3, true);
		foe1.setMap(map);
		
		// Initializing the Foe2.
		Foe foe2 = initFoe1(5,8);
		foe2.setTarget(player);
		foe2.setVisualRange(3, true);
		foe2.setMap(map);
		
		
		//Creates a GameScene
		GameScene gameScene = initGameScene(map);
		gameScene.setPlayer(player);
		gameScene.addNPC(foe1);
		gameScene.addNPC(foe2);
		gameScene.zoomIn();
		gameScene.addItemCapsule(3, 1, new WeaponItem("Corruption Pistol", 10, BULLET_1, 0.075, 5, 1), CAPSULE_1);
		
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
	
	
	
	/**
	 * Initializes the map.
	 * @return Map object.
	 */
	private Map initMap() {
		// Initialize GameScene.
		HashMap<Integer, Tile> colorMap = new HashMap<Integer, Tile>();
		
		colorMap.put( 0xffe900f7, new Tile(0, PORTAL_TILE_1, true));
		colorMap.put( 0xffa322ab, new Tile(0, PORTAL_TILE_2, true));
		colorMap.put( 0xff4c214f, new Tile(0, PORTAL_TILE_3, true));
		colorMap.put( 0xff6a1f6f, new Tile(0, PORTAL_TILE_4, true));


		colorMap.put( 0xff5b6065, new Tile(0, WATER_MOUNTAIN_TILE_1, true));
		colorMap.put( 0xff7bc0ee, new Tile(0, WATER_TILE_1, true));
		colorMap.put( 0xffcfe16d, new Tile(0, FIRE_TILE_1, true));

		
		colorMap.put( 0xff4caf50, new Tile(0, GRASS_TILE_1, false));
		colorMap.put( 0xff242323, new Tile(0, GRAVE_TILE_1, true));
		colorMap.put( 0xfff70000, new Tile(40, DESERT_TILE_2, true));

		colorMap.put( 0xff8e4a4a, new Tile(0, DESERT_TILE_1, false));
		 
		String mapPath =  Paths.get("").toAbsolutePath().getParent()  +  "/res/temp20.png";
		return new Map(mapPath, colorMap);
	}
	
	
	
	
	
	/**
	 * Initializes a GameScene object. It needs atleast a player and a map to be initialized.
	 * @param player - Player object.
	 * @param map - Map object.
	 * @return GameScene object which it can be attached to the GameEngine object.
	 */
	private GameScene initGameScene(Map map) {
		return new GameScene(GameEngine.GetWidth(), GameEngine.GetHeight(), map);	
	}
	
	
	
	
	
	/**
	 * Creates a player object on the given coordinates
	 * @param xPos - Isometric x coordinate of where the Player should be spawned
	 * @param yPos - Isometric y coordinate of where the Player should be spawned
	 * @return Player type object. It can be attached to a GameScene object.
	 */
	private Player initPlayer(int xPos, int yPos) {
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
		
		return new Player(xPos, yPos, playerSprites, spriteHeight, spriteWidth, dirSprMap);
	}
	
	

	/**
	 * Similar to create player, it creates a foe on the given coordinates
	 * @param xPos - Isometric x coordinate of where the Foe should be spawned
	 * @param yPos - Isometric y coordinate of where the Foe should be spawned
	 * @return Foe type object. It can be attached to a GameScene object.
	 */
	private Foe initFoe1(int xPos, int yPos) {
		/*Initialization the Sprites of the player*/
		int spriteWidth = 7; //Sprites per Direction
		int spriteHeight = 8; //Directions
		Sprite[] playerSprites = new Sprite[spriteWidth*spriteHeight];
		
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
		
		Foe f =  new Foe(xPos, yPos, playerSprites, spriteHeight, spriteWidth, dirSprMap);
		f.setMainWeapon(new WeaponItem("FoeGun", 5, BULLET_1 , 0.05, 5, 1));
		return f;
	}
	
	
}
