package com.esiea.tetris.Communication;

import net.engio.mbassy.bus.MBassador;

public class MessageBus {
    private static MBassador bus;
    
    public static MBassador getInstance(){
        if(bus == null){
            bus = new MBassador();
        }
        return bus;
    }
}
