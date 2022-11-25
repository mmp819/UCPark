package es.unican.ps.ucpark.domain;

import java.util.List;

public class Usuario {

	private int id;
	private String contrasenha;
	private List<Vehiculo> vehiculos;
	
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
}
