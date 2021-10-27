package ragmad.sound_engine;

import java.nio.file.Paths;

public class MusicClips {
	
    public final static MusicClips MAINMENU = new MusicClips(Paths.get("").toAbsolutePath().getParent().toString() + "/res/sounds/got.wav");
    public final static MusicClips BUTTON = new MusicClips(Paths.get("").toAbsolutePath().getParent().toString() + "/res/sounds/button_sound.wav");
    
    
    private final String path;
    
    public MusicClips(String s) {
        path = s;
    }

    public String toString() {
        return this.path;
    }
}
