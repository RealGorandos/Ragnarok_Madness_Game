package ragmad;

import java.awt.Dimension;

import javax.swing.JFrame;

import ragmad.graphics.GameCanvas;
import ragmad.io.Keyboard;
import ragmad.io.Mouse;

public class GameWindow extends JFrame{
	
	
	/**
	 * A default serial ID for swing and awt inheretance components
	 */
	private static final long serialVersionUID = 1L;
	private GameCanvas gc;
	private int w_width, w_height;

	
	/**
	 * Creates a game window instance. Note that initWindow() should be called to start the window.
	 * @param width width(Integer) of the game window.
	 * @param height height (Integer) of the game window.
	 */
	public GameWindow(int width, int height) {
		this.w_width = width;
		this.w_height = height;
		this.gc = new GameCanvas(this.w_width, this.w_height);
		 
		/*Initializing the frame values*/
		this.setSize(new Dimension(this.w_width, this.w_height));
		this.setMinimumSize(new Dimension(this.w_width, this.w_height));
		this.setPreferredSize(new Dimension(this.w_width, this.w_height)); // Setting window size
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closing the window if the "x" button is pressed
		this.setTitle("Ragnarok Madness Game and Engine Demo!"); // Window Title
		this.setResizable(false); // The Window should not be resizable.
		//this.setLocationRelativeTo(null); // Centering the window	
		this.setVisible(true); // Making the window visible
		
		/*Adding inputs to the window and its children components*/
		this.addKeyListener(new Keyboard());
		this.gc.addMouseListener(new Mouse());
		this.gc.addMouseMotionListener(new Mouse());
		this.add(gc);
	}

	
	
	/**
	 * Updating the window .. Nothing much happening here for the moment
	 */
	public void update() {
		gc.update();
	}
	
	
	
	/**
	 * rendering the window.. Rendering the game canvas for now
	 */
	public void render() {
		gc.render();
	}
	
	
	public int[] getPixels() {return this.gc.getPixels();}
	
	
	 
}