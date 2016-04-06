package com.esiea.tetris.model.builder;

import com.esiea.tetris.model.concrete.MainMenuComponent;
import com.esiea.tetris.model.Layout;
import com.esiea.tetris.model.concrete.console.InteractiveConsoleComponent;
import com.esiea.tetris.utils.vec2;

public class LayoutBuilder {

    public static Layout buildMainMenuLayout() {
        Layout menu_layout = new Layout();
        
        InteractiveConsoleComponent console = new InteractiveConsoleComponent();
        console.setPosition(new vec2(0,0));
        console.setSize(new vec2(50,20));
        
        menu_layout.addComponent(console);
        return menu_layout;
    }
    
    public static Layout buildMultiplayerLobby(){
        
        return null;
    }
    
    public static Layout buildSoloPlayerLayout(){
        
        return null;
    }
    
    public static Layout buildLocalMultiplayerLayout(int player_count){
        
        return null;
    }
    
    public static Layout buildNetworkMultiplayerLayout(boolean is_server){
        
        return null;
    }
    
    public static Layout buildHighScoresScreen(){
        
        return null;
    }
}
