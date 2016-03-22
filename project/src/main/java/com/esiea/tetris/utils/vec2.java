package com.esiea.tetris.utils;

public class vec2{
    public double  x;
    public double  y;
    
    public vec2(double _x, double _y){
        this.x = _x;
        this.y = _y;
    }
    
    public void add(vec2 v)
    {
    	this.x+=v.x;
    	this.y+=v.y;
    }
    
    public void add(double x, double y)
    {
    	this.x+=x;
    	this.y+=y;
    }
}