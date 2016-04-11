package com.esiea.tetris.utils;

public class GridUtil {
    
    public static <T> void showGrid(T[][] grid){
        System.out.println("----------------------------------");
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }
        System.out.println("----------------------------------");
    }
    
        
    public static <T> void clearGrid(T[][] grid, T defaultValue){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                grid[i][j] = defaultValue;
            }
        }
    }
}
