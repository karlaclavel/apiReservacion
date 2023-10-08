package com.uam.apiReservacion.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre ;
    
    private String direccion;
    
    private String foto_portada;
    
    private String telefono ;
    
    private String email;
    
    private String descripcion;
    
    private int visualizaciones;
    
    private int popularidad;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones ;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<FotoHotel> fotos ;


}
