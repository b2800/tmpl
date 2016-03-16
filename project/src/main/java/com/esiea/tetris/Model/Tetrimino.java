package com.esiea.tetris.Model;

// test modification

import com.esiea.tetris.Utils.vec2;

public abstract class Tetrimino {
    private vec2 position;
    private int rotation;
    private int[][] layout;
    private char visual;
    
    public final void setPosition(vec2 _position){
        this.position = _position;
    }
    
    public final void setRepresentation(int[][] _rep){
        this.layout = _rep;
    }
    
    public class I extends Tetrimino{
        
        public I(vec2 _position) {
            int[][] layout = new int[][]{
                {1, 1, 1, 1}
            };
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
    
    public class J extends Tetrimino{
        
        public J(vec2 _position) {
            int[][] layout = new int[][]{
                {1, 1, 1},
                {0, 0, 1}
            };
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
        
    public class L extends Tetrimino{
        
        public L(vec2 _position) {
            int[][] layout = new int[][]{
                {1, 1, 1},
                {1, 0, 0}
            };
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
            
    public class O extends Tetrimino{
        
        public O(vec2 _position) {
            int[][] layout = new int[][]{
                {1, 1},
                {1, 1}
            };
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
                
    public class S extends Tetrimino{
        
        public S(vec2 _position) {
            int[][] layout = new int[][]{
                {0, 1, 1},
                {1, 1, 0}
            };
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
    
    public class T extends Tetrimino{
        
        public T(vec2 _position) {
            int[][] layout = new int[][]{
                {1, 1, 1},
                {0, 1, 0}
            };
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
    
    public class Z extends Tetrimino{
        
        public Z(vec2 _position) {
            int[][] layout = new int[][]{
                {1, 1, 0},
                {0, 1, 1}
            };
            this.setPosition(_position);
            this.setRepresentation(layout);
        }
    }
}
