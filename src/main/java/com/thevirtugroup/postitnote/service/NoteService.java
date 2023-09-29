package com.thevirtugroup.postitnote.service;

import com.thevirtugroup.postitnote.converter.NoteConverter;
import com.thevirtugroup.postitnote.dto.request.NoteDto;
import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    public NoteDto save(NoteDto noteDto) {
        Note saved = noteRepository.save(NoteConverter.toEntity(noteDto));
        return NoteConverter.toDto(saved);
    }

    public List<NoteDto> getAll() {

        return noteRepository.findAll().stream().map(NoteConverter::toDto).collect(Collectors.toList());
    }

    public NoteDto getById(int id) {
        return NoteConverter.toDto(noteRepository.findById(id));
    }

    public void deleteById(int id) {
        noteRepository.deleteById(id);
    }

    public NoteDto update(NoteDto noteDto) {
        noteRepository.update(NoteConverter.toEntity(noteDto));
        return noteDto;
    }

}
