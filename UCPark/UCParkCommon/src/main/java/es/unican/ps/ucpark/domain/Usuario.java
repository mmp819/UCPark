package es.unican.ps.ucpark.domain;

import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

public class Usuario {

	private int id;
	private String contrasenha;
	@OneToMany(mappedBy="propietario")
	private List<Vehiculo> vehiculos;
	@OneToMany
	@JoinColumn(name="user_fk")
	private List<MedioPago> mediosPago;
	private String email;
	
	public Usuario() {
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getContrasenha() {
		return this.contrasenha;
	}
	
	public List<Vehiculo> getVehiculos() {
		return this.vehiculos;
	}

	public List<MedioPago> getMediosPago() {
		return mediosPago;
	}

	public void setMediosPago(List<MedioPago> mediosPago) {
		this.mediosPago = mediosPago;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
