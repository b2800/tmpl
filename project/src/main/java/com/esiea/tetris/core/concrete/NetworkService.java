package com.esiea.tetris.core.concrete;

import com.esiea.tetris.core.Updatable;
import com.esiea.tetris.communication.Message;
import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.GridStateNotification;
import com.esiea.tetris.communication.concrete.PenaltyNotification;

import java.util.ArrayList;

public class NetworkService implements Updatable{

    private MessageDispatcher<GridStateNotification> gridMessages;
    private MessageDispatcher<PenaltyNotification> penaltyMessages;
            
    public NetworkService(){
        
    }
    
    @Override
    public void update(){
        //gridMessages.dispatch();
        //penaltyMessages.dispatch();
    }
    
    public class MessageDispatcher<T>{
        private ArrayList<T> buffer;
        
        public MessageDispatcher(){
            buffer = new ArrayList<>();
        }
        
        public void dispatch(){
            for(T msg : buffer){
                MessageBus.getInstance().post(msg).asynchronously();
            }
            buffer.clear();
        }
    }
}
