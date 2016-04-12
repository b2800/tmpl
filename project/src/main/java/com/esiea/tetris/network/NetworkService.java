package com.esiea.tetris.network;

import com.esiea.tetris.core.Updatable;
import com.esiea.tetris.communication.Message;
import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.GridStateNotification;
import com.esiea.tetris.communication.concrete.MultiplayerMessage;
import com.esiea.tetris.communication.concrete.PenaltyNotification;
import com.esiea.tetris.utils.ByteUtil;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.engio.mbassy.listener.Handler;

public class NetworkService implements Updatable{
    
    private DatagramSocket senderSocket;
    private DatagramSocket receiverSocket;
    private InetAddress address;
    private int localPort;
    private int remotePort;
    private ThreadSafeBuffer buffer = new ThreadSafeBuffer();

    public NetworkService(){

    }
    
    @Handler
    public void handle(MultiplayerMessage msg){
        createSockets(msg.getAddress(), msg.getPort());
    }
    
    @Handler 
    public void handle(GridStateNotification msg){
        sendMessage(msg);
    }
    
    @Handler 
    public void handle(PenaltyNotification msg){
        sendMessage(msg);
    }
    
    private void createSockets(String address, int localPort){
        closeConnectionIfAny();
        try {
            this.localPort = localPort;
            if(address != null){
                remotePort = localPort;
                this.address = InetAddress.getAllByName(address)[0];
            }
            receiverSocket = new DatagramSocket(localPort);
            senderSocket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(NetworkService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(NetworkService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendMessage(Object msg){
        try {
            byte[] data = ByteUtil.toByteArray(msg);
            DatagramPacket packet = new DatagramPacket(data, data.length, address, remotePort);
            senderSocket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(NetworkService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void broadcastMessagesFromNetwork(){
        
    }
    
    private void closeConnectionIfAny(){
        if(senderSocket != null){
            senderSocket.close();
        }
        if(receiverSocket != null){
            receiverSocket.close();
        }
    }
    
    @Override
    public void update(){
        broadcastMessagesFromNetwork();
    }
}
