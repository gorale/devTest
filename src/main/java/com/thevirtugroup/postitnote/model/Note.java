package com.thevirtugroup.postitnote.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Note {
    private int id;
    private String name;
    private String text;
    private String createdTs;

    public Note() {
    }

    public Note(int id, String name, String text, String createdTs) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.createdTs = createdTs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(String createdTs) {
        this.createdTs = createdTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(name, note.name) && Objects.equals(text, note.text) && Objects.equals(createdTs, note.createdTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, text, createdTs);
    }
}
