package ragmad.graphics;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import ragmad.GameEngine;


public class GameCanvas extends Canvas {
	
	private static final long serialVersionUID = 1L; // added by default
	private BufferedImage background; 			// where we will be drawing our pixels
	private int[] pixels;					 	// reference to the background pixels (Raster)
	private int c_width, c_height; 				// Canvas width and height (background image width and height)
	
	
	/**
	 * Creates an instance of the game canvas.
	 * @param width - Width of the canvas and its image
	 * @param height - Height of the canvas and its image
	 */
	public GameCanvas(int width, int height) {
		this.c_height = height;
		this.c_width = width;
		this.setPreferredSize(new Dimension(width,height));
		this.setSize(new Dimension(width,height));
		this.setFocusable(false);
		
		this.background = new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB);  	// Defining an instance of the Image
		this.pixels = ((DataBufferInt)this.background.getRaster().getDataBuffer()).getData();
	}
	
	
	
	/**
	 * Rendering the canvas with the new image
	 * */
	public void render() {

		
		
		
		BufferStrategy bfr = this.getBufferStrategy(); 		// Return the currently used buffer strategy
		try {
			if(bfr == null) { 
				this.createBufferStrategy(3); // Re-creates a bufferstrategy if not found
				System.out.println("BFRS created");
				return; 
			} 
		}catch(java.lang.IllegalStateException ec) {
			System.out.println("You can't create a bufferstrategy if the canvas is not yet initialized nor if it is not attached to a Window");
			return;
		}
		
		/*Copying the GameEngine pixels to the image pixels (Acting as a buffer)*/
		int[] ge_pixels = GameEngine.pixels;
		
		
		for(int i = 0; i  < ge_pixels.length ; i++) {
			this.pixels[i] = ge_pixels[i]; // assigning a new value to it
		}
			
		
		/*	Drawing the Image to the canvas	*/
		Graphics g = bfr.getDrawGraphics();
		g.drawImage(this.background, 0,0, this.c_width, this.c_height, null);
		g.dispose();
		bfr.show();
	}

	
	public void update() {
	}
}
