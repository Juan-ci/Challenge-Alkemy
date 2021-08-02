package com.example.DisneyAPI.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.User;

@RestController
public class ControladorInicio {
    
    @GetMapping("/")
    public String inicio(@AuthenticationPrincipal User user) {
        String message = "App corriendo/nUsuario conectado: "+ user.getUsername();
        return message;
    }
}
