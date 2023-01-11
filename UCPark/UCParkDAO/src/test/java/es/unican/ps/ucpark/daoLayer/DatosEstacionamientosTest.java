package es.unican.ps.ucpark.daoLayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import es.unican.ps.ucpark.domain.Denuncia;
import es.unican.ps.ucpark.domain.Estacionamiento;
import es.unican.ps.ucpark.domain.Usuario;
import es.unican.ps.ucpark.domain.Vehiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class DatosEstacionamientosTest {

	// Constantes
	private static final int MINUTOS = 60;
	private static final double PRECIO_MINUTO = 0.10;
	private static final int AMPLIACION = 30;
		
	// Mocks y sut
	private EntityManager entityManagerMock;
	private DatosEstacionamientos sut;
	
	/**
	 * Inicializa los mocks y el sut de la clase de prueba antes de
	 * cada test.
	 */
	@BeforeEach
	public void inicializa() {
		entityManagerMock = mock(EntityManager.class);		
		sut = new DatosEstacionamientos(entityManagerMock);
	}
	
	/**
	 * Comprueba el correcto funcionamiento del metodo 'creaEstacionamiento'.
	 */
	@Test
	public void testCreaEstacionamiento() {
		// Crear estacionamientos o vehiculos necesarios para la prueba.
		Vehiculo v1 = new Vehiculo();
		v1.setMatricula("1234ABC");	
		v1.setPropietario(new Usuario());
		v1.setHistoricoEstacionamientos(new ArrayList<Estacionamiento>());
		v1.setEstacionamientoEnVigor(null);
		v1.setDenuncias(new ArrayList<Denuncia>());
		
		Vehiculo v2 = new Vehiculo();
		v2.setMatricula("5678DEF");	
		v2.setPropietario(new Usuario());
		v2.setHistoricoEstacionamientos(new ArrayList<Estacionamiento>());
		v2.setEstacionamientoEnVigor(null);
		v2.setDenuncias(new ArrayList<Denuncia>());
				
		Estacionamiento e1 = new Estacionamiento();
		e1.setHoraInicio(LocalDateTime.now());
		e1.setMinutos(MINUTOS);
		e1.setImporte((double) MINUTOS * PRECIO_MINUTO);
		e1.setVehiculoEstacionado(v1);
		
		Estacionamiento e2 = new Estacionamiento();
		e2.setId(2);
		e2.setHoraInicio(e1.getHoraInicio());
		e2.setMinutos(e1.getMinutos());
		e2.setImporte(e1.getImporte());
		e2.setVehiculoEstacionado(e1.getVehiculoEstacionado());
		
		Estacionamiento e3 = new Estacionamiento();
		e3.setId(1);
		e3.setHoraInicio(LocalDateTime.now());
		e3.setMinutos(MINUTOS);
		e3.setImporte((double) MINUTOS * PRECIO_MINUTO);
		e3.setVehiculoEstacionado(v2);
		
		// Definir el comportamiento
		Answer<Void> answer = new Answer<>() {
	        public Void answer(InvocationOnMock invocation) throws Throwable {
	            e1.setId(2);
				return null;
	        }
	    };
		doAnswer(answer).when(entityManagerMock).persist(e1);
		doThrow(new PersistenceException()).when(entityManagerMock).persist(e3);
		
		// Comprobar el correcto funcionamiento del metodo
		assertEquals(e2, sut.creaEstacionamiento(e1)); // Asigna ID
		verify(entityManagerMock).persist(e1);
		assertEquals(null, sut.creaEstacionamiento(e3));
		verify(entityManagerMock).persist(e3);
	}
	
	/**
	 * Comprueba el correcto funcionamiento del metodo 'modificaEstacionamiento'.
	 */
	@Test
	public void testModificaEstacionamiento() {
		// Crear estacionamientos o vehiculos necesarios para la prueba.
		Vehiculo v1 = new Vehiculo();
		v1.setMatricula("1234ABC");	
		v1.setPropietario(new Usuario());
		v1.setHistoricoEstacionamientos(new ArrayList<Estacionamiento>());
		v1.setEstacionamientoEnVigor(null);
		v1.setDenuncias(new ArrayList<Denuncia>());
				
		Estacionamiento e1 = new Estacionamiento();
		e1.setHoraInicio(LocalDateTime.now());
		e1.setMinutos(MINUTOS);
		e1.setImporte((double) MINUTOS * PRECIO_MINUTO);
		e1.setVehiculoEstacionado(v1);
		e1.setId(2);
		
		Estacionamiento e2 = new Estacionamiento();
		e2.setId(2);
		e2.setHoraInicio(e1.getHoraInicio());
		e2.setMinutos(e1.getMinutos() + AMPLIACION);
		e2.setImporte((double) (MINUTOS + AMPLIACION) * PRECIO_MINUTO);
		e2.setVehiculoEstacionado(e1.getVehiculoEstacionado());
		
		Estacionamiento e3 = new Estacionamiento();
		e1.setHoraInicio(LocalDateTime.now());
		e1.setMinutos(MINUTOS);
		e1.setImporte((double) MINUTOS * PRECIO_MINUTO);
		e1.setVehiculoEstacionado(v1);
		e1.setId(3);
		
		// Definir el comportamiento
		when(entityManagerMock.merge(e2)).thenReturn(e2);
		when(entityManagerMock.merge(e3)).thenThrow(new PersistenceException());
		
		// Comprobar el correcto funcionamiento del metodo
		assertEquals(e2, sut.modificaEstacionamiento(e2));
		verify(entityManagerMock).merge(e2);
		assertEquals(null, sut.modificaEstacionamiento(e3));
		verify(entityManagerMock).merge(e3);
	}
}
