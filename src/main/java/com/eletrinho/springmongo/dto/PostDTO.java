package com.eletrinho.springmongo.dto;

import com.eletrinho.springmongo.entities.Post;

import java.io.Serializable;

public class PostDTO implements Serializable {

    private String title;
    private String body;

    public PostDTO() {
    }

    public PostDTO(Post post) {

    }


}
