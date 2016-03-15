package Core;

import Communication.Command;
import Communication.Commandable;
import java.util.ArrayList;

public abstract class Service implements Commandable{

    @Override
    public Commandable Update() {
        // Read user input here
        return this;
    }

    @Override
    public Commandable NotifyCommands(ArrayList<Command> cmd) {
        return this;
    }
    
    @Override
    public Commandable GetNextCommands(ArrayList<Command> cmd){
        return this;
    }
}
