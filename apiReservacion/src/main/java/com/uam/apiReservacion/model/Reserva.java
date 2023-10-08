package com.uam.apiReservacion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reserva {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private Long numero_reserva;
	
	private Long id_habitacion;
	
	private String numero_habitacion;
	
	private String tipo_habitacion;
	
	private BigDecimal precio_habitacion;
	
    @ManyToOne
    private Usuario usuario;


}
