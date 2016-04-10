package com.esiea.tetris.model.concrete;

import com.esiea.tetris.communication.concrete.NextTetriminos;
import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.model.Component;
import com.esiea.tetris.model.Tetrimino;
import com.esiea.tetris.utils.vec2;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Listener;

@Listener
public class NextTetriminoIndicatorComponent extends Component
                                             implements Drawable{
    
    TPanel panel;
    Tetrimino nextTetrimino;
    int index;

    public NextTetriminoIndicatorComponent() {
        setSize(new vec2(4,4));
        index = 0;
        panel = new TPanel();
        panel.setDrawBorder(true);
        panel.add(this);
        panel.setSize(size);
    }
    
    @Handler
    public void handle(NextTetriminos msg){
        nextTetrimino = msg.getSequence()[index];
        nextTetrimino.setPosition(new vec2(0,0));
    }

    @Override
    public TPanel getDrawableContainer() {
        return panel;
    }

    @Override
    public String[] getDrawableText() {
        char[][] grid = new char[4][4];
        for(int y = 0; y < 4; y++){
            for(int x = 0; x < 4; x++){
                grid[y][x] = ' ';
            }
        }
        String[] output = new String[4];
        for(int i = 0; i < 4; i++){
            output[i] = new String(grid[i]);
        }
        if(nextTetrimino != null){
            for(vec2 pt : nextTetrimino.getPointList()){
                grid[pt.y][pt.x] = '\u2588';
            }
        }
        return output;
    }

    @Override
    public vec2 getDrawableRelativePosition() {
        return new vec2(0,0);
    }
    
    @Override
    public void setPosition(vec2 pos){
        super.setPosition(pos);
        panel.setPosition(pos);
    }
    
    public void setIndex(int i){
        index = i;
    }
}
