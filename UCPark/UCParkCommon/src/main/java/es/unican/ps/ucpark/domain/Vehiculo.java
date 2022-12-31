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

	public List<Denuncia> getHistoricoDenuncias() {
		return historicoDenuncias;
	}

	public void setHistoricoDenuncias(List<Denuncia> historicoDenuncias) {
		this.historicoDenuncias = historicoDenuncias;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
