package com.eletrinho.springmongo.resources;

import com.eletrinho.springmongo.dto.UserDTO;
import com.eletrinho.springmongo.entities.Post;
import com.eletrinho.springmongo.entities.User;
import com.eletrinho.springmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> list = userService.findAll().stream().map(UserDTO::new).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(new UserDTO(userService.findById(id)));
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPostsByUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id).getPosts());
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody User user) {
        UserDTO userDTO = new UserDTO(user);
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> put(@PathVariable String id, @RequestBody User user) {
        return ResponseEntity.ok(new UserDTO(userService.put(user)));
    }
}
