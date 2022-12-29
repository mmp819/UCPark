package es.unican.ps.ucpark.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Denuncia implements Serializable {
	
	@Id
	@GeneratedValue
	private int idDenuncia;
	private Date fecha;
	private double importe;
	private String causa;
	
	@Column(name="vehDenun")
	private Vehiculo vehiculoDenunciado;
	
	public int getIdDenuncia() {
		return idDenuncia;
	}
	
	public void setIdDenuncia(int idDenuncia) {
		this.idDenuncia = idDenuncia;
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
