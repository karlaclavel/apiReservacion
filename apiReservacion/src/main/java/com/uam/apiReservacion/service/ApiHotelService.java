package com.uam.apiReservacion.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ApiHotelService {

    ResponseEntity<?> mostrarListaHotelesReducidos();
    	
    ResponseEntity<?> mostrarHotelbyId(Long id);
    
    ResponseEntity<?> mostrarListaCompletaHoteles();
    	
	ResponseEntity<?> buscarHotelesPorDestino(String direccion);

	ResponseEntity<?> buscarHotelesPorPrecio(BigDecimal precioMinimo, BigDecimal precioMaximo);
	
	ResponseEntity<?> buscarHotelesPorPopularidad(int popularidad);

	ResponseEntity<?> buscarFotosHotel(Long id);

	ResponseEntity<?> buscarHabitaciones(Long id);

	ResponseEntity<?> actualizarHabitacion(Long id_hotel, Long id_habitacion);
	ResponseEntity<?> buscarHabitacionesDisponiblesPorFecha(Long id, Date fechaInicio, Date fechaFin) ;
}
