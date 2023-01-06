package es.unican.ps.ucpark.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

/**
 * Clase que representa un usuario.
 * 
 * @author Mario Martin Perez
 */
@SuppressWarnings("serial")
@Entity
public class Usuario implements Serializable {

	@Id
	private String email;
	@Column(name="pswrd")
	private String contrasenha;
	@OneToMany(mappedBy="propietario")
	private List<Vehiculo> vehiculos;
	@OneToMany
	@JoinColumn(name="user_fk")
	private List<MedioPago> mediosPago;
	
	/**
	 * Constructor por defecto.
	 */
	public Usuario() {
		
	}
	
	/*
	 * Getters & Setters.
	 */

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenha() {
		return contrasenha;
	}

	public void setContrasenha(String contrasenha) {
		this.contrasenha = contrasenha;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<MedioPago> getMediosPago() {
		return mediosPago;
	}

	public void setMediosPago(List<MedioPago> mediosPago) {
		this.mediosPago = mediosPago;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (other.getEmail().equals(this.email)) {
			return true;
		}
		return false;
	}
	
}
