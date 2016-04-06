package com.esiea.tetris.model;

import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.utils.vec2;

public class TButton implements Drawable{
    private String text;
    private Runnable callback;
    
    public TButton(){
        
    }
    
    public TButton Button(String _text){
        this.withText(_text);
        return this;
    }
    
    public TButton withText(String _text){
        this.text = _text;
        return this;
    }
    
    public TButton withCallback(Runnable _callback){
        this.callback = _callback;
        return this;
    }
    
    public Runnable getCallback(){
        return callback;
    }

    @Override
    public String[] getDrawableText() {
        String[] t = {text};
        return t;
    }

    @Override
    public vec2 getDrawableRelativePosition() {
        return new vec2(0,0);
    }
}
