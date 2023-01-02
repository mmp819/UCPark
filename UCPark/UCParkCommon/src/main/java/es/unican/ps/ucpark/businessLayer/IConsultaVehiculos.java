package es.unican.ps.ucpark.businessLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Vehiculo;

public interface IConsultaVehiculos {

	/**
	 * Consulta vehiculos registrados a nombre de un usuario concreto.
	 * 
	 * @param email Email del usuario a consultar.
	 * 
	 * @throws OperacionNoValida si no existe un usuario con el email indicado.
	 * @return lista de vehiculos registrados a nombre del usuario especificado.
	 */
	public List<Vehiculo> consultaVehiculosRegistrados(String email)
		throws OperacionNoValida;
	
	/**
	 * Consulta un vehiculo registrado a nombre de un usuario concreto.
	 * 
	 * @param email Email del usuario a consultar.
	 * @param matricula Matricula del vehiculo de interes.´
	 * 
	 * @throws OperacionNoValida si no existe un usuario con el email indicado.
	 * @return vehiculo perteneciente al usuario con la matricula especificada.
	 *         null si el usuario no posee un vehiculo con la matricula indicada.
	 */
	public Vehiculo consultaVehiculoRegistrado(String email, String matricula) 
		throws OperacionNoValida;
	
}
