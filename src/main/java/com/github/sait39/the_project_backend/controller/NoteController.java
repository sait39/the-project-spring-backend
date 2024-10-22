package com.github.sait39.the_project_backend.controller;

import com.github.sait39.the_project_backend.dto.NoteDto;
import com.github.sait39.the_project_backend.model.Note;
import com.github.sait39.the_project_backend.model.User;
import com.github.sait39.the_project_backend.service.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public List<Note> readNotes() {
        // Retrieve the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println(oAuth2User);

        User user = oAuth2User.getAttribute("sub");

        return noteService.getNotesForUser(user);
    }

    @GetMapping("/notes/{id}")
    public Note readNote(@PathVariable Long id, @PathVariable User user) {
        return noteService.findNoteByIdAndUser(id, user);
    }

    @PostMapping("/notes")
    public Note createNote(@RequestBody NoteDto note) {
        Note dto = new Note();
        return noteService.saveNote(note);
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody NoteDto noteDto) {

        return noteService.updateNote(id, noteDto);
    }



}
