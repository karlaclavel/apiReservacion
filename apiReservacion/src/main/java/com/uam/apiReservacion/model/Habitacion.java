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
public class Habitacion {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numero ;
    
    private String tipo;
    
    private BigDecimal precio ;
    
    private Boolean disponible;
    
    private String fotoPortada;

    @ManyToOne
    private Hotel hotel;

}

