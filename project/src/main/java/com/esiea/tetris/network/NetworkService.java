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

import java.util.logging.Level;
import java.util.logging.Logger;
import net.engio.mbassy.listener.Handler;

public class NetworkService implements Updatable{
    
    private DatagramSocket socket;
    private InetAddress address;
    private int localPort;
    private int remotePort;
    private SocketListener listener;

    public NetworkService(){
        MessageBus.getInstance().subscribe(this);
    }
    
    @Handler
    public void handle(MultiplayerMessage msg){
        createSockets(msg.getAddress(), msg.getPort());
    }
    
    @Handler 
    public void handle(GridStateNotification msg){
        if(msg.shouldPropagateOverNetwork()){
            sendMessage(msg);
        }
    }
    
    @Handler 
    public void handle(PenaltyNotification msg){
        sendMessage(msg);
    }
    
    private void createSockets(String address, int port){
        closeConnectionIfAny();
        this.listener = new SocketListener();
        try {
            if(address != null){
                remotePort = port;
                this.address = InetAddress.getAllByName(address)[0];
                socket = new DatagramSocket();
                this.localPort = socket.getLocalPort();
            } else { 
                this.localPort = port;
                socket = new DatagramSocket(localPort);
            }
            listener.provideSocket(socket);
            listener.start();
            
        } catch (SocketException | UnknownHostException ex) {
            Logger.getLogger(NetworkService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendMessage(Object msg){
        if(this.address == null){
            this.address = listener.getAddress();
            this.remotePort = listener.getRemotePort();
        }
        try {
            byte[] data = ByteUtil.toByteArray(msg);
            DatagramPacket packet = new DatagramPacket(data, data.length, address, remotePort);
            socket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(NetworkService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void closeConnectionIfAny(){
        if(socket != null){
            try {
                listener.close();
                listener.join();
                socket.close();
            } catch (InterruptedException ex) {
                Logger.getLogger(NetworkService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void update(){
        
    }
}
