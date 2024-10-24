package com.eletrinho.springmongo.resources;

import com.eletrinho.springmongo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        User alex = new User("2", "Alex", "alex@gmail.com");
        User maria = new User("1", "Maria", "maria@gmail.com");
        List<User> users = new ArrayList<>();
        users.add(maria);
        users.add(alex);

        return ResponseEntity.ok(users);
    }
}
