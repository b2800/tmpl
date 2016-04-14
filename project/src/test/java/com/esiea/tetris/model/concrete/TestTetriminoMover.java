/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esiea.tetris.model.concrete;

import com.esiea.tetris.model.Tetrimino;
import com.esiea.tetris.model.builder.TetriminoBuilder;
import com.esiea.tetris.utils.Vec2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestTetriminoMover {
    private Tetrimino t;
    
    @Before
    public void setUp() {
        t = TetriminoBuilder.createL();
        t.setPosition(new Vec2(5, 5));
    }

    @Test
    public void testMoveRight() {
        TetriminoMover.moveRight(t);
        int pos_x = t.getPosition().x;
        assertEquals(pos_x, 6);
    }

    @Test
    public void testMoveLeft() {
        TetriminoMover.moveLeft(t);
        int pos_x = t.getPosition().x;
        assertEquals(pos_x, 4);
    }

    @Test
    public void testMoveDown() {
        TetriminoMover.moveDown(t);
        int pos_y = t.getPosition().y;
        assertEquals(pos_y, 6);
    }

    @Test
    public void testMoveUp() {
        TetriminoMover.moveUp(t);
        int pos_y = t.getPosition().y;
        assertEquals(pos_y, 4);
    }

    @Test
    public void testMoveBottom() {
        TetriminoMover.moveBottom(t, 10);
        int pos_y = t.getPosition().y;
        assertEquals(pos_y, 15);
    }

    @Test
    public void testTurnRight() {
        TetriminoMover.turnRight(t);
        int[][] layout = t.getLayoutForActualOrientation();
        int[][] expected = {
            {1,1,0},
            {0,1,0},
            {0,1,0}
        };
        assertTrue(layout.length == expected.length);
        assertTrue(layout[0].length == expected[0].length);
        failIfDifferent(layout, expected);
    }

    @Test
    public void testTurnLeft() {
        TetriminoMover.turnLeft(t);
        int[][] layout = t.getLayoutForActualOrientation();
        int[][] expected = {
            {0,1,0},
            {0,1,0},
            {0,1,1}
        };
        assertTrue(layout.length == expected.length);
        assertTrue(layout[0].length == expected[0].length);
        failIfDifferent(layout, expected);
    }
    
    private void failIfDifferent(int[][] layout, int[][] expected){
        for(int y = 0; y < layout.length; y++){
            for(int x = 0; x < layout[0].length; x++){
                if(layout[y][x] != expected[y][x]){
                    fail();
                }
            }
        }
    }
}
