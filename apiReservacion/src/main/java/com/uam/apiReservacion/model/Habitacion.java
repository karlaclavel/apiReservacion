package com.uam.apiReservacion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habitacion {
	
    private Long id;
    
    private String numero ;
    
    private String tipo;
    
    private BigDecimal precio ;
    
    private Boolean disponible;

    private String fotoPortada;

}

