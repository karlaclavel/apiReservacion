package com.uam.apiReservacion.controller;

import com.uam.apiReservacion.exceptions.BadRequestException;
import com.uam.apiReservacion.exceptions.InternalServerErrorException;
import com.uam.apiReservacion.exceptions.UserApiCommunicationException;
import com.uam.apiReservacion.model.Reserva;
import com.uam.apiReservacion.model.Usuario;
import com.uam.apiReservacion.service.ApiHotelService;
import com.uam.apiReservacion.service.ApiUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiReservacion")
public class ReservacionController {
	
	@Autowired
	private ApiHotelService apiHotelService;
	
	@Autowired
	private ApiUsuarioService apiUsuarioService;

	/*
	 * LLAMADAS PARA API HOTELES
	 */
	
	@GetMapping("/apihotel-listareducido")
    public ResponseEntity<?> mostrarListaHotelesReducidos() {        
        ResponseEntity<?> response = apiHotelService.mostrarListaHotelesReducidos();               
        return response;
    }
	
	@GetMapping("/apihotel-id/{id}")
    public ResponseEntity<?> mostrarHotelbyId(@PathVariable Long id) {
        ResponseEntity<?> response = apiHotelService.mostrarHotelbyId(id);   
        return response;
    }
	
	@GetMapping("/apihotel-listahoteles")
    public ResponseEntity<?> mostrarListaCompletaHoteles() {        
        ResponseEntity<?> response = apiHotelService.mostrarListaCompletaHoteles();        
        return response;
    }
	
	 @GetMapping("/apihotel-destino/{direccion}") 
	    public ResponseEntity<?> buscarHotelesPorDestino(@PathVariable("direccion") String direccion) {
	        ResponseEntity<?> response = apiHotelService.buscarHotelesPorDestino(direccion);
	        return response;
	    }
    
	@GetMapping("/apihotel-precio") /*http://localhost:8080/apiReservacion/apihotel-precio?precioMinimo=100&precioMaximo=200*/
	public ResponseEntity<?> buscarHotelesPorPrecio(
			@RequestParam("precioMinimo") BigDecimal precioMinimo, 
			@RequestParam("precioMaximo") BigDecimal precioMaximo) {	
		ResponseEntity<?> response = apiHotelService.buscarHotelesPorPrecio(precioMinimo, precioMaximo);
        return response;
	}
	
	@GetMapping("/apihotel-popularidad/{popularidad}")
	public ResponseEntity<?> buscarHotelesPorPopularidad(@PathVariable("popularidad") int popularidad) {
		ResponseEntity<?> response = apiHotelService.buscarHotelesPorPopularidad(popularidad);
		return response;
	}
    
	@GetMapping("/apihotel-fotos/{id}")
	public ResponseEntity<?> buscarFotosHotel(@PathVariable("id") Long id) {
		ResponseEntity<?> response = apiHotelService.buscarFotosHotel(id);
		return response;
	}
	
	@GetMapping("/apihotel-habitaciones/{id}")
	public ResponseEntity<?> buscarHabitaciones(@PathVariable("id") Long id) {
		ResponseEntity<?> response = apiHotelService.buscarHabitaciones(id);
		return response;
	}
	@GetMapping("/apihotel-habitaciones")
	public ResponseEntity<?> buscarHabitacionesDisponiblesPorFecha(
			@RequestParam("idHotel") Long idHotel,
			@RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
			@RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
		ResponseEntity<?> response = apiHotelService.buscarHabitacionesDisponiblesPorFecha(idHotel,fechaInicio,fechaFin);
		return response;
	}
	
	@PutMapping("/apihotel-actualizarHabitacion/{id_hotel}/{id_habitacion}")
	public ResponseEntity<?> actualizarHabitacion(@PathVariable("id_hotel") Long id_hotel,@PathVariable("id_habitacion") Long id_habitacion) {
		ResponseEntity<?> response = apiHotelService.actualizarHabitacion(id_hotel, id_habitacion);
		return response;

	}
	
	
	
    /*
	 * LLAMADAS PARA API USUARIOS
	 */
	
	@GetMapping("/apiusuario-lista")
    public ResponseEntity<?> mostrarListaUsuarios() {
        ResponseEntity<?> response = apiUsuarioService.mostrarListaUsuarios();
        return response;
    }
	
	@GetMapping("/apiusuario-id/{id}")
    public ResponseEntity<?> mostrarUsuariobyId(@PathVariable Long id) {
        ResponseEntity<?> response = apiUsuarioService.mostrarUsuariobyId(id);
        return response;
    }
    
	@Operation(summary = "Agregar un nuevo usuario", description = "Permite agregar un nuevo usuario al sistema.",tags={"Usuario"})
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Usuario creado", content = @Content(schema = @Schema(implementation = Usuario.class))),
    	@ApiResponse(responseCode = "400", description = "Los datos del usuario no son válidos",content = @Content(schema = @Schema(implementation = Usuario.class))),
    	@ApiResponse(responseCode = "500", description = "Error interno del servidor",content = @Content(schema = @Schema(implementation = Usuario.class)))})
	@PostMapping("/apiusuario-agregar")
    public ResponseEntity<?> agregarUsuario(@RequestBody Usuario usuario) {
    try {
        return apiUsuarioService.agregarUsuario(usuario);
    } catch (BadRequestException e) {
        // Manejar la excepción BadRequestException aquí
        return ResponseEntity.badRequest().body(e.getMessage());
    } catch (InternalServerErrorException e) {
        // Manejar la excepción InternalServerErrorException aquí
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    } catch (UserApiCommunicationException e) {
        // Manejar la excepción UserApiCommunicationException aquí
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la informacion del usuario");
    }
}
	
	@PutMapping("/apiusuario-actualizar/{id}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
		ResponseEntity<?> response = apiUsuarioService.actualizarUsuario(id, usuarioActualizado);
		return response;
	}
	
	@PostMapping("/apiusuario-reserva")
    public ResponseEntity<?> agregarReserva(@RequestBody Reserva reserva) {
        ResponseEntity<?> response = apiUsuarioService.agregarReserva(reserva);
    	return response;
    }

}
