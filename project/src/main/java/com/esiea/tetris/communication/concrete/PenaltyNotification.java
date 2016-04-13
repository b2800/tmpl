package com.esiea.tetris.communication.concrete;

import com.esiea.tetris.communication.Message;

public class PenaltyNotification extends Message{
	
	private int idJoueur;
	private int idTypePenalty;
	
	public PenaltyNotification(int idJoueur, int idTypePenalty) {
		setPropagateOverNetwork(true);
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

	