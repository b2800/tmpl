package com.esiea.tetris.communication.concrete;

public class PenaltyNotification {
	
	private int idJoueur;
	private int idTypePenalty;
	
	public PenaltyNotification(int idJoueur, int idTypePenalty) {
		
		this.idJoueur=idJoueur;
		this.idTypePenalty=idTypePenalty;
	}

	public int getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	public int getIdTypePenalty() {
		return idTypePenalty;
	}

	public void setIdTypePenalty(int idTypePenalty) {
		this.idTypePenalty = idTypePenalty;
	}

}

	