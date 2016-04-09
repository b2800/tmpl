package com.esiea.tetris.util;

import com.esiea.tetris.communication.MessageBus;

import net.engio.mbassy.listener.Handler;

public class Listener {
	
	public Listener(){
		MessageBus.getInstance().subscribe(this);

	}

	@Handler
	public void handle(Object t) {
		
		
	}
	
	
}
