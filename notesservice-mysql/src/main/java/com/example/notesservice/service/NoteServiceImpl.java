package com.example.notesservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.notesservice.dto.NotesDto;
import com.example.notesservice.model.Note;
import com.example.notesservice.repo.NoteRepository;
import static com.example.notesservice.utils.NotesUtilities.convertToNote;
import static com.example.notesservice.utils.NotesUtilities.convertToNoteDto;
import static com.example.notesservice.utils.NotesUtilities.convertToNotesDtoList;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	private NoteRepository noteRepository;

	@Override
	public List<NotesDto> findAll() {
		// TODO Auto-generated method stub
		List<Note> list =  noteRepository.findAll();
		return convertToNotesDtoList(list);
	}

	@Override
	public NotesDto findById(long id) {
		// TODO Auto-generated method stub
		List<Note>list = noteRepository.findAll();
		List<NotesDto> dtolist = convertToNotesDtoList(list);
		NotesDto temp=null;
		for(NotesDto dtonote:dtolist) {
			if(dtonote.getId()==id){
				temp=dtonote;
				break;
			}
		}
		return  temp;
		
	}

	@Override
	public NotesDto addNote(NotesDto note) {
		Note dto = noteRepository.save(convertToNote(note));
		return convertToNoteDto(dto);
	}

	@Override
	public NotesDto deleteNote(long id) {
		// TODO Auto-generated method stub
		Optional<Note> notedto = noteRepository.findById(id);
		Note note = notedto.get();
		noteRepository.delete(note);
		return convertToNoteDto(note);

	}

	@Override
	public List<NotesDto> findAllByStatus(String status) {
		// TODO Auto-generated method stub
		List<Note> list = noteRepository.findAll();
		List<Note> notes = list.stream()
						 			.filter(n->n.getStatus().equals(status))
						 			.collect(Collectors.toList()); 
		return convertToNotesDtoList(notes);
	}

	@Override
	public List<NotesDto> findAllByTitle(String title) {
		// TODO Auto-generated method stub
		System.out.println("Inside the notes repository");
		List<Note> list = noteRepository.findAll();
		List<Note> notes = list.stream()
						 			.filter(n->n.getTitle().equals(title))
						 			.collect(Collectors.toList()); 
		return convertToNotesDtoList(notes);
	}

	@Override
	public List<NotesDto> findAllByAuthor(String author) {
		// TODO Auto-generated method stub
		List<Note> list = noteRepository.findAll();
		List<Note> notes = list.stream()
						 			.filter(n->n.getAuthor().equals(author))
						 			.collect(Collectors.toList()); 
		return convertToNotesDtoList(notes);
	}

	@Override
	public NotesDto updateStatus(long id, String status) {
		// TODO Auto-generated method stub
		Optional<Note> note= noteRepository.findById(id);
		Note notes = note.get();
		notes.setStatus(status);
		return convertToNoteDto(notes);
	}
}
