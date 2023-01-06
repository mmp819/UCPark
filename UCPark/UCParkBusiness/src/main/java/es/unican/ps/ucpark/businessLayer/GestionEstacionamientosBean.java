package es.unican.ps.ucpark.businessLayer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import es.unican.ps.ucpark.daoLayer.IEstacionamientosDAOLocal;
import es.unican.ps.ucpark.daoLayer.IUsuariosDAOLocal;
import es.unican.ps.ucpark.daoLayer.IVehiculosDAOLocal;
import es.unican.ps.ucpark.domain.Estacionamiento;
import es.unican.ps.ucpark.domain.Usuario;
import es.unican.ps.ucpark.domain.Vehiculo;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;
import jakarta.ejb.TimerService;

@Stateless
public class GestionEstacionamientosBean implements IConsultaEstacionamientosLocal, 
	IConsultaEstacionamientosRemote, IGestionEstacionamientosLocal, 
	IGestionEstacionamientosRemote {
	
	private static final double PRECIO_MINUTO = 0.10;
	private static final double MAX_MINUTOS = 120;
	
	@EJB
	IEstacionamientosDAOLocal estacionamientosDAO;
	
	@EJB
	IVehiculosDAOLocal vehiculosDAO;
	
	@EJB
	IUsuariosDAOLocal usuariosDAO;
	
	@Resource
	private TimerService timerService;
	
	/**
	 * Constructor por defecto.
	 */
	public GestionEstacionamientosBean() {
		
	}
	
	/**
	 * Constructor util para pruebas unitarias.
	 * 
	 * @param estacionamientosDAO DAO de estacionamientos.
	 * @param vehiculosDAO DAO de vehiculos.
	 */
	public GestionEstacionamientosBean(IEstacionamientosDAOLocal estacionamientosDAO,
			IVehiculosDAOLocal vehiculosDAO) {
		this.estacionamientosDAO = estacionamientosDAO;
		this.vehiculosDAO = vehiculosDAO;
	}
	
	@Override
	public Estacionamiento creaEstacionamiento(Vehiculo vehiculo, int minutos) 
			throws OperacionNoValida {
		
		if (vehiculo.getEstacionamientoEnVigor() != null) {
			throw new OperacionNoValida("Vehiculo con estacionamiento en vigor.");
		}
		
		Estacionamiento estacionamiento = new Estacionamiento(
				(double)minutos * PRECIO_MINUTO, minutos, LocalDateTime.now(),
				vehiculo);
		
		// Programacion de evento para eliminar estacionamiento en vigor
		LocalDateTime horaFin = estacionamiento.getHoraInicio().plusMinutes(minutos);
		
		timerService.createSingleActionTimer(horaFin.atZone(
				ZoneId.systemDefault()).toInstant().toEpochMilli(), null);
		
		estacionamiento = estacionamientosDAO.creaEstacionamiento(estacionamiento);
		vehiculo.setEstacionamientoEnVigor(estacionamiento);
		vehiculosDAO.modificaVehiculo(vehiculo);
		return estacionamiento;
	}
	
	@Override
	public Estacionamiento ampliaEstacionamiento(Estacionamiento estacionamiento,
			int minutos) throws OperacionNoValida {
		
		int minutosAcumulados = estacionamiento.getMinutos() + minutos;
		
		if (estacionamiento.getMinutos() + minutos > MAX_MINUTOS) {
			throw new OperacionNoValida("El tiempo acumulado total, excede" +
					" el máximo permitido.");
		}
			
		estacionamiento.setMinutos(minutosAcumulados);
		estacionamiento.setImporte(minutosAcumulados * PRECIO_MINUTO);
		return estacionamientosDAO.modificaEstacionamiento(estacionamiento);
	}
	
	@Override
	public Estacionamiento estacionamientoEnVigorDeVehiculo(String matricula) {
		
		Vehiculo vehiculo = vehiculosDAO.vehiculoPorMatricula(matricula);
		
		if (vehiculo == null) {
			throw new OperacionNoValida("No existe un vehiculo con la matri" +
					"cula especificada.");
		}
		
		return vehiculo.getEstacionamientoEnVigor();
	}

	@Override
	public List<Estacionamiento> estacionamientosEnVigorDeUsuario(String email) {
		
		Usuario usuario = usuariosDAO.usuarioPorEmail(email);
		
		if (usuario == null) {
			throw new OperacionNoValida("No existe un usuario con el email" +
					" especificado.");
		}
		
		List<Estacionamiento> estacionamientosEnVigor = new ArrayList<Estacionamiento>();
		Estacionamiento estacionamiento;
		
		for (Vehiculo vehiculo:usuario.getVehiculos()) {
			estacionamiento = vehiculo.getEstacionamientoEnVigor();
			if (estacionamiento != null) {
				estacionamientosEnVigor.add(estacionamiento);
			}
		}
		
		return estacionamientosEnVigor;
	}
	
	@Override
	public List<Estacionamiento> historicoEstacionamientosVehiculo(String matricula) {
		
		Vehiculo vehiculo = vehiculosDAO.vehiculoPorMatricula(matricula);
		
		if (vehiculo == null) {
			throw new OperacionNoValida("No existe un vehiculo con la matri" +
					"cula especificada.");
		}
		
		return vehiculo.getHistoricoEstacionamientos();
	}
	
	@Override
	public List<Estacionamiento> historicoEstacionamientosUsuario(String email) {
		Usuario usuario = usuariosDAO.usuarioPorEmail(email);
		
		if (usuario == null) {
			throw new OperacionNoValida("No existe un usuario con el email" +
					" especificado.");
		}
		
		List<Estacionamiento> historico = new ArrayList<Estacionamiento>();
		
		for (Vehiculo vehiculo:usuario.getVehiculos()) {
			historico.addAll(vehiculo.getHistoricoEstacionamientos());
		}
		
		return historico;
	}
	
	@Timeout
	public void finalizarEstacionamiento(Timer timer) {
		Vehiculo vehiculo = (Vehiculo) timer.getInfo();
		Estacionamiento estacionamientoEnVigor = vehiculo.getEstacionamientoEnVigor();
		
		vehiculo.getHistoricoEstacionamientos().add(estacionamientoEnVigor);
		vehiculo.setEstacionamientoEnVigor(null);
		
		vehiculosDAO.modificaVehiculo(vehiculo);
	}
}
