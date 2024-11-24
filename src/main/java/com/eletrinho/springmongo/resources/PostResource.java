package com.eletrinho.springmongo.resources;

import com.eletrinho.springmongo.config.util.JwtUtil;
import com.eletrinho.springmongo.entities.Post;
import com.eletrinho.springmongo.resources.util.URL;
import com.eletrinho.springmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findFist25() {
        return ResponseEntity.ok(postService.findFist25());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Post> insert(@RequestBody Post post, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(postService.insert(post,token));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, @RequestHeader("Authorization") String token) {
        String username = JwtUtil.extractUsername(token.substring(7));
        postService.deleteById(id, username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Post> put(@PathVariable String id, @RequestBody Post post, @RequestHeader("Authorization") String token) {
        String username = JwtUtil.extractUsername(token.substring(7));
        return ResponseEntity.ok(postService.put(post, id, username));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Post>> findByAuthor(@RequestParam(value = "username") String text) {
        text = URL.decodeParam(text);
        return ResponseEntity.ok(postService.findByAuthor(text));
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

    @GetMapping(value = "/search/title")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "title") String text) {
        return ResponseEntity.ok(postService.findByTitle(URL.decodeParam(text)));
    }
}
