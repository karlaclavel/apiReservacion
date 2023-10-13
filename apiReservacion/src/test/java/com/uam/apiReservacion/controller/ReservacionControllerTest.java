package com.uam.apiReservacion.controller;

import com.uam.apiReservacion.controller.ReservacionController;
import com.uam.apiReservacion.exceptions.BadRequestException;
import com.uam.apiReservacion.exceptions.InternalServerErrorException;
import com.uam.apiReservacion.exceptions.UserApiCommunicationException;
import com.uam.apiReservacion.model.Usuario;
import com.uam.apiReservacion.service.ApiUsuarioService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.Answer;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservacionControllerTest {

    @Mock
    private ApiUsuarioService apiUsuarioService;

    @InjectMocks
    private ReservacionController reservacionController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAgregarUsuarioSuccess() {
        Usuario usuario = new Usuario();
        usuario.setNombre("John");
        usuario.setContrasena("Password");
        usuario.setDireccion("123 Main St");
        usuario.setTelefono("1234567890");
        usuario.setEmail("john@example.com");
    
        ResponseEntity<?> successResponse = new ResponseEntity<>(usuario, HttpStatus.CREATED);
    
        doReturn(successResponse).when(apiUsuarioService).agregarUsuario(any(Usuario.class));
    
        ResponseEntity<?> response = reservacionController.agregarUsuario(usuario);
    
        verify(apiUsuarioService, times(1)).agregarUsuario(any(Usuario.class));
        verifyNoMoreInteractions(apiUsuarioService);
    
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

        
    @Test
    public void testAgregarUsuarioBadRequest() {
            Usuario usuario = new Usuario(); // Usuario sin datos requeridos
        
            doThrow(BadRequestException.class).when(apiUsuarioService).agregarUsuario(any(Usuario.class));
        
            ResponseEntity<?> response = reservacionController.agregarUsuario(usuario);
        
            verify(apiUsuarioService, times(1)).agregarUsuario(any(Usuario.class));
            verifyNoMoreInteractions(apiUsuarioService);
        
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertEquals("Error en la informacion del usuario", response.getBody()); 
    }
        

    @Test
    public void testAgregarUsuarioInternalServerError() {
        Usuario usuario = new Usuario();
        usuario.setNombre("John");
        usuario.setContrasena("Password");
        usuario.setDireccion("123 Main St");
        usuario.setTelefono("1234567890");
        usuario.setEmail("john@example.com");
    
        doThrow(InternalServerErrorException.class).when(apiUsuarioService).agregarUsuario(any(Usuario.class));
    
        ResponseEntity<?> response = reservacionController.agregarUsuario(usuario);
    
        verify(apiUsuarioService, times(1)).agregarUsuario(any(Usuario.class));
        verifyNoMoreInteractions(apiUsuarioService);
    
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error interno del servidor", response.getBody()); 
    }

    @Test
    public void testAgregarUsuarioCommunicationException() {
        Usuario usuario = new Usuario();
        usuario.setNombre("John");
        usuario.setContrasena("Password");
        usuario.setDireccion("123 Main St");
        usuario.setTelefono("1234567890");
        usuario.setEmail("john@example.com");
    
        doThrow(UserApiCommunicationException.class).when(apiUsuarioService).agregarUsuario(any(Usuario.class));
    
        ResponseEntity<?> response = reservacionController.agregarUsuario(usuario);
    
        verify(apiUsuarioService, times(1)).agregarUsuario(any(Usuario.class));
        verifyNoMoreInteractions(apiUsuarioService);
    
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Error en la informacion del usuario", response.getBody()); 
    }
    
}

