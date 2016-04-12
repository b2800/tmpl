package com.esiea.tetris.model.concrete;

import java.util.ArrayList;

import com.esiea.tetris.model.Tetrimino;
import com.esiea.tetris.utils.vec2;

public class CollisionSolver {
	
	private CollisionSolver(){};
	
	//Retourne true s'il y a collision avec un élément de la grille (= bord ou tétrimino arrivé au sol précédemment)
	//Retourne false s'il n'y a pas de collision
	public static boolean isInCollision(int[][] grid, Tetrimino t)
	{
		// On récupère les dimensions de la grille
		int wGrid=grid[0].length;
		int hGrid=grid.length;
				
		// Pour chaque point constituant le Tetrimino
		for (vec2 pt : t.getPointList()) {
			
			System.out.println("pt "+pt.x+"-"+pt.y+", contenu case :"+grid[pt.y][pt.x]);

			// On vérifie si la case n'est pas en dehors de la grille)
			if(pt.x<0 || pt.x>=wGrid || pt.y>=hGrid)
				return true;
                        
		System.out.println("1ère verif passee");
                        if(pt.y < 0)    // On skipe la vérification avec le haut de la grille
                            continue;
        System.out.println("2ème verif passee");	   
			// On vérifie le contenu de la case de la grille
			if(grid[pt.y][pt.x]!=0)
				return true;
			
		System.out.println("3ème verif passee");
		}
		System.out.println("sort de la boucle");
		// Si on sort de la boucle sans avoir retourner true, c'est qu'aucune collision a été détectée
		return false;
	}

}
