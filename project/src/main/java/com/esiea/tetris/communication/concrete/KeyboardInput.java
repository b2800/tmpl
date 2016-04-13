package com.esiea.tetris.communication.concrete;

// Abstraction class for underlying input objects (KeyStroke from lanterna etc)

import com.esiea.tetris.communication.Message;

public class KeyboardInput extends Message{
    Type keyType;
    char character;
    Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Type getKeyType() {
        return keyType;
    }

    public void setKeyType(Type keyCode) {
        this.keyType = keyCode;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
    
    public enum Type{CHARACTER, ARROW_KEY, ENTER, BACKSPACE, DELETE, UNSUPPORTED}
    public enum Direction{UP, DOWN, LEFT, RIGHT, NONE};
}