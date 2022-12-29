package es.unican.ps.ucpark.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Estacionamiento {

	@Id
	@GeneratedValue
	private int id;
	private double importe;
	private int minutos;
	private LocalDateTime horaInicio;
	@ManyToOne
	@JoinColumn(name="vclo_fk")
	private Vehiculo vehiculoEstacionado;
	
	public Estacionamiento() {
		
	}
	
	public Estacionamiento(double importe, int minutos, LocalDateTime horaInicio,
			Vehiculo vehiculoEstacionado) {
		this.importe = importe;
		this.minutos = minutos;
		this.horaInicio = horaInicio;
		this.vehiculoEstacionado = vehiculoEstacionado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
