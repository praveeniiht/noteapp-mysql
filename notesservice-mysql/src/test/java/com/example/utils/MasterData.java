package com.example.utils;

import java.io.IOException;

import com.example.notesservice.dto.NotesDto;
import com.example.notesservice.model.Note;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MasterData {
	public static NotesDto getNotesDto() {

		NotesDto notesdto = new NotesDto();
		notesdto.setId(10090);
		notesdto.setAuthor("Praveen");
		notesdto.setTitle("Jenkins");
		notesdto.setDescription("This is the best CI/CD tool");
		notesdto.setStatus("Done");
		//notesdto.setNoOfLikes(10);
		return notesdto;
	}
	
	public static Note getNotes() {

		Note notes = new Note();
		notes.setId(10090);
		notes.setAuthor("Praveen");
		notes.setTitle("Jenkins");
		notes.setDescription("This is the best CI/CD tool");
		notes.setStatus("Done");
		//notes.setNoOfLikes(10);
		
		return notes;
	}
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	public static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
    
}
