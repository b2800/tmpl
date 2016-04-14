package com.esiea.tetris.model.concrete.console.prompt;

import com.esiea.tetris.communication.concrete.KeyboardInput;
import java.util.ArrayList;
import net.engio.mbassy.listener.Handler;

public class CommandHistory {
    
    private int position;
    private ArrayList<String> history;

    public CommandHistory() {
        history = new ArrayList<>();
    }

    @Handler
    public void handle(KeyboardInput msg){
        if(msg.getKeyType() != KeyboardInput.Type.ARROW_KEY){ return; }
        move(msg.getDirection());
    }
    
    private void move(KeyboardInput.Direction d){
        int previousPosition = position;
        switch(d){
            case UP:
                position--;
                break;
            case DOWN:
                position++;
                break;
            default:
        }
        if(position > history.size() || position < 0)
            position = previousPosition;
    }
    
    public void resetPosition(){
        position = 0;
    }
    
    public void add(String cmd){
        history.add(cmd);
    }
}
