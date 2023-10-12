package com.uam.apiReservacion.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
    private String nombre;

    @NotBlank
    private String contrasena;

    @NotBlank
    private String direccion;

    @NotBlank
    private String telefono;

    @NotBlank
    private String email;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Reserva> reservas;
}
