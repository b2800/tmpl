package com.esiea.tetris.util;

import org.junit.Before;
import org.junit.Test;

import com.esiea.tetris.utils.vec2;

import static org.assertj.core.api.Assertions.*;

public class TestVec2 {
	
	public vec2 v1;
	public vec2 v2;
	
	@Before
	public void init()
	{
		v1=new vec2(4,1);
		v2=new vec2(8,3);
	}
	
	@Test
	public void itShouldCreateAnIdenticalVectorFromAnotherOne()
	{
		vec2 v3= new vec2 (v1);
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
		vec2 v3 = vec2.sum(v1,v2);
		assertThat(v3.x==12);
		assertThat(v3.y==4);
	}
	

}
