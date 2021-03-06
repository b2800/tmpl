package com.esiea.tetris.model.concrete;

import com.esiea.tetris.communication.concrete.JSONMessage;
import net.engio.mbassy.listener.Handler;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.LineNotification;
import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.model.Component;
import com.esiea.tetris.utils.ScoreUtil;
import com.esiea.tetris.utils.Vec2;

public class ScoreComponent extends Component implements Drawable{
	
	private int score;
	private int idJoueur;
	private int coef; // non static car propre à chaque joueur (modification possible pour un joueur en cours de partie)
	
	public ScoreComponent(int numJoueur, int score)
	{
		this.score=score;
		this.idJoueur=numJoueur;
		this.coef=100; // par défaut à 100
		
		MessageBus.getInstance().subscribe(this);
	}
	
	@Handler
	public void handle(LineNotification msg){
		//reçoit le message en paramètre
		
		// on extrait les informations contenues dans le message
		int idJoueur=msg.getPlayerId();
		int nbLignes=msg.getLineCount();
		
		// Si la notification concerne le joueur actuel, on incrémente son score
		if(idJoueur==this.idJoueur)
			incrementScore(nbLignes);
	}
        
        @Handler
        public void handle(JSONMessage msg){
            if("gameover".equals(msg.getType())){
                ScoreUtil.writeHighScore(score);
            } else if ("newgame".equals(msg.getType())){
                score = 0;
            }
        }

	public void incrementScore (int nbLignes) {
		this.score+=this.coef*nbLignes*nbLignes;
	}
	
        public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(int numJoueur) {
		this.idJoueur = numJoueur;
	}

    @Override
    public TPanel getDrawableContainer() {
        TPanel panel = new TPanel();
        panel.setPosition(position);
        panel.setSize(size);
        panel.add(this);
        panel.setDrawBorder(true);
        return panel;
    }

    @Override
    public String[] getDrawableText() {
        String[] text = { Integer.toString(score) };
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
