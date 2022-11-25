package es.unican.ps.ucpark.domain;

import java.util.List;

public class Vehiculo {

	private Estacionamiento estacionamientoEnVigor;
	private List<Estacionamiento> historicoEstacionamientos;
	private List<Denuncia> historicoDenuncias;
	private Usuario propietario;
	
	public Vehiculo() {
		
	}
	
	public Estacionamiento getEstacionamientoEnVigor() {
		return this.estacionamientoEnVigor;
	}
	
	public List<Estacionamiento> getHistoricoEstacionammientos() {
		return this.historicoEstacionamientos;
	}
	
	public List<Denuncia> getHistoricoDenuncias() {
		return this.historicoDenuncias;
	}
	
	public Usuario getPropietario() {
		return this.propietario;
	}
}
