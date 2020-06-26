package com.kafein.rental.serialzer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      
		if (value == null) {
            gen.writeNull();
        } else {
        	String dateAsStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
            gen.writeString(dateAsStr);
        }
	}

}
