package com.thevirtugroup.postitnote.dto.request;

public class NoteDto {

    private int id;
    private String name;
    private String text;
    private String createdTs;

    public NoteDto(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public NoteDto() {
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

}
