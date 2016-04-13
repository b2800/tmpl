package com.esiea.tetris.utils;

public class ColorUtil {
    public static int[] colorIndexToRGB(int index){
        switch(index){
            case 2: 
                return new int[]{200,0,0};
            case 3:
                return new int[]{0,200,0};
            case 4:
                return new int[]{0,0,200};
            case 5:
                return new int[]{200,200,0};
            case 6:
                return new int[]{200,0,200};
            case 7:
                return new int[]{0,200,200};
            case 8:
                return new int[]{0,0,0};
            case 1:
            default:
                return new int[]{255,255,255};
        }
    }
}
