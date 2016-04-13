package com.esiea.tetris.communication;

import java.io.Serializable;

public class Message implements Serializable{
    private boolean propagateOverNetwork;
    
    public Message() {
        propagateOverNetwork = false;
    }
    
    public final boolean shouldPropagateOverNetwork(){
        return propagateOverNetwork;
    }
    
    public final void setPropagateOverNetwork(boolean propagateOverNetwork){
        this.propagateOverNetwork = propagateOverNetwork;
    }
}
