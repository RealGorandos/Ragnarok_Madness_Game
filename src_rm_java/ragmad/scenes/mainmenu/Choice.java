package ragmad.scenes.mainmenu;

import java.awt.Rectangle;
import java.io.File;

public class Choice {

	public  String name;
	public final Rectangle frame;
	public final int x,y;
	public boolean hovered;
	
	File buttonSound;

	public Choice(String name, Rectangle pos, int x, int y, String clickSoundPath) {
		this.name = name;
		this.frame = pos;
		this.x = x;
		this.y = y;
		this.hovered = false;
		try {
			this.buttonSound = new File(clickSoundPath);
		}catch(Exception e) {
			System.out.println("File does not exist: " + clickSoundPath);
		}
		
	}
	
}