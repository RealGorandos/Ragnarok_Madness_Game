package test;

import org.junit.jupiter.api.Test;
import ragmad.GameEngine;
import ragmad.entity.characters.Player;
import ragmad.graphics.sprite.Sprite;
import ragmad.scenes.gamescene.GameScene;
import ragmad.scenes.gamescene.Map;
import ragmad.scenes.gamescene.Tile;

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
        ArrayList<Integer> expectedDirection = new ArrayList<>(
                Arrays.asList(
                        1,
                        3,
                        2,
                        0,
                        4,
                        5,
                        7,
                        6
                )
        );




        Sprite sprite = Sprite.PLAYER_TILE_BACK_1;
        String mapPath = "res/test_collision.png";
        HashMap<Integer, Tile> hashmap = new HashMap<Integer, Tile>();
        hashmap.put( 0xff000000,Tile.DESERT1);
        Map map = new Map(mapPath,hashmap);
        Player player = new Player(-100,0);
        for (int i=0;i<directions.size();i++){
            player.move(directions.get(i).x,directions.get(i).y,map,hashmap,sprite);
            assertEquals(player.getDirection(),expectedDirection.get(i));
        }




    }

    @Test
    void checkGettingTileAtWorks(){
        String mapPath = "res/test_collision.png";
        HashMap<Integer, Tile> hashmap = new HashMap<Integer, Tile>();
        hashmap.put( 0xff000000,Tile.PORTAL1);
        Map map = new Map(mapPath,hashmap);
        int[] n = map.getTileAt(100 ,0 , 0, 0);
        int id = map.getMap()[n[0] + n[1] * map.getWidth()];
        assertTrue(map.getTile(id).isSolid());
    }


    @Test
    void checkIfPlayerCollisionWork(){
        Sprite sprite = Sprite.PLAYER_TILE_BACK_1;
        String mapPath = "res/test_collision.png";
        HashMap<Integer, Tile> hashmap = new HashMap<Integer, Tile>();
        hashmap.put( 0xff000000,Tile.PORTAL1);
        Map map = new Map(mapPath,hashmap);
        Player player = new Player(-100,0);
        player.sprites=sprite;
        assertTrue(player.collision(1,0,map,hashmap));
    }
}

//Check change scene works from gameengine
//Sound Engine