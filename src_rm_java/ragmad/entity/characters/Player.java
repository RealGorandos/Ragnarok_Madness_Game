package ragmad.entity.characters;

import ragmad.scenes.gamescene.Map;

import ragmad.GameEngine;
import ragmad.graphics.sprite.Sprite;
import ragmad.io.Keyboard;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.gamescene.Tile;

public class Player extends Characters {
	private Sprite sprite;
	private Map map;
	private int anim = 0;
	private boolean isWalking = false;
	public Player() {
		this.x = -GameEngine.GetWidth()/2;
		this.y = -GameEngine.GetHeight()/2;
		//Tile.PLAYER = new Tile(0, Sprite.PLAYER_TILE_BACK_1, false);
		sprite = Sprite.PLAYER_TILE_BACK_1;
	}
	
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = Sprite.PLAYER_TILE_BACK_1;
		//Tile.PLAYER = new Tile(0, Sprite.PLAYER_TILE_BACK_1, false);

	}
	
	
	
	public void update(int frameMovement, Map map2) {
	//	int dirX = 0, dirY = 0;
		if(anim < 7500) anim++;
		else anim = 0;
		
		int xOffset =0 ,  yOffset = 0;
		if(Keyboard.isUp()) yOffset+=frameMovement;
		if(Keyboard.isDown()) yOffset-=frameMovement;
		if(Keyboard.isRight()) xOffset-=frameMovement;
		if(Keyboard.isLeft()) xOffset+=frameMovement;
		
		
		if(xOffset != 0 || yOffset != 0) { 
			move(xOffset, yOffset, map2);
			isWalking = true;
			}else {
				isWalking = false;
			}
		
	//	int posX=0, posY= 0;
		if(direction == 0 )
		{
			sprite = Sprite.PLAYER_TILE_BACK_1;
			if(isWalking) {
				if(anim % 20 > 10 && anim % 20 < 20) {
					sprite = Sprite.PLAYER_TILE_BACK_2;
				}
				if(anim % 30 >= 20 &&  anim % 30 < 30 ){
					sprite = Sprite.PLAYER_TILE_BACK_3;
				}
				if(anim % 40 >= 30 && anim % 40 < 40){
					sprite = Sprite.PLAYER_TILE_BACK_4;
				}
				if(anim % 50 >= 40 && anim % 50 < 50){
					sprite = Sprite.PLAYER_TILE_BACK_5;
				}
				if(anim % 60 >= 50 && anim % 60 < 60){
					sprite = Sprite.PLAYER_TILE_BACK_6;
				}
				if(anim % 70 >= 60 && anim % 70 < 70){
					sprite = Sprite.PLAYER_TILE_BACK_7;
				}
			}
		}
		
		if(direction == 1 ) { 
			//Tile.PLAYER = new Tile(0, Sprite.PLAYER_TILE_LEFT_1, false);
			sprite = Sprite.PLAYER_TILE_LEFT_1;
			if(isWalking) {
				if(anim % 20 > 10 && anim % 20 < 20) {
					sprite = Sprite.PLAYER_TILE_LEFT_2;
			}
				if(anim % 30 >= 20 &&  anim % 30 < 30 ){
					sprite = Sprite.PLAYER_TILE_LEFT_3;
			}
				if(anim % 40 >= 30 && anim % 40 < 40){
					sprite = Sprite.PLAYER_TILE_LEFT_4;
			}
				if(anim % 50 >= 40 && anim % 50 < 50){
					sprite = Sprite.PLAYER_TILE_LEFT_5;
			}
				if(anim % 60 >= 50 && anim % 60 < 60){
					sprite = Sprite.PLAYER_TILE_LEFT_6;
			}
				if(anim % 70 >= 60 && anim % 70 <= 70){
					sprite = Sprite.PLAYER_TILE_LEFT_7;
			}
			}
		}
		
		
		
		
		
		if(direction == 2 ) {
			//Tile.PLAYER = new Tile(0, Sprite.PLAYER_TILE_FORWARD_1, false);
			sprite = Sprite.PLAYER_TILE_FORWARD_1;
			if(isWalking) {
				if(anim % 20 > 10 && anim % 20 < 20) {
					sprite = Sprite.PLAYER_TILE_FORWARD_2;
			}
				if(anim % 30 >= 20 &&  anim % 30 < 30 ){
					sprite = Sprite.PLAYER_TILE_FORWARD_3;
			}
				if(anim % 40 >= 30 && anim % 40 < 40){
					sprite = Sprite.PLAYER_TILE_FORWARD_4;
			}
				if(anim % 50 >= 40 && anim % 50 < 50){
					sprite = Sprite.PLAYER_TILE_FORWARD_5;
			}
				if(anim % 60 >= 50 && anim % 60 < 60){
					sprite = Sprite.PLAYER_TILE_FORWARD_6;
			}
				if(anim % 70 >= 60 && anim % 70 <= 70){
					sprite = Sprite.PLAYER_TILE_FORWARD_7;
			}
			}
		}
		
		
		if(direction == 3 ) {
			//Tile.PLAYER = new Tile(0, Sprite.PLAYER_TILE_RIGHT_1, false);
			sprite = Sprite.PLAYER_TILE_RIGHT_1;
			if(isWalking) {
				if(anim % 20 > 10 && anim % 20 < 20) {
					sprite = Sprite.PLAYER_TILE_RIGHT_2;
			}
				if(anim % 30 >= 20 &&  anim % 30 < 30 ){
					sprite = Sprite.PLAYER_TILE_RIGHT_3;
			}
				if(anim % 40 >= 30 && anim % 40 < 40){
					sprite = Sprite.PLAYER_TILE_RIGHT_4;
			}
				if(anim % 50 >= 40 && anim % 50 < 50){
					sprite = Sprite.PLAYER_TILE_RIGHT_5;
			}
				if(anim % 60 >= 50 && anim % 60 < 60){
					sprite = Sprite.PLAYER_TILE_RIGHT_6;
			}
				if(anim % 70 >= 60 && anim % 70 <= 70){
					sprite = Sprite.PLAYER_TILE_RIGHT_7;
			}
			}
		
		
		}
		
		
		if(direction == 4 ) {
			//Tile.PLAYER = new Tile(0, Sprite.PLAYER_TILE_RIGHT_1, false);
			sprite = Sprite.PLAYER_TILE_UPPER_LEFT_1;
			if(isWalking) {
				if(anim % 20 > 10 && anim % 20 < 20) {
					sprite = Sprite.PLAYER_TILE_UPPER_LEFT_2;
			}
				if(anim % 30 >= 20 &&  anim % 30 < 30 ){
					sprite = Sprite.PLAYER_TILE_UPPER_LEFT_3;
			}
				if(anim % 40 >= 30 && anim % 40 < 40){
					sprite = Sprite.PLAYER_TILE_UPPER_LEFT_4;
			}
				if(anim % 50 >= 40 && anim % 50 < 50){
					sprite = Sprite.PLAYER_TILE_UPPER_LEFT_5;
			}
				if(anim % 60 >= 50 && anim % 60 < 60){
					sprite = Sprite.PLAYER_TILE_UPPER_LEFT_6;
			}
				if(anim % 70 >= 60 && anim % 70 <= 70){
					sprite = Sprite.PLAYER_TILE_UPPER_LEFT_7;
			}
			}
		
		
		}
		
		
		if(direction == 5) {
			//Tile.PLAYER = new Tile(0, Sprite.PLAYER_TILE_RIGHT_1, false);
			sprite = Sprite.PLAYER_TILE_UPPER_RIGHT_1;
			if(isWalking) {
				if(anim % 20 > 10 && anim % 20 < 20) {
					sprite = Sprite.PLAYER_TILE_UPPER_RIGHT_2;
			}
				if(anim % 30 >= 20 &&  anim % 30 < 30 ){
					sprite = Sprite.PLAYER_TILE_UPPER_RIGHT_3;
			}
				if(anim % 40 >= 30 && anim % 40 < 40){
					sprite = Sprite.PLAYER_TILE_UPPER_RIGHT_4;
			}
				if(anim % 50 >= 40 && anim % 50 < 50){
					sprite = Sprite.PLAYER_TILE_UPPER_RIGHT_5;
			}
				if(anim % 60 >= 50 && anim % 60 < 60){
					sprite = Sprite.PLAYER_TILE_UPPER_RIGHT_6;
			}
				if(anim % 70 >= 60 && anim % 70 <= 70){
					sprite = Sprite.PLAYER_TILE_UPPER_RIGHT_7;
			}
			}
		
		
		}
		
		
		if(direction == 6) {
			//Tile.PLAYER = new Tile(0, Sprite.PLAYER_TILE_RIGHT_1, false);
			sprite = Sprite.PLAYER_TILE_DOWN_RIGHT_1;
			if(isWalking) {
				if(anim % 20 > 10 && anim % 20 < 20) {
					sprite = Sprite.PLAYER_TILE_DOWN_RIGHT_2;
			}
				if(anim % 30 >= 20 &&  anim % 30 < 30 ){
					sprite = Sprite.PLAYER_TILE_DOWN_RIGHT_3;
			}
				if(anim % 40 >= 30 && anim % 40 < 40){
					sprite = Sprite.PLAYER_TILE_DOWN_RIGHT_4;
			}
				if(anim % 50 >= 40 && anim % 50 < 50){
					sprite = Sprite.PLAYER_TILE_DOWN_RIGHT_5;
			}
				if(anim % 60 >= 50 && anim % 60 < 60){
					sprite = Sprite.PLAYER_TILE_DOWN_RIGHT_6;
			}
				if(anim % 70 >= 60 && anim % 70 <= 70){
					sprite = Sprite.PLAYER_TILE_DOWN_RIGHT_7;
			}
			}
		
		
		}
		
		
		
		if(direction == 7) {
			//Tile.PLAYER = new Tile(0, Sprite.PLAYER_TILE_RIGHT_1, false);
			sprite = Sprite.PLAYER_TILE_DOWN_LEFT_1;
			if(isWalking) {
				if(anim % 20 > 10 && anim % 20 < 20) {
					sprite = Sprite.PLAYER_TILE_DOWN_LEFT_2;
			}
				if(anim % 30 >= 20 &&  anim % 30 < 30 ){
					sprite = Sprite.PLAYER_TILE_DOWN_LEFT_3;
			}
				if(anim % 40 >= 30 && anim % 40 < 40){
					sprite = Sprite.PLAYER_TILE_DOWN_LEFT_4;
			}
				if(anim % 50 >= 40 && anim % 50 < 50){
					sprite = Sprite.PLAYER_TILE_DOWN_LEFT_5;
			}
				if(anim % 60 >= 50 && anim % 60 < 60){
					sprite = Sprite.PLAYER_TILE_DOWN_LEFT_6;
			}
				if(anim % 70 >= 60 && anim % 70 <= 70){
					sprite = Sprite.PLAYER_TILE_DOWN_LEFT_7;
			}
			}
		
		
		}
		
		
		
		
		
		
		}
	
	
	public void render(int SCALING) {

		
		
		
	
		int[] outputPixels = GameEngine.GetPixels();
		int[] tilePixels = sprite.getPixels();
		
		int s_height =( sprite.getHeight()*SCALING);
		int s_width = (sprite.getWidth()*SCALING);

		
		
		int xPixel = x;
		int yPixel = y;
		
		
		//System.out.println(xPixel + ", " + yPixel);
		for(int y = 0 ; y < s_height; y++) {
			int yy = y - yPixel;   //Mapping coordinates space to the GameEngine pixel Space (Raster space) //yOffset for vertical movement
			if( yy >= GameEngine.GetHeight()) break;
			if(yy < -s_height) break;
			if(yy < 0) continue;
			for(int x = 0 ; x < s_width; x++) {
				int xx = x - xPixel;
				int col = tilePixels[x/SCALING + (y/SCALING) * sprite.getWidth()]; // getting the pixel colour of the tile
				
				if ( xx >= GameEngine.GetWidth() ) // break if the renderer pointer has exited screen right side
					break;
				if( xx < 0 || (col & 0xff000000) == 0 )  //don't do anything if the xx is out of bounds or pixel is transparent 
					continue;
				
				if(col != 0xffd6e7ea)outputPixels[xx + yy * GameEngine.GetWidth()] = col;
			}
		}


	}
}
