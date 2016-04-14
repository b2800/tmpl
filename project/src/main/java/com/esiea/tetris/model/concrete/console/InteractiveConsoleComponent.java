package com.esiea.tetris.model.concrete.console;

import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.model.Component;
import com.esiea.tetris.utils.Vec2;
import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.JSONMessage;
import com.esiea.tetris.model.concrete.console.command.HelpCommand;
import com.esiea.tetris.model.concrete.console.command.HighScoresCommand;
import com.esiea.tetris.model.concrete.console.command.HostCommand;
import com.esiea.tetris.model.concrete.console.command.JoinCommand;
import com.esiea.tetris.model.concrete.console.command.QuitCommand;
import com.esiea.tetris.model.concrete.console.command.StartCommand;
import com.esiea.tetris.model.concrete.console.prompt.Prompt;
import java.util.ArrayDeque;
import java.util.ArrayList;
import net.engio.mbassy.listener.Handler;
import org.json.JSONException;

public class InteractiveConsoleComponent extends Component 
                                         implements Drawable{
    
    private CommandInterpreter interpreter;
    private Prompt prompt;
    private ArrayDeque<String> consoleOutput;

    public InteractiveConsoleComponent() {
        initializeCommandInterpreter();
        prompt = new Prompt();
        consoleOutput = new ArrayDeque<>();
        setSize(new Vec2(50,10));
        MessageBus.getInstance().subscribe(this);
    }

    @Handler
    public void handle(JSONMessage msg) throws JSONException{
        if(msg.getType().equals("executeCommand")){
            String cmd = msg.getJSON().getString("command");
            executeCommand(cmd);
        }
    }
    
    private void executeCommand(String cmd){
        String[] output = interpreter.process(cmd);
        for(String line : output){
            consoleOutput.addFirst(line);
            System.out.println(line);
        }
    }

    @Override
    public TPanel getDrawableContainer() {
        TPanel panel = new TPanel();
        panel.setPosition(this.position);
        panel.setSize(this.size);
        panel.add(this);
        return panel; 
    }

    @Override
    public String[] getDrawableText() {
        ArrayList<String> output = new ArrayList<>();
        output.addAll(getLastConsoleOutput(size.y-1));
        output.add(prompt.getCurrentVisual());
        return output.toArray(new String[output.size()]);
    }

    @Override
    public Vec2 getDrawableRelativePosition() {
        return new Vec2(0,0);
    }
    
    private ArrayList<String> getLastConsoleOutput(int count){
        ArrayList<String> output = new ArrayList<>();
        // Get the last "count" lines
        consoleOutput.stream().limit(count).forEach( (s) -> {
            output.add(s);
        });
        // Reverse order 
        ArrayList<String> output_reversed = new ArrayList<>(output);
        for(int i = 0; i < output.size(); i++){
            output_reversed.set(i, output.get(output.size()-1-i));
        }
        return output_reversed;
    }
    
    @Override
    public int[][] getColorMap() {
        return null;
    }

    /*
        Permet d'indiquer a l'interpreteur de commandes les differentes
        actions disponibles.
    */
    private void initializeCommandInterpreter() {
        interpreter = new CommandInterpreter();
        interpreter.registerNewCommand(new HelpCommand());
        interpreter.registerNewCommand(new HighScoresCommand());
        interpreter.registerNewCommand(new HostCommand());
        interpreter.registerNewCommand(new JoinCommand());
        interpreter.registerNewCommand(new StartCommand());
        interpreter.registerNewCommand(new QuitCommand());
    }
}
