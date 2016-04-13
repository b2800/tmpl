package com.esiea.tetris.communication.concrete;

import com.esiea.tetris.model.Tetrimino;

public class NextTetriminos {
    private Tetrimino[] sequence;
    
    public NextTetriminos() {
        sequence = new Tetrimino[0];
    }
    
    public Tetrimino[] getSequence() {
        return sequence.clone();
    }

    public void setSequence(Tetrimino[] sequence) {
        this.sequence = sequence.clone();
    }
}
