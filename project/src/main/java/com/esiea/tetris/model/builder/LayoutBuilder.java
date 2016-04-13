package com.esiea.tetris.model.builder;

import com.esiea.tetris.model.Layout;
import com.esiea.tetris.model.concrete.NextTetriminoIndicatorComponent;
import com.esiea.tetris.model.concrete.PenaltyComponent;
import com.esiea.tetris.model.concrete.PlayableAreaComponent;
import com.esiea.tetris.model.concrete.ScoreComponent;
import com.esiea.tetris.model.concrete.SimpleTextComponent;
import com.esiea.tetris.model.concrete.console.InteractiveConsoleComponent;
import com.esiea.tetris.utils.Context;
import com.esiea.tetris.utils.ScoreUtil;
import com.esiea.tetris.utils.Vec2;

public class LayoutBuilder {

    public static Layout buildMainMenuLayout() {
        Layout menu_layout = new Layout();
        
        InteractiveConsoleComponent console = new InteractiveConsoleComponent();
        console.setPosition(new Vec2(0,0));
        console.setSize(Context.getWindowSize());
        
        menu_layout.addComponent(console);
        return menu_layout;
    }
    
    public static Layout buildMultiplayerLobby(String ip){
        
        return null;
    }
    
    public static Layout buildSoloPlayerLayout(int id){
        Layout gameLayout = new Layout();
        PenaltyComponent pc = new PenaltyComponent(id);
        ScoreComponent score = new ScoreComponent(id, 0);
        SimpleTextComponent text = new SimpleTextComponent();
        NextTetriminoIndicatorComponent next = new NextTetriminoIndicatorComponent(0);
        NextTetriminoIndicatorComponent next2 = new NextTetriminoIndicatorComponent(1);
        NextTetriminoIndicatorComponent next3 = new NextTetriminoIndicatorComponent(2);
        PlayableAreaComponent playZone = new PlayableAreaComponent(new Vec2(10, 20), id, false);

        
        playZone.setPosition(new Vec2(2,2));
        score.setPosition(new Vec2(14, 2));
        pc.setPosition(new Vec2(14, 5));
        next.setPosition(new Vec2(14, 9));
        next2.setPosition(new Vec2(19, 9));
        next3.setPosition(new Vec2(24, 9));
        text.setPosition(new Vec2(14, 15));
        
        score.setSize(new Vec2(6, 1));
        pc.setSize(new Vec2(14, 2));
        
        text.addLine("Arrow keys to move");
        text.addLine("Press v/b to rotate");
        text.addLine("Press space to drop");
        text.addLine("Press q to quit");
        text.addLine("Press r to play again");
        
        gameLayout.addComponent(playZone);
        gameLayout.addComponent(score);
        gameLayout.addComponent(pc);
        gameLayout.addComponent(next);
        gameLayout.addComponent(next2);
        gameLayout.addComponent(next3);
        gameLayout.addComponent(text);
        return gameLayout;
    }
    
    public static Layout buildMultiplayerLayout(int id_joueur){
        int id_opponent = (id_joueur+1)%2;
        Layout mlayout = buildSoloPlayerLayout(id_joueur);
        
        PlayableAreaComponent zone2 = new PlayableAreaComponent(new Vec2(10,20), id_opponent, true);
        zone2.setPosition(new Vec2(40, 2));
        mlayout.addComponent(zone2);
        
        return mlayout;
    }
}
