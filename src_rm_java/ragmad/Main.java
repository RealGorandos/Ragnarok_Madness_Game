package ragmad;

public class Main {
	// Final Constant Game Values
	private final static int WIDTH = 1024;
	private final static int HEIGHT = (WIDTH*9)/16;
	
	
	public static void main(String[] args) {
		GameEngine game = new GameEngine( WIDTH, HEIGHT );
		game.start();
	}

}
