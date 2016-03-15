package Core.Concrete;

import Communication.Command;
import Communication.Commandable;
import Core.Service;
import java.util.ArrayList;

public class NetworkServiceV1 extends Service{
    
    private ArrayList<Command> incoming_commands_from_network;
    private ArrayList<Command> commands_from_application;
    
    public NetworkServiceV1(){
        this.incoming_commands_from_network = new ArrayList<Command>();
        this.commands_from_application = new ArrayList<Command>();
    }
    
    @Override
    public Commandable Update(){
        
        return this;
    }
    
    @Override
    public Commandable NotifyCommands(ArrayList<Command> commands_from_application){
        this.commands_from_application.clear();
        this.commands_from_application.addAll(commands_from_application);
        return this;
    }

    @Override
    public Commandable GetNextCommands(ArrayList<Command> cmd) {
        cmd.clear();
        cmd.addAll(incoming_commands_from_network);
        return this;
    }
    
    public void ProcessCommands(){
        
    }
}
