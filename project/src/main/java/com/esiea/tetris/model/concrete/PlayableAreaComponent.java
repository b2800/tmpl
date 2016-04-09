package com.esiea.tetris.model.concrete;

import com.esiea.tetris.communication.Message;
import com.esiea.tetris.communication.concrete.KeyboardInput;
import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.model.Component;
import com.esiea.tetris.model.Tetrimino;
import com.esiea.tetris.utils.vec2;
import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.KeyboardInput.Direction;
import com.esiea.tetris.communication.concrete.KeyboardInput.Type;
import java.util.ArrayList;
import net.engio.mbassy.listener.Handler;

public class PlayableAreaComponent extends Component
                                   implements Drawable {
    
    private Tetrimino currentTetrimino;
    private int[][] grid;

    public PlayableAreaComponent(vec2 size) {
        this.setSize(size);
        grid = new int[size.y][size.x];
        clearGrid();
        MessageBus.getInstance().subscribe(this);
    }
    
    @Handler
    public void handle(KeyboardInput input){
        if(input.getKeyType() == Type.ARROW_KEY){
            moveTetrimino(input.getDirection());
        } else if (input.getKeyType() == Type.CHARACTER && input.getCharacter() == ' '){
            dropTetrimino();
        }
    }
    
    private void moveTetrimino(Direction d){
        switch(d){
            case LEFT:
            	TetriminoMover.moveLeft(currentTetrimino);
            	// Si collision, on "annule" le mouvement du Tetrimino
            	if(CollisionSolver.isInCollision(grid, currentTetrimino))
            		TetriminoMover.moveRight(currentTetrimino);
                break;
            case RIGHT:
            	TetriminoMover.moveRight(currentTetrimino);
            	if(CollisionSolver.isInCollision(grid, currentTetrimino))
            		TetriminoMover.moveLeft(currentTetrimino);
                break;
            case DOWN:
            	TetriminoMover.moveDown(currentTetrimino);
            	if(CollisionSolver.isInCollision(grid, currentTetrimino))
            		TetriminoMover.moveUp(currentTetrimino);
                break;
        }
    }
    
    private void dropTetrimino(){
    	TetriminoMover.moveBottom(currentTetrimino, size.y);
    }
    
    // Ajoute le Tetrimino à la grille (devient alors "statique", non contrôlable par l'utilsateur)
    private void addTetriminoToGrid()
    {
    	// Pour chaque point constituant le Tetrimino
		for (vec2 pt : currentTetrimino.getPointList()) {

			grid[pt.x][pt.y]=currentTetrimino.getIndiceCouleur();
		}
    }
    
    @Override
    public void update() {
    	// à chaque update, le tetrimino en cours descend d'une case sous l'effet de la gravité
    	TetriminoMover.moveDown(currentTetrimino);
    	
    	// Si collision (<=> tétrimino arrive au fond)
    	if(CollisionSolver.isInCollision(grid, currentTetrimino)) 
    	{
    		// On annule le mouvement
    		TetriminoMover.moveUp(currentTetrimino);
    		
    		// On intègre le tétrimino à la grille :
    		addTetriminoToGrid();
    		
    	}
    		
    }

    @Override
    public TPanel getDrawableContainer() {
        TPanel panel = new TPanel();
        panel.add(this);
        panel.setPosition(this.position);
        panel.setSize(this.size);
        panel.setDrawBorder(true);
        return panel;
    }

    @Override
    public String[] getDrawableText() {
        String[] output = new String[size.y];
        for(int y = 0; y < size.y; y++){
            output[y] = "";
            for(int x = 0; x < size.x; x++){
                output[y] += grid[y][x];
            }
        }
        return output;
    }

    @Override
    public vec2 getDrawableRelativePosition() {
        return new vec2(0,0);
    }
    
    private void clearGrid(){
        for(int y = 0; y < size.y; y++){
            for(int x = 0; x < size.x ; x++){
                grid[y][x] = 0;
            }
        }
    }
}
