/**
 * 
 */
package com.springboot.justbook.searchservice.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author M1006601
 *
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
	
	@Override
    public LocalDate deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException {

    	DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
    	DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    	LocalDate date = LocalDate.parse(arg0.getText(), inputFormatter);
    	String formattedDate = outputFormatter.format(date);
    	LocalDate dateTime = LocalDate.parse(formattedDate, outputFormatter);
    	/*LocalDate date = LocalDate.parse(arg0.getText(), inputFormatter);
    	String formattedDate = outputFormatter.format(date);
    	System.err.println(arg0.getText().toString()+"------------"+ formattedDate);
    	LocalDateTime released = null;
    	if(arg0.currentName().equalsIgnoreCase("movieReleaseDate")) {
    		released = LocalDateTime.parse(arg0.getText(), outputFormatter);
    	} else {
    		released = LocalDateTime.parse(arg0.getText());
    	}
        return released;*/
    	return dateTime;
    }
}