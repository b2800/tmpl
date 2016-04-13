package com.esiea.tetris.model.concrete;

import java.util.ArrayList;

import com.esiea.tetris.model.Tetrimino;
import com.esiea.tetris.utils.Vec2;

public class CollisionSolver {
	
	private CollisionSolver(){};
	
	//Retourne true s'il y a collision avec un élément de la grille (= bord ou tétrimino arrivé au sol précédemment)
	//Retourne false s'il n'y a pas de collision
	public static boolean isInCollision(int[][] grid, Tetrimino t)
	{
		// On récupère les dimensions de la grille
		int wGrid=grid[0].length;
		int hGrid=grid.length;
				
		ArrayList <Vec2> list = t.getPointList();
		
		// Pour chaque point constituant le Tetrimino
		for (Vec2 pt : list) {

			// On vérifie si la case n'est pas en dehors de la grille)
			if(pt.x<0 || pt.x>=wGrid || pt.y>=hGrid)
				return true;
                        
                        if(pt.y < 0)    // On skipe la vérification avec le haut de la grille
                            continue;   
			// On vérifie le contenu de la case de la grille
			if(grid[pt.y][pt.x]!=0)
				return true;
			
		}
		// Si on sort de la boucle sans avoir retourner true, c'est qu'aucune collision a été détectée
		return false;
	}

}
