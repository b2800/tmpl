package com.esiea.tetris.model.concrete.console.prompt;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.KeyboardInput;
import com.esiea.tetris.communication.concrete.KeyboardInput.Direction;
import com.esiea.tetris.communication.concrete.KeyboardInput.Type;
import net.engio.mbassy.listener.Handler;

public class Cursor {

    int position;
    StringBuffer userInputReference;
    
    public Cursor(StringBuffer userInput) {
        userInputReference = userInput;
        position = 0;
        MessageBus.getInstance().subscribe(this);
    }
    
    @Handler
    public void handle(KeyboardInput msg){
        if(msg.getKeyType() != Type.ARROW_KEY){ return; }
        move(msg.getDirection());
    }
    
    public void move(Direction d){
        int previousPosition = position;
        switch(d){
            case LEFT:
                position--;
                break;
            case RIGHT:
                position++;
                break;
            default:
        }
        if(position > userInputReference.length() || position < 0)
            position = previousPosition;
    }
    
    public int getPosition(){
        return position;
    }
    
    public void setPosition(int p){
        this.position = p;
    }
}
