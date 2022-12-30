package es.unican.ps.ucpark.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Vehiculo {

	@OneToOne
	@JoinColumn(name="est_vig_fk")
	private Estacionamiento estacionamientoEnVigor;
	@OneToMany(mappedBy="vehiculoEstacionado")
	private List<Estacionamiento> historicoEstacionamientos;
	@OneToMany(mappedBy="vehiculoDenunciado")
	private List<Denuncia> historicoDenuncias;
	@ManyToOne
	@JoinColumn(name="prop_fk")
	private Usuario propietario;
	@Id
	@GeneratedValue
	private int id;
	private String matricula;
	
	public Vehiculo() {
		
	}
	
	public Estacionamiento getEstacionamientoEnVigor() {
		return this.estacionamientoEnVigor;
	}
	
	public void setEstacionamientoEnVigor(Estacionamiento estacionamiento) {
		this.estacionamientoEnVigor = estacionamiento;
	}
	
	public List<Estacionamiento> getHistoricoEstacionamientos() {
		return this.historicoEstacionamientos;
	}
	
	public List<Denuncia> getHistoricoDenuncias() {
		return this.historicoDenuncias;
	}
	
	public Usuario getPropietario() {
		return this.propietario;
	}
	
	public String getMatricula() {
		return this.matricula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
