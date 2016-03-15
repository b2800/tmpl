package Core;

import Communication.Command;
import Core.Concrete.KeyboardInputService;
import Core.Concrete.NetworkServiceV1;
import Graphics.ConsoleRenderer;
import Graphics.Renderer;
import Model.Builder.LayoutBuilder;
import Model.Layout;
import java.util.ArrayList;

public class Tetris {
    
    private Layout current_layout;
    private Renderer renderer;
    private Service inputService;
    private Service networkService;
    private Boolean application_should_close;
    private ArrayList<Command> input_commands;
    private ArrayList<Command> commands_from_network;
    private ArrayList<Command> commands_to_network;
    
    public Tetris(){
        application_should_close = false;
        current_layout = LayoutBuilder.BuildMainMenuLayout();
        inputService = new KeyboardInputService();
        networkService = new NetworkServiceV1();
        renderer = new ConsoleRenderer();
        
        input_commands = new ArrayList<Command>();
        commands_to_network = new ArrayList<Command>();
        commands_from_network = new ArrayList<Command>();
    }
    
    public void Start(){
        
        while(!application_should_close){
            
            while(!current_layout.shouldClose()){
                
                inputService.Update().GetNextCommands(input_commands);
                networkService.Update().GetNextCommands(commands_from_network);
                current_layout.Update().GetNextCommands(commands_to_network);
                current_layout.Draw(renderer);
                
                networkService.NotifyCommands(commands_to_network);
                current_layout.NotifyCommands(input_commands)
                              .NotifyCommands(commands_from_network);
            }
            
            if(current_layout.Next() == null){
                application_should_close = true;
            }else{
                current_layout = current_layout.Next();
            }
        }
    }

    public static void main(String[] args) {
        Tetris game = new Tetris();
        game.Start();
    }
    
}
