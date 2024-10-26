package com.eletrinho.springmongo.dto;

import com.eletrinho.springmongo.entities.User;

import java.io.Serializable;

public class AuthorDTO implements Serializable {

    private String id;
    private String name;

    public AuthorDTO() {

    }

    public AuthorDTO(User obj) {
        id = obj.getId();
        name = obj.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
