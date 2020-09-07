package com.example.notesservice.dto;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class NotesDto {
	private long id;
	@NotNull
	@Length(min=5,max=15)
	private String title;
	@NotNull
	@Length(min=5,max=20)
	private String author;
	@NotNull
	@Length(min=10,max=300)
	private String description;
	@NotNull
	@Length(min=5,max=15)
	private String status;

}