package es.unican.ps.ucpark.businessLayer;

import java.util.List;

import es.unican.ps.ucpark.daoLayer.IUsuariosDAOLocal;
import es.unican.ps.ucpark.daoLayer.IVehiculosDAOLocal;
import es.unican.ps.ucpark.domain.Usuario;
import es.unican.ps.ucpark.domain.Vehiculo;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class GestionVehiculosBean implements IGestionVehiculosLocal, 
	IConsultaVehiculosRemote {
	
	@EJB
	private IUsuariosDAOLocal usuariosDAO;
	
	@EJB
	private IVehiculosDAOLocal vehiculosDAO;
	
	
	public GestionVehiculosBean() {
	}
	
	@Override
	public Vehiculo registraVehiculo(Vehiculo vehiculo, String email) 
		throws OperacionNoValida {
		
		Usuario usuario = usuariosDAO.usuarioPorEmail(email);
		
		if (usuario == null) {
			throw new OperacionNoValida("No existe un usuario para el email " +
					"indicado.");
		}
		
		Vehiculo vehiculoRegistrado;
		
		if (usuario.getVehiculos().contains(vehiculo)) {
			vehiculoRegistrado = null;
		} else {
			vehiculoRegistrado = vehiculosDAO.creaVehiculo(vehiculo);
			if (vehiculoRegistrado == null) { // Permite el mismo vehiculo para varias personas
				vehiculoRegistrado = vehiculo;
			}
			usuario.getVehiculos().add(vehiculoRegistrado);
			usuariosDAO.modificaUsuario(usuario);
		}
		
		return vehiculoRegistrado;
	}
	
	@Override
	public Vehiculo eliminaVehiculo(Vehiculo vehiculo) {
		
		Usuario propietario = vehiculo.getPropietario();
		
		propietario.getVehiculos().remove(vehiculo);
		usuariosDAO.modificaUsuario(propietario);
		
		return vehiculosDAO.eliminaVehiculo(vehiculo);
	}
	
	@Override
	public List<Vehiculo> consultaVehiculosRegistrados(String email) {
		
		Usuario propietario = usuariosDAO.usuarioPorEmail(email);
		
		if (propietario == null) {
			throw new OperacionNoValida("No existe un usuario con el email" + 
					" especificado.");
		}
		
		return propietario.getVehiculos();
	}
}
