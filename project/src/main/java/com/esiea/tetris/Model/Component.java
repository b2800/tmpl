package Model;

import Utils.vec2;

/*
 * A component defines : 
    + A position (on a grid)
    + The 2D space occupied by the component

   Concrete components defines the logic to run on each updates.
 */

public abstract class Component {
    private vec2 position;
    private vec2 size;
    
    public void Update(){
        
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
}
