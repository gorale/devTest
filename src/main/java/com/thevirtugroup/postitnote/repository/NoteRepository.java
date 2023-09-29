package com.thevirtugroup.postitnote.repository;

import com.thevirtugroup.postitnote.exception.EntityNotFoundException;
import com.thevirtugroup.postitnote.model.Note;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class NoteRepository {

    private final List<Note> notes = new ArrayList<>();

    public List<Note> findAll() {
        return notes;
    }

    public Note findById(int id) {
        return notes.stream().filter(note -> note.getId() == id).findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("not found note by id: %d", id)));
    }


    public Note save(Note noteDetail) {
        Note note = new Note();
        note.setId(notes.size() + 1);
        note.setName(noteDetail.getName());
        note.setText(noteDetail.getText());
        note.setCreatedTs(LocalDateTime.now().toString());
        notes.add(note);
        return note;
    }

    public void deleteById(Integer id) {
        notes.removeIf(x -> x.getId() == (id));
    }

    public Note update(Note noteDetail) {
        int index = 0;
        int id = 0;
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == (noteDetail.getId())) {
                id = noteDetail.getId();
                index = i;
                break;
            }
        }

        Note note = new Note();
        note.setId(id);
        note.setName(noteDetail.getName());
        note.setText(noteDetail.getText());
        note.setCreatedTs(LocalDateTime.now().toString());
        notes.set(index, note);
        return note;
    }

}
