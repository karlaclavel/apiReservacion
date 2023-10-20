package com.uam.apiReservacion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

	private Long id;

	private Long id_hotel;

	private Long id_habitacion;

	private Date fecha_entrada;

	private Date fecha_salida;

	private Long usuarioId;
}
