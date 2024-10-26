package com.eletrinho.springmongo.resources;

import com.eletrinho.springmongo.entities.Post;
import com.eletrinho.springmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;


@RestController
@RequestMapping(value = "/posts")
public class PostResource implements Serializable {

    @Autowired
    private PostService postService;


    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

}
