package com.eletrinho.springmongo.resources;

import com.eletrinho.springmongo.config.util.JwtUtil;
import com.eletrinho.springmongo.dto.LoginResponseDTO;
import com.eletrinho.springmongo.entities.User;
import com.eletrinho.springmongo.entities.UserLoginRequest;
import com.eletrinho.springmongo.services.UserService;
import com.eletrinho.springmongo.services.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.getUsername());
        if (user.getPassword().equals(loginRequest.getPassword())) {
            String token = JwtUtil.generateToken(loginRequest.getUsername());
            LoginResponseDTO response = new LoginResponseDTO(token, "Login successful");
            return ResponseEntity.ok().body(response);
        } throw new UnauthorizedException("Dados de login incorretos");
    }
}
