package com.esiea.tetris.Model.Concrete;

import net.engio.mbassy.listener.Handler;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.LineNotification;
import com.esiea.tetris.Model.Component;

public class ScoreComponent extends Component{
	
	private int score;
	private int idJoueur;
	private int coef; // non static car propre à chaque joueur (modification possible pour un joueur en cours de partie)
	
	public ScoreComponent(int numJoueur, int score)
	{
		this.score=score;
		this.idJoueur=numJoueur;
		
		MessageBus.getInstance().subscribe(this);
	}
	
	@Handler
	public void handle(LineNotification msg){
		//reçoit le message en paramètre
		
		// on extrait les informations contenues dans le message
		int idJoueur=msg.getIdJoueur();
		int nbLignes=msg.getNbLignes();
		
		// Si la notification concerne le joueur actuel
		if(idJoueur==this.idJoueur)
			incrementScore(nbLignes);
		
	}
	
	
	public void incrementScore (int nbLignes) {
		this.score=this.coef*nbLignes*nbLignes;
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

}
