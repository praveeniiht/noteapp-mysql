package com.example.notesservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.notesservice.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long>{

}