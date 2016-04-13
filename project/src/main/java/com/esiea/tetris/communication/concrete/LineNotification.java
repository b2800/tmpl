package com.esiea.tetris.communication.concrete;

import com.esiea.tetris.communication.Message;

public class LineNotification extends Message{
	
    private int playerId;
    private int lineCount;

    public LineNotification(int id, int _nbLines)
    {
        this.playerId=id;
        this.lineCount=_nbLines;
    }

    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int idJoueur) {
        this.playerId = idJoueur;
    }
    public int getLineCount() {
        return lineCount;
    }
    public void setLineCount(int nbLignes) {
        this.lineCount = nbLignes;
    }

}
