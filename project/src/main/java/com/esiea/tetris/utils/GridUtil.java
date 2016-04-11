package com.esiea.tetris.utils;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.LineNotification;
import com.esiea.tetris.graphics.TCharacter;
import java.util.ArrayList;

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
    
    public static void clearGrid(TCharacter[][] grid, char c){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                grid[i][j] = new TCharacter(c, new int[]{255,255,255});
            }
        }
    } 
    
    public static void clearGrid(int[][] grid, int c){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                grid[i][j] = c;
            }
        }
    } 
    
    public static int[][] logicalAnd(int[][] grid, int val){
        for(int y = 0; y < grid.length; y++){
            for(int x = 0; x < grid[0].length; x++){
                if(grid[y][x] != 0){
                    grid[y][x] = val;
                }
            }
        }
        return grid;
    }
    
    
    public static ArrayList<Integer> getAllFullLines(int[][] grid){
        ArrayList<Integer> fullLines = new ArrayList<>();
        
        for(int y = 0; y < grid.length; y++){
            boolean full = true;
            for(int x = 0; x < grid[0].length; x++){
                if(grid[y][x] == 0)
                    full = false;
            }
            if(full)
                fullLines.add(y);
        }
        return fullLines;
    }
    
    public static void shiftDownAt(int[][] grid, int index){
        while(index >= 0){
            copyLineAboveAt(grid, index);
            index--;
        }
    }
    
    private static void copyLineAboveAt(int[][]grid, int index){
        if(index >= grid.length ){ return; }
        for(int x = 0; x < grid[0].length; x++){
            if(index == 0){
                grid[index][x] = 0;
            } else {
                grid[index][x] = grid[index - 1][x];
            }
        }
    }
}
