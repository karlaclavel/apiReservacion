package com.uam.apiReservacion.model;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;

public class UsuarioNuevo {
	@NotBlank
    @ApiModelProperty(notes = "Nombre de usuario", required = true)
    private String nombre;

    @NotBlank
    @ApiModelProperty(notes = "Contraseña de usuario", required = true)
    private String contraseña;

    @NotBlank
    @ApiModelProperty(notes = "Dirección de usuario", required = true)
    private String direccion;

    @NotBlank
    @ApiModelProperty(notes = "Teléfono de usuario", required = true)
    private String telefono;

    @NotBlank
    private String email;
    
  //@OneToMany(mausuariosppedBy = "usuario", cascade = CascadeType.ALL)
    //private <Reservaciones> reservaciones;
}
