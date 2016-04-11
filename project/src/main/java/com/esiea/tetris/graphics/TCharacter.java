package com.esiea.tetris.graphics;

public class TCharacter {
    private char character;
    private int[] color;

    public TCharacter(){
        color = new int[]{255,255,255};
    }
    
    public TCharacter(char c){
        this();
        setCharacter(c);
    }
    
    public TCharacter(char c, int[] color){
        this(c);
        setColor(color);
    }
    
    public char getCharacter() {
        return character;
    }

    public final void setCharacter(char character) {
        this.character = character;
    }

    public int[] getColor() {
        return color;
    }

    public final void setColor(Integer[] color) {
        this.color[0] = color[0];
        this.color[1] = color[1];
        this.color[2] = color[2];
    }
    
    public final void setColor(int[] color){
        this.color = color;
    }
    
    @Override
    public String toString(){
        return String.valueOf(character);
    }
}
