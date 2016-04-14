package com.esiea.tetris.model.concrete.console;

import com.esiea.tetris.model.concrete.console.command.Command;
import com.esiea.tetris.utils.StringUtil;
import java.util.ArrayList;


public class CommandInterpreter {
    
    private ArrayList<Command> commands;

    public CommandInterpreter() {
        commands = new ArrayList<>();
    }

    public String[] process(String command){
        String output = parseAndRun(command);
        return StringUtil.linesToArray(output);
    }
    
    private String parseAndRun(String command){
        String[] args = command.split("\\s+");
        
        for(Command cmd : this.commands){
            if(args[0].equals(cmd.getName())){
                return cmd.execute(args);
            }
        }
        return args[0] + " : Command not found, type 'help' to display"
                            + " available commands \n";
    }
     
    /*
        First, we check if the command does not conflict with another
        command already registered
        If no conflicts found, we register the command
    */
    public void registerNewCommand(Command newCommand){
        String newCommandName = newCommand.getName();
        for(Command cmd : this.commands){
            if(cmd.getName().equals(newCommandName)){
                System.out.println("The new command conflicts with another one");
                return;
            }
        }
        commands.add(newCommand);
    }
}
