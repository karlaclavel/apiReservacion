package com.uam.apiReservacion.controller;

import com.uam.apiReservacion.model.Hotel;
import com.uam.apiReservacion.model.Usuario;
import com.uam.apiReservacion.service.ApiHotelService;
import com.uam.apiReservacion.service.ApiUsuarioService;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.client.RestTemplate;

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
	public ResponseEntity<?> buscarHabitacionesDisponibles(@PathVariable("id") Long id) {
		ResponseEntity<?> response = apiHotelService.buscarHabitacionesDisponibles(id);
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
    
	@PostMapping("/apiusuario-agregar")
    public ResponseEntity<?> agregarUsuario(@RequestBody Usuario usuario) {
        ResponseEntity<?> response = apiUsuarioService.agregarUsuario(usuario);
    	return response;
    }
	
	@PutMapping("/apiusuario-actualizar/{id}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
		ResponseEntity<?> response = apiUsuarioService.actualizarUsuario(id, usuarioActualizado);
		return response;
	}

    
    
  
	
    
}
