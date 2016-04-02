package com.esiea.tetris.model.concrete;

import com.esiea.tetris.model.builder.LayoutBuilder;
import com.esiea.tetris.model.Button;
import com.esiea.tetris.model.Component;
import com.esiea.tetris.model.Layout;

/*
    Responsability : Guide the player when launching the application
    Display menu options to start a solo game, start a multiplayer game, 
    check the highscores and quit the game
*/

public class MainMenuComponent extends Component{
    private Button soloPlayerButton;
    private Button multiPlayerButton;
    private Button highscoresButton;
    private Button exitButton;
            
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
    private Button createButton(String text, Layout nextLayout){
        return new Button().withText(text)
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
}
