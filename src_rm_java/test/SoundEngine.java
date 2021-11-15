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
        try{
            MusicClips MAINMENU = new MusicClips ("res/sounds/got.wav");
            Sound sound = new Sound();
            sound.openAudio(MAINMENU.toString(), 0);
            assertTrue(sound.isClipRunning());
        }catch(Exception e){
            System.out.println("[WARNING] Can't interpret the given .wav format on this machine. Or Can't file the given music file");
        }

    }

    @Test
    void soundPauseMethodWorks(){
         try{
            MusicClips MAINMENU = new MusicClips ("res/sounds/got.wav");
            Sound sound = new Sound();
            sound.openAudio(MAINMENU.toString(), 0);
            sound.pauseSound();
            assertFalse(sound.isClipRunning());
        }catch(Exception e){
            System.out.println("[WARNING] Can't interpret the given .wav format on this machine. Or Can't file the given music file");
        }
    }
}
