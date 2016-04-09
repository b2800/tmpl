package com.esiea.tetris.model.concrete;

import com.esiea.tetris.model.Tetrimino;
import com.esiea.tetris.utils.vec2;


// Classe qui se contente d'effectuer les déplacements. Tous les problèmes de collisions seront gérés en amont.
// Aucune de ces méthodes ne pourra être appelé si elle engendre une collision.
public class TetriminoMover {
	
	private TetriminoMover(){}
	
	public static void moveRight(Tetrimino T)
	{
		T.getPosition().add(1,0);
	}
	
	public static void moveLeft(Tetrimino T)
	{
		T.getPosition().add(-1,0);
	}
	
	public static void moveDown(Tetrimino T)
	{
		T.getPosition().add(0,-1);
	}
	
	// Dans le cas où l'action est de descendre le Tetrimino tout en bas on envoie la distance (dist) maximale avant collision
	public static void moveBottom(Tetrimino T, int dist)
	{
		T.getPosition().add(0,-1*dist);
	}

	public static void turnRight(Tetrimino T)
	{
		T.incrementRotation();
	}
	
	public static void turnLeft(Tetrimino T)
	{
		T.decrementRotation();
	}
}
