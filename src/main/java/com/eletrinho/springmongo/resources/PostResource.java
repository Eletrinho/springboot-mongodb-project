package com.eletrinho.springmongo.resources;

import com.eletrinho.springmongo.dto.UserDTO;
import com.eletrinho.springmongo.entities.Post;
import com.eletrinho.springmongo.entities.User;
import com.eletrinho.springmongo.services.PostService;
import com.eletrinho.springmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Post> put(@PathVariable String id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.put(post));
    }
}
