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

public class Sound extends Thread {


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
	
	
	
	
	
	
	public void updateAudio(String currentPath, long delay, boolean continous) {
		synchronized (this)
        {
			this.continous = continous;
			update = true;
			this.currentPath = currentPath;
			this.delay = delay;
		 
        }
	}
	
	public void pauseSound() {
		clipTimePosition = clip.getMicrosecondPosition();
		clip.stop();
		clipRunning=false;

	}
	
	public void resumeSound(){
		clip.setMicrosecondPosition(clipTimePosition);
		clip.start(); 
	}
	
}
