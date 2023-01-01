package es.unican.ps.ucpark.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Clase que representa una denuncia a un vehiculo.
 * 
 * @author Mario Martin Perez.
 */
@SuppressWarnings("serial")
@Entity
public class Denuncia implements Serializable {
	
	@Id
	@GeneratedValue
	private int id;
	private Date fecha;
	private double importe;
	private String causa;
	@ManyToOne
	@JoinColumn(name="vclo_fk")
	private Vehiculo vehiculoDenunciado;
	
	/**
	 * Constructor por defecto.
	 */
	public Denuncia() {
		
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	public Vehiculo getVehiculoDenunciado() {
		return vehiculoDenunciado;
	}

	public void setVehiculoDenunciado(Vehiculo vehiculoDenunciado) {
		this.vehiculoDenunciado = vehiculoDenunciado;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Denuncia other = (Denuncia) obj;
		if (other.fecha.equals(this.fecha) && 
				other.getVehiculoDenunciado().equals(this.vehiculoDenunciado)) {
			return true;
		}
		return false;
	} 
}
