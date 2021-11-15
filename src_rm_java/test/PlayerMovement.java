package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;


import ragmad.GameEngine;
import ragmad.entity.characters.Player;
import ragmad.graphics.sprite.Sprite;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.gamescene.Map;
import ragmad.scenes.gamescene.Tile;
import ragmad.graphics.sprite.SpriteSheet;
import ragmad.entity.characters.Direction;


import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PlayerMovement {

    


    @Test
    void movingThePlayerInAllDirectionsWorks(){

        ArrayList<Point> directions= new ArrayList<>(
                Arrays.asList(
                        new Point(1,0), //Moving to  east
                        new Point(-1,0),// Moving to west
                        new Point(0,1), //Moving to north
                        new Point(0,-1),//Moving to south
                        new Point(1,1), //Moving to north-east
                        new Point(-1,1), //Moving to north-west
                        new Point(1,-1), //Moving to south-east
                        new Point(-1,-1)//Moving to south-west

                )
        );

        ArrayList<Direction> expectedDirection = new ArrayList<>(
                Arrays.asList(
                        Direction.RIGHT,
                        Direction.LEFT,
                        Direction.DOWN,
                        Direction.UP,
                        Direction.DOWN_RIGHT,
                        Direction.DOWN_LEFT,
                        Direction.UP_RIGHT,
                        Direction.UP_LEFT
                )
        );



        Sprite DESERT_TILE_1 = new Sprite(new SpriteSheet("res/desert_res_orig.png"), 0, 0, 64, 32);
        Sprite sprite = new Sprite(new SpriteSheet("res/jaden_yuki_2.png"), 0, 0, 51, 84);

        String mapPath = "res/test_collision.png";
        HashMap<Integer, Tile> hashmap = new HashMap<Integer, Tile>();
        hashmap.put( 0xff000000, new Tile(0, DESERT_TILE_1, false));
        Map map = new Map(mapPath, hashmap);

        Player player = new Player(-100,0);
        for (int i=0;i<directions.size();i++){
            player.move(directions.get(i).x, directions.get(i).y, map, hashmap,sprite);
            assertEquals(player.getDirection(), expectedDirection.get(i));
        }
    }




    @Test
    void checkGettingTileAtWorks(){
        Sprite PORTAL_1 = new Sprite(new SpriteSheet("res/porotals.png"), 0, 0, 64, 32);
        
        String mapPath = "res/test_collision.png";
        HashMap<Integer, Tile> hashmap = new HashMap<Integer, Tile>();
        hashmap.put( 0xff000000,  new Tile(0, PORTAL_1, true));
        Map map = new Map(mapPath,hashmap);
        
        int[] n = map.getTileAt(100 ,0 , 0, 0);
        int id = map.getMap()[n[0] + n[1] * map.getWidth()];

        assertTrue(map.getTile(id).isSolid());
    }





    @Test
    void checkIfPlayerCollisionWork(){
        Sprite sprite = new Sprite(new SpriteSheet("res/jaden_yuki_2.png"), 0, 0, 51, 84);
        Sprite PORTAL_1 = new Sprite(new SpriteSheet("res/porotals.png"), 0, 0, 64, 32);

        String mapPath = "res/test_collision.png";
        HashMap<Integer, Tile> hashmap = new HashMap<Integer, Tile>();
        hashmap.put( 0xff000000, new Tile(0, PORTAL_1, true));

        Map map = new Map(mapPath,hashmap);
        Player player = new Player(-100,0);
        player.sprites = sprite;

        assertTrue(player.collision(1,0,map, hashmap));
    }
}

//Check change scene works from gameengine
//Sound Engine
