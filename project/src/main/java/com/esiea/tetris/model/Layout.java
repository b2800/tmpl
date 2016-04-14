package com.esiea.tetris.model;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.graphics.Renderer;
import com.esiea.tetris.utils.Vec2;
import com.esiea.tetris.communication.concrete.JSONMessage;
import com.esiea.tetris.communication.concrete.NavigationIntent;
import com.esiea.tetris.core.Updatable;
import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.TPanel;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.engio.mbassy.listener.Handler;

/* 
    Layout responsability : 
        Update all it's components on each frame

    A layout defines :
    + The grid's dimensions
    + A set of components
    + The next layout that will replace this layout when it will close,
        or null if it is the last layout
*/

public class Layout{
    private ArrayList<Component> components;
    private boolean shouldClose;
    private Layout nextLayout;
    
    public Layout(){
        components = new ArrayList<>();
        shouldClose = false;
        MessageBus.getInstance().subscribe(this);
    }
    
    @Handler
    public void handle(NavigationIntent msg){
        nextLayout = msg.nextLayout;
        shouldClose = true;
    }
    
    public void addComponent(Component _c){
        if(_c != null){
            this.components.add(_c);
        }
    }
    
    public void update(){
        for(Component c : components){
            if(c instanceof Updatable){
                ((Updatable)c).update();
            }
        }
    }
    
    public boolean shouldClose(){
        return this.shouldClose;
    }
    
    public void setShouldClose(boolean b) {
        shouldClose = b;
    }
    
    public Layout next(){
        return this.nextLayout;
    }
    
    public ArrayList<TPanel> getDrawableContainers(){
        ArrayList<TPanel> panels = new ArrayList<>();
        
        this.components.stream().forEach((c) -> {
            panels.add( c.getDrawableContainer() );
        });
        
        return panels;
    }


}
