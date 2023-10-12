package com.uam.apiReservacion.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.uam.apiReservacion.model.Hotel;
import com.uam.apiReservacion.service.ApiHotelService;

import io.swagger.models.HttpMethod;

@Service
public class ApiHotelServiceImpl implements ApiHotelService {


    @Autowired
    private RestTemplate restTemplate;
    
    
    
    public ResponseEntity<?> mostrarListaHotelesReducidos() {
        String apiHotelURL = "http://localhost:8081/api/Hotel";
        ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class); 
        
        System.out.println("Respuesta de ApiHotel: Lista de hoteles Reducida: " + response.getBody()); 
        
        return response;
    }
    
    public ResponseEntity<?> mostrarHotelbyId(Long id) {
    	UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8081/api/Hotel/{id}");
		String apiHotelURL = builder.buildAndExpand(id).toUriString();
        ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class);
        
        System.out.println("Respuesta de ApiHotel: Hotel con la id " + id + ": " + response.getBody()); 
        
        return response;
    }
	
    public ResponseEntity<?> mostrarListaCompletaHoteles() {
        String apiHotelURL = "http://localhost:8081/api/Hoteles";
        ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class);
        
        System.out.println("Respuesta de ApiHotel: Lista de hoteles: " + response.getBody()); 
        
        return response;
    }
    
    public ResponseEntity<?> buscarHotelesPorDestino(String direccion) { 
    	UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8081/api/Hotel/destino?direccion={direccion}");
    	String apiHotelURL = builder.buildAndExpand(direccion).toUriString();
        ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class); 
        
        System.out.println("Respuesta de ApiHotel: Hotel con destino en " + direccion + response.getBody()); 
        
        return response;
	}
	
    public ResponseEntity<?> buscarHotelesPorPrecio(BigDecimal precioMinimo, BigDecimal precioMaximo) {
    	UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8081/api/Hotel/precio")
                .queryParam("precioMinimo", precioMinimo)
                .queryParam("precioMaximo", precioMaximo);
		String apiHotelURL = builder.toUriString();
    	ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class); 
		
        System.out.println("Respuesta de ApiHotel: Hotel con rango de precio en " + precioMinimo + "," + precioMaximo + response.getBody()); 
        
        return response;
	}
    
	public ResponseEntity<?> buscarHotelesPorPopularidad(int popularidad) {
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8081/api/Hotel/popularidad/{popularidad}");
		String apiHotelURL = builder.buildAndExpand(popularidad).toUriString();
        ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class); 
		
        System.out.println("Respuesta de ApiHotel: Hotel con popularidad " + popularidad + ": " + response.getBody()); 
        
        return response;
	}
	
	public ResponseEntity<?> buscarFotosHotel(Long id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8081/api/Hotel/{id}/fotos");
		String apiHotelURL = builder.buildAndExpand(id).toUriString();
        ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class);
		
        System.out.println("Respuesta de ApiHotel: Fotos del Hotel" + id + response.getBody()); 
        
        return response;
	}
	
	public ResponseEntity<?> buscarHabitacionesDisponibles(Long id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8081/api/Hotel/{id}/habitaciones/disponibles");
		String apiHotelURL = builder.buildAndExpand(id).toUriString();
        ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class);
		
        System.out.println("Respuesta de ApiHotel: Las habitaciones disponibles del hotel" + id + response.getBody()); 
        
        return response;
	}

    
}