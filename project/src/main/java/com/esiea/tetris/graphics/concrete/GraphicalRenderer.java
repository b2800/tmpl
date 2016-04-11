package com.esiea.tetris.graphics.concrete;

import static java.lang.Math.abs;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.esiea.tetris.graphics.Renderer;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.model.Layout;
import com.esiea.tetris.utils.vec2;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class GraphicalRenderer extends Renderer {
	
	private char[][] grid;
    private TerminalSize terminalSize;
    private Terminal terminal;

    private long lastRefreshTime;
    private long refreshRate;
    
    private Graphics g;
    
    private JFrame window;
    
    public GraphicalRenderer(){
    	
    	 window = new JFrame();
    	 window.setTitle("Tetris");
    	 window.setSize(400, 700);
    	 
    	 grid = new char[40][70];
         clearGrid();
         
    	 //window.setLocationRelativeTo(null);
    	 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 
    	//Instanciation d'un objet JPanel
    	JPanel pan = new JPanel();
    	
    	//Définition de sa couleur de fond
    	pan.setBackground(Color.BLACK);
    	
    	//On prévient notre JFrame que notre JPanel sera son content pane
    	window.setContentPane(pan);               
    	window.setVisible(true);
    	
    	g=pan.getGraphics();
    	 
    }
    
    public JFrame getWindow()
    {
    	return window;
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
        clearGrid();
        lastRefreshTime = System.currentTimeMillis();
    }
    
    private void drawPanel(TPanel panel){
        if(panel == null){ return; }
        panel.getDrawables().stream().forEach( (d) -> {
            drawText(d.getDrawableText(), 
                     d.getDrawableRelativePosition(), 
                     panel.getPosition());
        });
        if(panel.drawBorder()){
            drawSquare(panel.getPosition(), panel.getSize(), false);
        }
    }
    
    // CHANGER NOM DE LA FONCTION
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
                grid[i+start.y][start.x] = '|';
            }
        } else if (start.y == end.y){
            for(int i = 0; i < abs(end.x - start.x); i++){
                grid[start.y][i+start.x] = '-';
            }
        }
        grid[start.y][start.x] = '+';
        grid[end.y][end.x] = '+';
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
    

    // FONCTION A MODIFIER
    private void drawToTerminal(){
    	//showGrid();
    	//System.out.println("drawtoscreen");
    	
    	
            for(int y = 0; y < grid.length; y++){
                for(int x = 0; x < grid[0].length; x++){
                	if(grid[y][x]!=' ')
                	{
                		//System.out.println("drawrectangle "+x+" "+y);
	                    g.setColor(Color.red);   
	                	g.fillRect(x*10, y*10,10,10);
                	}
                }
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
    
    public Terminal getTerminal() {
        return terminal;
    }

}
