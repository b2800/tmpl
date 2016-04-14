package com.esiea.tetris.model.concrete.console.prompt;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.JSONMessage;
import com.esiea.tetris.communication.concrete.KeyboardInput;
import com.esiea.tetris.communication.concrete.KeyboardInput.Direction;
import com.esiea.tetris.utils.StringUtil;
import net.engio.mbassy.listener.Handler;
import org.json.JSONObject;

public class Prompt {
    
    private Cursor cursor;
    private StringBuffer currentInput;
    private CommandHistory commandHistory;
    
    public Prompt(){
        currentInput = new StringBuffer();
        cursor = new Cursor(currentInput);
        commandHistory = new CommandHistory();
        MessageBus.getInstance().subscribe(this);
    }
    
    @Handler
    public void handle(KeyboardInput msg){
        switch(msg.getKeyType()){
            case CHARACTER:
                insertCharacter(msg.getCharacter());
                break;
            case ENTER:
                requestCommandExecution();
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
    
    private void requestCommandExecution(){
        JSONMessage msg = new JSONMessage();
        msg.setType("executeCommand");
        msg.setJson(new JSONObject().put("command", currentInput.toString()));
        MessageBus.getInstance().post(msg).now();
        clearPrompt();
    }
        
    private void insertCharacter(char c){
        String input = currentInput.toString();
        currentInput.setLength(0);
        currentInput.append(StringUtil.insertChartAt(input, c, cursor.getPosition()));
        cursor.move(Direction.RIGHT);
    }
    
    private void removePostCharacter(){
        String input = currentInput.toString();
        currentInput.setLength(0);
        currentInput.append(StringUtil.removeCharAt(input, cursor.getPosition()));
    }
    
    private void removePreCharacter(){
        String input = currentInput.toString();
        currentInput.setLength(0);
        currentInput.append(StringUtil.removeCharAt(input, cursor.getPosition() - 1));
        cursor.move(Direction.LEFT);
    }
    
    private void clearPrompt(){
        commandHistory.add(currentInput.toString());
        currentInput.setLength(0);
        cursor.setPosition(0);
    }
        
    public String getCurrentVisual(){
        char cc;    // Make it blink ! 
        cc = (System.currentTimeMillis()/1000)%2 == 0 ? '|' : '.';
        String inputPlusCursor;
        inputPlusCursor = StringUtil.insertChartAt(currentInput.toString(), cc, cursor.getPosition());
        return "> " + inputPlusCursor;
    }
}
