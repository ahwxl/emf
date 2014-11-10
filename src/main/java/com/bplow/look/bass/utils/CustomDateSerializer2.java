package com.bplow.look.bass.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class CustomDateSerializer2 extends JsonSerializer<Date>{

	@Override
	public void serialize(Date mydate, JsonGenerator jg, SerializerProvider sp)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        String formattedDate = formatter.format(mydate); 
        jg.writeString(formattedDate); 
	}

}
