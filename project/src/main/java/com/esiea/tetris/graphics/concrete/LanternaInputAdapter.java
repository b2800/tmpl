package com.esiea.tetris.graphics.concrete;

// Used to convert Lanterna input objects to internal format

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.KeyboardInput;
import com.esiea.tetris.communication.concrete.KeyboardInput.Type;
import com.esiea.tetris.utils.KeyUtil;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanternaInputAdapter {
    
    public void transferInput(Terminal terminal){
        try {
            KeyStroke key; 
            key = terminal.pollInput();

            while(key != null){
                handleKeyStroke(key);
                key = terminal.pollInput();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConsoleRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void handleKeyStroke(KeyStroke key){
        KeyboardInput msg = new KeyboardInput();
        KeyUtil.defineType(msg, key);
        if(msg.getKeyType() == Type.CHARACTER){
            msg.setCharacter(key.getCharacter());
        }
        MessageBus.getInstance().publish(msg);
    }
}
