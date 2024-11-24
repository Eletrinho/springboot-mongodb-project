package com.eletrinho.springmongo.resources;


import com.eletrinho.springmongo.entities.CommentDTO;
import com.eletrinho.springmongo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/comments/")
public class CommentResource {

    @Autowired
    private CommentService commentService;

    @PostMapping("{postId}")
    public ResponseEntity<CommentDTO> addComment(@PathVariable String postId, @RequestBody CommentDTO comment, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(commentService.addComment(postId, comment,token));
    }
}
