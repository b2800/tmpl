package com.esiea.tetris.graphics.concrete;

import com.esiea.tetris.model.Tetrimino;
import com.esiea.tetris.graphics.Renderer;
import com.esiea.tetris.graphics.exceptions.OutOfBoundsDrawException;
import com.esiea.tetris.model.Layout;
import com.esiea.tetris.utils.vec2;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.gui2.EmptySpace;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleRenderer extends Renderer{
    private char[][] grid;
    
    public ConsoleRenderer(){
        this.createWindow();
    }
        
    @Override
    public void Draw(Layout _layout) throws OutOfBoundsDrawException{
        
    }
    
    private void DrawTetrimino(Tetrimino _block, vec2 _position) 
                throws OutOfBoundsDrawException{
        
    }
    
    private void DrawText(String _text, vec2 _position)
                throws OutOfBoundsDrawException{
        
    }
    
    private void DrawChar(char _c, vec2 _position){
        
    }

    private void createWindow(){
        
        try {
            // Setup terminal and screen layers
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();
            
            // Create window to hold the panel
            BasicWindow window = new BasicWindow();
            
            // Create gui and start gui
            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.DEFAULT));
            gui.addWindowAndWait(window);
        } catch (IOException ex) {
            Logger.getLogger(ConsoleRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}