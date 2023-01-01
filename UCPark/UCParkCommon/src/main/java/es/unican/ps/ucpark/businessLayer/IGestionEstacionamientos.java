package es.unican.ps.ucpark.businessLayer;

import es.unican.ps.ucpark.domain.Estacionamiento;
import es.unican.ps.ucpark.domain.Vehiculo;

public interface IGestionEstacionamientos {

	/**
	 * Crea un estacionamiento de una duracion determinada para un vehiculo concreto.
	 * 
	 * @param vehiculo Vehiculo para el que se quiere crear un estacionamiento.
	 * @param minutos Numero de minutos que durara el estacionamiento.
	 * 
	 * @throws OperacionNoValida si el vehiculo tiene estacionamiento en vigor.
	 * @return estacionamiento creado para el vehiculo y duración deseados.
	 *         null si hay un error al almacenar el estacionamiento.
	 */
	public Estacionamiento creaEstacionamiento(Vehiculo vehiculo, int minutos)
		throws OperacionNoValida;
	
	/**
	 * Amplia un estacionamiento existente una duracion determinada.
	 * 
	 * @param estacionamiento Estacionamiento a ser ampliado.
	 * @param minutos Tiempo a ampliar el estacionamiento en minutos.
	 * @return estacionamiento ampliado.
	 *         null si hay un error al modificar el estacionamiento.
	 */
	public Estacionamiento ampliaEstacionamiento(Estacionamiento estacionamiento, 
			int minutos) throws OperacionNoValida;
}
