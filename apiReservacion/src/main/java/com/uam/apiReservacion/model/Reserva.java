package com.uam.apiReservacion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reserva {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	private Long id_hotel;
	
	private String nombre_hotel;
	
	private Long id_habitacion;
	
	private String numero_habitacion;
	
	private String tipo_habitacion;
	
	private BigDecimal precio_habitacion;
	
	private Date fecha_entrada;
	
	private Date fecha_salida;
	
    @ManyToOne
    private Usuario usuario;


}
