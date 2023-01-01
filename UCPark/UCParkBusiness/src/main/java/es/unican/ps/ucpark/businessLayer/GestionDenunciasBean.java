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
public class GestionDenunciasBean implements IDenunciasAgentesLocal, 
	IDenunciasAgentesRemote, IDenunciasUsuariosLocal, IDenunciasUsuariosRemote, 
	IConsultaDenunciasLocal, IConsultaDenunciasRemote {
	
	@EJB
	private IDenunciasDAOLocal denunciasDAO;
	
	@EJB
	private IVehiculosDAOLocal vehiculosDAO;
	
	/**
	 * Constructor por defecto.
	 */
	public GestionDenunciasBean() {
		
	}
	
	@Override
	public Estacionamiento obtieneUltimoEstacionamientoVehiculo(String matricula)
		throws OperacionNoValida {
		
		Vehiculo vehiculo = vehiculosDAO.vehiculoPorMatricula(matricula);
		
		if (vehiculo == null) {
			throw new OperacionNoValida("No existe un vehiculo con la matri" +
					"cula especificada.");
		}
		
		Estacionamiento ultimoEstacionamiento = vehiculo.getEstacionamientoEnVigor();
		
		if (ultimoEstacionamiento == null) {
			List<Estacionamiento> historico = vehiculo.getHistoricoEstacionamientos();
			
			if (!historico.isEmpty()) {
				ultimoEstacionamiento = historico.get(historico.size() - 1);
			}
		}
			
		return ultimoEstacionamiento;
	}
	
	
	@Override
	public Denuncia registraDenuncia(Denuncia denuncia, String matricula) 
		throws OperacionNoValida {
		
		Vehiculo vehiculo = vehiculosDAO.vehiculoPorMatricula(matricula);
		
		if (vehiculo == null) {
			throw new OperacionNoValida("No existe un vehiculo con la matri" +
					"cula especificada.");
		}
		
		Denuncia denunciaRegistrada = denuncia;
		
		if (vehiculo.getDenuncias().contains(denuncia)) {
			denunciaRegistrada = null;
		} else {
			denunciasDAO.creaDenuncia(denunciaRegistrada);
			vehiculo.getDenuncias().add(denunciaRegistrada);
			vehiculosDAO.modificaVehiculo(vehiculo);
		}
		
		return denunciaRegistrada;
	}
	
	@Override
	public Denuncia anulaDenuncia(Denuncia denuncia) {
		
		Vehiculo vehiculoDenunciado = denuncia.getVehiculoDenunciado();

		vehiculoDenunciado.getDenuncias().remove(denuncia);
		vehiculosDAO.modificaVehiculo(vehiculoDenunciado);
		
		return denunciasDAO.eliminaDenuncia(denuncia);
	}
	
	@Override
	public List<Denuncia> consultaDenunciasAcumuladas(Usuario usuario) {
		
		List<Vehiculo> vehiculos = usuario.getVehiculos();
		List<Denuncia> denuncias = new ArrayList<Denuncia>();
		
		for (Vehiculo vehiculo:vehiculos) {
			denuncias.addAll(vehiculo.getDenuncias());
		}
		
		return denuncias;
	}
}
