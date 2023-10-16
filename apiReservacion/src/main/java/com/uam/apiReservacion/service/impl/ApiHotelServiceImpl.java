package com.uam.apiReservacion.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.uam.apiReservacion.model.Habitacion;
import com.uam.apiReservacion.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.uam.apiReservacion.service.ApiHotelService;

@Service
public class ApiHotelServiceImpl implements ApiHotelService {


    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    public ApiHotelServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
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
	
	public ResponseEntity<?> buscarHabitaciones(Long id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8081/api/Hotel/{id}/habitaciones");
		String apiHotelURL = builder.buildAndExpand(id).toUriString();
        ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class);
		
        System.out.println("Respuesta de ApiHotel: Las habitaciones del hotel" + id + response.getBody());
        
        return response;
	}
    @Override
    public ResponseEntity<?> buscarHabitacionesDisponiblesPorFecha(Long id, Date fechaInicio,Date fechaFin) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8081/api/Hotel/{id}/habitaciones");
        String apiHotelURL = builder.buildAndExpand(id).toUriString();
        ResponseEntity<Habitacion[]> response = restTemplate.getForEntity(apiHotelURL, Habitacion[].class);
        Habitacion[] habitacionesArray = response.getBody();
        List<Habitacion> habitaciones = Arrays.asList(habitacionesArray);

        UriComponentsBuilder builderReserva = UriComponentsBuilder.fromUriString("http://localhost:8082/api/reserva")
                .queryParam("idHotel", id)
                .queryParam("fechaInicio", dateFormat.format(fechaInicio)) // Ajusta la fecha de inicio
                .queryParam("fechaFin", dateFormat.format(fechaFin));
        String apiReservaURL = builderReserva.toUriString();
        System.out.println(apiReservaURL);
        ResponseEntity<Reserva[]> responseRes = restTemplate.getForEntity(apiReservaURL, Reserva[].class);
        Reserva[] ReservaArray = responseRes.getBody();
        List<Reserva> reservas = Arrays.asList(ReservaArray);

        habitaciones.forEach(habitacion -> habitacion.setDisponible(true));

        reservas.stream()
                .map(Reserva::getId_habitacion)
                .forEach(idHabitacion -> habitaciones.stream()
                        .filter(habitacion -> Objects.equals(habitacion.getId(), idHabitacion))
                        .forEach(habitacion -> habitacion.setDisponible(false)));

        return response;
    }
	
	public 	ResponseEntity<?> actualizarHabitacion(Long id_hotel, Long id_habitacion) {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8081/api/Hotel/{id_hotel}/habitaciones/cambiardisponibilidad/{id_habitacion}");
			String apiHotelURL = builder.buildAndExpand(id_hotel, id_habitacion).toUriString();
	        try {
	            restTemplate.put(apiHotelURL, null);

	            return new ResponseEntity<>("Disponibilidad de habitación actualizada con éxito desde el BFF", HttpStatus.OK);
	        } catch (RestClientException e) {
	            return new ResponseEntity<>("Error al actualizar la disponibilidad de habitación desde el BFF", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	}

}