package ragmad.scenes.mainmenu;

import java.awt.Rectangle;

public class Choice {

	public  String name;
	public final Rectangle frame;
	public final int x,y;
	public boolean hovered;

	public Choice(String name, Rectangle pos, int x, int y) {
		this.name = name;
		this.frame = pos;
		this.x = x;
		this.y = y;
		this.hovered = false;
	}
}