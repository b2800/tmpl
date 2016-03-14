package Graphics;

import Model.Tetrimino;
import Graphics.Exceptions.OutOfBoundsDrawException;
import Model.Layout;
import Utils.vec2;

public class ConsoleRenderer extends Renderer{

    private vec2 size;
    private boolean draw_walls;
    private char[][] grid;
    
    public ConsoleRenderer(){
        size = new vec2(0,0);
        this.setWidth(10);
        this.setHeight(50);
        this.setDrawWalls(true);
    }
    
    public ConsoleRenderer(int _width, int _height, boolean _draw_walls){
        this.setWidth(_width);
        this.setHeight(_height);
        this.setDrawWalls(_draw_walls);
    }
    
    public ConsoleRenderer(vec2 _size, boolean _draw_walls){
        this.setSize(_size);
        this.setDrawWalls(_draw_walls);
    }
    
    private void DrawTetrimino(Tetrimino _block, vec2 _position) 
                throws OutOfBoundsDrawException{
        
    }
    
    private void DrawText(String _text, vec2 _position)
                throws OutOfBoundsDrawException{
        
    }
    
    private void DrawChar(char _c, vec2 _position){
        
    }
    
    @Override
    public void Draw(Layout _layout) throws OutOfBoundsDrawException{
        
    }
            
    // Getters and setters
    public int getWidth() {
        return (int)size.x;
    }

    public final void setWidth(int width) {
        this.size.x = width;
    }

    public int getHeight() {
        return (int)this.size.y;
    }

    public final void setHeight(int height) {
        this.size.y = height;
    }
    
    public vec2 getSize() {
        return size;
    }

    public final void setSize(vec2 size) {
        this.size = size;
    }
    
    public boolean isDrawWalls() {
        return draw_walls;
    }

    public final void setDrawWalls(boolean draw_walls) {
        this.draw_walls = draw_walls;
    }
}