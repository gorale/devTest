package com.thevirtugroup.postitnote.rest;

import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 */
@RestController
@RequestMapping("api/note")
public class NoteController
{
    @Autowired
    private NoteService noteService;
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public Note createNote(@RequestBody Note note) {
        return noteService.save(note);
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Note> findAllNotes() {
        return noteService.getAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, headers = "Accept=application/json")
    public Note findNoteById(@PathVariable int id) {
        return noteService.getById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public Note updateProduct(@RequestBody Note note) {
        return noteService.update(note);
    }

    @RequestMapping(value ="{/id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<?> deleteNoteById(@PathVariable int id) {
        noteService.deleteById(id);
        return ResponseEntity.ok("Item deleted successfully");
    }

}
