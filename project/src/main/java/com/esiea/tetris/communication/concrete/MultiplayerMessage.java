package com.esiea.tetris.communication.concrete;

import com.esiea.tetris.communication.Message;

public class MultiplayerMessage extends Message{
    public enum Type{ CREATE, JOIN };
    
    private Type type;
    private int port;
    private String address;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
