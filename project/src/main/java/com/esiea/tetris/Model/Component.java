package com.esiea.tetris.Model;

import com.esiea.tetris.Utils.vec2;

/*
 * A component defines : 
    + A position (on a grid)
    + The 2D space occupied by the component
    + The parent layout on which it's attached

   Concrete components defines the logic to run on each updates.
 */

public abstract class Component {
    private vec2 position;
    private vec2 size;
    
    public Component Component(){
        return this;
    }
    
    public void update(){
        
    }
    
    public vec2 getPosition() {
        return position;
    }

    public Component withPosition(vec2 position) {
        this.position = position;
        return this;
    }

    public vec2 getSize() {
        return size;
    }

    public Component withSize(vec2 size) {
        this.size = size;
        return this;
    }
}
