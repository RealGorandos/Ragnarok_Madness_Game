package test;

import org.junit.jupiter.api.Test;
import ragmad.sound_engine.MusicClips;
import ragmad.sound_engine.Sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

import static org.junit.jupiter.api.Assertions.*;

public class SoundEngine {
    @Test
    void soundUpdateMethodWorks() throws LineUnavailableException {
        Sound sound = new Sound();
        sound.openAudio(MusicClips.MAINMENU.toString(),1000);
        assertTrue(sound.isClipRunning());
    }

    @Test
    void soundPauseMethodWorks(){
        Sound sound = new Sound();
        sound.openAudio(MusicClips.MAINMENU.toString(),1000);
        sound.pauseSound();
        assertFalse(sound.isClipRunning());
    }
}
