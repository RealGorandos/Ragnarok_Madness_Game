package ragmad.sound_engine;

import java.io.File;
/*
import java.io.FileInputStream;
import java.io.InputStream;
*/
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	public void setMenuMusic(String audioName) {
		
		try {
			File path = new File(audioName);
			if(path.exists()) {
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
					clip = AudioSystem.getClip();
					clip.open(audioInput);
					clip.start();
					clip.loop(clip.LOOP_CONTINUOUSLY);
			}else {
				System.out.println("File not found!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void stopMenuMusic() {
		clip.stop();
	}
	
	
public void buttonSound() {
		
		try {
			File path = new File(".//ragmad//sound_engine//themes//button_sound.wav");
			if(path.exists()) {
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
					clip = AudioSystem.getClip();
					clip.open(audioInput);
					clip.start();

			}else {
				System.out.println("File not found!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
