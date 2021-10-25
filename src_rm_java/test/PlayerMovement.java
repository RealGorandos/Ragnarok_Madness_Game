package test;

import org.junit.jupiter.api.Test;
import ragmad.entity.characters.Player;
import ragmad.scenes.gamescene.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

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
        Player player = new Player();
        Map map = new Map();
        //Moving to the east
        for (int i=0;i<directions.size();i++){
            player.move(directions.get(i).x,directions.get(i).y,map);
            assertEquals(player.getDirection(),expectedDirection.get(i));
        }

    }

}

//Check change scene works from gameengine
//Sound Engine