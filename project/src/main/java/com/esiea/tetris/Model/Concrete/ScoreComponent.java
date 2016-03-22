package com.esiea.tetris.Model.Concrete;

import com.esiea.tetris.Model.Component;

public class ScoreComponent extends Component{
	
	private int score;
	private int numJoueur;
	private int coef; // non static car propre à chaque joueur (modification possible pour un joueur en cours de partie)
	
	public ScoreComponent(int numJoueur, int score)
	{
		this.score=score;
		this.numJoueur=numJoueur;
	}
	


	public void udpate(){
    
    	//reçoit le message
		int nbLignes=;
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

	public int getNumJoueur() {
		return numJoueur;
	}

	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}

}
