package es.unican.ps.ucpark.businessLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Estacionamiento;

public interface IConsultaEstacionamientos {

	/**
	 * Obtiene el estacionamiento en vigor para un determinado vehiculo.
	 * 
	 * @param matricula Matricula del vehiculo a comprobar.
	 * 
	 * @throws OperacionNoValida si no existe un vehiculo con la matricula
	 *                           indicada.
	 * @return estacionamiento en vigor para el vehiculo especificado.
	 *         null si no existe estacionamiento en vigor.
	 */
	public Estacionamiento estacionamientoEnVigorDeVehiculo(String matricula)
		throws OperacionNoValida;
	
	/**
	 * Obtiene los estacionamientos en vigor para un determinado usuario.
	 * 
	 * @param email Email del usuario a comprobar.
	 * 
	 * @throws OperacionNoValida si no existe un usuario con el email especificado.
	 * @return estacionamientos en vigor para el usuario especificado.
	 */
	public List<Estacionamiento> estacionamientosEnVigorDeUsuario(String email);
	
	/**
	 * Obtiene el historico de estacionamientos para un vehiculo.
	 * 
	 * @param matricula Matricula del vehiculo del que se quiere obtener el historico.
	 * @return historico de estacionamientos para el vehiculo indicado.
	 */
	public List<Estacionamiento> historicoEstacionamientosVehiculo(String matricula);
	
	/**
	 * Obtiene el historico de estacionamientos para un usuario.
	 * 
	 * @param email Email del usuario del que se quiere obtener el historico.
	 * @return historico de estacionamientos para el usuario indicado.
	 */
	public List<Estacionamiento> historicoEstacionamientosUsuario(String email);
}
