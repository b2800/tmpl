package com.esiea.tetris.graphics;

import com.esiea.tetris.utils.vec2;
import java.util.ArrayList;

public class TPanel {
    private ArrayList<Drawable> drawables;
    private vec2 size;
    private vec2 position;
    private boolean border;
    
    public TPanel(){
        drawables = new ArrayList<>();
        size = new vec2(0,0);
        position = new vec2(0,0);
    }
    
    public void add(Drawable d){
        if(drawables.contains(d)){ return; }
        drawables.add(d);
    }
    
    public ArrayList<Drawable> getDrawables(){
        return drawables;
    }
    
    public vec2 getSize(){
        return size;
    }
    
    public vec2 getPosition(){
        return position;
    }
    
    public void setSize(vec2 _size){
        this.size = _size;
    }
    
    public void setPosition(vec2 _position){
        this.position = _position;
    }
    
    public void setDrawBorder(boolean val){
        border = val;
    }
    
    public boolean drawBorder(){
        return border;
    }
}
