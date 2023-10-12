package com.uam.apiReservacion.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	
    @JsonProperty("nombre")
	@NotBlank
    private String nombre;

    @JsonProperty("contrasena")
    @NotBlank
    private String contrasena;

    @JsonProperty("direccion")
    @NotBlank
    private String direccion;

    @JsonProperty("telefono")
    @NotBlank
    private String telefono;

    @JsonProperty("email")
    @NotBlank
    private String email;
    
    @JsonProperty("reservas")
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Reserva> reservas;
}
