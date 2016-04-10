package com.esiea.tetris.model.concrete.console;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.NavigationIntent;
import com.esiea.tetris.model.builder.LayoutBuilder;
import com.esiea.tetris.utils.StringUtil;
import java.util.ArrayList;

public class CommandInterpreter {

    public CommandInterpreter() {
    }
    
    public String[] execute(String command){
        String output = parseAndRun(command.toLowerCase());
        return StringUtil.linesToArray(output);
    }
    
    private String displayHelp(){
        String output = "--\n"
                + "help : display this message.\n"
                + "start : Start a solo player game\n"
                + "join [ip] : Join a multiplayer game. replace [ip] with the host ip\n"
                + "host [port] : host a multiplayer game, default port is 4000\n"
                + "highscores : Display the 5 best highscores \n"
                + "--\n";
        return output;
    }
    
    private String startGame(){
        String output = "Starting a solo game \n";
        NavigationIntent msg = new NavigationIntent();
        msg.nextLayout = LayoutBuilder.buildSoloPlayerLayout();
        MessageBus.getInstance().post(msg).asynchronously();
        return output;
    }
    
    private String joinGame(String ip){
        String output = "Trying to join " + ip + "\n";
        NavigationIntent msg = new NavigationIntent();
        msg.nextLayout = LayoutBuilder.buildMultiplayerLobby(ip);
        MessageBus.getInstance().post(msg).asynchronously();
        return output;
    }
    
    private String parseAndRun(String command){
        String[] cmdTokens = command.split("\\s+");
        if(cmdTokens[0].equals("help")){
            return displayHelp();
        } if(cmdTokens[0].equals("start")){
            return startGame();
        } if(cmdTokens[0].equals("join")) {
            return joinGame(cmdTokens[1]);
        }
        return cmdTokens[0] + " : Command not found, type 'help' to display"
                            + " available commands \n";
    }
}
