package com.esiea.tetris.graphics.concrete;

import com.esiea.tetris.graphics.Renderer;
import com.esiea.tetris.graphics.TCharacter;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.model.Layout;
import com.esiea.tetris.utils.ColorUtil;
import com.esiea.tetris.utils.GridUtil;
import com.esiea.tetris.utils.vec2;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import static java.lang.Math.abs;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleRenderer extends Renderer{
    private TCharacter[][] grid;
    private TerminalSize terminalSize;
    private Terminal terminal;

    private long lastRefreshTime;
    private long refreshRate;
    
    public ConsoleRenderer(){
        this.createWindow();
        grid = new TCharacter[terminalSize.getRows()][terminalSize.getColumns()];
        clearGrids();
        lastRefreshTime = 0;
        refreshRate = 50;
    }
    
    @Override
    public void process(Layout _layout){
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
        clearGrids();
        lastRefreshTime = System.currentTimeMillis();
    }
    
    private void drawPanel(TPanel panel){
        if(panel == null){ return; }
        panel.getDrawables().stream().forEach( (d) -> {
            drawText(d.getDrawableText(),
                     d.getColorMap(),
                     d.getDrawableRelativePosition(), 
                     panel.getPosition());
        });
        if(panel.drawBorder()){
            drawSquare(panel.getPosition(), panel.getSize(), false);
        }
    }
    
    private void drawText(String[] text, int[][] colors, vec2 position, vec2 offset){
        for(int i = 0; i < text.length; i++){
            vec2 pos = new vec2(position);
            pos.y += i;
            pos.y += offset.y;
            pos.x += offset.x;
            insertLineAt(text[i], colors, pos);
        }
    }
    
    private void insertLineAt(String text, int[][] colors,  vec2 pos){
        for(int i = 0; i < text.length(); i++){
            if(pos.x + i >= grid[0].length || pos.y >= grid.length){
                continue;
            }
            if(colors != null)
                grid[pos.y][pos.x + i] = new TCharacter(text.charAt(i), colors[i]);
            else
                grid[pos.y][pos.x + i] = new TCharacter(text.charAt(i));
        }
    }
    
    private void drawSquare(vec2 position, vec2 size, boolean filled){
        if(filled){ return; }
        vec2 topLeft = new vec2(position);
            topLeft.add(new vec2(-1, -1));
        vec2 topRight = new vec2(position);
            topRight.x += size.x;
            topRight.add(new vec2(0, -1));
        vec2  bottomLeft = new vec2(position);
            bottomLeft.y += size.y;
            bottomLeft.add(new vec2(-1, 0));
        vec2 bottomRight = new vec2(position);
            bottomRight.add(size);
        
        drawLine(topLeft, topRight);
        drawLine(bottomLeft, bottomRight);
        drawLine(topLeft, bottomLeft);
        drawLine(topRight, bottomRight);
    }
    
    private void drawLine(vec2 start, vec2 end){
        if(start.x == end.x){
            for(int i = 0; i < abs(end.y - start.y); i++){
                grid[i+start.y][start.x] = new TCharacter('|');
            }
        } else if (start.y == end.y){
            for(int i = 0; i < abs(end.x - start.x); i++){
                grid[start.y][i+start.x] = new TCharacter('-');
            }
        }
        grid[start.y][start.x] = new TCharacter('+');
        grid[end.y][end.x] = new TCharacter('+');
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
                    TCharacter tc = grid[y][x]; 
                    if(tc == null){
                        tc = new TCharacter(' '); // Hack étrange a cause du langage
                    }
                    int[] colors = tc.getColor();
                    terminal.setForegroundColor(TextColor.Indexed.fromRGB(colors[0], colors[1], colors[2]));
                    terminal.putCharacter(tc.getCharacter());
                }
            }
            terminal.flush();
        } catch (IOException ex) {
            Logger.getLogger(ConsoleRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean shouldDraw(){
        long delta = System.currentTimeMillis() - lastRefreshTime;
        return delta > refreshRate;
    }
    
    public Terminal getTerminal() {
        return terminal;
    }
    
    private void clearGrids(){
        GridUtil.clearGrid(grid, ' ');
    }
    

}