package com.esiea.tetris.model.builder;

import com.esiea.tetris.model.Tetrimino;
import com.esiea.tetris.utils.vec2;
import java.util.ArrayList;

public class TetriminoBuilder {
    
    public static Tetrimino getRandomTetrimino(){
        int number = (int)(Math.random()*7);
        switch(number){
            case 0:
                return createI();
            case 1:
                return createJ();
            case 2:
                return createL();
            case 3:
                return createO();
            case 4:
                return createS();
            case 5:
                return createT();
            case 6:
                return createZ();  
        }
        return null;
    }
    
    public static Tetrimino createI(){
        Tetrimino I = new Tetrimino();
        ArrayList<int[][]>layout = new ArrayList <int[][]>();
            
        layout.add(new int[][]{
            {1, 1, 1, 1}
        });

        layout.add(new int[][]{
            {1},
            {1},
            {1},
            {1}
        });

        // Les deux orientations suivantes ne sont pas nécessaires car elles se répètent
        I.setRepresentation(layout);
        return I;
    }
    
    public static Tetrimino createJ(){
        Tetrimino J = new Tetrimino();
        ArrayList<int[][]> layout = new ArrayList <int[][]>();
            
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

        J.setRepresentation(layout);
        return J;
    }
        
    public static Tetrimino createL(){

        Tetrimino L = new Tetrimino();
        	
        ArrayList<int[][]> layout = new ArrayList <int[][]>();
            
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

        L.setRepresentation(layout);
        return L;
    }
            
    public static Tetrimino createO(){
        Tetrimino O = new Tetrimino();
        ArrayList<int[][]> layout = new ArrayList <int[][]>();

        layout.add(new int[][]{
            {1, 1},
            {1, 1}
        });
        
        O.setRepresentation(layout);
        return O;
    }
                
    public static Tetrimino createS(){
        Tetrimino S = new Tetrimino();
        ArrayList<int[][]> layout = new ArrayList <>();

        layout.add(new int[][]{
            {0, 1, 1},
            {1, 1, 0}
        });

        layout.add(new int[][]{
            {1,0},
            {1,1},
            {0,1}
        });

        S.setRepresentation(layout);
        return S;
    }
    
    public static Tetrimino createT(){
        Tetrimino T = new Tetrimino();
        ArrayList<int[][]> layout = new ArrayList <int[][]>();
            
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

        T.setRepresentation(layout);
        return T;
    }
    
    public static Tetrimino createZ(){
        Tetrimino Z = new Tetrimino();
        ArrayList<int[][]> layout = new ArrayList <int[][]>();

        layout.add(new int[][]{
            {1, 1, 0},
            {0, 1, 1}
        });

        layout.add(new int[][]{
            {0,1},
            {1,1},
            {1,0}
        });

        Z.setRepresentation(layout);
        return Z;
    }
}