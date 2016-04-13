package com.esiea.tetris.network;

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
                Object msg = ByteUtil.fromByteArray(receivedData);
                GridStateNotification notif = (GridStateNotification)msg;
                System.out.println("id : " + notif.getId());
                notif.setPropagateOverNetwork(false);
                MessageBus.getInstance().post(msg).asynchronously();
            } catch (IOException ex) {
                Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        socket.close();
    }
}
