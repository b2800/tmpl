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
		LineNotification msg= new LineNotification(1,1);
		MessageBus.getInstance().post(msg);
		
		assertThat(sc.getScore()==100);
	}
	
	@Test
	public void itShouldIncrementScoreForTwoLinesForPlayerOne()
	{
		LineNotification msg= new LineNotification(1,2);
		MessageBus.getInstance().post(msg);
		
		assertThat(sc.getScore()==400);
	}
	
	@Test
	public void itShouldNotIncrementScoreForWrongPlayer()
	{
		int initialScore=sc.getScore();
		LineNotification msg= new LineNotification(4,2);
		MessageBus.getInstance().post(msg);
		
		assertThat(sc.getScore()==initialScore);
	}

}
