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
		t=TetriminoBuilder.createO();
		
		//when(listener.handle(new PenaltyNotification(1,1)));
	}
	
	
	@Test
	public void itShouldReturnTrueWhenThereIsACollision() {

		grid=new int[][]{
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	            {0, 0, 0, 1, 0, 0},
	            {0, 0, 0, 0, 0, 0},
	        };
		
		t.setPosition(new vec2(3,4));
		
		/*
		{0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, X, X, 0, 0},
        {0, 0, X, X, 0, 0},
        {0, 0, 0, 0, 0, 0},
        */
		
		assertEquals(CollisionSolver.isInCollision(grid, t),true);
		
		grid=new int[][]{
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
        };
	
		t.setPosition(new vec2(3,4));
		
		/*
		{0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    {0, 0, X, X, 0, 0},
	    {0, 0, X, X, 0, 0},
	    {0, 0, 0, 0, 0, 0},
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
	    {X, X, 0, 0, 0, 0},
	    {X, X, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    */
		
		assertEquals(CollisionSolver.isInCollision(grid, t),true);
				
			
		
	}
	
	@Test
	public void itShouldReturnFalseWhenThereIsNoCollision() {
		
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
        {0, 0, X, X, 0, 0},
        {0, 0, X, X, 0, 0},
        {0, 0, 0, 0, 0, 0},
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
        {0, 0, X, X, 0, 0},
        {0, 0, X, X, 0, 0},
        {0, 0, 0, 0, 0, 0},
        */
		
		assertEquals(CollisionSolver.isInCollision(grid, t),false);
		
		grid=new int[][]{
	            {1, 0, 1, 0, 1, 0},
	            {0, 0, 1, 1, 0, 1},
	            {0, 0, 0, 1, 0, 0},
	            {0, 1, 0, 0, 0, 0},
	            {1, 0, 0, 0, 1, 1},
	            {1, 0, 0, 1, 0, 0},
	        };
		
		t.setPosition(new vec2(1,2));
		
		/*
		{0, 0, 0, 0, 0, 0},
        {X, X, 0, 0, 0, 0},
        {X, X, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0},
        */
		
		assertEquals(CollisionSolver.isInCollision(grid, t),false);
		
	}	
	
	@Test
	public void itShouldReturnTrueWhenTheTetriminoTryToGoOutScreen() {
		
		grid=new int[][]{
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
        };
	
		t.setPosition(new vec2(6,4));
		
		/*
		{0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, X}X,
	    {0, 0, 0, 0, 0, X}X,
	    {0, 0, 0, 0, 0, 0},
	    */
		
		assertEquals(CollisionSolver.isInCollision(grid, t),true);
		
		
		grid=new int[][]{
	        {0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0},
	    };
	
		t.setPosition(new vec2(3,6));
		
		/*
		{0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    {0, 0, X, X, 0, 0},
	           X  X
	    */
		
		assertEquals(CollisionSolver.isInCollision(grid, t),true);
		
		grid=new int[][]{
			{0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0},
	    };
	
		t.setPosition(new vec2(0,2));
		
		/*
		{0, 0, 0, 0, 0, 0},
	   X{X, 0, 0, 0, 0, 0},
	   X{X, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    {0, 0, 0, 0, 0, 0},
	    */
		
		assertEquals(CollisionSolver.isInCollision(grid, t),true);
		
		
	}

	


}
