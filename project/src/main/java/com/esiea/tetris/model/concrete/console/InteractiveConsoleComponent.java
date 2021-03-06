package com.esiea.tetris.model.concrete.console;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.model.Component;
import com.esiea.tetris.utils.Vec2;
import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.KeyboardInput;
import com.esiea.tetris.communication.concrete.KeyboardInput.Type;
import com.esiea.tetris.communication.concrete.KeyboardInput.Direction;
import com.esiea.tetris.communication.concrete.NavigationIntent;
import com.esiea.tetris.model.concrete.console.command.HelpCommand;
import com.esiea.tetris.model.concrete.console.command.HighScoresCommand;
import com.esiea.tetris.model.concrete.console.command.HostCommand;
import com.esiea.tetris.model.concrete.console.command.JoinCommand;
import com.esiea.tetris.model.concrete.console.command.QuitCommand;
import com.esiea.tetris.model.concrete.console.command.StartCommand;
import com.esiea.tetris.utils.KeyUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import net.engio.mbassy.listener.Handler;

public class InteractiveConsoleComponent extends Component 
                                         implements Drawable{
    
    private CommandInterpreter interpreter;
    private ArrayDeque<String> consoleOutput;
    private ArrayDeque<String> history;
    private String currentInput;
    private int cursorPosition;
    private int historyPosition;

    public InteractiveConsoleComponent() {
        initializeCommandInterpreter();
        consoleOutput = new ArrayDeque<>();
        history = new ArrayDeque<>();
        cursorPosition = 0;
        historyPosition = 0;
        currentInput = "";
        setSize(new Vec2(50,10));
        MessageBus.getInstance().subscribe(this);
    }
	
    @Handler
    public void handle(KeyboardInput msg){
        switch(msg.getKeyType()){
            case ARROW_KEY:
                handleMove(msg.getDirection());
                break;
            case CHARACTER:
                insertCharacter(msg.getCharacter());
                break;
            case ENTER:
                executeCommand();
                break;
            case BACKSPACE:
                removePreCharacter();
                break;
            case DELETE:
                removePostCharacter();
                break;
            default:
        }
    }
    
    @Handler
    public void handle(NavigationIntent msg){
        if(parent == null){ return; } 
        parent.setNextLayout(msg.nextLayout);
        parent.setShouldClose(true);
    }
    
    public void handleMove(Direction d){
        switch(d){
            case LEFT:
                movePromptCursor(-1);
                break;
            case RIGHT:
                movePromptCursor(1);
                break;
            case UP:
                moveHistoryCursor(1);
                break;
            case DOWN:
                moveHistoryCursor(-1);
                break;
            case NONE:
            default:
                break;
        }
    }
    
    public void insertCharacter(char c){
        currentInput = currentInput.substring(0, cursorPosition) + c + currentInput.substring(cursorPosition);
        cursorPosition++;
    }
    
    public void removePostCharacter(){
        currentInput = currentInput.substring(0, cursorPosition) + currentInput.substring(cursorPosition + 1);
    }
    
    public void removePreCharacter(){
        currentInput = currentInput.substring(0, cursorPosition - 1) + currentInput.substring(cursorPosition);
        cursorPosition--;
    }
    
    public void movePromptCursor(int offset){
        cursorPosition += offset;
        if(cursorPosition >= currentInput.length()){
            cursorPosition = currentInput.length();
        } else if(cursorPosition < 0){
            cursorPosition = 0;
        }
    }
    
    private void moveHistoryCursor(int offset) {
        historyPosition += offset;
        if(historyPosition < 0){
            historyPosition = 0;
        } else if(historyPosition >= history.size()){
            historyPosition = history.size()-1;
        }
        currentInput = history.toArray(new String[history.size()])[historyPosition];
        cursorPosition = currentInput.length();
    }
    
    private void executeCommand(){
        String[] output = interpreter.process(currentInput);
        for(int i = 0; i < output.length; i++){
            consoleOutput.addFirst(output[i]);
        }
        history.addFirst(currentInput);
        currentInput = "";
        cursorPosition = 0;
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
        output.add(getCurrentPrompt());
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
    
    private String getCurrentPrompt(){
        String cursor = "|";
        if((System.currentTimeMillis()/1000)%2 == 0){   // Make it blink!
            cursor = ".";
        }
        String currentInputWithCursor;
        currentInputWithCursor = currentInput.substring(0, cursorPosition) + 
                                 cursor +
                                 currentInput.substring(cursorPosition);
        return "> " + currentInputWithCursor;
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
