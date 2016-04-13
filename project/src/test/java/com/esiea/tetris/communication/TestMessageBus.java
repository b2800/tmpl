package com.esiea.tetris.communication;

import java.util.ArrayList;
import net.engio.mbassy.bus.publication.SyncAsyncPostCommand;
import net.engio.mbassy.listener.Handler;
import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestMessageBus {

    @Test
    public void itShouldSendMessagesToListener(){
        
        // Given a single listener registered to the MessageBus
        DummyListener A = new DummyListener();
        MessageBus.getInstance().subscribe(A);
        assertEquals(A.getValue(), 0);
        
        // When a message is immediatelly sent over the MessageBus
        sendMessage("TestMessage", 42).now();
        
        // Then the listener should automatically process the message
        assertEquals(A.getValue(), 42);
    }
    
    @Test
    public void itShouldSendMessagesToMultipleListeners(){
        
        // Given multiple listeners registered to the message bus
        ArrayList<DummyListener> listeners = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            DummyListener l = new DummyListener();
            MessageBus.getInstance().subscribe(l);
            listeners.add(l);
        }
        
        // When a message is sent
        sendMessage("TestMessage", 42).now();
        
        // All listeners should have received the message
        for(DummyListener l : listeners){
            assertEquals(l.getValue(), 42);
        }
    }
    
    @Test
    public void itShouldSendMessagesAsynchronously() throws InterruptedException{
        // Given a single listener registered to the MessageBus
        DummyListener A = new DummyListener();
        MessageBus.getInstance().subscribe(A);
        assertEquals(A.getValue(), 0);
        
        // When a message is sent asynchronously over the MessageBus
        sendMessage("TestMessage", 42).asynchronously();
        
        // Then the listener should process the message sometime in the future.
        Thread.sleep(1000);
        assertEquals(A.getValue(), 42);
    }
    
    private SyncAsyncPostCommand sendMessage(String type, int value){
        return MessageBus.getInstance().post(
                new Message().setType(type)
                             .setJson(new JSONObject().put("value", value))
        );
    }
    
    private static class DummyListener{
        private int value = 0;
        
        @Handler
        public void handle(Message msg){
            if("TestMessage".equals(msg.getType())){
                JSONObject obj = msg.getJSON();
                value = obj.getInt("value");
            }
        }
        
        public int getValue(){
            return value;
        }
    }
}
