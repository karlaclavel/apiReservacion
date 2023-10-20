package com.uam.apiReservacion.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    private Long id;

    private String nombre;

    private String contrasena;

    private String direccion;

    private String telefono;

    private String email;

	private List<Reserva> reservas;
}
