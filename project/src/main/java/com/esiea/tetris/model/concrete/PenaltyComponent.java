package com.esiea.tetris.model.concrete;

import java.util.Random;
import net.engio.mbassy.listener.Handler;
import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.LineNotification;
import com.esiea.tetris.communication.concrete.PenaltyNotification;
import com.esiea.tetris.model.Component;

public class PenaltyComponent extends Component {
	private int compteurLine;
	private int idJoueur;
	
	public PenaltyComponent () {
		MessageBus.getInstance().subscribe(this);
		compteurLine=0;
	}
	
	@Handler
	public void handle(LineNotification msg){
		//appel de la variables nbLignes contenues dans le message
		int nbLignes=msg.getNbLignes();
		int _idJoueur=msg.getIdJoueur();
		Random rn = new Random();
		int idTypePenalty = rn.nextInt(2);
		
		if(idJoueur==_idJoueur) {
			compteurLine+=nbLignes;
		}
		
		if (compteurLine==10) {
			PenaltyNotification msgPenalty = new PenaltyNotification(idJoueur,idTypePenalty);
			MessageBus.getInstance().post(msgPenalty);
		}
	}

}
