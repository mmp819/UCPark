package es.unican.ps.ucpark.businessLayer;

import es.unican.ps.ucpark.domain.Estacionamiento;
import es.unican.ps.ucpark.domain.Vehiculo;

public interface IGestionEstacionamientos {

	/**
	 * Crea un estacionamiento de una duracion determinada para un vehiculo concreto.
	 * 
	 * @param vehiculo Vehiculo para el que se quiere crear un estacionamiento.
	 * @param minutos Numero de minutos que durara el estacionamiento.
	 * @return estacionamiento creado para el vehiculo y duración deseados.
	 */
	public Estacionamiento creaEstacionamiento(Vehiculo vehiculo, int minutos);
	
	/**
	 * Amplia un estacionamiento existente una duracion determinada.
	 * 
	 * @param estacionamiento Estacionamiento a ser ampliado.
	 * @param minutos Tiempo a ampliar el estacionamiento en minutos.
	 * @return estacionamiento ampliado.
	 */
	public Estacionamiento ampliaEstacionamiento(Estacionamiento estacionamiento, int minutos);
}
