package es.unican.ps.ucpark.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;

@Entity
public class Estacionamiento {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
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
		} else if (other.getId() == id && other.getImporte() == importe
				&& other.getHoraInicio().equals(horaInicio) && other.minutos 
				== minutos && other.getVehiculoEstacionado() == vehiculoEstacionado) { 
			// Redundante. Teoricamente, valdria con el ID
			return true;
		}
		return false;
	}    
}
