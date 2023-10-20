package com.uam.apiReservacion.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.uam.apiReservacion.model.Hotel;

@Service
public interface ApiHotelService {

    ResponseEntity<?> mostrarListaHotelesReducidos();
    	
    ResponseEntity<?> mostrarHotelbyId(Long id);
    
    ResponseEntity<?> mostrarListaCompletaHoteles();
    	
	ResponseEntity<?> buscarHotelesPorDestino(String direccion);

	ResponseEntity<?> buscarHotelesPorPrecio(BigDecimal precioMinimo, BigDecimal precioMaximo);
	
	ResponseEntity<?> buscarHotelesPorPopularidad(int popularidad);

	ResponseEntity<?> buscarFotosHotel(Long id);
/*
	ResponseEntity<?> buscarHabitacionesDisponibles(Long id);
 */
	
	ResponseEntity<?> actualizarHabitacion(Long id_hotel, Long id_habitacion);

	ResponseEntity<?> buscarHabitaciones(Long id);

	ResponseEntity<?> buscarHabitacionesDisponiblesPorFecha(Long idHotel, Date fechaInicio, Date fechaFin);

	

}
