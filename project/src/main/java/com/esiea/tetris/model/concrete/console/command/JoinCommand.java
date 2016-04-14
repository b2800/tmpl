package com.esiea.tetris.model.concrete.console.command;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.MultiplayerMessage;
import com.esiea.tetris.communication.concrete.NavigationIntent;
import com.esiea.tetris.model.builder.LayoutBuilder;

public class JoinCommand implements Command{

    @Override
    public String execute(String[] args) {
        String ip = null;
        int port = 4000;
        
        if(args.length >= 2){
            ip = args[1];
        }
        if(args.length >= 3){
            port = Integer.parseInt(args[2]);
        }
        sendMultiplayerMessage(ip, port);
        sendNavigationMessage();
        
        return "Trying to join " + ip + ":" + port + "\n";
    }

    @Override
    public String getName() {
        return "join";
    }
    
    private void sendMultiplayerMessage(String ip, int port){
        MultiplayerMessage multi = new MultiplayerMessage();
        multi.setAddress(ip);
        multi.setPort(port);
        MessageBus.getInstance().post(multi).asynchronously();
    }
    
    private void sendNavigationMessage(){
        NavigationIntent msg = new NavigationIntent();
        msg.nextLayout = LayoutBuilder.buildMultiplayerLayout(1);
        MessageBus.getInstance().post(msg).asynchronously();
    }
}
