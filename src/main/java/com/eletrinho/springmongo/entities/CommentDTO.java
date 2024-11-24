package com.eletrinho.springmongo.entities;

import com.eletrinho.springmongo.dto.AuthorDTO;

import java.io.Serializable;
import java.time.Instant;

public class CommentDTO implements Serializable {

    private String text;
    private Instant date = Instant.now();
    private AuthorDTO author;

    public CommentDTO() {

    }

    public CommentDTO(String text, AuthorDTO author) {
        this.text = text;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getDate() {
        return date;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}
