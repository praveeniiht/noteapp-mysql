package com.example.notesservice.exception;

public class NotesNotFoundException extends RuntimeException{
	public NotesNotFoundException(String message) {
		super(message);
	}
}
