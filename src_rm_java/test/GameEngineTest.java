package test;
import org.junit.jupiter.api.Test;
import ragmad.GameEngine;
import ragmad.scenes.mainmenu.MainMenu;
import ragmad.scenes.settingsscene.Settings;

import static org.junit.jupiter.api.Assertions.*;

public class GameEngineTest {
    @Test
    void changeSceneMethodWorks(){
        GameEngine gameEngine = new GameEngine(200,200);
        assertTrue(gameEngine.getCurrentScene() instanceof MainMenu);
        gameEngine.ChangeScene("Settings");
        assertTrue(gameEngine.getCurrentScene() instanceof Settings);
    }
}
