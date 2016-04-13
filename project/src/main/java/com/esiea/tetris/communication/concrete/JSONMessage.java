package com.esiea.tetris.communication.concrete;

import com.esiea.tetris.communication.Message;
import org.json.JSONObject;

public class JSONMessage extends Message {
    private String type;    // Example : MoveLeft, MoveRight, SendMalus etc... 
    private JSONObject json;
    
    public JSONMessage setType(String _type){
        this.type = _type;
        return this;
    }
    
    public JSONMessage setJson(JSONObject _json){
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
