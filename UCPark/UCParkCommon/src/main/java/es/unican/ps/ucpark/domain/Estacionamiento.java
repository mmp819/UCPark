package es.unican.ps.ucpark.domain;

import java.time.LocalDateTime;

public class Estacionamiento {

	private double importe;
	private int minutos;
	private LocalDateTime horaInicio;
	
	public Estacionamiento(double importe, int minutos, LocalDateTime horaInicio) {
		this.importe = importe;
		this.minutos = minutos;
		this.horaInicio = horaInicio;
	}
}
