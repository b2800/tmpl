package com.esiea.tetris.utils;

public class Context {
    
    private static Vec2  windowSize;
    
    public static Vec2 getWindowSize(){
        return windowSize;
    }
    
    public static void setWindowSize(Vec2 size){
        windowSize = size;
    }
}
