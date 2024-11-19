package com.eletrinho.springmongo.resources;

import com.eletrinho.springmongo.config.util.JwtUtil;
import com.eletrinho.springmongo.config.util.PasswordUtil;
import com.eletrinho.springmongo.dto.LoginResponseDTO;
import com.eletrinho.springmongo.dto.UserDTO;
import com.eletrinho.springmongo.entities.User;
import com.eletrinho.springmongo.entities.UserLoginRequest;
import com.eletrinho.springmongo.services.UserService;
import com.eletrinho.springmongo.services.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());
        if (PasswordUtil.verifyPassword(loginRequest.getPassword(), user.getPassword())) {
            String token = JwtUtil.generateToken(loginRequest.getUsername());
            LoginResponseDTO response = new LoginResponseDTO(token, "Login successful");
            return ResponseEntity.ok().body(response);
        }
        throw new UnauthorizedException("Dados de login incorretos");
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> insert(@RequestBody User user) {
        UserDTO userDTO = new UserDTO(user);
        user.setPassword(PasswordUtil.getPasswordHash(user.getPassword()));
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }
}
