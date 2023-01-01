package es.unican.ps.ucpark.businessLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Vehiculo;

public interface IConsultaVehiculos {

	/**
	 * Consulta vehiculos registrados a nombre de un usuario concreto.
	 * 
	 * @param email Email del usuario a consultar.
	 * @return lista de vehiculos registrados a nombre del usuario especificado.
	 */
	public List<Vehiculo> consultaVehiculosRegistrados(String email);
	
}
