package test;
import org.junit.jupiter.api.Test;
import ragmad.GameEngine;
import ragmad.scenes.mainmenu.MainMenu;
import ragmad.scenes.settingsscene.Settings;

import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {
    @Test
    void changeSceneMethodWorks(){
        String SettingsMenuPath = "res/settings_menu.jpeg"; // background image of the settings menu.
		Settings settings= new Settings(200, 200, SettingsMenuPath);

        String background_path = "res/main_menu_temp.jpg";
		MainMenu menu = new MainMenu(200, 200, background_path);


        GameEngine gameEngine = new GameEngine(200,200, menu, settings );
        assertTrue(gameEngine.getCurrentScene() instanceof MainMenu);
        gameEngine.ChangeScene("Settings");
        assertTrue(gameEngine.getCurrentScene() instanceof Settings);
    }
}
