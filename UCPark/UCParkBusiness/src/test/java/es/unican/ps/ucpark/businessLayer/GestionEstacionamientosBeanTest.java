package es.unican.ps.ucpark.businessLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import es.unican.ps.ucpark.daoLayer.IEstacionamientosDAOLocal;
import es.unican.ps.ucpark.daoLayer.IVehiculosDAOLocal;
import es.unican.ps.ucpark.domain.Denuncia;
import es.unican.ps.ucpark.domain.Estacionamiento;
import es.unican.ps.ucpark.domain.Usuario;
import es.unican.ps.ucpark.domain.Vehiculo;

/**
 * Clase de pruebas unitarias que comprueba el funcionamiento 
 * de la clase 'GestionEstacionamientosBean'.
 * 
 * @author Mario Martin Perez
 * @version 1.0
 */
public class GestionEstacionamientosBeanTest {

	// Constantes
	private static final int MINUTOS = 60;
	private static final int AMPLIACION = 30;
	private static final int AMPLIACION_2 = 90;
	private static final double PRECIO_MINUTO = 0.10;
	
	// Mocks y sut
	private IEstacionamientosDAOLocal estacionamientosMock;
	private IVehiculosDAOLocal vehiculosMock;
	private GestionEstacionamientosBean sut;
	
	/**
	 * Inicializa los mocks y el sut de la clase de prueba antes de
	 * cada test.
	 */
	@BeforeEach
	public void inicializa() {
		estacionamientosMock = mock(IEstacionamientosDAOLocal.class);
		vehiculosMock = mock(IVehiculosDAOLocal.class);
		
		sut = new GestionEstacionamientosBean(estacionamientosMock, 
				vehiculosMock);
	}
	
	/**
	 * Comprueba el correcto funcionamiento del metodo 'ampliaEstacionamiento'.
	 */
	@Test
	public void testAmpliaEstacionamiento() {
		// Crear estacionamientos o vehiculos necesarios para la prueba.
		Vehiculo v1 = new Vehiculo();
		v1.setMatricula("1234ABC");	
		v1.setPropietario(new Usuario());
		v1.setHistoricoEstacionamientos(new ArrayList<Estacionamiento>());
		v1.setEstacionamientoEnVigor(null);
		v1.setDenuncias(new ArrayList<Denuncia>());
		
		Estacionamiento e1 = new Estacionamiento();
		e1.setId(1);
		e1.setHoraInicio(LocalDateTime.now());
		e1.setMinutos(MINUTOS);
		e1.setImporte((double) MINUTOS * PRECIO_MINUTO);
		e1.setVehiculoEstacionado(v1);
		
		Estacionamiento e2 = new Estacionamiento();
		e2.setId(1);
		e2.setHoraInicio(e1.getHoraInicio());
		e2.setMinutos(MINUTOS + AMPLIACION);
		e2.setImporte((double) (MINUTOS + AMPLIACION) * PRECIO_MINUTO);
		e2.setVehiculoEstacionado(v1);
		
		
		// Definir el comportamiento
		when(estacionamientosMock.modificaEstacionamiento(e2)).thenReturn(e2);
		
		// Comprobar el correcto funcionamiento del metodo
		assertEquals(e2, sut.ampliaEstacionamiento(e1, AMPLIACION));
		verify(estacionamientosMock).modificaEstacionamiento(e2);
		assertThrows(OperacionNoValida.class, () -> sut.ampliaEstacionamiento(e2,
				AMPLIACION_2));
		verify(estacionamientosMock, times(1)).modificaEstacionamiento(
				Mockito.any(Estacionamiento.class)); // No deberia llamar a la DAO
	}
}
