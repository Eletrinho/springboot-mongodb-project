package com.eletrinho.springmongo.dto;

import com.eletrinho.springmongo.entities.User;

import java.io.Serializable;

public class AuthorDTO implements Serializable {

    private String id;
    private String name;
    private String username;

    public AuthorDTO() {

    }

    public AuthorDTO(User obj) {
        id = obj.getId();
        name = obj.getName();
        username = obj.getUsername();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
