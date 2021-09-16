package ragmad.scenes.mainmenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;

import javax.imageio.ImageIO;

import ragmad.GameEngine;
import ragmad.io.Mouse;
import ragmad.scenes.Scene;
import ragmad.sound_engine.*;

public class MainMenu implements Scene {
	
	// Background image...
	private BufferedImage background; // the output of the scene.
	private int[] pixels;
	
	private int m_width,m_height;
	private BufferedImage backgroundImage;
	
	private Choice[] options;
	private Sound music;
	
	/**
	 * 
	 * @param m_width - The width of the main menu. Currently it is the same as the Engine width
	 * @param m_height - The //////	/	/	/	/	/	/	/	/	/	/
	 * @param url - The path of the background photo.
	 */
	public MainMenu(int m_width, int m_height, String url ) {
		this.m_height = m_height;
		this.m_width = m_width;
		
		
		this.background = new BufferedImage(m_width, m_height,  BufferedImage.TYPE_INT_ARGB);	// Storing the settings,
		this.pixels = ((DataBufferInt)this.background.getRaster().getDataBuffer()).getData();
		
		this.backgroundImage = new BufferedImage(m_width, m_height,  BufferedImage.TYPE_INT_ARGB); // The scene background image buffer
		
		Image img = null;
		try{
			 img = ImageIO.read(new File(url));
			 backgroundImage.getGraphics().drawImage( img, 0, 0 , m_width, m_height, null); // Storing the background image in the buffer.
		}catch(Exception e) {
			System.out.println("File does not exist");
			return;
		}

		initOptions();
		GameEngine.soundEngine.updateAudio(".//ragmad//sound_engine//themes//got.wav", 1000,true);
		
	}
	
	
	/**
	 * Initializing the game main menu's options. 
	 */
	private void initOptions() {
		
		String[] opts = new String[3];
		opts[0] = "Start";
		opts[1] = "Settings";
		opts[2] = "Exit";
		
		
		options = new Choice[3]; 
		
		Graphics g = background.getGraphics();
		g.setFont(new Font("Arial Black", Font.BOLD, 20));
		FontMetrics fm = g.getFontMetrics();	// Getting the Fonts settings 
		
		for(int i = 0 ; i < opts.length ; i++) {
			int str_wid = fm.stringWidth(opts[i]); // The default string width
			int str_hgt = fm.getHeight() + 10; 			// The default string height + margin
			int x =  m_width/2 - str_wid/2;			// x-Starting drawing point coord
			int y = m_height/2 + i * str_hgt ; 		// y-Starting drawing point coord
			
		    Rectangle2D rect = fm.getStringBounds(opts[i], g); 		// Getting to word bounds
		    int x_margin = 10; 												// Just a simple marging to increase the Background width
		    Rectangle pos = new Rectangle( x - x_margin , y - fm.getAscent(),
		    								(int) rect.getWidth() + 2*x_margin, (int) rect.getHeight());	// Button Background
		    
		    options[i] = new Choice(opts[i], pos, x,y);
		}
		
		g.dispose();
	}
	
	
	 
	
	/*
	 * This update is only for testing purposes.. In the future it should contain which button is hovered and which button is pressed.
	 * */
	@Override
	public void update() {
		
		for(int i = 0 ; i < this.options.length; i++) {
			if(this.options[i].frame.contains(new Point(Mouse.x,Mouse.y))) {
				this.options[i].hovered = true;
				if(Mouse.buttonNum == 1) {
					buttonClicked(i);
				}
			}
			else {
				this.options[i].hovered = false;
			}
		}
		
	}

	
	
	/**
	 * A callback function when an option has been clicked
	 * @param choice - the index of the clicked choice. Index = ChoiceNumber - 1;
	 */
	private void buttonClicked(int choice) {
		switch(choice) {
		case 0:
			GameEngine.soundEngine.updateAudio(".//ragmad//sound_engine//themes//button_sound.wav", 0, false);
			GameEngine.ChangeScene();
			
			break;
		case 1: 
			break;
		case 2: 
			
			System.exit(0);
			
			break;
		default:
			break;
		}
	}
	
	
	
	/**
	 * Renders the Game Main Menu. Renders the main background image then renders the buttons on top of it
	 */
	@Override
	public void render() {
		renderBackground();
		renderButtons();
		
		//music.play();
		int[] engineRaster = GameEngine.getPixels();
		for(int i = 0; i < this.m_width * this.m_height; i++)
			engineRaster[i] = pixels[i];
		
	}

	
	
	/**
	 * Render the main background image to the scene buffer. After this function is called, the scene buffer (background in this case)
	 *  will contain a picture
	 */
	private void renderBackground() {
		int[] image = ((DataBufferInt)this.backgroundImage.getRaster().getDataBuffer()).getData();
		for(int y = 0 ; y < this.m_height; y++) { 
			int y_offset = y % this.m_height;
			if (y_offset < 0) y_offset += this.m_height;
			 for(int x = 0; x < this.m_width;x++) { 
				 int x_offset = x % this.m_width;
				 if(x_offset < 0) x_offset = this.m_width + x_offset;
				 this.pixels[x + y*this.m_width] = image[x_offset + y_offset*m_width]; 
			 }
		 }
	}
	
	
	/**
	 * For the simplicity of usage. Graphics() object rendering is used to render the buttons to the canvas.
	 */
	private void renderButtons() {
		Graphics g = background.getGraphics();
		//g.setFont(new Font("Dialog", Font.BOLD, 20));
		g.setFont(new Font("Arial Black", Font.BOLD, 20));
		
		for(int i = 0 ; i < options.length; i++) {
			if(this.options[i].hovered)
				g.setColor(new Color(255, 255, 0,180));					// Settting the background colour
			else
				g.setColor(new Color(255, 255, 255,180));					// Settting the background colour
			
		    g.fillRect( this.options[i].frame.x,	this.options[i].frame.y, 
		    			(int)this.options[i].frame.getWidth(), (int)this.options[i].frame.getHeight());
		    
		    g.setColor(Color.BLACK);									// Text Color
		    g.drawString(options[i].name, this.options[i].x, this.options[i].y);			// Rendering text to the button
		}
		g.dispose();
	}
}



