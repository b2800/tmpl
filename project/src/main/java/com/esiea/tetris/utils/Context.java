package com.esiea.tetris.utils;

public class Context {
    
    private static vec2  windowSize;
    
    public static vec2 getWindowSize(){
        return windowSize;
    }
    
    public static void setWindowSize(vec2 size){
        windowSize = size;
    }
}
