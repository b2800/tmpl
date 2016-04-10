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
import com.esiea.tetris.communication.concrete.LineNotification;
import com.esiea.tetris.communication.concrete.NextTetriminos;
import com.esiea.tetris.core.Updatable;
import com.esiea.tetris.model.builder.LayoutBuilder;
import com.esiea.tetris.model.builder.TetriminoBuilder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import net.engio.mbassy.listener.Handler;
import sun.security.util.Length;

public class PlayableAreaComponent extends Component
                                   implements Drawable, Updatable {
    
    private Tetrimino currentTetrimino;
    private int[][] grid;
    private long timer;
    private long refreshInterval;
    private ArrayDeque<Tetrimino> tetriminoSequence;
    private boolean gameOver;
    private int idJoueur;

    public PlayableAreaComponent(vec2 size, int idJoueur) {
        this.setSize(size);
        grid = new int[size.y][size.x];
        clearGrid();
        refreshInterval = 1000;
        timer = System.currentTimeMillis();
        tetriminoSequence = new ArrayDeque<>();
        updateTetriminoSequence();
        currentTetrimino = tetriminoSequence.pop();
        gameOver = false;
        this.idJoueur = idJoueur;
        MessageBus.getInstance().subscribe(this);
    }
    
    @Handler
    public void handle(KeyboardInput input){
        if(input.getKeyType() == Type.ARROW_KEY){
            moveTetrimino(input.getDirection());
        } else if (input.getKeyType() == Type.CHARACTER){
            switch(input.getCharacter()){
                case ' ':
                    dropTetrimino();
                    break;
                case 'r':
                    playAgain();
                    break;
                case 'q':
                    quitToMainMenu();
                    break;
                case 'v':
                    rotateTetrimino(1);
                    break;
                case 'b':
                    rotateTetrimino(-1);
                    break;
            }
        }
    }
    
    private void moveTetrimino(Direction d){
        if(gameOver){ return; }
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
            	if(CollisionSolver.isInCollision(grid, currentTetrimino)){
            		TetriminoMover.moveUp(currentTetrimino);
                        addTetriminoToGrid();
                }
                break;
        }
    }
    
    public void rotateTetrimino(int d){
        if(d>0){
            TetriminoMover.turnLeft(currentTetrimino);
            if(CollisionSolver.isInCollision(grid, currentTetrimino))
            TetriminoMover.turnRight(currentTetrimino);
        } else {
            TetriminoMover.turnRight(currentTetrimino);
            if(CollisionSolver.isInCollision(grid, currentTetrimino))
                TetriminoMover.turnLeft(currentTetrimino);
        }
    }
    
    private void dropTetrimino(){
        if(gameOver){ return; }
        while(!CollisionSolver.isInCollision(grid, currentTetrimino)){
            TetriminoMover.moveDown(currentTetrimino);
        }
        TetriminoMover.moveUp(currentTetrimino);
        addTetriminoToGrid();
    }
    
    // Ajoute le Tetrimino à la grille (devient alors "statique", non contrôlable par l'utilsateur)
    private void addTetriminoToGrid()
    {
        for(vec2 pt : currentTetrimino.getPointList()) {
            if(pt.y < 0){   // Si on essaie de placer un tetrimino en dehors de l'écran
                            // c'est qu'on est arrivé tout en haut de la grille
                endGame();
                return;
            }
            grid[pt.y][pt.x]=1;
        }
        currentTetrimino = tetriminoSequence.pop();
        updateTetriminoSequence();
        removeFullLinesIfAny();
    }
    
    @Override
    public void update() {
        autoMoveDown();
    }
    
    // à chaque update, le tetrimino en cours descend d'une case sous l'effet de la gravité
    private void autoMoveDown(){
        if(gameOver){ return; }
        if(System.currentTimeMillis() - timer < refreshInterval){ return; }

        TetriminoMover.moveDown(currentTetrimino);
    	
    	// Si collision (<=> tétrimino arrive au fond)
    	if(CollisionSolver.isInCollision(grid, currentTetrimino)) {   
            // On annule le mouvement
            TetriminoMover.moveUp(currentTetrimino);
            addTetriminoToGrid();
    	}
        timer = System.currentTimeMillis();
    }
    
    private void removeFullLinesIfAny(){
        ArrayList<Integer> lines = getAllFullLines();
        if(!lines.isEmpty()){
            for(int index : lines){
                shiftDownAt(index);
            }
            LineNotification msg = new LineNotification(this.idJoueur, lines.size());
            MessageBus.getInstance().post(msg).asynchronously();
        }
    }
    
    private ArrayList<Integer> getAllFullLines(){
        ArrayList<Integer> fullLines = new ArrayList<>();
        
        for(int y = 0; y < grid.length; y++){
            boolean full = true;
            for(int x = 0; x < grid[0].length; x++){
                if(grid[y][x] == 0)
                    full = false;
            }
            if(full)
                fullLines.add(y);
        }
        return fullLines;
    }
    
    private void shiftDownAt(int index){
        while(index >= 0){
            copyLineAbove(index);
            index--;
        }
    }
    private void copyLineAbove(int index){
        if( index >= size.y ){ return; }
        for(int x = 0; x < grid[0].length; x++){
            if(index == 0){
                grid[index][x] = 0;
            } else {
                grid[index][x] = grid[index - 1][x];
            }
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
                if(grid[y][x] == 0){
                    output[y] += " ";
                } else {
                    output[y] += "\u2588";
                }
            }
        }
        for(vec2 pt : currentTetrimino.getPointList()){
            if(isWithinGrid(pt)){
                StringBuilder s = new StringBuilder(output[pt.y]);
                s.setCharAt(pt.x, '\u2588');
                output[pt.y] = s.toString();
            }
        }
        return output;
    }
    
    public boolean isWithinGrid(vec2 pos){
        return !(pos.x < 0 || pos.y < 0 || pos.x >= size.x || pos.y >= size.y);
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
    
    private void updateTetriminoSequence(){
        if(gameOver){ return; }
        int sequenceLength = 5;
        while(tetriminoSequence.size() < sequenceLength){ 
            Tetrimino tetrimino = TetriminoBuilder.getRandomTetrimino();
            tetrimino.setPosition(new vec2((int)(size.x/2), 0));
            tetriminoSequence.addLast(tetrimino);
        }
        NextTetriminos msg = new NextTetriminos();
        msg.setSequence(tetriminoSequence.toArray(new Tetrimino[tetriminoSequence.size()]));
        MessageBus.getInstance().post(msg).now();
    }
    
    public Tetrimino[] getTetriminoSequence(){
        return tetriminoSequence.toArray(new Tetrimino[tetriminoSequence.size()]);
    }
    
    public void endGame(){
        gameOver = true;
    }
    
    public void playAgain(){
        gameOver = false;
        clearGrid();
        updateTetriminoSequence();
        currentTetrimino = tetriminoSequence.pop();
        Message msg = new Message().withType("newgame");
        MessageBus.getInstance().post(msg).asynchronously();
    }
    
    public void quitToMainMenu(){
        endGame();
        Message msg = new Message().withType("gameover");
        MessageBus.getInstance().post(msg).now();
        parent.setNextLayout(LayoutBuilder.buildMainMenuLayout());
        parent.setShouldClose(true);
    }
}
