package com.thevirtugroup.postitnote.rest;

import com.thevirtugroup.postitnote.dto.request.NoteDto;
import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("api/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @RequestMapping(method = RequestMethod.POST)
    public NoteDto createNote(@RequestBody NoteDto noteDto) {
        return noteService.save(noteDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<NoteDto> findAllNotes() {
        return noteService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NoteDto findNoteById(@PathVariable int id) {
        return noteService.getById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public NoteDto updateProduct(@RequestBody NoteDto noteDto) {
        return noteService.update(noteDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNoteById(@PathVariable int id) {
        noteService.deleteById(id);
    }

}
