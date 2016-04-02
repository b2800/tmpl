package com.esiea.tetris.model.builder;

import com.esiea.tetris.model.concrete.MainMenuComponent;
import com.esiea.tetris.model.Layout;
import com.esiea.tetris.utils.vec2;

public class LayoutBuilder {

    public static Layout buildMainMenuLayout() {
        Layout menu_layout = new Layout();
        
        MainMenuComponent main_menu = new MainMenuComponent();
        main_menu.setPosition(new vec2(10,10));
        main_menu.setSize(new vec2(100,50));
        
        menu_layout.addComponent(main_menu);
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
