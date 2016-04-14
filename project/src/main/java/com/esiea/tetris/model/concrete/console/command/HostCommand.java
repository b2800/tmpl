package com.esiea.tetris.model.concrete.console.command;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.MultiplayerMessage;
import com.esiea.tetris.communication.concrete.NavigationIntent;
import com.esiea.tetris.model.builder.LayoutBuilder;

public class HostCommand implements Command{

    @Override
    public String execute(String[] args) {
        int port = 4000;
        
        if(args.length > 1){
            port = Integer.parseInt(args[1]);
        }
        
        sendMultiplayerMessage(port);
        sendNavigationMessage();
        
        return "Hosting a game on port " + port;    
    }

    @Override
    public String getName() {
        return "host";
    }
    
    private void sendMultiplayerMessage(int port){
        MultiplayerMessage msg = new MultiplayerMessage();
        msg.setPort(port);
        MessageBus.getInstance().post(msg).asynchronously();
    }
    
    private void sendNavigationMessage(){
        NavigationIntent nav = new NavigationIntent();
        nav.nextLayout = LayoutBuilder.buildMultiplayerLayout(0);
        MessageBus.getInstance().post(nav).asynchronously();
    }
}
