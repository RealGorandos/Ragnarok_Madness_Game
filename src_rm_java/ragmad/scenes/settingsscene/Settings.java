package ragmad.scenes.settingsscene;

import ragmad.GameEngine;
import ragmad.io.Mouse;
import ragmad.scenes.Scene;
import ragmad.scenes.mainmenu.Choice;
import ragmad.sound_engine.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.nio.file.Paths;

public class Settings implements Scene {

    // Background image...
    private BufferedImage background; // the output of the scene.
    private int[] pixels;

    private int m_width,m_height;
    private BufferedImage backgroundImage;

    private Choice[] options;
    private Choice[] options2;
    private Choice[] options3;
    private Sound music;

    /**
     *
     * @param m_width - The width of the settings menu. Currently it is the same as the Engine width
     * @param m_height - The //////	/	/	/	/	/	/	/	/	/	/
     * @param url - The path of the background photo.
     */
    public Settings(int m_width, int m_height, String url ) {
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
            System.out.println("File does not exist" + url);

            return;
        }

        initOptions();
        //GameEngine.GetSoundEngine().updateAudio( Paths.get("").toAbsolutePath().getParent().toString() +"//src_rm_java//ragmad//sound_engine//themes//got.wav", 1000,true);

    }


    /**
     * Initializing the game main menu's options.
     */
    private void initOptions() {
        //For the option buttons
        String[] opts = new String[5];
        opts[0] = "Music On/Off";
        opts[1] = "FPS";
        opts[2] = "Window Size";
        opts[3] = "BACK";
        opts[4] = "SAVE";
        options = new Choice[5];

    //For the option values
        String[] opts2 = new String[3];
        opts2[0] = "ON";
        opts2[1] = "60";
        opts2[2] = "800";
        options2 = new Choice[3];


        Graphics g = background.getGraphics();
        g.setFont(new Font("Arial Black", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();	// Getting the Fonts settings

        //Buttons that are clicked
        for(int i = 0 ; i < 3 ; i++) {

            int str_wid = fm.stringWidth(opts[i]); // The default string width
            int str_hgt = fm.getHeight() + 10; 			// The default string height + margin
            int x =  m_width/8-str_wid/2;		// x-Starting drawing point coord
            int y = m_height/2 + i * str_hgt ; 		// y-Starting drawing point coord

            Rectangle2D rect = fm.getStringBounds(opts[i], g); 		// Getting to word bounds
            int x_margin = 10; 												// Just a simple marging to increase the Background width
            Rectangle pos = new Rectangle( x_margin , y - fm.getAscent(),
                    this.m_width/4, (int) rect.getHeight());	// Button Background

            options[i] = new Choice(opts[i], pos, x,y);
        }



        //Buttons Back and Save
        Rectangle2D rect = fm.getStringBounds(opts[4], g);// Getting to word bound
        int x_margin = 20; 			// Just a simple marging to increase the Background width
        int width= (int) rect.getWidth() + 2*x_margin;

        int str_wid = fm.stringWidth(opts[4]); // The default string width
        int str_hgt = fm.getHeight() + 10; 			// The default string height + margin
        int x1 =  m_width/2-(x_margin+ width)+width/2-str_wid/2;		// x-Starting drawing point coord
        int x2 =  m_width/2+(x_margin )+width/2-str_wid/2;		// x-Starting drawing point coord
        int y = m_height - str_hgt ; 		// y-Starting drawing point coord

        Rectangle pos1 = new Rectangle( m_width/2-(x_margin+ width ), y - fm.getAscent(),
                width, (int) rect.getHeight());	// Button Background
        Rectangle pos2 = new Rectangle( m_width/2+(x_margin ), y - fm.getAscent(),
                width, (int) rect.getHeight());	// Button Background
        //The y coordinate will be the same
        options[3] = new Choice(opts[3], pos1, x1,y);
        options[4] = new Choice(opts[4], pos2, x2,y);


        //Blocks that display the values
        for(int i = 0 ; i < opts2.length ; i++) {

            str_wid = fm.stringWidth(opts2[i]); // The default string width
            str_hgt = fm.getHeight() + 10; 			// The default string height + margin
            int x =  m_width-(m_width/8)-10-str_wid/2;		// x-Starting drawing point coord
            y = m_height/2 + i * str_hgt ; 		// y-Starting drawing point coord

            rect = fm.getStringBounds(opts2[i], g); 		// Getting to word bounds
            x_margin = 10; 												// Just a simple marging to increase the Background width
            Rectangle pos = new Rectangle( m_width-(x_margin+ m_width/4 ), y - fm.getAscent(),
                    m_width/4, (int) rect.getHeight());	// Button Background

            options2[i] = new Choice(opts2[i], pos, x,y);
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
                if (options2[0].name.equals("OFF")){
                    options2[0].name="ON";
                }else{
                    options2[0].name="OFF";
                }


                break;
            case 1:
                //FPS
                break;
            case 2:

                //SIZE
                break;
            case 3:

                //BACK
                GameEngine.ChangeScene("Menu");
                break;
            case 4:

                //SAVE: The game settings

                break;
            default:
                break;
        }
    }



    /**
     * Renders the Settings Menu. Renders the main background image then renders the buttons on top of it
     */
    @Override
    public void render() {
        renderBackground();
        renderButtons();

        //music.play();
        int[] engineRaster = GameEngine.GetPixels();
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
        Graphics g = background.getGraphics();
        g.setFont(new Font("Arial Black", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.BLACK);									// Text Color
        g.drawString("SETTINGS", m_width/2-fm.stringWidth("SETTINGS")/2, fm.getHeight());			// Rendering text to the button
        g.dispose();
    }


    /**
     * For the simplicity of usage. Graphics() object rendering is used to render the buttons to the canvas.
     */
    private void renderButtons() {

        Graphics g = background.getGraphics();
        //g.setFont(new Font("Dialog", Font.BOLD, 20));
        g.setFont(new Font("Arial Black", Font.BOLD, 20));
        FontMetrics metrics=g.getFontMetrics();

        //Rendering the buttons
        for(int i = 0 ; i < options.length; i++) {
            if(this.options[i].hovered)
                g.setColor(new Color(255, 255, 0,180));					// Settting the background colour
            else
                g.setColor(new Color(255, 255, 255,180));					// Settting the background colour
            g.fillRect( this.options[i].frame.x,	this.options[i].frame.y,
                    (int)this.options[i].frame.getWidth(), (int)this.options[i].frame.getHeight());
            // Set the font
            g.setColor(Color.BLACK);									// Text Color
            g.drawString(options[i].name, this.options[i].x, this.options[i].y);			// Rendering text to the button
        }


        // Rendering the options values
        for(int i = 0 ; i < options2.length; i++) {
            // Setting the background colour
            g.setColor(new Color(255, 255, 255,180));
            g.fillRect(this.options2[i].frame.x,	this.options2[i].frame.y,
                    (int)this.options[i].frame.getWidth(), (int)this.options2[i].frame.getHeight());
            // Set the font
            g.setColor(Color.BLACK);									// Text Color
            g.drawString(options2[i].name, this.options2[i].x, this.options2[i].y);			// Rendering text to the button
        }


        g.dispose();
    }
}
