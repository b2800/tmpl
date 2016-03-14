package Communication;

import java.util.ArrayList;

/*
    Décrit les classes qui peuvent recevoir et emmetre des ordres (Command)
*/
public interface Commandable {
    public Commandable Update();
    public Commandable NotifyCommands(ArrayList<Command> cmd);
    public Commandable GetNextCommands(ArrayList<Command> cmd);
    
}
