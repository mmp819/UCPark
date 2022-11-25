package es.unican.ps.ucpark.businessLayer;

import java.time.LocalDateTime;

import es.unican.ps.ucpark.daoLayer.IEstacionamientosDAO;
import es.unican.ps.ucpark.domain.Estacionamiento;
import es.unican.ps.ucpark.domain.Vehiculo;

/**public class GestionEstacionamientos implements IConsultaEstacionamientos, 
	IGestionEstacionamientos {
	
	IEstacionamientosDAO estacionamientosDAO;
	
	private static final double PRECIO_MINUTO = 0.10;
	public GestionEstacionamientos() {
		
	}
	
	public Estacionamiento creaEstacionamiento(Vehiculo vehiculo, int minutos) {
		
		Estacionamiento estacionamiento = new Estacionamiento(
				(double)minutos * PRECIO_MINUTO, minutos, LocalDateTime.now());
		
		if (estacionamientosDAO)
		
	}

}*/
