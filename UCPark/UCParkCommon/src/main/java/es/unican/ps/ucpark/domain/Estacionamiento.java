package es.unican.ps.ucpark.domain;

import java.time.LocalDateTime;

public class Estacionamiento {

	private double importe;
	private int minutos;
	private LocalDateTime horaInicio;
	private Vehiculo vehiculoEstacionado;
	
	public Estacionamiento(double importe, int minutos, LocalDateTime horaInicio,
			Vehiculo vehiculoEstacionado) {
		this.importe = importe;
		this.minutos = minutos;
		this.horaInicio = horaInicio;
		this.vehiculoEstacionado = vehiculoEstacionado;
	}

	public double getImporte() {
		return this.importe;
	}
	
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	public int getMinutos() {
		return this.minutos;
	}
	
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
	
	public LocalDateTime getHoraInicio() {
		return this.horaInicio;
	}
	
	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public Vehiculo getVehiculoEstacionado() {
		return this.vehiculoEstacionado;
	}
	
	public void setVehiculoEstacionado(Vehiculo vehiculoEstacionado) {
		this.vehiculoEstacionado = vehiculoEstacionado;
	}
}
