package com.esiea.tetris.model.concrete;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.esiea.tetris.model.Tetrimino;
import com.esiea.tetris.model.builder.TetriminoBuilder;
import com.esiea.tetris.util.Listener;
import com.esiea.tetris.utils.vec2;

public class TestCollisionSolver {
	
	Tetrimino t;
	int grid[][];

	
	@Before
	public void init()
	{
		t=TetriminoBuilder.createL();
		
		//when(listener.handle(new PenaltyNotification(1,1)));
	}
	
	@Test
	public void itShouldReturnTrueWhenThereIsACollision() {
		System.out.println("test 1");
		grid=new int[][]{
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 1, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	        };
		
		t.setPosition(new vec2(3,4));
		
		/*
		{0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, X, X, X, 0},
        {0, 0, X, 0, 0, 0},
        */
		assertEquals(CollisionSolver.isInCollision(grid, t),true);
		
		grid=new int[][]{
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 1, 1, 0},
	            {0, 0, 0, 0, 0, 0},
	        };
		
		t.setPosition(new vec2(3,4));
		
		/*
		{0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, X, X, X, 0},
        {0, 0, X, 0, 0, 0},
        */
		
		assertEquals(CollisionSolver.isInCollision(grid, t),true);
		
		grid=new int[][]{
	            {1, 0, 0, 0, 0, 0},
	            {1, 0, 0, 0, 0, 0},
	            {1, 0, 0, 0, 0, 0},
	            {1, 0, 0, 0, 0, 0},
	            {1, 0, 0, 0, 0, 0},
	            {1, 0, 0, 0, 0, 0},
	        };
		
		t.setPosition(new vec2(1,2));
		
		/*
		{0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {X, X, X, 0, 0, 0},
        {X, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        */
		
		assertEquals(CollisionSolver.isInCollision(grid, t),true);
		
		
		
	}
	
	@Test
	public void itShouldReturnFalseWhenThereIsNoCollision() {
		System.out.println("test 2");
		grid=new int[][]{
	            {0, 0, 1, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	        };
		
		t.setPosition(new vec2(3,4));
		
		/*
		{0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, X, X, X, 0},
        {0, 0, X, 0, 0, 0},
        */
		
		assertEquals(CollisionSolver.isInCollision(grid, t),false);
		
		grid=new int[][]{
	            {0, 0, 1, 0, 0, 0},
	            {0, 0, 0, 0, 0, 1},
	            {0, 0, 0, 1, 0, 0},
	            {1, 0, 0, 0, 1, 0},
	            {0, 0, 0, 0, 0, 0},
	            {1, 1, 0, 0, 0, 0},
	        };
		
		t.setPosition(new vec2(3,4));
		
		/*
		{0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, X, X, X, 0},
        {0, 0, X, 0, 0, 0},
        */
		
		assertEquals(CollisionSolver.isInCollision(grid, t),false);
		
		grid=new int[][]{
	            {1, 0, 1, 0, 1, 0},
	            {1, 1, 1, 1, 0, 1},
	            {0, 1, 0, 1, 0, 0},
	            {0, 1, 1, 1, 0, 0},
	            {1, 0, 1, 0, 1, 1},
	            {1, 0, 0, 1, 0, 0},
	        };
		
		t.setPosition(new vec2(1,2));
		
		/*
		{0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {X, X, X, 0, 0, 0},
        {X, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        */
		
		assertEquals(CollisionSolver.isInCollision(grid, t),false);
		
	}
	
	@Test
	public void itShouldReturnTrueWhenTheTetriminoTryToGoOutScreen() {
		
	}


}
