package com.esiea.tetris.communication;

import org.json.JSONObject;

public class Message {
    private String type;    // Example : MoveLeft, MoveRight, SendMalus etc... 
    private JSONObject json;
    
    public Message setType(String _type){
        this.type = _type;
        return this;
    }
    
    public Message setJson(JSONObject _json){
        this.json = _json;
        return this;
    }
    
    public String getType(){
        return this.type;
    }
    
    public JSONObject getJSON(){
        return json;
    }
}
