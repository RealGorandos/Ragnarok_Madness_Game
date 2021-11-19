package ragmad.sound_engine;

import java.nio.file.Paths;

/**
 * Initialises the music clips for the game
 */
public class MusicClips {
	
    public final static MusicClips MAINMENU = new MusicClips( "res/sounds/got.wav");
    public final static MusicClips BUTTON = new MusicClips( "res/sounds/button_sound.wav");
    
    
    private final String path;
    
    public MusicClips(String s) {
        path = s;
    }

    public String toString() {
        return this.path;
    }
}
