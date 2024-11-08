package com.eletrinho.springmongo.dto;

import java.io.Serializable;

public class LoginResponseDTO implements Serializable {
    private String message;
    private String token;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getToken_type() {
        return "bearer";
    }
}
