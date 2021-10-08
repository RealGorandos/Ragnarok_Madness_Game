package ragmad.sound_engine;

import java.nio.file.Paths;

public enum MusicClips {
    MAINMENU (Paths.get("").toAbsolutePath().toString()+"//src_rm_java//ragmad//sound_engine//themes//got.wav"),
    BUTTON (Paths.get("").toAbsolutePath().toString()+"//src_rm_java//ragmad//sound_engine//themes//got.wav");
    private final String path;

    private MusicClips(String s) {
        path = s;
    }


    public String toString() {
        return this.path;
    }
}
