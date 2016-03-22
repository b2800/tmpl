package com.esiea.tetris.Model;

import com.esiea.tetris.Core.Updatable;
import com.esiea.tetris.Utils.vec2;

/*
 * A component defines : 
    + A position (on a grid)
    + The 2D space occupied by the component
    + The parent layout on which it's attached

   Concrete components defines the logic to run on each updates.
 */

public abstract class Component implements Updatable{
    protected vec2 position;
    protected vec2 size;
    protected Layout parent;
    
    public Component(){

    }
    
    @Override
    public void update(){
        
    }
    
    public vec2 getPosition() {
        return position;
    }

    public void setPosition(vec2 position) {
        this.position = position;
    }

    public vec2 getSize() {
        return size;
    }

    public void setSize(vec2 size) {
        this.size = size;
    }
    
    public void setParent(Layout _parent){
        this.parent = _parent;
    }
    
    public Layout getParent(){
        return this.parent;
    }
    
}
