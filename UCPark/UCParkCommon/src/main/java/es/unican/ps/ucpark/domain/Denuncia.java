package es.unican.ps.ucpark.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
	
	public Denuncia() {
		
	}
	
	public int getIdDenuncia() {
		return id;
	}
	
	public void setIdDenuncia(int id) {
		this.id = id;
	}
	
	public Date fecha() {
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
		return this.vehiculoDenunciado;
	}
	
	public void setVehiculoDenunciado(Vehiculo vehiculoDenunciado) {
		this.vehiculoDenunciado = vehiculoDenunciado;
	}
}
