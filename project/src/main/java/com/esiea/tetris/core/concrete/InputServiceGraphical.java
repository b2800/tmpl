package com.esiea.tetris.core.concrete;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.KeyboardInput;
import com.esiea.tetris.core.Updatable;
import com.esiea.tetris.utils.KeyUtil;
import com.googlecode.lanterna.input.KeyStroke;

public class InputServiceGraphical implements Updatable, KeyListener {

	JFrame provider;

    public InputServiceGraphical(JFrame frame) {
    	
    	provider=frame;
        provider.addKeyListener(this);
        
    }

	@Override
	public void keyPressed(KeyEvent e) {
		
		System.out.println("Touche pressée : " + e.getKeyCode() + 
                " (" + e.getKeyChar() + ")");
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void update() {
		
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
