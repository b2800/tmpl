package com.esiea.tetris.model;


import java.util.ArrayList;

import com.esiea.tetris.utils.vec2;

public class Tetrimino {
    private vec2 position;

    private ArrayList <int[][]> layout; // tableau représentant la configuration du Tetrimino selon son orientation
    
    private int orientation; // nombre représentant l'orientation du Tetrimino
    /*
     * 	/!\ En général on aura 4 orientations, donc valeurs possibles : 0,1,2,3
     *  
     *  Dans certains cas on aura 2 valeurs possibles : 0,1
     *  Pour des pièces dont les orientations sont identiques deux à deux | _ | _
     *  
     *  Ou une seule valeur possible : 0
     *  Pour les pièces carrées
     *  
     */
    
    private int indiceCouleur; // indice permettant de différencier la couleur d'un tétrimino
    
    public Tetrimino(){
        
    }
    
    public Tetrimino(Tetrimino t){
        setRepresentation(t.getFullRepresentation());
        setPosition(t.getPosition());
    }
    
    public final void setPosition(vec2 _position){
        this.position = _position;
    }
    
    public final vec2 getPosition(){
        return this.position;
    }
    
    public final int getIndiceCouleur() {
    	return indiceCouleur;
    }
    
    public final void setRepresentation(ArrayList<int[][]> layout2){
        this.layout = layout2;
    }
    
    public final ArrayList<int[][]> getFullRepresentation(){
        return layout;
    }
    
    public final void incrementRotation(){
    	// On incrémente le nombre représentant l'état de rotation, modulo le nombre d'orientation possible
        this.orientation=(this.orientation+1)%(this.layout.size());
    }
    
    public final void decrementRotation(){
    	// On décrémente le nombre représentant l'état de rotation, modulo le nombre d'orientation possible
    	this.orientation--; 
        if(this.orientation < 0)
            this.orientation = this.layout.size()-1;
    }
    
    public final int[][] getLayoutForActualOrientation()
    {
    	return layout.get(orientation);
    }
    
    // Retourne une liste contenant les coordonnées exactes de chaque point constituant le tetrimino
    // Facilite un grand nombre d'actions ultérieures tel que pour dessiner le Tetrimino sur la surface de jeu
    public final ArrayList<vec2> getPointList()
    {
    	ArrayList<vec2> list= new ArrayList <vec2>();
    	
    	// On récupère le tableau du layout décrivant l'état actuel du Tetrimino
    	// ex :
    	//110
        //010
        //011
        int [][] lay=getLayoutForActualOrientation();

        // On récupère les dimensions du tableau dans des variables pour simplifier l'écriture des calculs qui vont suivre
        int wLay=lay.length;
        int hLay=lay[0].length;

        int xChecked,yChecked; // Variables intermédiaires pour simplifier l'écriture des calculs qui vont suivre

        // On parcourt chaque case du layout du tetrimino
        for(int i = 0; i <wLay ; i++){
            for(int j = 0; j <hLay ; j++){
                if(lay[i][j]==1) // Si la case correspond au "corps" du tétrimino, on ajoute un point à la liste
                {
                    // On récupère les coordonnées de la case de la grille correspondante
                    xChecked=i+position.x-Math.round(wLay/2);
                    yChecked=j+position.y-Math.round(hLay/2);

                    list.add(new vec2(xChecked,yChecked));
                }	   
            }
        }
        return list;
    }
}
