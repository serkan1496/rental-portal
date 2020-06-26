package com.kafein.rental.serialzer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date>{

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		String dateAsStr = p.getText();
		if(dateAsStr == null || dateAsStr.equals("")) {
			return null;
		}
		
		try {
		    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateAsStr); 
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
