package com.uam.apiReservacion.service;

import com.uam.apiReservacion.model.Reserva;
import com.uam.apiReservacion.model.Usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ApiUsuarioService {
	
	ResponseEntity<?> mostrarListaUsuarios();

	ResponseEntity<?> mostrarUsuariobyId(Long id);

	ResponseEntity<?> agregarUsuario(Usuario usuario);

	ResponseEntity<?> actualizarUsuario(Long id, Usuario usuarioActualizado);

	ResponseEntity<?> agregarReserva(Reserva reserva);

	
	 ResponseEntity<?> buscarUsuarioPorEmailYContrasena(String email, String contrasena);
}
