package com.esiea.tetris.model.concrete;

import java.util.Random;
import net.engio.mbassy.listener.Handler;
import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.LineNotification;
import com.esiea.tetris.communication.concrete.PenaltyNotification;
import com.esiea.tetris.graphics.Drawable;
import com.esiea.tetris.graphics.TPanel;
import com.esiea.tetris.model.Component;
import com.esiea.tetris.utils.vec2;

public class PenaltyComponent extends Component
                              implements Drawable {
	private int compteurLine;
	private int idJoueur;
	
	public PenaltyComponent (int idJoueur) {
		MessageBus.getInstance().subscribe(this);
		compteurLine=0;
                this.idJoueur = idJoueur;
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
        String[] text = new String[2];
        text[0] = "Lines before malus : ";
        text[1] = Integer.toString(10 - compteurLine);
        return text;
    }

    @Override
    public vec2 getDrawableRelativePosition() {
        return new vec2(0,0);
    }

}
