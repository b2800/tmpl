package com.esiea.tetris.communication.concrete;

import com.esiea.tetris.communication.Message;

public class GridStateNotification extends Message{
    private int[][] grid;
    private int[][] colorMap;
    private int id;

    public GridStateNotification() {
        grid = new int[0][0];
        colorMap = new int[0][0];
        setPropagateOverNetwork(true);
    }
    
    public int[][] getGrid() {
        return grid.clone();
    }

    public void setGrid(int[][] grid) {
        this.grid = grid.clone();
    }
    
    public int[][] getColorMap() {
        return colorMap.clone();
    }

    public void setColorMap(int[][] colorMap) {
        this.colorMap = colorMap.clone();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
