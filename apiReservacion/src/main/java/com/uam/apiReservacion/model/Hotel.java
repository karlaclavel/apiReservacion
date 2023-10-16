package com.uam.apiReservacion.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private Long id;
    
    private String nombre ;
    
    private String direccion;
    
    private String foto_portada;
    
    private String telefono ;
    
    private String email;
    
    private String descripcion;
    
    private int visualizaciones;
    
    private int popularidad;

    private List<Habitacion> habitaciones ;

    private List<FotoHotel> fotos ;

}
