package com.thevirtugroup.postitnote.converter;

import com.thevirtugroup.postitnote.dto.request.NoteDto;
import com.thevirtugroup.postitnote.model.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteConverter {
    public static NoteDto toDto(Note note) {
        NoteDto noteDto = new NoteDto();
        noteDto.setId(note.getId());
        noteDto.setName(note.getName());
        noteDto.setText(note.getText());
        noteDto.setCreatedTs(note.getCreatedTs());
        return noteDto;
    }

    public static Note toEntity(NoteDto noteDto) {
        Note note = new Note();
        note.setId(noteDto.getId());
        note.setName(noteDto.getName());
        note.setText(noteDto.getText());
        note.setCreatedTs(noteDto.getCreatedTs());
        return note;
    }

}
