package com.uam.apiReservacion.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.uam.apiReservacion.model.LoginRequest;
import com.uam.apiReservacion.model.Usuario;
import com.uam.apiReservacion.service.ApiUsuarioService;

@Controller
public class AuthController {
	
	@Autowired
	private ApiUsuarioService usuarioService;
	
	/*
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        ResponseEntity<?> response = usuarioS.buscarUsuarioPorEmailYContrasena(usuario.getEmail(), usuario.getContraseña());

        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok("Autenticación exitosa");
        } else {
            return ResponseEntity.badRequest().body("Credenciales incorrectas");
        }
    }
    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; 
    }
    */
	
}