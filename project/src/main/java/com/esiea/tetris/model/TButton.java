package com.esiea.tetris.model;

public class Button {
    private String text;
    private Runnable callback;
    
    public Button(){
        
    }
    
    public Button Button(String _text){
        this.withText(_text);
        return this;
    }
    
    public Button withText(String _text){
        this.text = _text;
        return this;
    }
    
    public Button withCallback(Runnable _callback){
        this.callback = _callback;
        return this;
    }
}
