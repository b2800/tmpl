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
import com.esiea.tetris.model.builder.TetriminoBuilder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import net.engio.mbassy.listener.Handler;

public class PlayableAreaComponent extends Component
                                   implements Drawable {
    
    private TetriminoMover mover;
    private Tetrimino currentTetrimino;
    private int[][] grid;
    private long timer;
    private long refreshInterval;
    private ArrayDeque<Tetrimino> tetriminoSequence;

    public PlayableAreaComponent(vec2 size) {
        mover = new TetriminoMover();
        this.setSize(size);
        grid = new int[size.y][size.x];
        clearGrid();
        refreshInterval = 1000;
        timer = System.currentTimeMillis();
        updateTetriminoSequence();
        currentTetrimino = tetriminoSequence.pop();
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
                mover.moveLeft(currentTetrimino);
                break;
            case RIGHT:
                mover.moveRight(currentTetrimino);
                break;
            case DOWN:
                mover.moveDown(currentTetrimino);
                break;
        }
    }
    
    private void dropTetrimino(){
        mover.moveBottom(currentTetrimino, size.y);
    }
    
    @Override
    public void update() {
        autoMoveDown();
    }
    
    private void autoMoveDown(){
        if(System.currentTimeMillis() - timer > refreshInterval){
            mover.moveDown(currentTetrimino);
            timer = System.currentTimeMillis();
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
                grid[y][x] = ' ';
            }
        }
    }
    
    private void updateTetriminoSequence(){
        int sequenceLength = 5;
        while(tetriminoSequence.size() < sequenceLength){ 
            tetriminoSequence.addLast(TetriminoBuilder.getRandomTetrimino());
        }
    }
    
    public Tetrimino[] getTetriminoSequence(){
        return tetriminoSequence.toArray(new Tetrimino[tetriminoSequence.size()]);
    }
}
