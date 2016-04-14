package com.esiea.tetris.model.concrete.console;

import com.esiea.tetris.common.InputHelper;
import com.esiea.tetris.communication.concrete.KeyboardInput;
import com.esiea.tetris.communication.concrete.KeyboardInput.Direction;
import com.esiea.tetris.communication.concrete.KeyboardInput.Type;
import com.esiea.tetris.communication.concrete.NavigationIntent;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.utils.Vec2;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InteractiveConsoleComponentTest {
    
    private InteractiveConsoleComponent shell;
    
    public InteractiveConsoleComponentTest() {}

    
    @Before
    public void setUp() {
        shell = new InteractiveConsoleComponent();
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void itShouldPrintUserInput() {
        // Given an new empty shell
        
        // When the user type a message
        InputHelper.sendString("hello", true);
        
        // Then it should update the output accordingly
        String[] console_output = shell.getDrawableText();
        
        assertTrue(console_output.length == 1);
        assertTrue(console_output[0].contains("hello"));
        // Input will look like "> hello|"
    }
    
    @Test
    public void itShouldMoveCursorPrompt(){
        // Given an initial input in the prompt
        InputHelper.sendString("start", true);
        String output = shell.getDrawableText()[0]; // Garanteed to always exists

        // Cursor should be at the end
        // Cursor is either a pipe character '|' or a dot '.' 
        assertTrue(output.contains("start|") || output.contains("start."));
        
        // When the user press the left key
        InputHelper.sendArrowKey(Direction.LEFT).now();
        output = shell.getDrawableText()[0];
        
        // Then it should moves the cursor one character to the left
        assertTrue(output.contains("star|t") || output.contains("star.t"));
        
        // When the user press the right key
        InputHelper.sendArrowKey(Direction.RIGHT).now();
        output = shell.getDrawableText()[0];
        
        // Then it should moves the cursor one character to the right
        assertTrue(output.contains("start|") || output.contains("start."));
    }

    @Test
    public void itShouldEraseACharacter(){

        // When the user type something and press the backspace key
        InputHelper.sendString("start", true);
        InputHelper.sendSpecialCharacter(Type.BACKSPACE).now();
        
        // Then it should removes the last character typed (here : 't')
        String[] console_output = shell.getDrawableText();
        
        assertTrue(console_output.length == 1);
        assertTrue(console_output[0].contains("star")); // Should have removed the 't' 
        assertFalse(console_output[0].contains("start")); // Should not contain 'start'
    }
    
    @Test
    public void itShouldDeletePostCharacter() {
        // When the user type something, moves two times the cursor to the left 
        // and press the delete key
        InputHelper.sendString("start", true);
        InputHelper.sendArrowKey(Direction.LEFT).now();
        InputHelper.sendArrowKey(Direction.LEFT).now();
        InputHelper.sendSpecialCharacter(Type.DELETE).now();
        
        // Then it should have delete the 'r' character
        String output = shell.getDrawableText()[0];
        assertTrue(output.contains("sta|t") || output.contains("sta.t"));
    }
    
    @Test
    public void itShouldDisplayErrorMessageUponInvalidCommand(){
        // When the user type a invalid command and press Enter key
        InputHelper.sendString("fakeCommand");
        InputHelper.sendSpecialCharacter(Type.ENTER).now();
        
        // Then it should display an error message
        String output = shell.getDrawableText()[0];
        assertTrue(output.contains("Command not found"));
    }
    
    @Test
    public void itShouldExecuteACommand(){
        // When the user type a valid command and press the enter key
        InputHelper.sendString("help", true);
        InputHelper.sendSpecialCharacter(Type.ENTER).now();
        
        // Then it should display the command output
        String[] outputLines = shell.getDrawableText();
        assertTrue(outputLines.length > 2);
        assertTrue(outputLines[1].contains("help : display this message"));
    }
    
    @Test
    public void itShouldMoveThroughHistory(){
        // Given a state where the user typed commands
        InputHelper.sendString("cmd1", true);
        InputHelper.sendSpecialCharacter(Type.ENTER);
        InputHelper.sendString("cmd2", true);
        InputHelper.sendSpecialCharacter(Type.ENTER);
        
        // When the user press the up arrow key
        InputHelper.sendArrowKey(Direction.UP);
        
        // Then the prompt should change to the last used command
        String[] output = shell.getDrawableText();
        String prompt = output[output.length-1];
        
        assertTrue(prompt.contains("cmd2"));
        
        // When the user press the up key again
        InputHelper.sendArrowKey(Direction.UP);
        
        // Then it should move the the previous command again
        output = shell.getDrawableText();
        prompt = output[output.length-1];
        assertTrue(prompt.contains("cmd1"));
    }
    
}
