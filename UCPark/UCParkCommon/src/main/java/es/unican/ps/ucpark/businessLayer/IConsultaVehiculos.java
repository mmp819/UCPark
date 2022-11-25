package es.unican.ps.ucpark.businessLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Vehiculo;

public interface IConsultaVehiculos {

	/**
	 * Obtiene los vehiculos registrados para un usuario determinado.
	 * 
	 * @param idUsuario ID del usuario de interes.
	 * @return vehiculos registrados para el usuario cuyo ID se pasa como parametro.
	 */
	public List<Vehiculo> consultaVehiculosRegistrados(int idUsuario);
	
}
