package com.uam.apiReservacion.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.uam.apiReservacion.exceptions.BadRequestException;
import com.uam.apiReservacion.exceptions.InternalServerErrorException;
import com.uam.apiReservacion.exceptions.UserApiCommunicationException;
import com.uam.apiReservacion.model.Usuario;
import com.uam.apiReservacion.service.ApiUsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


@Api(tags = "Usuarios") // Etiqueta principal para el grupo de operaciones relacionadas con usuarios
@RestController
@RequestMapping("/api/usuarios")
@Service
public class ApiUsuarioServiceImpl implements ApiUsuarioService {

    @Value("${api.usuarios.url}") // Inyecta el valor de la propiedad "api.usuarios.url" aquí
    private String usuariosApiUrl;

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
        System.out.println("Usuario info: " + usuario);
    
        try {
            ResponseEntity<Usuario> response = restTemplate.postForEntity(apiUsuarioURL, usuario, Usuario.class);
    
            if (response.getStatusCode() == HttpStatus.CREATED) {
                Usuario usuarioAgregado = response.getBody();
                System.out.println("Respuesta de ApiUsuario: Usuario agregado: " + usuarioAgregado);
                return ResponseEntity.status(HttpStatus.CREATED).body(usuarioAgregado);
            } else if (response.getStatusCode() == HttpStatus.BAD_REQUEST||response.getStatusCode() == HttpStatus.CONFLICT) {
                System.out.println("Respuesta de ApiUsuario: Error en la solicitud al agregar usuario");
                throw new BadRequestException("Error en la solicitud al agregar usuario");
            } else {
                System.out.println("Respuesta de ApiUsuario: Error al agregar usuario");
                throw new InternalServerErrorException("Error de servidor");
            }
        } catch (RestClientException e) {
            System.out.println("Error de comunicación con la API de usuarios: " + e.getMessage());
            throw new UserApiCommunicationException("Error de comunicación con la API de usuarios", e);
        }
    }
    
    
    public ResponseEntity<?> actualizarUsuario(Long id, Usuario usuarioActualizado) {
    	UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8082/api/usuario/{id}");
		String apiHotelURL = builder.buildAndExpand(id).toUriString();
        ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class);

        System.out.println("Respuesta de ApiUsuario: Usuario actualizado con ID " + id + ": " + response.getBody()); 

        return response;
    }

}
