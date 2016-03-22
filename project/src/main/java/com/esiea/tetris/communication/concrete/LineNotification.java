package com.esiea.tetris.communication.concrete;

public class LineNotification {
	
	private int idJoueur;
	private int nbLignes;
	
	public LineNotification(int idJoueur, int nbLignes)
	{
		this.idJoueur=idJoueur;
		this.nbLignes=nbLignes;
	}
	
	public int getIdJoueur() {
		return idJoueur;
	}
	public void setIdJoueur(int idJoueur) {
		this.idJoueur = idJoueur;
	}
	public int getNbLignes() {
		return nbLignes;
	}
	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}

}
