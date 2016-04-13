package com.esiea.tetris.utils;

public class Vec2{
    public int x;
    public int y;
    
    public Vec2(int _x, int _y){
        this.x = _x;
        this.y = _y;
    }
    
    public Vec2(Vec2 a){
        this.x = a.x;
        this.y = a.y;
    }
    
    public void add(Vec2 v)
    {
    	this.x+=v.x;
    	this.y+=v.y;
    }
    
    public void add(int x, int y)
    {
    	this.x+=x;
    	this.y+=y;
    }
    
    public static Vec2 sum(Vec2 a, Vec2 b){
        Vec2 c = a;
        c.x += b.x;
        c.y += b.y;
        return c;
    }
    
    @Override
    public String toString(){
        return "x: " + Integer.toString(x) + " y:" + Integer.toString(y);
    }
}