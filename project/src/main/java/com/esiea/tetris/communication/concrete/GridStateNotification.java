package com.esiea.tetris.communication.concrete;

public class GridStateNotification {
    private int[][] grid;
    private boolean propagateOverNetwork;

    public GridStateNotification() {
        
    }
    
    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public boolean isPropagateOverNetwork() {
        return propagateOverNetwork;
    }

    public void setPropagateOverNetwork(boolean propagateOverNetwork) {
        this.propagateOverNetwork = propagateOverNetwork;
    }
}
