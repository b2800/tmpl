package com.esiea.tetris.network;

import com.esiea.tetris.communication.Message;
import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.GridStateNotification;
import com.esiea.tetris.utils.ByteUtil;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketListener extends Thread{

    private DatagramSocket socket;
    private boolean shouldClose;
    private InetAddress address;
    private int remotePort;
    
    public SocketListener() {
        shouldClose = false;
    }
    
    public void provideSocket(DatagramSocket s){
        this.socket = s;
    }
    
    public void close(){
        shouldClose = true;
    }
    
    public void run(){
        while(!shouldClose){
            try {
                
                byte[] receivedData = new byte[4096];
                DatagramPacket packet = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(packet);
                Message msg = (Message)ByteUtil.fromByteArray(receivedData);
                msg.setPropagateOverNetwork(false);  // Prevent broadcast storms
                MessageBus.getInstance().post(msg).asynchronously();
                
                if(address == null){
                    address = packet.getAddress();
                    remotePort = packet.getPort();
                }
            } catch (IOException ex) {
                Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        socket.close();
    }
    
    public InetAddress getAddress(){
        return address;
    }

    public int getRemotePort(){
        return remotePort;
    }
}
