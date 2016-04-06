package com.esiea.tetris.graphics.concrete;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.Renderer;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.graphics.exceptions.OutOfBoundsDrawException;
import com.esiea.tetris.model.Layout;
import com.esiea.tetris.utils.vec2;
import com.esiea.tetris.model.TButton;
import com.googlecode.lanterna.TerminalSize;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleRenderer extends Renderer{
    private char[][] grid;
    private TerminalSize terminalSize;
    private Terminal terminal;
    private LanternaInputAdapter inputAdapter;
    
    public ConsoleRenderer(){
        this.createWindow();
        inputAdapter = new LanternaInputAdapter();
        grid = new char[terminalSize.getRows()][terminalSize.getColumns()];
        clearGrid();
    }
    
    @Override
    public void update(Layout _layout){
        inputAdapter.transferInput(terminal);
        draw(_layout);
    }
        
    private void draw(Layout _layout){
        // Changes an internal grid with the new data.
        _layout.getDrawableContainers().stream().forEach((panel) -> {
            drawPanel(panel);
        });
        
        try {
            drawToTerminal();   // Actually sends the data to the terminal
            terminal.flush();
            clearGrid();
        } catch (IOException ex) {
            Logger.getLogger(ConsoleRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void drawPanel(TPanel panel){
        panel.getDrawables().stream().forEach( (d) -> {
            drawText(d.getDrawableText(), 
                     d.getDrawableRelativePosition(), 
                     panel.getPosition());
        });
    }
    
    private void drawText(String[] text, vec2 position, vec2 offset){
        for(int i = 0; i < text.length; i++){
            vec2 pos = new vec2(position);
            pos.y += i;
            pos.y += offset.y;
            pos.x += offset.x;
            insertLineAt(text[i], pos);
        }
    }
    
    private void insertLineAt(String text, vec2 pos){
        for(int i = 0; i < text.length(); i++){
            if(pos.x + i >= grid.length || pos.y >= grid[0].length){
                continue;
            }
            grid[pos.x+i][pos.y] = text.charAt(i);
        }
    }

    private void createWindow(){
        try {
            // Setup terminal and screen layers
            terminal = new DefaultTerminalFactory().createTerminal();
            terminal.enterPrivateMode();
            terminal.getTerminalSize();
            terminal.setCursorVisible(false);

            terminalSize = terminal.getTerminalSize();
            
        } catch (IOException ex) {
            Logger.getLogger(ConsoleRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void drawToTerminal(){
        try {
            for(int i = 0; i < grid.length; i++){
                terminal.setCursorPosition(i, 0);
                for(int j = 0; j < grid[i].length; j++){
                    terminal.putCharacter(grid[i][j]);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ConsoleRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //clearGrid();
    }
    
    private void clearGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                grid[i][j] = ' ';
            }
        }
    }
}