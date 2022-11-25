package es.unican.ps.ucpark.businessLayer;

import es.unican.ps.ucpark.domain.Vehiculo;

public interface IGestionVehiculos {

	/**
	 * Registra un vehiculo a nombre de un usuario.
	 * 
	 * @param vehiculo Vehiculo a registrar.
	 * @param idUsuario ID del usuario a quien asignar el vehiculo.
	 * 
	 * @return vehiculo registrado.
	 */
	public Vehiculo registraVehiculo(Vehiculo vehiculo, int idUsuario);
	
	/**
	 * Elimina un vehiculo del sistema.
	 * 
	 * @param vehiculo Vehiculo a eliminar.
	 * @return vehiculo eliminado.
	 */
	public Vehiculo eliminaVehiculo(Vehiculo vehiculo);
}
