package com.github.sait39.the_project_backend.service;

import com.github.sait39.the_project_backend.dto.NoteDto;
import com.github.sait39.the_project_backend.model.Note;
import com.github.sait39.the_project_backend.model.User;
import com.github.sait39.the_project_backend.repository.NoteRepository;
import com.github.sait39.the_project_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    // Create or update
    public Note saveNote(NoteDto noteDto) {
        Note note = new Note();
        note.setTitle(noteDto.title());
        note.setContent(noteDto.content());

        return noteRepository.save(note);
    }

    // Read
    public Note findNoteByIdAndUser(Long id, User user) {
        return noteRepository.findByIdAndUser(id, user);
    }

    public List<Note> getNotesForUser(User user) {

        Optional<User> userFromDatabase = userRepository.findByEmail(user.getEmail());

        if (userFromDatabase.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return noteRepository.findByUser(userFromDatabase.get());
    }

    // Delete
    public void deleteNoteById(Long id) {
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
