package com.esiea.tetris.model.concrete;

import org.junit.Before;
import org.junit.Test;

import com.esiea.tetris.communication.MessageBus;
import com.esiea.tetris.communication.concrete.LineNotification;

import static org.assertj.core.api.Assertions.*;

public class TestScoreComponent {
	
	public ScoreComponent sc;
	
	@Before
	public void init()
	{
		sc = new ScoreComponent(1,0);
	}
	
	@Test
	public void itShouldIncrementScoreForOneLineForPlayerOne()
	{
		int initialScore=sc.getScore();
		
		// Envoi d'une notification informant que le joueur 1 a complété 1 ligne
		LineNotification msg= new LineNotification(1,1);
		MessageBus.getInstance().post(msg);
		
		// ... le message est traité automatiquement par le ScoreComponent depuis sa méthode Handle 
		
		// On vérifie que la valeur du score a été modifiée correctement
		assertThat(sc.getScore()==initialScore+100);
	}
	
	@Test
	public void itShouldIncrementScoreForTwoLinesForPlayerOne()
	{
		int initialScore=sc.getScore();
		
		// Envoi d'une notification informant que le joueur 1 a complété 2 lignes
		LineNotification msg= new LineNotification(1,2);
		MessageBus.getInstance().post(msg);
		
		assertThat(sc.getScore()==initialScore+400);
	}
	
	@Test
	public void itShouldNotIncrementScoreForWrongPlayer()
	{
		int initialScore=sc.getScore();
		
		// Envoi d'une notificaiton qui s'adresse à un autre joueur (ici 4), le joueur testé (ici 1) ne devra donc pas incrémenter son score
		LineNotification msg= new LineNotification(4,2);
		MessageBus.getInstance().post(msg);
		
		assertThat(sc.getScore()==initialScore);
	}

}
