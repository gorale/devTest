package com.thevirtugroup.postitnote.service;

import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    public Note getById(int id) {
        return noteRepository.findById(id);
    }

    public void deleteById(int id) {
        noteRepository.deleteById(id);
    }

    public Note update(Note note) {
        return noteRepository.update(note);
    }

}
