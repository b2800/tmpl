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
    private long lastRefreshTime;
    private long refreshRate;
    
    public ConsoleRenderer(){
        this.createWindow();
        inputAdapter = new LanternaInputAdapter();
        grid = new char[terminalSize.getRows()][terminalSize.getColumns()];
        clearGrid();
        lastRefreshTime = 0;
        refreshRate = 50;
    }
    
    @Override
    public void update(Layout _layout){
        inputAdapter.transferInput(terminal);
        if(shouldDraw()){
            draw(_layout);
        }
    }
        
    private void draw(Layout _layout){
        // Changes an internal grid with the new data.
        _layout.getDrawableContainers().stream().forEach((panel) -> {
            drawPanel(panel);
        });
        
        drawToTerminal();   // Actually sends the data to the terminal
        clearGrid();
        lastRefreshTime = System.currentTimeMillis();
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
            if(pos.x + i >= grid[0].length || pos.y >= grid.length){
                continue;
            }
            grid[pos.y][pos.x + i] = text.charAt(i);
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
            for(int y = 0; y < grid.length; y++){
                terminal.setCursorPosition(0, y);
                for(int x = 0; x < grid[0].length; x++){
                    terminal.putCharacter(grid[y][x]);
                }
            }
            terminal.flush();
        } catch (IOException ex) {
            Logger.getLogger(ConsoleRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void clearGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                grid[i][j] = ' ';
            }
        }
    }
    
    private void showGrid(){
        System.out.println("----------------------------------");
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println(" ");
        }
        System.out.println("----------------------------------");
    }
    
    private boolean shouldDraw(){
        long delta = System.currentTimeMillis() - lastRefreshTime;
        return delta > refreshRate;
    }
}