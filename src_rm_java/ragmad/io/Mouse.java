package ragmad.io;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener,MouseMotionListener {
	public static int x,y,buttonNum;
	
	@Override
	public void mouseClicked(MouseEvent e) { /*Mouse Released is called before Mouse clicked for some reason.*/
		buttonNum = -1;
		x = -1;
		y = -1;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttonNum = e.getButton();
		x = e.getPoint().x;
		y = e.getPoint().y;
	}

	
	@Override
	public void mouseReleased(MouseEvent e) { /*Mouse Released is called before Mouse clicked for somereason. So traet it as a clicking callback*/
		buttonNum = e.getButton();
		x = e.getPoint().x;
		y = e.getPoint().y;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		x = e.getPoint().x;
		y = e.getPoint().y;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		buttonNum = -1;
		x = -1;
		y = -1;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		buttonNum = e.getButton();
		x = e.getPoint().x;
		y = e.getPoint().y;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getPoint().x;
		y = e.getPoint().y;
	}
	//Mouse Clear Method

}