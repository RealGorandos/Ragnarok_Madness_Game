package test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import ragmad.GameEngine;
import ragmad.scenes.mainmenu.MainMenu;
import ragmad.scenes.settingsscene.Settings;

import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {
    /*Sound Paths*/
    static String MUSIC_GOT_URL = "res/sounds/got.wav";
    static String SOUND_SLASH_URL = "res/sounds/button_sound.wav";
    /*image Paths*/
    static String MENU_IMAGE_URL = "res/main_menu_temp.jpg";
    static String SETTINGS_IMAGE_URL = "res/settings_menu.jpeg";


    static MainMenu main;
    static Settings settings;
    static GameEngine gameEngine;

    @BeforeAll
    public static void init(){
        try{
            gameEngine = new GameEngine(200,200);      
            //Init Main Menu Scene
            String[] opts = new String[3];
            String[] buttonSounds = new String[3];
            opts[0] = "Start";		buttonSounds[0] =  SOUND_SLASH_URL;
            opts[1] = "Settings";	buttonSounds[1] =  SOUND_SLASH_URL;
            opts[2] = "Exit";		buttonSounds[2] =  SOUND_SLASH_URL;
            main = new MainMenu(GameEngine.GetWidth(), GameEngine.GetHeight(), MENU_IMAGE_URL, opts, buttonSounds, MUSIC_GOT_URL);
            //Init Settings Menu
            settings = new Settings(GameEngine.GetWidth(), GameEngine.GetHeight(), SETTINGS_IMAGE_URL );
        }catch(Exception e){
            System.out.println("[WARNING] Can't Initialize the GameEngine in Headless mode.");
        }
        
    }
    

    @Test
    void changeSceneMethodWorks(){
        try{
            gameEngine = new GameEngine(200,200); 

            gameEngine.InitMainMenu(main);
            assertTrue(gameEngine.getCurrentScene() instanceof MainMenu);

            gameEngine.InitSettings(settings);
            assertTrue(gameEngine.getCurrentScene() instanceof Settings);
        }catch(Exception e){
            System.out.println("[WARNING] Can't test the GameEngine in Headless mode.");
        }
        
    }
}
