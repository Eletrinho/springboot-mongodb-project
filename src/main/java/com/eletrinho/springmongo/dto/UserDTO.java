package com.eletrinho.springmongo.dto;

import com.eletrinho.springmongo.entities.User;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private String name;
    private String email;
    private String username;

    public UserDTO() {
    }

    public UserDTO(User obj) {
        name = obj.getName();
        email = obj.getEmail();
        username = obj.getUsername();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
