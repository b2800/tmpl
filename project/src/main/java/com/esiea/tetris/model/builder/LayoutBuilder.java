package com.esiea.tetris.model.builder;

import com.esiea.tetris.model.Layout;
import com.esiea.tetris.model.concrete.PenaltyComponent;
import com.esiea.tetris.model.concrete.PlayableAreaComponent;
import com.esiea.tetris.model.concrete.ScoreComponent;
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
    
    public static Layout buildMultiplayerLobby(String ip){
        
        return null;
    }
    
    public static Layout buildSoloPlayerLayout(){
        Layout gameLayout = new Layout();
        PlayableAreaComponent playZone = new PlayableAreaComponent(new vec2(10, 20));
        PenaltyComponent pc = new PenaltyComponent();
        ScoreComponent score = new ScoreComponent(0, 0);
        
        playZone.setPosition(new vec2(2,2));
        score.setPosition(new vec2(14, 2));
        pc.setPosition(new vec2(14, 5));
        
        gameLayout.addComponent(playZone);
        gameLayout.addComponent(score);
        gameLayout.addComponent(pc);
        return gameLayout;
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
