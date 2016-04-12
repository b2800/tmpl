package com.esiea.tetris.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestScoreUtil {

	@Before
	public void init()
	{
	}
	
	@Test
	public void test() {
		ScoreUtil.writeHighScore(2500);
	}

}
