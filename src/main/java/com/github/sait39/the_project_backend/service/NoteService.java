package com.github.sait39.the_project_backend.service;

import com.github.sait39.the_project_backend.dto.NoteDto;
import com.github.sait39.the_project_backend.model.Note;
import com.github.sait39.the_project_backend.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // Create or update
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    // Read
    public Note findById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    // Delete
    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public List<Note> searchNotes(String title, String content) {
        return noteRepository.findByTitleOrContent(title, content);
    }

    public Note updateNote(Long id, NoteDto noteDto) {
        Note existingNote = noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));

        // Update only if title is provided
        Optional.ofNullable(noteDto.title()).ifPresent(existingNote::setTitle);

        // Update only if content is provided
        Optional.ofNullable(noteDto.content()).ifPresent(existingNote::setContent);

        return noteRepository.save(existingNote);
    }



}
