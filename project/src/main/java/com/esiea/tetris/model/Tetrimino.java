package com.esiea.tetris.model;


import java.util.ArrayList;

import com.esiea.tetris.utils.vec2;

public abstract class Tetrimino {
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
    
    private char indiceCouleur; // indice permettant de différencier la couleur d'un tétrimino
    
    public final void setPosition(vec2 _position){
        this.position = _position;
    }
    
    public final vec2 getPosition(){
        return this.position;
    }
    
    public final void setRepresentation(ArrayList<int[][]> layout2){
        this.layout = layout2;
    }
    
    public final void incrementRotation(){
    	// On incrémente le nombre représentant l'état de rotation, modulo le nombre d'orientation possible
        this.orientation=(this.orientation+1)%this.layout.size();
    }
    
    public final void decrementRotation(){
    	// On décrémente le nombre représentant l'état de rotation, modulo le nombre d'orientation possible
    	this.orientation=(this.orientation-1)%this.layout.size();
    }
    
    public final int[][] getLayoutForActualOrientation()
    {
    	return layout.get(orientation);
    }
    
    public class I extends Tetrimino{
        
        public I(vec2 _position) {
        	

            layout=new ArrayList <int[][]>();
            
            layout.add(new int[][]{
                {1, 1, 1}
            });
            
            layout.add(new int[][]{
                    {1},
                    {1},
                    {1}
                });
            
            // Les deux orientation suivante ne sont pas nécessaires car elles se répètent
            
            /*layout.add(new int[][]{
                    {1, 1, 1}
                });
            
            layout.add(new int[][]{
                    {1},
                    {1},
                    {1}
                });*/
            
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
    
    public class J extends Tetrimino{
        
        public J(vec2 _position) {
        	
        	layout=new ArrayList <int[][]>();
            
            layout.add(new int[][]{
                    {1, 1, 1},
                    {0, 0, 1}
                });
            
            layout.add(new int[][]{
                    {0,1},
                    {0,1},
                    {1,1}
                });
            
            layout.add(new int[][]{
                    {1, 0, 0},
                    {1, 1, 1}
                });
            
            layout.add(new int[][]{
                    {1,1},
                    {1,0},
                    {1,0}
                });
            
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
        
    public class L extends Tetrimino{
        
        public L(vec2 _position) {
        	
        	layout=new ArrayList <int[][]>();
            
            layout.add(new int[][]{
                    {1, 1, 1},
                    {1, 0, 0}
                });
            
            layout.add(new int[][]{
                    {1,1},
                    {0,1},
                    {0,1}
                });
            
            layout.add(new int[][]{
                    {0, 0, 1},
                    {1, 1, 1}
                });
            
            layout.add(new int[][]{
                    {1,0},
                    {1,0},
                    {1,1}
                });
            
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
            
    public class O extends Tetrimino{
        
        public O(vec2 _position) {
        	
        	layout=new ArrayList <int[][]>();
        	
        	layout.add(new int[][]{
                {1, 1},
                {1, 1}
            });
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
                
    public class S extends Tetrimino{
        
        public S(vec2 _position) {
        	
layout=new ArrayList <int[][]>();
            
            layout.add(new int[][]{
                    {0, 1, 1},
                    {1, 1, 0}
                });
            
            layout.add(new int[][]{
                    {1,0},
                    {1,1},
                    {0,1}
                });
            
            /*
            layout.add(new int[][]{
                    {0, 1, 1},
                    {1, 1, 0}
                });
            
            layout.add(new int[][]{
                    {1,0},
                    {1,1},
                    {0,1}
                });
            */
        	
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
    
    public class T extends Tetrimino{
        
        public T(vec2 _position) {
        	
        	layout=new ArrayList <int[][]>();
            
            layout.add(new int[][]{
            		{1, 1, 1},
                    {0, 1, 0}
                });
            
            layout.add(new int[][]{
                    {0,1},
                    {1,1},
                    {0,1}
                });
            
            layout.add(new int[][]{
                    {0, 1, 0},
                    {1, 1, 1}
                });
            
            layout.add(new int[][]{
                    {1,0},
                    {1,1},
                    {1,0}
                });
            
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
    
    public class Z extends Tetrimino{
        
        public Z(vec2 _position) {
        	
        	layout=new ArrayList <int[][]>();
        	
        	layout.add(new int[][]{
        			{1, 1, 0},
                    {0, 1, 1}
                });
            
            layout.add(new int[][]{
                    {0,1},
                    {1,1},
                    {1,0}
                });
            
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
}
