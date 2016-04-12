package com.esiea.tetris.network;

import com.esiea.tetris.core.Updatable;
import com.esiea.tetris.communication.Message;
import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.GridStateNotification;
import com.esiea.tetris.communication.concrete.MultiplayerMessage;
import com.esiea.tetris.communication.concrete.PenaltyNotification;
import java.net.DatagramSocket;
import java.net.SocketException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.engio.mbassy.listener.Handler;

public class NetworkService implements Updatable{
    
    private MessageDispatcher<GridStateNotification> listener;
    private DatagramSocket senderSocket;
    private DatagramSocket receiverSocket;
    private ThreadSafeBuffer receiverBuffer;
    private ThreadSafeBuffer senderBuffer;

    public NetworkService(){

    }
    
    @Handler
    public void handle(MultiplayerMessage msg){
        createSockets(msg.getAddress(), msg.getPort());
    }
    
    private void createSockets(String address, int port){
        try {
            receiverSocket = new DatagramSocket(port);
            senderSocket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(NetworkService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(){

    }
}
