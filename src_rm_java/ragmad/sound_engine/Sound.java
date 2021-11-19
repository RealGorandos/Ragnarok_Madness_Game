package ragmad.sound_engine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

/*
import java.io.FileInputStream;
import java.io.InputStream;
*/
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Sound Engine for the game.
 */
public class Sound extends Thread {

	/**
	 * Checks if a clip is running
	 * @return true if clip is running otherwise false
	 */
	public boolean isClipRunning() {
		return clipRunning;
	}

	private boolean clipRunning;

	public Clip getClip() {
		return clip;
	}

	Clip clip;
	
	long clipTimePosition;
	boolean update;

	public String getCurrentPath() {
		return currentPath;
	}

	String currentPath;
	long delay;

	public boolean isContinous() {
		return continous;
	}

	boolean continous;
	
	
	
	
	
	public void Sound() {
		this.update = false;
	}
	
	
	
	
	
	public void run() {
		while(true) {
			synchronized(this) {
				update();
			}
		}
	}
	
	
	
	
	
	private synchronized void update() {
		if(update == false) return; 
		update = false;
		if(clip != null)	
			clip.stop();
		
		this.openAudio(currentPath, delay);
	}


	/**
	 * Opens the audio
	 * @param path file path of the music clip
	 * @param ms
	 */
	
	public void openAudio(String path, long ms) {
		try {

			clip = AudioSystem.getClip();
			Thread.sleep(ms);
			File audioFile = new File(path);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(audioFile);
			clip.open(audioInput);
			clip.start();
			clipRunning = true;
			if(continous)
				clip.loop(clip.LOOP_CONTINUOUSLY);
			
		} catch (Exception e) {
			System.out.println("File not found: " + path);
			e.printStackTrace();
		}
	}


	/**
	 * updates the audio to a new one
	 * @param currentPath
	 * @param delay
	 * @param continous
	 */
	public void updateAudio(String currentPath, long delay, boolean continous) {
		synchronized (this)
        {
			this.continous = continous;
			update = true;
			this.currentPath = currentPath;
			this.delay = delay;
		 
        }
	}

	/**
	 * Pause the current music clip
	 */
	public void pauseSound() {
		clipTimePosition = clip.getMicrosecondPosition();
		clip.stop();
		clipRunning=false;

	}

	/**
	 * Resumes the music clip
	 */
	public void resumeSound(){
		clip.setMicrosecondPosition(clipTimePosition);
		clip.start(); 
	}
	
}
