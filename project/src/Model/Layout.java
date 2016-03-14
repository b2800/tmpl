package Model;

import Communication.Command;
import Communication.Commandable;
import Graphics.Exceptions.OutOfBoundsDrawException;
import Graphics.Renderer;
import Utils.vec2;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* A layout defines :
    + The grid's dimensions
    + A set of components
    + The next layout that will replace this layout when it will close,
        or nothing if it is the last layout
    + An Update methods that return commands for the rest of the application
*/

public class Layout implements Commandable{
    private vec2 size;
    private ArrayList<Component> components;
    private boolean should_close;
    private Layout next_layout;
    
    public Layout(){
        this(12, 30);
        components = new ArrayList<Component>();
    }
    
    public Layout(vec2 _size){
        this.size = _size;
    }
    
    public Layout(int _width, int _height){
        size = new vec2(_width, _height);
    }
    
    public void AddComponent(Component _c){
        this.components.add(_c);
    }
    
    @Override
    public Commandable Update(){
        this.components.stream().forEach((c) -> {
            c.Update();
        });
        return this;
    }
    
    @Override
    public Commandable NotifyCommands(ArrayList<Command> commands){
        
        return this;
    }
    
    @Override
    public Commandable GetNextCommands(ArrayList<Command> commands){
        
        return this;
    }
    
    public void Draw(Renderer renderer){
        try{
            renderer.Draw(this);
        } catch(OutOfBoundsDrawException e){
            System.out.println("Tried to draw something outside the map");
        } catch (Exception ex) {
            Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean shouldClose(){
        return this.should_close;
    }
    
    public Layout Next(){
        return this.next_layout;
    }
}
