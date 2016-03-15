package com.esiea.tetris.Model.Builder;

import com.esiea.tetris.Model.Concrete.MainMenuComponent;
import com.esiea.tetris.Model.Layout;
import com.esiea.tetris.Utils.vec2;

public class LayoutBuilder {

    public static Layout BuildMainMenuLayout() {
        Layout menu = new Layout();
        menu.addComponent( new MainMenuComponent()
                                .withPosition(new vec2(10,10))
                                .withSize(new vec2(100,50))
                         );
        
        return menu;
    }
    
    public static Layout BuildSoloPlayerLayout(){
        
        return null;
    }
    
    public static Layout BuildLocalMultiplayerLayout(int player_count){
        
        return null;
    }
    
    public static Layout BuildNetworkMultiplayerLayout(boolean is_server){
        
        return null;
    }
}
