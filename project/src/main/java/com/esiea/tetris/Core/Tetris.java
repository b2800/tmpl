package com.esiea.tetris.Core;

import com.esiea.tetris.Core.Concrete.TerminalKeyboardInputService;
import com.esiea.tetris.Core.Concrete.NetworkServiceV1;
import com.esiea.tetris.Graphics.Concrete.ConsoleRenderer;
import com.esiea.tetris.Graphics.Renderer;
import com.esiea.tetris.Model.Builder.LayoutBuilder;
import com.esiea.tetris.Model.Layout;

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
        inputService = new TerminalKeyboardInputService();
        networkService = new NetworkServiceV1();
        renderer = new ConsoleRenderer();
    }
    
    public void Start(){
        while(!application_should_close){   // Boucle principale du jeu 
            
            while(!current_layout.shouldClose()){   
                inputService.update();
                networkService.update();
                current_layout.update();
                current_layout.draw(renderer);
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
