package com.esiea.tetris.model.concrete;

import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.model.builder.LayoutBuilder;
import com.esiea.tetris.model.TButton;
import com.esiea.tetris.model.Component;
import com.esiea.tetris.model.Layout;
import java.util.ArrayList;

/*
    Responsability : Guide the player when launching the application
    Display menu options to start a solo game, start a multiplayer game, 
    check the highscores and quit the game
*/

public class MainMenuComponent extends Component{
    private TButton soloPlayerButton;
    private TButton multiPlayerButton;
    private TButton highscoresButton;
    private TButton exitButton;
            
    public MainMenuComponent(){
        soloPlayerButton = createButton("Partie locale", 
                                LayoutBuilder.buildSoloPlayerLayout());
        multiPlayerButton = createButton("Partie multijoueurs",
                                LayoutBuilder.buildMultiplayerLobby());
        highscoresButton = createButton("Meilleurs scores",
                                LayoutBuilder.buildHighScoresScreen());
        exitButton = createButton("Quitter", null);
    }
    
    // TODO : Move to a GUIBuilder 
    private TButton createButton(String text, Layout nextLayout){
        return new TButton().withText(text)
                           .withCallback( new Runnable(){
            @Override public void run() { 
                proceedToNextLayout(nextLayout);
            }
        });
    }
    
    private void proceedToNextLayout(Layout _nextLayout){
        this.parent.setNextLayout(_nextLayout);
        this.parent.setShouldClose(true);
    }

    @Override
    public void update() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TPanel getDrawableContainer() {
        TPanel drawables = new TPanel();
        
        drawables.add(soloPlayerButton);
        drawables.add(multiPlayerButton);
        drawables.add(highscoresButton);
        drawables.add(exitButton);
        return drawables;
    }
}
