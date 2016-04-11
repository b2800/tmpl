package com.esiea.tetris.core.concrete;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.KeyboardInput;
import com.esiea.tetris.core.Updatable;
import com.esiea.tetris.graphics.concrete.ConsoleRenderer;
import com.esiea.tetris.utils.KeyUtil;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputService implements Updatable{
    InputProvider provider;

    public InputService(InputProvider p) {
        setProvider(p);
    }
    
    public final void setProvider(InputProvider p){
        provider = p;
    }

    @Override
    public void update(){
        try {
            KeyStroke key; 
            key = provider.pollInput();

            while(key != null){
                handleKeyStroke(key);
                key = provider.pollInput();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConsoleRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void handleKeyStroke(KeyStroke key){
        KeyboardInput msg = new KeyboardInput();
        KeyUtil.defineType(msg, key);
        if(msg.getKeyType() == KeyboardInput.Type.CHARACTER){
            msg.setCharacter(key.getCharacter());
        }
        MessageBus.getInstance().publish(msg);
    }
}
