package com.esiea.tetris.communication.concrete;

import java.io.Serializable;

public class GridStateNotification implements Serializable{
    private int[][] grid;
    private int[][] colorMap;
    private boolean propagateOverNetwork;
    private int id;

    public GridStateNotification() {
        propagateOverNetwork = true;
    }
    
    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public boolean shouldPropagateOverNetwork() {
        return propagateOverNetwork;
    }

    public void setPropagateOverNetwork(boolean propagateOverNetwork) {
        this.propagateOverNetwork = propagateOverNetwork;
    }
    
    public int[][] getColorMap() {
        return colorMap;
    }

    public void setColorMap(int[][] colorMap) {
        this.colorMap = colorMap;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
