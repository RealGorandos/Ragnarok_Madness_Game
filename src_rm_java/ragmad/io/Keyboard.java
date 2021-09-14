package ragmad.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private static boolean[] keys = new boolean[120];
	private static boolean up, down, left, right,pause;
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W]; 
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]; 
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]; 
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		pause = keys[KeyEvent.VK_ESCAPE];
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W]; 
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]; 
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]; 
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		pause = keys[KeyEvent.VK_ESCAPE];
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W]; 
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]; 
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]; 
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		
	}
	
	
	
	
	public static boolean[] getKeys() {
		return keys;
	}
	
	public static boolean isUp() {return up;}
	public static boolean isDown() {return down;}
	public static boolean isRight() { return right;}
	public static boolean isLeft() { return left;}
	public static boolean esc() { return pause;}
	
}
