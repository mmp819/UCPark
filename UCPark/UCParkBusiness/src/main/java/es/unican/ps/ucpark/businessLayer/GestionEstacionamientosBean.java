package es.unican.ps.ucpark.businessLayer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
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
import jakarta.ejb.TimerConfig;
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
			IVehiculosDAOLocal vehiculosDAO, TimerService timerService) {
		this.estacionamientosDAO = estacionamientosDAO;
		this.vehiculosDAO = vehiculosDAO;
		this.timerService = timerService;
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
		
		long mili1 = horaFin.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		long mili2 = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		
		timerService.createSingleActionTimer(mili1 - mili2, new TimerConfig(estacionamiento, true));
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
					" el m??ximo permitido.");
		}
			
		estacionamiento.setMinutos(minutosAcumulados);
		estacionamiento.setImporte(minutosAcumulados * PRECIO_MINUTO);
		
		Collection<Timer> timers = timerService.getAllTimers();
		
		for (Timer timer:timers) {
			Estacionamiento aux = (Estacionamiento) timer.getInfo();
			if (aux.equals(estacionamiento)) {
				timer.cancel();
				
				long mili1 = estacionamiento.getHoraInicio().plusMinutes(estacionamiento.getMinutos()).
						atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
				long mili2 = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
				timerService.createSingleActionTimer(mili1 - mili2, new TimerConfig(estacionamiento, true));
			}
		}
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
		Estacionamiento est = (Estacionamiento) timer.getInfo();
		Vehiculo vehiculo = est.getVehiculoEstacionado();
		
		vehiculo.getHistoricoEstacionamientos().add(est);
		vehiculo.setEstacionamientoEnVigor(null);
		
		vehiculosDAO.modificaVehiculo(vehiculo);
	}
}
