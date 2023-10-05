package com.uam.apiReservacion.controller;

import com.uam.apiReservacion.model.UsuarioNuevo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/apiReservacion")
public class ReservacionController {
	
	@RequestMapping("/verificacion")
	public String Ejecucion() {
		return "La api principal (apiReservacion) esta recibiendo y enviando datos correctamente";
	} 

	@Autowired
    private RestTemplate restTemplate;
	
	public ReservacionController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@PostMapping("/llamar-api-usuariosCrear")
    public ResponseEntity<?> agregarApiUsuriosCrear(@RequestBody UsuarioNuevo usuarioNuevo) {
        String apiUsuariosURL = "http://localhost:8082/api/usuarios";
        ResponseEntity<?> response = restTemplate.postForEntity(apiUsuariosURL, usuarioNuevo, String.class);       
    	System.out.println("Respuesta de la API externa: " + response.getBody()); 

    	return response;
    }

    @GetMapping("/llamar-api-usuariosLista")
    public ResponseEntity<?> llamarApiUsuariosLista() {
        String apiUsuariosURL = "http://localhost:8082/api/usuarios";
        ResponseEntity<?> response = restTemplate.getForEntity(apiUsuariosURL, String.class);
    	System.out.println("Respuesta de la API externa: Lista de Usuarios: " + response.getBody()); 

        return response;
    }
    
    @GetMapping("/llamar-api-usuariosId")
    public ResponseEntity<?> llamarApiUsuariosId(@PathVariable Long id) {
        String apiUsuariosURL = "http://localhost:8082/api/usuarios/{id}";
        ResponseEntity<?> response = restTemplate.getForEntity(apiUsuariosURL, String.class);
    	System.out.println("Respuesta de la API externa: Usuarios con la id " + id + ": " + response.getBody()); 
    	
    	return response;
    }
    
    @GetMapping("/llamar-api-hotelReducido")
    public ResponseEntity<?> llamarApiHotelReducido() {
        String apiHotelURL = "http://localhost:8081/api/Hotel";
        ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class);       
        System.out.println("Respuesta de la API externa: Lista de hoteles Reducida: " + response.getBody()); 
        
        return response;
    }
	
    @GetMapping("/llamar-api-hotelId")
    public ResponseEntity<?> llamarApiHotelId(@PathVariable Long id) {
        String apiHotelURL = "http://localhost:8081/api/Hotel/{id}";
        ResponseEntity<?> response = restTemplate.getForEntity(apiHotelURL, String.class);       
        System.out.println("Repsuesta de la api externa: Hotel con la id " + id + ": " + response.getBody()); 
        
        return response;
    }
	
}
