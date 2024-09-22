package com.github.sait39.the_project_backend.controller;

import com.github.sait39.the_project_backend.dto.NoteDto;
import com.github.sait39.the_project_backend.model.Note;
import com.github.sait39.the_project_backend.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public List<Note> readNotes() {
        return noteService.findAll();
    }

    @GetMapping("/notes/{id}")
    public Note readNote(@PathVariable Long id) {
        return noteService.findById(id);
    }

    @PostMapping("/notes")
    public Note createNote(@RequestBody Note note) {
        return noteService.save(note);
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody NoteDto noteDto) {

        return noteService.updateNote(id, noteDto);
    }



}
