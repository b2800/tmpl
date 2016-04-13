package com.esiea.tetris.utils;

import com.esiea.tetris.communication.concrete.KeyboardInput;
import com.esiea.tetris.communication.concrete.KeyboardInput.Type;
import com.esiea.tetris.communication.concrete.KeyboardInput.Direction;
import com.googlecode.lanterna.input.KeyStroke;

public class KeyUtil {
    
    public static void defineType(KeyboardInput msg, KeyStroke key){
        msg.setKeyType(Type.UNSUPPORTED);
        
        switch(key.getKeyType()){
            case ArrowDown:
                msg.setKeyType(Type.ARROW_KEY);
                msg.setDirection(Direction.DOWN);
                break;
            case ArrowUp:
                msg.setKeyType(Type.ARROW_KEY);
                msg.setDirection(Direction.UP);
                break;
            case ArrowLeft:
                msg.setKeyType(Type.ARROW_KEY);
                msg.setDirection(Direction.LEFT);
                break;
            case ArrowRight:
                msg.setKeyType(Type.ARROW_KEY);
                msg.setDirection(Direction.RIGHT);
                break;
            case Character:
                msg.setKeyType(Type.CHARACTER);
                break;
            case Delete:
                msg.setKeyType(Type.DELETE);
                break;
            case Backspace:
                msg.setKeyType(Type.BACKSPACE);
                break;
            case Enter:
                msg.setKeyType(Type.ENTER);
                break;
            default:
        }
    }
}
