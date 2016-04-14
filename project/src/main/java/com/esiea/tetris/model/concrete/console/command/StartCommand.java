package com.esiea.tetris.model.concrete.console.command;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.NavigationIntent;
import com.esiea.tetris.model.builder.LayoutBuilder;

public class StartCommand implements Command{

    @Override
    public String execute(String[] args) {
        String output = "Starting a solo game \n";
        NavigationIntent msg = new NavigationIntent();
        msg.nextLayout = LayoutBuilder.buildSoloPlayerLayout(0);
        MessageBus.getInstance().post(msg).asynchronously();
        return output;
    }

    @Override
    public String getName() {
        return "start";
    }
}
