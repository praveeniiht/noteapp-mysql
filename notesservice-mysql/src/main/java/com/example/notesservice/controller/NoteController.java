package com.example.notesservice.controller;

import static com.example.notesservice.utils.NotesUtilities.convertToNotesDtoList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.notesservice.dto.NotesDto;
import com.example.notesservice.dto.NotesExceptionResponse;
import com.example.notesservice.exception.NotesException;
import com.example.notesservice.exception.NotesNotFoundException;
import com.example.notesservice.model.Note;
import com.example.notesservice.service.NoteService;

@RestController
@RequestMapping("/noteservice")
public class NoteController {
	
	@Autowired
	private NoteService noteService;

	@GetMapping("/all")
	public ResponseEntity<List<NotesDto>> findAll(){
		System.out.println("Inside the noteservice");
		 HttpStatus httpStatus = HttpStatus.OK; 
		 List<NotesDto> dtolist= noteService.findAll(); 
		 return new ResponseEntity<>(dtolist,httpStatus);
	}
	
	@PostMapping("/add")
	public ResponseEntity<NotesDto> addNote(@RequestBody NotesDto note, BindingResult result){
		HttpStatus status = HttpStatus.CREATED;
    	NotesDto saved = noteService.addNote(note);
        return new ResponseEntity<>(saved, status);
    }
	@PutMapping("/update/{id}/{status}")
	public ResponseEntity<NotesDto> updateStatus(@PathVariable("id") Integer id, @PathVariable("status") String status){ 
		HttpStatus statuscode = HttpStatus.CREATED;
    	NotesDto notedto = noteService.updateStatus(id, status);
        return new ResponseEntity<>(notedto, statuscode);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<NotesDto> deleteNote(@PathVariable("id") Integer id){
		HttpStatus status = HttpStatus.OK;
		NotesDto notedto = noteService.deleteNote(id);
		return new ResponseEntity<>(notedto,status);
	}
	
	@GetMapping("/note/{id}")
	public ResponseEntity<NotesDto> findById(@PathVariable("id") int  id){
		HttpStatus status = HttpStatus.OK;
		NotesDto notesdto=   noteService.findById(id);
		return new ResponseEntity<>(notesdto,status);
	}
	
	@GetMapping("/search/status/{status}")
	public ResponseEntity<List<NotesDto>> findAllByStatus(@PathVariable("status") String status){
		HttpStatus httpStatus = HttpStatus.OK;
		List<NotesDto> dtolist = noteService.findAllByStatus(status);
		return new ResponseEntity<>(dtolist,httpStatus);
	}
	
	@GetMapping("/search/author/{author}")
	public ResponseEntity<List<NotesDto>> findAllByAuthor(@PathVariable("author") String author){
		HttpStatus httpStatus = HttpStatus.OK;
		List<NotesDto> dtolist = noteService.findAllByAuthor(author);
		return new ResponseEntity<>(dtolist,httpStatus);
	}
	
	@GetMapping("/search/title/{title}")
	public ResponseEntity<List<NotesDto>> findAllByTitle(@PathVariable("title") String title){
		System.out.println("Inside the note controller....");
		HttpStatus httpStatus = HttpStatus.OK;
		List<NotesDto> dtolist = noteService.findAllByTitle(title);
		return new ResponseEntity<>(dtolist,httpStatus);
	}
	
	@ExceptionHandler(NotesException.class)
	public ResponseEntity<NotesExceptionResponse> NotesHandler(NotesException ex){
		NotesExceptionResponse resp = 
				new NotesExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value());
		
		ResponseEntity<NotesExceptionResponse> response = 
				new ResponseEntity<NotesExceptionResponse>(resp, HttpStatus.BAD_REQUEST);
		return response;
	}
	@ExceptionHandler(NotesNotFoundException.class)
	public ResponseEntity<NotesExceptionResponse> NotesHandler(NotesNotFoundException ex){
		NotesExceptionResponse resp = 
				new NotesExceptionResponse(ex.getMessage(),System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		ResponseEntity<NotesExceptionResponse> response = 
				new ResponseEntity<NotesExceptionResponse>(resp, HttpStatus.NOT_FOUND);
		return response;
	}
}
