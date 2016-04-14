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
        
        System.out.println(output);
        // Then it should moves the cursor one character to the left
        assertTrue(output.contains("star|t") || output.contains("star.t"));
        
        // When the user press the right key
        InputHelper.sendArrowKey(Direction.RIGHT).now();
        
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
    
}
