package es.unican.ps.ucpark.daoLayer;

import java.util.List;

import es.unican.ps.ucpark.domain.Estacionamiento;

public interface IEstacionamientosDAO {
	
	/**
	 * Crea un nuevo estacionamiento.
	 * 
	 * @param estacionamiento Estacionamiento nuevo a registrar.
	 * @return estacionamiento nuevo registrado.
	 */
	public Estacionamiento creaEstacionamiento(Estacionamiento estacionamiento);
	
	/**
	 * Obtiene la lista de estacionamientos registrados.
	 * 
	 * @return lista de estacionamientos registrados.
	 */
	public List<Estacionamiento> estacionamientos();
	
	/**
	 * Modifica un estacionamiento concreto.
	 * @param estacionamiento Estacionamiento modificado.
	 * @return estacionamiento modificado.
	 */
	public Estacionamiento modificaEstacionamiento(Estacionamiento estacionamiento);
	
	/**
	 * Elimina un estacionamiento concreto.
	 * @param estacionamiento Estacionamiento a eliminar.
	 * @return estacionamiento eliminado.
	 */
	public Estacionamiento eliminaEstacionamiento(Estacionamiento estacionamiento);
	
	/**
	 * Obtiene un estacionamiento concreto en base a su ID.
	 * 
	 * @param idEstacionamiento ID del estacionamiento a obtener.
	 * @return estacionamiento cuyo ID se corresponde con el indicado.
	 *         null si no existe ningun estacionamiento con el ID especificado.
	 */
	public Estacionamiento estacionamientoPorId(String idEstacionamiento);
	

}
