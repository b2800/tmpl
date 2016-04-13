package com.esiea.tetris.model;

import com.esiea.tetris.core.Updatable;
import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.utils.Vec2;
import java.util.ArrayList;

/*
 * A component defines : 
    + A position (on a grid)
    + The 2D space occupied by the component
    + The parent layout on which it's attached

   Concrete components defines the logic to run on each updates.
 */

public abstract class Component{
    protected Vec2 position;
    protected Vec2 size;
    protected Layout parent;
    
    public Component(){
        
    }
    
    public Vec2 getPosition() {
        return position;
    }

    public void setPosition(Vec2 position) {
        this.position = position;
    }

    public Vec2 getSize() {
        return size;
    }

    public void setSize(Vec2 size) {
        this.size = size;
    }
    
    public void setParent(Layout _parent){
        this.parent = _parent;
    }
    
    public Layout getParent(){
        return this.parent;
    }
    
    public abstract TPanel getDrawableContainer();
}
