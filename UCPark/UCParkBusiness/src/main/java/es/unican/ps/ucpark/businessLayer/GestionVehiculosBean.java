package es.unican.ps.ucpark.businessLayer;

import java.util.ArrayList;
import java.util.Iterator;
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
	public List<Vehiculo> consultaVehiculosRegistrados(String email) 
		throws OperacionNoValida {
		
		Usuario propietario = usuariosDAO.usuarioPorEmail(email);
		
		if (propietario == null) {
			throw new OperacionNoValida("No existe un usuario con el email" + 
					" especificado.");
		}
		
		return propietario.getVehiculos();
	}
	
	@Override
	public List<String> consultaMatriculasRegistradas(String email) 
			throws OperacionNoValida {
		List<Vehiculo> vehiculos = this.consultaVehiculosRegistrados(email);
		List<String> matriculas = new ArrayList<>();
		
		for (Vehiculo v:vehiculos) {
			matriculas.add(v.getMatricula());
		}
		
		return matriculas;
	}
	
	@Override
	public Vehiculo consultaVehiculoRegistrado(String email, String matricula) 
		throws OperacionNoValida {
		
		Usuario propietario = usuariosDAO.usuarioPorEmail(email);
		
		if (propietario == null) {
			throw new OperacionNoValida("No existe un usuario con el email" + 
					" especificado.");
		}
		
		List<Vehiculo> vehiculos = propietario.getVehiculos();
		Iterator<Vehiculo> iterador = vehiculos.iterator();
		
		boolean encontrado = false;
		Vehiculo v = null;
		
		while (!encontrado && iterador.hasNext()) {
			v = iterador.next();
			if (v.getMatricula().equals(matricula)) {
				encontrado = true;
			}
		}
		
		if (!encontrado) {
			v = null;
		}

		return v;
	}
	
}
