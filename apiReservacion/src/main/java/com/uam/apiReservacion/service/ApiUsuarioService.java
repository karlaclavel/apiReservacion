package com.uam.apiReservacion.service;

import com.uam.apiReservacion.model.LoginRequest;
import com.uam.apiReservacion.model.Reserva;
import com.uam.apiReservacion.model.Usuario;
import com.uam.apiReservacion.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ApiUsuarioService {
	
	ResponseEntity<?> mostrarListaUsuarios();

	ResponseEntity<?> mostrarUsuariobyId(Long id);

	ResponseEntity<?> agregarUsuario(Usuario usuario);

	ResponseEntity<?> actualizarUsuario(Long id, Usuario usuarioActualizado);

	ResponseEntity<?> agregarReserva(Long id, Reserva reserva);

	ResponseEntity<?> buscarUsuarioPorEmailYContrasena(String email, String contrasena);

	ResponseEntity<?> autentificarUsuario(LoginRequest loginRequest);

	ResponseEntity<?> mostrarUsuariobyEmail(String email);


}
