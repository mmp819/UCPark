package es.unican.ps.ucpark.businessLayer;

import es.unican.ps.ucpark.domain.Vehiculo;

public interface IGestionVehiculos {

	/**
	 * Registra un nuevo vehiculo a nombre de un usuario determinado.
	 * 
	 * @param vehiculo Vehiculo a registrar.
	 * @param email Email del usuario a quien asociar el vehiculo.
	 * 
	 * @throws OperacionNoValida si no existe un usuario para el email indicado.
	 * @return vehiculo registrado.
	 */
	public Vehiculo registraVehiculo(Vehiculo vehiculo, String email)
		throws OperacionNoValida;
	
	/**
	 * Elimina un vehiculo del sistema.
	 * 
	 * @param vehiculo Vehiculo a eliminar.
	 * @return vehiculo eliminado.
	 *         null si no existe el vehiculo indicado.
	 */
	public Vehiculo eliminaVehiculo(Vehiculo vehiculo);
}
