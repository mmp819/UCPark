package es.unican.ps.ucpark.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;

/**
 * Clase que representa un estacionamiento.
 * 
 * @author Mario Martin Perez
 */
@SuppressWarnings("serial")
@Entity
public class Estacionamiento implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private double importe;
	private int minutos;
	private LocalDateTime horaInicio;
	@ManyToOne
	@JoinColumn(name="vclo_fk")
	private Vehiculo vehiculoEstacionado;
	
	/**
	 * Constructor por defecto.
	 */
	public Estacionamiento() {
		
	}
	
	/**
	 * Constructor para facilitar creaciones de estacionamientos sin ID asignado
	 * por el sistema de persistencia.
	 * 
	 * @param importe Importe del estacionamiento en euros.
 	 * @param minutos Minutos del estacionamiento (< 120 minutos).
	 * @param horaInicio Hora de inicio del estacionamiento.
	 * @param vehiculoEstacionado Vehiculo estacionado.
	 */
	public Estacionamiento(double importe, int minutos, LocalDateTime horaInicio,
			Vehiculo vehiculoEstacionado) {
		this.importe = importe;
		this.minutos = minutos;
		this.horaInicio = horaInicio;
		this.vehiculoEstacionado = vehiculoEstacionado;
	}

	/*
	 * Getters & Setters.
	 */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Vehiculo getVehiculoEstacionado() {
		return vehiculoEstacionado;
	}

	public void setVehiculoEstacionado(Vehiculo vehiculoEstacionado) {
		this.vehiculoEstacionado = vehiculoEstacionado;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estacionamiento other = (Estacionamiento) obj;
		if (other.getVehiculoEstacionado() == null || other.getHoraInicio() == null) {
				return false;
		} else if (other.getHoraInicio().equals(horaInicio) && 
				other.getVehiculoEstacionado() == vehiculoEstacionado) { 
			return true;
		}
		return false;
	}    
}
