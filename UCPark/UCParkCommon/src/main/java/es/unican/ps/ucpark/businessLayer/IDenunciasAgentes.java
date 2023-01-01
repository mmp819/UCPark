package es.unican.ps.ucpark.businessLayer;

import es.unican.ps.ucpark.domain.Denuncia;
import es.unican.ps.ucpark.domain.Estacionamiento;

public interface IDenunciasAgentes {

	/**
	 * Retorna el ultimo estacionamiento realizado por un vehiculo.
	 * 
	 * @param matricula Matricula del vehiculo.
	 * 
	 * @throws OperacionNoValida si no existe un vehiculo con la matricula indicada
	 * @return ultimo estacionamiento realizado por el vehiculo.
	 *         null si no hay estacionamientos registrados para el vehiculo.
	 */
	public Estacionamiento obtieneUltimoEstacionamientoVehiculo(String matricula)
		throws OperacionNoValida;
	
	
	/**
	 * Registra una denuncia y la asocia a un vehiculo.
	 * 
	 * @param denuncia Denuncia a registrar.
	 * @parma matricula Matricula del vehiculo infractor.
	 * 
	 * @throws OperacionNoValida si no existe un vehiculo con la matricula indicada.
	 * @return denuncia registrada.
	 *         null si la denuncia a registrar ya existia en el vehiculo.
	 */
	public Denuncia registraDenuncia(Denuncia denuncia, String matricula)
		throws OperacionNoValida;
}
