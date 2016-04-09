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
		int wGrid=grid.length;
		int hGrid=grid[0].length;
				
		// Pour chaque point constituant le Tetrimino
		for (vec2 pt : t.getPointList()) {

			// On vérifie si la case n'est pas en dehors de la grille)
			if(pt.x<0 || pt.x>wGrid || pt.y<0 || pt.y>hGrid)
				return true;
						   
			// On vérifie le contenu de la case de la grille
			if(grid[pt.x][pt.y]!=0)
				return true;
		}
		
		// Si on sort de la boucle sans avoir retourner true, c'est qu'aucune collision a été détectée
		return false;
	}
	
	//Retourne true s'il y a collision avec un élément de la grille (= bord ou tétrimino arrivé au sol précédemment)
	//Retourne false s'il n'y a pas de collision
	public static boolean isInCollision_old_version(int[][] grid, Tetrimino t)
	{
		//int nbCollisions=0;
		
		// On récupère le tableau du layout décrivant l'état actuel du Tetrimino
		// ex :
		//110
		//010
		//011
		int [][] lay=t.getLayoutForActualOrientation();
		
		// On récupère les dimensions du tableau dans des variables pour simplifier l'écriture des calculs qui vont suivre
		int wLay=lay.length;
		int hLay=lay[0].length;
		
		// On récupère les dimensions de la grille dans des variables pour simplifier l'écriture des calculs qui vont suivre
		int wGrid=grid.length;
		int hGrid=grid[0].length;
		
		int xChecked,yChecked; // Variables intermédiaires pour simplifier l'écriture des calculs qui vont suivre
		
		// On parcourt chaque case du layout du tetrimino
		for(int i = 0; i <wLay ; i++){
			   for(int j = 0; j <hLay ; j++){

				   // Si la case correspond au "corps" du tétrimino, on regarde si la case sur la grille est occupée
				   if(lay[i][j]==1)
				   {
					   // On récupère les coordonnées de la case de la grille à vérifier
					   xChecked=i+t.getPosition().x-Math.round(wLay/2);
					   yChecked=j+t.getPosition().y-Math.round(hLay/2);
					   
					   // On vérifie si la case n'est pas en dehors de la grille)
					   if(xChecked<0 || xChecked>wGrid || yChecked<0 || yChecked>hGrid)
						   return true;
					   
					   // On vérifie le contenu de la case de la grille
					   if(grid[xChecked][yChecked]!=0)
						   return true;
				   }
				   
			   }
			}
		
		// Si on sort de la boucle sans avoir retourner true, c'est qu'aucune collision a été détectée
		return false;
	}

}
