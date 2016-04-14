package com.esiea.tetris.model.concrete.console.command;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.NavigationIntent;

public class QuitCommand implements Command{

    @Override
    public String execute(String[] args) {
        NavigationIntent msg = new NavigationIntent();
        msg.nextLayout = null;
        MessageBus.getInstance().post(msg).asynchronously();
        return "Quitting game\n";
    }

    @Override
    public String getName() {
        return "quit";
    }
    
}
