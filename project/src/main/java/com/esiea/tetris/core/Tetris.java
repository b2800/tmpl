package com.esiea.tetris.core;

import com.esiea.tetris.core.concrete.InputService;
import com.esiea.tetris.core.concrete.NetworkService;
import com.esiea.tetris.graphics.concrete.ConsoleRenderer;
import com.esiea.tetris.graphics.Renderer;
import com.esiea.tetris.model.builder.LayoutBuilder;
import com.esiea.tetris.model.Layout;

/* Main class
    Responsability : Update the instanciated services until the game ends.
*/

public class Tetris {
    
    private Layout current_layout;
    private final Renderer renderer;
    private final Updatable inputService;
    private final Updatable networkService;
    private Boolean application_should_close;
    
    public Tetris(){
        application_should_close = false;
        current_layout = LayoutBuilder.buildMainMenuLayout();
        networkService = new NetworkService();
        renderer = new ConsoleRenderer();
        inputService = new InputService(((ConsoleRenderer)renderer).getTerminal());
    }
    
    public void Start(){
        while(!application_should_close){   // Boucle principale du jeu 
            
            while(!current_layout.shouldClose()){   
                inputService.update();
                networkService.update();
                current_layout.update();
                renderer.process(current_layout);
            }
            
            if(current_layout.next() == null){
                application_should_close = true;
            }else{
                current_layout = current_layout.next();
            }
        }
    }

    public static void main(String[] args) {
        Tetris game = new Tetris();
        game.Start();
    }
}
