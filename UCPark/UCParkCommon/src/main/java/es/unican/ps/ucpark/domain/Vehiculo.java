package es.unican.ps.ucpark.domain;

import java.util.List;

public class Vehiculo {

	private Estacionamiento estacionamientoEnVigor;
	private List<Estacionamiento> historicoEstacionamientos;
	private List<Denuncia> historicoDenuncias;
	private Usuario propietario;
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
	
}
