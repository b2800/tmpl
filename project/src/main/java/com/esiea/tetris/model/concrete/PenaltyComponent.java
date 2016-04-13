package com.esiea.tetris.model.concrete;

import java.util.Random;
import net.engio.mbassy.listener.Handler;
import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.LineNotification;
import com.esiea.tetris.communication.concrete.PenaltyNotification;
import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.model.Component;
import com.esiea.tetris.utils.Vec2;

public class PenaltyComponent extends Component
                              implements Drawable {
    private int lineCount;
    private int playerId;

    public PenaltyComponent (int id) {
        MessageBus.getInstance().subscribe(this);
        lineCount=0;
        this.playerId = id;
    }

    @Handler
    public void handle(LineNotification msg){
        // Discard if message does not come from our player;
        if(msg.getPlayerId() != this.playerId){ return; }
        
        lineCount += msg.getLineCount();
        
        // Send penalty if enough lines were cleared. 
        if(lineCount >= 10){
            int penaltyTypeId = new Random().nextInt(2);
            int opponentId = (playerId+1)%2;
            PenaltyNotification msgPenalty = new PenaltyNotification(opponentId, penaltyTypeId);
            MessageBus.getInstance().post(msgPenalty);
            lineCount -= 10;
        }
    }

    @Override
    public TPanel getDrawableContainer() {
        TPanel panel = new TPanel();
        panel.add(this);
        panel.setPosition(position);
        panel.setSize(size);
        panel.setDrawBorder(true);
        return panel;
    }

    @Override
    public String[] getDrawableText() {
        int remainingLines = 10 - lineCount;
        String[] text = new String[2];
        text[0] = "Sending malus in: ";
        text[1] = Integer.toString(remainingLines) + " line";
        if(remainingLines > 1)
            text[1] += "s";
        return text;
    }

    @Override
    public Vec2 getDrawableRelativePosition() {
        return new Vec2(0,0);
    }

    @Override
    public int[][] getColorMap() {
        return null;
    }
}
