package com.esiea.tetris.graphics;

import com.esiea.tetris.utils.Vec2;
import java.util.ArrayList;

public class TPanel {
    private ArrayList<Drawable> drawables;
    private Vec2 size;
    private Vec2 position;
    private boolean border;
    
    public TPanel(){
        drawables = new ArrayList<>();
        size = new Vec2(0,0);
        position = new Vec2(0,0);
    }
    
    public void add(Drawable d){
        if(drawables.contains(d)){ return; }
        drawables.add(d);
    }
    
    public ArrayList<Drawable> getDrawables(){
        return drawables;
    }
    
    public Vec2 getSize(){
        return size;
    }
    
    public Vec2 getPosition(){
        return position;
    }
    
    public void setSize(Vec2 _size){
        this.size = _size;
    }
    
    public void setPosition(Vec2 _position){
        this.position = _position;
    }
    
    public void setDrawBorder(boolean val){
        border = val;
    }
    
    public boolean drawBorder(){
        return border;
    }
}
