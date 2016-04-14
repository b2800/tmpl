package com.esiea.tetris.common;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.KeyboardInput;
import com.esiea.tetris.communication.concrete.KeyboardInput.Direction;
import com.esiea.tetris.communication.concrete.KeyboardInput.Type;
import net.engio.mbassy.bus.publication.SyncAsyncPostCommand;

public class InputHelper {
    
    public static SyncAsyncPostCommand sendCharacter(char c){
        KeyboardInput input = new KeyboardInput();
        input.setKeyType(Type.CHARACTER);
        input.setCharacter(c);
        return MessageBus.getInstance().post(input);
    }
    
    public static void sendString(String line, boolean sync){
        for(int i = 0; i < line.length(); i++){
            char c = line.charAt(i);
            if(sync)
                sendCharacter(c).now();
            else
                sendCharacter(c).asynchronously();
        }
    }
    
    public static void sendString(String s){
        sendString(s, false);
    }

    public static SyncAsyncPostCommand sendSpecialCharacter(Type type) {
        KeyboardInput input = new KeyboardInput();
        input.setKeyType(type);
        return MessageBus.getInstance().post(input);
    }
    
    public static SyncAsyncPostCommand sendArrowKey(Direction direction) {
        KeyboardInput input = new KeyboardInput();
        input.setDirection(direction);
        input.setKeyType(Type.ARROW_KEY);
        return MessageBus.getInstance().post(input);
    }
}
