package com.esiea.tetris.model.concrete;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.LineNotification;
import com.esiea.tetris.communication.concrete.PenaltyNotification;
import com.esiea.tetris.util.Listener;

import static org.assertj.core.api.Assertions.*;



public class TestPenaltyComponent {
	
	private PenaltyComponent pc;
	private Listener listener;
	
	@Before
	public void init()
	{
		pc = new PenaltyComponent();
		listener = mock(Listener.class);
		when(listener.handle(new PenaltyNotification(1,1)));
	}
		
	@Test
	public void itShouldDisplayPenaltyNotificationWhenEnoughLineNotification()
	{
		LineNotification msg= new LineNotification(1,4);
		MessageBus.getInstance().post(msg);
		MessageBus.getInstance().post(msg);
		MessageBus.getInstance().post(msg); // 12 lignes qui ont été détruites (4lignes * 3messages)
		
	}
	
	
	
	
	
	
}
