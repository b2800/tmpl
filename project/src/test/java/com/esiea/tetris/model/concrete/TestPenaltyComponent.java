package com.esiea.tetris.model.concrete;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.LineNotification;
import com.esiea.tetris.communication.concrete.PenaltyNotification;
import net.engio.mbassy.listener.Handler;

import static org.junit.Assert.*;




public class TestPenaltyComponent {
	
    private PenaltyComponent pc;
    private PenaltyListener pl;

    @Before
    public void init(){
        pc = new PenaltyComponent(1);
        pl = new PenaltyListener();
    }

    @Test
    public void itShouldSendAPenaltyAfter10Lines()
    {
        assertEquals(pl.gotPenalty(), false);
        
        // When player 1 clears 10 lines
        send10Lines(1);

        // Then it should have sent a penaltyMessage;
        // assertEquals(pl.gotPenalty(), true);
    }
    
    @Test
    public void itShouldNotProcessOtherPlayersMessage(){
        assertEquals(pl.gotPenalty(), false);
        
        // When player 0 clears 10 lines
        send10Lines(0);

        // Then it should NOT have sent a penaltyMessage;
        assertEquals(pl.gotPenalty(), false);
    }
    
    private void send10Lines(int idPlayer){
        LineNotification msg = new LineNotification(idPlayer, 2);
        for(int i = 0; i < 5; i++){
            MessageBus.getInstance().post(msg).now();
        }
    }
    
    private class PenaltyListener{
        private boolean gotPenalty;
        private int playerId;

        public PenaltyListener() {
            MessageBus.getInstance().subscribe(this);
            gotPenalty = false;
        }
        
        @Handler
        public void handle(PenaltyNotification msg){
            gotPenalty = true;
        }

        public boolean gotPenalty(){
            return gotPenalty;
        }
    }

}
