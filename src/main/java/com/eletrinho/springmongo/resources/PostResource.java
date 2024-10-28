package com.eletrinho.springmongo.resources;

import com.eletrinho.springmongo.entities.Post;
import com.eletrinho.springmongo.resources.util.URL;
import com.eletrinho.springmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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

    @GetMapping(value = "/search/after")
    public ResponseEntity<List<Post>> findAfter(@RequestParam(value = "date") String text) {
        text = URL.decodeParam(text);
        Instant date = LocalDate.parse(text, DateTimeFormatter.ofPattern("dd MM yyyy")).atStartOfDay().toInstant(ZoneOffset.UTC);
        return ResponseEntity.ok(postService.findAfter(date));
    }

    @GetMapping(value = "/search/before")
    public ResponseEntity<List<Post>> findBefore(@RequestParam(value = "date") String text) {
        text = URL.decodeParam(text);
        Instant date = LocalDate.parse(text, DateTimeFormatter.ofPattern("dd MM yyyy")).atStartOfDay().toInstant(ZoneOffset.UTC);
        return ResponseEntity.ok(postService.findBefore(date));
    }
}
