package com.esiea.tetris.Model;

import com.esiea.tetris.Communication.Message;
import com.esiea.tetris.Graphics.Exceptions.OutOfBoundsDrawException;
import com.esiea.tetris.Graphics.Renderer;
import com.esiea.tetris.Utils.vec2;
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
    private boolean should_close;
    private Layout next_layout;
    
    public Layout Layout(){
        components = new ArrayList<>();
        size = new vec2(10, 10);
        return this;
    }
    
    public Layout withSize(vec2 _size){
        this.size = _size;
        return this;
    }
    
    public Layout withSize(int _width, int _height){
        size = new vec2(_width, _height);
        return this;
    }
    
    public void addComponent(Component _c){
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
        return this.should_close;
    }
    
    public Layout next(){
        return this.next_layout;
    }
    
    public void setNextLayout(Layout next){
        this.next_layout = next;
    }
    
    public ArrayList<Component> getComponents(){
        return this.components;
    }
}
