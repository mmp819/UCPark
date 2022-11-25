package es.unican.ps.ucpark.businessLayer;

import java.util.ArrayList;
import java.util.List;

import es.unican.ps.ucpark.daoLayer.IDenunciasDAOLocal;
import es.unican.ps.ucpark.daoLayer.IVehiculosDAOLocal;
import es.unican.ps.ucpark.domain.Denuncia;
import es.unican.ps.ucpark.domain.Estacionamiento;
import es.unican.ps.ucpark.domain.Usuario;
import es.unican.ps.ucpark.domain.Vehiculo;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class GestionDenunciasBean implements IDenunciasAgentesLocal, IDenunciasAgentesRemote,
	IDenunciasUsuariosLocal, IDenunciasUsuariosRemote, IConsultaDenunciasLocal,
	IConsultaDenunciasRemote {
	
	@EJB
	private IDenunciasDAOLocal denunciasDAO;
	
	@EJB
	private IVehiculosDAOLocal vehiculosDAO;
	
	
	public GestionDenunciasBean() {
	}
	

	/**
	 * Retorna el ultimo estacionamiento realizado por un vehiculo.
	 * 
	 * @param matricula Matricula del vehiculo.
	 * 
	 * @throws OperacionNoValida si no existe un vehiculo con la matricula indicada
	 * @return ultimo estacionamiento realizado por el vehiculo.
	 *         null si no hay estacionamientos registrados para el vehiculo.
	 */
	public Estacionamiento obtieneUltimoEstacionamientoVehiculo(String matricula)
		throws OperacionNoValida {
		
		Vehiculo vehiculo = vehiculosDAO.vehiculoPorMatricula(matricula);
		
		if (vehiculo == null) {
			throw new OperacionNoValida("No existe un vehiculo con la matri" +
					"cula especificada.");
		}
		
		Estacionamiento ultimoEstacionamiento = vehiculo.getEstacionamientoEnVigor();
		
		if (ultimoEstacionamiento == null) {
			List<Estacionamiento> historico = vehiculo.getHistoricoEstacionammientos();
			
			if (!historico.isEmpty()) {
				ultimoEstacionamiento = historico.get(historico.size() - 1);
			}
		}
			
		return ultimoEstacionamiento;
	}
	
	
	/**
	 * Registra una denuncia y la asocia a un vehiculo.
	 * 
	 * @param denuncia Denuncia a registrar.
	 * @parma matricula Matricula del vehiculo infractor.
	 * 
	 * @throws OperacionNoValida si no existe un vehiculo con la matricula indicada.
	 * @return denuncia registrada.
	 *         null si la denuncia a registrar ya existia en el vehiculo.
	 */
	public Denuncia registraDenuncia(Denuncia denuncia, String matricula) 
		throws OperacionNoValida {
		
		Vehiculo vehiculo = vehiculosDAO.vehiculoPorMatricula(matricula);
		
		if (vehiculo == null) {
			throw new OperacionNoValida("No existe un vehiculo con la matri" +
					"cula especificada.");
		}
		
		Denuncia denunciaRegistrada = denuncia;
		
		if (vehiculo.getHistoricoDenuncias().contains(denuncia)) {
			denunciaRegistrada = null;
		} else {
			denunciasDAO.creaDenuncia(denunciaRegistrada);
			vehiculo.getHistoricoDenuncias().add(denunciaRegistrada);
			vehiculosDAO.modificaVehiculo(vehiculo);
		}
		
		return denunciaRegistrada;
	}
	
	/**
	 * Anula una denuncia concreta.
	 * 
	 * @param denuncia Denuncia a anular.
	 * @return denuncia anulada.
	 */
	public Denuncia anulaDenuncia(Denuncia denuncia) {
		
		Vehiculo vehiculoDenunciado = denuncia.getVehiculoDenunciado();

		vehiculoDenunciado.getHistoricoDenuncias().remove(denuncia);
		vehiculosDAO.modificaVehiculo(vehiculoDenunciado);
		
		return denunciasDAO.eliminaDenuncia(denuncia);
	}
	
	/**
	 * Consulta las denuncias acumuladas vigentes de un usuario.
	 * 
	 * @param usuario Usuario del que se quieren consultar las denuncias.
	 * @return denuncias vigentes acumuladas por el usuario especificado.
	 */
	public List<Denuncia> consultaDenunciasAcumuladas(Usuario usuario) {
		
		List<Vehiculo> vehiculos = usuario.getVehiculos();
		List<Denuncia> denuncias = new ArrayList<Denuncia>();
		
		for (Vehiculo vehiculo:vehiculos) {
			denuncias.addAll(vehiculo.getHistoricoDenuncias());
		}
		
		return denuncias;
	}
}
