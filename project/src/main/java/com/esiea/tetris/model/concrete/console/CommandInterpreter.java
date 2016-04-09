package com.esiea.tetris.model.concrete.console;

import java.util.ArrayList;

public class CommandInterpreter {

    public CommandInterpreter() {
    }
    
    public String[] execute(String command){
        String output = parseAndRun(command.toLowerCase());
        return linesToArray(output);
    }
    
    private String displayHelp(){
        String output = "--\n"
                + "help : display this message.\n"
                + "start : Start a solo player game\n"
                + "join [ip] : Join a multiplayer game. replace [ip] with the host ip\n"
                + "host [port] : host a multiplayer game, default port is 4000\n"
                + "--\n";
        return output;
    }
    
    private String parseAndRun(String command){
        String[] cmdTokens = command.split("\\s+");
        if(cmdTokens[0].equals("help")){
            return displayHelp();
        }
        return cmdTokens[0] + " : Command not found, type 'help' to display"
                            + " available commands \n";
    }
    
    // Convertit les lignes qui contienent des '\n' en tableau de plusieurs
    // lignes. 
    private String[] linesToArray(String text){
        ArrayList<String> lines = new ArrayList<>();
        
        String current = "";
        for(int i = 0; i < text.length(); i++){
            if(text.charAt(i) == '\n'){
                lines.add(current);
                current = "";
            } else {
                current += text.charAt(i);
            }
        }
        return lines.toArray(new String[lines.size()]);
    }
}
