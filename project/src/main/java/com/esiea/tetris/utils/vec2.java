package com.esiea.tetris.utils;

public class vec2{
    public int x;
    public int y;
    
    public vec2(int _x, int _y){
        this.x = _x;
        this.y = _y;
    }
    
    public vec2(vec2 a){
        this.x = a.x;
        this.y = a.y;
    }
    
    public void add(vec2 v)
    {
    	this.x+=v.x;
    	this.y+=v.y;
    }
    
    public void add(int x, int y)
    {
    	this.x+=x;
    	this.y+=y;
    }
    
    public static vec2 sum(vec2 a, vec2 b){
        vec2 c = a;
        c.x += b.x;
        c.y += b.y;
        return c;
    }
    
    @Override
    public String toString(){
        return "x: " + Integer.toString(x) + " y:" + Integer.toString(y);
    }
}