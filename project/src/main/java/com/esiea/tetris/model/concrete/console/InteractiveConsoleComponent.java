package com.esiea.tetris.model.concrete.console;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.model.Component;
import com.esiea.tetris.utils.vec2;
import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.KeyboardInput;
import com.esiea.tetris.communication.concrete.KeyboardInput.Type;
import com.esiea.tetris.communication.concrete.KeyboardInput.Direction;
import com.esiea.tetris.utils.KeyUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import net.engio.mbassy.listener.Handler;

public class InteractiveConsoleComponent extends Component implements Drawable{
    
    private CommandInterpreter interpreter;
    private ArrayDeque<String> consoleOutput;
    private ArrayDeque<String> history;
    private String currentInput;
    private int cursorPosition;
    private int historyPosition;
    private String prompt;

    public InteractiveConsoleComponent() {
        interpreter = new CommandInterpreter();
        consoleOutput = new ArrayDeque<>();
        history = new ArrayDeque<>();
        cursorPosition = 0;
        historyPosition = 0;
        currentInput = "";
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
                removePostCharacter();
                break;
            case DELETE:
                removePreCharacter();
                break;
        }
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
                moveHistoryCursor(-1);
                break;
            case DOWN:
                moveHistoryCursor(1);
                break;
        }
    }
    
    public void insertCharacter(char c){
        currentInput = currentInput.substring(0, cursorPosition) + c + currentInput.substring(cursorPosition);
        cursorPosition++;
    }
    
    public void removePostCharacter(){
        currentInput = currentInput.substring(0, cursorPosition - 1) + currentInput.substring(cursorPosition);
        cursorPosition--;
    }
    
    public void removePreCharacter(){
        currentInput = currentInput.substring(0, cursorPosition) + currentInput.substring(cursorPosition + 1);
        cursorPosition++;
    }
    
    public void movePromptCursor(int offset){
        cursorPosition += offset;
        if(cursorPosition > currentInput.length() + 1 ){
            cursorPosition = currentInput.length() + 1;
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
    }
    
    private void executeCommand(){
        String[] output = interpreter.execute(currentInput);
        for(int i = 0; i < output.length; i++){
            consoleOutput.addFirst(output[i]);
        }
        history.add(currentInput);
        currentInput = "";
        cursorPosition = 0;
    }
    
    @Override
    public void update() {

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
        
        // Add the X last lines into the console output
        consoleOutput.stream().limit(10).forEach( (s) -> {
            output.add(s);
        });
        output.add(getCurrentPrompt());
        return output.toArray(new String[output.size()]);
    }

    @Override
    public vec2 getDrawableRelativePosition() {
        return new vec2(0,0);
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

}
