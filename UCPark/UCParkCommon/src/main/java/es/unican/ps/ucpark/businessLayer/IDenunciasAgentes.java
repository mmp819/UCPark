package es.unican.ps.ucpark.businessLayer;

import es.unican.ps.ucpark.domain.Denuncia;
import es.unican.ps.ucpark.domain.Estacionamiento;

public interface IDenunciasAgentes {

	/**
	 * Obtiene el ultimo estacionamiento realizado por un vehiculo.
	 * 
	 * @param matricula Matricula del vehiculo de interes.
	 * @return ultimo estacionamiento realizado por el vehiculo indicado.
	 */
	public Estacionamiento obtieneUltimoEstacionamientoVehiculo(String matricula);
	
	
	/**
	 * Registra una denuncia concreta para un vehiculo determinado.
	 * 
	 * @param denuncia Denuncia a registrar
	 * @param matricula Matricula del vehiculo denunciado
	 * @return denuncia que se ha asignado a un vehiculo.
	 */
	public Denuncia registraDenuncia(Denuncia denuncia, String matricula);
}
