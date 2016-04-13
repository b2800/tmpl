package com.esiea.tetris.model.concrete.console;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.MultiplayerMessage;
import com.esiea.tetris.communication.concrete.NavigationIntent;
import com.esiea.tetris.model.builder.LayoutBuilder;
import com.esiea.tetris.utils.ScoreUtil;
import com.esiea.tetris.utils.StringUtil;
import java.util.ArrayList;
import java.util.Locale;

public class CommandInterpreter {

    public CommandInterpreter() {
    }
    
    public String[] execute(String command){
        String output = parseAndRun(command);
        return StringUtil.linesToArray(output);
    }
    
    private String displayHelp(){
        String output = "--\n"
                + "help : display this message.\n"
                + "start : Start a solo player game\n"
                + "join [ip] : Join a multiplayer game. replace [ip] with the host ip\n"
                + "host [port] : host a multiplayer game, default port is 4000\n"
                + "scores : Display the 5 best highscores \n"
                + "quit : Quit the game" 
                + "--\n";
        return output;
    }
    
    private String startGame(){
        String output = "Starting a solo game \n";
        NavigationIntent msg = new NavigationIntent();
        msg.nextLayout = LayoutBuilder.buildSoloPlayerLayout(0);
        MessageBus.getInstance().post(msg).asynchronously();
        return output;
    }
    
    private String joinGame(String ip, int port){
        String output = "Trying to join " + ip + "\n";
        MultiplayerMessage multi = new MultiplayerMessage();
        multi.setAddress(ip);
        multi.setPort(port);
        MessageBus.getInstance().post(multi).asynchronously();
        NavigationIntent msg = new NavigationIntent();
        msg.nextLayout = LayoutBuilder.buildMultiplayerLayout(1);
        MessageBus.getInstance().post(msg).asynchronously();
        return output;
    }
    
    private String hostGame(int port){
        MultiplayerMessage msg = new MultiplayerMessage();
        msg.setPort(port);
        MessageBus.getInstance().post(msg).asynchronously();
        NavigationIntent nav = new NavigationIntent();
        nav.nextLayout = LayoutBuilder.buildMultiplayerLayout(0);
        MessageBus.getInstance().post(nav).asynchronously();
        return "Hosting a game";
    }
    
    private String displayHighscores(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("Meilleurs scores : \n");
        for(int score : ScoreUtil.getHighScores()){
            buffer.append(Integer.toString(score) + "\n");
        }
        return buffer.toString();
    }
    
    private String quitGame(){
        String output = "Quitting game\n";
        NavigationIntent msg = new NavigationIntent();
        msg.nextLayout = null;
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
            int port = 4000;
            if(cmdTokens.length >= 3)
                port = Integer.parseInt(cmdTokens[2]);
            return joinGame(cmdTokens[1], port);
        } if(cmdTokens[0].equals("host")){
            int port = 4000;
            if(cmdTokens.length >= 2)
                port = Integer.parseInt(cmdTokens[1]);
            return hostGame(port);
        }if(cmdTokens[0].equals("scores")){
            return displayHighscores();
        } if(cmdTokens[0].equals("quit")){
            return quitGame();
        }
        return cmdTokens[0] + " : Command not found, type 'help' to display"
                            + " available commands \n";
    }
}
