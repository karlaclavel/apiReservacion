package com.uam.apiReservacion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.uam.apiReservacion.model.Usuario;
import com.uam.apiReservacion.service.ApiUsuarioService;


@Service
public class ApiUsuarioServiceImpl implements ApiUsuarioService {

    @Autowired
    private RestTemplate restTemplate;
    
    public ResponseEntity<?> mostrarListaUsuarios() {
        String apiUsuarioURL = "http://localhost:8082/api/usuarios";
    	ResponseEntity<?> response = restTemplate.getForEntity(apiUsuarioURL, String.class);
    	
    	System.out.println("Respuesta de la ApiUsuario: Lista de Usuarios: " + response.getBody()); 
    	
    	return response;
    }
    
    
    public ResponseEntity<?> mostrarUsuariobyId(Long id) {
    	UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8082/api/usuario/{id}");
		String apiUsuarioURL = builder.buildAndExpand(id).toUriString();
        ResponseEntity<?> response = restTemplate.getForEntity(apiUsuarioURL, String.class);
    	
        System.out.println("Respuesta de ApiUsuario: Usuario con la id " + id + ": " + response.getBody()); 
        
        return response;
    }

    
    public ResponseEntity<?> agregarUsuario(Usuario usuario) {
    	String apiUsuarioURL = "http://localhost:8082/api/usuario";
        ResponseEntity<?> response = restTemplate.postForEntity(apiUsuarioURL, usuario, String.class);
        
        System.out.println("Respuesta de ApiUsuario: Usuario con la id " + usuario + ": " + response.getBody()); 
        
        return response;
    	
    }
    
    public ResponseEntity<?> actualizarUsuario(Long id, Usuario usuarioActualizado) {
    	UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8082/api/usuario/{id}");
		String apiHotelURL = builder.buildAndExpand(id).toUriString();
        ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class);

        System.out.println("Respuesta de ApiUsuario: Usuario actualizado con ID " + id + ": " + response.getBody()); 

        return response;
    }

    public ResponseEntity<?> buscarUsuarioPorEmailYContrasena(String email, String contrasena) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8082/api/usuario/login");
        builder.queryParam("email", email);
        builder.queryParam("contrasena", contrasena);
        String apiUsuarioURL = builder.toUriString();
        ResponseEntity<?> response = restTemplate.getForEntity(apiUsuarioURL, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response;
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
