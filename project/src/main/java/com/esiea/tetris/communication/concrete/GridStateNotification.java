package com.esiea.tetris.communication.concrete;

import java.io.Serializable;

public class GridStateNotification implements Serializable{
    private int[][] grid;
    private int[][] colorMap;
    private int id;

    public GridStateNotification() {
        grid = new int[0][0];
        colormap = new int[0][0];
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
