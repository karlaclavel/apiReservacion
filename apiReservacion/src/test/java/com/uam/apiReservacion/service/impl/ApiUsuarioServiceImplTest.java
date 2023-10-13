package com.uam.apiReservacion.service.impl;

import com.uam.apiReservacion.exceptions.BadRequestException;
import com.uam.apiReservacion.exceptions.InternalServerErrorException;
import com.uam.apiReservacion.exceptions.UserApiCommunicationException;
import com.uam.apiReservacion.model.Usuario;
import com.uam.apiReservacion.service.ApiUsuarioService;
import com.uam.apiReservacion.service.impl.ApiUsuarioServiceImpl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApiUsuarioServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ApiUsuarioServiceImpl apiUsuarioService;

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

        ResponseEntity<Object> successResponse = new ResponseEntity<>(usuario, HttpStatus.CREATED);

        when(restTemplate.postForEntity(anyString(), any(), any()))
                .thenReturn(successResponse);

        ResponseEntity<?> response = apiUsuarioService.agregarUsuario(usuario);

        verify(restTemplate, times(1)).postForEntity(anyString(), any(), any());
        verifyNoMoreInteractions(restTemplate);
        
        // Use assertThat to assert the response
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(usuario, response.getBody());
    }

    @Test
    public void testAgregarUsuarioBadRequest() {
        Usuario usuario = new Usuario(); // Usuario sin datos requeridos

        ResponseEntity<Object> badRequestResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        when(restTemplate.postForEntity(anyString(), any(), any()))
                .thenReturn(badRequestResponse);

        // Use assertThrows to assert the exception and its type
        BadRequestException exception = assertThrows(BadRequestException.class,
            () -> apiUsuarioService.agregarUsuario(usuario)
        );
        
        // Use assertThat to further assert the exception
        assertThat(exception, instanceOf(BadRequestException.class));
    }

    @Test
    public void testAgregarUsuarioInternalServerError() {
        Usuario usuario = new Usuario();
        usuario.setNombre("John");
        usuario.setContrasena("Password");
        usuario.setDireccion("123 Main St");
        usuario.setTelefono("1234567890");
        usuario.setEmail("john@example.com");

        ResponseEntity<Object> errorResponse = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        when(restTemplate.postForEntity(anyString(), any(), any()))
                .thenReturn(errorResponse);

        // Use assertThrows to assert the exception and its type
        InternalServerErrorException exception = assertThrows(InternalServerErrorException.class,
            () -> apiUsuarioService.agregarUsuario(usuario)
        );
        
        // Use assertThat to further assert the exception
        assertThat(exception, instanceOf(InternalServerErrorException.class));
    }

    @Test
    public void testAgregarUsuarioRestClientException() {
        Usuario usuario = new Usuario();
        usuario.setNombre("John");
        usuario.setContrasena("Password");
        usuario.setDireccion("123 Main St");
        usuario.setTelefono("1234567890");
        usuario.setEmail("john@example.com");

        when(restTemplate.postForEntity(anyString(), any(), any()))
                .thenThrow(RestClientException.class);

        // Use assertThrows to assert the exception and its type
        UserApiCommunicationException exception = assertThrows(UserApiCommunicationException.class,
            () -> apiUsuarioService.agregarUsuario(usuario)
        );
        
        // Use assertThat to further assert the exception
        assertThat(exception, instanceOf(UserApiCommunicationException.class));
    }
}

