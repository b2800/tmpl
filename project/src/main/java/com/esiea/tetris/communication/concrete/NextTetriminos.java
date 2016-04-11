package com.esiea.tetris.communication.concrete;

import com.esiea.tetris.model.Tetrimino;

public class NextTetriminos {
    private Tetrimino[] sequence;
    
    public NextTetriminos() {

    }
    
    public Tetrimino[] getSequence() {
        return sequence;
    }

    public void setSequence(Tetrimino[] sequence) {
        this.sequence = sequence;
    }
}
