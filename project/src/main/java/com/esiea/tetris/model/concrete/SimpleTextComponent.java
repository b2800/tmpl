package com.esiea.tetris.model.concrete;

import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.model.Component;
import com.esiea.tetris.utils.StringUtil;
import com.esiea.tetris.utils.vec2;
import java.util.ArrayList;

public class SimpleTextComponent extends Component
                                 implements Drawable{
    ArrayList<String> text;

    public SimpleTextComponent() {
        text = new ArrayList<>();
        setSize(new vec2(0,0));
    }
    
    public void addLine(String input){
        String[] lines = StringUtil.linesToArray(input);
        for(String l : lines){
            text.add(l);
        }
        updateSize();
    }

    private void updateSize(){
        int largest = 0;
        for(String line : text){
            if(line.length() > largest)
                largest = line.length();
        }
        setSize(new vec2(largest, text.size()));
    }
    
    @Override
    public TPanel getDrawableContainer() {
        TPanel panel = new TPanel();
        panel.setDrawBorder(true);
        panel.setSize(size);
        panel.setPosition(position);
        panel.add(this);
        return panel;
    }

    @Override
    public String[] getDrawableText() {
        return text.toArray(new String[text.size()]);
    }

    @Override
    public vec2 getDrawableRelativePosition() {
        return new vec2(0,0);
    }

    @Override
    public int[][] getColorMap() {
        return null;
    }
}
