package com.esiea.tetris.Communication;

import java.util.ArrayList;

/*
    Message Bus
    Responsability : Provide access to anyone to all messages exchanged during 
    the current frame.
    Must be reset at the beginning or at the end of the frame
*/

public class MessageBus {
    private static ArrayList<Message> commands = new ArrayList<>();
    
    public static void push(Message cmd){
        commands.add(cmd);
    }
    
    public static ArrayList<Message> pull(){
        return commands;
    }
    
    public static void clear(){
        commands.clear();
    }
}
