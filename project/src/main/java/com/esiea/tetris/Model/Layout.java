package com.esiea.tetris.Model;

import com.esiea.tetris.Graphics.Exceptions.OutOfBoundsDrawException;
import com.esiea.tetris.Graphics.Renderer;
import com.esiea.tetris.Utils.vec2;
import com.esiea.tetris.communication.Message;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* 
    Layout responsability : 
        Update all it's components on each frame

    A layout defines :
    + The grid's dimensions
    + A set of components
    + The next layout that will replace this layout when it will close,
        or nothing if it is the last layout
*/

public class Layout{
    private vec2 size;
    private ArrayList<Component> components;
    private boolean shouldClose;
    private Layout nextLayout;
    
    public Layout(){
        components = new ArrayList<>();
        size = new vec2(10, 10);
    }
    
    public void setSize(vec2 _size){
        this.size = _size;
    }
    
    public void setSize(int _width, int _height){
        size = new vec2(_width, _height);
    }
    
    public void addComponent(Component _c){
        if(_c == null){
            System.out.println("Problem here");
        }
        _c.setParent(this);
        this.components.add(_c);
    }
    
    public void update(){
        this.components.stream().forEach((c) -> {
            c.update();
        });
    }
    
    public void draw(Renderer renderer){
        try{
            renderer.Draw(this);
        } catch(OutOfBoundsDrawException e){
            System.out.println("Tried to draw something outside the map");
        } catch (Exception ex) {
            Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean shouldClose(){
        return this.shouldClose;
    }
    
    public Layout next(){
        return this.nextLayout;
    }
    
    public void setNextLayout(Layout next){
        this.nextLayout = next;
    }
    
    public ArrayList<Component> getComponents(){
        return this.components;
    }
    
    public void setShouldClose(boolean _val){
        this.shouldClose = _val;
    }
}
