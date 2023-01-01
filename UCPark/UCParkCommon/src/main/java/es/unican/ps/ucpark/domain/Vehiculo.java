package es.unican.ps.ucpark.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Vehiculo implements Serializable {

	@Id
	private String matricula;
	private String marca;
	private String modelo;
	@OneToOne
	@JoinColumn(name="est_vig_fk")
	private Estacionamiento estacionamientoEnVigor;
	@OneToMany(mappedBy="vehiculoEstacionado")
	private List<Estacionamiento> historicoEstacionamientos;
	@OneToMany(mappedBy="vehiculoDenunciado")
	private List<Denuncia> denuncias;
	@ManyToOne
	@JoinColumn(name="prop_fk")
	private Usuario propietario;
	
	/**
	 * Constructor por defecto.
	 */
	public Vehiculo() {
		
	}

	/*
	 * Getters & Setters.
	 */

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Estacionamiento getEstacionamientoEnVigor() {
		return estacionamientoEnVigor;
	}

	public void setEstacionamientoEnVigor(Estacionamiento estacionamientoEnVigor) {
		this.estacionamientoEnVigor = estacionamientoEnVigor;
	}

	public List<Estacionamiento> getHistoricoEstacionamientos() {
		return historicoEstacionamientos;
	}

	public void setHistoricoEstacionamientos(List<Estacionamiento> historicoEstacionamientos) {
		this.historicoEstacionamientos = historicoEstacionamientos;
	}

	public List<Denuncia> getDenuncias() {
		return denuncias;
	}

	public void setDenuncias(List<Denuncia> denuncias) {
		this.denuncias = denuncias;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}   
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
}
