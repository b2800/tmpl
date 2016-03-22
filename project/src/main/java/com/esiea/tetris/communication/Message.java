package com.esiea.tetris.communication;

import org.json.JSONObject;

public class Message {
    private String type;    // Example : MoveLeft, MoveRight, SendMalus etc... 
    private JSONObject json;
    
    public Message Message(){
        return this;
    }
    
    public Message withType(String _type){
        this.type = _type;
        return this;
    }
    
    public Message withJson(JSONObject _json){
        this.json = _json;
        return this;
    }
    
    public String getType(){
        return this.type;
    }
}
