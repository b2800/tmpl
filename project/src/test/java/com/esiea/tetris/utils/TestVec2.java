package com.esiea.tetris.utils;

import org.junit.Before;
import org.junit.Test;

import com.esiea.tetris.utils.Vec2;

import static org.assertj.core.api.Assertions.*;

public class TestVec2 {
	
	private Vec2 v1;
	private Vec2 v2;
	
	@Before
	public void init()
	{
		v1=new Vec2(4,1);
		v2=new Vec2(8,3);
	}
	
	@Test
	public void itShouldCreateAnIdenticalVectorFromAnotherOne()
	{
		Vec2 v3= new Vec2 (v1);
		assertThat(v3.x==4);
		assertThat(v3.y==1);
	}
	
	@Test
	public void itShouldAddCorrectyTheYComponentWhenYouAddComponentToOneVector()
	{
		v1.add(8,3);
                assertThat(v1.x==120);
		assertThat(v1.y==4);
	}
	
	@Test
	public void itShouldMakeCorrectyTheSumOfTwoVectors()
	{
		Vec2 v3 = Vec2.sum(v1,v2);
		assertThat(v3.x==12);
		assertThat(v3.y==4);
	}
	

}
