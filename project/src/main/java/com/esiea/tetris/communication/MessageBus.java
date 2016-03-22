package com.esiea.tetris.communication;

import net.engio.mbassy.bus.MBassador;

public final class MessageBus {
    private final static MBassador bus = new MBassador();
    
    public static MBassador getInstance(){
        return bus;
    }
}
