package es.unican.ps.ucpark.businessLayer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import es.unican.ps.ucpark.daoLayer.IEstacionamientosDAOLocal;
import es.unican.ps.ucpark.daoLayer.IUsuariosDAOLocal;
import es.unican.ps.ucpark.daoLayer.IVehiculosDAOLocal;
import es.unican.ps.ucpark.domain.Estacionamiento;
import es.unican.ps.ucpark.domain.Usuario;
import es.unican.ps.ucpark.domain.Vehiculo;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

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
	
	public GestionEstacionamientosBean() {
		
	}
	
	public Estacionamiento creaEstacionamiento(Vehiculo vehiculo, int minutos) 
	throws OperacionNoValida {
		
		if (vehiculosDAO.vehiculoPorMatricula(vehiculo.getMatricula()) == null) {
			throw new OperacionNoValida("No existe un vehiculo con la matri" +
					"cula especificada.");
		}
		
		Estacionamiento estacionamiento = new Estacionamiento(
				(double)minutos * PRECIO_MINUTO, minutos, LocalDateTime.now(),
				vehiculo);
		
		vehiculo.getHistoricoEstacionamientos().add(estacionamiento);
		vehiculosDAO.modificaVehiculo(vehiculo);
		return estacionamientosDAO.creaEstacionamiento(estacionamiento);
	}
	
	public Estacionamiento ampliaEstacionamiento(Estacionamiento estacionamiento,
			int minutos) {
		
		int minutosAcumulados = estacionamiento.getMinutos() + minutos;
		
		if (estacionamiento.getMinutos() + minutos > MAX_MINUTOS) {
			throw new OperacionNoValida("El tiempo acumulado total, excede" +
					" el máximo permitido.");
		}
			
		estacionamiento.setMinutos(minutosAcumulados);
		return estacionamientosDAO.modificaEstacionamiento(estacionamiento);
	}
	
	public Estacionamiento estacionamientoEnVigorDeVehiculo(String matricula) {
		
		Vehiculo vehiculo = vehiculosDAO.vehiculoPorMatricula(matricula);
		
		if (vehiculo == null) {
			throw new OperacionNoValida("No existe un vehiculo con la matri" +
					"cula especificada.");
		}
		
		return vehiculo.getEstacionamientoEnVigor();
	}

	public List<Estacionamiento> estacionamientosEnVigorDeUsuario(int idUsuario) {
		
		Usuario usuario = usuariosDAO.usuarioPorId(idUsuario);
		
		if (usuario == null) {
			throw new OperacionNoValida("No existe un usuario con el ID" +
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
	
	public List<Estacionamiento> historicoEstacionamientosVehiculo(String matricula) {
		
		Vehiculo vehiculo = vehiculosDAO.vehiculoPorMatricula(matricula);
		
		if (vehiculo == null) {
			throw new OperacionNoValida("No existe un vehiculo con la matri" +
					"cula especificada.");
		}
		
		return vehiculo.getHistoricoEstacionamientos();
	}
	
	public List<Estacionamiento> historicoEstacionamientosUsuario(int idUsuario) {
		Usuario usuario = usuariosDAO.usuarioPorId(idUsuario);
		
		if (usuario == null) {
			throw new OperacionNoValida("No existe un usuario con el ID" +
					" especificado.");
		}
		
		List<Estacionamiento> historico = new ArrayList<Estacionamiento>();
		
		for (Vehiculo vehiculo:usuario.getVehiculos()) {
			historico.addAll(vehiculo.getHistoricoEstacionamientos());
		}
		
		return historico;
	}
}
