package com.esiea.tetris.model.concrete.console.command;

public class HelpCommand implements Command{
    
    @Override
    public String execute(String[] args){
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
    
    @Override
    public String getName(){
        return "help";
    }
}
