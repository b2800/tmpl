package com.esiea.tetris.utils;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import com.esiea.tetris.communication.concrete.JSONMessage;

public class TestByteUtil {

	@Test
	public void itShouldReturnToTheInitialStateWhenYouConvertToByteArrayAndConvertBackToInitialType() {
		
		JSONMessage msg = new JSONMessage();
		msg.setType("MoveLeft");
		
		JSONObject json = new JSONObject();
		json.put("one", "test");
		json.put("two", new Integer(41));
		json.put("three", new Double(1124.21));
		json.put("four", new Boolean(true));
	    
		msg.setJson(json);
		
		byte[] ba=ByteUtil.toByteArray(msg);
		JSONMessage msg2=(JSONMessage) ByteUtil.fromByteArray(ba);
		
		assertEquals(msg.getType(),msg2.getType());
		
		assertEquals(msg.getJSON(),msg2.getJSON());
		
	}

}
