package es.unican.ps.ucpark.daoLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Vehiculo;

public interface IVehiculosDAO {

	/**
	 * Crea un nuevo vehiculo.
	 * 
	 * @param vehiculo Vehiculo nuevo a registrar.
	 * @return vehiculo nuevo creado.
	 */
	public Vehiculo creaVehiculo(Vehiculo vehiculo);
	
	/**
	 * Obtiene los vehiculos registrados.
	 * 
	 * @return lista de vehiculos registrados.
	 */
	public List<Vehiculo> vehiuclos();
	
	/**
	 * Modifica un vehiculo concreto.
	 * 
	 * @param vehiculo Vehiculo modificado.
	 * @return vehiculo modificado.
	 */
	public Vehiculo modificaVehiculo(Vehiculo vehiculo);
	
	/**
	 * Elimina un vehiculo concreto.
	 * 
	 * @param vehiculo Vehiculo a eliminar.
	 * @return vehiculo eliminado.
	 */
	public Vehiculo eliminaVehiculo(Vehiculo vehiculo);
	
	/**
	 * Obtiene un vehiculo concreto en base a su matricula.
	 * 
	 * @param matricula Matricula del vehiculo a obtener.
	 * @return vehiculo cuya matricula se corresponde con la indicada.
	 *         null si no existe ningun vehiculo con la matricula especificada.
	 */
	public Vehiculo vehiculoPorMatricula(String matricula);

}
