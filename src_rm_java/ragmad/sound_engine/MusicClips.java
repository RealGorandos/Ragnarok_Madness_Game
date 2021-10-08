package ragmad.sound_engine;

import java.nio.file.Paths;

public enum MusicClips {
    MAINMENU (Paths.get("").toAbsolutePath().getParent().toString()+"//res//sounds//got.wav"),
    BUTTON (Paths.get("").toAbsolutePath().getParent().toString()+"//res//sounds//got.wav");
    private final String path;

    private MusicClips(String s) {
        path = s;
    }


    public String toString() {
        return this.path;
    }
}
